package com.hacorp.shop.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hacorp.shop.repository.entity.Product;


@Repository
public interface ProductDAO  extends JpaRepository<Product, Long>{

}
