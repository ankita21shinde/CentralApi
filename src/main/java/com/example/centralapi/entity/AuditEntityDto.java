//package com.example.centralapi.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class AuditEntityDto {
//    private long id;
//    private String serviceName;
//    private String requestTime;
//    private String responseTime;
//    private int statusCode;
//    private String timeTaken;
//    private String requestURI;
//    private String requestMethod;
//    private String requestHeaderName;
//    private String contentType;
//    private String requestID;
//    private String hostName;
//    private String response;
//    private String errorTrace;
//
//    public AuditEntityDTO(AuditEntity auditEntity) {
//        this.id = auditEntity.getId();
//        this.serviceName = auditEntity.getServiceName();
//        this.requestTime = auditEntity.getRequestTime();
//        this.responseTime = auditEntity.getResponseTime();
//        this.statusCode = auditEntity.getStatusCode();
//        this.timeTaken = auditEntity.getTimeTaken();
//        this.requestURI = auditEntity.getRequestURI();
//        this.requestMethod = auditEntity.getRequestMethod();
//        this.requestHeaderName = auditEntity.getRequestHeaderName();
//        this.contentType = auditEntity.getContentType();
//        this.requestID = auditEntity.getRequestID();
//        this.hostName = auditEntity.getHostName();
//        this.response = auditEntity.getResponse();
//        this.errorTrace = auditEntity.getErrorTrace();
//    }
//}
