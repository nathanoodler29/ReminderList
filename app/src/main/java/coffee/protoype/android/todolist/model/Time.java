package coffee.protoype.android.todolist.model;

/**
 * Created by Noodle on 29/05/2017.
 */

public class Time {

    private int hour;

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    private int min;

    public Time() {

    }

    public Time(int hour, int min) {
        this.hour = hour;
        this.min = min;
    }
}
