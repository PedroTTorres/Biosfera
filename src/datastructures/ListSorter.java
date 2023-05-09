package datastructures;

import sensors.Humidity;

public class ListSorter {
    public static void sortDescendingHumidity(List<Humidity> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Humidity element1 = list.get(j);
                Humidity element2 = list.get(j + 1);
                if (element1.getValue() < element2.getValue()) {
                    list.remove(j);
                    list.add(j + 1, element1);
                }
            }
        }
    }
}

