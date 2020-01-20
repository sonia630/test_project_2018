package com.examples.utils;

import java.util.Iterator;

public class MyIterator<T> implements Iterator, Iterable {
    private Iterator myIterable;

    public MyIterator(Iterator myIterable) {
        this.myIterable = myIterable;
    }

    @Override
    public Iterator iterator() {
        return myIterable;
    }

    @Override
    public boolean hasNext() {
        return myIterable.hasNext();
    }

    @Override
    public Object next() {
        return myIterable.next();
    }

    @Override
    public void remove() {
        myIterable.remove();
    }

}
