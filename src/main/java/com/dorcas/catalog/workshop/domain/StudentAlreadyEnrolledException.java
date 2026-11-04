package com.dorcas.catalog.workshop.domain;

import com.dorcas.catalog.shared.domain.ConflictException;
import com.dorcas.catalog.shared.domain.ErrorCode;

public class StudentAlreadyEnrolledException extends ConflictException {

    public StudentAlreadyEnrolledException(String student, String workshop) {
        super(ErrorCode.STUDENT_ALREADY_ENROLLED, student + " is already enrolled in " + workshop);
    }
}
