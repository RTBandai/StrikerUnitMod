// Date: 2016/04/17 21:51:19
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package strikerunit;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class SUModelArmorR extends ModelBiped
{
	//private IModelCustom tankk = AdvancedModelLoader.loadModel(new ResourceLocation("strikerunit:textures/model/su01.obj"));
	//private IModelCustom tankk;
  public SUModelArmorR()
  {
    textureWidth = 512;
    textureHeight = 256;
  }
  
  @Override
  public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
  {
      this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
      
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  float iii;
  float iii2;
  @Override
  public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
  {
	  Minecraft minecraft = FMLClientHandler.instance().getClient();
      
	  EntityPlayer entityplayer = minecraft.thePlayer;
	  GL11.glPushMatrix();
      GL11.glEnable(GL12.GL_RESCALE_NORMAL);
      GL11.glTranslatef(0, 1.5F, 0);
		GL11.glRotatef(180F, 1.0F, 0.0F, 0.0F);
		if ((entityplayer.getEquipmentInSlot(1) != null)
				&& (entityplayer.getEquipmentInSlot(1).getItem() instanceof SUItemArmorR)) {
			SUItemArmorR armor = (SUItemArmorR) entityplayer.getEquipmentInSlot(1).getItem();
		{
			GL11.glPushMatrix();
			if(p_78087_7_.isSneaking())
		      {
				GL11.glTranslatef(0F, 0.1F, 0F);
				GL11.glTranslatef(0F, 0F, -0.3F);
				GL11.glRotated(2F, 1.0F, 0.0F, 0.0F);
		      }else{
			GL11.glRotated(2F, 1.0F, 0.0F, 0.0F);
		      }
			
			armor.tankk.renderPart("mat1");
			if(iii < 360F){
				  GL11.glTranslatef(armor.pralx, armor.praly, armor.pralz);//0.15,0.3,0
		    	  GL11.glRotatef(iii, 0.0F, 1.0F, 0.0F);
		    	  GL11.glTranslatef(-armor.pralx, -armor.praly, -armor.pralz);
		 			iii = iii + armor.praspeed;
		 		}else{
		 			iii = 0F;
		 		}
			armor.tankk.renderPart("mat2");
			GL11.glPopMatrix();
		}
		{
			GL11.glPushMatrix();
			if(p_78087_7_.isSneaking())
		      {
				GL11.glTranslatef(0F, 0.1F, 0F);
				GL11.glTranslatef(0F, 0F, -0.3F);
				GL11.glRotated(-2F, 1.0F, 0.0F, 0.0F);
		      }else{
			GL11.glRotated(-2F, 1.0F, 0.0F, 0.0F);
		      }
			
			armor.tankk.renderPart("mat3");
			if(iii2 < 360F){
				  GL11.glTranslatef(armor.prarx, armor.prary, armor.prarz);
		    	  GL11.glRotatef(iii2, 0.0F, 1.0F, 0.0F);
		    	  GL11.glTranslatef(-armor.prarx, -armor.prary, -armor.prarz);
		 			iii2 = iii2 + armor.praspeed;
		 		}else{
		 			iii2 = 0F;
		 		}
			armor.tankk.renderPart("mat4");
			GL11.glPopMatrix();
		}
		{
			GL11.glPushMatrix();
			if(p_78087_7_.isSneaking())
		      {
				GL11.glTranslatef(0F, 0.0F, 0F);
				GL11.glTranslatef(0F, 1.5F, 0F);
				GL11.glRotated(20F, 1.0F, 0.0F, 0.0F);
				GL11.glTranslatef(0F, -1.5F, 0F);
				GL11.glTranslatef(0F, 0F, -0.1F);
		      }else{
		      }
			armor.tankk.renderPart("mat5");
			GL11.glPopMatrix();
		}
		{
			GL11.glPushMatrix();
			GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
			ResourceLocation resourcelocation = ((AbstractClientPlayer)entityplayer).getLocationSkin();
	        if (resourcelocation == null)
	        {
	            resourcelocation = AbstractClientPlayer.getLocationSkin("default");
	        }
	        Minecraft.getMinecraft().renderEngine.bindTexture(resourcelocation);
	        {
				GL11.glPushMatrix();
				GL11.glTranslatef(0F, 0.75F, 0F);
				//GL11.glRotated(18F, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(0F, -0.75F, 0F);
				if(p_78087_7_.isSneaking())
			      {
					GL11.glTranslatef(0F, 0.1F, 0F);
					GL11.glTranslatef(0F, 0.75F, 0F);
					//GL11.glRotated(60F, 1.0F, 0.0F, 0.0F);
					GL11.glTranslatef(0F, -0.75F, 0F);
					GL11.glTranslatef(0F, 0F, -0.3F);
			      }else{
			    	  GL11.glTranslatef(0F, 0.75F, 0F);
				//GL11.glRotated(20F, 1.0F, 0.0F, 0.0F);
				GL11.glTranslatef(0F, -0.75F, 0F);
			      }
				armor.tankk.renderPart("mat31");
				GL11.glPopMatrix();
	        }
	        {
	        	GL11.glPushMatrix();
				GL11.glTranslatef(0F, 0.75F, 0F);
				//GL11.glRotated(-18F, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(0F, -0.75F, 0F);
				if(p_78087_7_.isSneaking())
			      {
					GL11.glTranslatef(0F, 0.1F, 0F);
					GL11.glTranslatef(0F, 0.75F, 0F);
					//GL11.glRotated(60F, 1.0F, 0.0F, 0.0F);
					GL11.glTranslatef(0F, -0.75F, 0F);
					GL11.glTranslatef(0F, 0F, -0.3F);
			      }else{
			    	  GL11.glTranslatef(0F, 0.75F, 0F);
				//GL11.glRotated(20F, 1.0F, 0.0F, 0.0F);
				GL11.glTranslatef(0F, -0.75F, 0F);
			      }
				armor.tankk.renderPart("mat32");
				GL11.glPopMatrix();
	        }
			GL11.glPopAttrib();
			GL11.glPopMatrix();
		}
		
		}
      GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	  
	  
  }

}