package com.ziad.productservice.repository;

import com.ziad.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product , String> {

}
