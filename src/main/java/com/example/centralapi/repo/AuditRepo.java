package com.example.centralapi.repo;

import com.example.centralapi.entity.AuditEntity;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface AuditRepo extends JpaRepository<AuditEntity,Long> {

}
