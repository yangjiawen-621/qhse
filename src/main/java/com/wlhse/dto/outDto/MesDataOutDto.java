package com.wlhse.dto.outDto;

import lombok.Data;

import java.util.List;

@Data
public class MesDataOutDto {

    //名称
    private String name;

    //类型
    String type="line";
    //数据,按时间给出某个率
    List  data;
}
