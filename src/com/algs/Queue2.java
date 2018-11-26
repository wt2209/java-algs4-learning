package com.algs;

import com.wt.libs.StdIn;
import com.wt.libs.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue2<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int n;

    public Queue2() {
        first = last = null;
        n = 0;
    }

    public void enqueue(Item item) {
        Node<Item> oldLast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (!isEmpty()) {
            oldLast.next = last;
        } else {
            first = last;
        }
        n++;
    }

    public Item dequeue(){
        if (!isEmpty()) {
            Item item = first.item;
            first = first.next;
            n--;
            if (isEmpty()) last = null;
            return item;
        }
        throw new NoSuchElementException();
    }

    public int size(){
        return n;
    }

    public boolean isEmpty(){
        return first == null;
    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(Item item: this) {
            s.append(item).append(' ');
        }
        return s.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }

    private class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    private class ListIterator<Item> implements Iterator<Item> {
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
            if (hasNext()) {
                Item item = current.item;
                current = current.next;
            }
            throw new NoSuchElementException();
        }
    }

    public static void main(String[] args) {
        Queue2<String> queue = new Queue2<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                queue.enqueue(item);
            else if (!queue.isEmpty())
                StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println("(" + queue.size() + " left on queue)");

        while(!queue.isEmpty()) {
            StdOut.println(queue.dequeue());
        }
    }
}
