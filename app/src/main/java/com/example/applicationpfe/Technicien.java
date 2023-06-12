package com.example.applicationpfe;

public class Technicien {
    private String name;
    private String prenom;
    private double latitude;
    private double longitude;
    private String Cin;
    private double Distance;
    private String Localisation;

    public Technicien(String name,String  prenom,  double latitude, double longitude, String Cin) {
        this.name = name;
        this.prenom = prenom;
        this.latitude = latitude;
        this.longitude = longitude;
        this.Cin=Cin;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }



    public String  getPrenom(){
        return prenom;}

    public  String getCin() {
        return Cin;

    }

    public double getDistance() {
        return Distance;
    }

    public String getLocalisation() {
        return Localisation;
    }
}
