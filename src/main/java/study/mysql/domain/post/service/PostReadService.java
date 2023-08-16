package study.mysql.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.mysql.domain.post.dto.DailyPostCount;
import study.mysql.domain.post.dto.DailyPostCountRequest;
import study.mysql.domain.post.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostReadService {
    private final PostRepository postRepository;

    public List<DailyPostCount> getDailyPostCount(DailyPostCountRequest request) {
    /*
        변환 값 -> list[작성일자, 작성회원, 작성 게시물 갯수]
        select createdDate, memberId, count(id)
        from Post
        where memberId = :memberId and createdDate between firstDate and lastDate
        group by createdDate, memberId
     */
        return postRepository.groupByCreatedDate(request);
    }
}
