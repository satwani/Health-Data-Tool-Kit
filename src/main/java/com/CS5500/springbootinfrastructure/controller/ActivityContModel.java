package com.CS5500.springbootinfrastructure.controller;


public class ActivityContModel {
    private String activity;
    private String group;
    private Boolean manual;
    private Integer start_hh, start_mm, start_ss;
    private Integer end_hh, end_mm, end_ss;
    private Integer calories;
    private Float duration;
    private Float distance;
    private Integer steps;

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Integer getStart_hh() {
        return start_hh;
    }

    public void setStart_hh(Integer start_hh) {
        this.start_hh = start_hh;
    }

    public Integer getStart_mm() {
        return start_mm;
    }

    public void setStart_mm(Integer start_mm) {
        this.start_mm = start_mm;
    }

    public Integer getStart_ss() {
        return start_ss;
    }

    public void setStart_ss(Integer start_ss) {
        this.start_ss = start_ss;
    }

    public Integer getEnd_hh() {
        return end_hh;
    }

    public void setEnd_hh(Integer end_hh) {
        this.end_hh = end_hh;
    }

    public Integer getEnd_mm() {
        return end_mm;
    }

    public void setEnd_mm(Integer end_mm) {
        this.end_mm = end_mm;
    }

    public Integer getEnd_ss() {
        return end_ss;
    }

    public void setEnd_ss(Integer end_ss) {
        this.end_ss = end_ss;
    }

    public Boolean getManual() {
        return manual;
    }

    public void setManual(Boolean manual) {
        this.manual = manual;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
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

    public Integer getSteps() {
        return steps;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }
}
