package bucheon.leafy.exception.controller;

import bucheon.leafy.application.service.SlackService;
import bucheon.leafy.exception.*;
import bucheon.leafy.exception.dto.ExceptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {

    private final SlackService slackService;

    @ExceptionHandler(Exception.class)
    public void SlackErrorMessage(Exception e){
        slackService.sendErrorForSlack(e);
    }

    @ExceptionHandler(PasswordNotMatchedException.class)
    public ResponseEntity<ExceptionResponse> passwordNotMatchedException(PasswordNotMatchedException e) {
        int statusCode = e.getStatusCode();

        ExceptionResponse response = ExceptionResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

        return ResponseEntity.status(statusCode).body(response);
    }

    @ExceptionHandler(FollowNotFoundException.class)
    public ResponseEntity<ExceptionResponse> followNotFoundException(FollowNotFoundException e) {
        int statusCode = e.getStatusCode();

        ExceptionResponse response = ExceptionResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

        return ResponseEntity.status(statusCode).body(response);
    }

    @ExceptionHandler(ExistException.class)
    public ResponseEntity<ExceptionResponse> existException(ExistException e) {
        int statusCode = e.getStatusCode();

        ExceptionResponse response = ExceptionResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

        return ResponseEntity.status(statusCode).body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> userNotFoundException(UserNotFoundException e) {
        int statusCode = e.getStatusCode();

        ExceptionResponse response = ExceptionResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

        return ResponseEntity.status(statusCode).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> blogException(MethodArgumentNotValidException e) {
        Map<String,String> error = new HashMap<>();
        e.getAllErrors().forEach(c -> error.put(((FieldError) c).getField(), c.getDefaultMessage()));
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> responseStatusException(ResponseStatusException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(FeedNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleFeedNotFoundException(FeedNotFoundException e) {
        int statusCode = e.getStatusCode();

        ExceptionResponse response = ExceptionResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

        return ResponseEntity.status(statusCode).body(response);
    }

    @ExceptionHandler(UserNotVerifiedException.class)
    public ResponseEntity<ExceptionResponse> userNotVerifiedException(UserNotVerifiedException e) {
        int statusCode = e.getStatusCode();

        ExceptionResponse response = ExceptionResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

        return ResponseEntity.status(statusCode).body(response);
    }

    @ExceptionHandler(AlarmDataAccessException.class)
    public ResponseEntity<ExceptionResponse> alarmDataAccessException(AlarmDataAccessException e) {
        int statusCode = e.getStatusCode();

        ExceptionResponse response = ExceptionResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

        return ResponseEntity.status(statusCode).body(response);
    }

    @ExceptionHandler(AlarmNotExistException.class)
    public ResponseEntity<ExceptionResponse> alarmNotExistException(AlarmNotExistException e) {
        int statusCode = e.getStatusCode();

        ExceptionResponse response = ExceptionResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

        return ResponseEntity.status(statusCode).body(response);
    }

    @ExceptionHandler(UserPasswordDataAccessException.class)
    public ResponseEntity<ExceptionResponse> userPasswordDataAccessException(UserPasswordDataAccessException e) {
        int statusCode = e.getStatusCode();

        ExceptionResponse response = ExceptionResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

        return ResponseEntity.status(statusCode).body(response);
    }

    @ExceptionHandler(SelfTargetException.class)
    public ResponseEntity<ExceptionResponse> selfTargetException(SelfTargetException e) {
        int statusCode = e.getStatusCode();

        ExceptionResponse response = ExceptionResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

        return ResponseEntity.status(statusCode).body(response);
    }

    @ExceptionHandler(PasswordEmailSendException.class)
    public ResponseEntity<ExceptionResponse> passwordEmailSendException(PasswordEmailSendException e) {
        int statusCode = e.getStatusCode();

        ExceptionResponse response = ExceptionResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

        return ResponseEntity.status(statusCode).body(response);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> sqlIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        String errorMessage = e.getMessage();
        Integer responseStatusCode;
        String responseMessage;

        if (errorMessage.contains("Duplicate entry")) {
            responseMessage = "Unique Key 제약조건을 위배했습니다.";
            responseStatusCode = 500;
        } else if (errorMessage.contains("FOREIGN KEY")) {
            responseMessage = "Foreign Key 관련 문제가 발생했습니다.";
            responseStatusCode = 500;
        } else if (errorMessage.contains("NOT NULL")) {
            responseMessage = "NOT NULL 제약조건을 위배했습니다.";
            responseStatusCode = 500;
        } else if (errorMessage.contains("CHECK constraint")) {
            responseMessage = "Check 제약조건을 위배했습니다.";
            responseStatusCode = 500;
        } else {
            responseMessage = "기타 무결성 제약조건을 위배했습니다.";
            responseStatusCode = 500;
        }

        ExceptionResponse response = ExceptionResponse.builder()
                .code(String.valueOf(e.getErrorCode()))
                .message(responseMessage)
                .build();

        return ResponseEntity.status(responseStatusCode).body(response);
    }

}
