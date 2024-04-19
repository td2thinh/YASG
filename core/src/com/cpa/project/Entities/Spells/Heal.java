package com.cpa.project.Entities.Spells;

import com.cpa.project.State.PlayState;

public class Heal extends Spell{
    protected final float DEFAULT_DAMAGE = 20;
    // Cooldown for the spell
    protected final float DEFAULT_COOLDOWN = 5f;
    // SPEED of displacing the enemies
    protected final float DEFAULT_SPEED = 500;

    public Heal() {
        this.timeToReady = 0;
        this.speed = this.DEFAULT_SPEED;
        this.spellCooldown = this.DEFAULT_COOLDOWN;
        this.damage = this.DEFAULT_DAMAGE;
        this.spellName = "Heal";
        this.autoCast = false;
    }
    @Override
    public void cast() {
        if (PlayState.player.getHealth() < PlayState.player.getMaxHealth()) {
            PlayState.player.setHealth(PlayState.player.getHealth() + this.damage);
            if (PlayState.player.getHealth() > PlayState.player.getMaxHealth()) {
                PlayState.player.setHealth(PlayState.player.getMaxHealth());
            }
        }
        this.timeToReady = DEFAULT_COOLDOWN;
    }


}
