package com.ttmy.awm.api.pojo;

import com.ttmy.awm.api.Entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 设备状态与服务持久类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)  //链式写法
public class Washingserver extends BaseEntity {
    private String machineserverId;
    private String machineId;
    private String state;
    private String server;
}
