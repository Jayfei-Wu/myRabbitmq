package rabbitmq.seven;

import com.rabbitmq.client.Channel;
import rabbitmq.utils.RabbitMqUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @PROJECT_NAME: myRabbitmq
 * @PACKAGE_NAME: rabbitmq.seven
 * @FILE_NAME: EmitLogTopic
 * @Author: Jayfei-Wu
 * @create: 2023-03-09 5:54
 * @DESCRIPTION: 生产者
 */
public class EmitLogTopic {

    /** 交换机名称 */
    public static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws Exception {

        Channel channel = RabbitMqUtils.getChannel();

        Map<String,String> bindingKeyMap = new HashMap<>();
        bindingKeyMap.put("quick.orange.rabbit","被队列 Q1Q2接收到");
        bindingKeyMap.put("lazy.orange.elephant","被队列Q1Q2接收到");
        bindingKeyMap.put("quick.orange.fox","被队列Q1接收到");
        bindingKeyMap.put("lazy.brown.fox","被队列Q2接收到");
        bindingKeyMap.put("lazy.pink.rabbit","虽然满足两个绑定 但只被队列Q2接收一次");
        bindingKeyMap.put("quick.brown.fox","不匹配任何绑定，不会被任何队列接收到，会被丢弃");
        bindingKeyMap.put("quick.orange.male.rabbit","是四个单词，不匹配任何绑定，会被丢弃");
        bindingKeyMap.put("lazy.orange.male.rabbit","是四个单词，但是匹配Q2");

        for (Map.Entry<String, String> bindingKeyEntry : bindingKeyMap.entrySet()) {
            String routingKey = bindingKeyEntry.getKey();
            String message = bindingKeyEntry.getValue();
            channel.basicPublish(EXCHANGE_NAME,routingKey,null,message.getBytes("UTF-8"));
            System.out.println("生产者发出消息：" + message);
        }
    }
}
