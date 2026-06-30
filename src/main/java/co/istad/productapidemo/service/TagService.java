package co.istad.productapidemo.service;

import co.istad.productapidemo.dto.TagRequest;
import co.istad.productapidemo.dto.TagResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TagService {
    TagResponse createTag(TagRequest request);
    Page<TagResponse> getAllTags(Pageable pageable);
}
