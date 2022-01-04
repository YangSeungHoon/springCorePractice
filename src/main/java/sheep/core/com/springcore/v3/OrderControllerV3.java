package sheep.core.com.springcore.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sheep.core.com.springcore.trace.TraceStatus;
import sheep.core.com.springcore.trace.logtrace.LogTrace;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

    private final OrderServiceV3 orderService;
    private final LogTrace trace;

    @GetMapping("/V3/request")
    public String request(String itemId) {


        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            orderService.orderItem(itemId);
            trace.end(status);
            return "ok";
        } catch (Exception e){
            trace.exception(status, e);
            throw e;
        }
    }
}
