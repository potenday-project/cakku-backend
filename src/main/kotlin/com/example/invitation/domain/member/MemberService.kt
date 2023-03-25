package com.example.invitation.domain.member

import org.springframework.stereotype.Service
import javax.transaction.TransactionScoped

interface MemberService {
    fun getMembers(): List<Member>
}

@Service
@TransactionScoped
class MemberServiceImpl(
    private val memberRepository: MemberRepository,
) : MemberService {

    override fun getMembers(): List<Member> {
        return memberRepository.findAll()
    }

}