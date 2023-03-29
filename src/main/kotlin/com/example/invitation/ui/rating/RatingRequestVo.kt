package com.example.invitation.ui.rating

import com.example.invitation.domain.rating.RatingType

data class RatingRequestVo(
    val ratingType: RatingType,
    val invitationId: Long? = null,
)
