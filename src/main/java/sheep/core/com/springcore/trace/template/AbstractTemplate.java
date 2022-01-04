package sheep.core.com.springcore.trace.template;

import sheep.core.com.springcore.trace.TraceStatus;
import sheep.core.com.springcore.trace.logtrace.LogTrace;

public abstract class AbstractTemplate<T> {

    private final LogTrace trace;

    public AbstractTemplate(LogTrace trace){
        this.trace = trace;
    }

    public T execute(String message){
        TraceStatus status = null;
        try {
            status = trace.begin(message);

            // 이 부분이 변하는 부분이니까 상속으로 구현해준다.
            T result = call();

            trace.end(status);
            return result;
        }catch(Exception e){
            trace.exception(status,e);
            throw e;
        }
    }

    protected abstract T call();
}
