package com.mk.examples.designpattern.strategy;

import static com.tvd12.ezyfox.bean.EzyBeanAutoConfig.LOGGER;

/**
 * Lambda implementation for enum strategy pattern.
 */
//@Slf4j
public class LambdaStrategy {

    /**
     * Enum to demonstrate strategy pattern.
     */
    public enum Strategy implements DragonSlayingStrategy {
        MeleeStrategy(() -> LOGGER.info(
                "With your Excalibur you severe the dragon's head!")),
        ProjectileStrategy(() -> LOGGER.info(
                "You shoot the dragon with the magical crossbow and it falls dead on the ground!")),
        SpellStrategy(() -> LOGGER.info(
                "You cast the spell of disintegration and the dragon vaporizes in a pile of dust!"));

        private final DragonSlayingStrategy dragonSlayingStrategy;

        Strategy(DragonSlayingStrategy dragonSlayingStrategy) {
            this.dragonSlayingStrategy = dragonSlayingStrategy;
        }

        @Override
        public void execute() {
            dragonSlayingStrategy.execute();
        }
    }
}
