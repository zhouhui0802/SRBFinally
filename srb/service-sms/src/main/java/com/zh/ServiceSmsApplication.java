package com.zh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableFeignClients
public class ServiceSmsApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ServiceSmsApplication.class, args);
        System.out.println( "Hello World!" );
    }
}
