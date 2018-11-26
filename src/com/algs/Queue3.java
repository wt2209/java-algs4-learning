package src.com.algs;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue3<Item> implements Iterable<Item>{
    private Node<Item> first;
    private Node<Item> last;
    private int n;

    public Queue3() {
        first = last = null;
        n = 0;
    }
    public void enqueue(Item item) {
        Node<Item> oldLast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;

//        last = new Node<Item>();
//        last.item = item;
//        last.next = null;
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
            if (isEmpty()) {
                last = null;
            }

            return item;
        }
        throw new NoSuchElementException();
    }
    public boolean isEmpty(){
        return first == null;
    }
    public int size(){
        return n;
    }
    @Override
    public Iterator<Item> iterator() {
        return new MyIterator<Item>(first);
    }

    private class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    private class MyIterator<Item> implements Iterator<Item> {
        private Node<Item> current;
        public MyIterator(Node<Item> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return first != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }


    public static void main(String[] args) {
        Queue3<String> queue = new Queue3<>();
        while (!com.wt.libs.StdIn.isEmpty()) {
            String item = com.wt.libs.StdIn.readString();
            if (!item.equals("-"))
                queue.enqueue(item);
            else if (!queue.isEmpty())
                com.wt.libs.StdOut.print(queue.dequeue() + " ");
        }
        com.wt.libs.StdOut.println("(" + queue.size() + " left on queue)");

        for(String item: queue) {
            com.wt.libs.StdOut.println(queue.dequeue());
        }
    }
}
