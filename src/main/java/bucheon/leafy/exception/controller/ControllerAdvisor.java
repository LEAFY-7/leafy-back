package bucheon.leafy.exception.controller;

import bucheon.leafy.slack.SlackApi;
import bucheon.leafy.exception.*;
import bucheon.leafy.exception.dto.ExceptionResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
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

    private final SlackApi slackApi;


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> SlackErrorMessage(Exception e){
        slackApi.sendErrorForSlack(e);

        ExceptionResponse response = ExceptionResponse.builder()
                .code(String.valueOf(500))
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(500).body(response);
    }

    @ExceptionHandler(OAuth2InfoNotExistException.class)
    public ResponseEntity<ExceptionResponse> oauth2InfoNotExistException(OAuth2InfoNotExistException e) {
        int statusCode = e.getStatusCode();

        ExceptionResponse response = ExceptionResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(statusCode).body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleForbiddenException(AccessDeniedException e) {
        ExceptionResponse response = ExceptionResponse.builder()
                .code(String.valueOf(403))
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<ExceptionResponse> authenticationFailedException(AuthenticationFailedException e) {
        ExceptionResponse response = ExceptionResponse.builder()
                .code(String.valueOf(403))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
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

    @ExceptionHandler(PrivateUserException.class)
    public ResponseEntity<ExceptionResponse> privateUserException(PrivateUserException e) {
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

    @ExceptionHandler(EmailSendException.class)
    public ResponseEntity<ExceptionResponse> passwordEmailSendException(EmailSendException e) {
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

    @ExceptionHandler({SecurityException.class, MalformedJwtException.class, ExpiredJwtException.class})
    public ResponseEntity<ExceptionResponse> jwtException(Exception e) {
        ExceptionResponse response = ExceptionResponse.builder()
                .code(String.valueOf(400))
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(400).body(response);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> badCredentialsException(BadCredentialsException e) {
        ExceptionResponse response = ExceptionResponse.builder()
                .code(String.valueOf(404))
                .message("비밀번호가 잘못되었슴니다.")
                .build();

        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(ReadFailedException.class)
    public ResponseEntity<ExceptionResponse> ReadFailedException(ReadFailedException e) {
        ExceptionResponse response = ExceptionResponse.builder()
                .code(String.valueOf(404))
                .message("불러오기가 실패했습니다.")
                .build();

        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(WriteFailedException.class)
    public ResponseEntity<ExceptionResponse> writeFailedException(WriteFailedException e) {
        int statusCode = e.getStatusCode();

        ExceptionResponse response = ExceptionResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

        return ResponseEntity.status(statusCode).body(response);
    }

    @ExceptionHandler(NoticeNotFoundException.class)
    public ResponseEntity<ExceptionResponse> noticeNotFoundException(NoticeNotFoundException e) {
        int statusCode = e.getStatusCode();

        ExceptionResponse response = ExceptionResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

        return ResponseEntity.status(statusCode).body(response);
    }

    @ExceptionHandler(QnaNotFoundException.class)
    public ResponseEntity<ExceptionResponse> QnaNotFoundException(QnaNotFoundException e) {
        int statusCode = e.getStatusCode();

        ExceptionResponse response = ExceptionResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

        return ResponseEntity.status(statusCode).body(response);
    }

    @ExceptionHandler(QnaCommentNotFoundException.class)
    public ResponseEntity<ExceptionResponse> QnaCommentNotFoundException(QnaNotFoundException e) {
        int statusCode = e.getStatusCode();

        ExceptionResponse response = ExceptionResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

        return ResponseEntity.status(statusCode).body(response);
    }

    @ExceptionHandler(ModifyFailedException.class)
    public ResponseEntity<ExceptionResponse> modifyFailedException(ModifyFailedException e) {
        int statusCode = e.getStatusCode();

        ExceptionResponse response = ExceptionResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

        return ResponseEntity.status(statusCode).body(response);
    }

    @ExceptionHandler(RemoveFailedException.class)
    public ResponseEntity<ExceptionResponse> removeFailedException(RemoveFailedException e) {
        int statusCode = e.getStatusCode();

        ExceptionResponse response = ExceptionResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

        return ResponseEntity.status(statusCode).body(response);
    }

}
