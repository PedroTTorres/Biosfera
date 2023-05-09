package sensors;

import java.time.LocalDateTime;

public class Co2 extends Sensor {

    public Co2(SensorCode name, double value, LocalDateTime hour) {
        super(name, value, hour);
    }

    @Override
    public String getType() {
        return "CO2";
    }
}
