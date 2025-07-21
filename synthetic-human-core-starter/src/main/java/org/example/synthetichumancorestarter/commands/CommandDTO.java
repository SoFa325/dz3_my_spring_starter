package org.example.synthetichumancorestarter.commands;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public record CommandDTO(
        @NotBlank @Size(max = 1000) String description,
        @NotNull CommandPriority priority,
        @NotBlank @Size(max = 100) String author,
        @NotNull @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX") Instant time
) {}

