package ftn.devops.user.repository;

import ftn.devops.user.entity.Grade;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {

    List<Grade> findByReviewee_Id(Integer userId);
}
