package com.wlhse.service;

import com.wlhse.util.R;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @Author tobing
 * @Date 2020/11/21 21:07
 * @Description
 */
public interface DashboardSecurityService {
    R uploadDashboardSecurity(MultipartFile file);

    R uploadDashboardSecurityProject(MultipartFile file);

    R uploadDashboardSecurityMillion(MultipartFile file);

    ResponseEntity<byte[]> downloadDashboardSecurityTemplate() throws IOException;

    ResponseEntity<byte[]> downloadDashboardSecurityProjectTemplate() throws IOException;

    ResponseEntity<byte[]> downloadDashboardSecurityMillionTemplate() throws IOException;

    R queryDashboardSecurity(String companyCode);

    R queryDashboardSecurityMillion(String companyCode);

    R queryDashboardSecurityProjectByLevel(String companyCode, String projectLevel);

    R queryDashboardSecurityProjectCount(String companyCode);
}
