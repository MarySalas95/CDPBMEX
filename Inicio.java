package com.comp.complementos.servlets;

import com.comp.complementos.DAO.cabeceraDAO;
import com.complementos.ConsultarCDP;
import com.comp.complementos.DTO.ActualizarCabeceraDTO;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import static java.lang.System.out;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mary Villanueva
 * 
 * Servlet encargado de conectar la información que viaja de la Plantilla Principal.html a la clase encargada de Consultar el CDP
 * 
 */
@WebServlet(name = "Inicio", urlPatterns = {"/Inicio"})
public class Inicio extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        if (request.getSession().getAttribute("usuario") == null) {
            response.sendRedirect("Index.jsp");
            System.out.println("La Session ya fue Cerrada!!");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //processRequest(request, response);
        HttpSession misession = (HttpSession) request.getSession();

        if (request.getSession().getAttribute("usuario") == null) {
            response.sendRedirect("Index.jsp");
            out.println("La Session ya fue Cerrada!!");
        } else {

            //misession.setAttribute("usuario", misession.getAttribute("usuario"));
            String fechaInicio = request.getParameter("fechaInicio").replace("-", "");
            String fechaFin = request.getParameter("fechaFin").replace("-", "");
            int fechaI = parseInt(fechaInicio);
            int fechaF = parseInt(fechaFin);
            fechaInicio = request.getParameter("fechaInicio");
            fechaFin = request.getParameter("fechaFin");

            out.println("Aplicando filtro de CDP Del: " + fechaInicio + " al " + fechaFin);

            //Formatear Fecha
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d1, d2;

            //Consultar los Complementos por rango de fechas.
            ConsultarCDP consulta = new ConsultarCDP();
            consulta.sendComplementos(fechaI, fechaF);

            try {
                d1 = sdf.parse(fechaInicio);
                d2 = sdf.parse(fechaFin);

                cabeceraDAO cabecera;
                ActualizarCabeceraDTO cab = new ActualizarCabeceraDTO();

                cabecera = cab.ActualizarCabecera(fechaI, fechaF);

                misession.setAttribute("cabecera", cabecera.getCabecera());
                misession.setAttribute("detalle", cabecera.getDetalle());
                misession.setAttribute("bancos", cabecera.getCtasBancarias());
                misession.setAttribute("fechaI", d1);
                misession.setAttribute("fechaF", d2);
                response.sendRedirect("Principal.jsp");

            } catch (ParseException ex) {
                getLogger(Inicio.class.getName()).log(SEVERE, null, ex);
            } 
        }
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
