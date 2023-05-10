import Utils.DataGenerator;
import datastructures.*;
import sensors.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int ROWS = 48;
        final int COLUMNS = 3;
        final int INITIAL_VALUES = 24; //dados gerados inicialmente

        // definindo a data e hora inicial como 00:00 de ontem
        LocalDateTime dateTime = LocalDateTime.now().minusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);

        Scanner scanner = new Scanner(System.in);
        SensorSet[][] sensorSets = DataGenerator.populateSensorSets(dateTime, ROWS, COLUMNS, INITIAL_VALUES);


        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Mostrar dados por Sensor");
            System.out.println("2 - Mostrar Todos os dados");
            System.out.println("3 - Adicionar Dados a um Sensor");
            System.out.println("4 - Sair");
            SensorCode sensorCode = null;
            int option = scanner.nextInt();
            int sensorNumber = -1;
            switch (option) {
                case 1:
                    System.out.println("Selecione o número do conjuto de sensores (1-3) para mostrar os dados:");
                    sensorNumber = scanner.nextInt();
                    sensorCode = null;
                    switch (sensorNumber) {
                        case 1:
                            sensorCode = SensorCode.SENSOR_1;
                            break;
                        case 2:
                            sensorCode = SensorCode.SENSOR_2;
                            break;
                        case 3:
                            sensorCode = SensorCode.SENSOR_3;
                            break;
                        default:
                            System.out.println("Número do conjuto de sensores inválido");
                            break;
                    }
                    showSensorData(sensorSets, sensorCode);
                    break;
                case 2:
                    showAllSensorData(sensorSets);
                    break;
                case 3:
                    LocalDateTime nowdateTime = LocalDateTime.now();
                    sensorCode = null;
                    System.out.println("Selecione o número do conjuto de sensores (1-3) para adicionar os dados:");
                    sensorNumber = scanner.nextInt();
                    System.out.print("Temperatura (°C): ");
                    double temperature = scanner.nextDouble();
                    System.out.print("Umidade (%): ");
                    double humidity = scanner.nextDouble();
                    System.out.print("CO2 (ppm): ");
                    double co2 = scanner.nextDouble();

                    switch (sensorNumber) {
                        case 1:
                            sensorCode = SensorCode.SENSOR_1;
                            break;
                        case 2:
                            sensorCode = SensorCode.SENSOR_2;
                            break;
                        case 3:
                            sensorCode = SensorCode.SENSOR_3;
                            break;
                        default:
                            System.out.println("Número do conjuto de sensores inválido");
                            break;
                    }

                    if (sensorCode != null) {
                        SensorSet newSensorSet = new SensorSet(sensorCode, temperature, humidity, co2, nowdateTime);
                        addSensorSet(sensorSets, newSensorSet);
                    }
                    break;
                case 4:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }

    private static SensorSet[][] addSensorSet(SensorSet[][] sensorSets, SensorSet sensorData) {
        SensorCode sensorCode = sensorData.getSensorCode();
        int columnIndex;
        switch (sensorCode) {
            case SENSOR_1:
                columnIndex = 0;
                break;
            case SENSOR_2:
                columnIndex = 1;
                break;
            case SENSOR_3:
                columnIndex = 2;
                break;
            default:
                throw new IllegalArgumentException("sensorCode Invalido: " + sensorCode);
        }

        for (int i = 0; i < sensorSets.length; i++) {
            if (columnIndex >= sensorSets[i].length) {
                // Column does not exist, do not add sensor
                showSensorData(sensorSets, sensorCode);
                return sensorSets;
            }
            if (sensorSets[i][columnIndex] == null) {
                sensorSets[i][columnIndex] = sensorData;
                showSensorData(sensorSets, sensorCode);
                return sensorSets;
            }
        }

        showSensorData(sensorSets, sensorCode);

        return sensorSets;
    }

    private static void showSensorData(SensorSet[][] sensorSets, SensorCode sensorCode) {

        Queue<Temperature> temperatureQueue = new Queue<>(48);
        List<Humidity> humidityList = new List<>();
        Stack<Co2> co2Stack = new Stack<>(48);
        String dateFormat = "dd/MM/yy - HH:mm";

        for (int i = 0; i < sensorSets.length; i++) {
            for (int j = 0; j < sensorSets[i].length; j++) {
                if (sensorSets[i][j] != null && sensorSets[i][j].getSensorCode() == sensorCode) {
                    SensorSet actualSensorData = sensorSets[i][j];
                    temperatureQueue.enqueue(actualSensorData.getTemperature());
                    humidityList.add(actualSensorData.getHumidity());
                    co2Stack.push(actualSensorData.getCO2());
                }
            }
        }

        ListSorter.sortDescendingHumidity(humidityList);
        QueueSorter.sortAscendingTemperature(temperatureQueue);
        StackSorter.sortAscendigCo2(co2Stack);

        System.out.println("[Ordem decrescente]");
        for (int i = 0; i < humidityList.size(); i++) {
            System.out.printf("\tUmidade: %.2f%% - Data e Hora: %s\n", humidityList.get(i).getValue(), humidityList.get(i).getReadingTime().format(DateTimeFormatter.ofPattern(dateFormat)));
        }

        System.out.println("[Ordem crescente]");
        while (!temperatureQueue.isEmpty()) {
            Temperature temperature = temperatureQueue.dequeue();
            System.out.printf("\tTemperatura: %.2f°C - Data e Hora: %s\n", temperature.getValue(), temperature.getReadingTime().format(DateTimeFormatter.ofPattern(dateFormat)));
        }

        System.out.println("[Ordem crescente]");
        while (!co2Stack.isEmpty()) {
            Co2 co2 = co2Stack.pop();
            System.out.printf("\tCo2: %.2fppm - Data e Hora: %s\n", co2.getValue(), co2.getReadingTime().format(DateTimeFormatter.ofPattern(dateFormat)));
        }

    }

    private static void showAllSensorData(SensorSet[][] sensorSets) {

        Queue<Temperature> temperatureQueue = new Queue<>(48);
        List<Humidity> humidityList = new List<>();
        Stack<Co2> co2Stack = new Stack<>(48);
        String dateFormat = "dd/MM/yy - HH:mm";

        for (int i = 0; i < sensorSets.length; i++) {
            for (int j = 0; j < sensorSets[i].length; j++) {
                if (sensorSets[i][j] != null) {
                    SensorSet actualSensorData = sensorSets[i][j];
                    temperatureQueue.enqueue(actualSensorData.getTemperature());
                    humidityList.add(actualSensorData.getHumidity());
                    co2Stack.push(actualSensorData.getCO2());
                }
            }
        }

        ListSorter.sortDescendingHumidity(humidityList);
        QueueSorter.sortAscendingTemperature(temperatureQueue);
        StackSorter.sortAscendigCo2(co2Stack);

        System.out.println("[Umidade - Ordem decrescente]");
        for (int i = 0; i < humidityList.size(); i++) {
            System.out.printf("\t%s - Umidade: %.2f%% - Data e Hora: %s\n", humidityList.get(i).getSensorCode().toString(), humidityList.get(i).getValue(), humidityList.get(i).getReadingTime().format(DateTimeFormatter.ofPattern(dateFormat)));
        }

        System.out.println("[Temperatura - Ordem crescente]");
        while (!temperatureQueue.isEmpty()) {
            Temperature temperature = temperatureQueue.dequeue();
            System.out.printf("\t%s - Temperatura: %.2f°C - Data e Hora: %s\n", temperature.getSensorCode().toString(), temperature.getValue(), temperature.getReadingTime().format(DateTimeFormatter.ofPattern(dateFormat)));
        }

        System.out.println("[Co2 - Ordem crescente]");
        while (!co2Stack.isEmpty()) {
            Co2 co2 = co2Stack.pop();
            System.out.printf("\t%s Co2: %.2fppm - Data e Hora: %s\n", co2.getSensorCode().toString(), co2.getValue(), co2.getReadingTime().format(DateTimeFormatter.ofPattern(dateFormat)));
        }

    }


    private static void showFullData(SensorSet[][] sensorSets) {
        for (int i = 0; i < sensorSets.length; i++) {
            for (int j = 0; j < sensorSets[i].length; j++) {
                if (sensorSets[i][j] != null) {
                    System.out.println("Local de armazenamento[" + i + "][" + j + "]:");
                    sensorSets[i][j].printData();
                }
            }
        }
    }


}