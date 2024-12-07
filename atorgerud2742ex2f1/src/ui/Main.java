package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import dataaccess.SensorReadingJSONParser;
import domain.Sensor;
import domain.SensorReading;

public class Main {
    public static void main(String[] args) {
        ArrayList<SensorReading> sensorReadings = new ArrayList<SensorReading>();
        Sensor sensor = new Sensor(2,1,1,"Heat register");

        try {
            SensorReadingJSONParser.readFile("atorgerud2742ex2f1\\resources\\readings.json");
            sensor.setSensorReadings(SensorReadingJSONParser.getSensorReadings());

        } catch (Exception e) {
            System.out.println(e);
        }
        for (SensorReading r : sensorReadings) {
            System.out.println(r.toString());
        }
    }
}