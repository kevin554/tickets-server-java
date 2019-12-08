package servlet;

import com.google.gson.Gson;
import dto.Paciente;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Administrador;

public class ServletRegistro extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String evento = request.getParameter("evento");

        try (PrintWriter out = response.getWriter()) {
            if (evento.equals("registrar_paciente")) {
                registrarPaciente(request, out);
            }

            if (evento.equals("todos")) {
                seleccionarTodos(request, out);
            }
            
            if (evento.equals("seleccionar")) {
                seleccionar(request, out);
            }
            
            if (evento.equals("actualizar")) {
                actualizar(request, out);
            }
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

    private void registrarPaciente(HttpServletRequest request, PrintWriter out) {
        Gson gerson = new Gson();
        
        String objStr = request.getParameter("obj");

        Paciente obj = gerson.fromJson(objStr, Paciente.class);
//        obj.setCodigoID(Administrador.getOrCreate().getSiguienteID());
        obj.setTicket(Administrador.getOrCreate().siguienteTicket());

        Administrador.getOrCreate().agregarPaciente(obj);

        /* CON SQLITE
        out.print(obj.getCodigoID());
        */
        
        out.print(obj.getTicket());
    }

    private void seleccionarTodos(HttpServletRequest request, PrintWriter out) {
        Gson gson = new Gson();
        out.print( gson.toJson(Administrador.getOrCreate().getCola().todos()) );
    }

    private void seleccionar(HttpServletRequest request, PrintWriter out) {
        Gson gson = new Gson();
        
        String ID = request.getParameter("codigo_id");
        Paciente obj = Administrador.getOrCreate().obtener(Integer.parseInt(ID));
        
        out.print( gson.toJson(obj) );
    }

    private void actualizar(HttpServletRequest request, PrintWriter out) {
        Gson gson = new Gson();
        
        String objStr = request.getParameter("obj");
        Paciente obj = gson.fromJson(objStr, Paciente.class);
    }

}
