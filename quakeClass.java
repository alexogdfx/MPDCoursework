package org.me.gcu.ojelade_alexander_s2004585;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class quakeClass {

    private String title;
    private String description;

    private String location;
    private double magnitude;
    private String link;
    private String pubDate;
    private String cat;
    private double latitude;
    private double longitude;
    private int depth;

    public quakeClass(){
        this.title = "";
        this.description = "";
        this.link = "";
        this.pubDate = "";
        this.cat = "";
        this.latitude = 0;
        this.longitude = 0;
        this.magnitude = 0;
        this.location = "";
        this.depth = 0;
    }


    public quakeClass(String title, String description, String link, String pubDate, String cat, double latitude, double longitude, double magnitude, String location) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.pubDate = pubDate;
        this.cat = cat;
        this.latitude = latitude;
        this.longitude = longitude;
        this.magnitude = magnitude;
        this.location = location;
        this.depth = depth;

        Pattern pattern = Pattern.compile("Magnitude: (\\d+\\.?\\d*)");
        Matcher matcher = pattern.matcher(description);
        if (matcher.find()) {
            this.magnitude = Double.parseDouble(matcher.group(1));
        } else {
            this.magnitude = 0;
        }
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getMagnitude() {return magnitude;}

    public void setMagnitude(double magnitude) {this.magnitude = magnitude;}

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = (int) depth;
    }

    @Override
    public String toString() {
        return "quakeClass{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", cat='" + cat + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
