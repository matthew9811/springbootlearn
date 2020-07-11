package com.shengxi.springboot.rocketmq.transaction;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * 快速入门
 */
public class Producer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException, UnsupportedEncodingException {
        TransactionMQProducer producer = new TransactionMQProducer("demo_transaction");
        producer.setNamesrvAddr("106.52.247.35:9876");
        producer.setCreateTopicKey("AUTO_CREATE_TOPIC_KEY");
//        producer指定消息监听和回查
        producer.setTransactionListener(new TransactionListenerImpl());
        ExecutorService executorService = new ThreadPoolExecutor(2,
                5, 100,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2000), r -> {
            Thread thread = new Thread(r);
            thread.setName("client_transaction-check_thread");
            return thread;
        });

        producer.setExecutorService(executorService);
        producer.start();
        Message msg = new Message("transaction_topic",
                "demo_tags",
                "key_T",
                new String("liangyanhao").getBytes(RemotingHelper.DEFAULT_CHARSET));
        TransactionSendResult transactionSendResult = producer.sendMessageInTransaction(msg, "hello_transaction");

        System.out.println(transactionSendResult);

        producer.shutdown();


    }
}
