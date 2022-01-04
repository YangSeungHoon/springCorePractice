package sheep.core.com.springcore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sheep.core.com.springcore.trace.logtrace.FieldLogTrace;
import sheep.core.com.springcore.trace.logtrace.LogTrace;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace(){
        return new FieldLogTrace();
    }
}
