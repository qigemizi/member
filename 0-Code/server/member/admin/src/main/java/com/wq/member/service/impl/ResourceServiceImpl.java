package com.wq.member.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.wq.member.mapper.ResourceMapper;
import com.wq.member.model.Resource;
import com.wq.member.model.ResourceExample;
import com.wq.member.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 后台资源管理Service实现类
 * Created by macro on 2020/2/2.
 */
@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;
    // @Autowired
    // private UmsAdminCacheService adminCacheService;
    @Override
    public int create(Resource umsResource) {
        umsResource.setCreateTime(new Date());
        return resourceMapper.insert(umsResource);
    }

    @Override
    public int update(Long id, Resource umsResource) {
        umsResource.setId(id);
        int count = resourceMapper.updateByPrimaryKeySelective(umsResource);
        // adminCacheService.delResourceListByResource(id);
        return count;
    }

    @Override
    public Resource getItem(Long id) {
        return resourceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(Long id) {
        int count = resourceMapper.deleteByPrimaryKey(id);
        // adminCacheService.delResourceListByResource(id);
        return count;
    }

    @Override
    public List<Resource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        ResourceExample example = new ResourceExample();
        ResourceExample.Criteria criteria = example.createCriteria();
        if(categoryId!=null){
            criteria.andCategoryIdEqualTo(categoryId);
        }
        if(StrUtil.isNotEmpty(nameKeyword)){
            criteria.andNameLike('%'+nameKeyword+'%');
        }
        if(StrUtil.isNotEmpty(urlKeyword)){
            criteria.andUrlLike('%'+urlKeyword+'%');
        }
        return resourceMapper.selectByExample(example);
    }

    @Override
    public List<Resource> listAll() {
        return resourceMapper.selectByExample(new ResourceExample());
    }
}
