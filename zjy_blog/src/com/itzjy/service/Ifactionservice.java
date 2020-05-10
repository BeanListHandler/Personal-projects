package com.itzjy.service;
import com.itzjy.domian.Classification;
import java.util.List;
public interface Ifactionservice {

    /**
     * 插入分类
     * @param classification
     */
    public void sava (Classification classification);

    /**
     * 遍历所有数据
     * @return
     */
    List<Classification> getall();

    /**
     * 根据id查询信息
     * @param cid
     * @return
     */
    Classification getone(Integer cid);

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
