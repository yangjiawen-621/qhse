package com.wlhse.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface MonitorFileDao {

    int insertNewFile(String filePath,String originName);

    String getOriginName(String filePath);
}
