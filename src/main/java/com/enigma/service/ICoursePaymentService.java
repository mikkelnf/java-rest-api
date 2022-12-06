package com.enigma.service;

import com.enigma.mdel.request.CoursePaymentRequest;
import com.enigma.mdel.response.CoursePaymentResponse;

public interface ICoursePaymentService {
    CoursePaymentResponse pay(CoursePaymentRequest coursePaymentRequest);
}
