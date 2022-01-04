package sheep.core.com.springcore.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sheep.core.com.springcore.trace.TraceId;
import sheep.core.com.springcore.trace.TraceStatus;
import sheep.core.com.springcore.trace.hellotrace.HelloTraceV2;


@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final HelloTraceV2 trace;

    public void save(TraceId traceId, String itemId){

        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId,"OrderRepository.save()");

            if(itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    private void sleep(int millis) {
        try{
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
