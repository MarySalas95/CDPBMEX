package com.comp.complementos.servlets;


import java.io.IOException;
import static java.lang.System.out;
import javax.servlet.RequestDispatcher;
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
 * Servlet encargado de validar la información que viaja de la Plantilla Index.html para validar el acceso a la aplicacion.
 * 
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //processRequest(request, response);

        String mensajeLoadLogin;
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        RequestDispatcher rd = request.getRequestDispatcher("/Index.jsp");
        HttpSession session = request.getSession(false);

        if (!usuario.isEmpty() && !password.isEmpty()) {
            if (usuario.equals("CDP20") && password.equals("Bell2024")) {
                request.getSession(true);
                out.println("USUARIO EN SESION: " + usuario);
                session.setAttribute("usuario", usuario);
                rd = request.getRequestDispatcher("Principal.jsp");
                rd.forward(request, response);
            } else {
                out.println("Verificar Usuario y/o Contraseña");
                mensajeLoadLogin = "Verificar Usuario y/o Contraseña";
                request.setAttribute("msj", mensajeLoadLogin);
                rd.forward(request, response);
            }
        } else {
            out.println("Debe Ingresar Usuario y/o Contraseña");
            mensajeLoadLogin = "Debe Ingresar Usuario y/o Contraseña";
            request.setAttribute("msj", mensajeLoadLogin);
            rd.forward(request, response);
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
