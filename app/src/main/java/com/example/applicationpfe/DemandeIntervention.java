package com.example.applicationpfe;

public class DemandeIntervention {
    private int iddemande;
    private String clientName;
    private String clientEmail;
    private String titreDemande;
    private String description;
    private String genre;
    private String lieu;
    private String date;
    private String contact;
    private String commentaire;

    public DemandeIntervention(String clientName, String clientEmail, String titreDemande, String description, String lieu, String typeDemande, String date) {
        // Constructeur vide requis pour Firestore
    }

    public DemandeIntervention(String clientName, String clientEmail, String titreDemande, String description,
                               String genre, String lieu, String date, String contact, String commentaire) {
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.titreDemande = titreDemande;
        this.description = description;
        this.genre = genre;
        this.lieu = lieu;
        this.date = date;
        this.contact = contact;
        this.commentaire = commentaire;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getTitreDemande() {
        return titreDemande;
    }

    public void setTitreDemande(String titreDemande) {
        this.titreDemande = titreDemande;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {

        this.commentaire = commentaire;
    }

    public int getiddemande()
    {

        return iddemande;
    }
}
