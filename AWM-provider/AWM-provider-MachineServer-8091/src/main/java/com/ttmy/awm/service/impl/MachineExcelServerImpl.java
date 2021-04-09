package com.ttmy.awm.service.impl;

import com.alibaba.excel.EasyExcel;
import com.ttmy.awm.api.pojo.Washingmachine;
import com.ttmy.awm.api.pojo.ec.MachineExcelEc;
import com.ttmy.awm.dao.MachineMapper;
import com.ttmy.awm.service.MachineExcelServer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MachineExcelServerImpl implements MachineExcelServer {
    String PATH="D:\\PROJECT\\AutoWashingMachine\\exc\\";

    @Autowired
    private MachineMapper machineMapper;

    public void ImportExcel() {
    }

    public List<MachineExcelEc> ExportExcel() {
        List<Washingmachine> washingmachines = machineMapper.selectList(null);
        List<MachineExcelEc> list = new ArrayList<MachineExcelEc>();
        for (Washingmachine washingmachine:washingmachines) {
            MachineExcelEc data = new MachineExcelEc();
            data.setBrand(washingmachine.getBrand());
            data.setModel(washingmachine.getModel());
            data.setType(washingmachine.getType());
            data.setLongitude(washingmachine.getLongitude());
            data.setLatitude(washingmachine.getLatitude());
            list.add(data);
        }
        return list;
//        String fileName =PATH+ new Date().getTime()+".xlsx";
//        EasyExcel.write(fileName, MachineExcelEc.class).sheet("设备").doWrite(list);
    }

}
