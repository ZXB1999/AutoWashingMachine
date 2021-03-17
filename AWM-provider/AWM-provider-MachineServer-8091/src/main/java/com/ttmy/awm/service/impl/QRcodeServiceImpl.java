package com.ttmy.awm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ttmy.awm.api.pojo.Awmqrcode;
import com.ttmy.awm.dao.MachineMapper;
import com.ttmy.awm.dao.QRCodeMapper;
import com.ttmy.awm.service.QRcodeService;
import com.ttmy.awm.utils.QRCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QRcodeServiceImpl implements QRcodeService {
    @Autowired
    private MachineMapper machineMapper;
    @Autowired
    private QRCodeMapper qrCodeMapper;
    @Autowired
    private QRCodeUtils qrCodeUtils;


    public byte[] QRcode(String MachineID) throws Exception {
        //机器是否存在
        if(machineMapper.selectById(MachineID)!=null){
            QueryWrapper<Awmqrcode> qrwrapper = new QueryWrapper();
            qrwrapper.in("machine_id", MachineID);
            Awmqrcode awmQRcode = qrCodeMapper.selectOne(qrwrapper);
            //是否有二维码，有则返回流，没有增加一条，返回流
            if(awmQRcode!=null){
                return awmQRcode.getQrcode();
            }else {
                Awmqrcode awmnewQRcode=new Awmqrcode();
                awmnewQRcode.setMachineId(MachineID);
                //这个流会被改成生成的图片流
//                awmnewQRcode.setQrcode(new byte[12]);
                awmnewQRcode.setQrcode(qrCodeUtils.encode(MachineID,"C:\\Users\\TTMY\\Desktop\\毕业设计\\素材\\logo\\logo.png",true));
                qrCodeMapper.insert(awmnewQRcode);
                return awmnewQRcode.getQrcode();
            }
        }
        return new byte[0];
    }
}
