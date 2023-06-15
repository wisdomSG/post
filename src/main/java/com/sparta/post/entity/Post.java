package com.sparta.post.entity;

import com.sparta.post.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "post") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Post extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String  title;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "contents", nullable = false)
    private String contents;
    @Column(name = "password", nullable = false)
    private String password;

    public Post(PostRequestDto requestDto) {
        this.title = title;
        this.username = username;
        this.contents = contents;
        this.password = password;
    }
    public void update(PostRequestDto requestDto) {
        this.title = title;
        this.username = username;
        this.contents = contents;
        this.password = password;
    }
}
