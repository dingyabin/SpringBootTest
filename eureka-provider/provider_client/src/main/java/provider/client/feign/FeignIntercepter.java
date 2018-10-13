package provider.client.feign;

import com.google.common.base.Strings;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * Created by MrDing
 * Date: 2018/10/9.
 * Time:23:58
 */
public class FeignIntercepter implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        String traceId = MDC.get("traceId");
        if (Strings.isNullOrEmpty(traceId)) {
            traceId = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
        }
        template.header("traceId", traceId);
    }
}
