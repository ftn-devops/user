package ftn.devops.user.service.implementation;

import ftn.devops.user.dto.GradeDTO;
import ftn.devops.user.entity.Grade;
import ftn.devops.user.messaging.messages.UserRatedMessage;
import ftn.devops.user.messaging.notifier.IUserNotifier;
import ftn.devops.user.repository.GradeRepository;
import ftn.devops.user.repository.UserRepository;
import ftn.devops.user.service.GradeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;

    private final UserRepository userRepository;

    private final IUserNotifier userNotifier;

    @Override
    public boolean addGrade(GradeDTO gradeDTO) {
        try {

            var reviewer = userRepository.findById(Integer.valueOf(gradeDTO.getReviewerId()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid reviewer id."));

            var reviewed = userRepository.findById(Integer.valueOf(gradeDTO.getReviewedId()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid reviewed id."));
            var grade = Grade.builder()
                .grade(gradeDTO.getGrade())
                .reviewer(reviewer)
                .reviewee(reviewed)
                .build();

            gradeRepository.save(grade);

            var allUserGrades = gradeRepository.findByReviewee_Id(Integer.valueOf(gradeDTO.getReviewedId()));
            var average = allUserGrades.stream().mapToDouble(Grade::getGrade).average().orElseThrow();

            reviewed.setAverageGrade((float) average);
            userRepository.save(reviewed);

            var message = UserRatedMessage.builder()
                .recipientEmail(reviewed.getEmail())
                .rating((float) gradeDTO.getGrade())
                .build();

            userNotifier.fireUserRateddNotification(message);
            return true;
        } catch (Exception e) {
            log.warn(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteGrade(GradeDTO gradeDTO) {
        gradeRepository.deleteById(gradeDTO.getId());
        try {
            gradeRepository.deleteById(gradeDTO.getId());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }
}
