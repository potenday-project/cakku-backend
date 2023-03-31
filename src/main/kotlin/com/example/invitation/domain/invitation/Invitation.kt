package com.example.invitation.domain.invitation

import com.example.invitation.domain.card.Card
import com.example.invitation.domain.file.File
import com.example.invitation.domain.invitation.draft.InvitationDraft
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

/**
 * 생성 완료된 초대장
 */
@Entity
@EntityListeners(AuditingEntityListener::class)
class Invitation(
    @Id
    @GeneratedValue
    val invitationId: Long = 0L,
    @OneToOne(mappedBy = "invitation")
    val card: Card? = null,
    /**
     * 초대한 사람 이름
     */
    val hostName: String,
    /**
     * 초대 목적 - 캐주얼한 약속 / 파티 / 대결 및 결투신청
     */
    val invitationType: InvitationType,
    /**
     * 초대 컨셉/분위기 - 초대 목적에 대한 추가 내용 ?
     */
    val invitationDetailType: InvitationDetailType,
    /**
     * 초대장 요약 - 가이드 제공, 수정 가능
     */
    val summary: String,
    /**
     * 초대장 설명 - 일정 상세, 타임테이블
     */
    val description: String,
    /**
     * 시간
     */
    val startedAt: LocalDateTime? = null,
    /**
     * 장소
     */
    val location: String? = null,
    @OneToMany
    @JoinColumn(name = "invitationId")
    val files: List<File> = emptyList(),
    var deleted: Boolean = false
) {
    @CreatedDate
    lateinit var createdAt: LocalDateTime

    @LastModifiedDate
    lateinit var updatedAt: LocalDateTime

    companion object {
        fun from(invitationDraft: InvitationDraft): Invitation {
            return Invitation(
                hostName = invitationDraft.hostName!!,
                invitationType = invitationDraft.invitationType!!,
                invitationDetailType = invitationDraft.invitationDetailType!!,
                summary = invitationDraft.summary!!,
                description = invitationDraft.description!!,
                startedAt = invitationDraft.startedAt,
                location = invitationDraft.location,
            )
        }

        fun from(invitationRequestVo: InvitationRequestVo): Invitation {
            return Invitation(
                hostName = invitationRequestVo.hostName,
                invitationType = invitationRequestVo.invitationType,
                invitationDetailType = invitationRequestVo.invitationDetailType,
                summary = invitationRequestVo.summary,
                description = invitationRequestVo.description,
                startedAt = invitationRequestVo.startedAt,
                location = invitationRequestVo.location,
            )
        }
    }
}
