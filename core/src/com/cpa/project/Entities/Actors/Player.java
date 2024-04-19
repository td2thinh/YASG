package com.cpa.project.Entities.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.cpa.project.Entities.Entity;
import com.cpa.project.Entities.Projectiles.BasicFireBall;
import com.cpa.project.Entities.Projectiles.Projectile;
import com.cpa.project.Entities.Spells.AutoFireBall;
import com.cpa.project.Entities.Spells.Heal;
import com.cpa.project.Entities.Spells.SonicWave;
import com.cpa.project.Entities.Spells.Spell;
import com.cpa.project.State.PlayState;
import com.cpa.project.UI.ProgressBar;

import javax.swing.plaf.TextUI;
import java.util.HashMap;
import java.util.Map;

public class Player extends Entity {
    protected final float DEFAULT_AS = 0.5f;
    protected float ATT_SPEED;
    protected Map<String, Spell> spells;
    protected float attackRate;
    protected float experience;
    protected float experienceToNextLevel;
    protected int level;

    public Player(Vector2 position, Sprite sprite) {
        super(position, sprite);
        this.entityType = EntityType.PLAYER;
        this.speed = 100;
        this.health = 100;
        this.maxHealth = 100;
        this.damage = 10;
        this.spells = new HashMap<>();
        this.spells.put("AutoFireBall", new AutoFireBall());
        this.spells.put("SonicWave", new SonicWave());
        this.spells.put("Heal", new Heal());
        this.experience = 0;
        this.experienceToNextLevel = 100;
        this.level = 1;
        this.attackRate = 0;
        this.ATT_SPEED = DEFAULT_AS;
    }


    public void move(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            PlayState.isPaused = !PlayState.isPaused;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.Q)) {
            if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.Z))
                this.velocity = new Vector2(-this.speed * dt, this.speed * dt).nor();
            else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S))
                this.velocity = new Vector2(-this.speed * dt, -this.speed * dt).nor();
            else
                this.velocity = new Vector2(-this.speed * dt, 0).nor();
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.Z))
                this.velocity = new Vector2(this.speed * dt, this.speed * dt).nor();
            else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S))
                this.velocity = new Vector2(this.speed * dt, -this.speed * dt).nor();
            else
                this.velocity = new Vector2(this.speed * dt, 0).nor();
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.Z)) {
            this.velocity = new Vector2(0, this.speed * dt).nor();
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            this.velocity = new Vector2(0, -this.speed * dt).nor();
        } else {
            this.velocity = new Vector2(0, 0);
        }
        this.sprite.translate(this.velocity.x * this.speed * dt, this.velocity.y * this.speed * dt);
    }

    @Override
    public void update(float dt) {
        if (this.experience > experienceToNextLevel) {
            this.experienceToNextLevel *= 1.5f;
            this.level++;
            this.experience = 0;
            // Increase health by 10 per level
            this.maxHealth = 100 + this.level * 10;
            // Reset health to max health
            this.health = this.maxHealth;
            // Increase damage by 5 per level
            this.damage = 10 + this.level * 5;
            // Increase attack speed by 0.05f per level
            this.ATT_SPEED = DEFAULT_AS - this.level * 0.005f;
        }
        this.move(dt);
        if (Gdx.input.isTouched()) {
            if (this.attackRate > 0) {
                this.attackRate -= dt;
            } else {
                Vector2 playerPosition = this.getPosition();
                Vector3 cursorPos3D = PlayState.topDownCamera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
                Vector2 cursorPos = new Vector2(cursorPos3D.x, cursorPos3D.y);
                Vector2 bulletDir = cursorPos.sub(playerPosition).nor();
                Projectile basicBall = new BasicFireBall(
                        playerPosition,
                        new Sprite(
                                new Texture("FireBallSmall.png")
                        ),
                        bulletDir,
                        200,
                        this.damage
                );
                PlayState.playerProjectiles.add(basicBall);
                this.attackRate = ATT_SPEED;
            }
        }

        // The way spells' cooldowns are handled is that we check the time to ready
        // if it is less than or equal to 0, we cast the spell
        // if it is greater than 0, we decrement it by dt
        // after casting the spell, we set the time to ready to the default cooldown

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            float castTime = this.spells.get("SonicWave").getTimeToReady();
            castTime -= dt;
            if (castTime <= 0) {
                this.spells.get("SonicWave").cast();
            } else {
                this.spells.get("SonicWave").setSpellTime(castTime);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.H)) {
            float castTime = this.spells.get("Heal").getTimeToReady();
            castTime -= dt;
            if (castTime <= 0) {
                this.spells.get("Heal").cast();
            } else {
                this.spells.get("Heal").setSpellTime(castTime);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.F)) {
            float castTime = this.spells.get("AutoFireBall").getTimeToReady();
            castTime -= dt;
            if (castTime <= 0) {
                this.spells.get("AutoFireBall").cast();
            } else {
                this.spells.get("AutoFireBall").setSpellTime(castTime);
            }
        }

        // Loop for spells with autocast on
        this.spells.values().forEach(spell ->
        {
            float castTime = spell.getTimeToReady();
            castTime -= dt;
            if (castTime <= 0) {
                if (spell.isAutoCast()) {
                    spell.cast();
                }
            } else {
                spell.setSpellTime(castTime);
            }
        });

    }

    @Override
    public void collidesWith(Entity other) {
        // More repulsive force
        // MORE PHYSICS BITCHES
        if (other.getEntityType() == EntityType.ENEMY) {
            this.setHealth(this.getHealth() - other.getDamage());
        }
    }

    @Override
    public Entity clone() {
        return new Player(new Vector2(this.getSprite().getX(), this.getSprite().getY()), this.sprite);
    }


    public void setExperience(float experience) {
        this.experience = experience;
    }

    public void addExperience(float experience) {
        this.experience += experience;
    }

    public float getExperience() {
        return experience;
    }

    public void setLevel(int level) {
        this.level = level;
        // Increase health by 10 per level
        this.maxHealth = 100 + this.level * 10;
        // Reset health to max health
        this.health = this.maxHealth;
        // Increase damage by 5 per level
        this.damage = 10 + this.level * 5;
        // Increase attack speed by 0.05f per level
        this.ATT_SPEED = DEFAULT_AS - this.level * 0.005f;
    }

    public int getLevel() {
        return level;
    }

    public void addSpell(Spell spell) {
        this.spells.put(spell.getSpellName(), spell);
    }

    public void setSpells(Map<String, Spell> spells) {
        this.spells = spells;
    }

    public Map<String, Spell> getSpells() {
        return spells;
    }

    public void setAttackRate(float attackRate) {
        this.attackRate = attackRate;
    }

    public float getAttackRate() {
        return attackRate;
    }

    public void setExperienceToNextLevel(float experienceToNextLevel) {
        this.experienceToNextLevel = experienceToNextLevel;
    }

    public float getExperienceToNextLevel() {
        return experienceToNextLevel;
    }

    public Texture getHealthBar() {
        int width = 300;
        int height = 20;
        return ProgressBar.makeBarTexture(width, height, this.health / this.maxHealth, Color.RED);
    }

    public Texture getXPBar() {
        int width = 300;
        int height = 20;
        return ProgressBar.makeBarTexture(width, height, this.experience / this.experienceToNextLevel, Color.YELLOW);
    }

}
