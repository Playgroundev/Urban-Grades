package com.dita.dev.Model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Trial")
public class Trial {
    /*The Class Name is the same name as the table name according to this ORM framework*/

    @Column(name = "Username")
    @Id
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;
}
