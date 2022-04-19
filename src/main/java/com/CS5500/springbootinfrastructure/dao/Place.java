package com.CS5500.springbootinfrastructure.dao;


import com.CS5500.springbootinfrastructure.helper.CheckNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@DiscriminatorValue("place")
@JsonTypeName("place")
public class Place extends Type{
    private String name;

    private String type;

    private Integer place_id;

    public Place() {

    }

    public Place(Place other) {
        super(other);
        name = other.name;
        type = other.type;
        place_id = other.place_id;
        location = other.location;
        foursquareId = other.foursquareId;
        foursquareCategoryIds = new ArrayList<>(other.foursquareCategoryIds);
        facebookPlaceId = other.facebookPlaceId;
    }

    @Override
    public String getKind() {
        return "Place";
    }

    @Override
    public String toString() {
        return "Place{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", location=" + location.toString() +
                ", foursquareId='" + foursquareId + '\'' +
                ", foursquareCategoryIds=" + CheckNull.toString(foursquareCategoryIds) +
                ", facebookPlaceId='" + facebookPlaceId + '\'' +
                ", typeID=" + typeID +
                '}';
    }

    @JsonProperty("place")
    @SuppressWarnings("unchecked")
    private void placeDeserializer(Map<String, Object> place) {
        this.place_id = (Integer) place.get("id");
        this.type = (String) place.get("type");
        Double lat = ((Map<String, Double>) place.get("location")).get("lat");
        Double lon = ((Map<String, Double>) place.get("location")).get("lon");

        this.location = new Location();
        this.location.setLat(lat);
        this.location.setLon(lon);

        this.name = (String) place.get("name");
    }

    @Embedded
    private Location location;

    private String foursquareId;

    @ElementCollection
    private List<String> foursquareCategoryIds;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<String> getFoursquareCategoryIds() {
        return foursquareCategoryIds;
    }

    public void setFoursquareCategoryIds(List<String> foursquareCategoryIds) {
        this.foursquareCategoryIds = foursquareCategoryIds;
    }

    public String getFacebookPlaceId() {
        return facebookPlaceId;
    }

    public void setFacebookPlaceId(String facebookPlaceId) {
        this.facebookPlaceId = facebookPlaceId;
    }

    private String facebookPlaceId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFoursquareId() {
        return foursquareId;
    }

    public void setFoursquareId(String foursquareId) {
        this.foursquareId = foursquareId;
    }

}
