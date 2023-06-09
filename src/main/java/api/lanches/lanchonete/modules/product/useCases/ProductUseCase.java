package api.lanches.lanchonete.modules.product.useCases;

import api.lanches.lanchonete.infra.exceptions.ValidationException;
import api.lanches.lanchonete.modules.category.infra.CategoryRepository;
import api.lanches.lanchonete.modules.product.dtos.CreateProductDTO;
import api.lanches.lanchonete.modules.product.dtos.DetailProductDTO;
import api.lanches.lanchonete.modules.product.dtos.ListProductDTO;
import api.lanches.lanchonete.modules.product.dtos.ProductDTO;
import api.lanches.lanchonete.modules.product.infra.Product;
import api.lanches.lanchonete.modules.product.infra.ProductRepository;
import api.lanches.lanchonete.modules.product.useCases.validation.ProductValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductUseCase {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private List<ProductValidation> validations;

    public ListProductDTO create(CreateProductDTO data) {

        validations.forEach(v -> v.validate(new ProductDTO(
                null,
                data.product(),
                data.price(),
                data.description(),
                data.idcategory(),
                true)));


        //individual validation
        if(productRepository.existsProductByProduct(data.product())) {
            throw new ValidationException("Já existe um produto com esse nome.");
        }

        if(data.idcategory() == null ||
                !categoryRepository.existsCategoryByIdcategoryAndIscategoryactiveTrue(data.idcategory())) {
            throw new ValidationException("A Categoria é invalida ou não existe.");
        }

        var category = categoryRepository.getReferenceById(data.idcategory());
        var product = new Product(
                null,
                data.product(),
                data.price(),
                data.description(),
                true,
                category,
                null
        );

        productRepository.save(product);
        return new ListProductDTO(product);
    }

    public Page<ListProductDTO> list(Pageable pageable, boolean isactive) {
        if(isactive) {
            return productRepository.findAllByIsproductactiveTrue(pageable).map(ListProductDTO::new);
        }

        return productRepository.findAllByIsproductactiveFalse(pageable).map(ListProductDTO::new);
    }

    public ListProductDTO getOne(Long idproduct) {
        return new ListProductDTO(productRepository.getReferenceById(idproduct));
    }

    public ListProductDTO update(ProductDTO data) {

        validations.forEach(v -> v.validate(data));

        //individual validation
        if(!productRepository.existsById(data.idproduct())) {
            throw new ValidationException("Produto inexistente");
        }

        var product = productRepository.getReferenceById(data.idproduct());
        var category = categoryRepository.getReferenceById(data.idcategory());

        var newproduct = new DetailProductDTO(
                data.idproduct(),
                data.product(),
                data.price(),
                data.description(),
                data.isproductactive(),
                category);

        product.updateProduct(newproduct);

        return new ListProductDTO(product);
    }

    public void delete(Long idproduct) {

        //individual validation
        if(!productRepository.existsProductByIdproductAndIsproductactiveTrue(idproduct)) {
            throw new ValidationException("Esse produto não existe ou ja está inativo");
        }

        var product = productRepository.getReferenceById(idproduct);
        product.delete();
    }

}
