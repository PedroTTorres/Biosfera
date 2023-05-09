package datastructures;

public class Queue<T> {
    private int maxSize;
    private int currentSize;
    private int headIndex;
    private int tailIndex;
    private Object[] elements;

    public Queue(int maxSize) {
        this.maxSize = maxSize;
        this.currentSize = 0;
        this.headIndex = 0;
        this.tailIndex = -1;
        this.elements = new Object[maxSize];
    }

    public boolean isEmpty() {
        return (currentSize == 0);
    }

    public boolean isFull() {
        return (currentSize == maxSize);
    }

    public void enqueue(T element) {
        if (isFull()) {
            throw new RuntimeException("Queue is full!");
        }

        tailIndex++;
        if (tailIndex == maxSize) {
            tailIndex = 0;
        }

        elements[tailIndex] = element;
        currentSize++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty!");
        }

        T element = (T) elements[headIndex];
        headIndex++;
        if (headIndex == maxSize) {
            headIndex = 0;
        }

        currentSize--;
        return element;
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty!");
        }

        return (T) elements[headIndex];
    }

    public int size() {
        return currentSize;
    }
}