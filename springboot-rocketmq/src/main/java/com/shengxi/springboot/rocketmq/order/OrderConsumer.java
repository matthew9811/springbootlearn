package com.shengxi.springboot.rocketmq.order;


import java.io.UnsupportedEncodingException;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class OrderConsumer {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("demo_consumer_group");
        consumer.setNamesrvAddr("106.52.247.35:9876");
        consumer.setConsumeMessageBatchMaxSize(3);
        consumer.subscribe("demo_topic", "*");
        consumer.setMessageListener((MessageListenerOrderly) (msgs, context) -> {
            for (MessageExt msg : msgs) {
                String topic = msg.getTopic();
                String msgId = msg.getMsgId();
                try {
                    String result = new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET);
                    System.out.println(topic + " : " + msgId + " : " + result);
                    System.out.println("-----------------------------------");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                }
            }
            return ConsumeOrderlyStatus.SUCCESS;
        });
        consumer.start();
        System.out.printf("Consumer Started.%n");
    }
}
