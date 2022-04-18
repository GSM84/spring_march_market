package ru.geekbrains.march.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.api.ProductDto;
import ru.geekbrains.march.market.api.ResourceNotFoundException;
import ru.geekbrains.march.market.core.converters.ProductConverter;
import ru.geekbrains.march.market.core.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.findAll().stream().map(productConverter::entityToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id){
        return productConverter.entityToDto(productService.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Продукт с id-%s не существует.", id))));
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable  Long id){
        productService.deleteProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewProdcut(@RequestBody ProductDto productDto){
        productService.createNewProduct(productDto);
    }
}