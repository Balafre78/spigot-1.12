/*     */ package net.minecraft.server.v1_12_R1;
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
/*     */ public class EntityEnderSignal
/*     */   extends Entity
/*     */ {
/*     */   private double a;
/*     */   private double b;
/*     */   private double c;
/*     */   private int d;
/*     */   private boolean e;
/*     */   
/*     */   public EntityEnderSignal(World paramWorld) {
/*  23 */     super(paramWorld);
/*  24 */     setSize(0.25F, 0.25F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void i() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityEnderSignal(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3) {
/*  42 */     super(paramWorld);
/*  43 */     this.d = 0;
/*     */     
/*  45 */     setSize(0.25F, 0.25F);
/*     */     
/*  47 */     setPosition(paramDouble1, paramDouble2, paramDouble3);
/*     */   }
/*     */   
/*     */   public void a(BlockPosition paramBlockPosition) {
/*  51 */     double d1 = paramBlockPosition.getX();
/*  52 */     int i = paramBlockPosition.getY();
/*  53 */     double d2 = paramBlockPosition.getZ();
/*     */     
/*  55 */     double d3 = d1 - this.locX;
/*  56 */     double d4 = d2 - this.locZ;
/*  57 */     float f = MathHelper.sqrt(d3 * d3 + d4 * d4);
/*     */     
/*  59 */     if (f > 12.0F) {
/*  60 */       this.a = this.locX + d3 / f * 12.0D;
/*  61 */       this.c = this.locZ + d4 / f * 12.0D;
/*  62 */       this.b = this.locY + 8.0D;
/*     */     } else {
/*  64 */       this.a = d1;
/*  65 */       this.b = i;
/*  66 */       this.c = d2;
/*     */     } 
/*     */     
/*  69 */     this.d = 0;
/*  70 */     this.e = (this.random.nextInt(5) > 0);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void B_() {
/*  89 */     this.M = this.locX;
/*  90 */     this.N = this.locY;
/*  91 */     this.O = this.locZ;
/*  92 */     super.B_();
/*     */     
/*  94 */     this.locX += this.motX;
/*  95 */     this.locY += this.motY;
/*  96 */     this.locZ += this.motZ;
/*     */     
/*  98 */     float f1 = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/*  99 */     this.yaw = (float)(MathHelper.c(this.motX, this.motZ) * 57.2957763671875D);
/* 100 */     this.pitch = (float)(MathHelper.c(this.motY, f1) * 57.2957763671875D);
/*     */     
/* 102 */     while (this.pitch - this.lastPitch < -180.0F) {
/* 103 */       this.lastPitch -= 360.0F;
/*     */     }
/* 105 */     while (this.pitch - this.lastPitch >= 180.0F) {
/* 106 */       this.lastPitch += 360.0F;
/*     */     }
/*     */     
/* 109 */     while (this.yaw - this.lastYaw < -180.0F) {
/* 110 */       this.lastYaw -= 360.0F;
/*     */     }
/* 112 */     while (this.yaw - this.lastYaw >= 180.0F) {
/* 113 */       this.lastYaw += 360.0F;
/*     */     }
/*     */     
/* 116 */     this.pitch = this.lastPitch + (this.pitch - this.lastPitch) * 0.2F;
/* 117 */     this.yaw = this.lastYaw + (this.yaw - this.lastYaw) * 0.2F;
/*     */     
/* 119 */     if (!this.world.isClientSide) {
/* 120 */       double d1 = this.a - this.locX;
/* 121 */       double d2 = this.c - this.locZ;
/* 122 */       float f3 = (float)Math.sqrt(d1 * d1 + d2 * d2);
/* 123 */       float f4 = (float)MathHelper.c(d2, d1);
/* 124 */       double d3 = f1 + (f3 - f1) * 0.0025D;
/* 125 */       if (f3 < 1.0F) {
/* 126 */         d3 *= 0.8D;
/* 127 */         this.motY *= 0.8D;
/*     */       } 
/* 129 */       this.motX = Math.cos(f4) * d3;
/* 130 */       this.motZ = Math.sin(f4) * d3;
/*     */       
/* 132 */       if (this.locY < this.b) {
/* 133 */         this.motY += (1.0D - this.motY) * 0.014999999664723873D;
/*     */       } else {
/* 135 */         this.motY += (-1.0D - this.motY) * 0.014999999664723873D;
/*     */       } 
/*     */     } 
/*     */     
/* 139 */     float f2 = 0.25F;
/* 140 */     if (isInWater()) {
/* 141 */       for (byte b = 0; b < 4; b++) {
/* 142 */         this.world.addParticle(EnumParticle.WATER_BUBBLE, this.locX - this.motX * 0.25D, this.locY - this.motY * 0.25D, this.locZ - this.motZ * 0.25D, this.motX, this.motY, this.motZ, new int[0]);
/*     */       }
/*     */     } else {
/* 145 */       this.world.addParticle(EnumParticle.PORTAL, this.locX - this.motX * 0.25D + this.random.nextDouble() * 0.6D - 0.3D, this.locY - this.motY * 0.25D - 0.5D, this.locZ - this.motZ * 0.25D + this.random.nextDouble() * 0.6D - 0.3D, this.motX, this.motY, this.motZ, new int[0]);
/*     */     } 
/*     */     
/* 148 */     if (!this.world.isClientSide) {
/* 149 */       setPosition(this.locX, this.locY, this.locZ);
/*     */       
/* 151 */       this.d++;
/* 152 */       if (this.d > 80 && !this.world.isClientSide) {
/* 153 */         a(SoundEffects.bb, 1.0F, 1.0F);
/* 154 */         die();
/* 155 */         if (this.e) {
/* 156 */           this.world.addEntity(new EntityItem(this.world, this.locX, this.locY, this.locZ, new ItemStack(Items.ENDER_EYE)));
/*     */         } else {
/* 158 */           this.world.triggerEffect(2003, new BlockPosition(this), 0);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(NBTTagCompound paramNBTTagCompound) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(NBTTagCompound paramNBTTagCompound) {}
/*     */ 
/*     */   
/*     */   public float aw() {
/* 174 */     return 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean bd() {
/* 184 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityEnderSignal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */