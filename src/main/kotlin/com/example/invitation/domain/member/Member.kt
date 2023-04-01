package com.example.invitation.domain.member

import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
@EntityListeners(AuditingEntityListener::class)
class Member(
    @Id
    @GeneratedValue(generator = "SnowflakeIdGenerator")
    @GenericGenerator(
        name = "SnowflakeIdGenerator",
        strategy = "com.example.invitation.infrastructure.SnowflakeIdGenerator",
    )
    val memberId: Long = 0L,
    val providerType: ProviderType,
    val providerUserId: String,
) {
    @CreatedDate
    lateinit var createdAt: LocalDateTime

    @LastModifiedDate
    lateinit var updatedAt: LocalDateTime

    companion object {
        fun anonymous(providerUserId: String): Member {
            return Member(
                providerType = ProviderType.ANONYMOUS,
                providerUserId = providerUserId,
            )
        }
    }
}
