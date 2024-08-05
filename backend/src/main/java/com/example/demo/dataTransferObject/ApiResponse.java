package com.example.demo.dataTransferObject;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiResponse<T> {
        private T data;
        private String message;
        private HttpStatus status;

        public ApiResponse() {
            this.status = HttpStatus.OK;
        }

        public ApiResponse(T data, String message) {
            this.data = data;
            this.message = message;
            this.status = HttpStatus.OK; // Default value
        }

        public ApiResponse(T data, String message, HttpStatus status) {
            this.data = data;
            this.message = message;
            this.status = status;
        }
}
