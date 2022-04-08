package ru.geekbrains.march.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.entities.Category;
import ru.geekbrains.march.market.repositories.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category findCategoryByTitle(String name){
        return categoryRepository.findByTitle(name).get();
    }
}
