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
/*     */ public class EntitySquid
/*     */   extends EntityWaterAnimal
/*     */ {
/*     */   public float a;
/*     */   public float b;
/*     */   public float c;
/*     */   public float bv;
/*     */   public float bw;
/*     */   public float bx;
/*     */   public float by;
/*     */   public float bz;
/*     */   private float bA;
/*     */   private float bB;
/*     */   private float bC;
/*     */   private float bD;
/*     */   private float bE;
/*     */   private float bF;
/*     */   
/*     */   public EntitySquid(World paramWorld) {
/*  35 */     super(paramWorld);
/*  36 */     setSize(0.8F, 0.8F);
/*  37 */     this.random.setSeed((1 + getId()));
/*  38 */     this.bB = 1.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager paramDataConverterManager) {
/*  42 */     EntityInsentient.a(paramDataConverterManager, EntitySquid.class);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void r() {
/*  47 */     this.goalSelector.a(0, new PathfinderGoalSquid(this));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initAttributes() {
/*  52 */     super.initAttributes();
/*     */     
/*  54 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(10.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getHeadHeight() {
/*  59 */     return this.length * 0.5F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect F() {
/*  64 */     return SoundEffects.hF;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect d(DamageSource paramDamageSource) {
/*  69 */     return SoundEffects.hH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect cf() {
/*  74 */     return SoundEffects.hG;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float cq() {
/*  79 */     return 0.4F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean playStepSound() {
/*  84 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/*  90 */     return LootTables.ak;
/*     */   }
/*     */ 
/*     */   
/*     */   public void n() {
/*  95 */     super.n();
/*     */     
/*  97 */     this.b = this.a;
/*  98 */     this.bv = this.c;
/*     */     
/* 100 */     this.bx = this.bw;
/* 101 */     this.bz = this.by;
/*     */     
/* 103 */     this.bw += this.bB;
/* 104 */     if (this.bw > 6.283185307179586D) {
/* 105 */       if (this.world.isClientSide) {
/* 106 */         this.bw = 6.2831855F;
/*     */       } else {
/* 108 */         this.bw = (float)(this.bw - 6.283185307179586D);
/* 109 */         if (this.random.nextInt(10) == 0) {
/* 110 */           this.bB = 1.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
/*     */         }
/* 112 */         this.world.broadcastEntityEffect(this, (byte)19);
/*     */       } 
/*     */     }
/*     */     
/* 116 */     if (this.inWater) {
/* 117 */       if (this.bw < 3.1415927F) {
/* 118 */         float f1 = this.bw / 3.1415927F;
/* 119 */         this.by = MathHelper.sin(f1 * f1 * 3.1415927F) * 3.1415927F * 0.25F;
/*     */         
/* 121 */         if (f1 > 0.75D) {
/* 122 */           this.bA = 1.0F;
/* 123 */           this.bC = 1.0F;
/*     */         } else {
/* 125 */           this.bC *= 0.8F;
/*     */         } 
/*     */       } else {
/* 128 */         this.by = 0.0F;
/* 129 */         this.bA *= 0.9F;
/* 130 */         this.bC *= 0.99F;
/*     */       } 
/*     */       
/* 133 */       if (!this.world.isClientSide) {
/* 134 */         this.motX = (this.bD * this.bA);
/* 135 */         this.motY = (this.bE * this.bA);
/* 136 */         this.motZ = (this.bF * this.bA);
/*     */       } 
/*     */       
/* 139 */       float f = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/*     */       
/* 141 */       this.aN += (-((float)MathHelper.c(this.motX, this.motZ)) * 57.295776F - this.aN) * 0.1F;
/* 142 */       this.yaw = this.aN;
/* 143 */       this.c = (float)(this.c + Math.PI * this.bC * 1.5D);
/* 144 */       this.a += (-((float)MathHelper.c(f, this.motY)) * 57.295776F - this.a) * 0.1F;
/*     */     } else {
/* 146 */       this.by = MathHelper.e(MathHelper.sin(this.bw)) * 3.1415927F * 0.25F;
/*     */       
/* 148 */       if (!this.world.isClientSide) {
/*     */         
/* 150 */         this.motX = 0.0D;
/* 151 */         this.motZ = 0.0D;
/*     */         
/* 153 */         if (hasEffect(MobEffects.LEVITATION)) {
/* 154 */           this.motY += 0.05D * (getEffect(MobEffects.LEVITATION).getAmplifier() + 1) - this.motY;
/*     */         }
/* 156 */         else if (!isNoGravity()) {
/* 157 */           this.motY -= 0.08D;
/*     */         } 
/*     */         
/* 160 */         this.motY *= 0.9800000190734863D;
/*     */       } 
/*     */ 
/*     */       
/* 164 */       this.a = (float)(this.a + (-90.0F - this.a) * 0.02D);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 170 */     move(EnumMoveType.SELF, this.motX, this.motY, this.motZ);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean P() {
/* 175 */     return (this.locY > 45.0D && this.locY < this.world.getSeaLevel() && super.P());
/*     */   }
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
/*     */   public void b(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 188 */     this.bD = paramFloat1;
/* 189 */     this.bE = paramFloat2;
/* 190 */     this.bF = paramFloat3;
/*     */   }
/*     */   
/*     */   public boolean p() {
/* 194 */     return (this.bD != 0.0F || this.bE != 0.0F || this.bF != 0.0F);
/*     */   }
/*     */   
/*     */   static class PathfinderGoalSquid extends PathfinderGoal {
/*     */     private final EntitySquid a;
/*     */     
/*     */     public PathfinderGoalSquid(EntitySquid param1EntitySquid) {
/* 201 */       this.a = param1EntitySquid;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 206 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 211 */       int i = this.a.bW();
/*     */       
/* 213 */       if (i > 100) {
/* 214 */         this.a.b(0.0F, 0.0F, 0.0F);
/* 215 */       } else if (this.a.getRandom().nextInt(50) == 0 || !EntitySquid.a(this.a) || !this.a.p()) {
/* 216 */         float f1 = this.a.getRandom().nextFloat() * 6.2831855F;
/* 217 */         float f2 = MathHelper.cos(f1) * 0.2F;
/* 218 */         float f3 = -0.1F + this.a.getRandom().nextFloat() * 0.2F;
/* 219 */         float f4 = MathHelper.sin(f1) * 0.2F;
/* 220 */         this.a.b(f2, f3, f4);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntitySquid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */