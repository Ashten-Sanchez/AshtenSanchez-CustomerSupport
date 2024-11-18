package com.ashtensanchezcustomersupport.entities;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "tickets")
public class TicketEntity implements Serializable{

    private static final long serialVersionUID = 1L; //unique id for serializable version
    private long id; //primary key
    private String customerName;
    private String subject;
    private String body;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    @Basic
    public String getBody() {

        return body;
    }

    public void setBody(String body) {

        this.body = body;
    }

    @Basic
    public String getSubject() {

        return subject;
    }

    public void setSubject(String subject) {

        this.subject = subject;
    }

    @Basic
    public String getCustomerName() {

        return customerName;
    }

    public void setCustomerName(String customerName) {

        this.customerName = customerName;
    }
}
