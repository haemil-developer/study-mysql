package study.mysql.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.mysql.domain.post.dto.DailyPostCount;
import study.mysql.domain.post.dto.DailyPostCountRequest;
import study.mysql.domain.post.dto.PostCommand;
import study.mysql.domain.post.service.PostReadService;
import study.mysql.domain.post.service.PostWriteService;

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
}
