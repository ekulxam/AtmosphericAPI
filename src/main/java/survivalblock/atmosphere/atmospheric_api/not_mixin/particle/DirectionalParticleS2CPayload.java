package survivalblock.atmosphere.atmospheric_api.not_mixin.particle;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;
import survivalblock.atmosphere.atmospheric_api.not_mixin.datafixer.AtmosphericPacketCodecs;
import survivalblock.atmosphere.atmospheric_api.not_mixin.util.PitchYawPair;

public record DirectionalParticleS2CPayload(ParticleEffect particleEffect, double x, double y, double z, float pitch, float yaw, double deltaX, double deltaY, double deltaZ, double velocityX, double velocityY, double velocityZ, boolean force /*? >=1.21.5 {*/ , boolean canSpawnOnMinimal /*?}*/) implements CustomPayload {

    public static final CustomPayload.Id<DirectionalParticleS2CPayload> ID = new CustomPayload.Id<>(AtmosphericAPI.id("directional_particle_s2c"));

    public static final PacketCodec<RegistryByteBuf, DirectionalParticleS2CPayload> PACKET_CODEC = AtmosphericPacketCodecs.tuple(
            ParticleTypes.PACKET_CODEC, payload -> payload.particleEffect,
            PacketCodecs.DOUBLE, payload -> payload.x,
            PacketCodecs.DOUBLE, payload -> payload.y,
            PacketCodecs.DOUBLE, payload -> payload.z,
            PacketCodecs.FLOAT, payload -> payload.pitch,
            PacketCodecs.FLOAT, payload -> payload.yaw,
            PacketCodecs.DOUBLE, payload -> payload.deltaX,
            PacketCodecs.DOUBLE, payload -> payload.deltaY,
            PacketCodecs.DOUBLE, payload -> payload.deltaZ,
            PacketCodecs.DOUBLE, payload -> payload.velocityX,
            PacketCodecs.DOUBLE, payload -> payload.velocityY,
            PacketCodecs.DOUBLE, payload -> payload.velocityZ,
            PacketCodecs./*? =1.21.1 {*/  /*BOOL *//*?} else {*/ BOOLEAN /*?}*/, payload -> payload.force,
            /*? >=1.21.5 {*/ PacketCodecs.BOOLEAN, payload -> payload.canSpawnOnMinimal, /*?}*/
            DirectionalParticleS2CPayload::new
    );

    @Override
    public Id<? extends DirectionalParticleS2CPayload> getId() {
        return ID;
    }

    public static class Builder {

        protected ParticleEffect particleEffect = null;
        protected double x = 0;
        protected double y = 0;
        protected double z = 0;
        protected float pitch = 0;
        protected float yaw = 0;
        protected double deltaX = 0;
        protected double deltaY = 0;
        protected double deltaZ = 0;
        protected double velocityX = 0;
        protected double velocityY = 0;
        protected double velocityZ = 0;
        protected boolean force = false;
        /*? >=1.21.5 {*/ protected boolean canSpawnOnMinimal = false; /*?}*/

        public Builder() {
        }

        public Builder particleEffect(ParticleEffect particleEffect) {
            this.particleEffect = particleEffect;
            return this;
        }

        public Builder pos(Vec3d vec3d) {
            return this.pos(vec3d.x, vec3d.y, vec3d.z);
        }

        public Builder pos(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
            return this;
        }

        public Builder direction(PitchYawPair pitchYawPair) {
           return this.direction(pitchYawPair.pitch(), pitchYawPair.yaw());
        }

        public Builder direction(float pitch, float yaw) {
            this.pitch = pitch;
            this.yaw = yaw;
            return this;
        }

        public Builder deltaPos(Vec3d delta) {
            return this.deltaPos(delta.x, delta.y, delta.z);
        }

        public Builder deltaPos(double deltaX, double deltaY, double deltaZ) {
            this.deltaX = deltaX;
            this.deltaY = deltaY;
            this.deltaZ = deltaZ;
            return this;
        }

        public Builder velocity(Vec3d velocity) {
            return this.velocity(velocity.x, velocity.y, velocity.z);
        }

        public Builder velocity(double velocityX, double velocityY, double velocityZ) {
            this.velocityX = velocityX;
            this.velocityY = velocityY;
            this.velocityZ = velocityZ;
            return this;
        }

        public Builder force(boolean force) {
            this.force = force;
            return this;
        }

        //? if >=1.21.5 {
        public Builder canSpawnOnMinimal(boolean canSpawnOnMinimal) {
            this.canSpawnOnMinimal = canSpawnOnMinimal;
            return this;
        }
        //?}

        public DirectionalParticleS2CPayload build() {
            return new DirectionalParticleS2CPayload(this.particleEffect, this.x, this.y, this.z, this.pitch, this.yaw, this.deltaX, this.deltaY, this.deltaZ, this.velocityX, this.velocityY, this.velocityZ, this.force/*? >=1.21.5 {*/ , canSpawnOnMinimal /*?}*/);
        }
    }
}
