package com.twospoons.example.sftp.dynamic.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 * Created by ctwospoons on 12/10/17.
 */
@Entity
@Table(name='SFTP_SETTINGS')
class SftpSettings {

    @Id
    @Column(name = 'ID', nullable = false)
    Long id

    @Column(name = 'HOST', nullable = false)
    String host

    @Column(name = 'PORT', nullable = false)
    Integer port

    @Column(name = 'USERNAME', nullable = false)
    String username

    @Column(name = 'PASSWORD', nullable = false)
    String password
}
