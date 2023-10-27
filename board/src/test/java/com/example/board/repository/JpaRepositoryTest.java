package com.example.board.repository;

import com.example.board.config.JpaConfig;
import com.example.board.domain.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Import(JpaConfig.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JpaRepositoryTest {
    private ArticleRepository articleRepository;
    private CommentRepository commentRepository;

    public JpaRepositoryTest(@Autowired final ArticleRepository articleRepository, @Autowired final CommentRepository commentRepository) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
    }

    @Test
    void test() {
        List<Article> articleList = articleRepository.findAll();
        assertThat(articleList).isNotNull().hasSize(0);
    }
}