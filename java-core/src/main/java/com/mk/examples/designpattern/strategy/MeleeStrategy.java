package com.mk.examples.designpattern.strategy;

import static com.tvd12.ezyfox.bean.EzyBeanAutoConfig.LOGGER;

/**
 * Melee strategy.
 */
public class MeleeStrategy implements DragonSlayingStrategy {

    @Override
    public void execute() {
        LOGGER.info("With your Excalibur you sever the dragon's head!");
    }
}
