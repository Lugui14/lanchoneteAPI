package api.lanches.lanchonete.modules.category.dtos;

import jakarta.validation.constraints.NotNull;

public record UpdateCategoryDTO(@NotNull long idcategory, String category, String description, boolean iscategoryactive) {
}
