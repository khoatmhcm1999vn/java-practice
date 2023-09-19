package com.mk.examples.designpattern.strategy;

import lombok.extern.slf4j.Slf4j;

import static com.tvd12.ezyfox.bean.EzyBeanAutoConfig.LOGGER;

/**
 * Projectile strategy.
 */
@Slf4j
public class ProjectileStrategy implements DragonSlayingStrategy {

    @Override
    public void execute() {
        LOGGER.info("You shoot the dragon with the magical crossbow and it falls dead on the ground!");
    }
}
