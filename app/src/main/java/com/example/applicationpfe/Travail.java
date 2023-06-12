package com.example.applicationpfe;

public class Travail {
    private String workId;
    private String title;
    private String nomclient;
    private String date;
    private String typeentretien;
    private String localisation;
    public Travail() {
        // Constructeur vide requis pour Firebase
    }

    public Travail(String workId, String title, String description, String date,String typeentretien,String localisation) {
        this.workId = workId;
        this.title = title;
        this.nomclient = nomclient;
        this.date = date;
        this.typeentretien=typeentretien;
        this.localisation=localisation;
    }
    // Définir les getters et les setters pour chaque propriété
    // ...

    // Exemple de méthode toString() pour afficher les détails du travail

    public String toString() {
        return "Work{" +
                "workId='" + workId + '\'' +
                ", title='" + title + '\'' +
                ",nomclient ='" + nomclient + '\'' +
                ", typeentretien='" + typeentretien + '\'' +
                ", localisation='" + localisation + '\'' +
                ", date='" + date + '\'' +
                '}';

    }

    public String getTitle() {
        return title;
    }

    public String getnomclient() {
        return nomclient;
    }

    public String getDate() {
        return date;
    }

    public  String gettypeentretien() {
        return typeentretien;
    }

    public  String getlocalisation() {

        return localisation;
    }
}
