package dw.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dw.modelos.entidades.user.Usuario;
import dw.servicios.impl.UsuarioService;
import java.util.List;

@WebServlet(name = "UsuariosServlet", urlPatterns = {"/UsuariosServlet"})
public class UsuariosServlet extends HttpServlet {

    private final UsuarioService usuarioService = new UsuarioService();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UsuariosServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UsuariosServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <-- -->
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null) {
                action = "listar";
            }

            switch (action) {
                case "listar":
                    listarUsuarios(request, response);
                    break;
                case "crear":
                    //mostrarFormularioCrear(request, response);
                    break;
                case "editar":
                    //mostrarFormularioEditar(request, response);
                    break;
                case "buscar":
                    //buscarUsuario(request, response);
                    break;
                default:
                //listarUsuarios(request, response);
            }
        } catch (Exception e) {
            throw new ServletException("Error en servlet: " + e.getMessage(), e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Usuario> usuarios = this.usuarioService.obtenerTodosUsuarios();
            
            request.setAttribute("usuarios", usuarios); 
            request.getRequestDispatcher("/WEB-INF/views/usuarios/listar.jsp")
                    .forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error al cargar usuarios: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp")
                    .forward(request, response);
        }
    }
}
