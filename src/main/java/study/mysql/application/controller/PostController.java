package study.mysql.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import study.mysql.domain.post.dto.DailyPostCount;
import study.mysql.domain.post.dto.DailyPostCountRequest;
import study.mysql.domain.post.dto.PostCommand;
import study.mysql.domain.post.entity.Post;
import study.mysql.domain.post.service.PostReadService;
import study.mysql.domain.post.service.PostWriteService;
import study.mysql.domain.util.CursorRequest;
import study.mysql.domain.util.PageCursor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostWriteService postWriteService;
    public final PostReadService postReadService;

    @PostMapping("")
    public Long create(@RequestBody PostCommand command) {
        return postWriteService.create(command);
    }

    @GetMapping("/daily-post-counts")
    private List<DailyPostCount> getDailyPostCounts(DailyPostCountRequest request) {
        return postReadService.getDailyPostCount(request);
    }

    @GetMapping("members/{memberId}")
    private Page<Post> getPosts(
            @PathVariable Long memberId,
            @RequestParam Pageable pageable
            ) {
        return postReadService.getPosts(memberId, pageable);
    }

    @GetMapping("members/{memberId}/by-cursor")
    private PageCursor<Post> getPostsByCursor(
            @PathVariable Long memberId,
            @RequestParam CursorRequest cursorRequest
    ) {
        return postReadService.getPosts(memberId, cursorRequest);
    }
}
