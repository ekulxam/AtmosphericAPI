/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.funny;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SuppressWarnings("unused")
@Retention(RetentionPolicy.SOURCE)
public @interface ThisIsABadIdea {

    LevelsOfHorrendousness value() default LevelsOfHorrendousness.NORMAL;

    enum LevelsOfHorrendousness {
        NORMAL,
        PROBABLY,
        EXTREMELY,
        WHAT_IS_GOING_ON
    }
}

