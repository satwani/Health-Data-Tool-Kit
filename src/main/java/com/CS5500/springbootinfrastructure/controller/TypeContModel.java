package com.CS5500.springbootinfrastructure.controller;

import java.sql.Date;

public class TypeContModel {

    String type;

    Integer start_hh, start_mm, start_ss;
    Integer end_hh, end_mm, end_ss;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    Date date;
}
