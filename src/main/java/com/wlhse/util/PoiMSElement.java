package com.wlhse.util;

import com.wlhse.dto.CheckListDto;
import com.wlhse.dto.EmployeeExcelDto;
import com.wlhse.dto.QualityCheckListDto;
import com.wlhse.dto.inDto.QSHEMSElementInDto;
import com.wlhse.dto.inDto.QualityManagerSysElementInDto;

import java.util.List;

public class PoiMSElement {

    public static String isDuplicelements(List<QSHEMSElementInDto> elements) {
        String duplic = null;
        for (int i = 0; i < elements.size(); i++) {
            for (int j = i + 1; j < elements.size(); j++) {
                if (elements.get(i).getCode().equals(elements.get(j).getCode())) {
                    duplic = elements.get(i).getCode();
                    break;
                }
            }
        }
        return duplic;
    }

    public static String isDuplicelements2(List<CheckListDto> elements) {
        String duplic = null;
        for (int i = 0; i < elements.size(); i++) {
            for (int j = i + 1; j < elements.size(); j++) {
                if (elements.get(i).getCheckListCode().equals(elements.get(j).getCheckListCode())) {
                    duplic = elements.get(i).getCheckListCode();
                    break;
                }
            }
        }
        return duplic;
    }

    public static String isDuplicelements3(List<QualityCheckListDto> elements) {
        String duplic = null;
        for (int i = 0; i < elements.size(); i++) {
            for (int j = i + 1; j < elements.size(); j++) {
                if (elements.get(i).getCheckListCode().equals(elements.get(j).getCheckListCode())) {
                    duplic = elements.get(i).getCheckListCode();
                    break;
                }
            }
        }
        return duplic;
    }

    public static String isDuplicelements4(List<QualityManagerSysElementInDto> elements) {
        String duplic = null;
        for (int i = 0; i < elements.size(); i++) {
            for (int j = i + 1; j < elements.size(); j++) {
                if (elements.get(i).getCode().equals(elements.get(j).getCode())) {
                    duplic = elements.get(i).getCode();
                    break;
                }
            }
        }
        return duplic;
    }
    public static String isDuplicelements5(List<EmployeeExcelDto> elements) {
        String duplic = null;
        for (int i = 0; i < elements.size(); i++) {
            for (int j = i + 1; j < elements.size(); j++) {
                if (elements.get(i).getuName().equals(elements.get(j).getuName())) {
                    duplic = elements.get(i).getuName();
                    break;
                }
            }
        }
        return duplic;
    }


}