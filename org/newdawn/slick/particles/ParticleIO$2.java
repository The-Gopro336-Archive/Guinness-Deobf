package org.newdawn.slick.particles;

static final class ParticleIO$2 implements ConfigurableEmitterFactory {
    @Override
    public ConfigurableEmitter createEmitter(final String name) {
        return new ConfigurableEmitter(name);
    }
}