package rabbitmq.two;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import rabbitmq.utils.RabbitMqUtils;

/**
 * @PROJECT_NAME: myRabbitmq
 * @PACKAGE_NAME: rabbitmq.two
 * @FILE_NAME: Worker01
 * @Author: Jayfei-Wu
 * @create: 2023-03-07 0:25
 * @DESCRIPTION: 这是一个工作线程 （相当于之前的消费者）
 */
public class Worker01 {

    /** 队列的名称 */
    public static final String QUEUE_NAME = "hello";

    /** 接收消息 */
    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtils.getChannel();

        //声明 接收消息
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("接收到的消息：" + new String(message.getBody()));
        };

        // 取消消息时的回调
        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println(consumerTag + "消费者取消消费接口回调逻辑");
        };

        System.out.println("C1等待接收消息---------");
        //消息的接收
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);
    }
}
