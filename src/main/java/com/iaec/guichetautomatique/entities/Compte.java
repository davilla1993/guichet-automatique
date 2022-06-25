package com.iaec.guichetautomatique.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Entity
public class Compte {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column(name = "numero_compte")
    private int numeroCompte;

    @Column(name = "date_creation")
    private Date dateCreation;

    @Column(name = "solde")
    private double solde;

    @ManyToOne
    @JoinColumn(name= "id_user")
    private User user;

    @OneToMany(mappedBy = "compte")
    private List<Operation> operations;

    public Compte() {
    }

    public Compte(Integer id, int numeroCompte, Date dateCreation, double solde, User user) {
        this.id = id;
        this.numeroCompte = numeroCompte;
        this.dateCreation = dateCreation;
        this.solde = solde;
        this.user = user;
    }

    public Compte(int numeroCompte, Date dateCreation, double solde, User user) {
        this.numeroCompte = numeroCompte;
        this.dateCreation = dateCreation;
        this.solde = solde;
        this.user = user;
    }

    public Compte(int numeroCompte, Date dateCreation, double solde, User user, List<Operation> operations) {
        this.numeroCompte = numeroCompte;
        this.dateCreation = dateCreation;
        this.solde = solde;
        this.user = user;
        this.operations = operations;
    }

    public Compte(double solde, User user) {
        this.solde = solde;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public User getUser() {

        return user;
    }

    public void setUser(User user) {

        this.user = user;
    }

    public List<Operation> getOperations() {

        return operations;
    }

    public void setOperations(List<Operation> operations) {

        this.operations = operations;
    }
}
