package com.weare4saken.pcstore.repository;

import com.weare4saken.pcstore.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, String> {
}
