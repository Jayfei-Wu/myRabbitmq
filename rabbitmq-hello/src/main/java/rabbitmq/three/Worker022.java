package rabbitmq.three;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import rabbitmq.utils.RabbitMqUtils;

/**
 * @PROJECT_NAME: myRabbitmq
 * @PACKAGE_NAME: rabbitmq.three
 * @FILE_NAME: worker021
 * @Author: Jayfei-Wu
 * @create: 2023-03-07 20:15
 * @DESCRIPTION: 消息在手动应答时不丢失，放回队列重新消费
 */
public class Worker022 {

    /** 队列名称 */
    public static final String TASK_QUEUE_NAME = "ack_durable_queue";

    /** 接收消息 */
    public static void main(String[] args) throws Exception{


        Channel channel = RabbitMqUtils.getChannel();
        System.out.println("C2 等待接收消息处理 时间较短");

        DeliverCallback deliverCallback = (consumerTag,message) -> {
            //沉睡 1S
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("接收到的消息：" + new String(message.getBody(),"UTF-8"));

            /** 手动应答
             *  1.消息的标记 tag
             *  2.是否批量应答
             */
            channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
        };

        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println(consumerTag + "消费者取消消费接口回调");
        };

        //设置不公平分发
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);
        //采用手动应答
        boolean autoAck = false;
        channel.basicConsume(TASK_QUEUE_NAME,autoAck,deliverCallback,cancelCallback);

    }
}
