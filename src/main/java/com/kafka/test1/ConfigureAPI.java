package com.kafka.test1;

/**
 * @Date Apr 28, 2015
 *
 * @Author dengjie
 *
 * @Note Set param path
 */
public class ConfigureAPI {

    public interface KafkaProperties {
        public final static String ZK = "192.168.128.128:2181";
        public final static String GROUP_ID = "test_group1";
        public final static String TOPIC = "test2";
        public final static String BROKER_LIST = "192.168.128.128:9092";
        public final static int BUFFER_SIZE = 64 * 1024;
        public final static int TIMEOUT = 20000;
        public final static int INTERVAL = 10000;
    }
    
    public static void main(String[] args) {
        JProducer pro = new JProducer(KafkaProperties.TOPIC);
        pro.start();

        JConsumer con = new JConsumer(KafkaProperties.TOPIC);
        con.start();
    }

}
