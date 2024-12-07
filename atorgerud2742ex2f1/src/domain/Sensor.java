package domain;

import javax.swing.tree.ExpandVetoException;
import java.util.ArrayList;
import java.util.Objects;

public class Sensor {
    private int sensorId;
    private int roomId;
    private int sensorTypeId;
    private String description;
    private Room room;
    private SensorType sensorType;
    private ArrayList<SensorReading> sensorReadings;

    public Sensor(int sensorId, int roomId, int sensorTypeId, String description) {
        this.sensorId = sensorId;
        this.roomId = roomId;
        this.sensorTypeId = sensorTypeId;
        this.description = description;
    }

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getSensorTypeId() {
        return sensorTypeId;
    }

    public void setSensorTypeId(int sensorTypeId) {
        this.sensorTypeId = sensorTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public domain.Room getRoom() {
        return room;
    }

    public void setRoom(domain.Room room) {
        this.room = room;
    }

    public domain.SensorType getSensorType() {
        return sensorType;
    }

    public void setSensorType(domain.SensorType sensorType) {
        this.sensorType = sensorType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sensor)) return false;
        Sensor sensor = (Sensor) o;
        return sensorId == sensor.sensorId &&
                roomId == sensor.roomId &&
                sensorTypeId == sensor.sensorTypeId &&
                Objects.equals(description, sensor.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sensorId, roomId, sensorTypeId, description);
    }

    @Override
    public String toString() {
        return Integer.toString(sensorId) +
                ", roomId=" + roomId +
                ", sensorTypeId=" + sensorTypeId +
                ", " + description;
    }

    public ArrayList<SensorReading> getSensorReadings() {
        return sensorReadings;
    }

    public void setSensorReadings(ArrayList<SensorReading> sensorReadings) {
        this.sensorReadings = sensorReadings;
    }

    public int findMinReading(){
        int returnValue = (int) this.sensorReadings.get(0).getValue();

        for (int i = 0; i < this.sensorReadings.size(); i++){
            if (returnValue > this.sensorReadings.get(i).getValue()){
                returnValue = (int) this.sensorReadings.get(i).getValue();
            }
        }
        return returnValue;
    }

    public int findMaxReading(){
        int returnValue = (int) this.sensorReadings.get(0).getValue();

        for (int i = 0; i < this.sensorReadings.size(); i++){
            if (returnValue < this.sensorReadings.get(i).getValue()){
                returnValue = (int) this.sensorReadings.get(i).getValue();
            }
        }
        return returnValue;
    }

    public int findMinReadingIndex(int min, int max) throws Exception {

        if (min < 0 || max < 0){
            throw new Exception("Index cannot be less than 0");
        }

        int maxSize = sensorReadings.size();
        if (min > maxSize || max > 1){
            throw new Exception("Index cannot be more than " + maxSize);
        }

        int returnValue = (int) this.sensorReadings.get(min).getValue();

        for (int i = min; i < max; i++){
            if (returnValue > this.sensorReadings.get(i).getValue()){
                returnValue = (int) this.sensorReadings.get(i).getValue();
            } else {
                break;
            }
        }
        return returnValue;
    }

    public int findMaxReadingIndex(int min, int max){
        int returnValue = (int) this.sensorReadings.get(min).getValue();

        for (int i = min; i < max; i++){
            if (returnValue < this.sensorReadings.get(i).getValue()){
                returnValue = (int) this.sensorReadings.get(i).getValue();
            }
        }
        return returnValue;
    }

    public int findNextCycleMaxIndex(int startIndex){
        SensorReading rmax = this.sensorReadings.get(startIndex);
        int i = startIndex + 1;

        for ( ; i < this.sensorReadings.size(); i++){
            if (rmax.getValue() < this.sensorReadings.get(i).getValue()){
                rmax = this.sensorReadings.get(i);
            } else {
                break;
            }
        }

        return i-1;
    }



}