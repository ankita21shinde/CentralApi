package com.example.centralapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "serviceAuditing")
public class AuditEntity {

    private String queryParameter;
    private LocalDateTime auditTime;
    private String clientId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String serviceName="Central API";

    //extra fields
    private String requestTime;
    private String responseTime;
    private int StatusCode;
    private String timeTaken;
    private String requestURI;
    private String requestMethod;
    private String requestHeaderName;
    private String contentType;
    private String requestID;
    private String hostName;
    private String response;
    private String errorTrace;

}
