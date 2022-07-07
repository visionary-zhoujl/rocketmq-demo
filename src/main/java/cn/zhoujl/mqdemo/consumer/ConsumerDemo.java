package cn.zhoujl.mqdemo.consumer;


import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Slf4j
@RocketMQMessageListener(consumerGroup = "consumer-demo",topic = "testTopic",selectorExpression = "tag1")
public class ConsumerDemo implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {
    @Override
    public void onMessage(String msg) {
        log.info("监听到消息: message:{}",msg);
    }

//    @Override
//    public void onMessage(List<MessageExt> msgs) {
//        log.info("本次处理 {} 条消息",msgs.size());
//
//        msgs.forEach(item->{
//            log.info("监听到消息 {} ",item.getBody());
//        });
//    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        defaultMQPushConsumer.setConsumeThreadMin(10);
        defaultMQPushConsumer.setConsumeThreadMax(10);
    }
}
