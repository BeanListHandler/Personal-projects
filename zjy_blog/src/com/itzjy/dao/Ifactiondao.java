package com.itzjy.dao;

import com.itzjy.domian.Classification;

import java.util.List;

public interface Ifactiondao {
    /**
     * 添加分类
     * @param classification
     */
    public void save(Classification classification);

    /**
     * 查询分类
     */
    List<Classification> list();

    /**
     * 根据id查询分类
     * @param cid
     * @return
     */
    Classification getonegor(Integer cid);

    /**
     * 修改分类
     * @param classification
     */
    void updatec(Classification classification);

    /**
     * 删除分类
     * @param classification
     */
    void delete(Classification classification);
}
