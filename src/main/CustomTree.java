package main;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    @Override
    public String get(int index) {
        return null;
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
