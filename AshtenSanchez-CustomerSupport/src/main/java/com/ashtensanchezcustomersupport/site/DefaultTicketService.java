package com.ashtensanchezcustomersupport.site;

import com.ashtensanchezcustomersupport.entities.TicketEntity;
import com.ashtensanchezcustomersupport.entities.Attachment;

import jakarta.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultTicketService implements TicketService {

    @Inject TicketRepository ticketRepository;
    @Inject AttachmentRepository attachmentRepository;

    @Override
    @Transactional
    public List<Ticket> getAllTickets() {

        List<Ticket> list = new ArrayList<>();
        ticketRepository.getAll().forEach(e -> list.add(this.convert(e)));

        return list;
    }

    @Override
    public Ticket getTicket(long id) {

        TicketEntity entity = ticketRepository.get(id);

        return entity == null ? null : this.convert(entity);
    }

    private Ticket convert(TicketEntity entity) {

        Ticket ticket = new Ticket();
            ticket.setId(entity.getId());
            ticket.setSubject(entity.getSubject());
            ticket.setCustomerName(entity.getCustomerName());
            ticket.setBody(entity.getBody());
            ticket.setAttachments(attachmentRepository.getByTicketId(entity.getId()));

        return ticket;
    }

    @Override
    @Transactional
    public void save(Ticket ticket) {

        TicketEntity entity = new TicketEntity();
            entity.setId(ticket.getId());
            entity.setSubject(ticket.getSubject());
            entity.setCustomerName(ticket.getCustomerName());
            entity.setBody(ticket.getBody());

        if (ticket.getId() < 1) {

            ticketRepository.add(entity);
            ticket.setId(entity.getId());

            if (ticket.hasAttachments()) {

                ticket.getAttachments().setTicketId(entity.getId());
                attachmentRepository.add(ticket.getAttachments());
            }
        }

        else {

            ticketRepository.update(entity);
        }
    }

    @Override
    @Transactional
    public void deleteTicket(long id) {

        ticketRepository.deleteById(id);
    }
}
