package com.shengxi.springboot.rocketmq.broadcasting;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * 快速入门
 */
public class Producer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException, UnsupportedEncodingException {
        DefaultMQProducer producer = new DefaultMQProducer("broadcasting");
        producer.setNamesrvAddr("106.52.247.35:9876");
        producer.setCreateTopicKey("AUTO_CREATE_TOPIC_KEY");
        producer.start();
        List<Message> messageList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Message msg = new Message("liangyanhao",
                    "demo_tags",
                    "key" + i,
                    ("hello" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            messageList.add(msg);
        }
        SendResult result = producer.send(messageList);

        System.out.println(result);

        producer.shutdown();


    }
}
