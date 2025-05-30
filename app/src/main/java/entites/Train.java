package entites;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Time;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Train {
    private String trainId;
    private String trainNo;

    private List<List<Integer>>seats;

    public Map<String, String> getStationTime() {
        return stationTime;
    }

    public void setStationTime(Map<String, String> stationTime) {
        this.stationTime = stationTime;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public List<List<Integer>> getSeats() {
        return seats;
    }

    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }

    public List<String> getStations() {
        return stations;
    }

    public void setStations(List<String> stations) {
        this.stations = stations;
    }
    public String getTrainInfo(){
        return String.format("Train ID: %s Train No: %s", trainId, trainNo);
    }

    private Map<String , String> stationTime;
    private List<String> stations;
}
