package com.ibanfr.hibernate.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
    private String username;
    private String createdBy;
    private Date createdDate;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public Address getAddress() {
        return address;
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
 
    public int getUserId() {
        return userId;
    }
 
	public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + "]";
	}

}
