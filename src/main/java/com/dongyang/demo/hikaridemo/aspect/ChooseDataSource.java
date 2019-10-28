package com.dongyang.demo.hikaridemo.aspect;

import com.dongyang.demo.hikaridemo.enums.DataSourceEnum;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author dongyang.hu
 * @date 2019/10/24 20:09
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ChooseDataSource {
    DataSourceEnum value() default DataSourceEnum.TEST_DB_1;
}
