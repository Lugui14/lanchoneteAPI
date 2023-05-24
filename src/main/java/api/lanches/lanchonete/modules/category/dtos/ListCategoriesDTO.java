package api.lanches.lanchonete.modules.category.dtos;

import api.lanches.lanchonete.modules.category.infra.Category;

public record ListCategoriesDTO(
        long idcategory,
        String category,
        String description,
        boolean isactive
) {

    public ListCategoriesDTO(Category category) {
        this(category.getIdcategory(), category.getCategory(), category.getDescription(), category.isIscategoryactive());
    }
}
