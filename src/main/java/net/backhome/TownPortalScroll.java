package net.backhome;

import java.util.NoSuchElementException;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;


public class TownPortalScroll extends Item {
    
    public TownPortalScroll(Settings settings) {
        super(settings);
    }



    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        // Verify we are processing the use action on the logical server
        if (playerEntity instanceof ServerPlayerEntity){
            // Cast to Server* class
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) playerEntity;
            ServerWorld serverWorld = (ServerWorld) world;

            if (serverWorld.getRegistryKey() !=  World.OVERWORLD)
                serverWorld = serverWorld.getServer().getWorld(World.OVERWORLD);
                
            try {
                // Get spawn postition
                BlockPos spawn_position = serverPlayerEntity.getSpawnPointPosition();

                // Calculate nearest suitable block to teleport
                Vec3d suitable_pos = PlayerEntity.findRespawnPosition(serverWorld, spawn_position, serverPlayerEntity.getSpawnAngle(), false, false).get();
                
                // Teleport and play sound of Enderman
                serverPlayerEntity.teleport(serverWorld, suitable_pos.getX(), suitable_pos.getY(), suitable_pos.getZ(), serverPlayerEntity.getYaw(), 1.0F);
                serverWorld.playSound(null, spawn_position, SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                 
            } catch (NoSuchElementException e) { // There is no spawn point
                // Calculate current position and plays there fail sound
                BlockPos current_pos = new BlockPos((int) serverPlayerEntity.getX(), (int) serverPlayerEntity.getY(), (int) serverPlayerEntity.getZ());
                serverWorld.playSound(null, current_pos, SoundEvents.BLOCK_DISPENSER_FAIL, SoundCategory.PLAYERS, 1.0F, 1.0F);
                
                return TypedActionResult.fail(playerEntity.getStackInHand(hand));
            }            
        }   
        
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}