package es.codeurjc.board.errorHandler;

import org.springframework.boot.webmvc.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

import es.codeurjc.board.rest.dto.ApiErrorDTO;

@Controller
public class ExceptionController implements ErrorController {

    @RequestMapping("/error")
    public Object handleError(HttpServletRequest request, Model model) {

        Integer statusCode = (Integer) request.getAttribute(
                RequestDispatcher.ERROR_STATUS_CODE
        );

        if (statusCode == null) {
            statusCode = 500;
        }

        // Si la petición original era para la API REST, devolver JSON en vez de HTML
        String originalUri = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
        if (originalUri == null) {
            originalUri = request.getRequestURI();
        }

        if (originalUri != null && originalUri.startsWith("/api/")) {
            return handleApiError(statusCode, originalUri);
        }

        // Para peticiones web, devolver la plantilla HTML como siempre
        model.addAttribute("statusCode", statusCode);

        if (statusCode == 404) {
            model.addAttribute("errorType", "Página no encontrada");
        } else if (statusCode >= 500) {
            model.addAttribute("errorType", "Error en el servidor");
        }

        return "error";
    }

    private ResponseEntity<ApiErrorDTO> handleApiError(Integer statusCode, String path) {
        String error;
        String message;

        if (statusCode == 404) {
            error = "NOT_FOUND";
            message = "Recurso no encontrado";
        } else if (statusCode == 401) {
            error = "UNAUTHORIZED";
            message = "No autenticado. Credenciales inválidas o sesión expirada.";
        } else if (statusCode == 403) {
            error = "FORBIDDEN";
            message = "Acceso denegado. No tienes permisos suficientes.";
        } else if (statusCode >= 500) {
            error = "INTERNAL_SERVER_ERROR";
            message = "Error interno del servidor";
        } else {
            error = "ERROR";
            message = "Ha ocurrido un error";
        }

        ApiErrorDTO body = new ApiErrorDTO(statusCode, error, message, path);

        return ResponseEntity.status(HttpStatus.valueOf(statusCode))
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);
    }
}
