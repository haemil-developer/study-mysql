package study.mysql.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.mysql.domain.member.dto.MemberDto;
import study.mysql.domain.member.dto.MemberNicknameRecordDto;
import study.mysql.domain.member.dto.RegisterMemberCommand;
import study.mysql.domain.member.entity.Member;
import study.mysql.domain.member.service.MemberReadService;
import study.mysql.domain.member.service.MemberWriteService;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberWriteService memberWriteService;

    private final MemberReadService memberReadService;

    @PostMapping("")
    public MemberDto register(@RequestBody RegisterMemberCommand command) {
        Member member = memberWriteService.register(command);
        return memberReadService.toDto(member);
    }

    @GetMapping("/{id}")
    public MemberDto getMember(@PathVariable Long id) {
        return memberReadService.getMember(id);
    }

    @PutMapping("/{id}/nickname")
    public MemberDto changeNickname(@PathVariable Long id, @RequestBody String nickname) {
        memberWriteService.changeNicknames(id, nickname);
        return memberReadService.getMember(id);
    }

    @GetMapping("/{memberId}/nickname-record")
    public List<MemberNicknameRecordDto> getMemberNicknameRecords(@PathVariable Long memberId) {
        return memberReadService.getMemberNicknameRecords(memberId);
    }
}
