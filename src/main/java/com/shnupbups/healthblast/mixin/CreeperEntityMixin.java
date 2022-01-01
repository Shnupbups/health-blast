package com.shnupbups.healthblast.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;

import com.shnupbups.healthblast.HealthBlast;

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin extends HostileEntity {
	private CreeperEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}

	@ModifyArg(method = "explode()V", at = @At(value="INVOKE", target="Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/world/explosion/Explosion$DestructionType;)Lnet/minecraft/world/explosion/Explosion;"), index = 4)
	public float multiplyExplosionPower(float power) {
		return power * HealthBlast.getExplosionMultiplier(this.getHealth());
	}
}
