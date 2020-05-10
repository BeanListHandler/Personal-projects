package com.itzjy.domian;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Classification implements Serializable {
    private Integer cid;
    private String cname;
    private Integer parentid;

    @Override
    public String toString() {
        return "Classification{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", parentid=" + parentid +
                '}';
    }
}
