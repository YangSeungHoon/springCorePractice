package sheep.core.com.springcore.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sheep.core.com.springcore.trace.TraceId;
import sheep.core.com.springcore.trace.TraceStatus;
import sheep.core.com.springcore.trace.hellotrace.HelloTraceV2;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepository;
    private final HelloTraceV2 trace;

    public void orderItem(TraceId traceId, String itemId) {

        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId,"OrderController.request()");
            orderRepository.save(status.getTraceId(),itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
