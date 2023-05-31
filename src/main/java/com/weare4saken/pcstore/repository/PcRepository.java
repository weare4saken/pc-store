package com.weare4saken.pcstore.repository;

import com.weare4saken.pcstore.model.Pc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PcRepository extends JpaRepository<Pc, String> {
}
