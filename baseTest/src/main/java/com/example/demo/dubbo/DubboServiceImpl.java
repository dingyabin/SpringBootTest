package com.example.demo.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.api.DubboService;
import org.springframework.stereotype.Component;

/**
 * Created by MrDing
 * Date: 2019/1/20.
 * Time:11:16
 */
@Component("dubboService")
@Service(interfaceClass = DubboService.class, version = "0.1",registry = "first")
public class DubboServiceImpl implements DubboService {

    @Override
    public String echo(String msg) {
        return msg;
    }
}
