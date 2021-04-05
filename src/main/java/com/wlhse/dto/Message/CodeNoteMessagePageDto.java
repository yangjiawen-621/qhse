package com.wlhse.dto.Message;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CodeNoteMessagePageDto<T> extends NoteMessageDto {
    private int code;
    private List<T> list;
    private int total;
    private int page;

    public CodeNoteMessagePageDto() {
    }

    public CodeNoteMessagePageDto(int code, List<T> list, int total, int page) {
        this.code = code;
        this.list = list;
        this.total = total;
        this.page = page;
    }

    @Override
    protected String getMessage() {
        JSONObject object = new JSONObject(true);
        JSONObject object1 = new JSONObject(true);
        object1.put("page", this.page);
        object1.put("total", this.total);
        object1.put("list", this.list);
        object.put("code", this.code);
        object.put("message", "操作成功");
        object.put("data", object1);
        return object.toJSONString();
    }
}
