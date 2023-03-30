package com.example.invitation.infrastructure.mixpanel

import com.example.invitation.domain.analytics.AnalyticsEvent
import com.example.invitation.domain.analytics.AnalyticsEventPublisher
import com.mixpanel.mixpanelapi.ClientDelivery
import com.mixpanel.mixpanelapi.MessageBuilder
import com.mixpanel.mixpanelapi.MixpanelAPI
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class MixpanelEventPublisher(
    @Value("\${invitation.mixpanel.project-token}") val projectToken: String,
) : AnalyticsEventPublisher {
    override fun publish(
        userId: String,
        event: AnalyticsEvent,
    ) {
        val sentEvent = MessageBuilder(projectToken).event(
            userId,
            event.eventName,
            JSONObject(),
        )

        val delivery = ClientDelivery()
        delivery.addMessage(sentEvent)

        val mixpanel = MixpanelAPI()
        mixpanel.deliver(delivery)
    }
}
