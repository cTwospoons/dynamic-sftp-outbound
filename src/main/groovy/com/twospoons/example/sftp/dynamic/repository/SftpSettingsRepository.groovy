package com.twospoons.example.sftp.dynamic.repository

import com.twospoons.example.sftp.dynamic.model.SftpSettings
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by ctwospoons on 12/10/17.
 */
interface SftpSettingsRepository extends JpaRepository<SftpSettings, Long> {

    SftpSettings findById(Long id)

}
