/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.entity;

/**
 * Originally from ATTA-V
 * reference to the ShieldboardEntity from my mod Shield Surfing
 */
public interface ControlBoarder {

    @SuppressWarnings("unused")
    default void setInputs(){
        this.setInputs(false, false, false, false);
    }

    void setInputs(boolean pressingLeft, boolean pressingRight, boolean pressingForward, boolean pressingBack);
}
