package com.ashtensanchezcustomersupport.site;

import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;

    @Validated
    public interface TicketService {

        @NonNull
        List<Ticket> getAllTickets();
        Ticket getTicket(long id);
        void save(Ticket ticket);
        void deleteTicket(long id);
    }

