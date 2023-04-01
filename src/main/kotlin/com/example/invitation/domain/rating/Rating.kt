package com.example.invitation.domain.rating

import com.example.invitation.domain.invitation.Invitation
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
class Rating(
    @Id
    @GeneratedValue(generator = "SnowflakeIdGenerator")
    @GenericGenerator(
        name = "SnowflakeIdGenerator",
        strategy = "com.example.invitation.infrastructure.SnowflakeIdGenerator",
    )
    val ratingId: Long = 0L,
    val ratingType: RatingType,
    @ManyToOne
    @JoinColumn(name = "invitationId")
    val invitation: Invitation?
) {
    @CreatedDate
    lateinit var createdAt: LocalDateTime

    @LastModifiedDate
    lateinit var updatedAt: LocalDateTime

    companion object {
        fun of(
            ratingType: RatingType,
            invitation: Invitation?,
        ): Rating {
            return Rating(
                ratingType = ratingType,
                invitation = invitation,
            )
        }
    }
}
