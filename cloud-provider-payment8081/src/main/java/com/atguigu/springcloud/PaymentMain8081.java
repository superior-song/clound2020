package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Created by Administrator on 2020/4/20 0020.
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.atguigu.springcloud.repository")
public class PaymentMain8081 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8081.class);
    }
}
