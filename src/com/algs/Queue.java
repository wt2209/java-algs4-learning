package com.algs;

import com.wt.libs.StdIn;
import com.wt.libs.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {
    private int n;
    private Node<Item> first;
    private Node<Item> last;

    private class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public Queue() {
        first = null;
        last = null;
        n = 0;
    }

    public void enqueue(Item item) {
        Node<Item> oldLast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        }
        else {
            oldLast.next = last;
        }
        n++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item: this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        ListIterator(Node<Item> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Unit tests the {@code Queue} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Queue<String> queue = new Queue<String>();
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
