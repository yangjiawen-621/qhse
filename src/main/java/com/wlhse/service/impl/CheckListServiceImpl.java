package com.wlhse.service.impl;

import com.wlhse.dao.CheckListDao;
import com.wlhse.dto.CheckListDto;
import com.wlhse.dto.inDto.CheckListAddDto;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.CheckListService;
import com.wlhse.util.R;
import com.wlhse.util.SortCodeUtil;
import com.wlhse.util.TreeUtil;
import com.wlhse.util.state_code.NR;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CheckListServiceImpl implements CheckListService {

    @Resource
    private CheckListDao checkListDao;

    @Resource
    private TreeUtil treeUtil;

    @Resource
    private SortCodeUtil sortCodeUtil;

    @Override
    public R getTreeDto(int tag) {
        if(tag==0) {//只查启用
            R ok = R.ok();
            ok.put("data", treeUtil.getCheckListTree(checkListDao.getTreeDto()));
            return ok;
        }
        else if(tag==1) {//查启用和停用
            R ok = R.ok();
            ok.put("data", treeUtil.getCheckListTree(checkListDao.getAllTreeDto()));
            return ok;
        }
        else {
            throw new WLHSException("查询失败");
        }
    }

    @Transactional
    @Override
    public R addCheckListNode(CheckListAddDto checkListAddDto) {
        //获取父节点
        String parentCode=checkListAddDto.getCheckListCode();
        CheckListDto saveDto=new CheckListDto();
        saveDto.setCheckListName(checkListAddDto.getCheckListName());
        saveDto.setStatus(checkListAddDto.getStatus());
        int i=1;
        //新增表
        if(parentCode==null||"".equals(parentCode)){//添加第一级code传null，Attribute传表
            System.out.println("新增表");
            //获取数据库所有一级节点
            List<String> list=checkListDao.getAllTableNode();
            String node;
            if(0==list.size()){
                node="0001";
            }else{
                //获取最大的表节点,加1
                String maxNode=sortCodeUtil.getMaxCode(list);
                node=sortCodeUtil.getMaxCodeString(maxNode);
            }
            System.out.println(node);
            saveDto.setCheckListCode(node);
            saveDto.setIsChildNode("true");
            saveDto.setAttribute(checkListAddDto.getAttribute());
        }else{
            //新增节点
            CheckListDto temp=null;
            //获取当前父节点下所有子节点code
            List<String> list=checkListDao.getCurrentChildNodes(parentCode);
            if(0==list.size()||null==list){//子节点列表为空
                saveDto.setCheckListCode(parentCode+"0001");//父节点下生成一个子节点
                //获取父节点信息，temp即父节点
                temp=checkListDao.getCheckListOne(parentCode);
                if(temp.getCheckListName().equals(checkListAddDto.getParentName())&&temp.getCheckListCode().length()==4){//第一级节点下的第一个节点
                    saveDto.setParentName(checkListAddDto.getParentName());
                }else{
                    saveDto.setParentName(temp.getParentName()+"/*a5f46saad*/"+checkListAddDto.getParentName());
                }
                saveDto.setAttribute(temp.getAttribute()+"/*a5f46saad*/"+checkListAddDto.getAttribute());
                saveDto.setIsChildNode("true");//新增节点一定是子节点
            }else{
                //System.out.println(list);
                //获取最大的子节点,加1
                String maxNode=sortCodeUtil.getMaxCode(list);
                String childNode=sortCodeUtil.getMaxCodeString(maxNode);
                //System.out.println(childNode);
                //获取最大子节点信息
                temp=checkListDao.getCheckListOne(maxNode);
                //System.out.println(temp);
                saveDto.setAttribute(temp.getAttribute());
                saveDto.setParentName(temp.getParentName());
                saveDto.setIsChildNode("true");//新增节点一定是子节点
                saveDto.setCheckListCode(childNode);
            }
            //判断当前父节点是不是最后一级，如果是，isChild变成false
            i=checkListDao.updateIsChild(checkListAddDto.getCheckListCode(),"false");
        }
        int j=checkListDao.addCheckList(saveDto);
        if ( i*j<= 0)
            throw new WLHSException("新增失败");
        return R.ok();
    }

    @Transactional
    @Override
    public R deleteCheckList(int id) {
        //根据id获取checkList
        CheckListDto checkListDto=checkListDao.getById(id);
        String code=checkListDto.getCheckListCode();
        if("启用".equals(checkListDto.getStatus()))//执行停用
        {
            //获取父节点
            String parentCode=code.substring(0,code.length()-4);
            //获取父节点下面同一级的所有子节点
            List<String>  listDtos= checkListDao.getCurrentChildNodes(parentCode);
            //System.out.println(parentCode);
           // System.out.println(listDtos);
            int i=1;
            if (listDtos.size()-1==0){
                //更新父节点isChild字段
                i=checkListDao.updateIsChild(parentCode,"true");
            }
            //更新当前节点下所有子节点的status
            int j=checkListDao.updateAllChildStatus(code);
            if ( i*j<= 0)
                throw new WLHSException("停用失败");
        }else if ("停用".equals(checkListDto.getStatus()))//设为启用
        {
            List<String> list = getParentByCode(code);//获得所有的父节点；
            int j=1;
            for (int i = 0; i < list.size(); i++) {
                j*=checkListDao.updateStatus("启用", list.get(i));//所有父节点设为启用；
                j*=checkListDao.updateIsChild(list.get(i),"false");//所有父节点设为不是叶子节点；
            }
            j*=checkListDao.updateStatus("启用",code );//当前节点设为启用
            j*=checkListDao.updateIsChild(code,"true");//当前节点设为叶子节点
            if(j<=0)
                throw new WLHSException("启用失败");
        }
        return R.ok();
    }
    public List<String> getParentByCode(String code) //获得所有父节点，不包括本人
    {
        List<String> list = new ArrayList<String>();
        int len = code.length();
        while (len > 0) {
            String str = code.substring(0, len);
            list.add(str);
            len -= 4;
        }
        list.remove(0);
        return list;
    }

    @Transactional
    @Override
    public R updateCheckList(CheckListAddDto checkListAddDto) {
        int i=1;
        //原本的值
        CheckListDto checkListOldDto=checkListDao.getCheckListOne(checkListAddDto.getCheckListCode());
        //改checkListName，改当前节点及下面的所有节点的parentName
        List<CheckListDto> listDtos=checkListDao.getCurrentAllChild(checkListAddDto.getCheckListCode());
        for (CheckListDto checkListDto:listDtos){
            if(checkListDto.getCheckListID().equals(checkListOldDto.getCheckListID())){//如果是当前节点还要改其他值
                checkListDto.setCheckListName(checkListAddDto.getCheckListName());//更改名字
                //checkListDto.setStatus(checkListAddDto.getStatus());
            }else{
                StringBuffer newParentName=new StringBuffer();
                String[] oldParentName=checkListDto.getParentName().split("/\\*a5f46saad\\*/");
                for(int j=0;j<oldParentName.length;j++){
                    if (checkListOldDto.getCheckListName().equals(oldParentName[j])){//当找到旧父节点名时，替换掉。
                        newParentName.append(checkListAddDto.getCheckListName());
                    }else{
                        newParentName.append(oldParentName[j]);
                    }
                    if(j!=oldParentName.length-1){
                        newParentName.append("/*a5f46saad*/");
                    }
                }
                checkListDto.setParentName(newParentName.toString());
            }
            i *= checkListDao.updateCheckList(checkListDto);
        }
        if ( i<= 0)
            throw new WLHSException("修改失败");
        return R.ok();
    }
}
