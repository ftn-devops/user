package ftn.devops.user.service;

import ftn.devops.user.dto.GradeDTO;
import ftn.devops.user.entity.Grade;
import java.util.List;

public interface GradeService {

    boolean addGrade(GradeDTO gradeDTO);

    boolean deleteGrade(GradeDTO gradeDTO);

    List<Grade> getAllGrades();
}
