package Utils;

import sensors.*;

import java.time.LocalDateTime;

public class DataGenerator {
    public static SensorSet[][] populateSensorSets(LocalDateTime dateTime, int lines, int columns, int numSensorSets) {
        // populando a matriz com dados aleatórios
        SensorSet[][] sensorSets = new SensorSet[lines][columns];
        int sensorSetCounter = 0;
        for (int n = 0; n < numSensorSets; n++) {
            for (int i = 0; i < lines; i++) {
                for (int j = 0; j < columns; j++) {
                    SensorSet set = new SensorSet(SensorCode.valueOfName("Sensor " + (j + 1)), Math.random() * 10 + 20, Math.random() * 30 + 40, Math.random() * 1000 + 200, dateTime.minusDays(1).plusMinutes(i * 30L));
                    sensorSets[i][j] = set;
                    sensorSetCounter++;
                    if (sensorSetCounter == numSensorSets) {
                        return sensorSets;
                    }
                }
            }
        }
        return sensorSets;
    }

}







