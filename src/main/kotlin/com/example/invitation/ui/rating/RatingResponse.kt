package com.example.invitation.ui.rating

import com.example.invitation.domain.rating.RatingType

data class RatingResponse(
    val ratingId: String,
    val ratingType: RatingType,
    val invitationId: String?,
)
