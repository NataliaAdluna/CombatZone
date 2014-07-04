package pl.adluna.combatzone.model;

/**
 * Created by Natalia Stawowy on 04.07.14.
 */
public class Hours {
    private String hour1;
    private String hour2;

    public Hours(String hour1, String hour2) {
        this.hour1 = hour1;
        this.hour2 = hour2;
    }

    public String getHour1() {
        return hour1;
    }

    public void setHour1(String hour1) {
        this.hour1 = hour1;
    }

    public String getHour2() {
        return hour2;
    }

    public void setHour2(String hour2) {
        this.hour2 = hour2;
    }
}
