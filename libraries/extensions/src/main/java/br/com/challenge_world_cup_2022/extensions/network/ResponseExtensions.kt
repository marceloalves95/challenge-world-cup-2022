package br.com.challenge_world_cup_2022.extensions.network

import br.com.challenge_world_cup_2022.network.model.ServiceState
import okhttp3.ResponseBody
import retrofit2.Response

fun <T : Any> Response<T>.parseResponse(
    body: T? = this.body(),
    httpCode: Int = this.code(),
    errorBody: ResponseBody? = this.errorBody(),
    message: String = this.message()
): ServiceState<T> {
    return if (isSuccessful && body != null) {
        ServiceState.Success(
            data = body,
            httpCode = httpCode
        )
    } else {
        when (httpCode) {
            404 -> {
                ServiceState.Error(
                    response = body(),
                    message = "Page not found",
                    httpCode = httpCode,
                    errorBody = errorBody,
                    exception = NotFoundException("Page not found")
                )
            }

            500 -> {
                ServiceState.Error(
                    response = body(),
                    message = "Internal Server Error",
                    httpCode = httpCode,
                    errorBody = errorBody,
                    exception = InternalErrorException("Internal Server Error")
                )
            }

            else -> {
                ServiceState.Error(
                    response = body(),
                    message = message,
                    httpCode = httpCode,
                    errorBody = errorBody,
                    exception = Throwable()
                )
            }
        }
    }
}

fun <T : Any> ServiceState<T>.toResponse(): T = when (this) {
    is ServiceState.Success -> data
    is ServiceState.Error -> throw exception
}

