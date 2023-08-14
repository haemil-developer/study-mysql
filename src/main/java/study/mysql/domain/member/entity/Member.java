package study.mysql.domain.member.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Member {
    /**
     * 변경 불가능한 항복은 final 선언 해 주도록 한다.
     */

    // id 의 경우 변경 불가 이기 때문에 final 선언
    final private Long id;

    // nickname 은 변경가능한 항목
    private String nickname;

    final private String email;

    final private LocalDate birthday;

    final private LocalDateTime createdAt;

    final private static Long NAME_MAX_LENGTH = 10L;

    @Builder
    public Member(Long id, String nickname, String email, LocalDate birthday, LocalDateTime createdAt) {
        this.id = id;
        this.email = Objects.requireNonNull(email);
        this.birthday = Objects.requireNonNull(birthday);

        validateNickname(nickname);
        this.nickname = Objects.requireNonNull(nickname);

        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }

    void validateNickname(String nickname) {
        // Custom Exception 을 만들어서 throw 시키는 것도 좋은 방법임.
        Assert.isTrue( nickname.length() <= NAME_MAX_LENGTH, "최대 길이를 초과했습니다.");
    }
}
