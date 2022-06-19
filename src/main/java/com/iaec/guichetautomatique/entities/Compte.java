package com.iaec.guichetautomatique.entities;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
public class Compte {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column(name = "numero_compte")
    private int numeroCompte;

    @Column(name = "date_creation")
    private Date dateCreation;

    @Column(name = "solde")
    @Digits(integer =16, fraction = 2, message = "Le solde doit être uniquement des chiffres")
    private double solde;

    @ManyToOne
    @JoinColumn(name= "id_client")
    private Client client;

    @OneToMany(mappedBy = "compte")
    private List<Operation> operations;

    public Compte() {
    }

    public Compte(int numeroCompte, Date dateCreation, double solde, Client client) {
        this.numeroCompte = numeroCompte;
        this.dateCreation = dateCreation;
        this.solde = solde;
        this.client = client;
    }

    public Compte(int numeroCompte, Date dateCreation, double solde, Client client, List<Operation> operations) {
        this.numeroCompte = numeroCompte;
        this.dateCreation = dateCreation;
        this.solde = solde;
        this.client = client;
        this.operations = operations;
    }

    public Compte(double solde, Client client) {
        this.solde = solde;
        this.client = client;
    }

    public int getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(int numeroCompte) {
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
