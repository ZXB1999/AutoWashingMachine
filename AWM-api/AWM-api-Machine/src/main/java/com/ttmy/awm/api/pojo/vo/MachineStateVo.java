package com.ttmy.awm.api.pojo.vo;

import com.ttmy.awm.api.Entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)  //链式写法
public class MachineStateVo extends BaseEntity {
    private String brand;
    private String type;
    private String model;
    private String state;
    private String server;
    private String serverlevel;
    private BigDecimal cost;
}
