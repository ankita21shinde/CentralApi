package com.example.centralapi.service;

import com.example.centralapi.entity.AuditEntity;
import com.example.centralapi.repo.AuditRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class CentralServiceImpl implements CentralService {

    @Autowired
    public AuditRepo auditRepo;

    @Override
    public AuditEntity save(AuditEntity auditEntity) {
        return auditRepo.save(auditEntity);
    }


}
