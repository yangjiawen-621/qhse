package com.wlhse.util.state_code;

import com.alibaba.fastjson.JSONObject;

import java.util.List;


public class NR<T> {
    public static String r(int retrunType, int i, int operate, Object data, List list, int total, int page) {
        JSONObject object = new JSONObject(true);
        JSONObject object1 = new JSONObject(true);
        //增删改操作
        if (CodeDict.CODE_MESSAGE == retrunType) {
            if (i > 0) {
                object.put("code", CodeDict.SUCCESS);
                object.put("message", "操作成功");
            } else {
                if (operate == CodeDict.INSERT_FAILE) {
                    object.put("code", CodeDict.INSERT_FAILE);
                    object.put("message", "新增失败");
                } else if (operate == CodeDict.DELETE_FAILE) {
                    object.put("code", CodeDict.DELETE_FAILE);
                    object.put("message", "删除失败");
                } else if (operate == CodeDict.UPDATE_FAILE) {
                    object.put("code", CodeDict.UPDATE_FAILE);
                    object.put("message", "更新失败");
                } else if (operate == CodeDict.UPLOAD_EMPTY) {
                    object.put("code", CodeDict.UPLOAD_EMPTY);
                    object.put("message", "上传为空");
                } else if (operate == CodeDict.UPLOAD_TYPE_ERROR) {
                    object.put("code", CodeDict.UPLOAD_TYPE_ERROR);
                    object.put("message", "上传文件格式错误");
                } else if (operate == CodeDict.UPLOAD_FAIL) {
                    object.put("code", CodeDict.UPLOAD_FAIL);
                    object.put("message", "上传失败");
                } else if (operate == CodeDict.SELECT_FAILE) {
                    object.put("code", CodeDict.SELECT_FAILE);
                    object.put("message", "查询失败");
                } else if (operate == CodeDict.SERVER_BUSY) {
                    object.put("code", CodeDict.SERVER_BUSY);
                    object.put("message", "BusyService");
                } else if (operate == CodeDict.INSERT_FAILE_PLAN_EMPTY) {
                    object.put("code", CodeDict.INSERT_FAILE_PLAN_EMPTY);
                    object.put("message", "编制计划为空");
                } else {
                    object.put("code", CodeDict.UNKOWN_FAILE);
                    object.put("message", "未知错误");
                }
            }

        } else if (CodeDict.CODE_MESSAGE_DATA == retrunType) {
            object.put("code", CodeDict.SUCCESS);
            object.put("message", "操作成功");
            object.put("data", data);
        } else if (CodeDict.CODE_MESSAGE_DATA_PAGE_LIST == retrunType) {
            object1.put("page", page);
            object1.put("total", total);
            object1.put("list", list);
            object.put("code", CodeDict.SUCCESS);
            object.put("message", "操作成功");
            object.put("data", object1);
        } else if (CodeDict.CODE_MESSAGE_DATA_LIST == retrunType) {
            object1.put("list", list);
            object.put("code", CodeDict.SUCCESS);
            object.put("message", "操作成功");
            object.put("data", object1);
        } else if (CodeDict.LOGIN_SUCCESS == retrunType) {
            object.put("code", CodeDict.SUCCESS);
            object.put("message", "登录成功");
            object.put("data", data);
        } else if (CodeDict.ILLEGAL_LOGIN == retrunType) {
            object.put("code", CodeDict.ILLEGAL_LOGIN);
            object.put("message", "非法闯入");
            object.put("data", data);
        } else if (CodeDict.LOGIN_FAIL == retrunType) {
            object.put("code", CodeDict.LOGIN_FAIL);
            object.put("message", "登录失败");
        } else if (CodeDict.PASSWORD_ERROR == retrunType) {
            object.put("code", CodeDict.PASSWORD_ERROR);
            object.put("message", "密码错误");
        } else if (CodeDict.INSERT_FAILE_CHECK_ITEM_ERROR == retrunType) {
            object.put("code", CodeDict.INSERT_FAILE_CHECK_ITEM_ERROR);
            object.put("message", "检查项未选择完整");
        } else if (CodeDict.INSERT_FAILE_TWO_PASSWORD_SIMILARITY == retrunType) {
            object.put("code", CodeDict.INSERT_FAILE_TWO_PASSWORD_SIMILARITY);
            object.put("message", "旧密码与新密码相同");
        } else if (CodeDict.INSERT_FAILE_PASSWORD_ERROR == retrunType) {
            object.put("code", CodeDict.INSERT_FAILE_PASSWORD_ERROR);
            object.put("message", "旧密码输入错误");
        } else if (CodeDict.LOGIN_FAIL_MANY_TIMES == retrunType) {
            object.put("code", CodeDict.LOGIN_FAIL_MANY_TIMES);
            object.put("message", "频繁登录!请2分钟后重试");
        } else if (CodeDict.REGISTER_SUCCESS == retrunType) {
            object.put("code", CodeDict.SUCCESS);
            object.put("message", "注册成功");
        } else if (CodeDict.REGISTER_FAIL == retrunType) {
            object.put("code", CodeDict.REGISTER_FAIL);
            object.put("message", "注册失败");
        } else if (CodeDict.ILLEGAL_FAIL == retrunType) {
            object.put("code", CodeDict.ILLEGAL_FAIL);
            object.put("message", "验证失败");
        } else if (CodeDict.REGISTER_FAIL_PASS_WORD_SHORT == retrunType) {
            object.put("code", CodeDict.REGISTER_FAIL_PASS_WORD_SHORT);
            object.put("message", "注册失败!密码过短");
        } else if (CodeDict.REGISTER_FAIL_PASS_WORD_LONG == retrunType) {
            object.put("code", CodeDict.REGISTER_FAIL_PASS_WORD_LONG);
            object.put("message", "注册失败!密码过长");
        } else if (CodeDict.REGISTER_FAIL_PASS_WORD_SIMPLE == retrunType) {
            object.put("code", CodeDict.REGISTER_FAIL_PASS_WORD_SIMPLE);
            object.put("message", "注册失败!密码过于简单");
        } else if (CodeDict.NOT_LOGGED_ == retrunType) {
            object.put("code", CodeDict.NOT_LOGGED_);
            object.put("message", "请登录");
        } else if (CodeDict.REGISTER_FAIL_USERNAME_SIMPLE == retrunType) {
            object.put("code", CodeDict.REGISTER_FAIL_USERNAME_SIMPLE);
            object.put("message", "注册失败!用户名过于简单");
        } else if (CodeDict.REGISTER_FAIL_USERNAME_REPEAT == retrunType) {
            object.put("code", CodeDict.REGISTER_FAIL_USERNAME_REPEAT);
            object.put("message", "注册失败!用户名已被注册");
        } else if (CodeDict.REGISTER_FAIL_EMPLOYEE_REPEAT == retrunType) {
            object.put("code", CodeDict.REGISTER_FAIL_USERNAME_REPEAT);
            object.put("message", "注册失败!该用户已注册");
        } else if (CodeDict.INSERT_SAME_EMPLOYEEE == retrunType) {
            object.put("code", CodeDict.INSERT_SAME_EMPLOYEEE);
            object.put("message", "该用户已存在");
        } else if (CodeDict.PROBLEM_INSERT_SAME == retrunType) {
            object.put("code", CodeDict.PROBLEM_INSERT_SAME);
            object.put("message", "问题录入重复");
        } else if (CodeDict.PROBLEM_TASK_SAME == retrunType) {
            object.put("code", CodeDict.PROBLEM_TASK_SAME);
            object.put("message", "任务下达重复");
        } else {
            object.put("code", CodeDict.UNKOWN_FAILE);
            object.put("message", "BusyService");
        }

        return object.toJSONString();
    }

