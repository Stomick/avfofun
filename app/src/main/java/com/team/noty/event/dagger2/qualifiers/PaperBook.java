package com.team.noty.event.dagger2.qualifiers;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface PaperBook {
    enum TYPE {
        USERS, EVENTCOMM, BAN
    }
    TYPE value() default TYPE.USERS;
}
