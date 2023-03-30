package com.example.invitation.domain.analytics

interface AnalyticsEventPublisher {
    fun publish(userId: String, event: AnalyticsEvent)
}
