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
package survivalblock.atmosphere.atmospheric_api.not_mixin.damage_type;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;

public class AtmosphericDamageTypeTags {
    public static final TagKey<DamageType> BYPASSES_CREATIVE = TagKey.create(Registries.DAMAGE_TYPE, AtmosphericAPI.id("bypasses_creative"));
    public static final TagKey<DamageType> BYPASSES_SPECTATOR = TagKey.create(Registries.DAMAGE_TYPE, AtmosphericAPI.id("bypasses_spectator"));
}
