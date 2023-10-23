package com.example.board.domain;

import java.time.LocalDateTime;

public class Comment {
    private Long id;
    private Article article;
    private String content;

    private String regId;
    private LocalDateTime regDate;
    private String modId;
    private LocalDateTime modDate;
}
