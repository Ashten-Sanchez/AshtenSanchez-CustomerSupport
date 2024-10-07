package com.ashtensanchezcustomersupport;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicketTest {

    private Ticket ticket;
    private Attachment attachment;

    @BeforeEach
    public void setUp() {

        ticket = new Ticket("Bill Tin", "Issue with login", "I am unable to log in.");
        attachment = new Attachment();
        attachment.setName("screenshot.png");
        attachment.setContents(new byte[]{1, 2, 3, 4});
    }

    @Test
    public void testTicketCreation() {

        assertNotNull(ticket);
        assertEquals("Oli Oliman", ticket.getCustomerName());
        assertEquals("Issue with login", ticket.getSubject());
        assertEquals("I am unable to log in.", ticket.getBody());
        assertEquals(0, ticket.getNumberOfAttachments());
    }

    @Test
    public void testAddAttachment() {

        ticket.addAttachment(attachment);
        assertEquals(1, ticket.getNumberOfAttachments());
        assertNotNull(ticket.getAttachment(0));
        assertEquals("screenshot.png", ticket.getAttachment(0).getName());
    }

    @Test
    public void testGetAttachmentByIndex() {

        ticket.addAttachment(attachment);
        Attachment retrieved = ticket.getAttachment(0);
        assertNotNull(retrieved);
        assertEquals("screenshot.png", retrieved.getName());
    }

    @Test
    public void testGetAllAttachments() {

        ticket.addAttachment(attachment);
        assertEquals(1, ticket.getAllAttachments().size());
    }

    @Test
    public void testInvalidAttachmentIndex() {

        assertNull(ticket.getAttachment(0)); // Should return null for invalid index
    }
}
