/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ 
/*     */ public class EntityPig
/*     */   extends EntityAnimal {
/*  11 */   private static final DataWatcherObject<Boolean> bx = DataWatcher.a((Class)EntityPig.class, DataWatcherRegistry.h);
/*  12 */   private static final DataWatcherObject<Integer> by = DataWatcher.a((Class)EntityPig.class, DataWatcherRegistry.b);
/*  13 */   private static final Set<Item> bz = Sets.newHashSet((Object[])new Item[] { Items.CARROT, Items.POTATO, Items.BEETROOT });
/*     */   private boolean bB;
/*     */   private int bC;
/*     */   private int bD;
/*     */   
/*     */   public EntityPig(World world) {
/*  19 */     super(world);
/*  20 */     setSize(0.9F, 0.9F);
/*     */   }
/*     */   
/*     */   protected void r() {
/*  24 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*  25 */     this.goalSelector.a(1, new PathfinderGoalPanic(this, 1.25D));
/*  26 */     this.goalSelector.a(3, new PathfinderGoalBreed(this, 1.0D));
/*  27 */     this.goalSelector.a(4, new PathfinderGoalTempt(this, 1.2D, Items.CARROT_ON_A_STICK, false));
/*  28 */     this.goalSelector.a(4, new PathfinderGoalTempt(this, 1.2D, false, bz));
/*  29 */     this.goalSelector.a(5, new PathfinderGoalFollowParent(this, 1.1D));
/*  30 */     this.goalSelector.a(6, new PathfinderGoalRandomStrollLand(this, 1.0D));
/*  31 */     this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 6.0F));
/*  32 */     this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
/*     */   }
/*     */   
/*     */   protected void initAttributes() {
/*  36 */     super.initAttributes();
/*  37 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(10.0D);
/*  38 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.25D);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Entity bE() {
/*  43 */     return bF().isEmpty() ? null : bF().get(0);
/*     */   }
/*     */   
/*     */   public boolean cV() {
/*  47 */     Entity entity = bE();
/*     */     
/*  49 */     if (!(entity instanceof EntityHuman)) {
/*  50 */       return false;
/*     */     }
/*  52 */     EntityHuman entityhuman = (EntityHuman)entity;
/*     */     
/*  54 */     return !(entityhuman.getItemInMainHand().getItem() != Items.CARROT_ON_A_STICK && entityhuman.getItemInOffHand().getItem() != Items.CARROT_ON_A_STICK);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(DataWatcherObject<?> datawatcherobject) {
/*  59 */     if (by.equals(datawatcherobject) && this.world.isClientSide) {
/*  60 */       this.bB = true;
/*  61 */       this.bC = 0;
/*  62 */       this.bD = ((Integer)this.datawatcher.<Integer>get(by)).intValue();
/*     */     } 
/*     */     
/*  65 */     super.a(datawatcherobject);
/*     */   }
/*     */   
/*     */   protected void i() {
/*  69 */     super.i();
/*  70 */     this.datawatcher.register(bx, Boolean.valueOf(false));
/*  71 */     this.datawatcher.register(by, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {
/*  75 */     EntityInsentient.a(dataconvertermanager, EntityPig.class);
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/*  79 */     super.b(nbttagcompound);
/*  80 */     nbttagcompound.setBoolean("Saddle", hasSaddle());
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/*  84 */     super.a(nbttagcompound);
/*  85 */     setSaddle(nbttagcompound.getBoolean("Saddle"));
/*     */   }
/*     */   
/*     */   protected SoundEffect F() {
/*  89 */     return SoundEffects.fo;
/*     */   }
/*     */   
/*     */   protected SoundEffect d(DamageSource damagesource) {
/*  93 */     return SoundEffects.fq;
/*     */   }
/*     */   
/*     */   protected SoundEffect cf() {
/*  97 */     return SoundEffects.fp;
/*     */   }
/*     */   
/*     */   protected void a(BlockPosition blockposition, Block block) {
/* 101 */     a(SoundEffects.fs, 0.15F, 1.0F);
/*     */   }
/*     */   
/*     */   public boolean a(EntityHuman entityhuman, EnumHand enumhand) {
/* 105 */     if (!super.a(entityhuman, enumhand)) {
/* 106 */       ItemStack itemstack = entityhuman.b(enumhand);
/*     */       
/* 108 */       if (itemstack.getItem() == Items.NAME_TAG) {
/* 109 */         itemstack.a(entityhuman, this, enumhand);
/* 110 */         return true;
/* 111 */       }  if (hasSaddle() && !isVehicle()) {
/* 112 */         if (!this.world.isClientSide) {
/* 113 */           entityhuman.startRiding(this);
/*     */         }
/*     */         
/* 116 */         return true;
/* 117 */       }  if (itemstack.getItem() == Items.SADDLE) {
/* 118 */         itemstack.a(entityhuman, this, enumhand);
/* 119 */         return true;
/*     */       } 
/* 121 */       return false;
/*     */     } 
/*     */     
/* 124 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void die(DamageSource damagesource) {
/* 130 */     if (!this.world.isClientSide && 
/* 131 */       hasSaddle()) {
/* 132 */       a(Items.SADDLE, 1);
/*     */     }
/*     */ 
/*     */     
/* 136 */     super.die(damagesource);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/* 141 */     return LootTables.E;
/*     */   }
/*     */   
/*     */   public boolean hasSaddle() {
/* 145 */     return ((Boolean)this.datawatcher.<Boolean>get(bx)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setSaddle(boolean flag) {
/* 149 */     if (flag) {
/* 150 */       this.datawatcher.set(bx, Boolean.valueOf(true));
/*     */     } else {
/* 152 */       this.datawatcher.set(bx, Boolean.valueOf(false));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLightningStrike(EntityLightning entitylightning) {
/* 158 */     if (!this.world.isClientSide && !this.dead) {
/* 159 */       EntityPigZombie entitypigzombie = new EntityPigZombie(this.world);
/*     */ 
/*     */       
/* 162 */       if (CraftEventFactory.callPigZapEvent(this, entitylightning, entitypigzombie).isCancelled()) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 167 */       entitypigzombie.setSlot(EnumItemSlot.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
/* 168 */       entitypigzombie.setPositionRotation(this.locX, this.locY, this.locZ, this.yaw, this.pitch);
/* 169 */       entitypigzombie.setNoAI(isNoAI());
/* 170 */       if (hasCustomName()) {
/* 171 */         entitypigzombie.setCustomName(getCustomName());
/* 172 */         entitypigzombie.setCustomNameVisible(getCustomNameVisible());
/*     */       } 
/*     */ 
/*     */       
/* 176 */       this.world.addEntity(entitypigzombie, CreatureSpawnEvent.SpawnReason.LIGHTNING);
/* 177 */       die();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a(float f, float f1, float f2) {
/* 182 */     Entity entity = bF().isEmpty() ? null : bF().get(0);
/*     */     
/* 184 */     if (isVehicle() && cV()) {
/* 185 */       this.yaw = entity.yaw;
/* 186 */       this.lastYaw = this.yaw;
/* 187 */       this.pitch = entity.pitch * 0.5F;
/* 188 */       setYawPitch(this.yaw, this.pitch);
/* 189 */       this.aN = this.yaw;
/* 190 */       this.aP = this.yaw;
/* 191 */       this.P = 1.0F;
/* 192 */       this.aR = cy() * 0.1F;
/* 193 */       if (this.bB && this.bC++ > this.bD) {
/* 194 */         this.bB = false;
/*     */       }
/*     */       
/* 197 */       if (bI()) {
/* 198 */         float f3 = (float)getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).getValue() * 0.225F;
/*     */         
/* 200 */         if (this.bB) {
/* 201 */           f3 += f3 * 1.15F * MathHelper.sin(this.bC / this.bD * 3.1415927F);
/*     */         }
/*     */         
/* 204 */         k(f3);
/* 205 */         super.a(0.0F, 0.0F, 1.0F);
/*     */       } else {
/* 207 */         this.motX = 0.0D;
/* 208 */         this.motY = 0.0D;
/* 209 */         this.motZ = 0.0D;
/*     */       } 
/*     */       
/* 212 */       this.aF = this.aG;
/* 213 */       double d0 = this.locX - this.lastX;
/* 214 */       double d1 = this.locZ - this.lastZ;
/* 215 */       float f4 = MathHelper.sqrt(d0 * d0 + d1 * d1) * 4.0F;
/*     */       
/* 217 */       if (f4 > 1.0F) {
/* 218 */         f4 = 1.0F;
/*     */       }
/*     */       
/* 221 */       this.aG += (f4 - this.aG) * 0.4F;
/* 222 */       this.aH += this.aG;
/*     */     } else {
/* 224 */       this.P = 0.5F;
/* 225 */       this.aR = 0.02F;
/* 226 */       super.a(f, f1, f2);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean dm() {
/* 231 */     if (this.bB) {
/* 232 */       return false;
/*     */     }
/* 234 */     this.bB = true;
/* 235 */     this.bC = 0;
/* 236 */     this.bD = getRandom().nextInt(841) + 140;
/* 237 */     getDataWatcher().set(by, Integer.valueOf(this.bD));
/* 238 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityPig b(EntityAgeable entityageable) {
/* 243 */     return new EntityPig(this.world);
/*     */   }
/*     */   
/*     */   public boolean e(ItemStack itemstack) {
/* 247 */     return bz.contains(itemstack.getItem());
/*     */   }
/*     */   
/*     */   public EntityAgeable createChild(EntityAgeable entityageable) {
/* 251 */     return b(entityageable);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityPig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */