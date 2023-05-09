package sensors;

import java.time.LocalDateTime;

public class Temperature extends Sensor {
    public Temperature(SensorCode name, double value, LocalDateTime hour) {
        super(name, value, hour);
    }

    @Override
    public String getType() {
        return "Temperature";
    }
}



