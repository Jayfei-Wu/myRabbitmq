package rabbitmq.five;

import com.rabbitmq.client.Channel;
import rabbitmq.utils.RabbitMqUtils;

import java.util.Scanner;

/**
 * @PROJECT_NAME: myRabbitmq
 * @PACKAGE_NAME: rabbitmq.five
 * @FILE_NAME: EmitLog
 * @Author: Jayfei-Wu
 * @create: 2023-03-09 0:46
 * @DESCRIPTION: 生产者 负责发消息给交换机
 */
public class EmitLog {

    /** 交换机的名称 */
    public static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws Exception {

        Channel channel = RabbitMqUtils.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String message = scanner.next();
            channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes("UTF-8"));
            System.out.println("生产者发出消息：" + message);
        }
    }

}
