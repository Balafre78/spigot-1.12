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
/*     */ public class EntityHorseSkeleton
/*     */   extends EntityHorseAbstract
/*     */ {
/*  22 */   private final PathfinderGoalHorseTrap bH = new PathfinderGoalHorseTrap(this);
/*     */   
/*     */   private boolean bI;
/*     */   
/*     */   private int bJ;
/*     */   
/*     */   public EntityHorseSkeleton(World paramWorld) {
/*  29 */     super(paramWorld);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initAttributes() {
/*  34 */     super.initAttributes();
/*     */     
/*  36 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(15.0D);
/*  37 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.20000000298023224D);
/*  38 */     getAttributeInstance(attributeJumpStrength).setValue(dN());
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect F() {
/*  43 */     super.F();
/*  44 */     return SoundEffects.gS;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect cf() {
/*  49 */     super.cf();
/*  50 */     return SoundEffects.gT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect d(DamageSource paramDamageSource) {
/*  55 */     super.d(paramDamageSource);
/*  56 */     return SoundEffects.gU;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumMonsterType getMonsterType() {
/*  61 */     return EnumMonsterType.UNDEAD;
/*     */   }
/*     */ 
/*     */   
/*     */   public double aG() {
/*  66 */     return super.aG() - 0.1875D;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/*  72 */     return LootTables.K;
/*     */   }
/*     */ 
/*     */   
/*     */   public void n() {
/*  77 */     super.n();
/*     */     
/*  79 */     if (dl() && this.bJ++ >= 18000) {
/*  80 */       die();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager paramDataConverterManager) {
/*  85 */     EntityHorseAbstract.c(paramDataConverterManager, EntityHorseSkeleton.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(NBTTagCompound paramNBTTagCompound) {
/*  90 */     super.b(paramNBTTagCompound);
/*     */     
/*  92 */     paramNBTTagCompound.setBoolean("SkeletonTrap", dl());
/*  93 */     paramNBTTagCompound.setInt("SkeletonTrapTime", this.bJ);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(NBTTagCompound paramNBTTagCompound) {
/*  98 */     super.a(paramNBTTagCompound);
/*     */     
/* 100 */     p(paramNBTTagCompound.getBoolean("SkeletonTrap"));
/* 101 */     this.bJ = paramNBTTagCompound.getInt("SkeletonTrapTime");
/*     */   }
/*     */   
/*     */   public boolean dl() {
/* 105 */     return this.bI;
/*     */   }
/*     */   
/*     */   public void p(boolean paramBoolean) {
/* 109 */     if (paramBoolean == this.bI) {
/*     */       return;
/*     */     }
/*     */     
/* 113 */     this.bI = paramBoolean;
/* 114 */     if (paramBoolean) {
/* 115 */       this.goalSelector.a(1, this.bH);
/*     */     } else {
/* 117 */       this.goalSelector.a(this.bH);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman paramEntityHuman, EnumHand paramEnumHand) {
/* 123 */     ItemStack itemStack = paramEntityHuman.b(paramEnumHand);
/* 124 */     boolean bool = !itemStack.isEmpty() ? true : false;
/* 125 */     if (bool && itemStack.getItem() == Items.SPAWN_EGG) {
/* 126 */       return super.a(paramEntityHuman, paramEnumHand);
/*     */     }
/*     */     
/* 129 */     if (!isTamed()) {
/* 130 */       return false;
/*     */     }
/*     */     
/* 133 */     if (isBaby()) {
/* 134 */       return super.a(paramEntityHuman, paramEnumHand);
/*     */     }
/*     */     
/* 137 */     if (paramEntityHuman.isSneaking()) {
/* 138 */       c(paramEntityHuman);
/* 139 */       return true;
/*     */     } 
/*     */     
/* 142 */     if (isVehicle()) {
/* 143 */       return super.a(paramEntityHuman, paramEnumHand);
/*     */     }
/*     */     
/* 146 */     if (bool) {
/* 147 */       if (itemStack.getItem() == Items.SADDLE && !dG()) {
/* 148 */         c(paramEntityHuman);
/* 149 */         return true;
/*     */       } 
/*     */       
/* 152 */       if (itemStack.a(paramEntityHuman, this, paramEnumHand)) {
/* 153 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 157 */     g(paramEntityHuman);
/* 158 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityHorseSkeleton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */