package com.intigral.ott.popcorn.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Creating POJO for validating Popcorn API Response Body with invalid APIKey.
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {
    private Error error;
    public Error getError() {
		return error;
	}
	@Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Error{
        public String getMessage() {
			return message;
		}
		public String getCode() {
			return code;
		}
		public String getRequestId() {
			return requestId;
		}
		String message;
        String code;
        String requestId;

    }
}
