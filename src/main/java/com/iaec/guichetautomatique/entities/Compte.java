package com.iaec.guichetautomatique.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "numero_compte")
    private String numeroCompte;

    @Column(name = "date_creation")
    private Date dateCreation;

    @Column(name = "solde")
    private double solde;

    @ManyToOne
    @JoinColumn(name= "idClient")
    private Client client;

    @OneToMany(mappedBy = "compte")
    private List<Operation> operations;

    public Compte() {
    }

    public Compte(String numeroCompte, Date dateCreation, double solde, Client client) {
        this.numeroCompte = numeroCompte;
        this.dateCreation = dateCreation;
        this.solde = solde;
        this.client = client;
    }

    public Compte(String numeroCompte, Date dateCreation, double solde, Client client, List<Operation> operations) {
        this.numeroCompte = numeroCompte;
        this.dateCreation = dateCreation;
        this.solde = solde;
        this.client = client;
        this.operations = operations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }
}
