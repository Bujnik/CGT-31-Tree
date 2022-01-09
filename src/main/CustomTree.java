package main;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
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

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void replaceAll(UnaryOperator<String> operator) {
        super.replaceAll(operator);
    }

    @Override
    public void sort(Comparator<? super String> c) {
        super.sort(c);
    }

    @Override
    public Spliterator<String> spliterator() {
        return super.spliterator();
    }

    @Override
    public boolean removeIf(Predicate<? super String> filter) {
        return super.removeIf(filter);
    }

    @Override
    public Stream<String> stream() {
        return super.stream();
    }

    @Override
    public Stream<String> parallelStream() {
        return super.parallelStream();
    }

    @Override
    public void forEach(Consumer<? super String> action) {
        super.forEach(action);
    }
}
