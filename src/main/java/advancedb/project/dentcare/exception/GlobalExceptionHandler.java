package advancedb.project.dentcare.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleInvalidInput(MethodArgumentNotValidException exception){
        log.info("Invalid input exception");
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->{
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return errors;
    }
    @ExceptionHandler({BadCredentialsException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionResponse handleBadCredentials(BadCredentialsException exception){
        log.info("Bad credentials exception");
        return ExceptionResponse.builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
    @ExceptionHandler({DisabledException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleDisable(DisabledException exception){
        log.info("Disable exception");
        return ExceptionResponse.builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleDisable(IllegalArgumentException exception){
        log.info("Disable exception");
        return ExceptionResponse.builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleResourceNotFound(ResourceNotFoundException exception){
        log.info("Resource not found exception");
        return ExceptionResponse
                .builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleInternalError(Exception exception){
        log.info("Common exception");
        log.info(exception.toString());
        return ExceptionResponse
                .builder()
                .message("Internal server errors")
                .timestamp(LocalDateTime.now())
                .build();
    }
}
