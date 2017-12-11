package com.twospoons.example.sftp.dynamic.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.integration.file.remote.session.DelegatingSessionFactory
import org.springframework.integration.support.MessageBuilder
import org.springframework.messaging.MessageChannel
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * Created by ctwospoons on 12/10/17.
 */
@RestController
class SftpTestController {

    @Autowired
    DelegatingSessionFactory delegatingSessionFactory

    @Autowired
    MessageChannel outboundSftpChannel


    @RequestMapping(value = '/test/{threadKey}')
    def testEndpoint(
            @PathVariable('threadKey') Long threadKey
    ){
        delegatingSessionFactory.setThreadKey(threadKey)

        def message = MessageBuilder.withPayload('test')

        outboundSftpChannel.send(message.build())

    }

    @RequestMapping(method = RequestMethod.DELETE, value = '/test/{threadKey}')
    def deleteThreadKeyEndpoint(
            @PathVariable('threadKey') Long threadKey
    ){
        delegatingSessionFactory.factoryLocator.remove(threadKey)

    }

    @RequestMapping(method = RequestMethod.GET ,value = '/test/clear')
    def invalidateSftpLocator(){
        delegatingSessionFactory.factoryLocator.invalidateSessionFactoryMap()
    }
}
