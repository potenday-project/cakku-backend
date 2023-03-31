package com.example.invitation.domain.invitation.detail

import com.example.invitation.domain.invitation.InvitationType
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
class InvitationDetailType(
    @Id
    @GeneratedValue
    val invitationDetailTypeId: Long = 0L,
    @Enumerated(EnumType.STRING)
    val invitationType: InvitationType,
    val name: String,
    val emoji: String,
    val description: String,
) {
    @CreatedDate
    lateinit var createdAt: LocalDateTime

    @LastModifiedDate
    lateinit var updatedAt: LocalDateTime
}