    // ok
    public static String r() {
        JSONObject object = new JSONObject(true);
        object.put("code", CodeDict.SUCCESS);
        object.put("message", "操作成功");
        return object.toJSONString();
    }

    public static String r(Object data) {
        JSONObject object = new JSONObject(true);
        object.put("code", CodeDict.SUCCESS);
        object.put("message", "操作成功");
        object.put("data", data);
        return object.toJSONString();
    }

    public static String r(List list, int total, int page) {
        JSONObject object = new JSONObject(true);
        JSONObject object1 = new JSONObject(true);
        object1.put("page", page);
        object1.put("total", total);
        object1.put("list", list);
        object.put("code", CodeDict.SUCCESS);
        object.put("message", "操作成功");
        object.put("data", object1);
        return object.toJSONString();
    }

    public static String r(List list, String companyName, String companyCode,String year,String status) {
        JSONObject object = new JSONObject(true);
        JSONObject object1 = new JSONObject(true);
        object1.put("companyName", companyName);
        object1.put("companyCode", companyCode);
        object1.put("year", year);
        object1.put("status", status);
        object1.put("list", list);
        object.put("code", CodeDict.SUCCESS);
        object.put("message", "操作成功");
        object.put("data", object1);
        return object.toJSONString();
    }

    public static String getPoiProblemReturn(int code, int i) {
        JSONObject object = new JSONObject(true);
        if (code == CodeDict.SUCCESS) {
            object.put("code", CodeDict.SUCCESS);
            object.put("message", "导入操作成功");
        } else if (code == CodeDict.POI_PROBLEM_TASK_ERROR) {
            object.put("code", CodeDict.POI_PROBLEM_TASK_ERROR);
            object.put("message", "请检查第" + i + "行,第A列任务编码(系统查无此记录)");
        } else if (code == CodeDict.POI_PROBLEM_COMPANY_ERROR) {
            object.put("code", CodeDict.POI_PROBLEM_COMPANY_ERROR);
            object.put("message", "请检查第" + i + "行,第B,C列单位名称(系统查无此记录)");
        } else if (code == CodeDict.POI_PROBLEM_CHECK_DATE_ERROR) {
            object.put("code", CodeDict.POI_PROBLEM_CHECK_DATE_ERROR);
            object.put("message", "请检查第" + i + "行,第D列检查时间");
        } else if (code == CodeDict.POI_PROBLEM_CHECK_ITEM_ERROR) {
            object.put("code", CodeDict.POI_PROBLEM_CHECK_ITEM_ERROR);
            object.put("message", "请检查第" + i + "行,第E,F,G,H,列检查名称(系统查无此记录)");
        } else if (code == CodeDict.POI_PROBLEM_TASK_AND_PROCESS_ERROR) {
            object.put("code", CodeDict.POI_PROBLEM_TASK_AND_PROCESS_ERROR);
            object.put("message", "请检查第" + i + "行,第I,J列任务,工序(系统查无此记录)");
        } else if (code == CodeDict.POI_PROBLEM_DESCRIPTION_ERROR) {
            object.put("code", CodeDict.POI_PROBLEM_DESCRIPTION_ERROR);
            object.put("message", "请检查第" + i + "行,第K列问题描述(系统查无此记录)");
        } else if (code == CodeDict.POI_PROBLEM_RESPONSE_PERSON_ERROR) {
            object.put("code", CodeDict.POI_PROBLEM_RESPONSE_PERSON_ERROR);
            object.put("message", "请检查第" + i + "行,第N,O,P列员工信息(系统查无此记录)");
        } else if (code == CodeDict.POI_PROBLEM_EMPTY_FIRST) {
            object.put("code", CodeDict.POI_PROBLEM_EMPTY_FIRST);
            object.put("message", "导入EXCEL为空");
        } else {
            object.put("code", CodeDict.POI_PROBLEM_UNKNOWN_ERROR);
            object.put("message", "请参照模板进行修改");
        }
        return object.toJSONString();
    }

    public static String getPoiReportsReturn(int code,String str){
        JSONObject object = new JSONObject(true);
        if (code == CodeDict.SUCCESS) {
            object.put("code", CodeDict.SUCCESS);
            object.put("message", "导入操作成功");
        } else if (code == CodeDict.POI_ReportCodeDuplic_ERROR) {
            object.put("code", CodeDict.POI_ReportCodeDuplic_ERROR);
            object.put("message", "报告编号存在重复："  + str);
        }else if(code == CodeDict.POI_Report_Insert_ERROR){
            object.put("code", CodeDict.POI_ReportCodeDuplic_ERROR);
            object.put("message", "报告导入出错:"  + str);
        }
        return object.toJSONString();
    }

    public static String getEmptyNote(int i, int j) {
        JSONObject object = new JSONObject(true);
        object.put("code", CodeDict.POI_PROBLEM_TASK_ERROR);
        char j1 = (char) (j + 65);
        object.put("message", "请填写第" + i + "行,第" + j1 + "列(此处为空)");
        return object.toJSONString();
    }
}
