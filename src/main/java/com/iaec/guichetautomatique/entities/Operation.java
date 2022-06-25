package com.iaec.guichetautomatique.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_operation",
        discriminatorType = DiscriminatorType.STRING)
public abstract class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "date_operation")
    private Date dateOperation;

    @Column(name = "montant")
    private int montant;

    @ManyToOne
    @JoinColumn(name = "id_compte")
    private Compte compte;

    public Operation() {
    }

    public Operation(Date dateOperation, int montant, Compte compte) {
        this.dateOperation = dateOperation;
        this.montant = montant;
        this.compte = compte;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateOperation() {

        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {

        this.dateOperation = dateOperation;
    }

    public double getMontant() {

        return montant;
    }

    public void setMontant(int montant) {

        this.montant = montant;
    }

    public Compte getCompte() {

        return compte;
    }

    public void setCompte(Compte compte) {

        this.compte = compte;
    }
}
