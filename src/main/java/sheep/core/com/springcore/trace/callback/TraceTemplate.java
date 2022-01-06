package sheep.core.com.springcore.trace.callback;

import lombok.extern.slf4j.Slf4j;
import sheep.core.com.springcore.trace.TraceStatus;
import sheep.core.com.springcore.trace.logtrace.LogTrace;

@Slf4j
public class TraceTemplate {

    private final LogTrace trace;

    public TraceTemplate(LogTrace trace) {
        this.trace = trace;
    }

    // 제네릭 타입, 반환 타입
    public <T> T execute(String message, TraceCallback<T> callback) {
        TraceStatus status = null;
        try {
            status = trace.begin(message);

            // 이 부분이 변하는 부분이니까 상속으로 구현해준다.
            T result = callback.call();

            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
