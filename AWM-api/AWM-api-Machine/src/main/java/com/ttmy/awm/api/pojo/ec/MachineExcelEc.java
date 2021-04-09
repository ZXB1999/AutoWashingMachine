package com.ttmy.awm.api.pojo.ec;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MachineExcelEc {
    @ExcelProperty("品牌")
    private String brand;
    @ExcelProperty("型号")
    private String model;
    @ExcelProperty("类别")
    private String type;
    @ExcelProperty("经度")
    private BigDecimal longitude;
    @ExcelProperty("纬度")
    private BigDecimal latitude;
}
