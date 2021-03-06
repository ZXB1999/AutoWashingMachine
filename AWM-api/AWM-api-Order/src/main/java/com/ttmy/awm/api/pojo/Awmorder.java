package com.ttmy.awm.api.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ttmy.awm.api.Entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.util.Date;

/**
 * 订单持久化类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)  //链式写法
public class Awmorder extends BaseEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private String orderId;

    private String customerId;
    private String machineId;
    private String serverlevel;
    private Date startTime;
    private String orderState;
}
