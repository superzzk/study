package aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * A aspect class that contains before advice.
 */
@Aspect
public class TrackOperation{
    @Pointcut("execution(* Operation.*(..))")
    public void k(){}//pointcut name

    @Before("k()")//applying pointcut on before advice
    public void myadvice(JoinPoint jp)//it is advice (before advice)
    {
        System.out.println("Before concern");
        //System.out.println("Method Signature: "  + jp.getSignature());
    }
    @After("k()")//applying pointcut on after advice
    public void myadviceAfter(JoinPoint jp)//it is advice (after advice)
    {
        System.out.println("After concern");
        //System.out.println("Method Signature: "  + jp.getSignature());
    }

    @Pointcut("execution(* Operation.m*(..))")
    public void k2(){}

    @Before("k2()")
    public void myadvice2(JoinPoint jp){
        System.out.println("myadvice2 concern");
    }


    @AfterReturning(
            pointcut = "execution(* Operation.*(..))",
            returning= "result")
    public void myadviceAfterReturning(JoinPoint jp,Object result)//it is advice (after returning advice)
    {
        System.out.println("after returning concern");
        System.out.println("Method Signature: "  + jp.getSignature());
        System.out.println("Result in advice: "+result);
        System.out.println("end of after returning advice...");
    }

    @Around("k()")
    public Object myadviceAround(ProceedingJoinPoint pjp) throws Throwable
    {
        System.out.println("Around Concern Before calling actual method");
        Object obj=pjp.proceed();
        System.out.println("Around Concern After calling actual method");
        return obj;
    }
}