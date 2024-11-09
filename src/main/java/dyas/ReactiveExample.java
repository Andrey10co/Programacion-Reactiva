package dyas;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

class SensorData {
    private final String sensorId;
    private final double temperature;

    public SensorData(String sensorId, double temperature) {
        this.sensorId = sensorId;
        this.temperature = temperature;
    }

    public String getSensorId() {
        return sensorId;
    }

    public double getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return "SensorData{" +
                "sensorId='" + sensorId + '\'' +
                ", temperature=" + temperature +
                '}';
    }
}

public class ReactiveExample {
    public static void main(String[] args) {
        List<SensorData> sensorReadings = Arrays.asList(
                new SensorData("sensor1", 22.5),
                new SensorData("sensor2", 25.0),
                new SensorData("sensor1", 30.0),
                new SensorData("sensor3", 18.0),
                new SensorData("sensor2", 35.0),
                new SensorData("sensor1", 40.0),
                new SensorData("sensor1", 30.0),
                new SensorData("sensor3", 18.0),
                new SensorData("sensor2", 35.0),
                new SensorData("sensor1", 40.0),
                new SensorData("sensor3", 15.0),
                new SensorData("sensor2", 28.0),
                new SensorData("sensor1", 33.0),
                new SensorData("sensor3", 20.0),
                new SensorData("sensor2", 37.0),
                new SensorData("sensor1", 42.0),
                new SensorData("sensor3", 19.0),
                new SensorData("sensor2", 36.0),
                new SensorData("sensor1", 39.0)
        );

        Observable.fromIterable(sensorReadings)
                .map(data -> new SensorData(data.getSensorId(), data.getTemperature() * 1.8 + 32)) // Convertir a Fahrenheit
                .filter(data -> data.getTemperature() > 100 || data.getTemperature() < 32) // Filtrar temperaturas anómalas
                .buffer(3, TimeUnit.SECONDS) // Agrupar lecturas en ventanas de 3 segundos
                .flatMap(Observable::fromIterable)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        data -> System.out.println("Anomalous reading: " + data),
                        Throwable::printStackTrace,
                        () -> System.out.println("Processing complete.")
                );

        // Esperar para permitir que el procesamiento se complete
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
