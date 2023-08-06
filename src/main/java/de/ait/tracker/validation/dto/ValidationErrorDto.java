package de.ait.tracker.validation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Ошибка валидации")
public class ValidationErrorDto {

    @Schema(description = "Поле, в котором возникла ошибка", example = "email")
    private String field;

    @Schema(description = "Сообщение об ошибке", example = "must be a well-formed email address")
    private String message;

    @Schema(description = "Какое значение было получено о клиента", example = "sidikov.marsel.com")
    private String rejectedValue;
}
