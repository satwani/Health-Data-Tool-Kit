package com.CS5500.springbootinfrastructure.dao;

import com.CS5500.springbootinfrastructure.parser.DataFormatterImpl;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Timestamp;

// @Entity // This tells Hibernate to make a table out of this class
// @Table(name = "summary")
public class Summary {
    /*@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;*/

    @JsonProperty("lastUpdate")
    @SuppressWarnings("unchecked")
    private void lastUpdateDeserializer(String lastUpdateTime) {
        this.lastUpdate = new DataFormatterImpl().convertTimestamp(lastUpdateTime);
    }

    @Id
    public String activity;

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String sumGroup) {
        this.group = sumGroup;
    }

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Double getSteps() {
        return steps;
    }

    public void setSteps(Double steps) {
        this.steps = steps;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String group;

    public Float duration;

    public Float distance;

    public Double steps;

    public Integer calories;

    public Timestamp lastUpdate;

}

