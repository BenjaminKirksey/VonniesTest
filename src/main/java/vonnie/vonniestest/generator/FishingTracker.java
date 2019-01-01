/*package vonnie.vonniestest.generator;

import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.*;

public class FishingTracker {

    public static final FishingTracker instance = new FishingTracker();

    private Map<Integer, Map<BlockPos, Set<UUID>>> tracking = new HashMap<>();

    public void resetFishing() {
        tracking.clear();
    }

    public void registerFishing(Integer dimension, BlockPos pos, UUID entity) {
        if (!tracking.containsKey(dimension)){
            tracking.put(dimension, new HashMap<>());
        }
        Map<BlockPos, Set<UUID>> dimensionTracking = tracking.get(dimension);
        if (!dimensionTracking.containsKey(pos)) {
            dimensionTracking.put(pos, new HashSet<>());
        }
        dimensionTracking.get(pos).add(entity);
    }
    public void removeFishing(Integer dimension, BlockPos pos) {
        if (tracking.containsKey(dimension)) {
            tracking.get(dimension).remove(pos);
        }
    }

    public void clearFishing(Integer dimension, BlockPos pos) {
        if (tracking.containsKey(dimension)) {
            Map<BlockPos, Set<UUID>> dimensionTracking = tracking.get(dimension);
            if (dimensionTracking.containsKey(pos)) {
                dimensionTracking.get(pos).clear();
            }
        }
    }

    @SubscribeEvent
    public void onFish(ItemFishedEvent event) {
        int amountFish = event.getRodDamage();
        EntityFishHook entity = event.getHookEntity();
        World world = entity.world;
        int dimension = world.provider.getDimension();
        System.out.println("The dimension is " + dimension);
        System.out.println("The rod damage is " + amountFish);

        if (amountFish > 0 && tracking.containsKey(dimension)) {
            Map<BlockPos, Set<UUID>> dimensionTracking = tracking.get(dimension);
            for (Map.Entry<BlockPos, Set<UUID>> entry : dimensionTracking.entrySet()) {
                if (entry.getValue().contains(entity.getUniqueID())){
                    if (world.isBlockLoaded(entry.getKey())) {
                        TileEntity tileEntity = world.getTileEntity(entry.getKey());
                        if (tileEntity instanceof TileGenerator) {
                            ((TileGenerator) tileEntity).senseFishing(entity, amountFish);
                        }
                    }
                }
            }
        }


    }
}
*/