package pl.week3.car_app.model;

public class Car {
    Long id;
    String mark;
    String model;
    Color color;

    public Car(Long id, String mark, String model, Color color) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.color = color;
    }
}
