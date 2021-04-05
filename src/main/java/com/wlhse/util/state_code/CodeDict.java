package com.wlhse.util.state_code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Properties;

@Component
public class CodeDict {
    public final static int SUCCESS = 1000;
    public final static int INSERT_FAILE = 1100;
    public final static int INSERT_FAILE_PLAN_EMPTY = 1110;
    public final static int INSERT_FAILE_CHECK_ITEM_ERROR = 1120;
    public final static int INSERT_FAILE_TWO_PASSWORD_SIMILARITY = 1130;
    public final static int INSERT_FAILE_PASSWORD_ERROR = 1140;
    public final static int INSERT_SAME_EMPLOYEEE = 1150;

    public final static int DELETE_FAILE = 1200;
    public final static int UPDATE_FAILE = 1300;
    public final static int SELECT_FAILE = 1400;

    public final static int ILLEGAL_FAIL = 1401;

    public final static int ILLEGAL_LOGIN = 1403;

    public final static int LOGIN_SUCCESS = 1000;
    public final static int LOGIN_FAIL = 1600;
    public final static int LOGIN_FAIL_MANY_TIMES = 1610;
    public final static int NOT_LOGGED_ = 1620;
    public final static int PASSWORD_ERROR = 1621;

    public final static int REGISTER_SUCCESS = 1000;
    public final static int REGISTER_FAIL = 1900;
    public final static int REGISTER_FAIL_PASS_WORD_SHORT = 1910;
    public final static int REGISTER_FAIL_PASS_WORD_LONG = 1920;
    public final static int REGISTER_FAIL_PASS_WORD_SIMPLE = 1930;
    public final static int REGISTER_FAIL_USERNAME_SIMPLE = 1940;
    public final static int REGISTER_FAIL_USERNAME_REPEAT = 1950;
    public final static int REGISTER_FAIL_EMPLOYEE_REPEAT = 1960;

    public final static int UPLOAD_EMPTY = 1700;
    public final static int UPLOAD_FAIL = 1800;
    public final static int UPLOAD_TYPE_ERROR = 1801;

    public final static int PROBLEM_INSERT_SAME = 2110;
    public final static int PROBLEM_TASK_SAME = 2120;


    public final static int UNKOWN_FAILE = 2000;

    public final static int SERVER_BUSY = 2100;


    public final static int CODE_MESSAGE = 3000;
    public final static int CODE_MESSAGE_DATA = 3100;
    public final static int CODE_MESSAGE_DATA_PAGE_LIST = 3200;
    public final static int CODE_MESSAGE_DATA_LIST = 3300;

    public final static int POI_PROBLEM_TASK_ERROR = 4110;
    public final static int POI_PROBLEM_COMPANY_ERROR = 4120;
    public final static int POI_PROBLEM_CHECK_DATE_ERROR = 4130;
    public final static int POI_PROBLEM_CHECK_ITEM_ERROR = 4140;
    public final static int POI_PROBLEM_TASK_AND_PROCESS_ERROR = 4150;
    public final static int POI_PROBLEM_DESCRIPTION_ERROR = 4160;
    public final static int POI_PROBLEM_RESPONSE_PERSON_ERROR = 4170;
    public final static int POI_PROBLEM_UNKNOWN_ERROR = 4200;
    public final static int POI_PROBLEM_EMPTY_FIRST = 4300;
    public final static int POI_ReportCodeDuplic_ERROR = 4410;
    public final static int POI_Report_Insert_ERROR = 4420;

    public final static int TWELVE_HOURS_SECONDS = 43200;

    public final static int TWENTY_MINUTES = 1200;

    public final static int TIME_FIRSTDAY = 9000;
    public final static int TIME_LASTDAY = 9100;

    public final static int SEND_TASK = 4210;
    public final static int SEND_VERIFY = 4220;
    public final static int SEND_TASK_NO_PASS = 4230;
    public final static int SEND_PROBLEM_FOUND = 4250;
    public final static int SEND_PROBLEM_RECTI = 4260;
    public final static int SEND_PROBLEM_VERIFY = 4270;

    public final static int SEND_TASK_PASS = 4280;
    public final static int SEND_TASK_FINISH = 4290;
    public final static int SEND_VERIFY_NO_PASS = 4291;
    public final static int SEND_RECTI_NO_PASS = 4292;

    public final static int SEND_PROBLEM_NO_PASS = 4293;
    public final static int SEND_PROBLEM_EXAMINE = 4294;


    private static Properties properties;

    @Autowired
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
    public static String getRESOURCES_URL() {
        String RESOURCES_URL = properties.getProperty("RESOURCES_URL");
        return RESOURCES_URL;
    }

}
