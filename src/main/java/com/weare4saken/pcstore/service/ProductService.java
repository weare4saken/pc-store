package com.weare4saken.pcstore.service;

public interface ProductService<R> {

    R findProductById(String serialNumber);

}
