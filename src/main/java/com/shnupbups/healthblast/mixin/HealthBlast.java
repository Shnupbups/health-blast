package com.shnupbups.healthblast.mixin;

public class HealthBlast {
	public static final float DEFAULT_CREEPER_HEALTH = 20f;
	public static final float DEFAULT_EXPLOSION_MULTIPLIER = 1f;
	public static final float MIN_EXPLOSION_MULTIPLIER = 0.3f;
	public static final float HEALTH_EXPLOSION_MULTIPLIER = (DEFAULT_EXPLOSION_MULTIPLIER - MIN_EXPLOSION_MULTIPLIER) / DEFAULT_CREEPER_HEALTH;
	public static final float MAX_EXPLOSION_MODIFIER = 10f;

	public static float getExplosionMultiplier(float health) {
		return Math.min((HEALTH_EXPLOSION_MULTIPLIER * health) + MIN_EXPLOSION_MULTIPLIER, MAX_EXPLOSION_MODIFIER);
	}
}
