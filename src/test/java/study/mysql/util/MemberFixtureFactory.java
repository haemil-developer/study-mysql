package study.mysql.util;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import study.mysql.domain.member.entity.Member;

public class MemberFixtureFactory {
    //ObjectMother Pattern

    static public Member create() {
        var param = new EasyRandomParameters();
        return new EasyRandom(param).nextObject(Member.class);
    }
    static public Member create(Long seed) {
        var param = new EasyRandomParameters().seed(seed);
        return new EasyRandom(param).nextObject(Member.class);
    }
}
