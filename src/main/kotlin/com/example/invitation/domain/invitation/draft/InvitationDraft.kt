package com.example.invitation.domain.invitation.draft

import com.example.invitation.domain.invitation.InvitationDetailType
import com.example.invitation.domain.invitation.InvitationType
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

/**
 * 발송하기 전 수정 가능한 초대장
 */
@Entity
@EntityListeners(AuditingEntityListener::class)
class InvitationDraft(
    @Id
    @GeneratedValue
    val invitationDraftId: Long = 0L,
    /**
     * 초대한 사람 이름
     */
    var hostName: String? = null,
    /**
     * 초대 목적 - 캐주얼한 약속 / 파티 / 대결 및 결투신청
     */
    @Enumerated(EnumType.STRING)
    var invitationType: InvitationType? = null,
    /**
     * 초대 컨셉/분위기 - 초대 목적에 대한 추가 내용 ?
     */
    @Enumerated(EnumType.STRING)
    var invitationDetailType: InvitationDetailType? = null,
    /**
     * 초대장 요약 - 가이드 제공, 수정 가능
     */
    var summary: String? = null,
    /**
     * 초대장 설명 - 일정 상세, 타임테이블
     */
    var description: String? = null,
    /**
     * 시간
     */
    var startedAt: LocalDateTime? = null,
    /**
     * 장소
     */
    var location: String? = null,
    @Enumerated(EnumType.STRING)
    var draftStatus: InvitationDraftStatus = InvitationDraftStatus.DRAFT,
    var deleted: Boolean = false,
) {
    @CreatedDate
    lateinit var createdAt: LocalDateTime

    @LastModifiedDate
    lateinit var updatedAt: LocalDateTime

    fun update(invitationDraftUpdateVo: InvitationDraftUpdateVo) {
        if (draftStatus != InvitationDraftStatus.DRAFT) {
            throw IllegalArgumentException("발송된 초대장은 수정할 수 없습니다.")
        }
        invitationDraftUpdateVo.hostName?.let { this.hostName = it }
        invitationDraftUpdateVo.invitationType?.let { this.invitationType = it }
        invitationDraftUpdateVo.invitationDetailType?.let { this.invitationDetailType = it }
        invitationDraftUpdateVo.summary?.let { this.summary = it }
        invitationDraftUpdateVo.description?.let { this.description = it }
        invitationDraftUpdateVo.startedAt?.let { this.startedAt = it }
        invitationDraftUpdateVo.location?.let { this.location = it }
    }

    private fun validate() {
        if (hostName.isNullOrBlank()) {
            throw IllegalArgumentException("초대한 사람 이름을 입력해주세요.")
        }
        if (invitationType == null) {
            throw IllegalArgumentException("초대 목적을 선택해주세요.")
        }
        if (invitationDetailType == null) {
            throw IllegalArgumentException("초대 컨셉/분위기를 선택해주세요.")
        }
        if (summary.isNullOrBlank()) {
            throw IllegalArgumentException("초대장 요약을 입력해주세요.")
        }
        if (description.isNullOrBlank()) {
            throw IllegalArgumentException("초대장 설명을 입력해주세요.")
        }
    }

    fun publish() {
        if (draftStatus != InvitationDraftStatus.DRAFT) {
            throw IllegalArgumentException("발송된 초대장은 다시 발송할 수 없습니다.")
        }
        validate()
        draftStatus = InvitationDraftStatus.PUBLISHED
    }
}
