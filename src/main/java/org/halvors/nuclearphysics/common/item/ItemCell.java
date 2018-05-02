package org.halvors.nuclearphysics.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStackSimple;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.halvors.nuclearphysics.common.NuclearPhysics;
import org.halvors.nuclearphysics.common.init.ModFluids;
import org.halvors.nuclearphysics.common.utility.FluidUtility;
import org.halvors.nuclearphysics.common.utility.LanguageUtility;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemCell extends ItemTooltip {
    public static final int capacity = 200;

    public ItemCell() {
        super("cell");
        
        setContainerItem(this);
    }

    @Override
    public void registerItemModel() {
        NuclearPhysics.getProxy().registerItemRenderer(this, 0, name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(final ItemStack itemStack, final EntityPlayer player, final List<String> list, final boolean flag) {
        final FluidStack fluidStack = FluidUtil.getFluidContained(itemStack);

        if (fluidStack != null) {
            list.add(LanguageUtility.transelate(getUnlocalizedName(itemStack) + ".tooltip", fluidStack.getLocalizedName()));
        } else {
            list.add(LanguageUtility.transelate("tooltip.empty"));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(final @Nonnull Item item, final CreativeTabs tab, final NonNullList<ItemStack> list) {
        for (EnumCell type : EnumCell.values()) {
            list.add(type.getFluid() == null ? new ItemStack(item) : FluidUtility.getFilledCell(type.getFluid()));
        }
    }

    @Override
    @Nonnull
    public ICapabilityProvider initCapabilities(final ItemStack itemStack, final NBTTagCompound tag) {
        return new FluidHandlerItemStackSimple(itemStack, capacity);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public enum EnumCell {
        EMPTY,
        DEUTERIUM(ModFluids.deuterium),
        TRITIUM(ModFluids.tritium),
        WATER(FluidRegistry.WATER);

        private Fluid fluid;

        EnumCell() {

        }

        EnumCell(final Fluid fluid) {
            this.fluid = fluid;
        }

        public String getName() {
            return name().toLowerCase();
        }

        public Fluid getFluid() {
            return fluid;
        }
    }
}