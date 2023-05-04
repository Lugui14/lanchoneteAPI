package api.lanches.lanchonete.modules.category.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryDTO(
        @NotBlank
        String category,

        @NotBlank
        String description
) {}
