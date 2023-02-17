package aspectj.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class TrackOperation{
    public void myadviceBefore(JoinPoint jp)//it is advice
    {
        System.out.println("before additional concern");
        //System.out.println("Method Signature: "  + jp.getSignature());
    }

    public void myadviceAfter(JoinPoint jp)
    {
        System.out.println("after additional concern");
    }

    public void myadviceAfterReturning(JoinPoint jp,Object result)//it is advice (after advice)
    {
        System.out.println("AfterReturning additional concern");
        System.out.println("Method Signature: "  + jp.getSignature());
        System.out.println("Result in advice: "+result);
        System.out.println("end of after returning advice...");
    }

    public Object myadviceAround(ProceedingJoinPoint pjp) throws Throwable
    {
        System.out.println("around Additional Concern Before calling actual method");
        Object obj=pjp.proceed();
        System.out.println("around Additional Concern After calling actual method");
        return obj;
    }
}