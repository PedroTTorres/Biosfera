package datastructures;

import java.util.NoSuchElementException;

public class List<T> {
    private int size;
    private ListNode<T> head;
    private ListNode<T> tail;

    public List() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void add(T element) {
        ListNode<T> newNode = new ListNode<T>(element);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        ListNode<T> newNode = new ListNode<T>(element);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
            if (isEmpty()) {
                tail = newNode;
            }
        } else if (index == size) {
            tail.next = newNode;
            tail = newNode;
        } else {
            ListNode<T> prevNode = getNode(index - 1);
            newNode.next = prevNode.next;
            prevNode.next = newNode;
        }
        size++;
    }

    public void addLast(T element) {
        ListNode<T> newNode = new ListNode<T>(element);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public T remove(int index) {
        if (isEmpty() || index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        T removedElement;
        if (index == 0) {
            removedElement = head.element;
            head = head.next;
            if (size == 1) {
                tail = null;
            }
        } else {
            ListNode<T> prevNode = getNode(index - 1);
            removedElement = prevNode.next.element;
            prevNode.next = prevNode.next.next;
            if (index == size - 1) {
                tail = prevNode;
            }
        }
        size--;
        return removedElement;
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T removedElement = tail.element;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            ListNode<T> prevNode = getNode(size - 2);
            prevNode.next = null;
            tail = prevNode;
        }
        size--;
        return removedElement;
    }

    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T removedElement = head.element;
        head = head.next;
        size--;
        if (isEmpty()) {
            tail = null;
        }
        return removedElement;
    }


    public T get(int index) {
        if (isEmpty() || index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        ListNode<T> node = getNode(index);
        return node.element;
    }

    private ListNode<T> getNode(int index) {
        ListNode<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public T getLast() {
        if (isEmpty()) {
            throw new RuntimeException("List is empty!");
        }
        return tail.element;
    }


    private static class ListNode<T> {
        T element;
        ListNode<T> next;

        public ListNode(T element) {
            this.element = element;
            this.next = null;
        }
    }
}