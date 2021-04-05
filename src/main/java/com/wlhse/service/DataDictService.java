package com.wlhse.service;


import com.wlhse.entity.DataDictPojo;

public interface DataDictService {

    String getChirdren(String parent);

    String insertSonDataDict(DataDictPojo dictPojo);

    String deleteDataDict(DataDictPojo dictPojo);

    String updateDataDict(DataDictPojo dictPojo);

    String deleteDataDictById(int id);

    String getDataDictTree();
}
