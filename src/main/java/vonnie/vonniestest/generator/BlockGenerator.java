package vonnie.vonniestest.generator;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vonnie.vonniestest.tools.GenericBlock;
import vonnie.vonniestest.vonniestest;

import javax.annotation.Nullable;
import java.util.List;

public class BlockGenerator extends GenericBlock implements ITileEntityProvider {

    public static final PropertyDirection FACING_HORIZ = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public static final ResourceLocation GENERATOR = new ResourceLocation(vonniestest.MODID, "generator");

    public BlockGenerator() {
        super(Material.IRON);
        setRegistryName(GENERATOR);
        setTranslationKey(vonniestest.MODID + ".generator");
        setHarvestLevel("pickaxe", 1);


        setDefaultState(blockState.getBaseState().withProperty(FACING_HORIZ, EnumFacing.NORTH));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        NBTTagCompound tagCompound = stack.getTagCompound();
        if (tagCompound != null) {
            int energy = tagCompound.getInteger("energy");
            addInformationLocalized(tooltip, "message.vonniestest.generator", energy);
        }
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileGenerator();
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING_HORIZ, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING_HORIZ);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING_HORIZ, EnumFacing.byIndex((meta & 3) + 2));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING_HORIZ).getIndex() - 2;
    }
}