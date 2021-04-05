package com.wlhse.util;

import com.wlhse.dao.CheckListDao;
import com.wlhse.dao.ModuleDao;
import com.wlhse.dao.QHSEManageSysElementsDao;
import com.wlhse.dao.QualityManagerSysElementDao;
import com.wlhse.dto.*;
import com.wlhse.dto.inDto.QualityYearElementsDto;
import com.wlhse.dto.inDto.YearElementsDto;
import com.wlhse.dto.outDto.*;
import com.wlhse.entity.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;


@Component
//适配器
public class TreeUtil {

    @Resource
    private ModuleDao moduleDao;

    @Resource
    QHSEManageSysElementsDao qhseManageSysElementsDao;

    @Resource
    QualityManagerSysElementDao qualityManagerSysElementDao;

    @Resource
    private CheckListDao checkListDao;

    public List<TreeDto> GetModuleTree(List<ModulePojo> pojos) {
        Map<String, TreeDto> map1 = new TreeMap<>();
        List<Integer> code = new ArrayList<>();
        for (ModulePojo pojo : pojos) {
            TreeDto treeDto = new TreeDto();
            treeDto.setLabel(pojo.getName());
            treeDto.setNodeCode(pojo.getModuleCode());
            map1.put(treeDto.getNodeCode(), treeDto);

            if (code.indexOf(pojo.getModuleCode().length()) == -1)
                code.add(pojo.getModuleCode().length());
        }
        return returnList(map1, code);
    }
    // clone() 修改
    public List<TreeDto> GetRoleModuleTree(List<RoleModulePojo> pojos) {
        Map<String, TreeDto> map1 = new TreeMap<>();
        List<Integer> code = new ArrayList<>();
        for (RoleModulePojo pojo : pojos) {
            TreeDto treeDto = new TreeDto();
            ModulePojo modulePojo = moduleDao.queryByModuleCode(pojo.getModuleCode());
            treeDto.setLabel(modulePojo.getName());
            treeDto.setNodeCode(pojo.getModuleCode());
            map1.put(treeDto.getNodeCode(), treeDto);

            if (code.indexOf(pojo.getModuleCode().length()) == -1)
                code.add(pojo.getModuleCode().length());
        }
        return returnList(map1, code);
    }

    public List<TreeDto> getDataDictTree(List<DataDictPojo> dataDictPojos) {
        Map<String, TreeDto> map1 = new TreeMap<>();
        List<Integer> code = new ArrayList<>();
        for (DataDictPojo pojo : dataDictPojos) {
            TreeDto treeDto = new TreeDto();
            treeDto.setLabel(pojo.getName());
            treeDto.setNodeCode(pojo.getDictCode());
            map1.put(treeDto.getNodeCode(), treeDto);

            if (code.indexOf(pojo.getDictCode().length()) == -1)
                code.add(pojo.getDictCode().length());
        }
        return returnList(map1, code);
    }


    private static final String staus = "停用";


    public List<TreeDto> getCompanyTree(List<CompanyPojo> companyPojo) {
        Map<String, TreeDto> map1 = new TreeMap<>();
        List<Integer> code = new ArrayList<>();
        for (CompanyPojo pojo : companyPojo) {
            TreeDto treeDto = new TreeDto();
            treeDto.setId(pojo.getCompanyCode());
            treeDto.setLabel(pojo.getName());
            treeDto.setNodeCode(pojo.getCompanyCode());
            map1.put(treeDto.getNodeCode(), treeDto);

            if (code.indexOf(pojo.getCompanyCode().length()) == -1)
                code.add(pojo.getCompanyCode().length());
        }
        System.out.println(map1);
        return returnList(map1, code);
    }

    public List<TreeDto> getQhseCompanyTree(List<CompanyPojo> companyPojo) {
        Map<String, TreeDto> map1 = new TreeMap<>();
        List<Integer> code = new ArrayList<>();
        for (CompanyPojo pojo : companyPojo) {
            TreeDto treeDto = new TreeDto();
            treeDto.setId(pojo.getSysCompanyID().toString());
            treeDto.setLabel(pojo.getName());
            treeDto.setNodeCode(pojo.getCompanyCode());
            map1.put(treeDto.getNodeCode(), treeDto);

            if (code.indexOf(pojo.getCompanyCode().length()) == -1)
                code.add(pojo.getCompanyCode().length());
        }
        System.out.println(map1);
        return returnList(map1, code);
    }

    public List<QHSECompanySysElementsPojo> getQHSEReportTree(List<QHSECompanySysElementsPojo> list) {
        Map<String,QHSECompanySysElementsPojo> map=new TreeMap<>();
        List<String> codes=new LinkedList<>();
        for(QHSECompanySysElementsPojo node : list){
            map.put(node.getCode(),node);
            codes.add(node.getCode());
        }
        return returnQHSEList(map,codes);
    }

    public List<TreeDto> getRoleModuleOutDto(List<RoleModuleOutDto> roleModuleOutDtos) {
        Map<String, TreeDto> map1 = new TreeMap<>();
        List<Integer> code = new ArrayList<>();
        for (RoleModuleOutDto pojo : roleModuleOutDtos) {
            TreeDto treeDto = new TreeDto();
            treeDto.setLabel(pojo.getLabel());
            treeDto.setNodeCode(pojo.getModuleCode());
            treeDto.setuRl(pojo.getuRL());
            treeDto.setImg(pojo.getImg());
            map1.put(treeDto.getNodeCode(), treeDto);

            if (code.indexOf(pojo.getModuleCode().length()) == -1)
                code.add(pojo.getModuleCode().length());
        }
        return returnList(map1, code);
}

