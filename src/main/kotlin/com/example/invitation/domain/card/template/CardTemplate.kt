package com.example.invitation.domain.card.template

import com.example.invitation.domain.card.template.item.CardTemplateItem
import com.example.invitation.domain.invitation.InvitationType
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
class CardTemplate(
    @Id
    @GeneratedValue
    val cardTemplateId: Long = 0L,

    @OneToMany
    @JoinColumn(name = "cardTemplateId")
    val cardTemplateItems: List<CardTemplateItem> = emptyList(),
    /**
     * 초대 목적
     */
    @Enumerated(EnumType.STRING)
    val invitationType: InvitationType,
    /**
     * 이미지 주소
     */
    val imageUrl: String,
    /**
     * 이름
     */
    val name: String,
    /**
     * 너비
     */
    val width: Int = 1080,
    /**
     * 높이
     */
    val height: Int = 1080,
) {
    @CreatedDate
    lateinit var createdAt: LocalDateTime

    @LastModifiedDate
    lateinit var updatedAt: LocalDateTime
}
