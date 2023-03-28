package com.example.invitation.ui.invitation

import com.example.invitation.application.invitation.InvitationApplicationService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class InvitationDraftControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var invitationApplicationService: InvitationApplicationService

    @Test
    fun testCreateInvitationDraft() {
        mockMvc.perform(post("/api/v1/invitation-drafts"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.data.invitationDraftId").isNumber)
    }

    @Test
    fun testGetInvitationDraft() {
        // given
        val invitationDraft = invitationApplicationService.createInvitationDraft()
        val invitationDraftId = invitationDraft.invitationDraftId
        // when
        mockMvc.perform(get("/api/v1/invitation-drafts/{invitationDraftId}", invitationDraftId))
            // then
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.data.invitationDraftId").value(invitationDraftId))
    }

    @Test
    fun testUpdateInvitationDraft() {
        // given
        val invitationDraft = invitationApplicationService.createInvitationDraft()
        val invitationDraftId = invitationDraft.invitationDraftId
        // when
        mockMvc.perform(
            put("/api/v1/invitation-drafts/{invitationDraftId}", invitationDraftId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                        "hostName": "hostName",
                        "invitationType": "CASUAL"
                    }
                    """.trimIndent()
                )
        )
            // then
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.data.invitationDraftId").value(invitationDraftId))
            .andExpect(jsonPath("$.data.hostName").value("hostName"))
            .andExpect(jsonPath("$.data.invitationType").value("CASUAL"))
    }
}
