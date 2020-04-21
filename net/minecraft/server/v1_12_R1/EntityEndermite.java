/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityEndermite
/*     */   extends EntityMonster
/*     */ {
/*     */   private int a;
/*     */   private boolean b;
/*     */   
/*     */   public EntityEndermite(World paramWorld) {
/*  35 */     super(paramWorld);
/*  36 */     this.b_ = 3;
/*     */     
/*  38 */     setSize(0.4F, 0.3F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void r() {
/*  43 */     this.goalSelector.a(1, new PathfinderGoalFloat(this));
/*  44 */     this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, 1.0D, false));
/*  45 */     this.goalSelector.a(3, new PathfinderGoalRandomStrollLand(this, 1.0D));
/*  46 */     this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
/*  47 */     this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
/*     */     
/*  49 */     this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true, new Class[0]));
/*  50 */     this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
/*     */   }
/*     */ 
/*     */   
/*     */   public float getHeadHeight() {
/*  55 */     return 0.1F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initAttributes() {
/*  60 */     super.initAttributes();
/*     */     
/*  62 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(8.0D);
/*  63 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.25D);
/*  64 */     getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(2.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean playStepSound() {
/*  69 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect F() {
/*  74 */     return SoundEffects.bj;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect d(DamageSource paramDamageSource) {
/*  79 */     return SoundEffects.bl;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect cf() {
/*  84 */     return SoundEffects.bk;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockPosition paramBlockPosition, Block paramBlock) {
/*  89 */     a(SoundEffects.bm, 0.15F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/*  95 */     return LootTables.al;
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager paramDataConverterManager) {
/*  99 */     EntityInsentient.a(paramDataConverterManager, EntityEndermite.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(NBTTagCompound paramNBTTagCompound) {
/* 104 */     super.a(paramNBTTagCompound);
/* 105 */     this.a = paramNBTTagCompound.getInt("Lifetime");
/* 106 */     this.b = paramNBTTagCompound.getBoolean("PlayerSpawned");
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(NBTTagCompound paramNBTTagCompound) {
/* 111 */     super.b(paramNBTTagCompound);
/* 112 */     paramNBTTagCompound.setInt("Lifetime", this.a);
/* 113 */     paramNBTTagCompound.setBoolean("PlayerSpawned", this.b);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void B_() {
/* 119 */     this.aN = this.yaw;
/*     */     
/* 121 */     super.B_();
/*     */   }
/*     */ 
/*     */   
/*     */   public void h(float paramFloat) {
/* 126 */     this.yaw = paramFloat;
/* 127 */     super.h(paramFloat);
/*     */   }
/*     */ 
/*     */   
/*     */   public double aF() {
/* 132 */     return 0.1D;
/*     */   }
/*     */   
/*     */   public boolean p() {
/* 136 */     return this.b;
/*     */   }
/*     */   
/*     */   public void a(boolean paramBoolean) {
/* 140 */     this.b = paramBoolean;
/*     */   }
/*     */ 
/*     */   
/*     */   public void n() {
/* 145 */     super.n();
/*     */     
/* 147 */     if (this.world.isClientSide) {
/* 148 */       for (byte b = 0; b < 2; b++) {
/* 149 */         this.world.addParticle(EnumParticle.PORTAL, this.locX + (this.random.nextDouble() - 0.5D) * this.width, this.locY + this.random.nextDouble() * this.length, this.locZ + (this.random.nextDouble() - 0.5D) * this.width, (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D, new int[0]);
/*     */       }
/*     */     } else {
/* 152 */       if (!isPersistent()) {
/* 153 */         this.a++;
/*     */       }
/*     */       
/* 156 */       if (this.a >= 2400) {
/* 157 */         die();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean s_() {
/* 164 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean P() {
/* 169 */     if (super.P()) {
/* 170 */       EntityHuman entityHuman = this.world.findNearbyPlayer(this, 5.0D);
/* 171 */       return (entityHuman == null);
/*     */     } 
/* 173 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumMonsterType getMonsterType() {
/* 178 */     return EnumMonsterType.ARTHROPOD;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityEndermite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */