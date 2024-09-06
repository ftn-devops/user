package ftn.devops.user.dto;

import ftn.devops.user.entity.Grade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GradeDTO {

    Integer id;

    String reviewerId;

    String reviewerUsername;

    String reviewedId;

    String reviewedName;

    int grade;

    final boolean isForAccommodation = true;

    public GradeDTO(Grade grade) {
        this.id = grade.getId();
        this.reviewerId = grade.getReviewer().getId().toString();
        this.reviewerUsername = grade.getReviewer().getUsername();
        this.reviewedId = grade.getReviewee().getId().toString();
        this.reviewedName = grade.getReviewee().getUsername();
        this.grade = grade.getGrade();
    }
}
