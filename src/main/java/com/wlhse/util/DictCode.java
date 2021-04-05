package com.wlhse.util;

import org.springframework.stereotype.Component;


@Component
public class DictCode {
    public final static int CHECK_ITEM_LEVEL_1 = 4;
    public final static int CHECK_ITEM_LEVEL_2 = 6;
    public final static int CHECK_ITEM_LEVEL_3 = 8;
    public final static int CHECK_ITEM_LEVEL_4 = 10;

    //Company表的一、二、三、四级编码长度
    public final static int COMPANY_LEVEL_1 = 2;
    public final static int COMPANY_LEVEL_2 = 4;
    public final static int COMPANY_LEVEL_3 = 6;
    public final static int COMPANY_LEVEL_4 = 8;

    //Roles表的一级编码长度
    public final static int ROLES_LEVEL_1 = 4;

    //Module表的一、二、三、四级编码长度
    public final static int MODULE_LEVEL_1 = 4;
    public final static int MODULE_LEVEL_2 = 6;

    //TaskAndProcess表的一级编码长度
    public final static int TASK_AND_PROCESS_LEVEL_1 = 2;
    public final static int TASK_AND_PROCESS_LEVEL_2 = 4;

    //TaskObject表的一、二级编码长度
    public final static int TASK_OBJECT_LEVEL_1 = 4;
    public final static int TASK_OBJECT_LEVEL_2 = 6;

    //CheckType表的一、二级编码长度
    public final static int CHECK_TYPE_LEVEL_1 = 4;
    public final static int CHECK_TYPE_LEVEL_2 = 6;

    //ProblemFactor表的一、二级编码长度
    public final static int PROBLEM_FACTOR_LEVEL_1 = 4;
    public final static int PROBLEM_FACTOR_LEVEL_2 = 6;

    //ProblemSource表的一、二级编码长度
    public final static int PROBLEM_SOURCE_LEVEL_1 = 3;
    public final static int PROBLEM_SOURCE_LEVEL_2 = 6;

    //ProblemDescription表的一、二级编码长度
    public final static int PROBLEM_DESCRIPTION_LEVEL_1 = 4;
    public final static int PROBLEM_DESCRIPTION_LEVEL_2 = 6;

    public final static int DATA_DICT_LEVEL_1=4;
    public final static int DATA_DICT_LEVEL_2=6;

    public final static int SATET_CODE_INSERT=1;
    public final static int SATET_CODE_DELETE=2;
    public final static int SATET_CODE_UPDATE=3;
    public final static int SATET_CODE_SELECT=4;

}
