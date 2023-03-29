package com.example.invitation.application.rating

import com.example.invitation.domain.rating.Rating
import com.example.invitation.ui.rating.RatingRequest
import com.example.invitation.ui.rating.RatingRequestVo
import com.example.invitation.ui.rating.RatingResponse

fun Rating.toDto(): RatingResponse {
    return RatingResponse(
        ratingId = this.ratingId,
        ratingType = this.ratingType,
        invitationId = this.invitation?.invitationId,
    )
}

fun RatingRequest.toVo(): RatingRequestVo {
    return RatingRequestVo(
        ratingType = this.ratingType,
        invitationId = this.invitationId,
    )
}

