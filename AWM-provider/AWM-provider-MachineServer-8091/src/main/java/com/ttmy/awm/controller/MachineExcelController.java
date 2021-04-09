package com.ttmy.awm.controller;

import com.alibaba.excel.EasyExcel;
import com.ttmy.awm.api.pojo.ec.MachineExcelEc;
import com.ttmy.awm.service.MachineExcelServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@RestController
public class MachineExcelController {
    @Autowired
    private MachineExcelServer machineExcelServer;

    @GetMapping("/download")
    public void download(HttpServletResponse response)throws IOException {
        List<MachineExcelEc> list = machineExcelServer.ExportExcel();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), MachineExcelEc.class).sheet("模板").doWrite(list);
    }
}
