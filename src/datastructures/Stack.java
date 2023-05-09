package datastructures;

public class Stack<T> {
    private int maxSize;
    private int currentSize;
    private Object[] elements;

    public Stack(int maxSize) {
        this.maxSize = maxSize;
        this.currentSize = 0;
        this.elements = new Object[maxSize];
    }

    public boolean isEmpty() {
        return (currentSize == 0);
    }

    public boolean isFull() {
        return (currentSize == maxSize);
    }

    public void push(T element) {
        if (isFull()) {
            throw new RuntimeException("Stack is full!");
        }

        elements[currentSize] = element;
        currentSize++;
    }

    public T pop() {
        if (isEmpty()) { // verifica se a pilha está vazia
            throw new RuntimeException("Stack is empty!");
        }

        T element = (T) elements[currentSize-1];
        currentSize--;
        return element;
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }

        return (T) elements[currentSize-1];
    }

    public int size() {
        return currentSize;
    }
}