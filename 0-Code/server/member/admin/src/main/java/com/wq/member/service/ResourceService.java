package com.wq.member.service;

import com.wq.member.model.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ResourceService {
    /**
     * 添加资源
     */
    int create(Resource resource);

    /**
     * 修改资源
     */
    int update(Long id, Resource resource);

    /**
     * 获取资源详情
     */
    Resource getItem(Long id);

    /**
     * 删除资源
     */
    int delete(Long id);

    /**
     * 分页查询资源
     */
    List<Resource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum);

    /**
     * 查询全部资源
     */
    List<Resource> listAll();
}
