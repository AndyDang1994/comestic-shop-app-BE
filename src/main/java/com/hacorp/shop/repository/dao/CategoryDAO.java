package com.hacorp.shop.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hacorp.shop.repository.entity.Category;


@Repository
public interface CategoryDAO  extends JpaRepository<Category, String>{

}
