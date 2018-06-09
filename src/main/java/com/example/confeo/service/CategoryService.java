package com.example.confeo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.confeo.model.Category;
import com.example.confeo.repository.CategoryRepository;

@Service
public class CategoryService {
	private final CategoryRepository categoryRepository;
	
	@Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
	
	public List<Category> findAll(){
		return this.categoryRepository.findAll();
	}
	
}
