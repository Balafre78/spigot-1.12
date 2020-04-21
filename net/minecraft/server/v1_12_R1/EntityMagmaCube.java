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
/*     */ public class EntityMagmaCube
/*     */   extends EntitySlime
/*     */ {
/*     */   public EntityMagmaCube(World paramWorld) {
/*  18 */     super(paramWorld);
/*  19 */     this.fireProof = true;
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager paramDataConverterManager) {
/*  23 */     EntityInsentient.a(paramDataConverterManager, EntityMagmaCube.class);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initAttributes() {
/*  28 */     super.initAttributes();
/*     */     
/*  30 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.20000000298023224D);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean P() {
/*  35 */     return (this.world.getDifficulty() != EnumDifficulty.PEACEFUL);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawn() {
/*  40 */     return (this.world.a(getBoundingBox(), this) && this.world.getCubes(this, getBoundingBox()).isEmpty() && !this.world.containsLiquid(getBoundingBox()));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setSize(int paramInt, boolean paramBoolean) {
/*  45 */     super.setSize(paramInt, paramBoolean);
/*  46 */     getAttributeInstance(GenericAttributes.h).setValue((paramInt * 3));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float aw() {
/*  56 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected EnumParticle p() {
/*  61 */     return EnumParticle.FLAME;
/*     */   }
/*     */ 
/*     */   
/*     */   protected EntitySlime de() {
/*  66 */     return new EntityMagmaCube(this.world);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/*  72 */     return dm() ? LootTables.a : LootTables.ai;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBurning() {
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int df() {
/*  82 */     return super.df() * 4;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void dg() {
/*  87 */     this.a *= 0.9F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void cu() {
/*  92 */     this.motY = (0.42F + getSize() * 0.1F);
/*  93 */     this.impulse = true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void cw() {
/*  98 */     this.motY = (0.22F + getSize() * 0.05F);
/*  99 */     this.impulse = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void e(float paramFloat1, float paramFloat2) {}
/*     */ 
/*     */   
/*     */   protected boolean dh() {
/* 108 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int di() {
/* 113 */     return super.di() + 2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect d(DamageSource paramDamageSource) {
/* 118 */     if (dm()) {
/* 119 */       return SoundEffects.hj;
/*     */     }
/* 121 */     return SoundEffects.dW;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEffect cf() {
/* 127 */     if (dm()) {
/* 128 */       return SoundEffects.hi;
/*     */     }
/* 130 */     return SoundEffects.dV;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEffect dj() {
/* 136 */     if (dm()) {
/* 137 */       return SoundEffects.hk;
/*     */     }
/* 139 */     return SoundEffects.dY;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEffect dk() {
/* 145 */     return SoundEffects.dX;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityMagmaCube.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */