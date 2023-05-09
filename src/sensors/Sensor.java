package sensors;

import java.time.LocalDateTime;


public abstract class Sensor {
    private SensorCode sensorCode;
    private double value;
    private LocalDateTime readingTime;

    public Sensor(SensorCode sensorName, double value, LocalDateTime hour) {
        this.sensorCode = sensorName;
        this.value = value;
        this.readingTime = hour;
    }

    public SensorCode getSensorCode() {
        return sensorCode;
    }

    public double getValue() {
        return value;
    }

    public LocalDateTime getReadingTime() {
        return readingTime;
    }

    public void setValue(double value) {
        this.value = value;
        this.readingTime = LocalDateTime.now();
    }

    public abstract String getType();

    @Override
    public String toString() {
        return "[" + sensorCode + ": " + value + " - " + readingTime + "]\n";
    }
}

