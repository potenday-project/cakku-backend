package com.example.invitation.ui.rating

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class RatingControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

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
            .andExpect(jsonPath("$.data.ratingId").isNumber)
            .andExpect(jsonPath("$.data.ratingType").value("GOOD"))
            .andExpect(jsonPath("$.data.invitationId").value(null))
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
            .andExpect(jsonPath("$.data.ratingId").isNumber)
            .andExpect(jsonPath("$.data.ratingType").value("NOT_GOOD"))
            .andExpect(jsonPath("$.data.invitationId").value(null))
    }
}
