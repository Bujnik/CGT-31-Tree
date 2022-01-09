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
                break;
            }
            else {
                queue.add(node.leftChild);
                queue.add(node.rightChild);
            }
        }
        return true;
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
            if (!node.availableToAddRightChildren) queue.add(node.rightChild);
            if (!node.availableToAddLeftChildren) queue.add(node.leftChild);
        }
        return null;
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

        public boolean isAvailableToAddChildren(){
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }
}
