package api.lanches.lanchonete.modules.category.dtos;

public record UpdateCategoryDTO(long idcategory, String category, String description, boolean iscategoryactive) {
}
