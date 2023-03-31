package com.example.invitation.domain.card

import com.example.invitation.domain.card.template.CardTemplate
import com.example.invitation.domain.card.template.item.CardTemplateItem
import com.example.invitation.domain.invitation.Invitation
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
class Card(
    @Id
    @GeneratedValue
    val cardId: Long = 0L,
    @OneToOne
    @JoinColumn(name = "invitationId")
    val invitation: Invitation,
    var imageUrl: String? = null,
    @ManyToOne
    val cardTemplate: CardTemplate,
    @ManyToMany
    val cardTemplateItems: List<CardTemplateItem>,
) {
    @CreatedDate
    lateinit var createdAt: LocalDateTime

    @LastModifiedDate
    lateinit var updatedAt: LocalDateTime

    fun update(imageUrl: String) {
        this.imageUrl = imageUrl
    }
}
