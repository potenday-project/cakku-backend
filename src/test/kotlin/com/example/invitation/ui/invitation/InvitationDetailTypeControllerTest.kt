package com.example.invitation.ui.invitation

import com.example.invitation.domain.card.template.CardTemplate
import com.example.invitation.domain.card.template.CardTemplateRepository
import com.example.invitation.domain.invitation.InvitationType
import com.example.invitation.domain.invitation.detail.InvitationDetailType
import com.example.invitation.domain.invitation.detail.InvitationDetailTypeRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class InvitationDetailTypeControllerTest {
    @Autowired
    lateinit var invitationDetailTypeRepository: InvitationDetailTypeRepository

    @Autowired
    lateinit var cardTemplateRepository: CardTemplateRepository

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun getInvitationDetailTypes() {
        val cardTemplate = cardTemplateRepository.save(
            CardTemplate(
                invitationType = InvitationType.CASUAL,
                name = "카드 템플릿",
                imageUrl = "imageUrl",
            )
        )
        invitationDetailTypeRepository.saveAll(
            listOf(
                InvitationDetailType(
                    cardTemplate = cardTemplate,
                    invitationType = InvitationType.CASUAL,
                    name = "밥약속",
                    emoji = "🍚",
                    description = "",
                ),
                InvitationDetailType(
                    cardTemplate = cardTemplate,
                    invitationType = InvitationType.CASUAL,
                    name = "술약속",
                    emoji = "🍺",
                    description = "",
                ),
                InvitationDetailType(
                    cardTemplate = cardTemplate,
                    invitationType = InvitationType.CASUAL,
                    name = "소풍 & 여행",
                    emoji = "🎒",
                    description = "",
                ),
                InvitationDetailType(
                    cardTemplate = cardTemplate,
                    invitationType = InvitationType.CASUAL,
                    name = "기타",
                    emoji = "🪄",
                    description = "",
                )
            )
        )
        mockMvc.perform(get("/api/v1/invitation-detail-types"))
            .andExpect(status().isOk)
    }
}
