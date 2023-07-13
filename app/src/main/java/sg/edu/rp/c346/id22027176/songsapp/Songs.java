package sg.edu.rp.c346.id22027176.songsapp;

import androidx.annotation.NonNull;
import java.io.Serializable;

public class Songs implements Serializable{

    private int id;
    private String title;
    private String singers;
    private Integer year;
    private Integer stars;

    public Songs(int id, String title, String singers, Integer year, Integer stars) {
        this.id = id;
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public int getId() { return id; }

    public String getTitle() {
        return title;
    }

    public String getSingers() {
        return singers;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getStars() {
        return stars;
    }

    public void setTitle(String title) { this.title = title; }

    public void setSingers(String singers) { this.singers = singers;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    @NonNull
    @Override
    public String toString() {
        return "ID: " + id + "\n" + "title: " + title + "\n" + "singer: " + singers + "\n" + "year: " + year + "\n" + "ratings: " + stars;
    }

}