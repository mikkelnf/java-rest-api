package com.enigma.repository.interfaces;

import com.enigma.mdel.request.CoursePaymentRequest;
import com.enigma.mdel.response.CoursePaymentResponse;

public interface ICoursePaymentRepository {
    CoursePaymentResponse callPaymentApi(CoursePaymentRequest coursePaymentRequest);
}
