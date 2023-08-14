package study.mysql.domain.member;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.mysql.domain.member.entity.Member;
import study.mysql.util.MemberFixtureFactory;

import java.util.stream.LongStream;

public class MemberTest {

    @DisplayName("회원은 닉네임을 변경할 수 있다.")
    @Test
    public void TestChangeNickname() {
        /*
        - How to use easy random with seed

        LongStream.range(0, 10)
                .mapToObj(MemberFixtureFactory::create)
                .forEach(member -> {
                    System.out.println(member.getNickname());
                });
        */

        Member member = MemberFixtureFactory.create();
        String expected = "hailey";

        member.changeNickname(expected);

        Assertions.assertEquals(expected, member.getNickname());
    }

    @DisplayName("회원은 닉네임은 10자를 넘을 수 없다.")
    @Test
    public void TestChangeNicknameMaxLength() {
        /*
        - How to use easy random with seed

        LongStream.range(0, 10)
                .mapToObj(MemberFixtureFactory::create)
                .forEach(member -> {
                    System.out.println(member.getNickname());
                });
        */

        Member member = MemberFixtureFactory.create();
        String overMaxLengthNickname = "hailey-developer";

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> member.changeNickname(overMaxLengthNickname));
    }
}
