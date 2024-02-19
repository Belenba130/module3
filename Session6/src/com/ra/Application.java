package com.ra;

import com.ra.model.entity.ProductEntity;
import com.ra.repository.IRepository;
import com.ra.repository.impl.Repository;

public class Application {
    public static void main(String[] args) {
        IRepository<ProductEntity, String> productRepository = new Repository<>();

        for (ProductEntity p : productRepository.findAll(ProductEntity.class)) {
            System.out.println(p.getId() + ": " + p.getName());
        }

        System.out.println("findId()");
        ProductEntity p = productRepository.findId("P001", ProductEntity.class);
        System.out.println(p.getId() + " | " + p.getName());
    }
}
