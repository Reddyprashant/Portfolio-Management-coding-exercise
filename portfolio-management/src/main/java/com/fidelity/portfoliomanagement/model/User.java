package com.fidelity.portfoliomanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fidelity.portfoliomanagement.dao.UserPortfolioAccountRepository;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;


@Entity
@Table(name = "user")
public class User {

    @Id
    private String id;

    @Column()
    private String firstName;

    @Column()
    private String lastName;

    @Column()
    @JsonFormat(pattern = "MM-dd-yyyy")
    private LocalDate dateOfBirth;

    @Column()
    private BigDecimal asset;

    @OneToOne
    private UserPortfolioAccount userPortfolio;

    public User() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public BigDecimal getAsset() {
        return asset;
    }

    public void setAsset(BigDecimal asset) {
        this.asset = asset;
    }

    public UserPortfolioAccount getUserPortfolio() {
        if (userPortfolio == null)
            userPortfolio = new UserPortfolioAccount();
        return userPortfolio;
    }

    public void setUserPortfolio(UserPortfolioAccount userPortfolio) {
        this.userPortfolio = userPortfolio;
    }

}
