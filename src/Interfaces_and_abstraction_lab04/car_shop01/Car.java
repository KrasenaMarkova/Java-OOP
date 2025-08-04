package Interfaces_and_abstraction_lab04.car_shop01;

import java.io.Serializable;

public interface Car extends Serializable {

    //+TIRES: Integer
    Integer TIRES = 4;

    //+getModel(): String
    String getModel();

    //+getColor(): String
    String getColor();

    //+getHorsePower(): Integer
    Integer getHorsePower();

    //+countryProduced(): String
    String countryProduced();
}
