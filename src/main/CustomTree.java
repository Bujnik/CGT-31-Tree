package main;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;
    int size = 0;

    public CustomTree() {
        this.root = new Entry<>("0");
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }
    // Methods above are not supported
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(String s) {
        //We create queue of our entries, starting with root
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        //New node is created and its name is the passed string
        Entry<String> newNode = new Entry<>(s);
        while (!queue.isEmpty()){
            //We take first element from the queue and check if we can add it to either side of the tree,
            //if not, we add children and move on
            Entry<String> node = queue.poll();
            if (node.isAvailableToAddChildren()){
                if(node.availableToAddLeftChildren){
                    node.leftChild = newNode;
                    node.availableToAddLeftChildren = false;
                }
                else{
                    node.rightChild = newNode;
                    node.availableToAddRightChildren = false;
                }
                newNode.parent = node;
                size++;
                return true;
            }
            else {
                if (node.leftChild != null) queue.add(node.leftChild);
                if (node.rightChild != null) queue.add(node.rightChild);
            }
        }
        restore();
        add(s);
        return true;
    }

    private void restore() {
        //We create queue of our entries, starting with root
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            //We check if we have null child fields, if so, we reset availability check
            Entry<String> node = queue.poll();
            if (!node.isAvailableToAddChildren()){
                if(node.leftChild == null && node.rightChild == null) {
                    //We change state only if both children are null
                    node.availableToAddLeftChildren = true;
                    node.availableToAddRightChildren = true;
                }
            }
            if (node.leftChild != null) queue.add(node.leftChild);
            if (node.rightChild != null) queue.add(node.rightChild);
        }
    }

    public String getParent(String s){
        //We add to the queue our root
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){

            //We check if current node elementName equals our string
            Entry<String> node = queue.poll();
            if (node.equals(root)) {
                //We have to check if passed string points to the root
                if (s.equals(node.elementName)) return null;
            }
            else if(node.elementName.equals(s)){
                return node.parent.elementName;
            }
            //If we have children, we add them to the queue
            if (node.leftChild != null) queue.add(node.leftChild);
            if (node.rightChild != null) queue.add(node.rightChild);
        }
        return null;
    }

    @Override
    public boolean remove(Object o) {
        if (!(o instanceof String)) throw new UnsupportedOperationException();
        //We add to the queue our root
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            //We check if current node elementName equals our string
            Entry<String> node = queue.poll();
            if (node.elementName.equals(o)) {
                //We check if current element is root, we reset root to initial state
                if (node.equals(root)){
                    root = new Entry<>("0");
                    size = 0;
                }
                else {
                    Entry<String> parent = node.parent;
                    size -= (countNodes(node) + 1);
                    if (parent.leftChild != null && parent.leftChild.equals(node)) {
                        parent.leftChild = null;
                    }
                    else {
                        parent.rightChild = null;
                    }
                    //This method will not restore ability to add nodes to given parent
                    //Only when ability is lost for adding new members, it will be restored
                    return true;
                }
            }
            //If we have children, we add them to the queue
            if (node.leftChild != null) queue.add(node.leftChild);
            if (node.rightChild != null) queue.add(node.rightChild);
        }
        return false;
    }

    private int countNodes(Entry<String> s){
        //This method returns number of nodes from given node
        int count = -1;
        //We create queue of our entries, starting with root
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty()){
            //We take first element from the queue and check if we can add it to either side of the tree,
            //if not, we add children and move on
            Entry<String> node = queue.poll();
            count++;
            //If we have children, we add them to the queue
            if (!node.availableToAddRightChildren) queue.add(node.rightChild);
            if (!node.availableToAddLeftChildren) queue.add(node.leftChild);
        }
        return count;
    }

    static class Entry<T> implements Serializable{
        String elementName;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;
        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }

        @Override
        public String toString() {
            return elementName;
        }

        public boolean isAvailableToAddChildren(){
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }
}
