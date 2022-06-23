package com.iaec.guichetautomatique.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("Versement")
public class Versement extends Operation{

    public Versement() {
    }

    public Versement(Date dateOperation, double montant, Compte compte) {

        super(dateOperation, montant, compte);
    }
}
