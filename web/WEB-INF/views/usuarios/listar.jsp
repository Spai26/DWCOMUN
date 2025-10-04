<%-- 
    Document   : Listar
    Created on : 3 oct. 2025, 11:32:47
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true" %>
<%@page errorPage="/WEB-INF/views/error.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <title>Lista de Usuarios</title>
         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h1 class="h3">Lista de Usuarios</h1>
            <a href="${pageContext.request.contextPath}/UsuariosServlet" class="btn btn-primary">Actualizar</a>
        </div>
        
        <c:if test="${not empty error}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                <strong>Error:</strong> ${error}
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        </c:if>
        
        <c:choose>
            <c:when test="${not empty usuarios}">
                <div class="table-responsive">
                    <table class="table table-striped table-hover">
                        <thead class="table-dark">
                            <tr>
                                <th>ID</th>
                                <th>Email</th>
                                <th>Nombres</th>
                                <th>Apellidos</th>
                                <th>Estado</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="usuario" items="${usuarios}">
                                <tr>
                                    <td><code>${usuario.getId().toString()}</code></td>
                                    <td>${usuario.email()}</td>
                                    <td>${usuario.names()}</td>
                                    <td>${usuario.lastNames()}</td> 
                                    <td>${usuario.isActive() ? 'Activo' : 'Inactivo'}</td>
                                    <td>
                                        <span class="badge ${usuario.active() ? 'bg-success' : 'bg-secondary'}">
${usuario.active() ? 'Activo' : 'Inactivo'}
                                        </span>
                                    </td>
                                    <td>
                                        <button class="btn btn-sm btn-outline-warning">Editar</button>
                                        <button class="btn btn-sm btn-outline-danger">Eliminar</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                
                <div class="alert alert-info">
                    Total de usuarios: <strong>${usuarios.size()}</strong>
                </div>
            </c:when>
            
            <c:otherwise>
                <div class="alert alert-warning text-center">
                    <h4>No hay usuarios registrados</h4>
                    <p>Parece que no hay usuarios en la base de datos.</p>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
