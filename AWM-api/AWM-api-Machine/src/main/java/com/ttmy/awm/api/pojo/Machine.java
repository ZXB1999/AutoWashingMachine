package com.ttmy.awm.api.pojo;

import com.ttmy.awm.api.Entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 设备持久化类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)  //链式写法
public class Machine extends BaseEntity {
    private String machineId;
    private String brand;
    private String model;
    private String type;
}
