package br.com.zoi.apptdah.config.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<RestErrorMessage> handleValidationExceptions(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
				.collect(Collectors.toList());

		RestErrorMessage errorMessage = new RestErrorMessageValid(HttpStatus.BAD_REQUEST, "Error de Validação", errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<RestErrorMessage> dataIntegrity(DataIntegrityViolationException ex) {
		RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.CONFLICT, ex.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<RestErrorMessage> runTime(RuntimeException ex) {
		RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	}

	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<RestErrorMessage> handleExpiredJwtException(ExpiredJwtException ex) {
		RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.UNAUTHORIZED, "Token JWT expirado");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
	}

	@ExceptionHandler(JwtException.class)
	public ResponseEntity<RestErrorMessage> handleJwtException(JwtException ex) {
		RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.UNAUTHORIZED, "Token JWT inválido");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
	}

}