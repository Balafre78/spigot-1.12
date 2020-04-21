/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftLivingEntity;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.event.entity.LingeringPotionSplashEvent;
/*     */ import org.bukkit.event.entity.PotionSplashEvent;
/*     */ 
/*     */ public class EntityPotion extends EntityProjectile {
/*  18 */   private static final DataWatcherObject<ItemStack> e = DataWatcher.a((Class)EntityPotion.class, DataWatcherRegistry.f);
/*  19 */   private static final Logger f = LogManager.getLogger();
/*  20 */   public static final Predicate<EntityLiving> d = new Predicate() {
/*     */       public boolean a(@Nullable EntityLiving entityliving) {
/*  22 */         return EntityPotion.c(entityliving);
/*     */       }
/*     */       
/*     */       public boolean apply(@Nullable Object object) {
/*  26 */         return a((EntityLiving)object);
/*     */       }
/*     */     };
/*     */   
/*     */   public EntityPotion(World world) {
/*  31 */     super(world);
/*     */   }
/*     */   
/*     */   public EntityPotion(World world, EntityLiving entityliving, ItemStack itemstack) {
/*  35 */     super(world, entityliving);
/*  36 */     setItem(itemstack);
/*     */   }
/*     */   
/*     */   public EntityPotion(World world, double d0, double d1, double d2, ItemStack itemstack) {
/*  40 */     super(world, d0, d1, d2);
/*  41 */     if (!itemstack.isEmpty()) {
/*  42 */       setItem(itemstack);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void i() {
/*  48 */     getDataWatcher().register(e, ItemStack.a);
/*     */   }
/*     */   
/*     */   public ItemStack getItem() {
/*  52 */     ItemStack itemstack = getDataWatcher().<ItemStack>get(e);
/*     */     
/*  54 */     if (itemstack.getItem() != Items.SPLASH_POTION && itemstack.getItem() != Items.LINGERING_POTION) {
/*  55 */       if (this.world != null) {
/*  56 */         f.error("ThrownPotion entity {} has no item?!", Integer.valueOf(getId()));
/*     */       }
/*     */       
/*  59 */       return new ItemStack(Items.SPLASH_POTION);
/*     */     } 
/*  61 */     return itemstack;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItem(ItemStack itemstack) {
/*  66 */     getDataWatcher().set(e, itemstack);
/*  67 */     getDataWatcher().markDirty(e);
/*     */   }
/*     */   
/*     */   protected float j() {
/*  71 */     return 0.05F;
/*     */   }
/*     */   
/*     */   protected void a(MovingObjectPosition movingobjectposition) {
/*  75 */     if (!this.world.isClientSide) {
/*  76 */       ItemStack itemstack = getItem();
/*  77 */       PotionRegistry potionregistry = PotionUtil.d(itemstack);
/*  78 */       List<MobEffect> list = PotionUtil.getEffects(itemstack);
/*  79 */       boolean flag = (potionregistry == Potions.b && list.isEmpty());
/*     */       
/*  81 */       if (movingobjectposition.type == MovingObjectPosition.EnumMovingObjectType.BLOCK && flag) {
/*  82 */         BlockPosition blockposition = movingobjectposition.a().shift(movingobjectposition.direction);
/*     */         
/*  84 */         a(blockposition, movingobjectposition.direction);
/*  85 */         Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */         
/*  87 */         while (iterator.hasNext()) {
/*  88 */           EnumDirection enumdirection = iterator.next();
/*     */           
/*  90 */           a(blockposition.shift(enumdirection), enumdirection);
/*     */         } 
/*     */       } 
/*     */       
/*  94 */       if (flag) {
/*  95 */         n();
/*     */       }
/*  97 */       else if (isLingering()) {
/*  98 */         a(itemstack, potionregistry);
/*     */       } else {
/* 100 */         a(movingobjectposition, list);
/*     */       } 
/*     */ 
/*     */       
/* 104 */       int i = potionregistry.c() ? 2007 : 2002;
/*     */       
/* 106 */       this.world.triggerEffect(i, new BlockPosition(this), PotionUtil.c(itemstack));
/* 107 */       die();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void n() {
/* 112 */     AxisAlignedBB axisalignedbb = getBoundingBox().grow(4.0D, 2.0D, 4.0D);
/* 113 */     List<EntityLiving> list = this.world.a(EntityLiving.class, axisalignedbb, d);
/*     */     
/* 115 */     if (!list.isEmpty()) {
/* 116 */       Iterator<EntityLiving> iterator = list.iterator();
/*     */       
/* 118 */       while (iterator.hasNext()) {
/* 119 */         EntityLiving entityliving = iterator.next();
/* 120 */         double d0 = h(entityliving);
/*     */         
/* 122 */         if (d0 < 16.0D && c(entityliving)) {
/* 123 */           entityliving.damageEntity(DamageSource.DROWN, 1.0F);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(MovingObjectPosition movingobjectposition, List<MobEffect> list) {
/* 131 */     AxisAlignedBB axisalignedbb = getBoundingBox().grow(4.0D, 2.0D, 4.0D);
/* 132 */     List<EntityLiving> list1 = this.world.a(EntityLiving.class, axisalignedbb);
/* 133 */     Map<LivingEntity, Double> affected = new HashMap<>();
/*     */     
/* 135 */     if (!list1.isEmpty()) {
/* 136 */       Iterator<EntityLiving> iterator = list1.iterator();
/*     */       
/* 138 */       while (iterator.hasNext()) {
/* 139 */         EntityLiving entityliving = iterator.next();
/*     */         
/* 141 */         if (entityliving.cR()) {
/* 142 */           double d0 = h(entityliving);
/*     */           
/* 144 */           if (d0 < 16.0D) {
/* 145 */             double d1 = 1.0D - Math.sqrt(d0) / 4.0D;
/*     */             
/* 147 */             if (entityliving == movingobjectposition.entity) {
/* 148 */               d1 = 1.0D;
/*     */             }
/*     */ 
/*     */             
/* 152 */             affected.put((LivingEntity)entityliving.getBukkitEntity(), Double.valueOf(d1));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 158 */     PotionSplashEvent event = CraftEventFactory.callPotionSplashEvent(this, affected);
/* 159 */     if (!event.isCancelled() && list != null && !list.isEmpty()) {
/* 160 */       for (LivingEntity victim : event.getAffectedEntities()) {
/* 161 */         if (!(victim instanceof CraftLivingEntity)) {
/*     */           continue;
/*     */         }
/*     */         
/* 165 */         EntityLiving entityliving = ((CraftLivingEntity)victim).getHandle();
/* 166 */         double d1 = event.getIntensity(victim);
/*     */ 
/*     */         
/* 169 */         Iterator<MobEffect> iterator1 = list.iterator();
/*     */         
/* 171 */         while (iterator1.hasNext()) {
/* 172 */           MobEffect mobeffect = iterator1.next();
/* 173 */           MobEffectList mobeffectlist = mobeffect.getMobEffect();
/*     */           
/* 175 */           if (!this.world.pvpMode && getShooter() instanceof EntityPlayer && entityliving instanceof EntityPlayer && entityliving != getShooter()) {
/* 176 */             int j = MobEffectList.getId(mobeffectlist);
/*     */             
/* 178 */             if (j == 2 || j == 4 || j == 7 || j == 15 || j == 17 || j == 18 || j == 19) {
/*     */               continue;
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 184 */           if (mobeffectlist.isInstant()) {
/* 185 */             mobeffectlist.applyInstantEffect(this, getShooter(), entityliving, mobeffect.getAmplifier(), d1); continue;
/*     */           } 
/* 187 */           int i = (int)(d1 * mobeffect.getDuration() + 0.5D);
/*     */           
/* 189 */           if (i > 20) {
/* 190 */             entityliving.addEffect(new MobEffect(mobeffectlist, i, mobeffect.getAmplifier(), mobeffect.isAmbient(), mobeffect.isShowParticles()));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void a(ItemStack itemstack, PotionRegistry potionregistry) {
/* 200 */     EntityAreaEffectCloud entityareaeffectcloud = new EntityAreaEffectCloud(this.world, this.locX, this.locY, this.locZ);
/*     */     
/* 202 */     entityareaeffectcloud.setSource(getShooter());
/* 203 */     entityareaeffectcloud.setRadius(3.0F);
/* 204 */     entityareaeffectcloud.setRadiusOnUse(-0.5F);
/* 205 */     entityareaeffectcloud.setWaitTime(10);
/* 206 */     entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / entityareaeffectcloud.getDuration());
/* 207 */     entityareaeffectcloud.a(potionregistry);
/* 208 */     Iterator<MobEffect> iterator = PotionUtil.b(itemstack).iterator();
/*     */     
/* 210 */     while (iterator.hasNext()) {
/* 211 */       MobEffect mobeffect = iterator.next();
/*     */       
/* 213 */       entityareaeffectcloud.a(new MobEffect(mobeffect));
/*     */     } 
/*     */     
/* 216 */     NBTTagCompound nbttagcompound = itemstack.getTag();
/*     */     
/* 218 */     if (nbttagcompound != null && nbttagcompound.hasKeyOfType("CustomPotionColor", 99)) {
/* 219 */       entityareaeffectcloud.setColor(nbttagcompound.getInt("CustomPotionColor"));
/*     */     }
/*     */ 
/*     */     
/* 223 */     LingeringPotionSplashEvent event = CraftEventFactory.callLingeringPotionSplashEvent(this, entityareaeffectcloud);
/* 224 */     if (!event.isCancelled() && !entityareaeffectcloud.dead) {
/* 225 */       this.world.addEntity(entityareaeffectcloud);
/*     */     } else {
/* 227 */       entityareaeffectcloud.dead = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLingering() {
/* 233 */     return (getItem().getItem() == Items.LINGERING_POTION);
/*     */   }
/*     */   
/*     */   private void a(BlockPosition blockposition, EnumDirection enumdirection) {
/* 237 */     if (this.world.getType(blockposition).getBlock() == Blocks.FIRE) {
/* 238 */       this.world.douseFire(null, blockposition.shift(enumdirection), enumdirection.opposite());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {
/* 244 */     EntityProjectile.a(dataconvertermanager, "ThrownPotion");
/* 245 */     dataconvertermanager.a(DataConverterTypes.ENTITY, new DataInspectorItem(EntityPotion.class, new String[] { "Potion" }));
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/* 249 */     super.a(nbttagcompound);
/* 250 */     ItemStack itemstack = new ItemStack(nbttagcompound.getCompound("Potion"));
/*     */     
/* 252 */     if (itemstack.isEmpty()) {
/* 253 */       die();
/*     */     } else {
/* 255 */       setItem(itemstack);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/* 261 */     super.b(nbttagcompound);
/* 262 */     ItemStack itemstack = getItem();
/*     */     
/* 264 */     if (!itemstack.isEmpty()) {
/* 265 */       nbttagcompound.set("Potion", itemstack.save(new NBTTagCompound()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean c(EntityLiving entityliving) {
/* 271 */     return !(!(entityliving instanceof EntityEnderman) && !(entityliving instanceof EntityBlaze));
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */