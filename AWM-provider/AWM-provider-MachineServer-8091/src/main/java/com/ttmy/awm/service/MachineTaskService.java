package com.ttmy.awm.service;

import com.ttmy.awm.api.pojo.Awmorder;

public interface MachineTaskService {
    void startProduction(Awmorder neworder) throws InterruptedException;
}
