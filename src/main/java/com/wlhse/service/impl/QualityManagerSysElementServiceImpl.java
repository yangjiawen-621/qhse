package com.wlhse.service.impl;

import com.wlhse.cache.JedisClient;
import com.wlhse.dao.QHSEManageSysElementsDao;
import com.wlhse.dao.QualityManagerSysElementDao;
import com.wlhse.dto.QHSEproblemDiscriptionDto;
import com.wlhse.dto.QualityManagerSysEleReviewTermsDto;
import com.wlhse.dto.QualityManagerSysElementProDesDto;
import com.wlhse.dto.inDto.QualityYearElementsDto;
import com.wlhse.dto.inDto.YearElementsDto;
import com.wlhse.dto.outDto.ElementAndConfigStatusDto;
import com.wlhse.entity.QHSECompanySysElementsPojo;
import com.wlhse.entity.QHSEManageSysElements;
import com.wlhse.entity.QhseElementsPojo;
import com.wlhse.entity.QualityElementsPojo;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.QualityManagerSysElementService;
import com.wlhse.util.R;
import com.wlhse.util.SortCodeUtil;
import com.wlhse.util.TreeUtil;
import com.wlhse.util.state_code.NR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class QualityManagerSysElementServiceImpl implements QualityManagerSysElementService {

    @Resource
    QualityManagerSysElementDao qualityManagerSysElementDao;

    @Resource
    private SortCodeUtil sortCodeUtil;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TreeUtil treeUtil;
    @Resource
    JedisClient jedisClient;
    private final int QHSEMSETREE_MAX_HEIGHT=4;//定义树的最大层数
    private final int QHSEMSETREE_CODE_BITS=3;//定义树的每级编码位数
    private final int QHSEMSETREE_CODE_MAXLEN=QHSEMSETREE_MAX_HEIGHT*QHSEMSETREE_CODE_BITS;//叶子节点长度


    public void setOff(String str, String code) {
        qualityManagerSysElementDao.setOff(str, code);

    }//本人及其儿子改成停用

    public void setOn(String str, String code) {
        qualityManagerSysElementDao.setOn(str, code);
    }//本人及其父母改成启用

    public List<String> getParent(String code) {
        List<String> list = new ArrayList<String>();
        int len = code.length();
        while (len > 0) {//父节点
            String str = code.substring(0, len);
            list.add(str);
            len -= 3;
        }
        list.remove(0);
        return list;
    }//当前节点的所有父节点 不包含本人

    public Integer getScore(String code) {
        return qualityManagerSysElementDao.getScore(code);//返回这个叶子节点的分数
    }

    //    public void subScoreCount(String code,Integer score){
//        qhseManageSysElementsDao.subSoreCount(code,score);//父节点减去分数score和项数1
//    }
    public int sumScore(String code) {
        return qualityManagerSysElementDao.sumScore(code,QHSEMSETREE_CODE_MAXLEN);//当前所有叶子节点的分数之和
    }

    public int sumCount(String code) {
        return qualityManagerSysElementDao.sumCount(code,QHSEMSETREE_CODE_MAXLEN);//当前所有叶子节点的项数之和
    }

    public void updateScoreCount(String code, Integer score, Integer count) {
        if((qualityManagerSysElementDao.updateScoreCount(code, score, count))<=0)//父母的score count减去
            throw new WLHSException("更新失败");
    }

    public void toZero(String code) {
       if(( qualityManagerSysElementDao.toZero(code,QHSEMSETREE_CODE_MAXLEN))<=0)//当前项到叶子节点前一项的所有分数和项数都归零
           throw new WLHSException("更新失败");
    }

    public void addScoreCount(List<String> list, Integer score) {//所有父母依次加分 项数加一
       int j=1;
        for (int i = 0; i < list.size(); i++) {
            j*=(qualityManagerSysElementDao.addScoreCount(list.get(i), score));
        }
        if(j<=0)
            throw new WLHSException("更新失败");
    }

    //-------------------------------------更新区------------------------------------------
    //th-查询基本数据表
    @Override
    public R queryAllElement() {
        R ok = R.ok();
        ok.put("data", treeUtil.getQualityElementTree(qualityManagerSysElementDao.queryQhseElements()));
        return ok;
    }

    //th-查询年度要素
    @Override
    public R queryYearElement(YearElementsDto yearElementsDto) {
        List<YearElementsDto> lists=qualityManagerSysElementDao.queryQhseYearElements(yearElementsDto);
        for (YearElementsDto yearElement:lists) {
            int sums=qualityManagerSysElementDao.querySchedule(yearElement.getCode(),yearElement.getCompanyCode(),yearElement.getYear());
            int num=qualityManagerSysElementDao.querySchdules(yearElement.getCode(),yearElement.getCompanyCode(),yearElement.getYear());
            int num1=sums-num;
            if(yearElement.getCode().length()!=QHSEMSETREE_MAX_HEIGHT*QHSEMSETREE_CODE_BITS)//树的最大编码
            yearElement.setSchedule(num1+"/"+sums);
        }
        R ok = R.ok();
        ok.put("data", treeUtil.getQhseYearElementTree(lists));
        return ok;
    }

    //th-查询基本数据表两级
    @Override
    public R queryChildElement() {
        R ok = R.ok();
        ok.put("data", treeUtil.getQualityElementTree(qualityManagerSysElementDao.queryQhseChildElements()));
        return ok;
    }

    /**
     * 该方法用于根据节点是否启用获得审核要素的查询树，
     * @return 返回新增操作成功，失败的消息，为json对象
     */
    @Override
    public R queryAllElements(Integer tag) {
        if (tag == 0) //查启用
        {
            R ok = R.ok();
            ok.put("data", treeUtil.getQualityElementTreeForExcel(qualityManagerSysElementDao.queryQhseElements()));
            return ok;
        } else if (tag == 1) //查所有
        {
            R ok = R.ok();
            ok.put("data", treeUtil.getQualityElementTreeForExcel(qualityManagerSysElementDao.queryQhseAllElements()));
            return ok;
        } else {
            throw new WLHSException("查询失败");
        }
    }


    /**
     * 该方法用于审核要素的状态切换
     * @param id 该参数为int型，表示欲改变status的节点的ID
     * @return 返回新增操作成功，失败的消息，为json对象
     */
    @Override
    public R updateElementStatus(int id) {
        /*
        思想：根据id查询其节点，如果是停用，就改为启用，上面父节点全部设启用，如果是叶子，还要改变所有父节点分数；
        如果是启用，就改为停用，下面孩子全部停用，上面所有父节点也要改变分数和节点数；
         */
        QualityElementsPojo qhseElementsPojo=qualityManagerSysElementDao.getElementById(id);//根据id得到节点
        String code = qhseElementsPojo.getCode();//获得相关属性
        Integer len = code.length();
        String status = qhseElementsPojo.getStatus();
        List<String> list = getParent(code);//得到所有的父节点
        if (status.equals("启用")) {//如果原来状态是启用，就停用；
            setOff("停用", code);//本人及其孩子改成停用
            if (len == (QHSEMSETREE_CODE_MAXLEN)) {//如果是叶子节点，修改上面父节点的分数和节点数；
                int score = getScore(code);
                for (int i = 0; i < list.size(); i++) {
                    updateScoreCount(list.get(i), score, 1);
                }
            } else {//不是叶子节点，先统计本节点的总分，和叶节点数；
                Integer score = sumScore(code); //score: 0
                Integer count = sumCount(code);//count: 0
                toZero(code);//将当前项和子项的分数和项数都归零
                for (int i = 0; i < list.size(); i++) {//该节点所有分母依次减分减项数
                    updateScoreCount(list.get(i), score, count);
                }
            }
        } else if (status.equals("停用")) {//如果原来状态是启停用，就启用；
            for (int i = 0; i < list.size(); i++) {//父节点全设为启用
                setOn("启用", list.get(i));
            }
            setOn("启用", code);//本节点设为启用
            if (len == (QHSEMSETREE_CODE_MAXLEN)) {//如果是叶子节点，更新所有父节点的分数，cout;
                int score = getScore(code);
                addScoreCount(list, score);
            }
        }
        return R.ok();
    }

    /**
     * 该方法用于审核要素的跟新编辑
     * @param qhseManageSysElement 该参数为一个QhseElementsPojo对象，为欲编辑的节点；
     * @return 返回新增操作成功，失败的消息，为json对象
     */
    @Override
    public R updateElementcontent(QualityElementsPojo qhseManageSysElement) {
         /*
        思想：不是叶子节点，直接更新。是叶子节点，如果分数变化，还要更新上面所有父节点的分数；
         */
        String code = qhseManageSysElement.getCode();
        //根据ID查询数据库更新前的节点信息；
        QualityElementsPojo qhseElementsPojo=qualityManagerSysElementDao.getElementByCode(code);
        Integer len = code.length();
        String status =qhseElementsPojo.getStatus();
        //System.out.println(status+" "+code+""+qhseManageSysElement.getQhseManagerSysElementID());
        if ("启用".equals(status)) {
            if (len == (QHSEMSETREE_CODE_MAXLEN)) { //如果是叶子节点，还要更新所有父节点的分数
                Integer score = qhseElementsPojo.getInitialScore();
                Integer newScore = qhseManageSysElement.getInitialScore() - score;//获得新编辑的分数与原来的分数差
                if (newScore != 0) {//获得所有父节点
                    List<String> parentCode = new ArrayList<String>();
                    while (len > QHSEMSETREE_CODE_BITS) {
                        len -= QHSEMSETREE_CODE_BITS;
                        String str = code.substring(0, len);
                        parentCode.add(str);
                    }
                    for (int i = 0; i < parentCode.size(); i++) {//所有父节点加上这个分数差
                        String pcode = parentCode.get(i);
                        if (qualityManagerSysElementDao.addInitialScore(pcode, newScore) <= 0)
                            throw new WLHSException("更新失败");
                    }
                }
            }
        }
        if (qualityManagerSysElementDao.updateElement(qhseManageSysElement) <= 0)//更新本节点的内容
            throw new WLHSException("更新失败");
        return R.ok();

    }

    /**
     * 该方法用于审核要素的新增
     * @param qhseManageSysElement 该参数为一个QhseElementsPojo对象，为欲新增节点的父节点；
     * @return 返回新增操作成功，失败的消息，为json对象
     */
    @Override
    public R addElement(QualityElementsPojo qhseManageSysElement) {
        /*
        思想：先根据父节点在数据库里找到父节点下的子节点的最大编码，然后加1，生成新增节点编码。
        然后又根据第一级节点，中间节点，叶子节点的不同情况，分别设置属性，其中叶子节点还要更新所有父节点的分数，节点数。最后插入添加的节点。
         */
        String parentCode = qhseManageSysElement.getCode();//传入的code都是插入的这一级的父节点编码
        try {
            if (parentCode == null || "".equals(parentCode)) //如果是插入的第一级，父节点就是空字符
            {
                String maxCode = qualityManagerSysElementDao.querryLastQHSEChildCode2("");//获得当前插入这一级的最大编码
                String newcode;
                if(maxCode == null ||"".equals(maxCode)){//如果第一级还没有节点；
                    newcode="001";
                }else{//有节点，最大节点加一；
                    newcode=sortCodeUtil.getMaxCodeString(maxCode);
                }
                qhseManageSysElement.setCode(newcode);
                qhseManageSysElement.setTotalCount(0);//设置叶子总是
                qhseManageSysElement.setInitialScore(0);//设置分数
            } else {
                Integer len = parentCode.length();
                if (len < (QHSEMSETREE_MAX_HEIGHT-1)*QHSEMSETREE_CODE_BITS) {    //如果插入是非第一级和最后一级,len<9
                    qhseManageSysElement.setTotalCount(0);
                    qhseManageSysElement.setInitialScore(0);
                    String maxCode = qualityManagerSysElementDao.querryLastQHSEChildCode2(parentCode); //查找插入那级最大编码
                    //System.out.println(maxCode);
                    if (maxCode == null || "".equals(maxCode))//如果还没节点，直接生成
                        qhseManageSysElement.setCode(parentCode + "001");
                    else {//否则最大编码加1
                        qhseManageSysElement.setCode(sortCodeUtil.getMaxCodeString(maxCode));
                    }
                } else if (len == (QHSEMSETREE_MAX_HEIGHT-1)*QHSEMSETREE_CODE_BITS) {  //插入是叶子节点,len==15
                    qhseManageSysElement.setTotalCount(1);
                    String maxCode = qualityManagerSysElementDao.querryLastQHSEChildCode2(parentCode);//查找插入那级最大编码
                    if (maxCode == null || "".equals(maxCode))
                        qhseManageSysElement.setCode(parentCode + "001");
                    else {
                        qhseManageSysElement.setCode(sortCodeUtil.getMaxCodeString(maxCode));
                    }
                    //如果是插入叶子节点还要修改父节的分数，cout;
                    Integer len1 = len;
                    Integer initialScore = qhseManageSysElement.getInitialScore();
                    List<String> pCode = new ArrayList<String>();//获得所有父节点
                    while (len1 > 0) {
                        String str = parentCode.substring(0, len1);
                        pCode.add(str);
                        len1 -= QHSEMSETREE_CODE_BITS;
                    }
                    for (int i = 0; i < pCode.size(); i++) {//修改叶子节点所有的父节点的分数和节点数
                        String code = pCode.get(i);
                        if (qualityManagerSysElementDao.addTotalCount(code, 1) <= 0)
                            throw new WLHSException("更新失败");
                        if (qualityManagerSysElementDao.addInitialScore(code, initialScore) <= 0)
                            throw new WLHSException("更新失败");
                    }
                }
            }
            //最后添加叶子节点
            if (qualityManagerSysElementDao.addQHSEElement(qhseManageSysElement) <= 0)
                throw new WLHSException("新增失败");
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw new WLHSException("新增失败");
        }

    }

    /**
     * 该方法用于根据审核要素查询其所有的问题描述
     * @param code 审核要素管理节点的code
     * @return 返回问题描述的树
     */
    @Override
    public R querryQhseProblemDiscription(String code) {
        R ok = R.ok();
        ok.put("data", qualityManagerSysElementDao.querryDescriptionBycode(code));
        return ok;
    }

    /**
     * 该方法用于问题描述的删除
     * @param id 问题描述的id
     * @return 返回新增操作成功，失败的消息，为json对象
     */
    @Override
    public R deleteQhseProblemDiscription(Integer id) {
        if(qualityManagerSysElementDao.deleteDescriptionById(id)<=0)
            throw new WLHSException("删除失败");
        return R.ok();
    }

    /**
     * 该方法用于更新问题描述
     * @param qHSEproblemDiscriptionDto 问题描述的一个对象
     * @return 返回新增操作成功，失败的消息，为json对象
     */
    @Override
    public R updateQhseProblemDiscription(QualityManagerSysElementProDesDto qHSEproblemDiscriptionDto) {
        if(qualityManagerSysElementDao.updateDescriptionById(qHSEproblemDiscriptionDto)<=0)
            throw new WLHSException("更新失败");
        return R.ok();
    }

    /**
     * 该方法用于增加问题描述
     * @param qHSEproblemDiscriptionDto 问题描述的一个对象
     * @return 返回新增操作成功，失败的消息，为json对象
     */
    @Override
    public R addQhseProblemDiscription(QualityManagerSysElementProDesDto qHSEproblemDiscriptionDto) {
        String code=qHSEproblemDiscriptionDto.getCode();
        String description=qHSEproblemDiscriptionDto.getDescription();
        if(qualityManagerSysElementDao.addProblemDescription(code,description)<=0)
            throw new WLHSException("添加失败");
        return R.ok();
    }

    @Override
    public R queryQualityReviewTerms(String code) {
        R ok = R.ok();
        ok.put("data", qualityManagerSysElementDao.queryReviewTermsByCode(code));
        return ok;
    }

    @Override
    public R deleteQualityReviewTerms(Integer id) {
        if(qualityManagerSysElementDao.deleteReviewTermsById(id)<=0)
            throw new WLHSException("删除失败");
        return R.ok();
    }

    @Override
    public R updateQualityReviewTerms(QualityManagerSysEleReviewTermsDto qualityManagerSysEleReviewTermsDto) {
        if(qualityManagerSysElementDao.updateReviewTermsById(qualityManagerSysEleReviewTermsDto)<=0)
            throw new WLHSException("更新失败");
        return R.ok();
    }

    @Override
    public R addQualityReviewTerms(QualityManagerSysEleReviewTermsDto qualityManagerSysEleReviewTermsDto) {
        if(qualityManagerSysElementDao.addReviewTerms(qualityManagerSysEleReviewTermsDto)<=0)
            throw new WLHSException("添加失败");
        return R.ok();
    }

    @Override
    public R addYearElement(YearElementsDto yearElementsDto) {
        return null;
    }

    @Override
    public R getTableCheckedProgress(int tableId) {
        return null;
    }

    @Override
    public R queryYearElements(YearElementsDto yearElementsDto) {
        return null;
    }


}
