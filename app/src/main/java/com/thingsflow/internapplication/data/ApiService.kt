package com.thingsflow.internapplication.data

import com.apollographql.apollo.ApolloClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiService @Inject constructor(
    baseUrl: String
) {
    private val apolloClient = ApolloClient.builder()
        .serverUrl(baseUrl)
        .build()
}