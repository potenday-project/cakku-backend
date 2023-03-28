package com.example.invitation.domain.invitation

/**
 * 초대 목적 - 캐주얼한 약속 / 파티 / 대결 및 결투신청
 */
enum class InvitationType(
    private val description: String,
) {
    CASUAL("캐주얼한 약속"),
    PARTY("파티"),
    GAME("대결 및 결투신청"),
}
