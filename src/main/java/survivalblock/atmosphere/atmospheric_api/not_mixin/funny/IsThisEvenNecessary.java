package survivalblock.atmosphere.atmospheric_api.not_mixin.funny;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SuppressWarnings("unused")
@Retention(RetentionPolicy.SOURCE)
public @interface IsThisEvenNecessary {

    Levels value() default Levels.NORMAL;

    @SuppressWarnings("unused")
    enum Levels {
        NORMAL,
        PROBABLY_NOT,
        BASCIALLY_DEPRECATED,
        WHAT_IS_GOING_ON
    }
}

