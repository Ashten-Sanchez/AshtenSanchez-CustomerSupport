package com.ashtensanchezcustomersupport.site;

import java.io.Serializable;

public class Ticket implements Serializable {

    private String customerName;
    private String subject;
    private String body;
    private String attachmentName;
    private Attachment attachments;

    public Ticket() {

        super();
    }

    public Ticket(String customerName, String subject, String body, String attachmentName,
                  Attachment attachments) {

        this.customerName = customerName;
        this.subject = subject;
        this.body = body;
        this.attachmentName = attachmentName;
        this.attachments = attachments;
    }

    public String getAttachmentName() {

        return attachmentName != null ? attachmentName : "No attachment";
    }

    public void setAttachmentName(String attachmentName) {

        this.attachmentName = attachmentName;
    }

    public Attachment getAttachments() {

        return attachments;
    }

    public void setAttachments(Attachment attachments) {

        this.attachments = attachments;
    }

    public String getBody() {

        return body;
    }

    public void setBody(String body) {

        this.body = body;
    }

    public String getSubject() {

        return subject;
    }

    public void setSubject(String subject) {

        this.subject = subject;
    }

    public String getCustomerName() {

        return customerName;
    }

    public void setCustomerName(String customerName) {

        this.customerName = customerName;
    }

    public boolean hasAttachments() {

        return attachments != null && attachments.getName().length() > 0 && attachments.getContents().length > 0;
    }


    @Override
    public String toString() {
        return "Ticket{" +
                "customerName='" + customerName + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", attachments=" + attachments +
                '}';
    }
}
