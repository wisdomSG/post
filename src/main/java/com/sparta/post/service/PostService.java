package com.sparta.post.service;

import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.dto.PostResponseDto;
import com.sparta.post.entity.Post;
import com.sparta.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponseDto createPost(PostRequestDto requestDto) {
        // RequestDto -> Entity
        Post post = new Post(requestDto);

        // DB 저장
        Post savePost = postRepository.save(post);

        // Entity -> ResponseDto
        PostResponseDto postResponseDto = new PostResponseDto(savePost);

        return postResponseDto;
    }
    public List<PostResponseDto> getPosts() {
        return postRepository.findAllByOrderByModifiedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    public List<PostResponseDto> getPost(Long id) {
        // DB 조회 -> id에 맞는 게시물을 조회하는게 문제임

        return postRepository.findById(id).stream().map(PostResponseDto::new).toList();

    }

    @Transactional
    public Long updatePost(Long id, PostRequestDto requestDto) {
        // 해당 게시물이 DB에 존재하는지 확인
        Post post = findPost(id);



        // 비밀번호가 맞는지 확인
        if (!post.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 게시물 내용 수정
        post.update(requestDto);

        return id;

    }


    public Long deletePost(Long id, PostRequestDto requestDto) {
        // 해당 게시물이 DB에 존재하는지 확인
        Post post = findPost(id);


        // 비밀번호가 맞는지 확인
        if (!post.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 포스트 삭제
        postRepository.delete(post);

        return id;
    }



    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시물은 존재하지 않습니다.")
        );
    }

}
