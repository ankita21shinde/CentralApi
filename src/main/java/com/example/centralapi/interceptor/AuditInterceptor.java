package com.example.centralapi.interceptor;



import com.example.centralapi.entity.AuditEntity;
import com.example.centralapi.repo.AuditRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

@Component
@Slf4j
public class AuditInterceptor implements HandlerInterceptor {
    private WebClient.Builder builder;

    @Autowired
    private AuditRepo auditRepo;


    Date requestTime = new Date(); // Capture the current date and time

    private long startTime;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Pre-handle logic to capture information
        startTime = System.currentTimeMillis();
        Date requestTime = new Date(); // Capture the current date and time
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Request Time: " + dateFormat.format(requestTime));
        request.setAttribute("startTime", startTime);
        return true;


    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Post Handler method");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        AuditEntity auditEntity  =new AuditEntity();

        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        Date responseTime = new Date(); // Capture the current date and time for response
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        //for error trace
        String errorStackTrace = null;
        if (ex != null) {
            // Capture the exception stack trace in a variable
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            errorStackTrace = sw.toString();
            System.out.println(" error trace : " + errorStackTrace);
        }


        //for response
        ContentCachingResponseWrapper wrapper;
        if (response instanceof ContentCachingResponseWrapper) {
            wrapper = (ContentCachingResponseWrapper) response;
        } else {
            wrapper = new ContentCachingResponseWrapper(response);
        }
        String responseContent = getResponse(wrapper);


        //for storing into database
        auditEntity.setRequestTime(dateFormat.format(requestTime));
        auditEntity.setResponseTime(dateFormat.format(responseTime));
        auditEntity.setStatusCode(response.getStatus());
        auditEntity.setTimeTaken(String.valueOf(timeTaken));
        auditEntity.setRequestURI(request.getRequestURI());
        auditEntity.setRequestMethod(request.getMethod());
        auditEntity.setRequestHeaderName(getRequestHeaderNames(request));
        auditEntity.setContentType(request.getContentType());
        auditEntity.setRequestID(request.getRequestId()); //
        auditEntity.setHostName(request.getServerName());
        auditEntity.setResponse(responseContent);
        auditEntity.setErrorTrace(errorStackTrace);

//      auditRepo.save(auditEntity);


        WebClient webClient = WebClient.create();
        webClient.post()
                .uri("http://localhost:7000/api/data")
                .body(BodyInserters.fromValue(auditEntity))
                .retrieve()
                .bodyToMono(String.class)
                .block();


    }

    private String getRequestHeaderNames(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        StringBuilder headerNamesStr = new StringBuilder();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headerNamesStr.append(headerName).append(", ");
        }
        return headerNamesStr.toString();
    }

    private String getResponse(ContentCachingResponseWrapper contentCachingResponseWrapper) {

        String response = IOUtils.toString(contentCachingResponseWrapper.getContentAsByteArray(), contentCachingResponseWrapper.getCharacterEncoding());
        return response;
    }


    }


