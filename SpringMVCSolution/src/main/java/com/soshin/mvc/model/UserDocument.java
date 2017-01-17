package com.soshin.mvc.model;

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
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }
}
