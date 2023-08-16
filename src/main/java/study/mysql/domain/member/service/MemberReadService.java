package study.mysql.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.mysql.domain.member.dto.MemberDto;
import study.mysql.domain.member.dto.MemberNicknameRecordDto;
import study.mysql.domain.member.entity.Member;
import study.mysql.domain.member.entity.MemberNicknameRecord;
import study.mysql.domain.member.repository.MemberNicknameRecordRepository;
import study.mysql.domain.member.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberReadService {
    private final MemberRepository memberRepository;

    private final MemberNicknameRecordRepository memberNicknameRecordRepository;

    public MemberDto getMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow();
        return toDto(member);
    }

    public List<MemberDto> getMembers(List<Long> memberIds) {
        var members = memberRepository.findAllByIdIn(memberIds);
        return members.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<MemberNicknameRecordDto> getMemberNicknameRecords(Long memberId) {
        return memberNicknameRecordRepository
                .findAllByMemberId(memberId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public MemberDto toDto(Member member) {
       return new MemberDto(member.getId(), member.getEmail(), member.getNickname(), member.getBirthday());
    }

    private MemberNicknameRecordDto toDto(MemberNicknameRecord record) {
        return new MemberNicknameRecordDto(record.getId(), record.getMemberId(), record.getNickname(), record.getCreatedAt());
    }

}
