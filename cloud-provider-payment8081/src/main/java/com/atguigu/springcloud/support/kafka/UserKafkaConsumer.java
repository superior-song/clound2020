package com.atguigu.springcloud.support.kafka;


import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;


public class UserKafkaConsumer extends Thread {

	public static void main(String[] args){
		Properties properties = new Properties();
		properties.put("bootstrap.servers", "22.86.116.41:9092");//xxx是服务器集群的ip
		properties.put("group.id", "jd-group");
		properties.put("enable.auto.commit", "true");
		properties.put("auto.commit.interval.ms", "1000");
		properties.put("auto.offset.reset", "latest");
		properties.put("session.timeout.ms", "30000");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
		kafkaConsumer.subscribe(Arrays.asList("kafka2tab"));
		while (true) {
			ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				System.out.println("-----------------");
				System.out.printf("offset = %d, value = %s", record.offset(), record.value());
				System.out.println();
			}
		}

	}
}