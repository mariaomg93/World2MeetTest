package com.world2meettest.app.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class LoggingAspect {

	@Before("execution(* com.world2meettest.app.controllers.NaveEspacialController.getSpaceShipById(..))")
	public void logBefore(JoinPoint joinPoint) {

		Object[] signatureArgs = joinPoint.getArgs();
		for (Object signatureArg : signatureArgs) {
			try {
				if ((Long) signatureArg < 0) {
					System.out.println("El id solicitado de la nave es negativo.");
				}
			} catch (Exception e) {

			}

		}
	}
}
