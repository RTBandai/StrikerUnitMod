package strikerunit;
 
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import edfmod.render.ClientProxyEDF;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
 
public class SUItemArmor extends ItemArmor {
 
	//public static final String armor_layer1 = "strikerunit:textures/model/sp.png";
    //public static final String armor_layer2 = "strikerunit:textures/model/sp.png";
    //public static final String armor_layer1 = "strikerunit:textures/model/su01.png";
    //public static final String armor_layer2 = "strikerunit:textures/model/su01.png";
	public String armor_layer1 = "su01.png";
    public String armor_layer2 = "su01.png";
    
    public String armor_obj = "su01.obj";
    
    public double speed = 0.4D;
    public double maxspeed = 1.0D;
    
    public float praspeed = 20F;
    public float prarx = -0.15F;
    public float prary = 0.3F;
    public float prarz = 0.0F;
    public float pralx = 0.15F;
    public float praly = 0.3F;
    public float pralz = 0.0F;

    public IModelCustom tankk;
	
	public SUItemArmor(ArmorMaterial armorMaterial, int type) {
		super(armorMaterial, 0, type);
		//tankk = AdvancedModelLoader.loadModel(new ResourceLocation("strikerunit:textures/model/" + this.armor_obj));
	}
 
	@Override
	public String getArmorTexture(ItemStack itemStack, Entity entity, int slot, String type) {
		if (this.armorType == 2) {
			return "strikerunit:textures/model/" + armor_layer2;
		}
		return "strikerunit:textures/model/" + armor_layer1;
	}
}
