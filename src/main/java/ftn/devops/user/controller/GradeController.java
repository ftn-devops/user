package ftn.devops.user.controller;

import ftn.devops.user.dto.GradeDTO;
import ftn.devops.user.service.GradeService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grades")
@RequiredArgsConstructor
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping
    public ResponseEntity<List<GradeDTO>> getAllAccommodationGrades() {

        var result = gradeService.getAllGrades().stream()
            .map(GradeDTO::new)
            .toList();

        return ResponseEntity.ok().body(result);
    }

    @PostMapping()
    public ResponseEntity<Boolean> addGrade(@RequestBody GradeDTO gradeDTO) {
        boolean ret = gradeService.addGrade(gradeDTO);
        return ResponseEntity.ok().body(ret);
    }

    @DeleteMapping()
    public ResponseEntity<Boolean> deleteGrade(@RequestBody GradeDTO gradeDTO) {
        boolean ret = gradeService.deleteGrade(gradeDTO);
        return ResponseEntity.ok().body(ret);
    }
}
