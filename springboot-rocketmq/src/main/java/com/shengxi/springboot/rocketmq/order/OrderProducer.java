package com.shengxi.springboot.rocketmq.order;

import java.util.List;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class OrderProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("demo-yan");
        producer.setNamesrvAddr("106.52.247.35:9876");
        producer.setCreateTopicKey("AUTO_CREATE_TOPIC_KEY");
        producer.start();


        Message msg = new Message("demo_topic",
                "demo_tags",
                "key_1",
                "hello".getBytes(RemotingHelper.DEFAULT_CHARSET));

        //arg是队列的下标
        for (int i = 0; i < 5; i++) {
            SendResult result = producer.send(msg, (mqs, msg1, arg) -> mqs.get((Integer) arg), 1);

            System.out.println(result);
        }

        producer.shutdown();


    }
}
