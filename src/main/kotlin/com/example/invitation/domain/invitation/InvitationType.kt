package com.example.invitation.domain.invitation

/**
 * 초대 목적 - 캐주얼한 약속 / 파티 / 대결 및 결투신청
 */
enum class InvitationType(
    private val description: String,
    val displayIndex: Int,
) {
    CASUAL("캐주얼한 약속", 0),
    PARTY("파티", 1),
    CHALLENGE("대결 및 결투신청", 2),
    ;

    companion object {
        fun from(displayIndex: Int): InvitationType {
            return values().firstOrNull { it.displayIndex == displayIndex }
                ?: throw IllegalArgumentException("displayIndex 에 맞는 invitationType 이 존재하지 않습니다. displayIndex: $displayIndex")
        }
    }

}
