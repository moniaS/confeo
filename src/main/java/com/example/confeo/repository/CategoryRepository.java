package com.example.confeo.repository;

import com.example.confeo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mstobieniecka on 2018-05-26.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
