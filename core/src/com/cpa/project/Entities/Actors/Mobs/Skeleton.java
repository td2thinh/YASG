package com.cpa.project.Entities.Actors.Mobs;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.cpa.project.Entities.Entity;

import static com.cpa.project.Survivors.audioHandler;

public class Skeleton extends Entity {

    public float timeToNextPath = 0;

    private String soundName ;
    private Sound roamingSound;
    public Skeleton(Vector2 position, Sprite sprite, float speed, float damage, float health ) {
        super(position, sprite, speed, damage);
        this.health = health;
        this.maxHealth = health;
        this.entityType = EntityType.ENEMY;
        this.soundName = "skeletonRoaming + " + this.hashCode();
        this.roamingSound = audioHandler.loadSound("audio/monster/Monster-1.wav");
    }

    public Skeleton(Vector2 position, Sprite sprite ) {
        super(position, sprite);
        this.speed = 20;
        this.health = 100;
        this.maxHealth = 100;
        this.damage = 10;
        this.entityType = EntityType.ENEMY;
        this.velocity = new Vector2(0, 0);
        this.soundName = "skeletonRoaming + " + this.hashCode();
        this.roamingSound = audioHandler.loadSound("audio/monster/Monster-1.wav");
    }

    @Override
    public void update(float dt) {
        this.sprite.translate(this.velocity.x * this.speed * dt, this.velocity.y * this.speed * dt);

    }

    public void moveTowardsPlayer(Vector2 playerPosition) {
        Vector2 direction = new Vector2(playerPosition.x - this.getPosition().x, playerPosition.y - this.getPosition().y);
        direction.nor();
        this.velocity = direction;
    }


    @Override
    public void collidesWith(Entity other) {
        if (other.getEntityType() == EntityType.PLAYER_PROJECTILE) {
            this.setHealth(this.getHealth() - other.getDamage());
        }
        // we already  do this in player class
//        if (other.getEntityType() == EntityType.PLAYER) {
//           other.setHealth(other.getHealth() - this.getDamage());
//        }
    }

    @Override
    public Entity clone() {
        return new Skeleton(new Vector2(this.getSprite().getX(),
                this.getSprite().getY()),
                this.sprite, this.speed, this.damage, this.health);
    }

    public void handleSound(Vector2 position) {
        if (this.getPosition().dst(position) < 500 ){
            if (audioHandler.hasSound(soundName)) {
                audioHandler.playSoundEffect(soundName);
            } else {
                audioHandler.addSoundEffect(soundName, roamingSound);
            }
        }
    }
}
