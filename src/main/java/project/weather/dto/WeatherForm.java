package project.weather.dto;

public class WeatherForm {

    private String location;


    public WeatherForm(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "location" + location;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
