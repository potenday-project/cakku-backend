package com.example.invitation.domain.member

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface MemberService {
    fun getMembers(): List<Member>
    fun findMember(providerType: ProviderType, providerUserId: String): Member?
    fun createMember(providerType: ProviderType, providerUserId: String): Member
}

@Service
@Transactional(readOnly = true)
class MemberServiceImpl(
    private val memberRepository: MemberRepository,
) : MemberService {

    override fun getMembers(): List<Member> {
        return memberRepository.findAll()
    }

    override fun findMember(providerType: ProviderType, providerUserId: String): Member? {
        return memberRepository.findByProviderTypeAndProviderUserId(providerType, providerUserId)
    }

    @Transactional
    override fun createMember(providerType: ProviderType, providerUserId: String): Member {
        if (providerType != ProviderType.ANONYMOUS) {
            throw IllegalArgumentException("Only anonymous provider type is allowed. providerType: $providerType, providerUserId: $providerUserId")
        }
        val member = Member.anonymous(providerUserId)
        return memberRepository.save(member)
    }
}
