package com.ibanfr.hibernate.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Table(name = "passport")
@Entity
public class Passport implements Serializable {
    private Long id;

    private static final long serialVersionUID = -9113042583599453674L;

    private String name;

    private String surname;

    private String passportNum;

    private LocalDate birthDate;

    private LocalDate issueDate;

    private LocalDate expiryDate;


    public Passport() {
    }

    public Passport(String name) {
        this.name      = name;
        this.passportNum = UUID.randomUUID().toString();
    }

    @Column(name = "passportNum")
    public String getPassportNum() {
        return passportNum;
    }

    public void setPassportNum(String passportNum) {
        this.passportNum = passportNum;
    }

    @Column(name = "expiryDate")
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "issuedate")
    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    @Column(name = "birthdate")
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}