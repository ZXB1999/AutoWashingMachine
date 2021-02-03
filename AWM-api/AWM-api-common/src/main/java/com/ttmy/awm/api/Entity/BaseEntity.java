package com.ttmy.awm.api.Entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
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
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 更新时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date updateTime;

    private String delflag;

    @Version
    private Integer version;
}
