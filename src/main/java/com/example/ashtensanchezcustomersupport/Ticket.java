package com.example.ashtensanchezcustomersupport;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Ticket {

    private String customerName;
    private LocalDate date;
    private String subject;
    private String body;
    private List<Attachment> attachments;


    // Default constructor
    public Ticket() {

        this.attachments = new ArrayList<>();
    }

    // Constructor with parameters
    public Ticket(String customerName, String subject, String body) {

        this.customerName = customerName;
        this.subject = subject;
        this.body = body;
        this.attachments = new ArrayList<>();
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate() {
        this.date = LocalDate.now();
    }

    public String getCustomerName() {

        return customerName;
    }

    public void setCustomerName(String customerName) {

        this.customerName = customerName;
    }

    public String getSubject() {

        return subject;
    }

    public void setSubject(String subject) {

        this.subject = subject;
    }

    public String getBody() {

        return body;
    }

    public void setBody(String body) {

        this.body = body;
    }

    public void addAttachment(Attachment attachment) {

        this.attachments.add(attachment);
    }

    public int getNumberOfAttachments() {

        return attachments.size();
    }

    public Attachment getAttachment(int index) {

        if (index >= 0 && index < attachments.size()) {

            return attachments.get(index);
        }

        return null; // or throw an exception
    }

    public List<Attachment> getAllAttachments() {

        return new ArrayList<>(attachments);
    }
}


