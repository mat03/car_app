package pl.week3.car_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.week3.car_app.model.Car;
import pl.week3.car_app.model.Color;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
public class CarController {
    private List<Car> carList;

    public CarController() {
        carList = new ArrayList<>();

        carList.add(new Car(1L,"Fiat", "126p", Color.RED));
        carList.add(new Car(2L,"Fiat", "Seicento", Color.RED));
        carList.add(new Car(3L,"Pegout", "206", Color.GREEN));
        carList.add(new Car(4L,"Seat", "Altea xl", Color.WHITE));
    }

    @GetMapping
    public ResponseEntity<List<Car>> getCars() {
        return new ResponseEntity<>(carList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable long id) {
        Optional<Car> first = carList.stream().filter(c -> c.getId() == id).findFirst();
        return first.map(car -> new ResponseEntity<>(car, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity addCar(@RequestBody Car car) {
        boolean add = carList.add(car);
        if (add) {
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeCar(@PathVariable long id) {
        Optional<Car> first = carList.stream().filter(c -> c.getId() == id).findFirst();
        if (first.isPresent()) {
            carList.remove(first.get());
            return new ResponseEntity<>(first.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity modVideo(@RequestBody Car modCar) {
        Optional<Car> first = carList.stream().filter(c -> c.getId().equals(modCar.getId()) ).findFirst();
        if (first.isPresent()) {
            carList.remove(first.get());
            carList.add(modCar);
            return new ResponseEntity(modCar,HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/colour")
    public ResponseEntity queryByColor(@RequestParam String color)
    {
        Color colorToFind;
        try {
            colorToFind = Color.valueOf(color);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        List<Car> carWithTheSameColor = carList.stream().filter(c ->c.getColor() == colorToFind).collect(Collectors.toList());

        if(carWithTheSameColor.isEmpty())
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(carWithTheSameColor, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity modSelectedField(@RequestParam long id, @RequestParam String field, @RequestParam Object value)
    {
        Optional<Car> first = carList.stream().filter(c -> c.getId() == id).findFirst();

        if(first.isEmpty())
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Car modCar = first.get();
        carList.remove(modCar);

        try {
            Field carField = Car.class.getField(field);
            carField.set(modCar, value);
        }catch (NoSuchFieldException noSuchFieldException)
        {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        catch (IllegalAccessException e) {
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }
        carList.add(modCar);

        return new ResponseEntity(modCar,HttpStatus.NOT_FOUND);
    }

}
