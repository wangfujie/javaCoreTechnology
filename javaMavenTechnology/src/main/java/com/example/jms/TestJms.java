package com.example.jms;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

/**
 * @author wangfj
 * @date 2020/7/29
 */
public class TestJms {
    private static final String PROVIDER_URL = "tcp://10.10.22.81:61616";

    private static final String CONTEXT_FACTORY_CLASS_NAME = "org.apache.activemq.jndi.ActiveMQInitialContextFactory";

    private static final String CONTEXT_FACTORY_JNID_NAME = "ConnectionFactory";

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    private static final String TEST_QUEUE = "testQueue";

    public static void main(String[] args) throws Exception{
        //1、通过Content获取连接
        Connection connection = createConnection();
        //2、开启连接
        connection.start();
        //3、获取消息队列的目的地
        Destination destination = getDestination("dynamicQueues/" + TEST_QUEUE);
        //4、获取会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5、创建消息的生产者对象
        MessageProducer producer = session.createProducer(destination);
        //6、创建消息内容
        TextMessage textMessage = session.createTextMessage("message");
        //7、发送消息,指定发布到哪个队列
        producer.send(destination,textMessage);
        //8、关闭资源
        producer.close();
        session.close();
        connection.close();
    }

    public static Context getContext() throws Exception{
        Properties prop = new Properties();
        prop.put(Context.INITIAL_CONTEXT_FACTORY, CONTEXT_FACTORY_CLASS_NAME);
        prop.put(Context.PROVIDER_URL, PROVIDER_URL);
        return new InitialContext(prop);
    }

    /**
     * 创建ConnectionFactory
     * @return
     * @throws Exception
     */
    public static ConnectionFactory getConnectionFactory() throws Exception{
        return (ConnectionFactory)getContext().lookup(CONTEXT_FACTORY_JNID_NAME);
    }

    /**
     * 创建Connection
     * @return
     * @throws Exception
     */
    public static Connection createConnection() throws Exception{
        Connection connection;
        ConnectionFactory cf = getConnectionFactory();
        if (USERNAME != null && !"".equals(USERNAME)) {
            connection =  cf.createConnection(USERNAME, PASSWORD);
        } else {
            connection = cf.createConnection();
        }
        return connection;
    }

    /**
     * 使用JNDI获得一个Destination
     * @param destName
     * @return
     * @throws Exception
     */
    public static Destination getDestination(String destName) throws Exception{
        return (Destination) getContext().lookup(destName);
    }

}
