package pl.week3.car_app.controller;

import org.springframework.web.bind.annotation.RestController;
import pl.week3.car_app.model.Car;
import pl.week3.car_app.model.Color;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CarController {
    private List<Car> carList;

    public CarController() {
        carList = new ArrayList<>();

        carList.add(new Car(1L,"Fiat", "126p", Color.RED));
        carList.add(new Car(2L,"Fiat", "Seicento", Color.GREEN));
        carList.add(new Car(3L,"Pegout", "206", Color.GREEN));
        carList.add(new Car(2L,"Seat", "Altea xl", Color.WHITE));
    }
}
