package rabbitmq.three;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import rabbitmq.utils.RabbitMqUtils;

import java.util.Scanner;

/**
 * @PROJECT_NAME: myRabbitmq
 * @PACKAGE_NAME: rabbitmq.three
 * @FILE_NAME: Task02
 * @Author: Jayfei-Wu
 * @create: 2023-03-07 20:08
 * @DESCRIPTION: 消息在手动应答时不丢失，放回队列中重新消费
 */
public class Task02 {

    /** 队列名称 */
    public static final String TASK_QUEUE_NAME = "ack_durable_queue";

    public static void main(String[] args) throws Exception {

        //获取信道
        Channel channel = RabbitMqUtils.getChannel();

        //开启发布确认
        channel.confirmSelect();

        //声明队列持久化
        boolean durable = true;

        //声明队列
        channel.queueDeclare(TASK_QUEUE_NAME,durable,false,false,null);

        //从控制台中输入信息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String message = scanner.next();

            //设置生产者发布消息为持久化
            channel.basicPublish("",TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes("UTF-8"));
            System.out.println("生产者发出消息：" + message);
        }
    }
}
