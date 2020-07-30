package com.example.jms;

import org.apache.qpid.jms.JmsConnectionFactory;
import org.apache.qpid.jms.JmsSession;
import javax.jms.*;

/**
 * @author wangfj
 * @date 2020/7/30
 */
public class TestQpidJmsClient {

    private static final String PROVIDER_URL = "amqp://127.0.0.1:5672";
    private static final String PROVIDER_URL_TCP = "tcp://127.0.0.1:61616";

    private static final String CONTEXT_FACTORY_CLASS_NAME = "org.apache.qpid.jms.jndi.JmsInitialContextFactory";

    private static final String TEST_QUEUE = "qpidTestQueue";


    public static void main(String[] args) throws Exception {
        JmsConnectionFactory jmsConnectionFactory = new JmsConnectionFactory(PROVIDER_URL_TCP);
        //1、通过Content获取连接
        Connection connection = jmsConnectionFactory.createConnection();
        //2、开启连接
        connection.start();
        //3、获取消息队列的目的地
        Queue queue = jmsConnectionFactory.createContext().createQueue(TEST_QUEUE);
        //4、获取会话
        JmsSession session = (JmsSession) connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5、创建消息的生产者对象
        MessageProducer producer = session.createProducer(queue);
        //6、创建消息内容
        TextMessage textMessage = session.createTextMessage("test message");
        //7、发送消息,指定发布到哪个队列
        producer.send(queue,textMessage);
        //8、关闭资源
        producer.close();
        session.close();
        connection.close();
    }
}
