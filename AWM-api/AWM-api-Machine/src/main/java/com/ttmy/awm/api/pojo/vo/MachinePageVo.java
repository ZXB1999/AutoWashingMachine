package com.ttmy.awm.api.pojo.vo;

import com.ttmy.awm.api.pojo.Washingmachine;
import lombok.Data;

import java.util.List;

@Data
public class MachinePageVo {
    private Integer current;
    private Integer size;
    private Long total;
    private List<Washingmachine> washingmachineList;
}
