package com.wlhse.service.impl;

import com.wlhse.dao.ModuleDao;
import com.wlhse.dto.outDto.MenuOutDto;
import com.wlhse.entity.ModulePojo;
import com.wlhse.service.ModuleService;
import com.wlhse.util.DictCode;
import com.wlhse.util.TreeUtil;
import com.wlhse.util.state_code.CodeDict;
import com.wlhse.util.state_code.NR;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Resource
    private ModuleDao moduleDao;

    @Resource
    private TreeUtil treeUtil;

    @Override
    public String getMenuModule(int employeeId) {
        List<ModulePojo> list = moduleDao.queryModulesByEmpId(employeeId);
//        List<ModulePojo> list = moduleDao.queryModulesByEmpId(186);
//        Map<String, MenuOutDto> map = new HashMap<>();
//        for (ModulePojo modulePojo : list) {
//            if (StringUtils.isNotBlank(modulePojo.getModuleCode())) {
//                MenuOutDto menuOutDto = new MenuOutDto();
//                menuOutDto.setIcon(modulePojo.getImg());
//                menuOutDto.setTitle(modulePojo.getName());
//                String[] strarray = modulePojo.getUrl().split(",");
//                menuOutDto.setRouteName(strarray[0]);
//                List<String> strList = Arrays.asList(strarray);
//                if (strList.size() > 0) {
//                    menuOutDto.setRoutes(strList);
//                }
//                map.put(modulePojo.getModuleCode(), menuOutDto);
//            }
//        }
        //treeUtil.returnMenuOutDtoList(map, DictCode.MODULE_LEVEL_1)
        return NR.r(treeUtil.getMenuTree(list));
    }
}
