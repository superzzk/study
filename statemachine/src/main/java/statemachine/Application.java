package statemachine;

import statemachine.demo1.StatemachineService;
import statemachine.demo1.event.TurnstileEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private StatemachineService statemachineService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        Map<String, Object> context = new HashMap<>(16);
        context.put("context", "some code");
        System.out.println("执行第1次 PUSH");
        statemachineService.execute(1, TurnstileEvents.PUSH, context);
        System.out.println("执行第2次 PUSH");
        statemachineService.execute(1, TurnstileEvents.PUSH, context);
        System.out.println("执行第3次 COIN");
        statemachineService.execute(1, TurnstileEvents.COIN, context);
        System.out.println("执行第4次 COIN");
        statemachineService.execute(1, TurnstileEvents.COIN, context);

    }

}