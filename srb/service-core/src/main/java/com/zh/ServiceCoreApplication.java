package com.zh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class ServiceCoreApplication
{

    public static void main( String[] args )
    {
        SpringApplication.run(ServiceCoreApplication.class, args);
        System.out.println( "Hello World!" );
    }
}
