package com.example.invitation.application.rating

import com.example.invitation.ui.rating.RatingRequest
import com.example.invitation.ui.rating.RatingResponse
import com.example.invitation.ui.rating.RatingService
import org.springframework.stereotype.Component

@Component
class RatingApplicationService(
    private val ratingService: RatingService,
) {
    fun createRating(ratingRequest: RatingRequest): RatingResponse {
        return ratingService.create(
            ratingRequestVo = ratingRequest.toVo(),
        ).toDto()
    }
}
