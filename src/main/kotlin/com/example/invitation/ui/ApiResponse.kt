package com.example.invitation.ui

class ApiResponse<T>(
    val code: String,
    val message: String,
    val data: T? = null,
) {
    companion object {
        private const val successCode = "SUCCESS"
        private const val successMessage = "SUCCESS"
        private const val failureCode = "FAILURE"
        private const val failureMessage = "FAILURE"

        fun success(): ApiResponse<Unit> {
            return ApiResponse(
                code = successCode,
                message = successMessage,
            )
        }

        fun <T> success(data: T): ApiResponse<T> {
            return ApiResponse(
                code = successCode,
                message = successMessage,
                data = data,
            )
        }

        fun <T> success(contents: List<T>): ApiResponse<List<T>> {
            return ApiResponse(
                code = successCode,
                message = successMessage,
                data = contents,
            )
        }

        fun failure(): ApiResponse<Unit> {
            return ApiResponse(
                code = failureCode,
                message = failureMessage,
            )
        }
    }
}
