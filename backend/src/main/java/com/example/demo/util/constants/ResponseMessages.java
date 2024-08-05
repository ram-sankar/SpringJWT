package com.example.demo.util.constants;

public class ResponseMessages {

        private ResponseMessages() {
            throw new UnsupportedOperationException("This is a constants class and cannot be instantiated");
        }

        // Common response messages
        public static final String CREATED_SUCCESSFULLY = "Created successfully";
        public static final String UPDATED_SUCCESSFULLY = "Updated successfully";
        public static final String DELETED_SUCCESSFULLY = "Deleted successfully";
        public static final String DATA_FETCHED_SUCCESSFULLY = "Data fetched successfully";
        public static final String OPERATION_FAILED = "Operation failed";
        public static final String INVALID_REQUEST = "Invalid request";
        public static final String RESOURCE_NOT_FOUND = "Resource not found";
        public static final String UNAUTHORIZED_ACCESS = "Unauthorized access";
        public static final String FORBIDDEN = "Forbidden";
        public static final String INTERNAL_SERVER_ERROR = "Internal server error";

}
