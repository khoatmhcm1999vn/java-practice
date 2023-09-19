package com.mk.examples.designpattern.strategy;

import lombok.extern.slf4j.Slf4j;

import static com.tvd12.ezyfox.bean.EzyBeanAutoConfig.LOGGER;

/**
 * Spell strategy.
 */
@Slf4j
public class SpellStrategy implements DragonSlayingStrategy {

    @Override
    public void execute() {
        LOGGER.info("You cast the spell of disintegration and the dragon vaporizes in a pile of dust!");
    }

}
