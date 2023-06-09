package com.example.invitation.domain.card

import com.example.invitation.domain.card.template.CardTemplate
import com.example.invitation.domain.card.template.item.CardTemplateItem
import com.example.invitation.domain.file.File
import com.example.invitation.domain.invitation.Invitation
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
class Card(
    @Id
    @GeneratedValue(generator = "SnowflakeIdGenerator")
    @GenericGenerator(
        name = "SnowflakeIdGenerator",
        strategy = "com.example.invitation.infrastructure.SnowflakeIdGenerator",
    )
    val cardId: Long = 0L,
    @OneToOne
    @JoinColumn(name = "invitationId")
    val invitation: Invitation,
    @ManyToOne
    @JoinColumn(name = "fileId")
    var file: File? = null,
    @ManyToOne
    @JoinColumn(name = "cardTemplateId")
    val cardTemplate: CardTemplate,
    @ManyToMany
    @JoinTable(
        name = "card_template_item_map",
        joinColumns = [JoinColumn(name = "cardId")],
        inverseJoinColumns = [JoinColumn(name = "cardTemplateItemId")]
    )
    val cardTemplateItems: List<CardTemplateItem>,
) {
    @CreatedDate
    lateinit var createdAt: LocalDateTime

    @LastModifiedDate
    lateinit var updatedAt: LocalDateTime

    fun update(file: File) {
        this.file = file
    }
}
