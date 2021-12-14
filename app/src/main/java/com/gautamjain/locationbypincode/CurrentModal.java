package com.gautamjain.locationbypincode;

public class CurrentModal {

    private String placeName;
    private String stateName;
    private String stateAbbrevation;
    private double latitude;
    private double longitude;

    public CurrentModal(String stateName, String stateAbbrevation, double latitude, double longitude, String placeName) {
        this.stateName = stateName;
        this.stateAbbrevation = stateAbbrevation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.placeName = placeName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateAbbrevation() {
        return stateAbbrevation;
    }

    public void setStateAbbrevation(String stateAbbrevation) { this.stateAbbrevation = stateAbbrevation; }

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

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }


}
