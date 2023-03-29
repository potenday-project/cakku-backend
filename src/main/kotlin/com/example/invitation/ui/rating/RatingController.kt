package com.example.invitation.ui.rating

import com.example.invitation.application.rating.RatingApplicationService
import com.example.invitation.ui.ApiResponse
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/ratings")
@RestController
class RatingController(
    private val ratingApplicationService: RatingApplicationService,
) {
    @PostMapping
    fun createRating(
        @RequestBody ratingRequest: RatingRequest,
    ): ApiResponse<RatingResponse> {
        return ApiResponse.success(
            data = ratingApplicationService.createRating(ratingRequest),
        )
    }
}
