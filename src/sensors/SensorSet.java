package sensors;

import datastructures.List;
import datastructures.Queue;
import datastructures.Stack;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SensorSet {
    private Temperature temperature;
    private Humidity humidity;
    private Co2 co2;

    private LocalDateTime dateTime;
    private SensorCode sensorName;

    public SensorSet(SensorCode sensorCode, double temperature, double humidity, double co2, LocalDateTime dateTime) {
        this.sensorName = sensorCode;
        this.temperature = new Temperature(sensorCode, temperature, dateTime);
        this.humidity = new Humidity(sensorCode, humidity, dateTime);
        this.co2 = new Co2(sensorCode, co2, dateTime);
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Temperature getTemperature() {
        if (temperature == null) {
            throw new IllegalStateException("Temperature is empty");
        }
        return temperature;
    }

    public Humidity getHumidity() {
        if (humidity == null) {
            throw new IllegalStateException("Humidity is empty");
        }
        return humidity;
    }

    public Co2 getCO2() {
        if (co2 == null) {
            throw new IllegalStateException("CO2 is empty");
        }
        return co2;
    }

    public void printData() {
        System.out.println("------" + sensorName + "------");
        String dateFormat = "dd/MM/yy - HH:mm";
        System.out.println("Data e Hora: " + dateTime.format(DateTimeFormatter.ofPattern(dateFormat)));
        System.out.printf("\tTemperatura: %.2f°C\n", temperature.getValue());
        System.out.printf("\tUmidade: %.2f%%\n", humidity.getValue());
        System.out.printf("\tCO2: %.2fppm\n", co2.getValue());
        System.out.println();
    }

    public SensorCode getSensorCode() {
        return sensorName;
    }
}

