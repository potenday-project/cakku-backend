package com.example.invitation.ui.rating

import com.example.invitation.domain.invitation.InvitationRepository
import com.example.invitation.domain.rating.Rating
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface RatingService {
    fun create(ratingRequestVo: RatingRequestVo): Rating
    fun getRatings(): List<Rating>
    fun getRating(ratingId: Long): Rating?
}

@Service
@Transactional(readOnly = true)
class RatingServiceImpl(
    private val ratingRepository: RatingRepository,
    private val invitationRepository: InvitationRepository,
) : RatingService {
    @Transactional
    override fun create(ratingRequestVo: RatingRequestVo): Rating {
        val invitation = ratingRequestVo.invitationId?.let {
            invitationRepository.findByIdOrNull(it)
        }
        return ratingRepository.save(
            Rating.of(
                ratingType = ratingRequestVo.ratingType,
                invitation = invitation,
            )
        )
    }

    override fun getRatings(): List<Rating> {
        return ratingRepository.findAll()
    }

    override fun getRating(ratingId: Long): Rating? {
        return ratingRepository.findByIdOrNull(ratingId)
    }
}
