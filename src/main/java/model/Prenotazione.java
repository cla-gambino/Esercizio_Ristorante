package model;

import jakarta.persistence.*;

@Entity
@Table(name = "prenotazione")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;
    @Column(name = "nomeCognome", length = 50, nullable = false)
    private String nomeCognome;
    @Column(name = "recapitoTelefonico", length = 50, nullable = false)
    private String recapitoTelefonico;
    @Column(name = "numeroPersone", length = 50, nullable = false)
    private String numeroPersone;

    public Prenotazione() {
    }

    public Prenotazione(String nomeCognome, String recapitoTelefonico, String numeroPersone) {
        this.nomeCognome = nomeCognome;
        this.recapitoTelefonico = recapitoTelefonico;
        this.numeroPersone = numeroPersone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCognome() {
        return nomeCognome;
    }

    public void setNomeCognome(String nomeCognome) {
        this.nomeCognome = nomeCognome;
    }

    public String getRecapitoTelefonico() {
        return recapitoTelefonico;
    }

    public void setRecapitoTelefonico(String recapitoTelefonico) {
        this.recapitoTelefonico = recapitoTelefonico;
    }

    public String getNumeroPersone() {
        return numeroPersone;
    }

    public void setNumeroPersone(String numeroPersone) {
        this.numeroPersone = numeroPersone;
    }
}
