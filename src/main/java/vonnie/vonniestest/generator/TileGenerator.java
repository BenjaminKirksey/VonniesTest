package vonnie.vonniestest.generator;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.model.animation.CapabilityAnimation;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import vonnie.vonniestest.config.GeneratorConfig;
import vonnie.vonniestest.tools.IGuiTile;
import vonnie.vonniestest.tools.IRestorableTileEntitiy;
import vonnie.vonniestest.tools.MyEnergyStorage;
import vonnie.vonniestest.vonniestest;

import javax.annotation.Nullable;
import java.util.List;

public class TileGenerator extends TileEntity implements ITickable, IRestorableTileEntitiy, IGuiTile {

    private int length = (((GeneratorConfig.TRACKING_BOX_LENGTH)-1)/2);
    private int width = (((GeneratorConfig.TRACKING_BOX_WIDTH)-1)/2);
    private int height = (((GeneratorConfig.TRACKING_BOX_HEIGHT)-1)/2);
    private int trackCounter = 0;
    private AxisAlignedBB trackingBox;
    private int clientEnergy = -1;

    @Nullable
    private final IAnimationStateMachine asm;

    public TileGenerator() {
        asm = vonniestest.proxy.load(new ResourceLocation(vonniestest.MODID, "asms/block/generator.json"), ImmutableMap.of());
    }

    private MyEnergyStorage energyStorage = new MyEnergyStorage(GeneratorConfig.MAX_POWER, 0);

    @Override
    public void update() {
        if (!world.isRemote) {
            trackCounter--;
            if (trackCounter <= 0) {
                trackCounter = 20;
                findEntitiesDamage();
                //findEntitiesFishing();
            }

            sendEnergy();
        }
    }

    @Override
    public void invalidate() {
        super.invalidate();
        DamageTracker.instance.removeDamage(world.provider.getDimension(), pos);
        //FishingTracker.instance.removeFishing(world.provider.getDimension(), pos);
    }

    private void sendEnergy() {
        if (energyStorage.getEnergyStored() > 0) {
            for (EnumFacing facing : EnumFacing.VALUES) {
                TileEntity tileEntity = world.getTileEntity(pos.offset(facing));
                if (tileEntity != null && tileEntity.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite())) {
                    IEnergyStorage handler = tileEntity.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite());
                    if (handler != null && handler.canReceive()) {
                        int accepted = handler.receiveEnergy(energyStorage.getEnergyStored(), false);
                        energyStorage.consumePower(accepted);
                        if (energyStorage.getEnergyStored() <= 0) {
                            break;
                        }
                    }
                }
            }
            markDirty();
        }
    }

    public int getClientEnergy() {
        return clientEnergy;
    }
    public void setClientEnergy(int clientEnergy) {
        this.clientEnergy = clientEnergy;
    }
    public int getEnergy() {
        return energyStorage.getEnergyStored();
    }


    private void findEntitiesDamage(){
        DamageTracker.instance.clearDamage(world.provider.getDimension(), pos);

        List<EntityLivingBase> entitiesDamage = world.getEntitiesWithinAABB(EntityLivingBase.class, getTrackingBox());
        for (EntityLivingBase entity : entitiesDamage) {
            DamageTracker.instance.registerDamage(world.provider.getDimension(), pos, entity.getUniqueID());
        }
    }
/*    private void findEntitiesFishing(){
        FishingTracker.instance.clearFishing(world.provider.getDimension(), pos);

        List<EntityFishHook> entitiesFishing = world.getEntitiesWithinAABB(EntityFishHook.class, getTrackingBox());
        for (EntityFishHook entity : entitiesFishing) {
            DamageTracker.instance.registerDamage(world.provider.getDimension(), pos, entity.getUniqueID());
        }
    }*/

    private AxisAlignedBB getTrackingBox() {
        if (trackingBox == null) {
            trackingBox = new AxisAlignedBB(pos.add((length * -1),(height* -1), (width* -1)), pos.add(length, height , width));
        }
        return trackingBox;
    }

    public void senseDamage(EntityLivingBase entity, float amount) {
        if (getTrackingBox().contains(entity.getPositionVector())) {
            energyStorage.generatePower((int) (amount * GeneratorConfig.POWER_DAMAGE_FACTOR));
        }
    }
/*    public void senseFishing(EntityFishHook entity, float amountFish) {
        if (getTrackingBox().contains(entity.getPositionVector())) {
            energyStorage.generatePower((int) (amountFish * GeneratorConfig.POWER_FISHING_FACTOR));
        }
    }*/
    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        readRestorableFromNBT(compound);
    }

    @Override
    public void readRestorableFromNBT(NBTTagCompound compound) {
        energyStorage.setEnergy(compound.getInteger("energy"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        writeRestorableToNBT(compound);
        return compound;
    }

    @Override
    public void writeRestorableToNBT(NBTTagCompound compound) {
        compound.setInteger("energy", energyStorage.getEnergyStored());
    }

    @Override
    public Container createContainer(EntityPlayer player) {
        return new ContainerGenerator(player.inventory, this);
    }

    @Override
    public GuiContainer createGui(EntityPlayer player) {
        return new GuiGenerator(this, new ContainerGenerator(player.inventory, this));
    }

    public boolean canInteractWith(EntityPlayer playerIn) {
        // If we are too far away from this tile entitiy you cannot use it
        return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
    }

    @Override
    public boolean hasFastRenderer() {
        return true;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            return true;
        }
        if (capability == CapabilityAnimation.ANIMATION_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            return CapabilityEnergy.ENERGY.cast(energyStorage);
        }
        if (capability == CapabilityAnimation.ANIMATION_CAPABILITY) {
            return CapabilityAnimation.ANIMATION_CAPABILITY.cast(asm);
        }
        return super.getCapability(capability, facing);
    }
}
