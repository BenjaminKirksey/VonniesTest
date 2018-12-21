package vonnie.vonniestest.tools;

import net.minecraft.nbt.NBTTagCompound;

public interface IRestorableTileEntitiy {
    void readRestorableFromNBT(NBTTagCompound compound);

    void writeRestorableToNBT(NBTTagCompound compound);
}
