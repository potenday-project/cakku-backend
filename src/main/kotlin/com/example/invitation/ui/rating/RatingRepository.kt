package com.example.invitation.ui.rating

import com.example.invitation.domain.rating.Rating
import org.springframework.data.jpa.repository.JpaRepository

interface RatingRepository : JpaRepository<Rating, Long> {
}
