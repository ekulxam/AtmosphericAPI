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
//? if >=1.21.9 {
package survivalblock.atmosphere.atmospheric_api.not_mixin.particle.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.ReportedException;
import net.minecraft.client.Camera;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.client.particle.ParticleGroup;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.renderer.culling.Frustum;
//~ if >=26 'renderer.state' -> 'renderer.state.level' {
import net.minecraft.client.renderer.state.level.ParticleGroupRenderState;
import net.minecraft.client.renderer.state.level.QuadParticleRenderState;
//~}
import survivalblock.atmosphere.atmospheric_api.not_mixin.AtmosphericAPI;
import survivalblock.atmosphere.atmospheric_api.not_mixin.particle.client.DirectionalParticle;

import java.util.Objects;

@Environment(EnvType.CLIENT)
public class DirectionalParticleRenderer extends ParticleGroup<DirectionalParticle> {

    public static final ParticleRenderType DIRECTIONAL = new ParticleRenderType(AtmosphericAPI.id("directional").toString());

    protected final ParticleRenderType particleType;
    protected final QuadParticleRenderState renderState = new QuadParticleRenderState();

    public DirectionalParticleRenderer(ParticleEngine engine, ParticleRenderType renderType) {
        super(engine);
        this.particleType = renderType;
    }

    public DirectionalParticleRenderer(ParticleEngine engine) {
        this(engine, DIRECTIONAL);
    }

    /**
     * @see net.minecraft.client.particle.QuadParticleGroup#extractRenderState(Frustum, Camera, float)
     */
    @Override
    public ParticleGroupRenderState extractRenderState(Frustum frustum, Camera camera, float tickProgress) {
        for (DirectionalParticle directionalParticle : this.particles) {
            if (frustum.pointInFrustum(directionalParticle.getX(), directionalParticle.getY(), directionalParticle.getZ())) {
                try {
                    directionalParticle.render(this.renderState, camera, tickProgress);
                } catch (Throwable throwable) {
                    CrashReport crashReport = CrashReport.forThrowable(throwable, "Rendering Particle");
                    CrashReportCategory crashReportCategory = crashReport.addCategory("Particle being rendered");
                    crashReportCategory.setDetail("Particle", Objects.requireNonNull(directionalParticle)::toString);
                    crashReportCategory.setDetail("Particle Type", Objects.requireNonNull(this.particleType)::toString);
                    throw new ReportedException(crashReport);
                }
            }
        }

        return this.renderState;
    }
}
//?}