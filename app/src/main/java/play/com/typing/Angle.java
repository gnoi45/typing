package play.com.typing;

/**
 * Created by YSPL on 7/10/2017.
 */

public class Angle {
    private double hourAngle;
    private double minuteAngle;
    private double secAngle;

    public Angle() {

    }

    public Angle(double minuteAngle, double hourAngle, double secAngle) {
        this.minuteAngle = minuteAngle;
        this.hourAngle = hourAngle;
        this.secAngle = secAngle;
    }

    public double getSecAngle() {
        return secAngle;
    }

    public void setSecAngle(double secAngle) {
        this.secAngle = secAngle;
    }

    public double getHourAngle() {
        return hourAngle;
    }

    public void setHourAngle(double hourAngle) {
        this.hourAngle = hourAngle;
    }

    public double getMinuteAngle() {
        return minuteAngle;
    }

    public void setMinuteAngle(double minuteAngle) {
        this.minuteAngle = minuteAngle;
    }

    @Override
    public String toString() {
        return "Angle{" +
                "hourAngle=" + hourAngle +
                ", minuteAngle=" + minuteAngle +
                ", secAngle=" + secAngle +
                '}';
    }
}
