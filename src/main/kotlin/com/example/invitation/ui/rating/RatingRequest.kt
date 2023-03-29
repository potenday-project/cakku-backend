package com.example.invitation.ui.rating

import com.example.invitation.domain.rating.RatingType

data class RatingRequest(
    val ratingType: RatingType,
    val invitationId: Long?,
)
