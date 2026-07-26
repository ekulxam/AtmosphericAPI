/*
 * Copyright (c) 21:56 UTC 26 July 2026 - present ekulxam
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package survivalblock.atmosphere.atmospheric_api.not_mixin.entity;

/**
 * Originally from ATTA-V
 * <p>
 * This is a reference to the {@code ShieldboardEntity} from Shield Surfing
 */
public interface ControlBoarder {

    @SuppressWarnings("unused")
    default void setInputs(){
        this.setInputs(false, false, false, false);
    }

    void setInputs(boolean pressingLeft, boolean pressingRight, boolean pressingForward, boolean pressingBack);
}
