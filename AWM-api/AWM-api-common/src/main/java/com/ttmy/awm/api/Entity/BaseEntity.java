package com.ttmy.awm.api.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


/**
 * Entity基类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)  //链式写法
public abstract class BaseEntity implements Serializable {
    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    private String delflag;
}
