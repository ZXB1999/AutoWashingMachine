package com.ttmy.awm.api.pojo;

import com.ttmy.awm.api.Entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 服务等级与金额持久化类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)  //链式写法
public class Servercontext extends BaseEntity {
    private String scId;
    private String servertype;
    private String serverlevel;
    private BigDecimal cost;
}
