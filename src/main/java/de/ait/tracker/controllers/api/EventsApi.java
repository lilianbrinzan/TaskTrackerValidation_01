package de.ait.tracker.controllers.api;

import de.ait.tracker.dto.ErrorDto;
import de.ait.tracker.dto.EventDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import de.ait.tracker.dto.NewEventDto;

@RequestMapping("/events")
public interface EventsApi {
    @Operation(summary = "Создание статьи о пользователе", description = "Доступно всем пользователям")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "422", description = "Пользователь с указанным ID отсутствует в системе",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
                    }),
            @ApiResponse(responseCode = "201", description = "Добавленная статья",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = EventDto.class))
                    })
    })
    @PostMapping
    EventDto addEvent(@RequestBody NewEventDto newEvent);
}
