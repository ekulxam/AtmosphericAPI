/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.funny;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This is used when a public static field should only be changed by the project
 * that it is defined in and should be treated as final elsewhere
 */
@SuppressWarnings("unused")
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface ReadOnly {

    String additionalInformation() default "";
}
