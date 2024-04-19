package com.cpa.project.Entities.Spells;

public abstract class Spell {

    protected String spellName;
    protected float timeToReady;

    // Cooldown for the spell
    protected float spellCooldown;
    // Speed of the spell
    protected float speed;
    // Damage of the spell
    protected float damage;

    // Auto casting or not
    protected boolean autoCast;

    //Cast behaviour
    public abstract void cast();

    public float getTimeToReady() {
        return timeToReady;
    }

    public void setSpellTime(float rate) {
        this.timeToReady = rate;
    }

    public float getSpellCooldown() {
        return spellCooldown;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public String getSpellName() {
        return spellName;
    }

    public boolean isAutoCast() {
        return autoCast;
    }

    public void toggleAutoCast() {
        this.autoCast = !this.autoCast;
    }
}
