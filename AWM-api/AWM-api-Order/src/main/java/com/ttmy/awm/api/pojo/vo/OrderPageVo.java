package com.ttmy.awm.api.pojo.vo;

import com.ttmy.awm.api.pojo.Awmorder;
import lombok.Data;

import java.util.List;

@Data
public class OrderPageVo {
    private Integer current;
    private Integer size;
    private Long total;
    private List<Awmorder> awmorderList;
}
