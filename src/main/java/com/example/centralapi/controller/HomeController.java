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

//    @PostMapping("/create")
//    public ResponseEntity<String> create(@RequestParam String name) {
//        // Create a new User entity
//        AuditEntity auditEntity=new AuditEntity();
//        auditEntity.setQueryParameter(name);
//
//        // Save the user to the database
//        auditRepo.save(auditEntity);
//
//        // Return a success message
//        return ResponseEntity.ok("User created successfully In central Api");
//    }

    @PostMapping("/api/data")
    public void saveAuditData(@RequestBody AuditEntity auditEntity) {
        auditRepo.save(auditEntity);

    }





}
