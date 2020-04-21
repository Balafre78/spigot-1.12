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
/*     */ 
/*     */ public class EntityMinecartFurnace
/*     */   extends EntityMinecartAbstract
/*     */ {
/*  25 */   private static final DataWatcherObject<Boolean> c = DataWatcher.a((Class)EntityMinecartFurnace.class, DataWatcherRegistry.h);
/*     */   
/*     */   private int d;
/*     */   
/*     */   public EntityMinecartFurnace(World paramWorld) {
/*  30 */     super(paramWorld);
/*     */   }
/*     */   public double a; public double b;
/*     */   public EntityMinecartFurnace(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3) {
/*  34 */     super(paramWorld, paramDouble1, paramDouble2, paramDouble3);
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager paramDataConverterManager) {
/*  38 */     EntityMinecartAbstract.a(paramDataConverterManager, EntityMinecartFurnace.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityMinecartAbstract.EnumMinecartType v() {
/*  43 */     return EntityMinecartAbstract.EnumMinecartType.FURNACE;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void i() {
/*  48 */     super.i();
/*  49 */     this.datawatcher.register(c, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public void B_() {
/*  54 */     super.B_();
/*     */     
/*  56 */     if (this.d > 0) {
/*  57 */       this.d--;
/*     */     }
/*  59 */     if (this.d <= 0) {
/*  60 */       this.a = 0.0D;
/*  61 */       this.b = 0.0D;
/*     */     } 
/*  63 */     l((this.d > 0));
/*     */     
/*  65 */     if (j() && this.random.nextInt(4) == 0) {
/*  66 */       this.world.addParticle(EnumParticle.SMOKE_LARGE, this.locX, this.locY + 0.8D, this.locZ, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected double p() {
/*  72 */     return 0.2D;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(DamageSource paramDamageSource) {
/*  77 */     super.a(paramDamageSource);
/*     */     
/*  79 */     if (!paramDamageSource.isExplosion() && this.world.getGameRules().getBoolean("doEntityDrops")) {
/*  80 */       a(new ItemStack(Blocks.FURNACE, 1), 0.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/*  86 */     super.a(paramBlockPosition, paramIBlockData);
/*     */     
/*  88 */     double d = this.a * this.a + this.b * this.b;
/*  89 */     if (d > 1.0E-4D && this.motX * this.motX + this.motZ * this.motZ > 0.001D) {
/*  90 */       d = MathHelper.sqrt(d);
/*  91 */       this.a /= d;
/*  92 */       this.b /= d;
/*     */       
/*  94 */       if (this.a * this.motX + this.b * this.motZ < 0.0D) {
/*  95 */         this.a = 0.0D;
/*  96 */         this.b = 0.0D;
/*     */       } else {
/*  98 */         double d1 = d / p();
/*  99 */         this.a *= d1;
/* 100 */         this.b *= d1;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void r() {
/* 107 */     double d = this.a * this.a + this.b * this.b;
/*     */     
/* 109 */     if (d > 1.0E-4D) {
/* 110 */       d = MathHelper.sqrt(d);
/* 111 */       this.a /= d;
/* 112 */       this.b /= d;
/* 113 */       double d1 = 1.0D;
/* 114 */       this.motX *= 0.800000011920929D;
/* 115 */       this.motY *= 0.0D;
/* 116 */       this.motZ *= 0.800000011920929D;
/* 117 */       this.motX += this.a * 1.0D;
/* 118 */       this.motZ += this.b * 1.0D;
/*     */     } else {
/* 120 */       this.motX *= 0.9800000190734863D;
/* 121 */       this.motY *= 0.0D;
/* 122 */       this.motZ *= 0.9800000190734863D;
/*     */     } 
/*     */     
/* 125 */     super.r();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(EntityHuman paramEntityHuman, EnumHand paramEnumHand) {
/* 130 */     ItemStack itemStack = paramEntityHuman.b(paramEnumHand);
/* 131 */     if (itemStack.getItem() == Items.COAL && this.d + 3600 <= 32000) {
/* 132 */       if (!paramEntityHuman.abilities.canInstantlyBuild) {
/* 133 */         itemStack.subtract(1);
/*     */       }
/* 135 */       this.d += 3600;
/*     */     } 
/*     */     
/* 138 */     this.a = this.locX - paramEntityHuman.locX;
/* 139 */     this.b = this.locZ - paramEntityHuman.locZ;
/*     */     
/* 141 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(NBTTagCompound paramNBTTagCompound) {
/* 146 */     super.b(paramNBTTagCompound);
/* 147 */     paramNBTTagCompound.setDouble("PushX", this.a);
/* 148 */     paramNBTTagCompound.setDouble("PushZ", this.b);
/* 149 */     paramNBTTagCompound.setShort("Fuel", (short)this.d);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(NBTTagCompound paramNBTTagCompound) {
/* 154 */     super.a(paramNBTTagCompound);
/* 155 */     this.a = paramNBTTagCompound.getDouble("PushX");
/* 156 */     this.b = paramNBTTagCompound.getDouble("PushZ");
/* 157 */     this.d = paramNBTTagCompound.getShort("Fuel");
/*     */   }
/*     */   
/*     */   protected boolean j() {
/* 161 */     return ((Boolean)this.datawatcher.<Boolean>get(c)).booleanValue();
/*     */   }
/*     */   
/*     */   protected void l(boolean paramBoolean) {
/* 165 */     this.datawatcher.set(c, Boolean.valueOf(paramBoolean));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData x() {
/* 170 */     return (j() ? Blocks.LIT_FURNACE : Blocks.FURNACE).getBlockData().set(BlockFurnace.FACING, EnumDirection.NORTH);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityMinecartFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */