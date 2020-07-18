package strikerunit;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.basic.BasicComboBoxUI.ItemHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemLilyPad;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.common.util.EnumHelper;

public class Event_ItemLoad
{
  static List<ArrayList<String>> mainList = new ArrayList();
  public static List Guns = new ArrayList();
  
  static String GunName = null;
  static String Namegun = null;
  static String FileName = null;
  static int kazu;
  static double health;
  static boolean canex;
  static String texture;
  static Item[] addi;
  public static List addit = new ArrayList();
  
  static Item magazine;;
  
  static Item itema;
  static Item itemb;
  static Item itemc;
  static Item itemd;
  static Item iteme;
  static Item itemf;
  static Item itemg;
  static Item itemh;
  static Item itemi;
  static String re1 = "abc";
  static String re2 = "def";
  static String re3 = "ghi";
  
  static String armortex;
  static String armorobj;
  static double speed;
  static double mspeed;
  static float pspeed;
  static float plx;
  static float ply;
  static float plz;
  static float prx;
  static float pry;
  static float prz;
  
  static int damage;
  
  public static void load(File configfile, boolean isClient, File file1)
  {
	  {
		  health = 100D;
		  Namegun = null;
		   itema = Item.getItemById(0);
		   itemb = Item.getItemById(0);
		   itemc = Item.getItemById(0);
		   itemd = Item.getItemById(0);
		   iteme = Item.getItemById(0);
		   itemf = Item.getItemById(0);
		   itemg = Item.getItemById(0);
		   itemh = Item.getItemById(0);
		   itemi = Item.getItemById(0);
		   re1 = "abc";
		   re2 = "def";
		   re3 = "ghi";
		   damage = 430;
	  }
	  
	  
	  try {
		  File file = file1;
          if (checkBeforeReadfile(file))
          {
        	  BufferedReader br = new BufferedReader(new FileReader(file));  // ファイルを開く
          
          String str;
          while((str = br.readLine()) != null){  // 1行ずつ読み込む
        	  String[] type = str.split(",");
        	  int guntype = 0;
        	  if (type.length != 0)
              {//1
        			  if(type[0].equals("Texture")){
        				  texture = type[1];
            		  }
        			  if(type[0].equals("ArmorTexture")){
        				  armortex = type[1];
            		  }
        			  if(type[0].equals("ArmorObj")){
        				  armorobj = type[1];
            		  }
        			  if (type[0].equals("Speed")) {
        				  speed = Double.parseDouble(type[1]);
						}
        			  if (type[0].equals("MaxSpeed")) {
        				  mspeed = Double.parseDouble(type[1]);
						}
        			  if (type[0].equals("PraSpeed")) {
        				  pspeed = Float.parseFloat(type[1]);
						}
        			  if (type[0].equals("PraLeftposX")) {
        				  plx = Float.parseFloat(type[1]);
						}
        			  if (type[0].equals("PraLeftposY")) {
        				  ply = Float.parseFloat(type[1]);
						}
        			  if (type[0].equals("PraLeftposZ")) {
        				  plz = Float.parseFloat(type[1]);
						}
        			  if (type[0].equals("PraRightposX")) {
        				  prx = Float.parseFloat(type[1]);
						}
        			  if (type[0].equals("PraRightposY")) {
        				  pry = Float.parseFloat(type[1]);
						}
        			  if (type[0].equals("PraRightposZ")) {
        				  prz = Float.parseFloat(type[1]);
						}
        			  if (type[0].equals("ArmorDamage")) {
        				  damage = Integer.parseInt(type[1]);
						}
        			  
        			  if(type[0].equals("Name")){
        				  Namegun = type[1];
            		  }
                		if(type[0].equals("StrikerUnit")){
                			ArmorMaterial fuarmor = EnumHelper.addArmorMaterial("StrikerArmor", damage, new int[] { 4, 10, 6, 2 }, 10);
                			GunName = type[1];
                  			Item newgun	= new SUItemArmorS(fuarmor, 3).setUnlocalizedName(GunName)
                  				  .setTextureName("strikerunit:"+texture).setCreativeTab(CreativeTabs.tabCombat);
                    		GameRegistry.registerItem(newgun, GunName);
                    		if(Namegun != null){
                  		      LanguageRegistry.instance().addNameForObject(newgun, "ja_JP", Namegun);
                            }else{
                          	  LanguageRegistry.instance().addNameForObject(newgun, "ja_JP", GunName);
                            }
                    		SUItemArmorS iiitem = (SUItemArmorS)newgun;
                    		iiitem.armor_layer1 = armortex;
                    		iiitem.armor_layer2 = armortex;
                    		iiitem.armor_obj = armorobj;
                    		iiitem.speed = speed;
                    		iiitem.maxspeed = mspeed;
                    		iiitem.praspeed = pspeed;
                    		iiitem.pralx = plx;
                    		iiitem.praly = ply;
                    		iiitem.pralz = plz;
                    		iiitem.prarx = prx;
                    		iiitem.prary = pry;
                    		iiitem.prarz = prz;
                    		iiitem.tankk = AdvancedModelLoader.loadModel(new ResourceLocation("strikerunit:textures/model/" + armorobj));
                    		Guns.add(newgun);
                		}else if(type[0].equals("StrikerUnitR")){
                			ArmorMaterial fuarmor = EnumHelper.addArmorMaterial("StrikerArmorR", damage, new int[] { 4, 10, 6, 2 }, 10);
                			GunName = type[1];
                  			Item newgun	= new SUItemArmorR(fuarmor, 3).setUnlocalizedName(GunName)
                  				  .setTextureName("strikerunit:"+texture).setCreativeTab(CreativeTabs.tabCombat);
                    		GameRegistry.registerItem(newgun, GunName);
                    		if(Namegun != null){
                  		      LanguageRegistry.instance().addNameForObject(newgun, "ja_JP", Namegun);
                            }else{
                          	  LanguageRegistry.instance().addNameForObject(newgun, "ja_JP", GunName);
                            }
                    		SUItemArmorR iiitem = (SUItemArmorR)newgun;
                    		iiitem.armor_layer1 = armortex;
                    		iiitem.armor_layer2 = armortex;
                    		iiitem.armor_obj = armorobj;
                    		iiitem.speed = speed;
                    		iiitem.maxspeed = mspeed;
                    		iiitem.praspeed = pspeed;
                    		iiitem.pralx = plx;
                    		iiitem.praly = ply;
                    		iiitem.pralz = plz;
                    		iiitem.prarx = prx;
                    		iiitem.prary = pry;
                    		iiitem.prarz = prz;
                    		iiitem.tankk = AdvancedModelLoader.loadModel(new ResourceLocation("strikerunit:textures/model/" + armorobj));
                    		Guns.add(newgun);
                		}else{ /*if(type[0].equals("StrikerUnitH")){
                			ArmorMaterial fuarmor = EnumHelper.addArmorMaterial("StrikerArmorH", damage, new int[] { 4, 10, 6, 2 }, 10);
                			GunName = type[1];
                  			Item newgun	= new SUItemArmorH(fuarmor, 3).setUnlocalizedName(GunName)
                  				  .setTextureName("strikerunit:"+texture).setCreativeTab(CreativeTabs.tabCombat);
                    		GameRegistry.registerItem(newgun, GunName);
                    		if(Namegun != null){
                  		      LanguageRegistry.instance().addNameForObject(newgun, "ja_JP", Namegun);
                            }else{
                          	  LanguageRegistry.instance().addNameForObject(newgun, "ja_JP", GunName);
                            }
                    		SUItemArmorH iiitem = (SUItemArmorH)newgun;
                    		iiitem.armor_layer1 = armortex;
                    		iiitem.armor_layer2 = armortex;
                    		iiitem.armor_obj = armorobj;
                    		iiitem.speed = speed;
                    		iiitem.maxspeed = mspeed;
                    		iiitem.praspeed = pspeed;
                    		iiitem.pralx = plx;
                    		iiitem.praly = ply;
                    		iiitem.pralz = plz;
                    		iiitem.prarx = prx;
                    		iiitem.prary = pry;
                    		iiitem.prarz = prz;
                    		iiitem.tankk = AdvancedModelLoader.loadModel(new ResourceLocation("strikerunit:textures/model/" + armorobj));
                    		Guns.add(newgun);*/
                		}
                		
                		if(type[0].equals("Recipe1")){
          				  re1 = type[1];
              		    }
                		if(type[0].equals("Recipe2")){
            				  re2 = type[1];
                		}
                		if(type[0].equals("Recipe3")){
            				  re3 = type[1];
                		    }
                		if(type[0].equals("ItemA") && !type[1].equals("null")){
                			itema = GameRegistry.findItem(type[1], type[2]);
                		    }
                		if(type[0].equals("ItemB") && !type[1].equals("null")){
                    			itemb = GameRegistry.findItem(type[1], type[2]);
                    		}
                		if(type[0].equals("ItemC") && !type[1].equals("null")){
                    			itemc = GameRegistry.findItem(type[1], type[2]);
                    		    }
                		if(type[0].equals("ItemD") && !type[1].equals("null")){
                    			itemd = GameRegistry.findItem(type[1], type[2]);
                    		    }
                		if(type[0].equals("ItemE") && !type[1].equals("null")){
                    			iteme = GameRegistry.findItem(type[1], type[2]);
                    		    }
                		if(type[0].equals("ItemF") && !type[1].equals("null")){
                    			itemf = GameRegistry.findItem(type[1], type[2]);
                    		    }
                		if(type[0].equals("ItemG") && !type[1].equals("null")){
                    			itemg = GameRegistry.findItem(type[1], type[2]);
                    		    }
                		if(type[0].equals("ItemH") && !type[1].equals("null")){
                    			itemh = GameRegistry.findItem(type[1], type[2]);
                		}
                		if(type[0].equals("ItemI") && !type[1].equals("null")){
                    			itemi = GameRegistry.findItem(type[1], type[2]);
                		}
                		if(type[0].equals("addNewRecipe")){
                			Item additem = GameRegistry.findItem("StrikerUnit", type[1]);
              			  int kazu1 = Integer.parseInt(type[2]);
              			GameRegistry.addRecipe(new ItemStack(additem, kazu1),
                  				re1,
                  				re2,
                  				re3, 
                  				'a',itema,
                  				'b',itemb,
                  				'c',itemc,
                  				'd',itemd,
                  				'e',iteme,
                  				'f',itemf,
                  				'g',itemg,
                  				'h',itemh,
                  				'i',itemi
                			);
                		}
                		
                		
                		
              }//1
        	  
        	  
        	  
        	  
        	  
          }
          br.close();  // ファイルを閉じる
          }
          else 
          {
        	  
          }
      } catch (FileNotFoundException ex) {
          ex.printStackTrace();
      } catch (IOException ex) {
          ex.printStackTrace();
      }
  }
  
  private static boolean checkBeforeReadfile(File file){
	    if (file.exists()){
	      if (file.isFile() && file.canRead()){
	        return true;
	      }
	    }

	    return false;
	  }
}
