package com.cpa.project.Entities.Spells;

import com.badlogic.gdx.math.Vector2;
import com.cpa.project.Entities.Entity;
import com.cpa.project.State.PlayState;
import com.cpa.project.Utils.SonicWaveProps;

public class SonicWave extends Spell {

    public static float MAX_DISTANCE_AWAY = 400;
    protected final float DEFAULT_DAMAGE = 0;
    // Cooldown for the spell
    protected final float DEFAULT_COOLDOWN = 5f;
    // SPEED of displacing the enemies
    protected final float DEFAULT_SPEED = 500;

    public SonicWave() {
        this.timeToReady = 0;
        this.speed = this.DEFAULT_SPEED;
        this.spellCooldown = this.DEFAULT_COOLDOWN;
        this.damage = this.DEFAULT_DAMAGE;
        this.spellName = "SonicWave";
        this.autoCast = false;
    }

    @Override
    public void cast() {
        for (Entity enemy : PlayState.enemies) {
            Vector2 playerPosition = PlayState.player.getPosition();
            Vector2 enemyPosition = enemy.getPosition();
            Vector2 direction = enemyPosition.sub(playerPosition);
            if (direction.len() < 300) {
                SonicWaveProps props = new SonicWaveProps(DEFAULT_SPEED, playerPosition, direction.nor());
                PlayState.affectedBySonicWave.put(enemy, props);
            }
        }
        this.timeToReady = DEFAULT_COOLDOWN;
    }

}
