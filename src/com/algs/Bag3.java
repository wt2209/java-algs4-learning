package com.algs;

import com.wt.libs.StdIn;
import com.wt.libs.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag3<Item> implements Iterable<Item>{
    private class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    private Node<Item> first;

    private int n;

    public Bag3() {
        first = null;
        n = 0;
    }

    public void add(Item item) {
        Node<Item> old = first;
        first = new Node<Item>();
        first.item = item;
        first.next = old;
        n++;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size() {
        return n;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<>(first);
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (hasNext()) {
                Item item = current.item;
                current = current.next;
                return item;
            }
            throw new NoSuchElementException();
        }
    }

    public static void main(String[] args) {
        Bag3<String> bag = new Bag3<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            bag.add(item);
        }

        StdOut.println("size of bag = " + bag.size());
        for (String s : bag) {
            StdOut.println(s);
        }
    }
}
