package com.example.invitation.domain.member

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByProviderTypeAndProviderUserId(providerType: ProviderType, providerUserId: String): Member?
}
