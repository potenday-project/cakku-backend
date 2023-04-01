package com.example.invitation.application.rating

import com.example.invitation.domain.rating.Rating
import com.example.invitation.ui.rating.RatingRequest
import com.example.invitation.ui.rating.RatingRequestVo
import com.example.invitation.ui.rating.RatingResponse

fun Rating.toDto(): RatingResponse {
    @Suppress("SimpleRedundantLet")
    return RatingResponse(
        ratingId = this.ratingId.toString(),
        ratingType = this.ratingType,
        // FIXME: let 을 단순히 축약하면 안됨. let 이 없으면, invitation 이 존재하지 않을 때, null 이 아닌 'null' 문자열이 들어감.
        invitationId = this.invitation?.let { it.toString() },
    )
}

fun RatingRequest.toVo(): RatingRequestVo {
    return RatingRequestVo(
        ratingType = this.ratingType,
        invitationId = this.invitationId?.toLong(),
    )
}

