package rabbitmq.six;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import rabbitmq.utils.RabbitMqUtils;

/**
 * @PROJECT_NAME: myRabbitmq
 * @PACKAGE_NAME: rabbitmq.six
 * @FILE_NAME: ReceiveLogsDirect02
 * @Author: Jayfei-Wu
 * @create: 2023-03-09 4:13
 * @DESCRIPTION: TODO
 */
public class ReceiveLogsDirect02 {

    /** 交换机名字 */
    public static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws  Exception {

        Channel channel = RabbitMqUtils.getChannel();

        // 声明一个交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        // 声明一个队列
        channel.queueDeclare("disk",false,false,false,null);

        channel.queueBind("disk",EXCHANGE_NAME,"error");

        // 消费者接收消息回调
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("ReceiveLogsDirect02控制台打印接收到的消息：" + new String(message.getBody(),"UTF-8"));
        };
        // 消费者取消消息回调
        CancelCallback cancelCallback = (consumerTag) -> {

        };

        channel.basicConsume("disk",true,deliverCallback,cancelCallback);


    }
}
