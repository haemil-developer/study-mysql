package study.mysql.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.mysql.domain.member.dto.RegisterMemberCommand;
import study.mysql.domain.member.entity.Member;
import study.mysql.domain.member.entity.MemberNicknameRecord;
import study.mysql.domain.member.repository.MemberNicknameRecordRepository;
import study.mysql.domain.member.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberWriteService {
    final private MemberRepository memberRepository;

    final private MemberNicknameRecordRepository memberNicknameRecordRepository;

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

        Member savedMember = memberRepository.save(member);
        saveMemberNicknameRecord(savedMember);
        return savedMember;
    }

    public void changeNicknames(Long memberId, String nickname) {
        /*
            1. 회원의 닉네임을 변경
            2. 변경 내역을 저장
         */
        Member member = memberRepository.findById(memberId).orElseThrow();
        member.changeNickname(nickname);
        Member savedMember = memberRepository.save(member);

        saveMemberNicknameRecord(savedMember);
    }

    private void saveMemberNicknameRecord(Member member) {
        MemberNicknameRecord memberNicknameRecord = MemberNicknameRecord
                .builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .build();

        memberNicknameRecordRepository.save(memberNicknameRecord);
    }
}
