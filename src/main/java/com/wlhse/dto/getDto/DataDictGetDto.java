package com.wlhse.dto.getDto;

import org.springframework.stereotype.Component;


@Component
public class DataDictGetDto{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
