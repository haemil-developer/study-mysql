package study.mysql.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.mysql.domain.member.dto.RegisterMemberCommand;
import study.mysql.domain.member.entity.Member;
import study.mysql.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberWriteService {
    final private MemberRepository memberRepository;

    public Member register(RegisterMemberCommand command) {
        /*
            Goal - 회원정보(이메일, 닉네임, 생년월일)를 등록한다.
                 - 닉네임은 10자를 넘길 수 없다.
            parameter  - memberRegisterCommand

            member = Member.of(memberRegisterCommand)
            memberRepository.save(member);
         */

        Member member = Member.builder()
                .nickname(command.nickname())
                .email(command.email())
                .birthday(command.birthday())
                .build();
        return memberRepository.save(member);
    }
}
