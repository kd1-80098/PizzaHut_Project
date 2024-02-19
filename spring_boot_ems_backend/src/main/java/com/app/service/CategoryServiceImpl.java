package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.dao.CategoryDao;
import com.app.entities.Category;


@Component
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryDao categoryDao;
	
	public Category addCategory(Category category) {
		categoryDao.save(category);
		return category;
	}
	
	public Category updateCategory(Category category) {
		
		Category updCat = this.categoryDao.findById(category.getId()).orElse(null);
		updCat.setCategoryName(category.getCategoryName());
		updCat.setDescription(category.getDescription());
		
		this.categoryDao.save(updCat);
		
		return updCat;
	}
	
	@Override
	public Optional<Category> findCategoryByID(Long id) {
		System.out.println("Finding the category with the Id: "+ id);
		return categoryDao.findById(id);
	}

	@Override
	public void deleteCategoryByID(Long id) {
		System.out.println("Finding the category with the Id: "+ id);
		categoryDao.deleteById(id);
	}

	@Override
	public List<Category> allCategory() {
		return categoryDao.findAll();
	}

	
}
