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
 
public class SUItemArmorR extends SUItemArmor {
 
	public SUItemArmorR(ArmorMaterial armorMaterial, int type) {
		super(armorMaterial, type);
		//tankk = AdvancedModelLoader.loadModel(new ResourceLocation("strikerunit:textures/model/" + this.armor_obj));
	}
 
	@Override
	public String getArmorTexture(ItemStack itemStack, Entity entity, int slot, String type) {
		if (this.armorType == 2) {
			return "strikerunit:textures/model/" + armor_layer2;
		}
		return "strikerunit:textures/model/" + armor_layer1;
	}
 
	
	@SideOnly(Side.CLIENT)
	  public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
	  {
	    return ClientProxySU.ArmorR;
	  }
	
}
