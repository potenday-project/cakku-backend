package com.example.invitation.ui

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class CorsTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun testCors() {
        mockMvc.perform(options("/api/v1/invitations"))
            .andExpect(status().isOk)
    }
}
