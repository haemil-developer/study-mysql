package study.mysql.domain.follow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import study.mysql.domain.follow.entity.Follow;
import study.mysql.domain.follow.repository.FollowRepository;
import study.mysql.domain.member.dto.MemberDto;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FollowWriteService {
    private final FollowRepository followRepository;

    public void crate(MemberDto fromMember, MemberDto toMember) {
        /*
            from, to 2명의 회원 정보를 받아서 저장
            from != to validate
         */
        Assert.isTrue(!fromMember.id().equals(toMember.id()), "From, To 회원이 동일합니다.");

        Follow follow = Follow.builder()
                .fromMemberId(fromMember.id())
                .toMemberId(toMember.id())
                .build();

        followRepository.save(follow);
    }
}
