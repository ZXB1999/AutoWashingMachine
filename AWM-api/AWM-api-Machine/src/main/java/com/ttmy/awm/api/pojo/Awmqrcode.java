package com.ttmy.awm.api.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ttmy.awm.api.Entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)  //链式写法
public class Awmqrcode extends BaseEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private String qrId;
    private String machineId;
    private byte[] qrcode;
}
