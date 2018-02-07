package play.com.typing;

/**
 * Created by YSPL on 7/10/2017.
 */

public class Time {
    private int hour;
    private int min;

    public Time() {

    }

    public Time(int hour, int min) {
        this.hour = hour;
        this.min = min;
    }

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

    @Override
    public String toString() {
        return "Time{" +
                "hour=" + hour +
                ", min=" + min +
                '}';
    }
}
