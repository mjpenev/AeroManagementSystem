package com.flightreservation.aero.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public class Response {

    protected LocalDateTime timeStamp;
    protected HttpStatus status;
    protected String message;
    protected boolean success;
    protected Map<String, Object> data;

    public static ResponseBuilder builder() {
        return new ResponseBuilder();
    }

    public static class ResponseBuilder {
        private LocalDateTime timeStamp;
        private HttpStatus status;
        private String message;
        private boolean success;
        private Map<String, Object> data = new HashMap<>();

        public ResponseBuilder timeStamp(LocalDateTime timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public ResponseBuilder status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public ResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ResponseBuilder success(boolean success) {
            this.success = success;
            return this;
        }

        public ResponseBuilder data(String key, Object value) {
            this.data.put(key, value);
            return this;
        }

        public Response build() {
            return new Response(
                    this.timeStamp,
                    this.status,
                    this.message,
                    this.success,
                    this.data
            );
        }
    }
}