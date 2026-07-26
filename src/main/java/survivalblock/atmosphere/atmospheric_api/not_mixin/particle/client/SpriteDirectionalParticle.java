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
package survivalblock.atmosphere.atmospheric_api.not_mixin.particle.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public abstract class SpriteDirectionalParticle extends DirectionalParticle {
    protected TextureAtlasSprite sprite;

    @SuppressWarnings("unused")
    protected SpriteDirectionalParticle(ClientLevel clientWorld, double d, double e, double f) {
        super(clientWorld, d, e, f);
    }

    protected SpriteDirectionalParticle(ClientLevel clientWorld, double d, double e, double f, double g, double h, double i) {
        super(clientWorld, d, e, f, g, h, i);
    }

    protected void setSprite(TextureAtlasSprite sprite) {
        this.sprite = sprite;
    }

    @Override
    protected float getMinU() {
        return this.sprite.getU0();
    }

    @Override
    protected float getMaxU() {
        return this.sprite.getU1();
    }

    @Override
    protected float getMinV() {
        return this.sprite.getV0();
    }

    @Override
    protected float getMaxV() {
        return this.sprite.getV1();
    }

    public void setSprite(SpriteSet spriteProvider) {
        this.setSprite(spriteProvider.get(this.random));
    }

    public void setSpriteForAge(SpriteSet spriteProvider) {
        if (!this.removed) {
            this.setSprite(spriteProvider.get(this.age, this.lifetime));
        }
    }
}