    public List<TreeDto> returnList(Map<String, TreeDto> map, List<Integer> code) {
        List<TreeDto> result = new ArrayList<>();
        Collections.sort(code);
        for (Map.Entry<String, TreeDto> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key.length() == code.get(0))//第一层
                result.add(entry.getValue());
            else {
                TreeDto treeDto = map.get(key.substring(0, code.get(code.indexOf(key.length()) - 1)));//获取父节点
                if (null == treeDto)
                    continue;
                if (null == treeDto.getChildren()) {
                    List<TreeDto> tmp = new ArrayList<>();
                    tmp.add(entry.getValue());
                    treeDto.setChildren(tmp);
                } else
                    treeDto.getChildren().add(entry.getValue());
            }
        }
        return result;
    }

    public List<MenuOutDto> returnMenuOutDtoList(Map<String, MenuOutDto> map1, int parentLength) {
        List<MenuOutDto> list = new ArrayList<>();
        Map<String, MenuOutDto> treeMap = new TreeMap<String, MenuOutDto>(map1);
        for (Map.Entry<String, MenuOutDto> entry : treeMap.entrySet()) {
            if (entry.getKey().length() == parentLength) {
                list.add(entry.getValue());
            }
        }
        for (Map.Entry<String, MenuOutDto> entry : treeMap.entrySet()) {
            String code = entry.getKey();
            if (code.length() > parentLength) {
                code = code.substring(0, parentLength);
                MenuOutDto parentTree = map1.get(code);
                if (parentTree != null) {
                    if (parentTree.getChildren() != null) {
                        parentTree.getChildren().add(entry.getValue());
                    } else {
                        List<MenuOutDto> list1 = new ArrayList<>();
                        list1.add(entry.getValue());
                        parentTree.setChildren(list1);
                    }

                }
            }
        }
        return list;
    }

    public List<QHSECompanySysElementsPojo> returnQHSEList(Map<String, QHSECompanySysElementsPojo> map, List<String> codes){
        List<QHSECompanySysElementsPojo> result = new ArrayList<>();
        for(String code: codes){
            QHSECompanySysElementsPojo node=map.get(code);
            if(code.length()==2){//第一层
                result.add(node);
            }else{//后面几层
                String parentCode=code.substring(0,code.length()-2);
                map.get(parentCode).getChildNode().add(node);
            }
        }
        return result;
    }

    //th---checkListTree
    public List<CheckListTreeDto> returnCheckList(Map<String, CheckListTreeDto> map, List<Integer> code) {
        List<CheckListTreeDto> result = new ArrayList<>();
        Collections.sort(code);
        for (Map.Entry<String, CheckListTreeDto> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key.length() == code.get(0))
                result.add(entry.getValue());
            else {
                CheckListTreeDto treeDto = map.get(key.substring(0, code.get(code.indexOf(key.length()) - 1)));
                if (null == treeDto)
                    continue;
                if (null == treeDto.getChildren()) {
                    List<CheckListTreeDto> tmp = new ArrayList<>();
                    tmp.add(entry.getValue());
                    treeDto.setChildren(tmp);
                } else {
                    //找到节点中长度最长的那一个
                    treeDto.getChildren().add(entry.getValue());
                }
            }
        }
        return result;
    }

    public List<CheckListTreeDto> getCheckListTree(List<CheckListDto> checkListDtos) {
        Map<String, CheckListTreeDto> map1 = new TreeMap<>();
        List<Integer> code = new ArrayList<>();
        for (CheckListDto pojo : checkListDtos) {
            CheckListTreeDto checkListTreeDto = new CheckListTreeDto();
            checkListTreeDto.setCheckListID(pojo.getCheckListID());
            checkListTreeDto.setCheckListCode(pojo.getCheckListCode());
            checkListTreeDto.setCheckListName(pojo.getCheckListName());
            checkListTreeDto.setAttribute(pojo.getAttribute());
            checkListTreeDto.setParentName(pojo.getParentName());
            checkListTreeDto.setIsChildNode(pojo.getIsChildNode());
            checkListTreeDto.setStatus(pojo.getStatus());
            map1.put(checkListTreeDto.getCheckListCode(), checkListTreeDto);

            //同一层节点长度一样
            if (code.indexOf(pojo.getCheckListCode().length()) == -1)
                code.add(pojo.getCheckListCode().length());
        }
        return returnCheckList(map1, code);
    }

    //new menu module

    public List<MenuOutDto> returnMenuList(Map<String, MenuOutDto> map, List<Integer> code) {
        List<MenuOutDto> result = new ArrayList<>();
        Collections.sort(code);
        for (Map.Entry<String, MenuOutDto> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key.length() == code.get(0))
                result.add(entry.getValue());
            else {
                MenuOutDto treeDto = map.get(key.substring(0, code.get(code.indexOf(key.length()) - 1)));
                if (null == treeDto)
                    continue;
                if (null == treeDto.getChildren()) {
                    List<MenuOutDto> tmp = new ArrayList<>();
                    tmp.add(entry.getValue());
                    treeDto.setChildren(tmp);
                } else
                    treeDto.getChildren().add(entry.getValue());
            }
        }
        return result;
    }

    public List<MenuOutDto> getMenuTree(List<ModulePojo> list) {
        Map<String, MenuOutDto> map1 = new TreeMap<>();
        List<Integer> code = new ArrayList<>();
        for (ModulePojo pojo : list) {
            MenuOutDto menuOutDto = new MenuOutDto();
            menuOutDto.setRouteName(pojo.getUrl());
            menuOutDto.setIcon(pojo.getImg());
            menuOutDto.setTitle(pojo.getName());
            menuOutDto.setCode(pojo.getModuleCode());
            map1.put(pojo.getModuleCode(), menuOutDto);

            //同一层节点长度一样
            if (code.indexOf(pojo.getModuleCode().length()) == -1)
                code.add(pojo.getModuleCode().length());
        }
        return returnMenuList(map1, code);
    }

    /**
     * 该方法为生成树的核心算法
     * @param map Map<String, QhseElementsOutDto>型，放的是code,和code对应的节点
     * @param code List<Integer>型，放的code的长度；
     * @return 一棵树
     */
    public List<QhseElementsOutDto> returnQhseElementList(Map<String, QhseElementsOutDto> map, List<Integer> code) {
        /*
        思想：创建一个list,然后遍历map里的节点,如果是一级节点，就放进list；
        如果不是一级节点，就去找他的父节点，找到后就把当前节点放进父节里的孩子list中，最后逐渐形成一棵树；
         */
        List<QhseElementsOutDto> result = new ArrayList<>();
        Collections.sort(code);
        for (Map.Entry<String, QhseElementsOutDto> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key.length() == code.get(0))//是一级节点，就放进list；
                result.add(entry.getValue());
            else {//如果不是一级节点，就去找他的父节点
                QhseElementsOutDto treeDto = map.get(key.substring(0, code.get(code.indexOf(key.length()) - 1)));
                if (null == treeDto)//父节孩子为空
                    continue;
                if (null == treeDto.getChildNode()) {//父节孩子为空，建一个list放入
                    List<QhseElementsOutDto> tmp = new ArrayList<>();
                    tmp.add(entry.getValue());
                    treeDto.setChildNode(tmp);
                } else////父节孩子不为空，直接放入
                    treeDto.getChildNode().add(entry.getValue());
            }
        }
        return result;
    }

    /**
     * 该方法用于把查询出来的list里的对象，封装为一棵树；
     * @param qhseElementsPojos 该参数为List<QhseElementsPojo>集合，为数据库查询出的结果
     * @return 一棵树
     */
    public List<QhseElementsOutDto> getQhseElementTree(List<QhseElementsPojo> qhseElementsPojos) {
        /*
        思想，把list里的对象逐个遍历；QhseElementsPojo赋值给QhseElementsOutDto，
        并把节点<code,QhseElementsOutDto>,放进map集合，方便后续操作；同时设一个code list记录节点长度；
         */
        Map<String, QhseElementsOutDto> map1 = new TreeMap<>();
        List<Integer> code = new ArrayList<>();
        for (QhseElementsPojo pojo : qhseElementsPojos) {
            QhseElementsOutDto qhseElementsOutDto = new QhseElementsOutDto();
            qhseElementsOutDto.setAuditMode(pojo.getAuditMode());
            qhseElementsOutDto.setCode(pojo.getCode());
            qhseElementsOutDto.setContent(pojo.getContent());
            qhseElementsOutDto.setTotalCount(pojo.getTotalCount());
            qhseElementsOutDto.setFormula(pojo.getFormula());
            qhseElementsOutDto.setInitialScore(pojo.getInitialScore());
            qhseElementsOutDto.setName(pojo.getName());
            qhseElementsOutDto.setStatus(pojo.getStatus());
            qhseElementsOutDto.setId(pojo.getQhseManagerSysElementID());
            map1.put(qhseElementsOutDto.getCode(), qhseElementsOutDto);

            //同一层节点长度一样
            if (code.indexOf(pojo.getCode().length()) == -1)
                code.add(pojo.getCode().length());
        }
        return returnQhseElementList(map1, code);
    }

    /**
     * 该方法用于导出excel查询树
     * @param qhseElementsPojos 该参数为List<QhseElementsPojo>集合，为数据库查询出的结果
     * @return 一棵树
     */
    public List<QhseElementsOutDto> getQhseElementTreeForExcel(List<QhseElementsPojo> qhseElementsPojos) {
        /*
        思想：同上，增加了setProblemDescription字段的导出
         */
        Map<String, QhseElementsOutDto> map1 = new TreeMap<>();
        //获得问题描述map
        Map<String,String> discriptionMap=getProblemDescriptionMap();
        List<Integer> code = new ArrayList<>();
        for (QhseElementsPojo pojo : qhseElementsPojos) {
            QhseElementsOutDto qhseElementsOutDto = new QhseElementsOutDto();
            qhseElementsOutDto.setAuditMode(pojo.getAuditMode());
            qhseElementsOutDto.setCode(pojo.getCode());
            qhseElementsOutDto.setTotalCount(pojo.getTotalCount());
            qhseElementsOutDto.setFormula(pojo.getFormula());
            qhseElementsOutDto.setInitialScore(pojo.getInitialScore());
            qhseElementsOutDto.setName(pojo.getName());
            qhseElementsOutDto.setStatus(pojo.getStatus());
            qhseElementsOutDto.setId(pojo.getQhseManagerSysElementID());
            if(pojo.getCode().length()==18&&!discriptionMap.isEmpty())//加入问题描述字段
            {
                qhseElementsOutDto.setProblemDescription(discriptionMap.get(pojo.getCode()));
                //System.out.println(pojo.getCode()+"-----"+discriptionMap.get(pojo.getCode()));
            }
            map1.put(qhseElementsOutDto.getCode(), qhseElementsOutDto);

            //同一层节点长度一样
            if (code.indexOf(pojo.getCode().length()) == -1)
                code.add(pojo.getCode().length());
        }
        return returnQhseElementList(map1, code);
    }

    /**
     * 该方法用于获得拼接好的审核要素问题描述字段
     * @return 问题描述<code,Description>map
     */
    public Map<String,String> getProblemDescriptionMap()//获得问题描述<code,Description>map
    {
        /*
        思想：直接一次把所有问题描述查完，根据code排序,放进list;然后根据code拼接，把code,对应问题描述字符，放进map;
         */
        //获得list
        Map<String,String> disMap=new HashMap<>();
        List<QHSEproblemDiscriptionDto> discriptionList;
        //System.out.println(qhseManageSysElementsDao.querryAllDescription().isEmpty() );
        if(qhseManageSysElementsDao.querryAllDescription().isEmpty() ){
            return disMap;
        }
        else discriptionList=qhseManageSysElementsDao.querryAllDescription();
        //核心算法；k代表当前code对象,j代表下一个对象;i表示序号，temp代表拼接的字符串；类似字符串的Index算法；
        String temp=1+"."+discriptionList.get(0).getDescription();
        for(int j=1,k=0,i=2;j<discriptionList.size();j++) {
            //k的对象与自增j的对象code相等时，把j的字符和K拼在一起
            if(discriptionList.get(k).getCode().equals(discriptionList.get(j).getCode())) {
                temp+=i+"."+discriptionList.get(j).getDescription();
                i++;
                //list最后一个元素，须判断放入；
                if(j==(discriptionList.size()-1)) {
                    disMap.put(discriptionList.get(j).getCode(),temp);
                }
            }
            else {
                //k的对象与j的对象code不相等时，因为是有序，说明到了下一个字符，把上一个拼好的放入map;开始下一个字符拼接，k=j；
                disMap.put(discriptionList.get(k).getCode(),temp);
                k=j;
                temp=1+"."+discriptionList.get(k).getDescription();
                //序号重置；
                i=2;
            }
        }
        return disMap;
    }
    //th---年度要素
    public List<QhseYearElementsOutDto> returnQhseYearElementList(Map<String, QhseYearElementsOutDto> map, List<Integer> code) {
        List<QhseYearElementsOutDto> result = new ArrayList<>();
        Collections.sort(code);
        for (Map.Entry<String, QhseYearElementsOutDto> entry : map.entrySet()) {
            String key = entry.getKey();                        
            if (key.length() == code.get(0))
                result.add(entry.getValue());
            else {
                QhseYearElementsOutDto treeDto = map.get(key.substring(0, code.get(code.indexOf(key.length()) - 1)));
                if (null == treeDto)
                    continue;
                if (null == treeDto.getChildNode()) {
                    List<QhseYearElementsOutDto> tmp = new ArrayList<>();
                    tmp.add(entry.getValue());
                    treeDto.setChildNode(tmp);
                } else
                    treeDto.getChildNode().add(entry.getValue());
            }
        }
        return result;
    }

    public List<QhseYearElementsOutDto> getQhseYearElementTree(List<YearElementsDto> qhseElementsPojos) {
        Map<String, QhseYearElementsOutDto> map1 = new TreeMap<>();
        List<Integer> code = new ArrayList<>();
        for (YearElementsDto pojo : qhseElementsPojos) {
            QhseYearElementsOutDto qhseElementsOutDto = new QhseYearElementsOutDto();
            qhseElementsOutDto.setAuditMode(pojo.getAuditMode());
            qhseElementsOutDto.setCode(pojo.getCode());
            qhseElementsOutDto.setContent(pojo.getContent());
            qhseElementsOutDto.setTotalCount(pojo.getTotalCount());
            qhseElementsOutDto.setFormula(pojo.getFormula());
            qhseElementsOutDto.setInitialScore(pojo.getInitialScore());
            qhseElementsOutDto.setName(pojo.getName());
            qhseElementsOutDto.setStatus(pojo.getStatus());
            qhseElementsOutDto.setId(pojo.getQhseCompanyYearManagerSysElementID());
            qhseElementsOutDto.setTableID(pojo.getQhseCompanyYearManagerSysElementTableID());
            qhseElementsOutDto.setCompanyCode(pojo.getCompanyCode());
            qhseElementsOutDto.setCompanyName(pojo.getCompanyName());
            qhseElementsOutDto.setYear(pojo.getYear());
            qhseElementsOutDto.setFileCheckStatus(pojo.getFileCheckStatus());
            qhseElementsOutDto.setSchedule(pojo.getSchedule());
            qhseElementsOutDto.setCheckStatus(pojo.getCheckStatus());
            qhseElementsOutDto.setIsInvolve(pojo.getIsInvolve());
            map1.put(qhseElementsOutDto.getCode(), qhseElementsOutDto);

            //同一层节点长度一样
            if (code.indexOf(pojo.getCode().length()) == -1)
                code.add(pojo.getCode().length());
        }
        return returnQhseYearElementList(map1, code);
    }


    //th/lhl---单位年度管理体系要素表根据当前登陆人和年度单位查询
    public List<QhseElementsOutDto> returnCurrentQhseElementList(Map<String, QhseElementsOutDto> map, List<Integer> code) {
        List<QhseElementsOutDto> result = new ArrayList<>();
        Collections.sort(code);
        for (Map.Entry<String, QhseElementsOutDto> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key.length() == code.get(0))
                result.add(entry.getValue());
            else {
                QhseElementsOutDto treeDto = map.get(key.substring(0, code.get(code.indexOf(key.length()) - 1)));
                if (null == treeDto)
                    continue;
                if (null == treeDto.getChildNode()) {
                    List<QhseElementsOutDto> tmp = new ArrayList<>();
                    tmp.add(entry.getValue());
                    treeDto.setChildNode(tmp);
                } else
                    treeDto.getChildNode().add(entry.getValue());
            }
        }
        return result;
    }

    public List<QhseElementsOutDto> getCurrentQhseElementTree(List<QHSECompanyYearManagerSysElementDto> qhseElementsPojos) {
        Map<String, QhseElementsOutDto> map1 = new TreeMap<>();
        List<Integer> code = new ArrayList<>();
        for (QHSECompanyYearManagerSysElementDto pojo : qhseElementsPojos) {
            QhseElementsOutDto qhseElementsOutDto = new QhseElementsOutDto();
            qhseElementsOutDto.setAuditMode(pojo.getAuditMode());
            qhseElementsOutDto.setCode(pojo.getCode());
            qhseElementsOutDto.setContent(pojo.getContent());
            qhseElementsOutDto.setTotalCount(pojo.getTotalCount());
            qhseElementsOutDto.setFormula(pojo.getFormula());
            qhseElementsOutDto.setInitialScore(pojo.getInitialScore());
            qhseElementsOutDto.setProblemDescription(pojo.getProblemDescription());
            qhseElementsOutDto.setName(pojo.getName());
            qhseElementsOutDto.setStatus(pojo.getStatus());
            qhseElementsOutDto.setId(pojo.getqHSE_CompanyYearManagerSysElement_ID());
            //System.out.println(pojo.getQhseManagerSysElementID());
            map1.put(qhseElementsOutDto.getCode(), qhseElementsOutDto);

            //同一层节点长度一样
            if (code.indexOf(pojo.getCode().length()) == -1)
                code.add(pojo.getCode().length());
        }
        return returnCurrentQhseElementList(map1, code);
    }

    //年度要素表树
    public List<QHSECompanyYearManagerSysElementDto> returnCurrentQhseElementList1(Map<String, QHSECompanyYearManagerSysElementDto> map, List<Integer> code) {
        List<QHSECompanyYearManagerSysElementDto> result = new ArrayList<>();
        Collections.sort(code);
        for (Map.Entry<String, QHSECompanyYearManagerSysElementDto> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key.length() == code.get(0))
                result.add(entry.getValue());
            else {
                QHSECompanyYearManagerSysElementDto treeDto = map.get(key.substring(0, code.get(code.indexOf(key.length()) - 1)));
                if (null == treeDto)
                    continue;
                if (null == treeDto.getChildNode()) {
                    List<QHSECompanyYearManagerSysElementDto> tmp = new ArrayList<>();
                    tmp.add(entry.getValue());
                    treeDto.setChildNode(tmp);
                } else
                    treeDto.getChildNode().add(entry.getValue());
            }
        }
        return result;
    }

    public List<QHSECompanyYearManagerSysElementDto> getCurrentQhseElementTree1(List<QHSECompanyYearManagerSysElementDto> qhseElementsPojos) {
        Map<String, QHSECompanyYearManagerSysElementDto> map1 = new TreeMap<>();
        List<Integer> code = new ArrayList<>();
        for (QHSECompanyYearManagerSysElementDto pojo : qhseElementsPojos) {
            map1.put(pojo.getCode(), pojo);
            //存入长度相同的数字
            if (code.indexOf(pojo.getCode().length()) == -1)
                code.add(pojo.getCode().length());
        }
        return returnCurrentQhseElementList1(map1, code);
    }

    //checkrecord树状显示
    public List<CheckRecordTreeOutDto> returnCheckRecordTree(Map<String, CheckRecordTreeOutDto> map, List<Integer> code) {
        List<CheckRecordTreeOutDto> result = new ArrayList<>();
        Collections.sort(code);
        for (Map.Entry<String, CheckRecordTreeOutDto> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key.length() == code.get(0))
                result.add(entry.getValue());
            else {
                CheckRecordTreeOutDto treeDto = map.get(key.substring(0, code.get(code.indexOf(key.length()) - 1)));
                if (null == treeDto)
                    continue;
                if (null == treeDto.getChildNode()) {
                    List<CheckRecordTreeOutDto> tmp = new ArrayList<>();
                    tmp.add(entry.getValue());
                    treeDto.setChildNode(tmp);
                } else
                    treeDto.getChildNode().add(entry.getValue());
            }
        }
        return result;
    }

    public List<CheckRecordTreeOutDto> getCheckRecordTree(List<CheckRecordTreeDto> checkRecordTreeDtos) {
        Map<String, CheckRecordTreeOutDto> map1 = new TreeMap<>();
        List<Integer> code = new ArrayList<>();
        for (CheckRecordTreeDto pojo : checkRecordTreeDtos) {
            CheckRecordTreeOutDto checkRecordTreeOutDto = new CheckRecordTreeOutDto();
            checkRecordTreeOutDto.setCheckDate(pojo.getCheckDate());
            checkRecordTreeOutDto.setCheckListCode(pojo.getCheckListCode());
            checkRecordTreeOutDto.setCheckListName(pojo.getCheckListName());
            checkRecordTreeOutDto.setCheckRecordID(pojo.getCheckRecordID());
            checkRecordTreeOutDto.setCompanyCode(pojo.getCompanyCode());
            checkRecordTreeOutDto.setCompanyName(pojo.getCompanyName());
            checkRecordTreeOutDto.setCheckType(pojo.getCheckType());
            checkRecordTreeOutDto.setProblems(pojo.getProblems());
            checkRecordTreeOutDto.setPass(pojo.getPass());
            checkRecordTreeOutDto.setCheckPersonId(pojo.getCheckPersonId());
            checkRecordTreeOutDto.setCheckPerson(pojo.getCheckPerson());
            checkRecordTreeOutDto.setCheckTypeCode(pojo.getCheckTypeCode());
            checkRecordTreeOutDto.setReason(pojo.getReason());
            //System.out.println(pojo.getQhseManagerSysElementID());
            map1.put(checkRecordTreeOutDto.getCheckListCode(), checkRecordTreeOutDto);

            //同一层节点长度一样
            if (code.indexOf(pojo.getCheckListCode().length()) == -1)
                code.add(pojo.getCheckListCode().length());
        }
        return returnCheckRecordTree(map1, code);
    }

    //factor树状显示
    public List<FactorOutDto2> returnFactoryTree(Map<String, FactorOutDto2> map, List<Integer> code) {
        List<FactorOutDto2> result = new ArrayList<>();
        Collections.sort(code);
        for (Map.Entry<String, FactorOutDto2> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key.length() == code.get(0))
                result.add(entry.getValue());
            else {
                FactorOutDto2 treeDto = map.get(key.substring(0, code.get(code.indexOf(key.length()) - 1)));
                if (null == treeDto)
                    continue;
                if (null == treeDto.getChildNode()) {
                    List<FactorOutDto2> tmp = new ArrayList<>();
                    tmp.add(entry.getValue());
                    treeDto.setChildNode(tmp);
                } else
                    treeDto.getChildNode().add(entry.getValue());
            }
        }
        return result;
    }

    // 【质量】
    public List<QualityFactorOutDto> returnQualityFactoryTree(Map<String, QualityFactorOutDto> map, List<Integer> code) {
        List<QualityFactorOutDto> result = new ArrayList<>();
        Collections.sort(code);
        for (Map.Entry<String, QualityFactorOutDto> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key.length() == code.get(0))
                result.add(entry.getValue());
            else {
                QualityFactorOutDto treeDto = map.get(key.substring(0, code.get(code.indexOf(key.length()) - 1)));
                if (null == treeDto)
                    continue;
                if (null == treeDto.getChildNode()) {
                    List<QualityFactorOutDto> tmp = new ArrayList<>();
                    tmp.add(entry.getValue());
                    treeDto.setChildNode(tmp);
                } else
                    treeDto.getChildNode().add(entry.getValue());
            }
        }
        return result;
    }

    public List<FactorOutDto2> getFactoryTree(List<FactorOutDto> checkRecordTreeDtos) {
        Map<String, FactorOutDto2> map1 = new TreeMap<>();
        List<Integer> code = new ArrayList<>();
        for (FactorOutDto pojo : checkRecordTreeDtos) {
            FactorOutDto2 factorOutDto2 = new FactorOutDto2();
            factorOutDto2.setId(pojo.getId());
            factorOutDto2.setFactorCode(pojo.getFactorCode());
            factorOutDto2.setFactorID(pojo.getFactorID());
            factorOutDto2.setName(pojo.getName());
            factorOutDto2.setRight(pojo.getRigth());
            factorOutDto2.setFactorHseCode(pojo.getFactorHseCode());
            factorOutDto2.setFactorObserverCode(pojo.getFactorObserverCode());
            factorOutDto2.setFactorSourceCode(pojo.getFactorSourceCode());
            factorOutDto2.setFactorDepartmentCode(pojo.getFactorDepartmentCode());
            map1.put(factorOutDto2.getFactorCode(), factorOutDto2);

            //同一层节点长度一样
            if (code.indexOf(pojo.getFactorCode().length()) == -1)
                code.add(pojo.getFactorCode().length());
        }
        return returnFactoryTree(map1, code);
    }

    // 【质量】
    public List<QualityFactorOutDto> getQualityFactoryTree(List<QualityFactorOutDto> checkRecordTreeDtos) {
        Map<String, QualityFactorOutDto> map1 = new TreeMap<>();
        List<Integer> code = new ArrayList<>();
        for (QualityFactorOutDto pojo : checkRecordTreeDtos) {
            QualityFactorOutDto factorOutDto2 = new QualityFactorOutDto();
            factorOutDto2.setId(pojo.getId());
            factorOutDto2.setFactorCode(pojo.getFactorCode());
            factorOutDto2.setFactorID(pojo.getFactorID());
            factorOutDto2.setName(pojo.getName());
            factorOutDto2.setRight(pojo.getRight());
            factorOutDto2.setFactorHseCode(pojo.getFactorHseCode());
            factorOutDto2.setFactorObserverCode(pojo.getFactorObserverCode());
            factorOutDto2.setFactorSourceCode(pojo.getFactorSourceCode());
            factorOutDto2.setFactorDepartmentCode(pojo.getFactorDepartmentCode());
            map1.put(factorOutDto2.getFactorCode(), factorOutDto2);

            //同一层节点长度一样
            if (code.indexOf(pojo.getFactorCode().length()) == -1)
                code.add(pojo.getFactorCode().length());
        }
        return returnQualityFactoryTree(map1, code);
    }

    //th---QualityCheckListTree
    public List<QualityCheckListTreeDto> returnQualityCheckList(Map<String, QualityCheckListTreeDto> map, List<Integer> code) {
        List<QualityCheckListTreeDto> result = new ArrayList<>();
        Collections.sort(code);
        for (Map.Entry<String, QualityCheckListTreeDto> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key.length() == code.get(0))
                result.add(entry.getValue());
            else {
                QualityCheckListTreeDto treeDto = map.get(key.substring(0, code.get(code.indexOf(key.length()) - 1)));
                if (null == treeDto)
                    continue;
                if (null == treeDto.getChildren()) {
                    List<QualityCheckListTreeDto> tmp = new ArrayList<>();
                    tmp.add(entry.getValue());
                    treeDto.setChildren(tmp);
                } else {
                    //找到节点中长度最长的那一个
                    treeDto.getChildren().add(entry.getValue());
                }
            }
        }
        return result;
    }

    public List<QualityCheckListTreeDto> getQualityCheckListTree(List<QualityCheckListDto> QualityCheckListDtos) {

        Map<String, QualityCheckListTreeDto> map1 = new TreeMap<>();
        List<Integer> code = new ArrayList<>();
        for (QualityCheckListDto pojo : QualityCheckListDtos) {
            QualityCheckListTreeDto qualityCheckListTreeDto = new QualityCheckListTreeDto();
            qualityCheckListTreeDto.setCheckListID(pojo.getCheckListID());
            qualityCheckListTreeDto.setCheckListCode(pojo.getCheckListCode());
            qualityCheckListTreeDto.setCheckListName(pojo.getCheckListName());
            qualityCheckListTreeDto.setAttribute(pojo.getAttribute());
            qualityCheckListTreeDto.setParentName(pojo.getParentName());
            qualityCheckListTreeDto.setIsChildNode(pojo.getIsChildNode());
            qualityCheckListTreeDto.setStatus(pojo.getStatus());
            qualityCheckListTreeDto.setCheckCategory(pojo.getCheckCategory());
            qualityCheckListTreeDto.setCheckBasis(pojo.getCheckBasis());
            qualityCheckListTreeDto.setCheckMethod(pojo.getCheckMethod());

            qualityCheckListTreeDto.setTargetScore(pojo.getTargetScore());
            qualityCheckListTreeDto.setScoreShows(pojo.getScoreShows());
            qualityCheckListTreeDto.setStandard(pojo.getStandard());
            qualityCheckListTreeDto.setStandardNo(pojo.getStandardNo());
            qualityCheckListTreeDto.setStandardContent(pojo.getStandardContent());

            map1.put(qualityCheckListTreeDto.getCheckListCode(), qualityCheckListTreeDto);

            //同一层节点长度一样
            if (code.indexOf(pojo.getCheckListCode().length()) == -1)
                code.add(pojo.getCheckListCode().length());
        }
        return returnQualityCheckList(map1, code);
    }


    public List<QualityCheckTableRecordTreeDto> returnQualityCheckTree(Map<String, QualityCheckTableRecordTreeDto> map, List<Integer> code) {
        List<QualityCheckTableRecordTreeDto> result = new ArrayList<>();
        Collections.sort(code);
        for (Map.Entry<String, QualityCheckTableRecordTreeDto> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key.length() == code.get(0))
                result.add(entry.getValue());
            else {
                QualityCheckTableRecordTreeDto treeDto = map.get(key.substring(0, code.get(code.indexOf(key.length()) - 1)));
                if (null == treeDto)
                    continue;
                if (null == treeDto.getChildren()) {
                    List<QualityCheckTableRecordTreeDto> tmp = new ArrayList<>();
                    tmp.add(entry.getValue());
                    treeDto.setChildren(tmp);
                } else {
                    treeDto.getChildren().add(entry.getValue());
                }
            }
        }
        return result;
    }

    public List<QualityCheckTableRecordTreeDto> getQualityCheckRecordTree(List<QualityCheckTableRecordDto> qualityCheckTableRecordDtos){
        Map<String, QualityCheckTableRecordTreeDto> map1 = new TreeMap<>();
        List<Integer> code = new ArrayList<>();
        for (QualityCheckTableRecordDto pojo : qualityCheckTableRecordDtos) {
            QualityCheckTableRecordTreeDto qualityCheckTableRecord = new QualityCheckTableRecordTreeDto();
            qualityCheckTableRecord.setQualityCheckTableRecordID(pojo.getQualityCheckTableRecordID());
            qualityCheckTableRecord.setQualityCheckID(pojo.getQualityCheckID());
            qualityCheckTableRecord.setCheckListCode(pojo.getCheckListCode());
            qualityCheckTableRecord.setQualityCheckName(pojo.getQualityCheckName());
            qualityCheckTableRecord.setCheckResult(pojo.getCheckResult());
            qualityCheckTableRecord.setAttach(pojo.getAttach());
            qualityCheckTableRecord.setDescription(pojo.getDescription());
            qualityCheckTableRecord.setPic(pojo.getPic());
            map1.put(qualityCheckTableRecord.getCheckListCode(), qualityCheckTableRecord);
            //同一层节点长度一样
            if (code.indexOf(pojo.getCheckListCode().length()) == -1)
                code.add(pojo.getCheckListCode().length());
        }
        return returnQualityCheckTree(map1, code);
    }

    public List<QualityManagerSysElementOutDto> returnQualityElementList(Map<String, QualityManagerSysElementOutDto> map, List<Integer> code) {
        /*
        思想：创建一个list,然后遍历map里的节点,如果是一级节点，就放进list；
        如果不是一级节点，就去找他的父节点，找到后就把当前节点放进父节里的孩子list中，最后逐渐形成一棵树；
         */
        List<QualityManagerSysElementOutDto> result = new ArrayList<>();
        Collections.sort(code);
        for (Map.Entry<String, QualityManagerSysElementOutDto> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key.length() == code.get(0))//是一级节点，就放进list；
                result.add(entry.getValue());
            else {//如果不是一级节点，就去找他的父节点
                QualityManagerSysElementOutDto treeDto = map.get(key.substring(0, code.get(code.indexOf(key.length()) - 1)));
                if (null == treeDto)//父节孩子为空
                    continue;
                if (null == treeDto.getChildNode()) {//父节孩子为空，建一个list放入
                    List<QualityManagerSysElementOutDto> tmp = new ArrayList<>();
                    tmp.add(entry.getValue());
                    treeDto.setChildNode(tmp);
                } else////父节孩子不为空，直接放入
                    treeDto.getChildNode().add(entry.getValue());
            }
        }
        return result;
    }

    /**
     * 该方法用于把查询出来的list里的对象，封装为一棵树；
     * @param qhseElementsPojos 该参数为List<QhseElementsPojo>集合，为数据库查询出的结果
     * @return 一棵树
     */
    public List<QualityManagerSysElementOutDto> getQualityElementTree(List<QualityElementsPojo> qhseElementsPojos) {
        /*
        思想，把list里的对象逐个遍历；QhseElementsPojo赋值给QhseElementsOutDto，
        并把节点<code,QhseElementsOutDto>,放进map集合，方便后续操作；同时设一个code list记录节点长度；
         */
        Map<String, QualityManagerSysElementOutDto> map1 = new TreeMap<>();
        List<Integer> code = new ArrayList<>();
        for (QualityElementsPojo pojo : qhseElementsPojos) {
            QualityManagerSysElementOutDto qhseElementsOutDto = new QualityManagerSysElementOutDto();
            qhseElementsOutDto.setAuditMode(pojo.getAuditMode());
            qhseElementsOutDto.setCode(pojo.getCode());
            qhseElementsOutDto.setContent(pojo.getContent());
            qhseElementsOutDto.setTotalCount(pojo.getTotalCount());
            qhseElementsOutDto.setFormula(pojo.getFormula());
            qhseElementsOutDto.setInitialScore(pojo.getInitialScore());
            qhseElementsOutDto.setName(pojo.getName());
            qhseElementsOutDto.setStatus(pojo.getStatus());
            qhseElementsOutDto.setId(pojo.getQhseManagerSysElementID());
            qhseElementsOutDto.setScoreShows(pojo.getScoreShows());
            map1.put(qhseElementsOutDto.getCode(), qhseElementsOutDto);

            //同一层节点长度一样
            if (code.indexOf(pojo.getCode().length()) == -1)
                code.add(pojo.getCode().length());
        }
        return returnQualityElementList(map1, code);
    }



    /**
     * 该方法用于导出excel查询树
     * @param qhseElementsPojos 该参数为List<QhseElementsPojo>集合，为数据库查询出的结果
     * @return 一棵树
     */
    public List<QualityManagerSysElementOutDto> getQualityElementTreeForExcel(List<QualityElementsPojo> qhseElementsPojos) {
           /*
        思想：同上，增加了setProblemDescription字段的导出
         */
        Map<String, QualityManagerSysElementOutDto> map1 = new TreeMap<>();
        //获得问题描述map
        Map<String,String> discriptionMap=getQualityDescriptionMap();
        Map<String,String> ReviewMap=getReviewTermsMap();
        List<Integer> code = new ArrayList<>();
        for (QualityElementsPojo pojo : qhseElementsPojos) {
            QualityManagerSysElementOutDto qhseElementsOutDto = new QualityManagerSysElementOutDto();
            qhseElementsOutDto.setAuditMode(pojo.getAuditMode());
            qhseElementsOutDto.setCode(pojo.getCode());
            qhseElementsOutDto.setTotalCount(pojo.getTotalCount());
            qhseElementsOutDto.setFormula(pojo.getFormula());
            qhseElementsOutDto.setInitialScore(pojo.getInitialScore());
            qhseElementsOutDto.setName(pojo.getName());
            qhseElementsOutDto.setStatus(pojo.getStatus());
            qhseElementsOutDto.setId(pojo.getQhseManagerSysElementID());
            qhseElementsOutDto.setScoreShows(pojo.getScoreShows());
            if(pojo.getCode().length()==12)//加入问题描述字段
            {
                qhseElementsOutDto.setProblemDescription(discriptionMap.get(pojo.getCode()));
                //System.out.println(pojo.getCode()+"-----"+discriptionMap.get(pojo.getCode()));
                qhseElementsOutDto.setReviewTerms(ReviewMap.get(pojo.getCode()));
            }
            map1.put(qhseElementsOutDto.getCode(), qhseElementsOutDto);

            //同一层节点长度一样
            if (code.indexOf(pojo.getCode().length()) == -1)
                code.add(pojo.getCode().length());
        }
        return returnQualityElementList(map1, code);
    }

    public Map<String,String> getQualityDescriptionMap()//获得问题描述<code,Description>map
    {
        /*
        思想：直接一次把所有问题描述查完，根据code排序,放进list;然后根据code拼接，把code,对应问题描述字符，放进map;
         */
        //获得list
        List<QualityManagerSysElementProDesDto> discriptionList=qualityManagerSysElementDao.queryAllDescription();
        Map<String,String> disMap=new HashMap<>();
        //核心算法；k代表当前code对象,j代表下一个对象;i表示序号，temp代表拼接的字符串；类似字符串的Index算法；
        String temp=1+"."+discriptionList.get(0).getDescription();
        for(int j=1,k=0,i=2;j<discriptionList.size();j++) {
            //k的对象与自增j的对象code相等时，把j的字符和K拼在一起
            if(discriptionList.get(k).getCode().equals(discriptionList.get(j).getCode())) {
                temp+=i+"."+discriptionList.get(j).getDescription();
                i++;
                //list最后一个元素，须判断放入；
                if(j==(discriptionList.size()-1)) {
                    disMap.put(discriptionList.get(j).getCode(),temp);
                }
            }
            else {
                //k的对象与j的对象code不相等时，因为是有序，说明到了下一个字符，把上一个拼好的放入map;开始下一个字符拼接，k=j；
                disMap.put(discriptionList.get(k).getCode(),temp);
                k=j;
                temp=1+"."+discriptionList.get(k).getDescription();
                //序号重置；
                i=2;
            }
        }
        return disMap;
    }


