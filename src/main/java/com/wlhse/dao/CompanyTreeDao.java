package com.wlhse.dao;


import com.wlhse.entity.CompanyPojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CompanyTreeDao {
    //增加单位节点，传入父节点的code和新节点的name
    int addCompanyTreeNode(CompanyPojo companyPojo);
    //获取父节点的最后个节点
    String queryLastChildCode(String companyCode);
    //删除单位节点只改变状态
    int deleteCompanyTreeNode(@Param("companyCode") String companyCode);
    //删除单位节点，传入待删除节点的code，当待删除节点无子节点
    // 且其或其子节点在employee、plan、routetaskrecord、eventrecord、accidentrecord、problem表中无记录时，
    //才能删除成功
//    int deleteCompanyTreeNode(@Param("companyCode") String companyCode);
    //传入code，更改单位树节点名字，不能更改code
    int updateCompanyTreeNode(CompanyPojo companyPojo);
    //当删除失败时，调用该函数，返回company相关的表记录条数
    int getCompanyTreeDelOutDto(@Param("companyCode") String companyCode, @Param("tableName") String tableName);

    String queryChildCode(@Param("companyCode") String companyCode);

    String queryContractingCompany();
}
