package tech.jiangchen.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(2)
public class JlifAspect {

    @Pointcut(value = "@annotation(tech.jiangchen.aop.Jlif)")
    public void access() {

    }

    @Around("@annotation(jlif)")
    public Object around(ProceedingJoinPoint pjp, Jlif jlif) {
        //获取注解里的值
        System.out.println("second around:" + jlif.desc());
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    @Before("access()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        System.out.println("second before");
    }

    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
    @After("access()")
    public void after(JoinPoint jp) {
        System.out.println("自定义注解方法最后执行.....");
    }

    @AfterReturning(returning = "ret", pointcut = "access()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        System.out.println("自定义注解方法的返回值 : " + ret);
    }

    //后置异常通知
    @AfterThrowing("access()")
    public void throwss(JoinPoint jp) {
        System.out.println("自定义注解方法异常时执行.....");
    }

}
