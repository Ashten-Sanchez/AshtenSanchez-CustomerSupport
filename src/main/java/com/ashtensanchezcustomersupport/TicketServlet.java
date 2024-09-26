package com.ashtensanchezcustomersupport;

import com.ashtensanchezcustomersupport.Attachment;
import com.ashtensanchezcustomersupport.Ticket;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/ticket")
@MultipartConfig
public class TicketServlet extends HttpServlet {

    private Map<Integer, Ticket> ticketMap = new HashMap<>();
    private int ticketIdCounter = 1;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("list".equals(action)) {

            listTickets(request, response);
        }

        else if ("view".equals(action)) {

            viewTicket(request, response);
        }

        else if ("showForm".equals(action)) {

            showTicketForm(request, response);
        }

        else {

            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("create".equals(action)) {

            createTicket(request, response);
        }

        else if ("download".equals(action)) {

            downloadAttachment(request, response);
        }

        else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    private void listTickets(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Collection<Ticket> tickets = ticketMap.values();
            request.setAttribute("tickets", tickets);
            request.getRequestDispatcher("/listTickets.jsp").forward(request, response);
    }

    private void viewTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id;

        try {

            id = Integer.parseInt(request.getParameter("id"));
        }

        catch (NumberFormatException e) {

            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ticket ID");
            return;
        }

        Ticket ticket = getTicket(id);

        if (ticket != null) {

            request.setAttribute("ticket", ticket);
            request.getRequestDispatcher("/viewTicket.jsp").forward(request, response);
        }

        else {

            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ticket not found");
        }
    }

    private void createTicket(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String customerName = request.getParameter("customerName");
        String subject = request.getParameter("subject");
        String body = request.getParameter("body");

        Ticket ticket = new Ticket(customerName, subject, body);

        // Handle file upload if any
        Part filePart = request.getPart("attachment");

        if (filePart != null && filePart.getSize() > 0) {

            processAttachment(ticket, filePart);
        }

        ticketMap.put(ticketIdCounter++, ticket);
        response.sendRedirect("ticket?action=list");
    }

    private void downloadAttachment(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int ticketId;

        int attachmentIndex;

        try {

            ticketId = Integer.parseInt(request.getParameter("ticketId"));
            attachmentIndex = Integer.parseInt(request.getParameter("attachmentIndex"));
        }

        catch (NumberFormatException e) {

            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ticket ID or attachment index");
            return;
        }

        Ticket ticket = getTicket(ticketId);

        if (ticket != null) {

            Attachment attachment = ticket.getAttachment(attachmentIndex);

            if (attachment != null) {

                response.setHeader("Content-Disposition", "attachment; filename=\"" + attachment.getName() + "\"");
                response.setContentType("application/octet-stream");
                response.getOutputStream().write(attachment.getContents());
                response.getOutputStream().flush();
            }

            else {

                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Attachment not found");
            }
        }

        else {

            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ticket not found");
        }
    }

    private void showTicketForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/ticketForm.jsp").forward(request, response);
    }

    private void processAttachment(Ticket ticket, Part filePart) throws IOException {

        String fileName = filePart.getSubmittedFileName();

        try (InputStream fileContent = filePart.getInputStream()) {

            byte[] contents = new byte[(int) filePart.getSize()];

            fileContent.read(contents);
            Attachment attachment = new Attachment();
            attachment.setName(fileName);
            attachment.setContents(contents);
            ticket.addAttachment(attachment);
        }
    }

    private Ticket getTicket(int id) {

        return ticketMap.get(id);
    }
}

