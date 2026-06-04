package co.istad.productapidemo.repository;

import co.istad.productapidemo.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Repository
public class CategoryRepository {
    // we aren't working with dbs yet, so this is an example
    private final List<Category> categoryList = new ArrayList<>(){{
        add(new Category(1001, "Drinks", "Drinks like cola",true));
        add(new Category(1002, "Electronics", "Tech stuff, you get it",true));
        add(new Category(1003, "Books", "Who reads books now?",false));
    }};

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public Category createCategory(Category cg) {
        categoryList.add(cg);
        return cg;
    }

    public Category findCategoryById(Integer id) {
        return categoryList.stream()
                .filter(category -> Objects.equals(category.getId(), id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Category not found"));
    }

    public boolean deleteCategoryById(Integer id) {
        return categoryList.removeIf(category -> Objects.equals(category.getId(), id));
    }

    public Category updateCategory(Category updatedCategory) {
        for (int i = 0; i < categoryList.size(); i++) {
            var category = categoryList.get(i);
            if (category.getId() == updatedCategory.getId()) {
                categoryList.set(i, updatedCategory);
                return updatedCategory;
            }
        }
        return null;
    }
}