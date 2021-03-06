package provider.client.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import provider.client.config.MyFeignClientsConfig;
import provider.client.factory.ProviderServiceFactory;
import provider.common.model.Weight;

/**
 * Created by MrDing
 * Date: 2018/10/13.
 * Time:12:57
 */
@FeignClient(value = "eureka-provider",
             configuration = MyFeignClientsConfig.class,
             fallbackFactory = ProviderServiceFactory.class)
public interface ProviderService {


    @RequestMapping("provider/service/v1")
    Weight provider(@RequestBody Weight weight);

}
