package com.samuylov.projectstart.repository;

import com.samuylov.projectstart.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByBookIdOrderByDateDesc(Long bookId);
}
