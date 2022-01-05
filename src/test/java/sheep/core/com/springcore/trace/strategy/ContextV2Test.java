package sheep.core.com.springcore.trace.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import sheep.core.com.springcore.trace.strategy.code.strategy.ContextV2;
import sheep.core.com.springcore.trace.strategy.code.strategy.Strategy;
import sheep.core.com.springcore.trace.strategy.code.strategy.StrategyLogic1;
import sheep.core.com.springcore.trace.strategy.code.strategy.StrategyLogic2;


@Slf4j
public class ContextV2Test {

    @Test
    void strategyV1() {
        ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }

    // 익명 내부 클래스 활요
    @Test
    void strategyV2(){
        ContextV2 context1 = new ContextV2();
        context1.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });

        ContextV2 context2 = new ContextV2();
        context2.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        });
    }

    // 람다 활용
    // 익명 내부 클래스 활요
    @Test
    void strategyV3(){
        ContextV2 context1 = new ContextV2();
        context1.execute(() -> log.info("비즈니스 로직1 실행"));

        ContextV2 context2 = new ContextV2();
        context2.execute(() -> log.info("비즈니스 로직2 실행"));
    }

}
