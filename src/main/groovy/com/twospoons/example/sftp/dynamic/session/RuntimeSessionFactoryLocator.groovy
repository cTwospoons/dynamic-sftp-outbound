package com.twospoons.example.sftp.dynamic.session

import com.twospoons.example.sftp.dynamic.repository.SftpSettingsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.integration.file.remote.session.SessionFactory
import org.springframework.integration.file.remote.session.SessionFactoryLocator
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory
import org.springframework.stereotype.Component

/**
 * Created by ctwospoons on 12/10/17.
 */
@Component
class RuntimeSessionFactoryLocator implements SessionFactoryLocator {

    private final Map<Object, DefaultSftpSessionFactory> sessionFactoryMap = [:]

    @Autowired
    SftpSettingsRepository sftpSettingsRepository

    @Override
    SessionFactory getSessionFactory(Object key) {
        def sessionFactory = sessionFactoryMap[key]

        if (!sessionFactory){
            sessionFactory = generateSessionFactory(key as Long)
            sessionFactoryMap[key as Long] = sessionFactory
        }

        sessionFactory
    }

    private DefaultSftpSessionFactory generateSessionFactory(Long key){
        def sftpConfiguration = sftpSettingsRepository.findById(key)

        if (!sftpConfiguration){
            throw new Exception("Unable to find sftp configuration for $key")
        }
        new DefaultSftpSessionFactory(
                host: sftpConfiguration.host,
                port: sftpConfiguration.port,
                user: sftpConfiguration.username,
                password: sftpConfiguration.password,
                allowUnknownKeys: true
        )
    }

    public invalidateSessionFactoryMap(){
        sessionFactoryMap.clear()
    }

    public remove(Object key){
        sessionFactoryMap.remove(key)
    }
}
