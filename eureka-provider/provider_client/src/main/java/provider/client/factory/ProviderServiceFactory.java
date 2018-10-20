package provider.client.factory;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import provider.client.service.ProviderService;
import provider.common.model.Weight;


/**
 * Created by MrDing
 * Date: 2018/10/20.
 * Time:16:03
 */
@Component
public class ProviderServiceFactory implements FallbackFactory<ProviderService> {


    @Override
    public ProviderService create(Throwable cause) {
        return weight -> new Weight(0,0 ,"这是降级返回的");
    }



}
