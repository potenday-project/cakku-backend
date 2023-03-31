package com.example.invitation.ui.invitation

import com.example.invitation.domain.card.template.CardTemplate
import com.example.invitation.domain.card.template.CardTemplateRepository
import com.example.invitation.domain.card.template.item.CardTemplateItem
import com.example.invitation.domain.card.template.item.CardTemplateItemRepository
import com.example.invitation.domain.invitation.InvitationDetailType
import com.example.invitation.domain.invitation.InvitationType
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class InvitationControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var cardTemplateRepository: CardTemplateRepository

    @Autowired
    lateinit var cardTemplateItemRepository: CardTemplateItemRepository

    @Test
    fun testGetInvitation() {
        mockMvc.perform(get("/api/v1/invitations/1"))
            .andExpect(status().isInternalServerError)
    }

    @Test
    fun testCreateInvitation() {
        // given
        val cardTemplateId = 1L
        val cardTemplate = CardTemplate(
            cardTemplateId = cardTemplateId,
            invitationType = InvitationType.CASUAL,
            name = "name",
            imageUrl = "imageUrl",
        )
        cardTemplateRepository.save(cardTemplate)
        cardTemplateItemRepository.saveAll(
            listOf(
                CardTemplateItem(
                    cardTemplateItemId = 1L,
                    invitationDetailType = InvitationDetailType.MEAL,
                    imageUrl = "imageUrl",
                    name = "name",
                    emoji = "emoji",
                    x = 0,
                    y = 0,
                ),
                CardTemplateItem(
                    cardTemplateItemId = 2L,
                    invitationDetailType = InvitationDetailType.MEAL,
                    imageUrl = "imageUrl",
                    name = "name",
                    emoji = "emoji",
                    x = 1,
                    y = 0,
                ),
                CardTemplateItem(
                    cardTemplateItemId = 3L,
                    invitationDetailType = InvitationDetailType.MEAL,
                    imageUrl = "imageUrl",
                    name = "name",
                    emoji = "emoji",
                    x = 2,
                    y = 0,
                ),
            )
        )
        // when
        mockMvc.perform(
            post("/api/v1/invitations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                        "hostName": "hostName",
                        "invitationType": "CASUAL",
                        "invitationDetailType": "MEAL",
                        "summary": "밥 한 번 먹자",
                        "description": "K-밥약",
                        "cardTemplateId": $cardTemplateId,
                        "cardTemplateItemIds": [1,2,3]
                    }""".trimIndent()
                )
        )
            // then
            .andExpect(status().isOk)
    }

}
