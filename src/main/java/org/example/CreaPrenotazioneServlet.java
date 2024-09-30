package org.example;

import dao.PrenotazioneDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Prenotazione;

import java.io.IOException;

@WebServlet(description = "Servlet per aggiungere prenotazione", urlPatterns = {"/CreaPrenotazione.do"})
public class CreaPrenotazioneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the form parameters
        String nomeCognome = request.getParameter("NomeCognome");
        String recapitoTelefonico = request.getParameter("RecapitoTelefonico");
        String numeroPersone = request.getParameter("numeroPersone");

        // Check for empty or null values
        if (nomeCognome == null || nomeCognome.isEmpty() ||
                recapitoTelefonico == null || recapitoTelefonico.isEmpty() ||
                numeroPersone == null || numeroPersone.isEmpty()) {
            response.setContentType("text/html");
            response.getWriter().println("<h2>Errore: tutti i campi sono obbligatori!</h2>");
            response.getWriter().println("<a href='./creaPrenotazione.html'>Torna indietro</a>");
            return; // Termina l'esecuzione se ci sono campi mancanti
        }

        // Verifica che numeroPersone sia un numero
        try {
            int persone = Integer.parseInt(numeroPersone);
            if (persone <= 0) {
                response.getWriter().println("<h2>Errore: il numero di persone deve essere positivo!</h2>");
                response.getWriter().println("<a href='./creaPrenotazione.html'>Torna indietro</a>");
                return; // Termina l'esecuzione se il numero di persone non è valido
            }
        } catch (NumberFormatException e) {
            response.getWriter().println("<h2>Errore: inserisci un numero valido per il numero di persone!</h2>");
            response.getWriter().println("<a href='./creaPrenotazione.html'>Torna indietro</a>");
            return; // Termina l'esecuzione se il numero di persone non è un numero
        }

        // Crea l'oggetto Prenotazione se tutte le convalide passano
        Prenotazione prenotazione = new Prenotazione(nomeCognome, recapitoTelefonico, numeroPersone);

        // Salva prenotazione usando il DAO
        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
        prenotazioneDAO.savePrenotazione(prenotazione);

        // Set the response type and send the confirmation
        response.setContentType("text/html");
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h2>Grazie per la prenotazione!</h2>");
        response.getWriter().println("<p>Nome: " + nomeCognome + "</p>");
        response.getWriter().println("<p>Recapito Telefonico: " + recapitoTelefonico + "</p>");
        response.getWriter().println("<p>Numero di Persone: " + numeroPersone + "</p>");
        response.getWriter().println("<a href='./index.html'>Torna alla Home</a>");
        response.getWriter().println("</body></html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle GET requests if necessary
        doPost(request, response); // Redirect to POST
    }
}
