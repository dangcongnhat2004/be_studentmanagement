package com.example.be.controller;

import com.example.be.Dto.AttendanceRequest;
import com.example.be.Dto.GradeDto;
import com.example.be.Dto.ReqRes;
import com.example.be.entity.Attendance;
import com.example.be.entity.Grades;
import com.example.be.entity.Users;
import com.example.be.repository.AttendanceRepository;
import com.example.be.repository.CourseRepository;
import com.example.be.repository.GradesRepo;
import com.example.be.repository.OurUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserController {
    @Autowired
    private CourseRepository courseRepo;
    @Autowired
    private OurUserRepo ourUserRepo;
    @Autowired
    private GradesRepo gradesRepo;
    @Autowired
    private AttendanceRepository attendanceRepository;

    @GetMapping("/user/course")
    public ResponseEntity<Object> getAllCourse(){
        return ResponseEntity.ok(courseRepo.findAll());
    }

    @PostMapping("/student/savestudent")
    public ResponseEntity<Object> SaveUsers(@RequestBody ReqRes userRequest, Principal principal) {
        // Kiểm tra xem principal (người dùng đang đăng nhập) có tồn tại không
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }

        // Lấy email của người dùng đang đăng nhập từ principal
        String userEmail = principal.getName();

        // Tìm người dùng trong cơ sở dữ liệu bằng email
        Optional<Users> optionalUser = ourUserRepo.findByEmail(userEmail);

        if (optionalUser.isPresent()) {
            Users userToUpdate = optionalUser.get();

            // Cập nhật thông tin người dùng
            userToUpdate.setNameUser(userRequest.getNameUser());
            userToUpdate.setNumberPhone(userRequest.getNumberPhone());
            userToUpdate.setAddress(userRequest.getAddress());
            userToUpdate.setSchoolName(userRequest.getSchoolName());

            // Lưu người dùng đã được cập nhật
            ourUserRepo.save(userToUpdate);
            return ResponseEntity.ok("User updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @GetMapping("/student/getphonenumbers")
    public ResponseEntity<Object> getAllPhoneNumbers() {
        // Lấy danh sách id, name và số điện thoại từ cơ sở dữ liệu
        List<Map<String, Object>> userData = ourUserRepo.findAllIdNameAndPhoneNumbers();

        // Kiểm tra xem danh sách có rỗng hay không
        if (userData.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user data found");
        }

        return ResponseEntity.ok(userData);
    }


    @GetMapping("/student/getHomepage")
    public ResponseEntity<Object> getAllUserDetails() {
        // Lấy thông tin người dùng đang đăng nhập
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userEmail = userDetails.getUsername(); // Giả sử username là email

        // Lấy thông tin của người dùng dựa trên email
        Map<String, Object> userDetailsMap = ourUserRepo.findUserByEmail(userEmail);

        // Kiểm tra xem thông tin người dùng có rỗng hay không
        if (userDetailsMap == null || userDetailsMap.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user data found");
        }

        return ResponseEntity.ok(userDetailsMap);
    }


    @GetMapping("/student/grades")
    public ResponseEntity<List<GradeDto>> getGrades(Authentication authentication) {
        // Lấy email của user hiện tại từ Authentication
        String userEmail = authentication.getName();

        // Tìm user trong cơ sở dữ liệu bằng email
        Users student = ourUserRepo.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Lấy danh sách điểm từ cơ sở dữ liệu
        List<Grades> gradesList = gradesRepo.findByStudent(student);

        // Chuyển đổi danh sách Grades sang danh sách GradesDto
        List<GradeDto> gradesDtoList = gradesList.stream().map(this::convertToDto).collect(Collectors.toList());

        return ResponseEntity.ok(gradesDtoList);
    }

    private GradeDto convertToDto(Grades grade) {
        GradeDto dto = new GradeDto();
        dto.setStudentId(grade.getStudent().getId());
        dto.setNameUser(grade.getStudent().getNameUser());
        dto.setCourseId(grade.getCourse().getId());

        // Kiểm tra và gán ScholasticId
        if (grade.getScholastic() != null) {
            dto.setScholasticId(grade.getScholastic().getId());
        } else {
           // Đảm bảo rằng khi Scholastic là null thì set null vào dto
        }

        dto.setFrequentScore1(grade.getFrequentScore1());
        dto.setFrequentScore2(grade.getFrequentScore2());
        dto.setFrequentScore3(grade.getFrequentScore3());
        dto.setFrequentScore4(grade.getFrequentScore4());
        dto.setFrequentScore5(grade.getFrequentScore5());
        dto.setMidtermScore(grade.getMidtermScore());
        dto.setFinalScore(grade.getFinalScore());
        dto.setComments(grade.getComments());
        dto.setCourseName(grade.getCourse().getNameCourse());

        return dto;
    }


    @PostMapping("/student/saveask")
    public ResponseEntity<Object> saveAttendance(@RequestBody AttendanceRequest attendanceRequest, Principal principal) {
        // Kiểm tra xem principal (người dùng đang đăng nhập) có tồn tại không
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }

        // Lấy email của người dùng đang đăng nhập từ principal
        String userEmail = principal.getName();

        // Tìm người dùng trong cơ sở dữ liệu bằng email
        Optional<Users> optionalUser = ourUserRepo.findByEmail(userEmail);

        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();

            // Tạo mới đối tượng Attendance từ request
            Attendance attendance = new Attendance();
            attendance.setStudent(user); // Đặt người dùng là học sinh trong điểm danh
            attendance.setAttendance_ask(attendanceRequest.getAttendance_ask());
            attendance.setAttendance_title(attendanceRequest.getTitle());
            attendance.setAttendance_date(attendanceRequest.getAttendance_date());
            attendance.setStatusnow(Attendance.StatusNow.NORMAL); // Đặt trạng thái mặc định là NORMAL

            // Lưu điểm danh vào cơ sở dữ liệu
            attendanceRepository.save(attendance);

            return ResponseEntity.ok("Attendance saved successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }



}


