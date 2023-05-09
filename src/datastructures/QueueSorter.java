package datastructures;


import sensors.Temperature;

public class QueueSorter {
    public static void sortAscendingTemperature(Queue<Temperature> queue) {
        List<Temperature> tempList = new List<>();

        while (!queue.isEmpty()) {
            Temperature temp = queue.dequeue();
            while (!tempList.isEmpty() && tempList.getLast().getValue() > temp.getValue()) {
                queue.enqueue(tempList.removeLast());
            }
            tempList.addLast(temp);
        }

        while (!tempList.isEmpty()) {
            queue.enqueue(tempList.removeFirst());
        }
    }
}
