package com.shengxi.springboot.rocketmq.quickstart;

import java.io.UnsupportedEncodingException;
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
        DefaultMQProducer producer = new DefaultMQProducer("demo-yan");
        producer.setNamesrvAddr("106.52.247.35:9876");
        producer.setCreateTopicKey("AUTO_CREATE_TOPIC_KEY");
        producer.start();

        Message msg = new Message("demo_topic",
                "demo_tags",
                "key_1",
                "hello".getBytes(RemotingHelper.DEFAULT_CHARSET));

        SendResult result = producer.send(msg);

        System.out.println(result);

        producer.shutdown();


    }
}
