package com.wlhse.dto.Message;

import com.alibaba.fastjson.JSONObject;
import com.wlhse.util.state_code.CodeDict;
import org.springframework.stereotype.Component;

@Component
public class CodeNoteMessageDto extends NoteMessageDto {

    private int code;

    public CodeNoteMessageDto() {
    }

    public CodeNoteMessageDto(int code) {
        this.code = code;
    }

    @Override
    protected String getMessage() {
        JSONObject object = new JSONObject(true);
        object.put("code", this.code);
        object.put("message", NoteMessage());
        return object.toJSONString();
    }

    private String NoteMessage() {
        String noteMessage = null;
        switch (this.code) {
            case CodeDict.LOGIN_FAIL:
                noteMessage = "登录失败";
                break;
            case CodeDict.PASSWORD_ERROR:
                noteMessage = "密码错误";
                break;
            case CodeDict.INSERT_FAILE:
                noteMessage = "新增失败";
                break;
            case CodeDict.DELETE_FAILE:
                noteMessage = "删除失败";
                break;
            case CodeDict.UPDATE_FAILE:
                noteMessage = "更新失败";
                break;
            case CodeDict.SELECT_FAILE:
                noteMessage = "查询失败";
                break;
        }
        return noteMessage;
    }
}
