package br.com.challenge_world_cup_2022.extensions.network

import java.net.HttpURLConnection
import retrofit2.HttpException as RetrofitHttpException

fun <T> Result<T>.getOrThrowDomainError(): T = getOrElse { throwable ->
    throw throwable.toDomainError()
}

internal fun Throwable.toDomainError(): Throwable {
    return when (this) {
        is RetrofitHttpException -> {
            when (code()) {
                HttpURLConnection.HTTP_NOT_FOUND -> NotFoundException("Page not found")
                HttpURLConnection.HTTP_INTERNAL_ERROR -> InternalErrorException("Internal Server Error")
                else -> UnexpectedException()
            }
        }

        else -> this
    }
}

sealed class HttpException(
    message: String? = null,
    cause: Throwable? = null
) : Throwable(message, cause)

class NotFoundException(message: String?) : HttpException(message)
class InternalErrorException(message: String?) : HttpException(message)
class UnexpectedException : HttpException()