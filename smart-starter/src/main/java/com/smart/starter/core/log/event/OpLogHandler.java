package com.smart.starter.core.log.event;

import com.rabbitmq.client.Channel;
import com.smart.starter.core.log.ConstantsLog;
import com.smart.starter.core.log.OpLogParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.io.IOException;

/**
 * @author guwenchang
 * @date 2019-05-16 15:59
 */
@Slf4j
public class OpLogHandler {

    @RabbitListener(queues = {ConstantsLog.OP_LOG_QUEUE})
    public void listenerAutoAck(OpLogParam opLogParam, Message message, Channel channel) {
        // TODO 如果手动ACK,消息会被监听消费,但是消息在队列中依旧存在,如果 未配置 acknowledge-mode 默认是会在消费完毕后自动ACK掉
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            log.info("[listenerAutoAck 监听的消息] - [{}]", opLogParam.toString());
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            try {
                log.error("[listenerAutoAck 监听的消息-处理失败] - [{}]", opLogParam.toString());
                channel.basicRecover();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
