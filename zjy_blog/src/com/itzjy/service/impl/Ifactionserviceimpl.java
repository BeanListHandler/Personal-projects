package com.itzjy.service.impl;

import com.itzjy.dao.Ifactiondao;
import com.itzjy.domian.Classification;
import com.itzjy.service.Ifactionservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class Ifactionserviceimpl implements Ifactionservice {
    @Autowired
    private Ifactiondao ifactiondao;

    @Override
    public void sava(Classification classification) {
        ifactiondao.save(classification);
    }

    @Override
    public List<Classification> getall() {
         return ifactiondao.list();

    }

    @Override
    public Classification getone(Integer cid) {
        Classification classification = ifactiondao.getonegor(cid);
        return classification
                ;
    }

    @Override
    public void updatec(Classification classification) {
        ifactiondao.updatec(classification);
    }

    @Override
    public void delete(Classification classification) {
        ifactiondao.delete(classification);
    }
}
