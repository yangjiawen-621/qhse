package com.wlhse.dao;

import com.wlhse.entity.DataDictPojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataDictDao {

    //根据父节点Code删除
    int deleteDataDict(@Param("parent") String parent);

    int updateCode(int id,String name);

    List<DataDictPojo> queryAllChildren(@Param("parentName") String parentName);

    int insertDataDict(@Param("parentCode")String parentCode, @Param("name")String name);

    int deleteDataDictById(int id);

    List<DataDictPojo> getAllDataDictPojo();

    int deleteDataDictByCode(@Param("code")String code);
}
