package mybatis.demo.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import mybatis.demo.controller.StudentController;


@Component
@Aspect
public class LogAspect {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);
	
	
	@Before("within(mybatis.demo.controller.*)")
	public void before(JoinPoint joinPoint) {
		// 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        LOGGER.info("请求地址 : " + request.getRequestURL().toString());
        LOGGER.info("方法描述 : ");
        LOGGER.info("HTTP METHOD : " + request.getMethod());
        LOGGER.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        LOGGER.info("参数 : " + Arrays.toString(joinPoint.getArgs()));

	}
	
	@After("within(mybatis.demo.controller.*)")
	public void after(JoinPoint joinPoint){ 
		System.out.println("后置最终通知执行了！");
	}  
}
