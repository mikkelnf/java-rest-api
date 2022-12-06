package com.enigma.repository.interfaces;

import com.enigma.model.request.CoursePaymentRequest;
import com.enigma.model.response.CoursePaymentResponse;

public interface ICoursePaymentRepository {
    CoursePaymentResponse callPaymentApi(CoursePaymentRequest coursePaymentRequest);
}
