package pl.adluna.combatzone.model;

import java.util.Date;

/**
 * Created by Natalia Stawowy on 01.07.14.
 */
public class News {
    private String stringDate;
    private String text;
    private Date date;

    public String getStringDate() {
        return stringDate;
    }

    public void setStringDate(String stringDate) {
        this.stringDate = stringDate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
