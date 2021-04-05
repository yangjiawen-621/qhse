package com.wlhse.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface DrFileDao {

    int insertNewFile(String filePath,String originName);

    String getOriginName(String filePath);
}
