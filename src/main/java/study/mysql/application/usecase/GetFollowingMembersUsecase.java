package study.mysql.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.mysql.domain.follow.entity.Follow;
import study.mysql.domain.follow.service.FollowReadService;
import study.mysql.domain.member.dto.MemberDto;
import study.mysql.domain.member.service.MemberReadService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetFollowingMembersUsecase {
    final private MemberReadService memberReadService;
    final private FollowReadService followReadService;

    public List<MemberDto> execute(Long memberId) {
        var followings = followReadService.getFollowings(memberId);
        var followingMemberIds = followings.stream().map(Follow::getToMemberId).toList();
        return memberReadService.getMembers(followingMemberIds);
    }
}
