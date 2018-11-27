package com.algs;

import com.wt.libs.StdIn;
import com.wt.libs.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item> {
    private int n;
    private Node<Item> head;

    public Stack() {
        this.head = null;
        n = 0;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item: this) {
            s.append(item).append(' ');
        }
        return s.toString();
    }

    public void push(Item item) {
        Node<Item> oldHead = head;
        head = new Node<Item>();
        head.item = item;
        head.next = oldHead;
        n++;
    }

    public Item pop(){
        if (isEmpty()) throw new NoSuchElementException();
        Item item = head.item;
        head = head.next;
        n--;
        return item;
    }

    public int size(){
        return n;
    }
    public boolean isEmpty(){
        return head == null;
    }
    @Override
    public Iterator<Item> iterator() {
        return new MyIterator<Item>(head);
    }

    private class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    private class MyIterator<Item> implements Iterator<Item> {
        private Node<Item> current;
        public MyIterator(Node<Item> head) {
            current = head;
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

    /**
     * Unit tests the {@code Stack} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                stack.push(item);
            else if (!stack.isEmpty())
                StdOut.print(stack.pop() + " ");
        }
        StdOut.println("(" + stack.size() + " left on stack)");

        for(String s: stack) {
            System.out.println(s);
        }
    }
}
