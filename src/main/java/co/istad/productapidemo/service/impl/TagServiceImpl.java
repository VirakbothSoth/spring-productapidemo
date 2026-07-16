package co.istad.productapidemo.service.impl;

import co.istad.productapidemo.dto.TagRequest;
import co.istad.productapidemo.dto.TagResponse;
import co.istad.productapidemo.mapper.TagMapper;
import co.istad.productapidemo.repository.TagRepository;
import co.istad.productapidemo.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public TagResponse createTag(TagRequest request) {
        var tag = tagMapper.mapToEntity(request);
        return tagMapper.mapToResponse(tagRepository.save(tag));
    }

    @Override
    public Page<TagResponse> getAllTags(Pageable pageable) {
        return tagRepository
                .findAll(pageable)
                .map(tagMapper::mapToResponse);
    }
}
