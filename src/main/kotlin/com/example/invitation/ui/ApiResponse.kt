package com.example.invitation.ui

class ApiResponse<T>(
    val code: String,
    val message: String,
    val data: T? = null,
) {
    companion object {
        fun success(): ApiResponse<Unit> {
            return ApiResponse(
                code = "SUCCESS",
                message = "SUCCESS",
            )
        }

        fun <T> success(data: T): ApiResponse<T> {
            return ApiResponse(
                code = "SUCCESS",
                message = "SUCCESS",
                data = data,
            )
        }

        fun <T> success(contents: List<T>): ApiResponse<List<T>> {
            return ApiResponse(
                code = "SUCCESS",
                message = "SUCCESS",
                data = contents,
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
