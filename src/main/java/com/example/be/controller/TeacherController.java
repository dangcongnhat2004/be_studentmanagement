package com.example.be.controller;

import com.example.be.Dto.*;
import com.example.be.entity.*;
import com.example.be.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class TeacherController {


    @Autowired
    private GradesRepo gradesRepository;

    @Autowired
    private OurUserRepo usersRepository;

    @Autowired
    private ScholasticRepository scholasticRepository;

    @Autowired
    private ClassesRepository classesRepository;

    @Autowired
    private CourseRepository courseRepository;



    @PostMapping("/teacher/grades")
    public ResponseEntity<Object> addGrade(@RequestBody GradeDto gradeRequest) {
        try {
            Optional<Users> studentOpt = usersRepository.findById(gradeRequest.getStudentId());
            Optional<Scholastic> scholasticOpt = scholasticRepository.findById(gradeRequest.getScholasticId());
            Optional<Course> courseOpt = courseRepository.findById(gradeRequest.getCourseId());

            if (studentOpt.isPresent() && scholasticOpt.isPresent() && courseOpt.isPresent()) {
                Grades grade = new Grades();
                grade.setStudent(studentOpt.get());
                grade.setScholastic(scholasticOpt.get());
                grade.setCourse(courseOpt.get());
                grade.setFrequentScore1(gradeRequest.getFrequentScore1());
                grade.setFrequentScore2(gradeRequest.getFrequentScore2());
                grade.setFrequentScore3(gradeRequest.getFrequentScore3());
                grade.setFrequentScore4(gradeRequest.getFrequentScore4());
                grade.setFrequentScore5(gradeRequest.getFrequentScore5());
                grade.setMidtermScore(gradeRequest.getMidtermScore());
                grade.setFinalScore(gradeRequest.getFinalScore());

                // Tính toán điểm trung bình
                BigDecimal total = BigDecimal.ZERO;
                int count = 0;
                if (gradeRequest.getFrequentScore1() != null) { total = total.add(gradeRequest.getFrequentScore1()); count++; }
                if (gradeRequest.getFrequentScore2() != null) { total = total.add(gradeRequest.getFrequentScore2()); count++; }
                if (gradeRequest.getFrequentScore3() != null) { total = total.add(gradeRequest.getFrequentScore3()); count++; }
                if (gradeRequest.getFrequentScore4() != null) { total = total.add(gradeRequest.getFrequentScore4()); count++; }
                if (gradeRequest.getFrequentScore5() != null) { total = total.add(gradeRequest.getFrequentScore5()); count++; }
                if (gradeRequest.getMidtermScore() != null) { total = total.add(gradeRequest.getMidtermScore()); count++; }
                if (gradeRequest.getFinalScore() != null) { total = total.add(gradeRequest.getFinalScore()); count++; }

                if (count > 0) {
                    BigDecimal averageScore = total.divide(BigDecimal.valueOf(count), 2, BigDecimal.ROUND_HALF_UP);
                    grade.setAverageScore(averageScore);
                } else {
                    grade.setAverageScore(BigDecimal.ZERO);
                }

                grade.setComments(gradeRequest.getComments());

                gradesRepository.save(grade);
                return ResponseEntity.ok("Grade added successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student, Scholastic or Course not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }


    @PostMapping("/teacher/scholastic")
    public ResponseEntity<Object> addScholastic(@RequestBody ScholasticRequest scholasticRequest, Principal principal) {
        try {
            // Kiểm tra xem principal (người dùng đang đăng nhập) có tồn tại không
            if (principal == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
            }

            // Lấy email của người dùng đang đăng nhập từ principal
            String userEmail = principal.getName();

            // Tìm người dùng trong cơ sở dữ liệu bằng email
            Optional<Users> optionalUser = usersRepository.findByEmail(userEmail);

            if (optionalUser.isPresent()) {
                Users user = optionalUser.get();

                // Tạo một đối tượng Scholastic mới từ ScholasticRequest
                Scholastic scholastic = new Scholastic();
                scholastic.setNameYear(scholasticRequest.getNameYear());
                scholastic.setSemester1(scholasticRequest.getSemester1());
                scholastic.setSemester2(scholasticRequest.getSemester2());
                scholastic.setSemesterAll(scholasticRequest.getSemesterAll());

                // Gán user cho Scholastic
                scholastic.setUser(user);

                // Lưu Scholastic vào cơ sở dữ liệu
                scholasticRepository.save(scholastic);

                // Trả về thành công
                return ResponseEntity.ok("Scholastic added successfully");
            } else {
                // Nếu không tìm thấy người dùng, trả về lỗi
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (Exception e) {
            // Nếu có lỗi, trả về lỗi và thông báo
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }



    @PostMapping("/teacher/course")
    public ResponseEntity<Object> addCourse(@RequestBody CourseRequest courseRequest, Principal principal) {
        try {
            // Kiểm tra xem principal (người dùng đang đăng nhập) có tồn tại không
            if (principal == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
            }

            // Lấy email của người dùng đang đăng nhập từ principal
            String userEmail = principal.getName();

            // Tìm người dùng trong cơ sở dữ liệu bằng email
            Optional<Users> optionalTeacher = usersRepository.findById(courseRequest.getTeacherId());
            if (!optionalTeacher.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not found");
            }
            Optional<Scholastic> optionalScholastic = scholasticRepository.findById(courseRequest.getScholasticId());
            if (!optionalScholastic.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Scholastic not found");
            }
            // Tìm lớp học từ ID lớp học
            Optional<Classes> optionalClasses = classesRepository.findById(courseRequest.getClassesId());
            if (!optionalClasses.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Class not found");
            }


            // Tạo đối tượng Course mới
            Course course = new Course();
            course.setNameCourse(courseRequest.getNameCourse());
            course.setTeacher(optionalTeacher.get());
            course.setScholastic(optionalScholastic.get());
            course.setClasses(optionalClasses.get());

            // Lưu khóa học vào cơ sở dữ liệu
            courseRepository.save(course);

            return ResponseEntity.ok("Course added successfully");
        } catch (Exception e) {
            // Nếu có lỗi, trả về lỗi và thông báo
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/teacher/classes")
    public ResponseEntity<Object> addClasses(@RequestBody ClassesRequest classesRequest, Principal principal) {
        try {
            // Kiểm tra xem principal (người dùng đang đăng nhập) có tồn tại không
            if (principal == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
            }

            // Tạo đối tượng Classes mới
            Classes classes = new Classes();
            classes.setNameClass(classesRequest.getNameClass());
            classes.setTotal(classesRequest.getTotal());

            // Lưu lớp học vào cơ sở dữ liệu
            classesRepository.save(classes);

            // Lấy người dùng từ cơ sở dữ liệu dựa trên email của người dùng đăng nhập
            String userEmail = principal.getName();
            Optional<Users> optionalUser = usersRepository.findByEmail(userEmail);

            if (optionalUser.isPresent()) {
                Users userToUpdate = optionalUser.get();

                // Cập nhật class_id của người dùng với id của lớp học vừa tạo
                userToUpdate.setClassId(classes.getId());

                // Lưu người dùng đã được cập nhật
                usersRepository.save(userToUpdate);

                return ResponseEntity.ok("Classes added successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (Exception e) {
            // Nếu có lỗi, trả về lỗi và thông báo
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }



}



