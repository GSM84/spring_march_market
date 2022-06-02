package ru.geekbrains.march.market.core.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.api.PageDto;
import ru.geekbrains.march.market.api.ProductDto;
import ru.geekbrains.march.market.api.ResourceNotFoundException;
import ru.geekbrains.march.market.core.converters.PageConverter;
import ru.geekbrains.march.market.core.converters.ProductConverter;
import ru.geekbrains.march.market.core.exceptions.AppError;
import ru.geekbrains.march.market.core.services.ProductService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Продукты", description = "Методы работы с продуктами")
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;
    private final PageConverter pageConverter;

    @Operation(
            summary = "Запрос на получения списка продуктов",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PageDto.class))
                    )
            }
    )
    @GetMapping
    public PageDto<ProductDto> getAllProducts(@RequestParam Map<String, String> allParams) {
        return pageConverter.entityToDto(productService.findAll(allParams).map(productConverter::entityToDto));
    }

    @Operation(
            summary = "Запрос на получения продукта по ID",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ),
                    @ApiResponse(
                            description = "Продукт не найден", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id){
        return productConverter.entityToDto(productService.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Продукт с id-%s не существует.", id))));
    }

    @Operation(
            summary = "Запрос на удаление продукта по ID",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable  Long id){
        productService.deleteProductById(id);
    }

    @Operation(
            summary = "Запрос на добавление нового продукта",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "201"
                    ),
                    @ApiResponse(
                            description = "Категория не найдена", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewProdcut(@RequestBody ProductDto productDto){
        productService.createNewProduct(productDto);
    }
}
