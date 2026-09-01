package dev.placeflow.api;

import java.time.Instant;
import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ApiExceptionHandler {
 record ErrorBody(Instant timestamp,int status,String message,Object details){}
 @ExceptionHandler(ResponseStatusException.class) ResponseEntity<ErrorBody> status(ResponseStatusException e){return ResponseEntity.status(e.getStatusCode()).body(new ErrorBody(Instant.now(),e.getStatusCode().value(),e.getReason(),null));}
 @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<ErrorBody> validation(MethodArgumentNotValidException e){Map<String,String> fields=new LinkedHashMap<>();e.getBindingResult().getFieldErrors().forEach(x->fields.putIfAbsent(x.getField(),x.getDefaultMessage()));return ResponseEntity.badRequest().body(new ErrorBody(Instant.now(),400,"Validation failed",fields));}
}
