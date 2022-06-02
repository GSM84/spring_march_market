package ru.geekbrains.march.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ru.geekbrains.march.market.api.ProductDto;
import ru.geekbrains.march.market.api.ResourceNotFoundException;
import ru.geekbrains.march.market.core.entities.Product;
import ru.geekbrains.march.market.core.repositories.ProductRepository;
import ru.geekbrains.march.market.core.repositories.specifications.ProductsSpecifications;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public Page<Product> findAll(Map<String, String> allParams) {
        int page;
        int pageSize;

        if (!allParams.containsKey("p")) {
            page = 1;
        } else {
            page = Integer.valueOf(allParams.get("p"));
        }
        if (!allParams.containsKey("page_size")) {
            pageSize = 5;
        } else {
            pageSize = Integer.valueOf(allParams.get("page_size"));
        }
        Specification<Product> spec = Specification.where(null);
        if (allParams.containsKey("title_part")) {
            spec = spec.and(ProductsSpecifications.titleLike(allParams.get("title_part")));
        }
        if (allParams.containsKey("min_price")) {
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThen(BigDecimal.valueOf(Integer.valueOf(allParams.get("min_price")))));
        }
        if (allParams.containsKey("max_price")) {
            spec = spec.and(ProductsSpecifications.priceLessThanOrEqualsThan(BigDecimal.valueOf(Integer.valueOf(allParams.get("max_price")))));
        }

        return productRepository.findAll(spec, PageRequest.of(page - 1, pageSize));
    }

    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }

    public void createNewProduct(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setCategory(categoryService.findCategoryByTitle(productDto.getCategoryTitle())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Категория %s не найдена.", productDto.getCategoryTitle()))
                )
        );

        productRepository.save(product);
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
}
