package com.twospoons.example.sftp.dynamic

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan

/**
 * Created by ctwospoons on 12/10/17.
 */
@SpringBootApplication
@EntityScan(basePackages = ['com.twospoons.example.sftp.dynamic.model'])
class SftpExampleApplication {

    public static void main(String... args){
        SpringApplication.run(SftpExampleApplication, args)
    }
}
