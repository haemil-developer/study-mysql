package study.mysql.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import study.mysql.domain.post.dto.DailyPostCount;
import study.mysql.domain.post.dto.DailyPostCountRequest;
import study.mysql.domain.post.entity.Post;
import study.mysql.domain.post.repository.PostRepository;
import study.mysql.domain.util.CursorRequest;
import study.mysql.domain.util.PageCursor;

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

    public Page<Post> getPosts(Long memberId, Pageable pageable) {
        return postRepository.findAllByMemberId(memberId, pageable);
    }

    public PageCursor<Post> getPosts(Long memberId, CursorRequest cursorRequest) {
        List<Post> posts = findAllBy(memberId, cursorRequest);
        long nextKey = posts.stream().mapToLong(Post::getId).min().orElse(CursorRequest.NONE_KEY);
        return new PageCursor<>(cursorRequest.next(nextKey), posts);
    }

    public List<Post> findAllBy(Long memberId, CursorRequest cursorRequest) {
        if (cursorRequest.hasKey()) {
            return postRepository.findAllByLessThanIdMemberIdAndOrderByIdDesc(cursorRequest.key(), memberId, cursorRequest.size());
        }
        return postRepository.findAllByMemberIdAndOrderByIdDesc(memberId, cursorRequest.size());
    }
}
