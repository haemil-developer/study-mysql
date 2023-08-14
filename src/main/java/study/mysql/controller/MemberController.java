package study.mysql.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.mysql.domain.member.dto.MemberDto;
import study.mysql.domain.member.dto.RegisterMemberCommand;
import study.mysql.domain.member.entity.Member;
import study.mysql.domain.member.service.MemberReadService;
import study.mysql.domain.member.service.MemberWriteService;

@RestController
@RequiredArgsConstructor
public class MemberController {

    final public MemberWriteService memberWriteService;

    final public MemberReadService memberReadService;

    @PostMapping("/members")
    public Member register(@RequestBody RegisterMemberCommand command) {
        return memberWriteService.register(command);
    }

    @GetMapping("/members/{id}")
    public MemberDto getMember(@PathVariable Long id) {
        return memberReadService.getMember(id);
    }
}
