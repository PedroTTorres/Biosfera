package datastructures;

import sensors.Co2;

public class StackSorter {
    public static void sortAscendigCo2(Stack<Co2> stack) {
        Stack<Co2> tempStack = new Stack<>(stack.size());

        while (!stack.isEmpty()) {
            Co2 temp = stack.pop();
            while (!tempStack.isEmpty() && tempStack.peek().getValue() > temp.getValue()) {
                stack.push(tempStack.pop());
            }
            tempStack.push(temp);
        }

        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
    }
}
