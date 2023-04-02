package com.example.invitation.ui.rating

import com.example.invitation.domain.analytics.AnalyticsEvent
import com.example.invitation.infrastructure.mixpanel.MixpanelEventPublisher
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class RatingControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var mixpanelEventPublisher: MixpanelEventPublisher

    @BeforeEach
    fun setup() {
        Mockito.doNothing().`when`(mixpanelEventPublisher)
            .publish(any(String::class.java), any(AnalyticsEvent::class.java));
    }

    @DisplayName("createRating : GOOD")
    @Test
    fun testCreateRating_GOOD() {
        mockMvc.perform(
            post("/api/v1/ratings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                {
                    "ratingType": "GOOD",
                    "invitationId": null
                }
            """.trimIndent()
                )
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.data.ratingId").isString)
            .andExpect(jsonPath("$.data.ratingType").value("GOOD"))
            .andExpect(jsonPath("$.data.invitationId").isEmpty)
    }

    @DisplayName("createRating : NOT_GOOD")
    @Test
    fun testCreateRating_NOT_GOOD() {
        mockMvc.perform(
            post("/api/v1/ratings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                {
                    "ratingType": "NOT_GOOD",
                    "invitationId": null
                }
            """.trimIndent()
                )
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.data.ratingId").isString)
            .andExpect(jsonPath("$.data.ratingType").value("NOT_GOOD"))
            .andExpect(jsonPath("$.data.invitationId").isEmpty)
    }

    private fun <T> any(type: Class<T>): T = Mockito.any(type)
}
