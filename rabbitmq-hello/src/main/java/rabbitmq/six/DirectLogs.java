package rabbitmq.six;

import com.rabbitmq.client.Channel;
import rabbitmq.utils.RabbitMqUtils;

import java.util.Scanner;

/**
 * @PROJECT_NAME: myRabbitmq
 * @PACKAGE_NAME: rabbitmq.six
 * @FILE_NAME: DirectLogs
 * @Author: Jayfei-Wu
 * @create: 2023-03-09 4:15
 * @DESCRIPTION: TODO
 */
public class DirectLogs {

    /** 交换机的名称 */
    public static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws Exception {

        Channel channel = RabbitMqUtils.getChannel();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String message = scanner.next();
            channel.basicPublish(EXCHANGE_NAME,"info",null,message.getBytes("UTF-8"));
            channel.basicPublish(EXCHANGE_NAME,"warning",null,message.getBytes("UTF-8"));
            channel.basicPublish(EXCHANGE_NAME,"error",null,message.getBytes("UTF-8"));
            System.out.println("生产者发出消息：" + message);
        }
    }

}
