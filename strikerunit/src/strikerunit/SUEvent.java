package strikerunit;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class SUEvent {

	private float iii;
	private boolean hurt = false;
	private int hurttime = 0;
	
	@SideOnly(Side.CLIENT)
    @SubscribeEvent
	  public void rendertest(EntityViewRenderEvent.RenderFogEvent event)
	  {
		EntityLivingBase entityLiving = event.entity;
		Minecraft minecraft = FMLClientHandler.instance().getClient();
		if (entityLiving instanceof EntityPlayer && entityLiving != null) {
			EntityPlayer entityplayer = (EntityPlayer) entityLiving;
			if ((entityplayer.getEquipmentInSlot(1) != null)
					&& (entityplayer.getEquipmentInSlot(1).getItem() instanceof SUItemArmor)) {
				int ii = entityplayer.getEquipmentInSlot(1).getMaxDamage() - entityplayer.getEquipmentInSlot(1).getItemDamage();
				int ii2 = entityplayer.getEquipmentInSlot(1).getMaxDamage()/2;
				
			if(minecraft.gameSettings.thirdPersonView == 1){
				float ix2 = 0;
				float iz2 = 0;
				float f12 = entityplayer.rotationYawHead * (2 * (float) Math.PI / 360);
				ix2 += (float) (MathHelper.sin(f12+1.3F) * 0.3);
				iz2 -= (float) (MathHelper.cos(f12+1.3F) * 0.3);
				if (mod_StrikerUnit.cfg_left) {
				GL11.glTranslatef(ix2, 0, iz2);
				}
			}else if(minecraft.gameSettings.thirdPersonView == 0 && (ii > ii2 && hurt)){
				GL11.glPushMatrix();
				GL11.glScalef(2, 2, 2);
				float ix2 = 0;
				float iz2 = 0;
				float f12 = entityplayer.rotationYawHead * (2 * (float) Math.PI / 360);
				ix2 -= (float) (MathHelper.sin(f12) * 1.5);
				iz2 += (float) (MathHelper.cos(f12) * 1.5);
				GL11.glTranslatef(ix2,-1,iz2);
				GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
				GL11.glEnable(GL12.GL_RESCALE_NORMAL);
				GL11.glRotatef(180.0F - entityplayer.rotationYawHead, 0.0F, 1.0F, 0.0F);
				if(iii < 360F){
					GL11.glTranslatef(0F, 1F, 0F);
			    	  GL11.glRotatef(iii, 0.0F, 0.0F, 1.0F);
			    	GL11.glTranslatef(0F, -1F, 0F);
			 			iii = iii + 0.1F;
			 		}else{
			 			iii = 0F;
			 		}
				Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("strikerunit:textures/model/tate.png"));
				AdvancedModelLoader
				.loadModel(new ResourceLocation("strikerunit:textures/model/tate.obj")).renderPart("mat1");
				GL11.glDisable(GL12.GL_RESCALE_NORMAL);
				GL11.glPopMatrix();
				
				/*GL11.glPushMatrix();
				GL11.glEnable(GL12.GL_RESCALE_NORMAL);
				ModelBiped modelBipedMain = new ModelBiped(0.5F);
				ResourceLocation resourcelocation = ((AbstractClientPlayer)entityplayer).getLocationSkin();
		        if (resourcelocation == null)
		        {
		            resourcelocation = AbstractClientPlayer.getLocationSkin("default");
		        }
		        Minecraft.getMinecraft().renderEngine.bindTexture(resourcelocation);
				GL11.glRotatef(180F, 1.0F, 0.0F, 0.0F);
				GL11.glTranslatef(0F, -0.5F, 0F);
				float ix22 = 0;
				float iz22 = 0;
				ix22 += (float) (MathHelper.sin(f12) * 1.0);
				iz22 -= (float) (MathHelper.cos(f12) * 1.0);
				GL11.glTranslatef(ix22,0,iz22);
				GL11.glRotatef(180.0F - entityplayer.rotationYawHead, 0.0F, 1.0F, 0.0F);
		        modelBipedMain.bipedLeftArm.render(0.0625f);
		        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
				GL11.glPopMatrix();*/
				//hurt = false;
			}
		}
			/*else if(minecraft.gameSettings.thirdPersonView == 1){
				float ix2 = 0;
				float iz2 = 0;
				float f12 = entityplayer.rotationYawHead * (2 * (float) Math.PI / 360);
				ix2 += (float) (MathHelper.sin(f12+1.3F) * 0.3);
				iz2 -= (float) (MathHelper.cos(f12+1.3F) * 0.3);
				GL11.glTranslatef(ix2, 0, iz2);
			}*/
		}
	  }
	
	@SubscribeEvent
	public void onHurtEvent(LivingHurtEvent event) {
		EntityLivingBase target = event.entityLiving;
		DamageSource source = event.source;
		float damage = event.ammount;
		ItemStack hold = null;
		if (target instanceof EntityPlayer && target != null) {
			EntityPlayer entityplayer = (EntityPlayer) target;
			if ((entityplayer.getEquipmentInSlot(1) != null)
					&& (entityplayer.getEquipmentInSlot(1).getItem() instanceof SUItemArmor)) {
				//if(mod_StrikerUnit.proxy.leftclick())
				int ii = entityplayer.getEquipmentInSlot(1).getMaxDamage() - entityplayer.getEquipmentInSlot(1).getItemDamage();
				int ii2 = entityplayer.getEquipmentInSlot(1).getMaxDamage()/2;
				if(ii > ii2)
				{
					if (target != null && source.getEntity() instanceof EntityLivingBase) {
						EntityLivingBase attacker = (EntityLivingBase) source.getEntity();
						if (attacker != null) {
							event.ammount = 0;
							entityplayer.playSound("mob.blaze.hit", 1.0F, 2.0F);
							float ix2 = 0;
							float iz2 = 0;
							float f12 = entityplayer.rotationYawHead * (2 * (float) Math.PI / 360);
							ix2 -= (float) (MathHelper.sin(f12) * 1.5);
							iz2 += (float) (MathHelper.cos(f12) * 1.5);
							entityplayer.worldObj.spawnParticle("cloud", 
									entityplayer.posX +ix2, entityplayer.posY +0, entityplayer.posZ+iz2, 0.0D, 0.0D, 0.0D);
							SUItemArmor armor = (SUItemArmor) entityplayer.getEquipmentInSlot(1).getItem();
							if (!entityplayer.worldObj.isRemote) 
							{
								entityplayer.getEquipmentInSlot(1).damageItem(50, entityplayer);
							}
							hurt = true;
						}
					}
					if (target != null && source.isExplosion()) {
						event.ammount = 0;
						entityplayer.playSound("mob.blaze.hit", 1.0F, 2.0F);
						SUItemArmor armor = (SUItemArmor) entityplayer.getEquipmentInSlot(1).getItem();
						if (!entityplayer.worldObj.isRemote) 
						{
							entityplayer.getEquipmentInSlot(1).damageItem(50, entityplayer);
						}
						hurt = true;
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void ArmorLiving(LivingFallEvent event) {
		// if(eevent.getSide().isClient())
		{
			EntityLivingBase entityLiving = event.entityLiving;
			if ((entityLiving.getEquipmentInSlot(1) != null)
					&& (entityLiving.getEquipmentInSlot(1).getItem() instanceof SUItemArmor)) {
				event.distance = 0.0F;
				// return;
			}
		}
	}

	@SubscribeEvent
	public void PlayerLiving(LivingUpdateEvent event) {
			EntityLivingBase entityLiving = event.entityLiving;

			if (entityLiving instanceof EntityPlayer && entityLiving != null) {
				EntityPlayer entityplayer = (EntityPlayer) entityLiving;
				PlayerMoving(entityplayer);
				PlayerUp(entityplayer);
			}
	}
	
	private void PlayerMoving(EntityPlayer player) {
		float f1 = player.rotationYawHead * (2 * (float) Math.PI / 360);

		if ((player.getEquipmentInSlot(1) != null)
				&& (player.getEquipmentInSlot(1).getItem() instanceof SUItemArmorS)) {

			if (!player.worldObj.isRemote && mod_StrikerUnit.cfg_sound) {
				 player.worldObj.playSoundAtEntity(player,"strikerunit:strikerunit.pera", 1.0F, 1.0F);
			}
			player.worldObj.spawnParticle("cloud", player.posX, player.posY - 2, player.posZ, 0.0D, 0.0D, 0.0D);
			// player.motionY = 0.0005D;
			player.motionY = 0.00005D;

			Vec3 look = player.getLookVec();
			SUItemArmorS armor = (SUItemArmorS) player.getEquipmentInSlot(1).getItem();
			if (player.moveForward > 0F)
			{
				player.motionX = look.xCoord * armor.speed;
				player.motionZ = look.zCoord * armor.speed;
				player.motionY = look.yCoord * armor.speed;

			}
			if (player.moveForward < 0F)
			{
				player.motionX = - look.xCoord * armor.speed;
				player.motionZ = - look.zCoord * armor.speed;
				player.motionY = - look.yCoord * armor.speed;

			}
			if(player.moveStrafing < 0.0F){
				player.motionX += MathHelper.sin(player.rotationYawHead * 0.01745329252F-1.8F) * armor.speed/10;
				player.motionZ -= MathHelper.cos(player.rotationYawHead * 0.01745329252F-1.8F) * armor.speed/10;
				player.motionY = look.yCoord * armor.speed;
            }
            if(player.moveStrafing > 0.0F){
            	player.motionX += MathHelper.sin(player.rotationYawHead * 0.01745329252F+1.8F) * armor.speed/10;
            	player.motionZ -= MathHelper.cos(player.rotationYawHead * 0.01745329252F+1.8F) * armor.speed/10;
            	player.motionY = look.yCoord * armor.speed;
            }
			
			
			if (player.isSneaking() == true)
			// if (player.moveForward > 0F)
			{
				player.motionX = look.xCoord * armor.maxspeed;
				player.motionZ = look.zCoord * armor.maxspeed;
				player.motionY = look.yCoord * armor.maxspeed;

			}
			
		}else if ((player.getEquipmentInSlot(1) != null)
				&& (player.getEquipmentInSlot(1).getItem() instanceof SUItemArmorR)) {
			
			player.worldObj.spawnParticle("cloud", player.posX, player.posY - 1, player.posZ, 0.0D, 0.0D, 0.0D);
			Vec3 look = player.getLookVec();
			SUItemArmorR armor = (SUItemArmorR) player.getEquipmentInSlot(1).getItem();
			if (player.moveForward > 0F)
			{
				player.motionX = look.xCoord * armor.speed;
				player.motionZ = look.zCoord * armor.speed;
			}
			if (player.moveForward < 0F)
			{
				player.motionX = - look.xCoord * armor.speed;
				player.motionZ = - look.zCoord * armor.speed;
			}
			if(player.moveStrafing < 0.0F){
				player.motionX += MathHelper.sin(player.rotationYawHead * 0.01745329252F-1.8F) * armor.speed/10;
				player.motionZ -= MathHelper.cos(player.rotationYawHead * 0.01745329252F-1.8F) * armor.speed/10;
            }
            if(player.moveStrafing > 0.0F){
            	player.motionX += MathHelper.sin(player.rotationYawHead * 0.01745329252F+1.8F) * armor.speed/10;
            	player.motionZ -= MathHelper.cos(player.rotationYawHead * 0.01745329252F+1.8F) * armor.speed/10;
            }
			
			if (player.isSneaking() == true)
			{
				player.motionX = look.xCoord * armor.maxspeed;
				player.motionZ = look.zCoord * armor.maxspeed;
				if (!player.worldObj.isRemote  && mod_StrikerUnit.cfg_sound) {
					 player.worldObj.playSoundAtEntity(player,"strikerunit:strikerunit.tank", 1.0F, 1.0F);
				}
			}else{
				if (!player.worldObj.isRemote  && mod_StrikerUnit.cfg_sound) {
					 player.worldObj.playSoundAtEntity(player,"strikerunit:strikerunit.tank", 0.2F, 1.0F);
				}
			}
		}else if ((player.getEquipmentInSlot(1) != null)
				&& (player.getEquipmentInSlot(1).getItem() instanceof SUItemArmorH)) {
			if (!player.worldObj.isRemote) {
		//		 player.worldObj.playSoundAtEntity(player,"strikerunit:strikerunit.pera", 1.0F, 1.0F);
			}
		//	player.worldObj.spawnParticle("cloud", player.posX, player.posY - 1, player.posZ, 0.0D, 0.0D, 0.0D);
			Vec3 look = player.getLookVec();
			SUItemArmorH armor = (SUItemArmorH) player.getEquipmentInSlot(1).getItem();
			if (player.moveForward > 0F)
			{
				player.motionX = look.xCoord * armor.speed;
				player.motionZ = look.zCoord * armor.speed;
			}
			if (player.moveForward < 0F)
			{
				player.motionX = - look.xCoord * armor.speed;
				player.motionZ = - look.zCoord * armor.speed;
			}
			if(player.moveStrafing < 0.0F){
				player.motionX += MathHelper.sin(player.rotationYawHead * 0.01745329252F-1.8F) * armor.speed/10;
				player.motionZ -= MathHelper.cos(player.rotationYawHead * 0.01745329252F-1.8F) * armor.speed/10;
            }
            if(player.moveStrafing > 0.0F){
            	player.motionX += MathHelper.sin(player.rotationYawHead * 0.01745329252F+1.8F) * armor.speed/10;
            	player.motionZ -= MathHelper.cos(player.rotationYawHead * 0.01745329252F+1.8F) * armor.speed/10;
            }
			
			if (player.isSneaking() == true)
			{
				player.motionX = look.xCoord * armor.maxspeed;
				player.motionZ = look.zCoord * armor.maxspeed;
			}
		}  
	}
	
	private void PlayerUp(EntityPlayer player) {
		if ((player.getEquipmentInSlot(1) != null)
				&& (player.getEquipmentInSlot(1).getItem() instanceof SUItemArmor)) {
			if ((player.getEquipmentInSlot(1) != null)
					&& (player.getEquipmentInSlot(1).getItem() instanceof SUItemArmor)) {
				SUItemArmor armor = (SUItemArmor) player.getEquipmentInSlot(1).getItem();
				if (!player.worldObj.isRemote) {
					player.getEquipmentInSlot(1).damageItem(-1, player);
				}
				if(hurt){
					++hurttime;
					if(hurttime > 10){
						hurt = false;
						hurttime = 0;
					}
				}
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void renderLivingE(RenderLivingEvent.Pre event) {
		EntityLivingBase entityLiving = event.entity;
		Minecraft minecraft = FMLClientHandler.instance().getClient();
		if (entityLiving instanceof EntityPlayer && entityLiving != null) {
			EntityPlayer entityplayer = (EntityPlayer) entityLiving;
			if ((entityplayer.getEquipmentInSlot(1) != null)
					&& (entityplayer.getEquipmentInSlot(1).getItem() instanceof SUItemArmor)) {

			}
		}
	}
	
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void renderover(RenderGameOverlayEvent.Text event) {
		Minecraft minecraft = FMLClientHandler.instance().getClient();
			EntityPlayer entityplayer = minecraft.thePlayer;
			ScaledResolution scaledresolution = new ScaledResolution(minecraft, minecraft.displayWidth,
					minecraft.displayHeight);
			int i = scaledresolution.getScaledWidth();
			int j = scaledresolution.getScaledHeight();
			if ((entityplayer.getEquipmentInSlot(1) != null)
					&& (entityplayer.getEquipmentInSlot(1).getItem() instanceof SUItemArmor)) {
				SUItemArmor armor = (SUItemArmor) entityplayer.getEquipmentInSlot(1).getItem();
				FontRenderer fontrenderer = minecraft.fontRenderer;
				GL11.glPushMatrix();
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				String d1 = String.format("%1$3d", entityplayer.getEquipmentInSlot(1).getMaxDamage() 
						- entityplayer.getEquipmentInSlot(1).getItemDamage());
				String d2 = String.format("%1$3d", entityplayer.getEquipmentInSlot(1).getMaxDamage());
				int ii = entityplayer.getEquipmentInSlot(1).getMaxDamage() - entityplayer.getEquipmentInSlot(1).getItemDamage();
				int ii2 = entityplayer.getEquipmentInSlot(1).getMaxDamage()/2;
				if(ii > ii2){
				fontrenderer.drawStringWithShadow(d1+"/"+d2, i - 50, j - 60 + 0, 0xFFFFFF);
				}else{
					fontrenderer.drawStringWithShadow(d1+"/"+d2, i - 50, j - 60 + 0, 0xFFD700);
				}
				GuiIngame g  = minecraft.ingameGUI;
				minecraft.getTextureManager().bindTexture(TextureMap.locationItemsTexture);
				g.drawTexturedModelRectFromIcon(i-70, j-63, armor.getIconFromDamage(0), 16, 16);
				GL11.glPopMatrix();
		}
	}
	
	
	public boolean ren;
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void renderLiving(RenderPlayerEvent.Pre event) {
		RenderPlayer renderplayer = event.renderer;
		//RendererLivingEntity render = event.renderer;
		Minecraft minecraft = FMLClientHandler.instance().getClient();

		EntityPlayer entityplayer = event.entityPlayer;
		/*{
			GL11.glPushMatrix();
			GL11.glScalef(1, 1, 1);
			float ix2 = 0;
			float iz2 = 0;
			float f12 = entityplayer.rotationYawHead * (2 * (float) Math.PI / 360);
			ix2 -= (float) (MathHelper.sin(f12) * 1);
			iz2 += (float) (MathHelper.cos(f12) * 1);
			GL11.glTranslatef(ix2,0,iz2);
			GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glRotatef(180.0F - entityplayer.rotationYawHead, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(entityplayer.rotationPitch, 1.0F, 0.0F, 0.0F);
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("strikerunit:textures/model/hou3.png"));
			AdvancedModelLoader
			.loadModel(new ResourceLocation("strikerunit:textures/model/hou3.obj")).renderPart("mat1");
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
		}*/
		if ((entityplayer.getEquipmentInSlot(1) != null)
				&& (entityplayer.getEquipmentInSlot(1).getItem() instanceof SUItemArmor)) {
			if(entityplayer.worldObj.isRemote){
			renderplayer.modelBipedMain.bipedRightLeg.showModel = false;
			renderplayer.modelBipedMain.bipedLeftLeg.showModel = false;

			renderplayer.modelArmorChestplate.bipedRightLeg.showModel = false;
			renderplayer.modelArmorChestplate.bipedLeftLeg.showModel = false;
			renderplayer.modelArmor.bipedRightLeg.showModel = false;
			renderplayer.modelArmor.bipedLeftLeg.showModel = false;
			
			renderplayer.modelArmorChestplate.bipedBody.showModel = false;
			renderplayer.modelArmorChestplate.bipedHead.showModel = false;
			}
			ren = false;
			
			
			int ii = entityplayer.getEquipmentInSlot(1).getMaxDamage() - entityplayer.getEquipmentInSlot(1).getItemDamage();
			int ii2 = entityplayer.getEquipmentInSlot(1).getMaxDamage()/2;
			if(ii > ii2 && hurt){
			GL11.glPushMatrix();
			GL11.glScalef(2, 2, 2);
			//GL11.glTranslatef(0,0,0);
			float ix2 = 0;
			float iz2 = 0;
			float f12 = entityplayer.rotationYawHead * (2 * (float) Math.PI / 360);
			ix2 -= (float) (MathHelper.sin(f12) * 1.5);
			iz2 += (float) (MathHelper.cos(f12) * 1.5);
			GL11.glTranslatef(ix2,-1,iz2);
			//GL11.glTranslated(entityLiving.posX,entityLiving.posY,entityLiving.posZ);
			GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glRotatef(180.0F - entityplayer.rotationYawHead, 0.0F, 1.0F, 0.0F);
			if(iii < 360F){
				GL11.glTranslatef(0F, 1F, 0F);
		    	  GL11.glRotatef(iii, 0.0F, 0.0F, 1.0F);
		    	GL11.glTranslatef(0F, -1F, 0F);
		 			iii = iii + 0.1F;
		 		}else{
		 			iii = 0F;
		 		}
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("strikerunit:textures/model/tate.png"));
			AdvancedModelLoader
			.loadModel(new ResourceLocation("strikerunit:textures/model/tate.obj")).renderPart("mat1");
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
			//hurt = false;
			}
			
			
		} else {
			if (!ren) {
				ren = true;
				
				if(entityplayer.worldObj.isRemote){
					renderplayer.modelBipedMain.bipedRightLeg.showModel = true;
					renderplayer.modelBipedMain.bipedLeftLeg.showModel = true;

					renderplayer.modelArmorChestplate.bipedRightLeg.showModel = true;
					renderplayer.modelArmorChestplate.bipedLeftLeg.showModel = true;
					renderplayer.modelArmor.bipedRightLeg.showModel = true;
					renderplayer.modelArmor.bipedLeftLeg.showModel = true;
				}
				
			}
		}

	}
	
}
