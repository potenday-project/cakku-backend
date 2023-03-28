package com.example.invitation.domain.card.template

import com.example.invitation.domain.card.template.item.CardTemplateItem
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

    val name: String,

    val backgroundImageUrl: String,
) {
    @CreatedDate
    lateinit var createdAt: LocalDateTime

    @LastModifiedDate
    lateinit var updatedAt: LocalDateTime
}
