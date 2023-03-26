package com.example.invitation.domain.file

import org.springframework.data.jpa.repository.JpaRepository

interface FileRepository : JpaRepository<File, Long> {
}
