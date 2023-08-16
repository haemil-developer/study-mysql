package study.mysql.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.mysql.domain.follow.service.FollowWriteService;
import study.mysql.domain.member.dto.MemberDto;
import study.mysql.domain.member.service.MemberReadService;

/**
 * Usecase Layer 는 가능한 로직이 없고, 흐름만 제어하도록 해야한다.
 * 각각 domain에 대한 business logic 은 각각 domain service 에서 제어해야한다.
 */
@Service
@RequiredArgsConstructor
public class CreateFollowMemberUsecase {
    private final MemberReadService memberReadService;
    private final FollowWriteService followWriteService;

    public void execute(Long fromMemberId, Long toMemberId) {
        /*
            1. 입력받은 memberId 로 회원조회
            2. FollowWriteService.create()
         */

        MemberDto fromMember = memberReadService.getMember(fromMemberId);
        MemberDto toMember = memberReadService.getMember(toMemberId);

        followWriteService.crate(fromMember, toMember);
    }
}
