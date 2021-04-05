package com.wlhse.dto.getDto;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.stereotype.Component;


@Component
public class BaseGetDto {
    @JSONField(serialize = false)
    private int pageIdx = 1;
    @JSONField(serialize = false)
    private int pageSize = 25;
    @JSONField(serialize = false)
    private String type = "page";

    public int getPageIdx() {
        return pageIdx;
    }

    public void setPageIdx(int pageIdx) {
        this.pageIdx = pageIdx;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getType() { return type; }

    public void setType(String type) {
        this.type = type;
    }
}
