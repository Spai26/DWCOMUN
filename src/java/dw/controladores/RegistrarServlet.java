package dw.controladores;

import dw.modelos.dto.RegistrarDTO;
import facade.FacadeRegistro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import utils.valuesObjects.AsignRol;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegistrarServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RegistrarDTO usuario = extractParameters(request);

        if (!usuario.isValid()) {
            String errors = usuario.getValidationErrors();
            request.setAttribute("error", errors);
            request.getRequestDispatcher("/registro.jsp").forward(request, response);
            return;
        }

        FacadeRegistro facade = new FacadeRegistro();
        boolean result = facade.run(usuario);

        if (result) {
            response.sendRedirect(request.getContextPath() + "/exito.jsp?mensaje=Registro exitoso");

        } else {
            request.setAttribute("error", "No se pudo registrar el usuario");
            request.getRequestDispatcher("/registro.jsp").forward(request, response);
        }
    }

    private RegistrarDTO extractParameters(HttpServletRequest request) {
        RegistrarDTO usuario = new RegistrarDTO();
        usuario.setPassword(request.getParameter("password"));
        usuario.setConfirmPassword(request.getParameter("confirmPassword"));
        usuario.setNombres(request.getParameter("nombres"));
        usuario.setApellidos(request.getParameter("apellidos"));
        usuario.setDni(request.getParameter("dni"));
        usuario.setEmail(request.getParameter("email"));
        usuario.setRol(AsignRol.fromString(request.getParameter("rol")));

        return usuario;
    }
}
