package com.netcrackerTask.backend.business.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is a simple message response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {
    /**
     * Response text.
     */
    private String message;
}