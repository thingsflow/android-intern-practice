package com.thingsflow.internapplication

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiHeaderInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return try {
            val newRequest = chain.request().newBuilder()
                .addHeader("Cookie", "token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjQzMDU0LCJ0cyI6MTY0OTc0MzI2OTQ0Mywib25seU9UUCI6ZmFsc2UsImlhdCI6MTY0OTc0MzI2OSwiZXhwIjozNTQxOTAzMjY5fQ.gfI2OS3tZA5JOviCcaCftyJUagLltwObvuxSuSOX-7w")
                .build()

            chain.proceed(newRequest)
        } catch (e: Throwable){
            chain.proceed(chain.request())
        }
    }
}