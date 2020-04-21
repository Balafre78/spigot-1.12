/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class EntityChicken
/*     */   extends EntityAnimal {
/*   9 */   private static final Set<Item> bF = Sets.newHashSet((Object[])new Item[] { Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS });
/*     */   public float bx;
/*     */   public float by;
/*     */   public float bz;
/*     */   public float bB;
/*  14 */   public float bC = 1.0F;
/*     */   public int bD;
/*     */   public boolean bE;
/*     */   
/*     */   public EntityChicken(World world) {
/*  19 */     super(world);
/*  20 */     setSize(0.4F, 0.7F);
/*  21 */     this.bD = this.random.nextInt(6000) + 6000;
/*  22 */     a(PathType.WATER, 0.0F);
/*     */   }
/*     */   
/*     */   protected void r() {
/*  26 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*  27 */     this.goalSelector.a(1, new PathfinderGoalPanic(this, 1.4D));
/*  28 */     this.goalSelector.a(2, new PathfinderGoalBreed(this, 1.0D));
/*  29 */     this.goalSelector.a(3, new PathfinderGoalTempt(this, 1.0D, false, bF));
/*  30 */     this.goalSelector.a(4, new PathfinderGoalFollowParent(this, 1.1D));
/*  31 */     this.goalSelector.a(5, new PathfinderGoalRandomStrollLand(this, 1.0D));
/*  32 */     this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 6.0F));
/*  33 */     this.goalSelector.a(7, new PathfinderGoalRandomLookaround(this));
/*     */   }
/*     */   
/*     */   public float getHeadHeight() {
/*  37 */     return this.length;
/*     */   }
/*     */   
/*     */   protected void initAttributes() {
/*  41 */     super.initAttributes();
/*  42 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(4.0D);
/*  43 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.25D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void n() {
/*  48 */     if (isChickenJockey()) {
/*  49 */       this.persistent = !isTypeNotPersistent();
/*     */     }
/*     */     
/*  52 */     super.n();
/*  53 */     this.bB = this.bx;
/*  54 */     this.bz = this.by;
/*  55 */     this.by = (float)(this.by + (this.onGround ? -1 : 4) * 0.3D);
/*  56 */     this.by = MathHelper.a(this.by, 0.0F, 1.0F);
/*  57 */     if (!this.onGround && this.bC < 1.0F) {
/*  58 */       this.bC = 1.0F;
/*     */     }
/*     */     
/*  61 */     this.bC = (float)(this.bC * 0.9D);
/*  62 */     if (!this.onGround && this.motY < 0.0D) {
/*  63 */       this.motY *= 0.6D;
/*     */     }
/*     */     
/*  66 */     this.bx += this.bC * 2.0F;
/*  67 */     if (!this.world.isClientSide && !isBaby() && !isChickenJockey() && --this.bD <= 0) {
/*  68 */       a(SoundEffects.af, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
/*  69 */       this.forceDrops = true;
/*  70 */       a(Items.EGG, 1);
/*  71 */       this.forceDrops = false;
/*  72 */       this.bD = this.random.nextInt(6000) + 6000;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void e(float f, float f1) {}
/*     */   
/*     */   protected SoundEffect F() {
/*  80 */     return SoundEffects.ad;
/*     */   }
/*     */   
/*     */   protected SoundEffect d(DamageSource damagesource) {
/*  84 */     return SoundEffects.ag;
/*     */   }
/*     */   
/*     */   protected SoundEffect cf() {
/*  88 */     return SoundEffects.ae;
/*     */   }
/*     */   
/*     */   protected void a(BlockPosition blockposition, Block block) {
/*  92 */     a(SoundEffects.ah, 0.15F, 1.0F);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/*  97 */     return LootTables.D;
/*     */   }
/*     */   
/*     */   public EntityChicken b(EntityAgeable entityageable) {
/* 101 */     return new EntityChicken(this.world);
/*     */   }
/*     */   
/*     */   public boolean e(ItemStack itemstack) {
/* 105 */     return bF.contains(itemstack.getItem());
/*     */   }
/*     */   
/*     */   protected int getExpValue(EntityHuman entityhuman) {
/* 109 */     return isChickenJockey() ? 10 : super.getExpValue(entityhuman);
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {
/* 113 */     EntityInsentient.a(dataconvertermanager, EntityChicken.class);
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/* 117 */     super.a(nbttagcompound);
/* 118 */     this.bE = nbttagcompound.getBoolean("IsChickenJockey");
/* 119 */     if (nbttagcompound.hasKey("EggLayTime")) {
/* 120 */       this.bD = nbttagcompound.getInt("EggLayTime");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/* 126 */     super.b(nbttagcompound);
/* 127 */     nbttagcompound.setBoolean("IsChickenJockey", this.bE);
/* 128 */     nbttagcompound.setInt("EggLayTime", this.bD);
/*     */   }
/*     */   
/*     */   protected boolean isTypeNotPersistent() {
/* 132 */     return (isChickenJockey() && !isVehicle());
/*     */   }
/*     */   
/*     */   public void k(Entity entity) {
/* 136 */     super.k(entity);
/* 137 */     float f = MathHelper.sin(this.aN * 0.017453292F);
/* 138 */     float f1 = MathHelper.cos(this.aN * 0.017453292F);
/*     */ 
/*     */ 
/*     */     
/* 142 */     entity.setPosition(this.locX + (0.1F * f), this.locY + (this.length * 0.5F) + entity.aF() + 0.0D, this.locZ - (0.1F * f1));
/* 143 */     if (entity instanceof EntityLiving) {
/* 144 */       ((EntityLiving)entity).aN = this.aN;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isChickenJockey() {
/* 150 */     return this.bE;
/*     */   }
/*     */   
/*     */   public void p(boolean flag) {
/* 154 */     this.bE = flag;
/*     */   }
/*     */   
/*     */   public EntityAgeable createChild(EntityAgeable entityageable) {
/* 158 */     return b(entityageable);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityChicken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */