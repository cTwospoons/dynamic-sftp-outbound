package com.twospoons.example.sftp.dynamic.config

import com.twospoons.example.sftp.dynamic.session.RuntimeSessionFactoryLocator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.channel.DirectChannel
import org.springframework.integration.config.EnableIntegration
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlows
import org.springframework.integration.dsl.sftp.Sftp
import org.springframework.integration.file.remote.session.DelegatingSessionFactory
import org.springframework.messaging.MessageChannel

/**
 * Created by ctwospoons on 12/10/17.
 */
@EnableIntegration
@Configuration
class SftpConfiguration {

    @Bean
    DelegatingSessionFactory delegatingSessionFactory(RuntimeSessionFactoryLocator runtimeSessionFactoryLocator){
        new DelegatingSessionFactory(runtimeSessionFactoryLocator)
    }

    @Bean
    MessageChannel outboundSftpChannel(){
        new DirectChannel()
    }

    @Bean
    IntegrationFlow sftpOutboundFlow(DelegatingSessionFactory delegatingSessionFactory){
        IntegrationFlows.from('outboundSftpChannel')
            .handle(Sftp.outboundAdapter(delegatingSessionFactory)
            .remoteDirectory('/tmp/' ))
            .get()
    }

}
