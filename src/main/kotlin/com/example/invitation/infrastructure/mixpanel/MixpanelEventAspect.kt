package com.example.invitation.infrastructure.mixpanel

import com.example.invitation.domain.analytics.AnalyticsEvent
import com.example.invitation.domain.analytics.AnalyticsEventPublished
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*

@Aspect
@Component
class MixpanelEventAspect(
    private val mixpanelEventPublisher: MixpanelEventPublisher,
) {
    @Around("@annotation(com.example.invitation.domain.analytics.AnalyticsEventPublished)")
    fun aroundAnalyticsEventPublished(pjp: ProceedingJoinPoint): Any? {
        val result = pjp.proceed()
        try {
            mixpanelEventPublisher.publish(
                userId = UUID.randomUUID().toString(),
                event = extractAnalyticsEvent(pjp),
            )
        } catch (e: Exception) {
            log.error("Failed to publish analytics event", e)
        }
        return result
    }

    private fun extractAnalyticsEvent(pjp: ProceedingJoinPoint): AnalyticsEvent {
        val signature = pjp.signature as MethodSignature
        val method = signature.method
        val analyticsEventPublished = method.getAnnotation(AnalyticsEventPublished::class.java)
        return analyticsEventPublished.event
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(MixpanelEventAspect::class.java)
    }
}
