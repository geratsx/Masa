package com.alexey.mvc.model;

import javax.persistence.*;

@Entity
@Table(name = "user_documents")
public class UserDocument {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
