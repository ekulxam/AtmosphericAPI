package survivalblock.atmosphere.atmospheric_api.not_mixin.funny;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SuppressWarnings("unused")
@Retention(RetentionPolicy.SOURCE)
public @interface IsThisEvenNecessary {

    LevelsOfUnnecessity value() default LevelsOfUnnecessity.NORMAL;

    @SuppressWarnings("unused")
    enum LevelsOfUnnecessity {
        NORMAL,
        PROBABLY_NOT,
        BASCIALLY_DEPRECATED,
        WHAT_IS_GOING_ON
    }
}

