package provider.client.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by MrDing
 * Date: 2018/10/9.
 * Time:23:58
 */
@Component
public class FeignIntercepter implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        template.header("traceId", UUID.randomUUID().toString().toUpperCase());
    }
}
