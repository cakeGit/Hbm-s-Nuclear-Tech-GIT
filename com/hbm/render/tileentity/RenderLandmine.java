package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.blocks.ModBlocks;
import com.hbm.main.ResourceManager;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderLandmine extends TileEntitySpecialRenderer {

    @Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f)
    {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y, z + 0.5D);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glRotatef(180, 0F, 1F, 0F);

		Block block = tileEntity.getWorldObj().getBlock(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);

		if(block == ModBlocks.mine_ap) {
			GL11.glScaled(1.5F, 1.5F, 1.5F);
			bindTexture(ResourceManager.mine_ap_tex);
        	ResourceManager.mine_ap.renderAll();
		}
		if(block == ModBlocks.mine_he) {
			bindTexture(ResourceManager.mine_he_tex);
        	ResourceManager.mine_he.renderAll();
		}
		if(block == ModBlocks.mine_shrap) {
			bindTexture(ResourceManager.mine_shrap_tex);
        	ResourceManager.mine_he.renderAll();
		}

        GL11.glPopMatrix();
    }

}