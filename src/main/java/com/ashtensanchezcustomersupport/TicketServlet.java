package com.ashtensanchezcustomersupport;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ticket", value = "/ticket")
@MultipartConfig(fileSizeThreshold = 5_242_880, maxFileSize = 20_971_520L, maxRequestSize = 41_943_040L)

public class TicketServlet extends HttpServlet {

    private volatile int ticketIdCounter = 1;
    private final Map<Integer, Ticket> ticketMap = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {

            case "createTicket":
                request.getRequestDispatcher("/WEB-INF/jsp/view/ticketForm.jsp").forward(request, response);
                break;

            case "view":
                viewTicket(request, response); // View ticket details
                break;

            case "download":
                downloadAttachment(request, response); // Handle file download
                break;

            default:
                listTickets(request, response); // List all tickets
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        if ("create".equals(action)) {
            createTicket(request, response);
        } else {
            response.sendRedirect("ticket");
        }
    }

    private void listTickets(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("ticketDB", ticketMap);  // Set the ticket map in the request
        request.getRequestDispatcher("/WEB-INF/jsp/view/listTickets.jsp").forward(request, response);  // Forward to JSP
    }

    private void viewTicket(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id;

        try {
            id = Integer.parseInt(request.getParameter("ticketId"));

        }

        catch (NumberFormatException e) {

            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ticket ID");
            return;
        }

        Ticket ticket = getTicket(id);
        if (ticket != null) {

            request.setAttribute("ticket", ticket);
            request.getRequestDispatcher("/WEB-INF/jsp/view/viewTicket.jsp").forward(request, response);
        }

        else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ticket not found");
        }
    }

    private void createTicket(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String customerName = request.getParameter("customerName");
        String subject = request.getParameter("subject");
        String body = request.getParameter("body");

        if (customerName == null || subject == null || body == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing ticket data");
            return;
        }

        Ticket newTicket = new Ticket(customerName, subject, body);

        // Handle file upload if provided
        Part filePart = request.getPart("attachment");
        if (filePart != null && filePart.getSize() > 0) {
            processAttachment(newTicket, filePart);
        }

        synchronized (this) {
            ticketMap.put(ticketIdCounter++, newTicket);  // Add new ticket
        }

        response.sendRedirect("ticket?action=list");  // Redirect to ticket listing
    }

    private void downloadAttachment(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int ticketId;
        int attachmentIndex;

        try {
            ticketId = Integer.parseInt(request.getParameter("ticketId"));
            attachmentIndex = Integer.parseInt(request.getParameter("attachmentIndex"));
        } catch (NumberFormatException e) {
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
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Attachment not found");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ticket not found");
        }
    }

    private void showTicketForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/view/ticketForm.jsp").forward(request, response);  // Forward to JSP form
    }

    private void processAttachment(Ticket ticket, Part filePart) throws IOException {
        try (InputStream fileContent = filePart.getInputStream()) {
            byte[] contents = fileContent.readAllBytes();
            Attachment attachment = new Attachment();
            attachment.setName(filePart.getSubmittedFileName());
            attachment.setContents(contents);
            ticket.addAttachment(attachment);  // Add attachment to ticket
        }
    }

    private Ticket getTicket(int id) {
        return ticketMap.get(id);
    }
}
