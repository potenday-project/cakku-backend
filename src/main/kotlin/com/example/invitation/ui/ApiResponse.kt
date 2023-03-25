package com.example.invitation.ui

class ApiResponse<T>(
    val code: String,
    val message: String,
    val content: T? = null,
) {
    companion object {
        fun success(): ApiResponse<Unit> {
            return ApiResponse(
                code = "SUCCESS",
                message = "SUCCESS",
            )
        }

        fun <T> success(contents: List<T>): ApiResponse<List<T>> {
            return ApiResponse(
                code = "SUCCESS",
                message = "SUCCESS",
                content = contents,
            )
        }

        fun failure(): ApiResponse<Unit> {
            return ApiResponse(
                code = "FAILURE",
                message = "FAILURE",
            )
        }
    }
}