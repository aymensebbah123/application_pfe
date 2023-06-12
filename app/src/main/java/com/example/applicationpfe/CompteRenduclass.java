package com.example.applicationpfe;

public class CompteRenduclass {
    private String titre;
    private String description;
    private String date;

    // Constructeur par défaut (requis pour Firebase)
    public CompteRenduclass(String titre, String description) {
    }

    public CompteRenduclass (String titre, String description, String date) {
        this.titre = titre;
        this.description = description;
        this.date = date;
    }

    // Getters et setters
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}