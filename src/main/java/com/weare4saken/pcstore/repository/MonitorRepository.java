package com.weare4saken.pcstore.repository;

import com.weare4saken.pcstore.model.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorRepository extends JpaRepository<Monitor, String> {
}
