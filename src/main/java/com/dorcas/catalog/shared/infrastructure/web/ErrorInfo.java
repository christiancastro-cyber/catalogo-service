package com.dorcas.catalog.shared.infrastructure.web;

import com.dorcas.catalog.shared.domain.ErrorCode;

import java.time.Instant;
import java.util.List;

public record ErrorInfo(
        int status,
        String error,
        ErrorCode code,
        String message,
        String path,
        Instant timestamp,
        List<ErrorDetail> details
) {
}
