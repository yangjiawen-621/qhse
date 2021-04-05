package com.wlhse.service;

import com.wlhse.util.R;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author tobing
 * @Date 2020/11/23 19:58
 * @Description
 */
public interface DashboardEnvironmentManagementService {
    R uploadDashboardEnvironmentManagement(MultipartFile file);

    ResponseEntity<byte[]> downloadDashboardEnvironmentManagementTemplate() throws IOException;

    R queryDashboardEnvironmentManagement(String companyCode);
}
