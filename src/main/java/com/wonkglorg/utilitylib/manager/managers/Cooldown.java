package com.wonkglorg.utilitylib.manager.managers;

public class Cooldown {

    private final long cooldown;
    private final long startTime;

    public Cooldown(long cooldown) {
        this.cooldown = cooldown;
        this.startTime = System.currentTimeMillis();
    }

    public boolean isExpired() {
        return System.currentTimeMillis() - startTime > cooldown;
    }

    public long getRemainingTime() {
        return cooldown - (System.currentTimeMillis() - startTime);
    }
}
