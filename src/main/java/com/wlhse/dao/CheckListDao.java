package com.wlhse.dao;

import com.wlhse.dto.CheckListDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CheckListDao {

    Integer addCheckList(CheckListDto checkListDto);

    String checkListIsExist(CheckListDto checkListDto);
    //只查启用
    List<CheckListDto> getTreeDto();
    //查启用和停用
    List<CheckListDto> getAllTreeDto();

    //查询节点最大长度
    int getMaxLen();

    //根据code查询
    CheckListDto getCheckListOne(@Param("code") String code);

    //根据父节点获取当前父节点下一层所有子节点
    List<String> getCurrentChildNodes(@Param("code") String code);

    //更新isChild字段
    int updateIsChild(@Param("code") String code,@Param("status") String status);

    //获取所有表节点
    List<String> getAllTableNode();

    //根据id查询
    CheckListDto getById(@Param("id") int id);

    //改变当前节点下的所有子节点的status
    int updateAllChildStatus(@Param("code") String code);

    //根据父节点获取当前父节点下所有子节点
    List<CheckListDto> getCurrentAllChild(@Param("code") String code);

    int updateCheckList(CheckListDto checkListDto);

    int updateCheckListByCode(CheckListDto checkListDto);

    //更改状态
    int updateStatus(@Param("status") String status,@Param("code")String code);

    //查询所有编码
    List<String> querryAllCheckListCode();
}
