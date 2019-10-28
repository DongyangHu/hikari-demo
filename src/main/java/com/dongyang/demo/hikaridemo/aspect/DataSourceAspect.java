package com.dongyang.demo.hikaridemo.aspect;

import com.dongyang.demo.hikaridemo.config.DynamicDataSourceHolder;
import com.dongyang.demo.hikaridemo.enums.DataSourceEnum;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author dongyang.hu
 * @date 2019/10/24 20:09
 */
@Slf4j
@Aspect
@Component
public class DataSourceAspect {

    @Pointcut(value = "@annotation(ChooseDataSource)")
    public void pointcut() {

    }

    @Before("pointcut() && @annotation(chooseDataSource)")
    public void before(JoinPoint point, ChooseDataSource chooseDataSource) {
        DataSourceEnum value = chooseDataSource.value();
        DynamicDataSourceHolder.put(value.name());
        log.error("before dataSource:{}, joinPoint:{}", chooseDataSource.value(), point);
    }

    @After("pointcut() && @annotation(chooseDataSource)")
    public void after(JoinPoint point, ChooseDataSource chooseDataSource) {
        DynamicDataSourceHolder.remove();
    }
}
