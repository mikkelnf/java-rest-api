package com.enigma.controller;

import com.enigma.mdel.request.CoursePaymentRequest;
import com.enigma.mdel.response.CoursePaymentResponse;
import com.enigma.mdel.response.ErrorResponse;
import com.enigma.mdel.response.SuccessResponse;
import com.enigma.service.ICoursePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course-payment")
public class CoursePaymentController {
    private ICoursePaymentService coursePaymentService;

    public CoursePaymentController(@Autowired ICoursePaymentService coursePaymentService) {
        this.coursePaymentService = coursePaymentService;
    }

    @PostMapping
    public ResponseEntity coursePayment(@RequestBody CoursePaymentRequest coursePaymentRequest){
        CoursePaymentResponse response = coursePaymentService.pay(coursePaymentRequest);
        if(response.isStatus()){
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(response.getTransactionId(), "Succes payment"));
        }

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorResponse("X78", "Failed payment"));
    }
}
