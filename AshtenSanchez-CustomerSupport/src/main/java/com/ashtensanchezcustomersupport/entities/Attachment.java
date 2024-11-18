package com.ashtensanchezcustomersupport.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Arrays;

@Entity
@Table(name="attachments")
public class Attachment implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private long ticketId;
    private String name;

    private byte[] contents;

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
    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }

    public Attachment() {

        super();
    }

    @Basic
    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    @Lob
    public byte[] getContents() {

        return contents;
    }

    public void setContents(byte[] contents) {

        this.contents = contents;
    }

    @Override
    public String toString() {

        return "Attachment{" +
                "name='" + name + '\'' +
                ", contents=" + Arrays.toString(contents) +
                '}';
    }
}
