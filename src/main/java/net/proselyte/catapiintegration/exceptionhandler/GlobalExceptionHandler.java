package net.proselyte.catapiintegration.exceptionhandler;

import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import net.proselyte.catapiintegration.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<ErrorDto> handleFeignItemNotFound(FeignException.NotFound ex, HttpServletRequest request) {
        ErrorDto errorDto = new ErrorDto(
                "Cat not found in the external API",
                "CAT_API_ITEM_NOT_FOUND",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(CatApiUnauthorizedException.class)
    public ResponseEntity<ErrorDto> handleUnauthorizedException(CatApiUnauthorizedException ex, HttpServletRequest request) {
        ErrorDto errorDto = new ErrorDto(
                ex.getMessage(),
                "CAT_API_UNAUTHORIZED",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDto);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDto> handleNoHandlerFound(NoHandlerFoundException ex, HttpServletRequest request) {
        ErrorDto errorDto = new ErrorDto(
                "Endpoint not found",
                "CAT_API_NO_HANDLER",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDto> handleBadRequest(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        ErrorDto errorDto = new ErrorDto(
                "Invalid parameter: " +ex.getName(),
                "CAT_API_BAD_REQUEST",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGeneric(Exception ex, HttpServletRequest request) {
        ErrorDto errorDto = new ErrorDto(
                "Internal service error",
                "INTERNAL_SERVER_ERROR",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }















}
