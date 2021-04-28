package com.ttmy.awm.controller;


import com.ttmy.awm.api.Service.OrderClientService;
import com.ttmy.awm.api.Service.UserClientService;
import com.ttmy.awm.api.pojo.Awmorder;
import com.ttmy.awm.api.pojo.Washingmachine;
import com.ttmy.awm.api.pojo.Washingserver;
import com.ttmy.awm.api.pojo.vo.MachineStateVo;
import com.ttmy.awm.constant.OrderState;
import com.ttmy.awm.service.MachineService;
import com.ttmy.awm.service.MachineTaskService;
import com.ttmy.awm.service.QRcodeService;
import com.ttmy.awm.service.WashingContextService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MachineController {
    @Autowired
    private MachineService machineService;

    @Autowired
    private OrderClientService orderClientService;

    @Autowired
    private UserClientService userClientService;

    @Autowired
    private MachineTaskService machineTaskService;

    @Autowired
    private QRcodeService qRcodeService;

    @Autowired
    private WashingContextService washingContextService;

    @ApiOperation("查询所有机器")
    @GetMapping("/queryAll/list")
    public List<Washingmachine> queryAll() {
        return machineService.queryAll();
    }

    @ApiOperation("按ID(扫描二维码)查询机器")
    @GetMapping("/ById/{id}")
    public Washingmachine queryById(@PathVariable("id") String machineId) {
        return machineService.findMachineById(machineId);
    }

    @ApiOperation("查询机提供的服务")
    @GetMapping("/ByState/{id}")
    public List<MachineStateVo> queryByMachineState(@PathVariable("id") String machineId) {
        return machineService.queryMachineState(machineId);
    }

    @ApiOperation("创建新订单")
    @PostMapping("/creatorder")
    public void creatorder(@RequestBody HashMap<String,String> map) throws ParseException, InterruptedException {
        if (map==null){
            //System.out.println("未获取到订单信息");
            return;
        } else {
            Awmorder neworder = new Awmorder();
            neworder.setMachineId(map.get("machineId"));
            neworder.setServerlevel(map.get("serverlevel"));
            neworder.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(map.get("creattime")));
            neworder.setStartTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(map.get("starttime")));
            neworder.setCustomerId(userClientService.queryUserById(map.get("awmuser")).getAwmuserId());
            neworder.setOrderState(OrderState.USING_ORDER);
            orderClientService.creatneworder(neworder);//把map里的东西丢到order对象，用fegin传过去
            machineTaskService.startProduction(neworder);//异步任务，后台计算时间，时间结束改变订单状态
        }
    }

    @ApiOperation("更新设备状态")
    @PostMapping("/UpdateState")
    public int updatestatus(@RequestBody Washingserver newstate) {
        return machineService.updatestatus(newstate);
    }

    @ApiOperation("查询所有机器(ADMIN)")
    @GetMapping("/queryAllMachine/{current}/{size}")
    public List<Washingmachine> queryAllMachine(@PathVariable("current") int current,@PathVariable("size") int size) {
        return machineService.queryAllMachine(current,size);
    }

    @ApiOperation("查询总条数(ADMIN)")
    @GetMapping("/countmachine")
    public int countmachine(){
        return machineService.countmachine();
    }

    @ApiOperation("查询设备二维码(ADMIN)")
    @GetMapping("/machineQRcode/{MachineID}")
    public byte[] machineQRcode(@PathVariable("MachineID") String MachineID) throws Exception {
        return qRcodeService.QRcode(MachineID);
    }

    @ApiOperation("创建新设备(ADMIN)")
    @PostMapping("/creatnewMachine")
    public int creatnewMachine(@RequestBody Washingmachine newMachine) {
        return machineService.creatnewMachine(newMachine);
    }

    @ApiOperation("多条件查询某一设备(ADMIN)")
    @PostMapping("/queryMachine/{current}/{size}")
    public List<Washingmachine> queryMachine(@RequestBody Washingmachine newMachine,@PathVariable("current") int current,@PathVariable("size") int size) {
        return machineService.queryMachine(newMachine.getMachineId(),newMachine.getBrand(),current,size);
    }

    @ApiOperation("伪删除设备(ADMIN)")
    @PostMapping("/PseudodeleteMachine/{machineID}")
    public int PseudodeleteMachine(@PathVariable("machineID") String machineID) {
        return machineService.PseudodeleteMachine(machineID);
    }

    @ApiOperation("获取服务单价表(ADMIN)")
    @GetMapping("/servercost")
    public Map<String, BigDecimal> servercost() {
        return washingContextService.servercost();
    }

    @ApiOperation("获取地理位置信息(ADMIN)")
    @GetMapping("/position")
    public List<Map<String,Object>> position() {
        List<Map<String,Object>> returnlist =new ArrayList<Map<String, Object>>();
        List<Washingmachine> washingmachines = machineService.queryAll();
        for (Washingmachine machine: washingmachines) {
            List<BigDecimal> values = new ArrayList<BigDecimal>();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", machine.getBrand());
            List<MachineStateVo> machineStateVos = machineService.queryMachineState(machine.getMachineId());
            map.put("state",machineStateVos.get(0).getState());
            values.add(machine.getLongitude());
            values.add(machine.getLatitude());
            map.put("value", values);
            returnlist.add(map);
        }
        return returnlist;
    }

    @ApiOperation("设备回收站(ADMIN)")
    @GetMapping("/machinerecyclebin")
    public List<Map<String,Object>> machinerecyclebin() throws Exception {
        return machineService.machinerecyclebin();
    }

    @ApiOperation("批量伪删除(ADMIN)")
    @PostMapping("/PseudodeletelistMachine")
    public int PseudodeletelistMachine(@RequestBody List<Washingmachine> washingmachines) {
        return machineService.PseudodeletelistMachine(washingmachines);
    }

    @ApiOperation("多条件查询某一设备总条数(ADMIN)")
    @PostMapping("/queryMachinesize")
    public int queryMachinesize(@RequestBody Washingmachine newMachine) {
        return machineService.queryMachinesize(newMachine.getMachineId(),newMachine.getBrand());
    }

    @ApiOperation("更新设备信息(ADMIN)")
    @PostMapping("/commituptmachine")
    public void commituptmachine(@RequestBody Washingmachine uptMachine) {
        machineService.commituptmachine(uptMachine);
    }
}
