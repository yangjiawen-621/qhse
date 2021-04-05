//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
////package com.wlhse.test;
//
//import com.wlhse.cache.JedisClient;
//import com.wlhse.dao.*;
//import com.wlhse.dto.TreeDto;
//import com.wlhse.dto.inDto.IndexSystemInDto;
//import com.wlhse.dto.outDto.ProblemImportDto;
//import com.wlhse.entity.CheckItemPojo;
//import com.wlhse.entity.CheckItemStardardPojo;
//import com.wlhse.entity.ProblemDescriptionPojo;
//import com.wlhse.service.*;
//import com.wlhse.util.PoiCheckItemV2.PoiImplV1;
//import com.wlhse.util.PoiCheckItemV2.PoiTreeCheckItem;
//import com.wlhse.util.PoiProblemDescriptions;
//import com.wlhse.util.PoiUtil;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath:spring/*.xml"})
//public class MyTest {
//
//    //byType(名字无所谓)
////    @Autowired
////    private AndroidService androidService;
//
//    @Resource(name = "androidServiceImpl")
//    private AndroidService androidService;
//
////    @Resource(type = AndroidService.class)
////    private AndroidService androidService;
//
////    @Resource(name = "aaa")
////    private AndroidService androidService;
//
//    @Autowired
//    private ProblemImportDaoV2 problemImportDaoV2;
//
//    @Test
//    public void test1() {
//        List<Integer> list = new ArrayList<>();
//        list.add(625);
//        list.add(626);
//        list.add(627);
//        problemImportDaoV2.updateResponsePersonID(list, -1);
////        System.out.println(url);
////        echartsDto.setEchartsDto(1,"hahah",22);
////        System.out.println(echartsDto);
//    }
//
//    @Resource
//    private ProblemDescriptionDao problemDescriptionDao;
//
//    @Test
//    public void test2() {
//        List<ProblemDescriptionPojo> list = new ArrayList<>();
//        ProblemDescriptionPojo pojo1 = new ProblemDescriptionPojo();
//        ProblemDescriptionPojo pojo2 = new ProblemDescriptionPojo();
//        ProblemDescriptionPojo pojo3 = new ProblemDescriptionPojo();
//
//        pojo1.setDescription("测试");
//        pojo2.setDescription("测试");
//        pojo3.setDescription("测试");
//
//
//        list.add(pojo1);
//        list.add(pojo2);
//        list.add(pojo3);
//
//        System.out.println(list);
//
//        problemDescriptionDao.addManyProblemDescriptions(list);
//    }
//
//    @Resource
//    private PoiUtil poiUtil;
//
//    @Resource
//    private PoiProblemDescriptions descriptions;
//
//    @Test
//    public void test3() throws CloneNotSupportedException {
//        Workbook workbook = poiUtil.createWorkbook("C:\\logs\\西南分公司安全环保事故隐患分级标准.xls");
//        List<ProblemDescriptionPojo> beanList = descriptions.getBeanList(workbook);
//        problemDescriptionDao.addManyProblemDescriptions(beanList);
//    }
//
//    @Value("${PROBLEM_RANK_SAME_SCORE}")
//    private String lightScore;
//
//    @Value("${PROBLEM_RANK_LIGHT_PENALTY}")
//    private String lightPenalty;
//
//    @Test
//    public void test4() {
//        System.out.println(lightScore);
//        System.out.println(lightPenalty);
//    }
//
//    @Resource
//    private CompanyTreeDao companyTreeDao;
//
//    @Test
//    public void test5() {
//        System.out.println(companyTreeDao.queryChildCode("0001"));
//        System.out.println(companyTreeDao.queryContractingCompany());
//    }
//
//    @Test
//    public void test6() {
//        System.out.println("99123456789".substring(0, 2));
////        System.out.println(companyTreeDao.queryContractingCompany());
//    }
//
//    @Resource
//    private CompanyDao companyDao;
//
//    @Test
//    public void test7() {
//        System.out.println(companyDao.getNameCount("西南分公司"));
////        System.out.println(companyTreeDao.queryContractingCompany());
//    }
//
//    @Resource
//    private EmployeeManagementDao employeeManagementDao;
//
//    @Test
//    public void test8() {
//        System.out.println(employeeManagementDao.getAllEmployeeDto("", "").size());
//    }
//
//    @Test
//    public void test9() {
//        String s = Long.toString(Long.valueOf("00015Z", 36) + 1, 36).toUpperCase();
//        System.out.println(s);
//        System.out.println(next("0001", "00015Z"));
//    }
//
//    private String next(String parent, String code) {
//        char[] chars = code.toCharArray();
//        boolean next = false;
//        for (int i = code.length() - 1; i > parent.length() - 1; i--) {
//            if (chars[i] == 'Z' || (next && chars[i] == 'Y')) {
//                chars[i] = '0';
//                next = true;
//            } else {
//                if (chars[i] == '9' || (next && chars[i] == '8'))
//                    chars[i] = 'A';
//                else
//                    chars[i] = (char) (chars[i] + 1);
//                next = false;
//            }
//            if (!next)
//                break;
//        }
//        return String.valueOf(chars);
//    }
//
//    @Test
//    public void test10() {
//        System.out.println(1);
//    }
//
//    @Resource
//    private CheckItemService checkItemService;
//
//    @Test
//    public void test11() {
//        System.out.println(checkItemService.getTreeDto());
//    }
//
//    @Resource
//    private CheckItemDao checkItemDao;
//
//    @Resource
//    private CheckItemStardardDao checkItemStandardDao;
//
//    @Test
//    public void test13() {
//        List<CheckItemPojo> result = new ArrayList<>();
//        CheckItemPojo pojo = new CheckItemPojo();
//        CheckItemPojo pojo1 = new CheckItemPojo();
//        CheckItemPojo pojo2 = new CheckItemPojo();
//
//        pojo.setCheckItemCode("0001");
//        pojo1.setCheckItemCode("0002");
//        pojo2.setCheckItemCode("0003");
//
//        pojo.setCheckItemName("haha1");
//        pojo1.setCheckItemName("haha2");
//        pojo2.setCheckItemName("haha3");
//
//        result.add(pojo);
//        result.add(pojo1);
//        result.add(pojo2);
//        checkItemDao.insertManyCheckItemPojo(result, "启用");
//    }
//
//    @Test
//    public void test14() {
//        List<CheckItemStardardPojo> list = new ArrayList<>();
//        CheckItemStardardPojo pojo = new CheckItemStardardPojo();
//        CheckItemStardardPojo pojo1 = new CheckItemStardardPojo();
//        CheckItemStardardPojo pojo2 = new CheckItemStardardPojo();
//
//        pojo.setCheckItemCode("0001");
//        pojo1.setCheckItemCode("0002");
//        pojo2.setCheckItemCode("0003");
//
//        pojo.setContent("haha1");
//        pojo1.setContent("haha2");
//        pojo2.setContent("haha3");
//
//        list.add(pojo);
//        list.add(pojo1);
//        list.add(pojo2);
//        checkItemStandardDao.insertManyCheckItemStandardPojo(list, "启用");
//    }
//
//    @Test
//    public void test12() {
//        String path = "C:\\logs\\安全检查表（20190528）.xls";
//        PoiTreeCheckItem util = new PoiTreeCheckItem(new PoiImplV1(), path, CheckItemPojo.class);
//        List<String> root = util.getTreeRoot(0, 0);
//        List<CheckItemPojo> list = checkItemDao.queryCheckItemPojo();
////
////        //父节点4位
//        Map map = util.treeDict(root, list, 4);
//        //子节点4位
//        util.poiTree(4, 1, 3, map, root, 4);
//
//        List<CheckItemPojo> result = util.getResult();
////        System.out.println(result.size());
//        checkItemDao.insertManyCheckItemPojo(result, "启用");
//        List<CheckItemStardardPojo> checkItemStandardList = util.getCheckItemStandardList();
//        checkItemStandardDao.insertManyCheckItemStandardPojo(checkItemStandardList, "启用");
//    }
//
//    @Resource
//    private ProblemFactorDao problemFactorDao;
//
//    @Test
//    public void test15() {
//        System.out.println(problemFactorDao.queryProblemFactor());
//    }
//
//    @Test
//    public void test16() {
//        System.out.println(checkItemDao.queryMaxLength());
//    }
//
//    @Resource
//    private LeaderCheckItemDao leaderCheckItemDao;
//
//    @Resource
//    private LeaderCheckItemService leaderCheckItemService;
//
//    @Test
//    public void test17() {
////        leaderCheckItemDao.updateLeaderCheckItem("A000");
////        System.out.println(leaderCheckItemDao.queryLeaderCheckItem());
////        leaderCheckItemDao.addLeaderCheckItem(null);
//
//        System.out.println(leaderCheckItemDao.getMaxCode("A001", 4));
//        System.out.println(leaderCheckItemDao.codeCount("A001"));
//    }
//
//    @Test
//    public void test18() {
//        System.out.println(leaderCheckItemService.queryTree());
//    }
//
//    @Resource
//    private ProblemImportService problemImportService;
//
//    @Resource
//    private ProblemImportDto problemImportDto;
//
//
//    @Test
//    public void test19() {
//        problemImportDto.setCheckItemCode("000100010001");
////        System.out.println(checkItemDao.queryByCheckItemCode("000100010001".substring(0, 4)));
//        problemImportService.newInsertProblemImport(problemImportDto, 123);
////        System.out.println("A0010000".substring(0,4));
//    }
//
//
//    @Resource
//    private EchartsDao echartsDao;
//
//    @Resource
//    private EchartService echartService;
//
//    @Resource
//    private IndexSystemInDto indexSystemInDto;
//
//    @Test
//    public void test20() {
////        indexSystemInDto.setCheckItem("0001");
////        indexSystemInDto.setStartDate("2019-1-1");
////        indexSystemInDto.setEndDate("2019-2-2");
//        System.out.println(echartService.getIndexSystemOutDto(indexSystemInDto));
//    }
//
//    @Test
//    public void test21() {
//        System.out.println(checkItemService.getTreeDtoV2());
//    }
//
//    @Test
//    public void testExcel() {
//        System.out.println(checkItemService.getTreeDtoV2());
//    }
//
//}
