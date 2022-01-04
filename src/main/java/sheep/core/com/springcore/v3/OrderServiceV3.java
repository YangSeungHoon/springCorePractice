package sheep.core.com.springcore.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sheep.core.com.springcore.trace.TraceStatus;
import sheep.core.com.springcore.trace.logtrace.LogTrace;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId) {

        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            orderRepository.save(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
