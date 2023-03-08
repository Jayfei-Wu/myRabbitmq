package rabbitmq.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @PROJECT_NAME: myRabbitmq
 * @PACKAGE_NAME: rabbitmq.utils
 * @FILE_NAME: RabbitMqUtils
 * @Author: Jayfei-Wu
 * @create: 2023-03-07 0:21
 * @DESCRIPTION: 此类为连接工厂创建信道的工具类
 */
public class RabbitMqUtils {

    /** 得到一个连接的channel */
    public static Channel getChannel() throws Exception {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.118.129");
        factory.setUsername("admin");
        factory.setPassword("admin");

        //建立连接
        Connection connection = factory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();

        return channel;
    }
}
