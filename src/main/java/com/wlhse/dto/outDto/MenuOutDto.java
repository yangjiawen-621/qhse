package com.wlhse.dto.outDto;

import com.alibaba.fastjson.annotation.JSONType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@JSONType(orders = {"title","routeName","icon","routes","children","code"})
public class MenuOutDto {
    private String title;
    private String routeName;
    private String icon;
    private List<String> routes;
    private List<MenuOutDto> children;
    private String code;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<String> getRoutes() {
        return routes;
    }

    public void setRoutes(List<String> routes) {
        this.routes = routes;
    }

    public List<MenuOutDto> getChildren() {
        return children;
    }

    public void setChildren(List<MenuOutDto> children) {
        this.children = children;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
