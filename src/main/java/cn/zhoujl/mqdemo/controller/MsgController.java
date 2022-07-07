package cn.zhoujl.mqdemo.controller;


import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MsgController {
    @Autowired
    RocketMQTemplate rocketMQTemplate;

    @GetMapping("/sendMsg")
    public String sendMsg(String msg){

        Message<String> message = MessageBuilder.withPayload(msg).build();

        int i = 0;
        while (i<1000){
            rocketMQTemplate.send("testTopic:tag1",message);
            i++;
        }



        return "success";

    }

}
