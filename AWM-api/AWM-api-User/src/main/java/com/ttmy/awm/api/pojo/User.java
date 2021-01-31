package com.ttmy.awm.api.pojo;

import com.ttmy.awm.api.Entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)  //链式写法
public class User extends BaseEntity {
    private String awmuserId;
    private String awmname;
    private String awmusername;
    private String password;
}
