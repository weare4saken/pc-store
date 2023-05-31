package com.weare4saken.pcstore.service;

import java.util.List;

public interface ProductDtoService<T> {

    T addProduct(T product);
    T updateProduct(String serialNumber, T product);
    List<T> getAllProducts();
    T getProduct(String serialnumber);
    boolean validCheck(T product);

}
