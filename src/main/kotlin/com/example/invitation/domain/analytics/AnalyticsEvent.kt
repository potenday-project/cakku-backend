package com.example.invitation.domain.analytics

enum class AnalyticsEvent(
    private val description: String,
) {
    INVITATION_CREATED("초대장 생성"),
    INVITATION_RATED("초대장 평가"),
    ;

    val eventName: String
        get() = name
}
