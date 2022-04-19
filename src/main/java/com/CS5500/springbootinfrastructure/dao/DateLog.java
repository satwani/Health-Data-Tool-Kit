package com.CS5500.springbootinfrastructure.dao;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.CS5500.springbootinfrastructure.parser.DataFormatterImpl;
import com.CS5500.springbootinfrastructure.parser.DateLogFormatterImpl;
import com.fasterxml.jackson.annotation.*;


@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "date")
// @JsonIdentityReference(alwaysAsId = true)
@JsonIgnoreProperties({ "summary"})
@Entity // This tells Hibernate to make a table out of this class
@Table(name = "date_log")
public class DateLog {
    /*@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;*/

    @Id
    @Column(name = "date_of")
    private Date date;

    @JsonProperty("date")
    @SuppressWarnings("unchecked")
    private void dateDeserializer(String date) {
        this.date= new Date(new DateLogFormatterImpl().convertTimestamp(date).getTime());
    }

    private Integer caloriesIdle;

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    @OneToMany(mappedBy = "date", cascade = CascadeType.ALL)
    private List<Type> types;

    @JsonProperty("segments")
    @SuppressWarnings("unchecked")
    private void fkeySegmentsSerializer(List<Type> types) {
        this.types = types;
        for (Type type : this.types) {
            type.setDate(this);
        }
    }


    @Override
    public String toString() {
        return "DateLog{" +
                "date=" + date.toString() +
                ", caloriesIdle=" + caloriesIdle +
                ", types=" + types.toString() +
                ", lastUpdate=" + lastUpdate.toString() +
                '}';
    }

    public DateLog() {

    }

    private Timestamp lastUpdate;

    @JsonProperty("lastUpdate")
    @SuppressWarnings("unchecked")
    private void lastUpdateDeserializer(String lastUpdateTime) {
        this.lastUpdate = new DataFormatterImpl().convertTimestamp(lastUpdateTime);
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /*public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }*/

    public Date getDate() {
        return date;
    }

    public void setDate(Date dateOf) {
        this.date = dateOf;
    }

    public Integer getCaloriesIdle() {
        return caloriesIdle;
    }

    public void setCaloriesIdle(Integer caloriesIdle) {
        this.caloriesIdle = caloriesIdle;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void timestampLastUpdate() {
        this.lastUpdate = new Timestamp(System.currentTimeMillis());
    }
}
