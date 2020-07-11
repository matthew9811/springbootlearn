package com.shengxi.springboot.rocketmq.transaction;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author yan
 * @see org.apache.rocketmq.client.producer.TransactionListener
 */
public class TransactionListenerImpl implements TransactionListener {

    /**
     * 存储事务状态信息
     */
    private Map<String, Integer> localTrans = new ConcurrentHashMap();

    /**
     * 执行本地事务
     *
     * @param msg
     * @param arg
     * @return
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        String transactionId = msg.getTransactionId();
        localTrans.put(transactionId, 0);
        //本地操作类似service
        System.out.println("hello local");

        try {
            System.out.println("task ---------------------");
            Thread.sleep(12000);
            System.out.println("task ---------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
            localTrans.put(transactionId, 1);
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }

        return LocalTransactionState.COMMIT_MESSAGE;
    }

    /**
     * 消息回查
     *
     * @param msg
     * @return
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        String transactionId = msg.getTransactionId();
        Integer code = localTrans.get(transactionId);
        switch (code) {
            case 0:
                return LocalTransactionState.COMMIT_MESSAGE;

            case 1:
                return LocalTransactionState.UNKNOW;

            default:
                return LocalTransactionState.ROLLBACK_MESSAGE;
        }

    }
}
