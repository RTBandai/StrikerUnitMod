package strikerunit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
//import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.model.AdvancedModelLoader;
//import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Type;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.server.FMLServerHandler;

@Mod(modid = "StrikerUnit", name = "StrikerUnit", version = "1.7.x-srg-1")
public class mod_StrikerUnit {
	@SidedProxy(clientSide = "strikerunit.ClientProxySU", serverSide = "strikerunit.CommonSideProxySU")
	public static CommonSideProxySU proxy;
	public static final String MOD_ID = "StrikerUnit";
	@Mod.Instance("StrikerUnit")

	public static mod_StrikerUnit INSTANCE;
	// public static final KeyBinding Speedreload = new KeyBinding("Key.reload",
	// Keyboard.KEY_R, "GVCGunsPlus");

	public static boolean isDebugMessage = true;

	public static Item hmg_bullet;

	public static ArmorMaterial fuarmor;
	public static Item edf_fuarmor;
	
	public static Item ne;

	protected static File configFile;
	
	public static boolean cfg_sound;
	public static boolean cfg_left;

	// public static final CreativeTabs tabhmg = new MCWCreativeTab("MCWTab");

	public static void Debug(String pText, Object... pData) {
		if (isDebugMessage) {
			System.out.println(String.format("HandmadeGuns-" + pText, pData));
		}
	}

	// @net.minecraftforge.fml.common.Mod.EventHandler
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent pEvent) {

		configFile = pEvent.getSuggestedConfigurationFile();
		Configuration lconf = new Configuration(configFile);
		lconf.load();
		cfg_sound	= lconf.get("Sound", "cfg_sound", true).getBoolean(true);
		cfg_left	= lconf.get("Left", "cfg_left", true).getBoolean(true);
		lconf.save();
		
		/*fuarmor = EnumHelper.addArmorMaterial("PraArmor", 430, new int[] { 4, 10, 6, 2 }, 10);
		edf_fuarmor = new SUItemArmorS(fuarmor, 3).setUnlocalizedName("SArmor")
				.setTextureName("strikerunit:strikerunit").setCreativeTab(CreativeTabs.tabCombat);
		GameRegistry.registerItem(edf_fuarmor, "SArmor");*/

		ne = new SUItemSpwan(1).setUnlocalizedName("ne").setTextureName("gvcmob:ne")
				//.setCreativeTab(CreativeTabs.tabMisc)
				;
		GameRegistry.registerItem(ne, "ne");
		
		
		{
		    File directory1 = new File(proxy.FileC(),"mods" + File.separatorChar + "strikerunit" + File.separatorChar + "add");
		    {
			    File dire22 = new File(directory1, "addmodel");
		    	  File[] filelist22 = dire22.listFiles();
		    	  for (int ii = 0 ; ii < filelist22.length ; ii++){
		    	      if (filelist22[ii].isFile()){
		    	    	  File directory111 = new File(proxy.FileC(),"mods" + File.separatorChar + "strikerunit" 
		    	                  + File.separatorChar +  "assets" + File.separatorChar + "strikerunit" + File.separatorChar +
		    	    			  "textures"+ File.separatorChar + "model"+ File.separatorChar + filelist22[ii].getName());
		    	          try {
		    	              FileUtils.copyFile(filelist22[ii], directory111);
		    	          } catch (IOException e) {
		    	              e.printStackTrace();
		    	          }
		    	      }
		    	  }
			    }
			{
			    	File dire223 = new File(directory1, "addtexture");
			    	  File[] filelist223 = dire223.listFiles();
			    	  for (int ii = 0 ; ii < filelist223.length ; ii++){
			    	      if (filelist223[ii].isFile()){
			    	    	  File directory111 = new File(proxy.FileC(),"mods" + File.separatorChar + "strikerunit" 
			    	                  + File.separatorChar +  "assets" + File.separatorChar + "strikerunit" + File.separatorChar +
			    	    			  "textures"+ File.separatorChar + "items"+ File.separatorChar + filelist223[ii].getName());
			    	          try {
			    	              FileUtils.copyFile(filelist223[ii], directory111);
			    	          } catch (IOException e) {
			    	              e.printStackTrace();
			    	          }
			    	      }
			    	  }
			    }
		    {
		    	File dire21 = new File(directory1, "striker");
		    	  File[] filelist21 = dire21.listFiles();
		    	  for (int ii = 0 ; ii < filelist21.length ; ii++){
		    	      if (filelist21[ii].isFile()){
		    	    	  Event_ItemLoad.load(pEvent.getModConfigurationDirectory(), pEvent.getSide().isClient(), filelist21[ii]);
		    	      }
		    	  }
		    }
		    
		}
	}

	@EventHandler
	public void init(FMLInitializationEvent pEvent) {
		int D = Short.MAX_VALUE;

		/*GameRegistry.addRecipe(new ItemStack(edf_fuarmor, 1),
				"gig",
				"g g", 
				"b b", 
				'i', Items.iron_leggings,
				'g', Blocks.iron_block,
				'b', Items.blaze_rod
			);*/
		
		EntityRegistry.registerModEntity(EntityNE.class, "EntityNE", 0, this, 250, 1, false);
		EntityRegistry.registerModEntity(EntityNELaser.class, "EntityNELaser", 1, this, 250, 1, false);
		FMLCommonHandler.instance().bus().register(this);
		MinecraftForge.EVENT_BUS.register(new SUEvent());
		proxy.reisterRenderers();
		proxy.registerTileEntity();
		proxy.InitRendering();
		proxy.jumped();
		proxy.leftclick();
		proxy.rightclick();

	//	MinecraftForge.EVENT_BUS.register(new LivingEventHooksMP(pEvent));

	}

}
