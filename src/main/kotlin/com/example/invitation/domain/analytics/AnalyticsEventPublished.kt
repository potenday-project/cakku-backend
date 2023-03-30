package com.example.invitation.domain.analytics

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class AnalyticsEventPublished(
    val event: AnalyticsEvent,
)
