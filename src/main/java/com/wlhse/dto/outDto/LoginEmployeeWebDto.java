package com.wlhse.dto.outDto;

import com.wlhse.dto.TreeDto;
import com.wlhse.entity.ModulePojo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoginEmployeeWebDto {
    private Integer employeeID;
    private String name;
    private List<ModulePojo> modules;
    private int state;
    private String stateName;
    private List<TreeDto> treeDtos;

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ModulePojo> getModules() {
        return modules;
    }

    public void setModules(List<ModulePojo> modules) {
        this.modules = modules;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public List<TreeDto> getTreeDtos() {
        return treeDtos;
    }

    public void setTreeDtos(List<TreeDto> treeDtos) {
        this.treeDtos = treeDtos;
    }

}
