package com.ashtensanchezcustomersupport;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet(name = "ticket", value = "/ticket")
@MultipartConfig(fileSizeThreshold = 5_242_880, maxFileSize = 20971520L, maxRequestSize = 41_943_040L)

public class TicketServlet extends HttpServlet {

    private volatile int TICKET_ID = 1;

    private Map<Integer, Ticket> ticketDB = new LinkedHashMap<>();

    private Attachment attachment;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        String action = request.getParameter("action");

        if (action == null) {

            action = "list";
        }

        switch (action) {

            case "createTicket" -> showTicketForm(request, response);
            case "view" -> viewTicket(request, response);
            default -> listTickets(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        String action = request.getParameter("action");

        if (action == null) {

            action = "list";
        }

        switch (action) {

            case "createTicket" -> createTicket(request, response);
            default -> response.sendRedirect("ticket");
        }
    }

    private void listTickets(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

            out.println("<html><body><h2>Tickets</h2>");
            out.println("<a href=\"ticket?action=createTicket\">Create Ticket</a><br><br>");

        if (ticketDB.size()== 0) {

            out.println("There are no tickets saved.");
        }

        else {

            for (int id : ticketDB.keySet()) {

                Ticket ticket = ticketDB.get(id);
                    out.println("Ticket #" + id);
                    out.println(": <a href=\"ticket?action=view&ticketid=" + id+ "\">");
                    out.println(ticket.getSubject() + "</a><br>");
            }
        }

        out.println("</body></html>");
    }

    private void createTicket(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Ticket ticket = new Ticket();
            ticket.setSubject(request.getParameter("subject"));
            ticket.setBody(request.getParameter("body"));

        Part filePart = request.getPart("file1");

        if (filePart != null && filePart.getSize() > 0) {

            Attachment attachment = processAttachment(filePart);

            if (attachment != null) {

                ticket.setAttachmentName(attachment.getName());
                ticket.setAttachments(attachment.getContents());
            }
        }

        int id;

        synchronized (this) {

            id = this.TICKET_ID++;
                ticketDB.put(id, ticket);
        }

        response.sendRedirect("ticket?action=view&ticketid=" + id);
    }



    private Attachment processAttachment(Part file)
            throws IOException {

        InputStream in = file.getInputStream();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int read;

        final byte[] bytes = new byte[1024];

        while ((read = in.read(bytes)) != -1) {

            out.write(bytes, 0, read);
        }

        Attachment attachment = new Attachment();
            attachment.setName(file.getSubmittedFileName());
            attachment.setContents(out.toByteArray());

        return attachment;
    }

    private void attachmentDownload(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idString = request.getParameter("ticketid");

        Ticket ticket = getTicket(idString, response);

        if (ticket == null || ticket.getAttachments() == null || ticket.getAttachments().length == 0) {

            response.sendRedirect("ticket?action=view&ticketid=" + idString);
            return;
        }

        String attachmentName = ticket.getAttachmentName();

        if (attachmentName == null || attachmentName.isEmpty()) {

            attachmentName = "attachment_" + idString;
        }

        response.setHeader("Content-Disposition", "attachment; filename=\"" + attachmentName + "\"");
        response.setContentType("application/octet-stream");

        ServletOutputStream out = response.getOutputStream();
            out.write(ticket.getAttachments());
            out.close();
    }



    private void viewTicket(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idString = request.getParameter("ticketid");
        if (idString == null || idString.length() == 0) {
            response.sendRedirect("ticket");
            return;
        }

        try {

            int id = Integer.parseInt(idString);

            Ticket ticket = ticketDB.get(id);

            if (ticket == null) {

                response.sendRedirect("ticket");
                return;
            }

            PrintWriter out = response.getWriter();
                out.println("<html><body><h2>Ticket #" + id + "</h2>");
                out.println("<h3>" + ticket.getSubject() + "</h3>");
                out.println("<p>" + ticket.getBody() + "</p>");

            if (ticket.getAttachments() != null) {

                String attachmentName = ticket.getAttachmentName();
                    out.println("<a href=\"ticket?action=download&ticketid=" + id + "\">Download " + attachmentName + "</a><br><br>");
            }

            out.println("<a href=\"ticket\">Return to ticket list</a>");
            out.println("</body></html>");
        }

        catch (Exception e) {

            response.sendRedirect("ticket");
        }
    }


    private void showTicketForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

            out.println("<html><body><h2>Ticket Form</h2>");
            out.println("<form method = \"POST\" action = \"ticket\" enctype = \"multipart/form-data\">");
            out.println("<input type= \"hidden\" name = \"action\" value = \"createTicket\">");
            out.println("Subject:<br>");
            out.println("<input type = \"text\" name = \"subject\"><br><br>");
            out.println("Body:<br>");
            out.println("<textarea name = \"body\" rows = \"25\" cols = \"100\"></textarea><br><br>");
            out.println("<b>Attachment</b><br>");
            out.println("<input type = \"file\" name = \"file1\"><br><br>");
            out.println("<input type = \"submit\" value = \"Submit\">");
            out.println("</form></body></html>");
    }

    private Ticket getTicket(String idString, HttpServletResponse response)
            throws ServletException, IOException {

        if (idString == null || idString.length() == 0) {

            response.sendRedirect("ticket");
            return null;
        }

        try {

            int id = Integer.parseInt(idString);

            Ticket ticket = ticketDB.get(id);

            if (ticket == null) {

                response.sendRedirect("ticket");
                return null;
            }

            return ticket;

        } catch (Exception e) {

            response.sendRedirect("ticket");

            return null;
        }
    }

}
