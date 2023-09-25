package project.weather.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegionForm {

    public String address;


    @Override
    public String toString() {
        return "address" + address;
    }


}
