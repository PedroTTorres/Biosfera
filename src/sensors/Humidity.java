package sensors;

import java.time.LocalDateTime;

public class Humidity extends Sensor {
    public Humidity(SensorCode name, double value, LocalDateTime hour) {
        super(name, value, hour);
    }

    @Override
    public String getType() {
        return "Humidity";
    }
}
