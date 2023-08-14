package study.mysql.domain.member.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class MemberNicknameRecord {
    final private  Long id;

    final private Long memberId;

    // field 의 이름이 같다고 같은 data 가 아니다!
    // history 성 data 들은 정규화의 대상이 아니다!
    final private String nickname;

    final private LocalDateTime createdAt;

    @Builder
    public MemberNicknameRecord(Long id, Long memberId, String nickname, LocalDateTime createdAt) {
        this.id = id;
        this.memberId = Objects.requireNonNull(memberId);
        this.nickname = Objects.requireNonNull(nickname);
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }
}
