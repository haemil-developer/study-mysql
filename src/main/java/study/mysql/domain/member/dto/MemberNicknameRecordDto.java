package study.mysql.domain.member.dto;

import java.time.LocalDateTime;

public record MemberNicknameRecordDto(
        Long id,
        Long memberId,
        String nickname,
        LocalDateTime cratedDate
) {
}
