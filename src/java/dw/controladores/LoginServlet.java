package dw.controladores;

import dw.modelos.dto.LoginDTO;
import facade.FacadeLogin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import dw.modelos.entidades.user.Usuario;
import utils.valuesObjects.AsignRol;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        LoginDTO login = new LoginDTO();

        login.setPassword(request.getParameter("password"));
        login.setUsuario(request.getParameter("usuario"));

        if (!login.isValid()) {
            request.setAttribute("error", "Usuario y contraseña son requeridos");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        String tipo = login.getTypeEntry();
        if ("INVALID".equals(tipo)) {
            request.setAttribute("error", "El usuario debe ser un email válido o DNI de 8 dígitos");
            request.setAttribute("usuarioIntentado", login.getUsuario()); // Mantener el valor en el form
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        FacadeLogin facade = new FacadeLogin();
        Optional<Usuario> usuarioOpt = facade.run(login);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            String destino = determinarDestino(usuario.rol());
            response.sendRedirect(request.getContextPath() + destino);
        } else {
            // Login fallido
            request.setAttribute("error", "Credenciales incorrectas o usuario inactivo");
            request.setAttribute("usuarioIntentado", login.getUsuario()); // Mantener el valor en el form
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }

    private String determinarDestino(AsignRol rol) {
        if (rol == null) {
            return "/index.jsp";
        }

        switch (rol) {
            case ADMIN:
                return "/admin/dashboard.jsp";
            case MEDICO:
                return "/medico/dashboard.jsp";
            case PACIENTE:
                return "/paciente/dashboard.jsp";
            default:
                return "/index.jsp";
        }
    }
}
