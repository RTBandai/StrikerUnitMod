package strikerunit;


import java.io.File;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;


public class ClientProxySU extends CommonSideProxySU {
	
	///public static ModelBiped ArmorS = new SUModelArmorS();
	public static ModelBiped ArmorS = new SUModelArmorS2();
	public static ModelBiped ArmorR = new SUModelArmorR();
	public static ModelBiped ArmorH = new SUModelArmorH();
	
    @Override
	public World getCilentWorld(){
		return FMLClientHandler.instance().getClient().theWorld;
		}
    
    @Override
    public void registerClientInfo() {
        //ClientRegistry.registerKeyBinding(Speedreload);
    }
    
    @Override
	public void reisterRenderers(){
    	Minecraft mc = FMLClientHandler.instance().getClient();
    	RenderingRegistry.registerEntityRenderingHandler(EntityNE.class, new RenderNE());
    	RenderingRegistry.registerEntityRenderingHandler(EntityNELaser.class, new RenderNELaser());
    }
    
    @Override
    public File FileC(){
    	return Minecraft.getMinecraft().mcDataDir;
    }
    
    @Override
    public boolean jumped(){
		return Minecraft.getMinecraft().gameSettings.keyBindJump.getIsKeyPressed();
		//return false;
	}
    
    @Override
    public boolean leftclick(){
		return Minecraft.getMinecraft().gameSettings.keyBindAttack.getIsKeyPressed();
		//return false;
	}
    
    @Override
    public boolean rightclick(){
		return Minecraft.getMinecraft().gameSettings.keyBindUseItem.getIsKeyPressed();
		//return false;
	}
    
    @Override
    public void registerTileEntity() {
    	//GameRegistry.registerTileEntity(GVCTileEntityItemG36.class, "GVCTileEntitysample");
    }
    
    @Override
    public void InitRendering()
    {
    	//ClientRegistry.bindTileEntitySpecialRenderer(GVCTileEntityItemG36.class, new GVCRenderItemG36());
    }

    
    
 
}