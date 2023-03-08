package rabbitmq.two;

import com.rabbitmq.client.Channel;
import rabbitmq.utils.RabbitMqUtils;

import java.util.Scanner;

/**
 * @PROJECT_NAME: myRabbitmq
 * @PACKAGE_NAME: rabbitmq.two
 * @FILE_NAME: Task01
 * @Author: Jayfei-Wu
 * @create: 2023-03-07 2:45
 * @DESCRIPTION: 生产者 可以发送大量的消息
 */
public class Task01 {

    /** 队列名称 */
    public static final String QUEEN_NAME = "hello";

    /** 发送大量消息 */
    public static void main(String[] args) throws Exception{

        Channel channel = RabbitMqUtils.getChannel();

        // 队列声明
        channel.queueDeclare(QUEEN_NAME,false,false,false,null);
        //从控制台当中接受信息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            channel.basicPublish("",QUEEN_NAME,null,message.getBytes());
            System.out.println("发送消息完成：" + message);
        }

    }
}
