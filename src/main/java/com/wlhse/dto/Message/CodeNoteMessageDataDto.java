package com.wlhse.dto.Message;

import com.alibaba.fastjson.JSONObject;
import com.wlhse.util.state_code.CodeDict;
import org.springframework.stereotype.Component;

@Component
public class CodeNoteMessageDataDto<T> extends NoteMessageDto {
    private int code;
    private T t;

    public CodeNoteMessageDataDto() {
    }

    public CodeNoteMessageDataDto(int code, T t) {
        this.code = code;
        this.t = t;
    }

    @Override
    protected String getMessage() {
        JSONObject object = new JSONObject(true);
        object.put("code", this.code);
        object.put("message", NoteMessage());
        object.put("data", this.t);
        return object.toJSONString();
    }

    private String NoteMessage() {
        String noteMessage = null;
        switch (this.code) {
            case CodeDict.LOGIN_SUCCESS:
                noteMessage = "登录成功";
                break;
        }
        return noteMessage;
    }
}
