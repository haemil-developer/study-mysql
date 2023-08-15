package study.mysql.domain.post;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;
import study.mysql.domain.post.entity.Post;
import study.mysql.domain.post.repository.PostRepository;
import study.mysql.util.PostFixtureFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class PostBulkInsertTest {
    @Autowired
    public PostRepository postRepository;

    @Test
    public void bulkInsert() {
        EasyRandom easyRandom = PostFixtureFactory.get(
                3L,
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 8, 1)
        );

        /*
        IntStream.range(0, 5)
                .mapToObj(i -> easyRandom.nextObject(Post.class))
                .forEach(x ->
                        // System.out.println(x.getMemberId()+ "/" + x.getCreatedDate())
                        postRepository.save(x)
                );
        */

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        List<Post> posts = IntStream.range(0, 10000)
                .parallel()
                .mapToObj(i -> easyRandom.nextObject(Post.class))
                .toList();

        stopWatch.stop();
        System.out.println("객체 생성 시간 (sec) : " + stopWatch.getTotalTimeSeconds());

        StopWatch queryStopWatch = new StopWatch();
        queryStopWatch.start();

        postRepository.bulkInsert(posts);

        queryStopWatch.stop();
        System.out.println("쿼리 실행 시간 (sec) : " + queryStopWatch.getTotalTimeSeconds());
    }
}
