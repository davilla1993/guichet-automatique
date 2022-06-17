package com.iaec.guichetautomatique.entities;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "nom")
    @NotEmpty(message = "Ce champ ne peut être vide")
    @Size(min=2, max=20, message="Le nom doit être compris entre 2 et 20 caractères")
    private String nom;
    @Column(name= "prenom")
    @NotEmpty(message = "Ce champ ne peut être vide")
    @Size(min=2, max=20, message="Le prénom doit être compris entre 2 et 20 caractères")
    private String prenom;
    @Column(name = "telephone")
    @NotEmpty(message = "Ce champ ne peut être vide")
    @Digits(integer=12,fraction=0, message = "Le numero de téléphone ne doit comporter que des chiffres")
    @Size(min = 7, max = 12, message = "Le numéro doit être compris entre 7 et 12 caractères")
    private String telephone;
    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy="client")
    private List<Compte> comptes;

    public Client() {

    }

    public Client(String nom, String prenom, String telephone, String login, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.login = login;
        this.password = password;
    }

    public Client(String nom, String prenom, String telephone, String login, String password, List<Compte> comptes) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.login = login;
        this.password = password;
        this.comptes = comptes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public List<Compte> getComptes() {
        return comptes;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }
}
