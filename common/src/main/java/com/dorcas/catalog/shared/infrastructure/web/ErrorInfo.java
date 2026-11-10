package com.dorcas.catalog.shared.infrastructure.web;

import java.time.Instant;
import java.util.List;

public record ErrorInfo(
        int status,
        String error,
        String code,
        String message,
        String path,
        Instant timestamp,
        List<ErrorDetail> details
) {
}
