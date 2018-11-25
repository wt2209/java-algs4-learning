package com.algs;

import com.wt.libs.StdIn;
import com.wt.libs.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag2<Item> implements Iterable<Item> {

    private class Node<Item>{
        private Item item;
        private Node<Item> next;
    }
    private Node<Item> first;
    private int n;

    public Bag2(){
        first = null;
        n = 0;
    }

    public void add(Item item){
        Node<Item> old = first;
        first = new Node<Item>();
        first.item = item;
        first.next = old;
        n++;
    }

    public int size(){
        return n;
    }
    public boolean isEmpty(){
        return first == null;
    }


    @Override
    public Iterator<Item> iterator() {
        return null;
    }

    private class ListIterator<Item> implements Iterator<Item>{
        private Node<Item> current;
        public ListIterator(Node<Item> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Bag<String> bag = new Bag<String>();
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
