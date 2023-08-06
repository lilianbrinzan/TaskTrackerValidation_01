package de.ait.tracker.validation.handler;

import de.ait.tracker.validation.dto.ValidationErrorDto;
import de.ait.tracker.validation.dto.ValidationErrorsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorsDto> handleException(MethodArgumentNotValidException e) {
        // собираем список всех ошибок в JSON-виде
        List<ValidationErrorDto> validationErrors = e.getBindingResult().getAllErrors().stream() // пробегаем все ошибки с помощью stream
                .filter(error -> error instanceof FieldError) // выбрали только FieldError
                .map(error -> (FieldError) error) // сделали преобразование
                .map(error -> { // собираем информацию об ошибке в формате JSON
                    ValidationErrorDto errorDto = ValidationErrorDto.builder()
                            .field(error.getField())
                            .message(error.getDefaultMessage())
                            .build();

                    if (error.getRejectedValue() != null) { // если пользователь ввел значение, которое не нравится валидатору
                        errorDto.setRejectedValue(error.getRejectedValue().toString()); // то добавим это значение в ответ
                    }

                    return errorDto;
                })
                .collect(Collectors.toList());

        return ResponseEntity // отправляем
                .badRequest()
                .body(ValidationErrorsDto.builder()
                        .errors(validationErrors)
                        .build());
    }
}
