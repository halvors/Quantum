package org.halvors.nuclearphysics.client.gui.modular.component;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface IProgressInfoHandler {
    double getProgress();
}