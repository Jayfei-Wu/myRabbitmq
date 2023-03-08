package rabbitmq.seven;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import rabbitmq.utils.RabbitMqUtils;

/**
 * @PROJECT_NAME: myRabbitmq
 * @PACKAGE_NAME: rabbitmq.seven
 * @FILE_NAME: ReceiveLogsTopic02
 * @Author: Jayfei-Wu
 * @create: 2023-03-09 5:51
 * @DESCRIPTION: 消费者 C2
 */
public class ReceiveLogsTopic02 {

    /** 交换机名称 */
    public static final String EXCHANGE_NAME = "topic_logs";

    /** 接收消息 */
    public static void main(String[] args) throws Exception {

        Channel channel = RabbitMqUtils.getChannel();

        // 声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        // 声明队列
        String queueName = "Q2";
        channel.queueDeclare(queueName,false,false,false,null);
        channel.queueBind(queueName,EXCHANGE_NAME,"*.*.rabbit");
        channel.queueBind(queueName,EXCHANGE_NAME,"lazy.#");

        System.out.println("等待接收消息... ... ...");

        // 消费者接收消息回调
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println(new String(message.getBody(),"UTF-8"));
            System.out.println("接收队列： " + queueName + " 绑定键： " + message.getEnvelope().getRoutingKey());
        };

        // 消费者取消消息回调
        CancelCallback cancelCallback = (consumerTag) -> {};
        // 接收消息
        channel.basicConsume(queueName,true,deliverCallback,cancelCallback);

    }

}
