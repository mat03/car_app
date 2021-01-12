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

    public Long getId() {
        return id;
    }

    public String getMark() {
        return mark;
    }

    public String getModel() {
        return model;
    }

    public Color getColor() {
        return color;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
