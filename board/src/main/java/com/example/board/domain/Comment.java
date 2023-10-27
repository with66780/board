package com.example.board.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {@Index(columnList = "content"), @Index(columnList = "regId"), @Index(columnList = "regDate")})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(optional = false)
    private Article article;

    @Setter
    @Column(nullable = false, length = 500)
    private String content;

    @CreatedBy
    @Column(nullable = false, length = 100)
    private String regId;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime regDate;

    @LastModifiedBy
    @Column(nullable = false, length = 100)
    private String modId;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modDate;

    private Comment(final Article article, final String content) {
        this.article = article;
        this.content = content;
    }

    public static Comment of(final Article article, final String content) {
        return new Comment(article, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment comment)) return false;
        return this.id != null && this.id.equals(comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}