//质量要素录入树
public List<QualityManergerSysElementPojo> returnCurrentQualityElementList(Map<String, QualityManergerSysElementPojo> map, List<Integer> code) {
    List<QualityManergerSysElementPojo> result = new ArrayList<>();
    Collections.sort(code);
    for (Map.Entry<String, QualityManergerSysElementPojo> entry : map.entrySet()) {
        String key = entry.getKey();
        if (key.length() == code.get(0))
            result.add(entry.getValue());
        else {
            QualityManergerSysElementPojo treeDto = map.get(key.substring(0, code.get(code.indexOf(key.length()) - 1)));
            if (null == treeDto)
                continue;
            if (null == treeDto.getChildNode()) {
                List<QualityManergerSysElementPojo> tmp = new ArrayList<>();
                tmp.add(entry.getValue());
                treeDto.setChildNode(tmp);
            } else
                treeDto.getChildNode().add(entry.getValue());
        }
    }
    return result;
}

    public  List<QualityManergerSysElementPojo> getCurrentQualityElementTree(List<QualityManergerSysElementPojo> qhseElementsPojos) {
        Map<String, QualityManergerSysElementPojo> map1 = new TreeMap<>();
        List<Integer> code = new ArrayList<>();
        for (QualityManergerSysElementPojo pojo : qhseElementsPojos) {
            map1.put(pojo.getCode(), pojo);
            //存入长度相同的数字
            if (code.indexOf(pojo.getCode().length()) == -1)
                code.add(pojo.getCode().length());
        }
        return returnCurrentQualityElementList(map1, code);
    }


    public List<QualityYearElementsOutDto> getQualityYearElementTree(List<QualityYearElementsDto> qhseElementsPojos) {
        Map<String, QualityYearElementsOutDto> map1 = new TreeMap<>();
        List<Integer> code = new ArrayList<>();
        for (QualityYearElementsDto pojo : qhseElementsPojos) {
            QualityYearElementsOutDto qualityElementsOutDto = new QualityYearElementsOutDto();
            qualityElementsOutDto.setAuditMode(pojo.getAuditMode());
            qualityElementsOutDto.setCode(pojo.getCode());
            qualityElementsOutDto.setContent(pojo.getContent());
            qualityElementsOutDto.setTotalCount(pojo.getTotalCount());
            qualityElementsOutDto.setFormula(pojo.getFormula());
            qualityElementsOutDto.setInitialScore(pojo.getInitialScore());
            qualityElementsOutDto.setName(pojo.getName());
            qualityElementsOutDto.setStatus(pojo.getStatus());
            qualityElementsOutDto.setId(pojo.getQualityCompanyYearManagerSysElementID());
            qualityElementsOutDto.setTableID(pojo.getQualityCompanyYearManagerSysElementTableID());
            qualityElementsOutDto.setCompanyCode(pojo.getCompanyCode());
            qualityElementsOutDto.setCompanyName(pojo.getCompanyName());
            qualityElementsOutDto.setYear(pojo.getYear());
            qualityElementsOutDto.setFileCheckStatus(pojo.getFileCheckStatus());
            qualityElementsOutDto.setSchedule(pojo.getSchedule());
            qualityElementsOutDto.setCheckStatus(pojo.getCheckStatus());
            map1.put(qualityElementsOutDto.getCode(), qualityElementsOutDto);

            //同一层节点长度一样
            if (code.indexOf(pojo.getCode().length()) == -1)
                code.add(pojo.getCode().length());
        }
        return returnQualityYearElementList(map1, code);
    }

    public List<QualityYearElementsOutDto> returnQualityYearElementList(Map<String, QualityYearElementsOutDto> map, List<Integer> code) {
        List<QualityYearElementsOutDto> result = new ArrayList<>();
        Collections.sort(code);
        for (Map.Entry<String, QualityYearElementsOutDto> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key.length() == code.get(0))
                result.add(entry.getValue());
            else {
                QualityYearElementsOutDto treeDto = map.get(key.substring(0, code.get(code.indexOf(key.length()) - 1)));
                if (null == treeDto)
                    continue;
                if (null == treeDto.getChildNode()) {
                    List<QualityYearElementsOutDto> tmp = new ArrayList<>();
                    tmp.add(entry.getValue());
                    treeDto.setChildNode(tmp);
                } else
                    treeDto.getChildNode().add(entry.getValue());
            }
        }
        return result;
    }


    /**
     * 该方法用于获得拼接好的审核要素问题描述字段
     * @return 问题描述<code,Description>map
     */
    public Map<String,String> getReviewTermsMap()//获得审核条款的<code,Description>map
    {
        /*
        思想：直接一次把所有问题描述查完，根据code排序,放进list;然后根据code拼接，把code,对应问题描述字符，放进map;
         */
        //获得list
        List<QualityManagerSysEleReviewTermsDto> discriptionList=qualityManagerSysElementDao.queryAllReviewTerms();
        Map<String,String> disMap=new HashMap<>();
        //核心算法；k代表当前code对象,j代表下一个对象;i表示序号，temp代表拼接的字符串；类似字符串的Index算法；
        QualityManagerSysEleReviewTermsDto qualityTermsDto=discriptionList.get(0);
        String temp=qualityTermsDto.getBasis()+"%"+qualityTermsDto.getTerms()+"%"+qualityTermsDto.getContent();
        for(int j=1,k=0;j<discriptionList.size();j++) {
            //k的对象与自增j的对象code相等时，把j的字符和K拼在一起
            if(discriptionList.get(k).getCode().equals(discriptionList.get(j).getCode())) {
                qualityTermsDto=discriptionList.get(j);
                temp+="/**/"+qualityTermsDto.getBasis()+"%"+qualityTermsDto.getTerms()+"%"+qualityTermsDto.getContent();
                //list最后一个元素，须判断放入；
                if(j==(discriptionList.size()-1)) {
                    disMap.put(discriptionList.get(j).getCode(),temp);
                }
            }
            else {
                //k的对象与j的对象code不相等时，因为是有序，说明到了下一个字符，把上一个拼好的放入map;开始下一个字符拼接，k=j；
                disMap.put(discriptionList.get(k).getCode(),temp);
                k=j;
                qualityTermsDto=discriptionList.get(k);
                temp=qualityTermsDto.getBasis()+"%"+qualityTermsDto.getTerms()+"%"+qualityTermsDto.getContent();
            }
        }
        return disMap;
    }
}