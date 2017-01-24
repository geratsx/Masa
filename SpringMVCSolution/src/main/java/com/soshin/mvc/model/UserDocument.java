package com.soshin.mvc.model;

import javax.persistence.*;

@Entity
@Table(name = "user_documents")
public class UserDocument {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column
    private String name;

    @ManyToOne
    User user;


    public UserDocument() {
    }

    public UserDocument(final String name) {
        this.name = name;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public String getName() {
        return this.name;
    }
}
