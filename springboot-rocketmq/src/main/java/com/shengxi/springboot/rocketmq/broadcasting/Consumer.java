package com.shengxi.springboot.rocketmq.broadcasting;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

public class Consumer {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("broadcasting");
        consumer.setNamesrvAddr("106.52.247.35:9876");
        consumer.setConsumeMessageBatchMaxSize(3);
        consumer.subscribe("liangyanhao", "*");
        consumer.setMessageModel(MessageModel.BROADCASTING);
        consumer.setMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            System.out.println(msgs);
            System.out.println("------------");
            System.out.println(context);
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
        System.out.printf("Consumer Started.%n");
    }
}
