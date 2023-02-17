package com.zzk.study;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
*
* @EnableBinding，该注解用来指定一个或多个定义了@Input或@Output注解的接口，
 * 以此实现对消息通道（Channel）的绑定。在上面的例子中，我们通过@EnableBinding(Sink.class)绑定了Sink接口，
 * 该接口是Spring Cloud Stream中默认实现的对输入消息通道绑定的定义
*/
@EnableBinding(Sink.class)
public class SinkReceiver {

    private static Logger logger = LoggerFactory.getLogger(SinkReceiver.class);

    @StreamListener(Sink.INPUT)
    public void receive(Object payload) {
        logger.info("Received: " + payload);
    }

}