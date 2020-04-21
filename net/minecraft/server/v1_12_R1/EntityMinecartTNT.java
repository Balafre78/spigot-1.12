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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityMinecartTNT
/*     */   extends EntityMinecartAbstract
/*     */ {
/*  24 */   private int a = -1;
/*     */   
/*     */   public EntityMinecartTNT(World paramWorld) {
/*  27 */     super(paramWorld);
/*     */   }
/*     */   
/*     */   public EntityMinecartTNT(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3) {
/*  31 */     super(paramWorld, paramDouble1, paramDouble2, paramDouble3);
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager paramDataConverterManager) {
/*  35 */     EntityMinecartAbstract.a(paramDataConverterManager, EntityMinecartTNT.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityMinecartAbstract.EnumMinecartType v() {
/*  40 */     return EntityMinecartAbstract.EnumMinecartType.TNT;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData x() {
/*  45 */     return Blocks.TNT.getBlockData();
/*     */   }
/*     */ 
/*     */   
/*     */   public void B_() {
/*  50 */     super.B_();
/*     */     
/*  52 */     if (this.a > 0) {
/*  53 */       this.a--;
/*  54 */       this.world.addParticle(EnumParticle.SMOKE_NORMAL, this.locX, this.locY + 0.5D, this.locZ, 0.0D, 0.0D, 0.0D, new int[0]);
/*  55 */     } else if (this.a == 0) {
/*  56 */       c(this.motX * this.motX + this.motZ * this.motZ);
/*     */     } 
/*     */     
/*  59 */     if (this.positionChanged) {
/*  60 */       double d = this.motX * this.motX + this.motZ * this.motZ;
/*     */       
/*  62 */       if (d >= 0.009999999776482582D) {
/*  63 */         c(d);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource paramDamageSource, float paramFloat) {
/*  70 */     Entity entity = paramDamageSource.i();
/*  71 */     if (entity instanceof EntityArrow) {
/*  72 */       EntityArrow entityArrow = (EntityArrow)entity;
/*  73 */       if (entityArrow.isBurning()) {
/*  74 */         c(entityArrow.motX * entityArrow.motX + entityArrow.motY * entityArrow.motY + entityArrow.motZ * entityArrow.motZ);
/*     */       }
/*     */     } 
/*  77 */     return super.damageEntity(paramDamageSource, paramFloat);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(DamageSource paramDamageSource) {
/*  82 */     double d = this.motX * this.motX + this.motZ * this.motZ;
/*     */     
/*  84 */     if (paramDamageSource.o() || paramDamageSource.isExplosion() || d >= 0.009999999776482582D) {
/*  85 */       if (this.a < 0) {
/*  86 */         j();
/*  87 */         this.a = this.random.nextInt(20) + this.random.nextInt(20);
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/*  92 */     super.a(paramDamageSource);
/*     */     
/*  94 */     if (!paramDamageSource.isExplosion() && this.world.getGameRules().getBoolean("doEntityDrops")) {
/*  95 */       a(new ItemStack(Blocks.TNT, 1), 0.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void c(double paramDouble) {
/* 101 */     if (!this.world.isClientSide) {
/* 102 */       double d = Math.sqrt(paramDouble);
/* 103 */       if (d > 5.0D) {
/* 104 */         d = 5.0D;
/*     */       }
/* 106 */       this.world.explode(this, this.locX, this.locY, this.locZ, (float)(4.0D + this.random.nextDouble() * 1.5D * d), true);
/* 107 */       die();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void e(float paramFloat1, float paramFloat2) {
/* 113 */     if (paramFloat1 >= 3.0F) {
/* 114 */       float f = paramFloat1 / 10.0F;
/* 115 */       c((f * f));
/*     */     } 
/*     */     
/* 118 */     super.e(paramFloat1, paramFloat2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
/* 123 */     if (paramBoolean && this.a < 0) {
/* 124 */       j();
/*     */     }
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
/*     */   public void j() {
/* 138 */     this.a = 80;
/*     */     
/* 140 */     if (!this.world.isClientSide) {
/* 141 */       this.world.broadcastEntityEffect(this, (byte)10);
/* 142 */       if (!isSilent()) {
/* 143 */         this.world.a((EntityHuman)null, this.locX, this.locY, this.locZ, SoundEffects.hW, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean l() {
/* 153 */     return (this.a > -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public float a(Explosion paramExplosion, World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 158 */     if (l() && (BlockMinecartTrackAbstract.i(paramIBlockData) || BlockMinecartTrackAbstract.b(paramWorld, paramBlockPosition.up()))) {
/* 159 */       return 0.0F;
/*     */     }
/*     */     
/* 162 */     return super.a(paramExplosion, paramWorld, paramBlockPosition, paramIBlockData);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(Explosion paramExplosion, World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, float paramFloat) {
/* 167 */     if (l() && (BlockMinecartTrackAbstract.i(paramIBlockData) || BlockMinecartTrackAbstract.b(paramWorld, paramBlockPosition.up()))) {
/* 168 */       return false;
/*     */     }
/*     */     
/* 171 */     return super.a(paramExplosion, paramWorld, paramBlockPosition, paramIBlockData, paramFloat);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(NBTTagCompound paramNBTTagCompound) {
/* 176 */     super.a(paramNBTTagCompound);
/* 177 */     if (paramNBTTagCompound.hasKeyOfType("TNTFuse", 99)) {
/* 178 */       this.a = paramNBTTagCompound.getInt("TNTFuse");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(NBTTagCompound paramNBTTagCompound) {
/* 184 */     super.b(paramNBTTagCompound);
/* 185 */     paramNBTTagCompound.setInt("TNTFuse", this.a);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityMinecartTNT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */