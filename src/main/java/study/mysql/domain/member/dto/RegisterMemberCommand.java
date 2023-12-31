package study.mysql.domain.member.dto;

import java.time.LocalDate;

/*
    record
    - Getter, Setter 자동생성.
    - 생성된 getter, setter 를 property 형식으로 사용할 수 있음.
    - Java의 Record class 를 상속받는 형태로 구현되어 있음.
 */
public record RegisterMemberCommand(
        String email,
        String nickname,
        LocalDate birthday
) {
}
