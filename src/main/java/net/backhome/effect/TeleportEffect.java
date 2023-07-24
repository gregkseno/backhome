package net.backhome.effect;

import java.util.NoSuchElementException;
import java.util.Optional;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;


public class TeleportEffect extends StatusEffect {
    
    public TeleportEffect(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity pLivingEntity, int pAmplitfier){
        System.out.println(pLivingEntity.getPos());
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        // Verify we are processing the use action on the logical server
        if (entity instanceof ServerPlayerEntity){
            // Cast to Server* class
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) entity;
            ServerWorld serverWorld = serverPlayerEntity.getServerWorld();

            if (serverWorld.getRegistryKey() !=  World.OVERWORLD)
                serverWorld = serverWorld.getServer().getWorld(World.OVERWORLD);
            
            // Teleportation
            int res = teleprot(serverPlayerEntity, serverWorld);

            // There is no spawn point
            if (res == 0){
                // TODO: Add random teleport
                // Calculate current position and plays there fail sound
                BlockPos current_pos = new BlockPos((int) serverPlayerEntity.getX(), (int) serverPlayerEntity.getY(), (int) serverPlayerEntity.getZ());
                serverWorld.playSound(null, current_pos, SoundEvents.BLOCK_DISPENSER_FAIL, SoundCategory.PLAYERS, 1.0F, 1.0F);
            }
        }   
    }

    // Returns 1 if teleport was successful, else 0
    private int teleprot(ServerPlayerEntity serverPlayerEntity, ServerWorld serverWorld) {
            // Get spawn postition
            BlockPos spawn_position = serverPlayerEntity.getSpawnPointPosition();
            if (spawn_position == null)
                return 0;

            // Calculate nearest suitable block to teleport
            Optional<Vec3d> opt_suitable_pos = PlayerEntity.findRespawnPosition(serverWorld, spawn_position, serverPlayerEntity.getSpawnAngle(), false, false);
            if (!opt_suitable_pos.isPresent()) 
                return 0;

            // Convert Optional<Vec3d> to Vec3d
            Vec3d suitable_pos = opt_suitable_pos.get();

            // Teleport and play sound of Enderman
            serverPlayerEntity.teleport(serverWorld, suitable_pos.getX(), suitable_pos.getY(), suitable_pos.getZ(), serverPlayerEntity.getYaw(), 1.0F);
            serverWorld.playSound(null, spawn_position, SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
            return 1;
        }
}