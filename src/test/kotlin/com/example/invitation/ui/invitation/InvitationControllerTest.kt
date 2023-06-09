package com.example.invitation.ui.invitation

import com.example.invitation.domain.analytics.AnalyticsEvent
import com.example.invitation.domain.card.image.CardImageCreateVo
import com.example.invitation.domain.card.image.CardImageService
import com.example.invitation.domain.card.template.CardTemplate
import com.example.invitation.domain.card.template.CardTemplateRepository
import com.example.invitation.domain.card.template.item.CardTemplateItem
import com.example.invitation.domain.card.template.item.CardTemplateItemRepository
import com.example.invitation.domain.file.File
import com.example.invitation.domain.file.FileRepository
import com.example.invitation.domain.invitation.InvitationType
import com.example.invitation.domain.invitation.detail.InvitationDetailType
import com.example.invitation.domain.invitation.detail.InvitationDetailTypeRepository
import com.example.invitation.infrastructure.mixpanel.MixpanelEventPublisher
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class InvitationControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var cardTemplateRepository: CardTemplateRepository

    @Autowired
    lateinit var cardTemplateItemRepository: CardTemplateItemRepository

    @Autowired
    lateinit var fileRepository: FileRepository

    @Autowired
    lateinit var invitationDetailTypeRepository: InvitationDetailTypeRepository

    @MockBean
    lateinit var cardImageService: CardImageService

    @MockBean
    lateinit var mixpanelEventPublisher: MixpanelEventPublisher

    @BeforeEach
    fun setup() {
        doNothing().`when`(mixpanelEventPublisher)
            .publish(any(String::class.java), any(AnalyticsEvent::class.java))
    }

    @Test
    fun testGetInvitation() {
        mockMvc.perform(get("/api/v1/invitations/1"))
            .andExpect(status().isInternalServerError)
    }

    @Test
    fun testCreateInvitation() {
        // given
        val cardTemplate = CardTemplate(
            invitationType = InvitationType.CASUAL,
            name = "name",
            imageUrl = "imageUrl",
        )
        cardTemplateRepository.save(cardTemplate)
        val invitationDetailType = invitationDetailTypeRepository.save(
            InvitationDetailType(
                cardTemplate = cardTemplate,
                invitationType = InvitationType.CASUAL,
                name = "맥주",
                emoji = "🍺",
                description = "",
            )
        )

        cardTemplateItemRepository.saveAll(
            listOf(
                CardTemplateItem(
                    cardTemplateItemId = 1L,
                    imageUrl = "imageUrl",
                    name = "name",
                    emoji = "emoji",
                    x = 0,
                    y = 0,
                ),
                CardTemplateItem(
                    cardTemplateItemId = 2L,
                    imageUrl = "imageUrl",
                    name = "name",
                    emoji = "emoji",
                    x = 1,
                    y = 0,
                ),
                CardTemplateItem(
                    cardTemplateItemId = 3L,
                    imageUrl = "imageUrl",
                    name = "name",
                    emoji = "emoji",
                    x = 2,
                    y = 0,
                ),
            )
        )
        val file = File(
            url = "url",
            name = "filename",
            contentType = "image/png",
            size = 1L,
        )
        fileRepository.save(file)
        `when`(cardImageService.createImage(any(CardImageCreateVo::class.java))).thenReturn(file)
        // when
        mockMvc.perform(
            post("/api/v1/invitations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                        "userName": "userName",
                        "invitationTypeIndex": 0,
                        "invitationDetailTypeId": ${invitationDetailType.invitationDetailTypeId},
                        "summary": "밥 한 번 먹자",
                        "description": "K-밥약",
                        "cardTemplateItemIds": [1, 2, 3],
                        "time": "12:34"
                    }""".trimIndent()
                )
        )
            // then
            .andExpect(status().isOk)
    }

    private fun <T> any(type: Class<T>): T = Mockito.any(type)

}
