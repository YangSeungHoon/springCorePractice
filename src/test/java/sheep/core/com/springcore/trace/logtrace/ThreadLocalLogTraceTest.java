package sheep.core.com.springcore.trace.logtrace;

import org.junit.jupiter.api.Test;
import sheep.core.com.springcore.trace.TraceStatus;

class ThreadLocalLogTraceTest {

    ThreadLocalLogTrace trace=  new ThreadLocalLogTrace();

    @Test
    void begin_end_level2(){
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");
        trace.end(status2);
        trace.end(status1);
        System.out.println("============");
    }

    @Test
    void begin_exception_level2(){
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");
        trace.exception(status2,new IllegalStateException());
        trace.exception(status1,new IllegalStateException());
    }

}