package com.wlhse.dao;

import com.wlhse.entity.FilePropagationPOJO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilePropagationDao {
    int insertNewFilePropagationPlan(FilePropagationPOJO filePropagationPOJO);

    List<FilePropagationPOJO> getAllFilePropagation();

    int deletePropagationPlan(Long id);
}
