package com.example.board.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {@Index(columnList = "title"), @Index(columnList = "hashTag"), @Index(columnList = "createdBy"), @Index(columnList = "createdAt")})
@Entity
public class Article extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String title;

    @Setter
    @Column(nullable = false, length = 65535)
    private String content;

    @Setter
    private String hashTag;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @OrderBy("id")
    @ToString.Exclude
    private final Set<Comment> comments = new LinkedHashSet<>();

    private Article(final String title, final String content, final String hashTag) {
        this.title = title;
        this.content = content;
        this.hashTag = hashTag;
    }

    public static Article of(final String title, final String content, final String hashTag) {
        return new Article(title, content, hashTag);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return this.id != null && this.id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}