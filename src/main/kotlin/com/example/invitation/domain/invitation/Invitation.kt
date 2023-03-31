package com.example.invitation.domain.invitation

import com.example.invitation.domain.card.Card
import com.example.invitation.domain.invitation.detail.InvitationDetailType
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
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
    var card: Card? = null,
    /**
     * 초대한 사람 이름
     */
    val userName: String,
    /**
     * 초대 목적 - 캐주얼한 약속 / 파티 / 대결 및 결투신청
     */
    val invitationType: InvitationType,
    /**
     * 초대 컨셉/분위기 - 초대 목적에 대한 추가 내용 ?
     */
    @ManyToOne
    @JoinColumn(name = "invitationDetailTypeId")
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
     * 날짜
     */
    var date: LocalDate? = null,
    /**
     * 시간
     */
    var time: LocalTime? = null,
    /**
     * 장소
     */
    val place: String? = null,
    var deleted: Boolean = false
) {
    @CreatedDate
    lateinit var createdAt: LocalDateTime

    @LastModifiedDate
    lateinit var updatedAt: LocalDateTime

    companion object {
        fun from(invitationRequestVo: InvitationRequestVo): Invitation {
            return Invitation(
                userName = invitationRequestVo.userName,
                invitationType = invitationRequestVo.invitationType,
                invitationDetailType = invitationRequestVo.invitationDetailType,
                summary = invitationRequestVo.summary,
                description = invitationRequestVo.description,
                date = invitationRequestVo.date,
                time = invitationRequestVo.time,
                place = invitationRequestVo.place,
            )
        }
    }
}
