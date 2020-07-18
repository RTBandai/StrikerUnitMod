package strikerunit;

import java.io.File;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.world.World;


public class CommonSideProxySU {
 
	public void registerClientInfo(){}
	
	public void IGuiHandler(){}
	
    public void reisterRenderers(){}
	
	public World getCilentWorld(){
		return null;}

	public void InitRendering() {
		
	}

	public File FileC(){
		return new File(".");
	}
	
	public boolean jumped(){
		return false;
	}
	
	public boolean leftclick(){
		return false;
	}
	
	public boolean rightclick(){
		return false;
	}
	
	public void registerTileEntity() {		
		//GameRegistry.registerTileEntity(GVCTileEntityItemG36.class, "GVCTileEntitysample");
	}
	
	
 
}