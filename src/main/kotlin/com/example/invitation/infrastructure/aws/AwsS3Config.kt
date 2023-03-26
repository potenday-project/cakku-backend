package com.example.invitation.infrastructure.aws

import com.amazonaws.ClientConfiguration
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AwsS3Config(
    @Value("\${invitation.aws.s3.access-key}")
    private val accessKey: String,
    @Value("\${invitation.aws.s3.secret-key}")
    private val secretKey: String,
    @Value("\${invitation.aws.s3.region}")
    private val regison: String,
    @Value("\${invitation.aws.s3.connection-timeout:1000}")
    private val connectionTimeout: Int,
    @Value("\${invitation.aws.s3.request-timeout:3000}")
    private val requestTimeout: Int,
) {
    @Bean
    fun amazonS3(): AmazonS3 {
        val awsCredentials: AWSCredentials = BasicAWSCredentials(accessKey, secretKey)
        val clientConfiguration = ClientConfiguration()
        clientConfiguration.connectionTimeout = connectionTimeout
        clientConfiguration.requestTimeout = requestTimeout
        return AmazonS3ClientBuilder.standard()
            .withRegion(regison)
            .withCredentials(AWSStaticCredentialsProvider(awsCredentials))
            .withClientConfiguration(clientConfiguration)
            .build()
    }
}
