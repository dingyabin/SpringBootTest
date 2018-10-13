package provider.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import provider.common.model.Weight;

import java.util.Date;

/**
 *
 * @author dingyabin
 * @date 2018/4/10
 * Time: 14:58
 * function:
 */
@Slf4j
@RestController
@RequestMapping("/provider")
public class ProviderController {


    @RequestMapping("service/v1")
    public Weight provider(@RequestBody Weight weight){
        log.info("provider/service/v1 收到请求:{}", weight);
        weight.setCreateTime(new Date());
        return weight;
    }


}
