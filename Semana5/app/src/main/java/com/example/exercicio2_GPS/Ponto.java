package com.example.exercicio2_GPS;

public class Ponto {

    private double latitude;
    private double longitude;
    private double altitude;

    Ponto(){
        this.latitude = 0.0;
        this.longitude = 0.0;
        this.altitude = 0.0;
    }

    Ponto(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    Ponto(double latitude, double longitude, double altitude){
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public String imprimir(){
        String aux = "Lat:"+ this.latitude + ", "+
                     "Long:"+ this.longitude + ", "+
                     "Alt:"+ this.altitude;
        return aux;
    }

    public String imprimir2(){
        String aux = "***************************\n"+
                      "Latitude:"+ this.latitude +"\n"+
                      "Longitude:"+ this.longitude +"\n"+
                      "Altitude:"+ this.altitude +"\n"+
                      "***************************\n";
        return aux;
    }


    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }



}
