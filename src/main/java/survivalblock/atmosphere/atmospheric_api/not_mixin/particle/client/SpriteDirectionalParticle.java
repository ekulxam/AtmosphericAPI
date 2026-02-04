/*
 * All Rights Reserved
 *
 * Copyright (c) 2024-present ekulxam
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