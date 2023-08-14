package study.mysql.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.mysql.domain.member.dto.MemberDto;
import study.mysql.domain.member.entity.Member;
import study.mysql.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberReadService {
    final private MemberRepository memberRepository;

    public MemberDto getMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow();
        return toDto(member);
    }

    public MemberDto toDto(Member member) {
       return new MemberDto(member.getId(), member.getEmail(), member.getNickname(), member.getBirthday());
    }
}
