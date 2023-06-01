package org.example.HospitalPlanner.model;

import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
@Entity
@Table(name = "account")
public class Account {

    @Id
    @Column(unique = true, nullable = false)
    private String username;

    @Column
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person person;

    public Account() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", person=" + (person != null ? person.getMail() : null) +
                '}';
    }
}
