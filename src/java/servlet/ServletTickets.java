package servlet;

import dto.Paciente;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Administrador;

public class ServletTickets extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletTickets</title>");
            out.println("<link href='css/index.css' type='text/css' rel='stylesheet'/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='contenedor_login'>");

            if (!Administrador.getOrCreate().getCola().estaVacio()) {
                Paciente obj = Administrador.getOrCreate().siguientePaciente();
                out.print("<div class='contenedor_formulario'>");
                // out.print("<p class='paciente' href=''>" + Administrador.getOrCreate().siguientePaciente().getNombre() + "</p>");
                out.print("<p class='paciente' href=''>Hay un paciente en espera " + obj.getNombre() + "</p>");
                out.print("<form action='ServletNotificador'>");
                out.print("<input class='boton' type='submit' value='ENVIAR'>");
                out.print("</form>");
                out.print("</div>");
                out.print("</div>");
            } else {
                out.println("<h2 class='paciente'>No hay pacientes en espera</h2>");
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
