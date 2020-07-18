package strikerunit;

import java.util.Calendar;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.biome.BiomeGenBase;

public class EntityNE extends EntityMob {
	private static final String __OBFID = "CL_00001697";

	public EntityNE(World par1World) {
		super(par1World);
		this.setSize(20F, 4F);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIRestrictSun(this));
		this.tasks.addTask(3, new EntityAIFleeSun(this, 1.0D));
		this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityGolem.class, 0, false));
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(3000.0D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(20D);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(3.0D);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
	}

	protected boolean canDespawn()
    {
        return false;
    }
	
	public int deathtick;

	protected void onDeathUpdate() {
		++deathtick;
		int x = this.worldObj.rand.nextInt(20);
		int y = this.worldObj.rand.nextInt(20);
		int z = this.worldObj.rand.nextInt(20);

		if (!this.worldObj.isRemote) {
			if (this.worldObj.rand.nextInt(5) == 0) {
				this.worldObj.createExplosion(this, this.posX - 10 + x, this.posY - 10 + y, this.posZ - 10 + z, 3.5F,
						false);
			}
			if (this.onGround) {
				for (int lj = 0; lj < 20; lj++) {
					int xx = this.worldObj.rand.nextInt(20);
					int yx = this.worldObj.rand.nextInt(20);
					int zx = this.worldObj.rand.nextInt(20);
					this.worldObj.createExplosion(this, this.posX - 10 + xx, this.posY - 10 + yx, this.posZ - 10 + zx,
							3.5F, false);
				}
				this.setDead();
			} else if (deathtick > 200) {
				for (int lj = 0; lj < 20; lj++) {
					int xx = this.worldObj.rand.nextInt(20);
					int yx = this.worldObj.rand.nextInt(20);
					int zx = this.worldObj.rand.nextInt(20);
					this.worldObj.createExplosion(this, this.posX - 10 + xx, this.posY - 10 + yx, this.posZ - 10 + zx,
							3.5F, false);
				}
				this.setDead();
			}
		}
	}

	int ii;
	float rote;
	public EntityLivingBase targetentity;

	public void onUpdate() {
		super.onUpdate();
		int i = this.worldObj.rand.nextInt(3);
		int genY = this.worldObj.getHeightValue((int) this.posX, (int) this.posZ) + 15 + i;
		if (!this.onGround && this.motionY < 0.0D) {
			this.motionY *= 0.6D;
		}
		if (this.posY < genY && this.getHealth() > 0.0F) {
			this.motionY += 0.2D;
		}
		float f1 = this.rotationYawHead * (2 * (float) Math.PI / 360);
		this.motionX -= MathHelper.sin(f1) * 0.01;
		this.motionZ += MathHelper.cos(f1) * 0.01;
		{
			Entity entity = null;
			List llist = this.worldObj.getEntitiesWithinAABBExcludingEntity(entity,
					this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(120.0D, 120.0D, 120.0D));
			if (llist != null) {
				for (int lj = 0; lj < llist.size(); lj++) {
					Entity entity1 = (Entity) llist.get(lj);
					if (entity1.canBeCollidedWith()) {
						if (entity1 instanceof EntityGolem || entity1 instanceof EntityPlayer && entity1 != null) {
							double d5 = entity1.posX - this.posX;
							double d6 = entity1.boundingBox.minY + (double) (entity1.height / 2.0F)
									- (this.posY + (double) (this.height / 2.0F));
							double d7 = entity1.posZ - this.posZ;
							// this.renderYawOffset = this.rotationYaw =
							// -((float)Math.atan2(d5, d7)) * 180.0F /
							// (float)Math.PI;
							// this.rotationYawHead = this.rotationYaw =
							// -((float)Math.atan2(d5, d7)) * 180.0F /
							// (float)Math.PI;
							this.rote = -((float) Math.atan2(d5, d7)) * 180.0F / (float) Math.PI;
							float turnspeed = 0.5F;
								if (this.rotationYawHead != this.rote) {
									if (this.rotationYawHead < this.rote) {
										this.rotationYawHead = this.rotationYawHead + turnspeed;
										this.rotationYaw = this.rotationYaw + turnspeed;
										this.prevRotationYaw = this.prevRotationYaw + turnspeed;
										this.prevRotationYawHead = this.prevRotationYawHead + turnspeed;
									} else if (this.rotationYawHead > this.rote) {
										this.rotationYawHead = this.rotationYawHead - turnspeed;
										this.rotationYaw = this.rotationYaw - turnspeed;
										this.prevRotationYaw = this.prevRotationYaw - turnspeed;
										this.prevRotationYawHead = this.prevRotationYawHead - turnspeed;
									}
							}
						}
					}
				}
			}
		}

		if (ii > 20) {
			EntityMob entity = null;
			List llist = this.worldObj.getEntitiesWithinAABBExcludingEntity(entity,
					this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(120.0D, 120.0D, 120.0D));
			if (llist != null) {
				for (int lj = 0; lj < llist.size(); lj++) {
					Entity entity1 = (Entity) llist.get(lj);
					if (entity1.canBeCollidedWith()) {
						if (entity1 instanceof EntityGolem
								|| entity1 instanceof EntityPlayer && entity1 != null && this.getHealth() > 0.0F) {
							boolean flag = this.getEntitySenses().canSee(entity1);
							if(flag)
							{
								for (int aa = 0; aa < 2; aa++){
									double xxx = 0;
									double zzz = 0;
									xxx -= MathHelper.sin(this.rotationYawHead * 0.01745329252F - 1.3F) * 5.0;
									zzz += MathHelper.cos(this.rotationYawHead * 0.01745329252F - 1.3F) * 5.0;
									
									EntityNELaser var3 = new EntityNELaser(this.worldObj, this);
									double var4 = entity1.posX - this.posX -xxx;
									double var6 = entity1.posY + (double) entity1.getEyeHeight() - 1.100000023841858D
											- var3.posY;
									double var8 = entity1.posZ - this.posZ -zzz;
									float var10 = MathHelper.sqrt_double(var4 * var4 + var8 * var8) * 0.02F;
									var3.setThrowableHeading(var4, var6 + (double) var10, var8, 1.6F, 2.0F);
									var3.setLocationAndAngles(this.posX + xxx, this.posY + 4, this.posZ + zzz,this.rotationYaw, 0F);
									this.playSound("gvcguns:gvcguns.fire", 5.0F, 1.0F);
									if (!this.worldObj.isRemote) {
										this.worldObj.spawnEntityInWorld(var3);
									}
								}
								for (int bb = 0; bb < 2; bb++){
									double xxx = 0;
									double zzz = 0;
									xxx -= MathHelper.sin(this.rotationYawHead * 0.01745329252F + 1.3F) * 5.0;
									zzz += MathHelper.cos(this.rotationYawHead * 0.01745329252F + 1.3F) * 5.0;
									
									EntityNELaser var3 = new EntityNELaser(this.worldObj, this);
									double var4 = entity1.posX - this.posX -xxx;
									double var6 = entity1.posY + (double) entity1.getEyeHeight() - 1.100000023841858D
											- var3.posY;
									double var8 = entity1.posZ - this.posZ -zzz;
									float var10 = MathHelper.sqrt_double(var4 * var4 + var8 * var8) * 0.02F;
									var3.setThrowableHeading(var4, var6 + (double) var10, var8, 1.6F, 2.0F);
									var3.setLocationAndAngles(this.posX + xxx, this.posY + 4, this.posZ + zzz,this.rotationYaw, 0F);
									this.playSound("gvcguns:gvcguns.fire", 5.0F, 1.0F);
									if (!this.worldObj.isRemote) {
										this.worldObj.spawnEntityInWorld(var3);
									}
								}
							
							break;
							}
						}
					}
				}
			}
			ii = 0;
		} else {
			++ii;
		}

	}

	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(13, new Byte((byte) 0));
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	public boolean isAIEnabled() {
		return true;
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound() {
		return "none";
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound() {
		return "mob.irongolem.hit";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound() {
		return "mob.irongolem.death";
	}

	protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
		this.playSound("gvcmob:gvcmob.tank", 1.20F, 1.0F);
	}

	public boolean attackEntityAsMob(Entity par1Entity) {

		{
			return false;
		}
	}

	/**
	 * Get this Entity's EnumCreatureAttribute
	 */
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}

	/**
	 * Handles updating while being ridden by an entity
	 */
	public void updateRidden() {
		super.updateRidden();

		if (this.ridingEntity instanceof EntityCreature) {
			EntityCreature var1 = (EntityCreature) this.ridingEntity;
			this.renderYawOffset = var1.renderYawOffset;
		}
	}

	protected void dropRareDrop(int par1) {

		this.entityDropItem(new ItemStack(Blocks.beacon, 1), 0.0F);

	}

	/**
	 * Makes entity wear random armor based on difficulty
	 */
	protected void addRandomArmor() {
		super.addRandomArmor();
		// this.setCurrentItemOrArmor(0, new ItemStack(GVCGunsPlus.fn_rpg7));
	}

	public IEntityLivingData onSpawnWithEgg(IEntityLivingData par1EntityLivingData) {
		par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);

		this.setCanPickUpLoot(
				this.rand.nextFloat() < 0.55F * this.worldObj.func_147462_b(this.posX, this.posY, this.posZ));

		if (this.getEquipmentInSlot(4) == null) {
			Calendar var2 = this.worldObj.getCurrentDate();

			if (var2.get(2) + 1 == 10 && var2.get(5) == 31 && this.rand.nextFloat() < 0.25F) {
				this.setCurrentItemOrArmor(4,
						new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.lit_pumpkin : Blocks.pumpkin));
				this.equipmentDropChances[4] = 0.0F;
			}
		}

		return par1EntityLivingData;
	}

	/**
	 * sets this entity's combat AI.
	 */
	public void setCombatTask() {
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);

		this.setCombatTask();
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);
		// par1NBTTagCompound.setByte("SkeletonType",
		// (byte)this.getSkeletonType());
	}

	/**
	 * Sets the held item, or an armor slot. Slot 0 is held item. Slot 1-4 is
	 * armor. Params: Item, slot
	 */
	public void setCurrentItemOrArmor(int par1, ItemStack par2ItemStack) {
		super.setCurrentItemOrArmor(par1, par2ItemStack);

		if (!this.worldObj.isRemote && par1 == 0) {
			this.setCombatTask();
		}
	}

	/**
	 * Returns the Y Offset of this entity.
	 */
	public double getYOffset() {
		return super.getYOffset() - 0.5D;
	}

	public static float getMobScale() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return 2;
	}
}
