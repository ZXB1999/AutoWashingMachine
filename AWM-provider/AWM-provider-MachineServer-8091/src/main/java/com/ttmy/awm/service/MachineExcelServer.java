package com.ttmy.awm.service;

import com.ttmy.awm.api.pojo.ec.MachineExcelEc;

import java.util.List;

public interface MachineExcelServer {
    void ImportExcel();

    List<MachineExcelEc> ExportExcel();
}
