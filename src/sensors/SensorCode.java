package sensors;

public enum SensorCode {
    SENSOR_1("Sensor 1"),
    SENSOR_2("Sensor 2"),
    SENSOR_3("Sensor 3");

    private final String name;

    private SensorCode(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static SensorCode valueOfName(String name) {
        for (SensorCode code : values()) {
            if (code.toString().equals(name)) {
                return code;
            }
        }
        throw new IllegalArgumentException("Invalid SensorCode name: " + name);
    }
}
