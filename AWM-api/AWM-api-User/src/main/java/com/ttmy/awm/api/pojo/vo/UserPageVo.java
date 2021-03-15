package com.ttmy.awm.api.pojo.vo;

import com.ttmy.awm.api.pojo.Awmuser;
import lombok.Data;

import java.util.List;

@Data
public class UserPageVo {
    private Integer current;
    private Integer size;
    private Long total;
    private List<Awmuser> awmuserList;
}
