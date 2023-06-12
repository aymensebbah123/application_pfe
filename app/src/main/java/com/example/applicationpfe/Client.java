package com.example.applicationpfe;

public class Client extends user {
    private int clientUid;
    private String clientName;
    private String motDePasse;
    private String Cin;

    public Client(int clientUid, String motDePasse, String clientName) {
        this.clientUid = clientUid;
        this.motDePasse = motDePasse;
        this.clientName = clientName;
    }

    public int getClientUid() {
        return clientUid;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getclientName() {
        return clientName;
    }
    public String getCin() {
        return Cin;
    }


}
