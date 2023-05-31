package com.weare4saken.pcstore.repository;

import com.weare4saken.pcstore.model.Harddisk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HarddiskRepository extends JpaRepository<Harddisk, String> {
}
