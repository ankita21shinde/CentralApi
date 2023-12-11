package com.example.centralapi.controller;
import com.example.centralapi.entity.AuditEntity;
import com.example.centralapi.repo.AuditRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {
    @Autowired
    private AuditRepo auditRepo;

    @GetMapping("/audit")
    public String getAudit(){
        return "audited the api";
    }
    @PostMapping("/api/data")
    public void saveAuditData(@RequestBody AuditEntity auditEntity) {
        auditRepo.save(auditEntity);

    }
}
