package co.istad.productapidemo.restcontrollers;

import co.istad.productapidemo.dto.TagRequest;
import co.istad.productapidemo.dto.TagResponse;
import co.istad.productapidemo.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagRestController {
    private final TagService tagService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TagResponse create(@RequestBody TagRequest tagRequest) {
        return tagService.createTag(tagRequest);
    }

    @GetMapping
    public Page<TagResponse> getAll(Pageable pageable) {
        return tagService.getAllTags(pageable);
    }
}
