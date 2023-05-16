package devbeom.Mixify.repository;

import devbeom.Mixify.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c " +
            "join fetch c.user u " +
            "where c.id = :commentId")
    Optional<Comment> findById(@Param("commentId") Long commentId);
}
