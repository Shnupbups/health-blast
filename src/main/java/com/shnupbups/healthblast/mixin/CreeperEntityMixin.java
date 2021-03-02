package com.shnupbups.healthblast.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin extends HostileEntity {
	private static final float MIN_MULTI = 0.5f;

	protected CreeperEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}

	@Redirect(method = "explode()V", at = @At(value="INVOKE", target="Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/world/explosion/Explosion$DestructionType;)Lnet/minecraft/world/explosion/Explosion;"))
	public Explosion createExplosion(World world, Entity entity, double x, double y, double z, float power, Explosion.DestructionType destructionType) {
		//world.getClosestPlayer(this, 20).sendMessage(Text.of("Explosion Power: "+power * getExplosionModifier()), true);
		return this.world.createExplosion(this, x, y, z, power * getExplosionModifier(), destructionType);
	}

	public float getExplosionModifier() {
		return MIN_MULTI + (this.getHealth() * (MIN_MULTI/20));
	}
}
