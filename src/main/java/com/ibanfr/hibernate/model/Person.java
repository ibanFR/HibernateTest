package com.ibanfr.hibernate.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="person")
public class Person {

    private Long id;

    private String username;

    private Passport passport;

    private String createdBy;
    private Date createdDate;

    /**
     * Empty constructor
     */
    public Person() {
    }

    public Person(Passport passport) {
        this.passport = passport;
        this.username = passport.getName();
    }

    /**
     * It's not required to explicitly define <code>team_id</code> attribute on the entity for the uni-directional
     * association.
     */
    private Long team_id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "passport_id", foreignKey = @ForeignKey(name = "FK_passport_id"))
    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    @Column(name="team_id")
    public Long getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Long team_id) {
        this.team_id = team_id;
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


    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getCreatedBy() {
        return createdBy;
    }
 
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
 
    public Date getCreatedDate() {
        return createdDate;
    }
 
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id)
                                        .append("username", username)
                                        .toString();
    }
}
