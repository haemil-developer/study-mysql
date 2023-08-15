package study.mysql.domain.post.dto;

import java.time.LocalDate;

public record PostCommand(
        Long memberId,
        String contents
) {
}
