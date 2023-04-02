package com.example.invitation.domain.card.template.item

import com.example.invitation.domain.invitation.detail.InvitationDetailType
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
class CardTemplateItem(
    @Id
    @GeneratedValue(generator = "SnowflakeIdGenerator")
    @GenericGenerator(
        name = "SnowflakeIdGenerator",
        strategy = "com.example.invitation.infrastructure.SnowflakeIdGenerator",
    )
    val cardTemplateItemId: Long = 0L,
    /**
     * 초대 컨셉, 분위기
     */
    @ManyToOne
    @JoinColumn(name = "invitationDetailTypeId")
    val invitationDetailType: InvitationDetailType,
    /**
     * 이미지 주소
     */
    val imageUrl: String,
    /**
     * 이름
     */
    val name: String,
    /**
     * 이모지
     */
    val emoji: String = "🔫",
    /**
     * 원점(top-left)으로부터 x 좌표 (가로, 오른쪽이 +)
     */
    val x: Int,
    /**
     * 원점(top-left)으로부터 y 좌표 (세로, 아래쪽이 +)
     */
    val y: Int,
    /**
     * 높이
     */
    val width: Int = 360,
    /**
     * 너비
     */
    val height: Int = 360,
    var deleted: Boolean = false,
) {
    @CreatedDate
    lateinit var createdAt: LocalDateTime

    @LastModifiedDate
    lateinit var updatedAt: LocalDateTime
}
