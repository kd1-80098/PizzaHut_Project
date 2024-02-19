package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.entities.Category;



public interface CategoryService {

	public Optional<Category> findCategoryByID(Long id);

	public void deleteCategoryByID(Long id);

	public List<Category> allCategory();

}
