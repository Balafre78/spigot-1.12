/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Calendar;
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
/*     */ public class EntityBat
/*     */   extends EntityAmbient
/*     */ {
/*  26 */   private static final DataWatcherObject<Byte> a = DataWatcher.a((Class)EntityBat.class, DataWatcherRegistry.a);
/*     */   
/*     */   private BlockPosition b;
/*     */ 
/*     */   
/*     */   public EntityBat(World paramWorld) {
/*  32 */     super(paramWorld);
/*     */     
/*  34 */     setSize(0.5F, 0.9F);
/*  35 */     setAsleep(true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void i() {
/*  40 */     super.i();
/*     */     
/*  42 */     this.datawatcher.register(a, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */   
/*     */   protected float cq() {
/*  47 */     return 0.1F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float cr() {
/*  52 */     return super.cr() * 0.95F;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public SoundEffect F() {
/*  58 */     if (isAsleep() && this.random.nextInt(4) != 0) {
/*  59 */       return null;
/*     */     }
/*  61 */     return SoundEffects.x;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect d(DamageSource paramDamageSource) {
/*  66 */     return SoundEffects.z;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect cf() {
/*  71 */     return SoundEffects.y;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCollidable() {
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void C(Entity paramEntity) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void cB() {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initAttributes() {
/*  92 */     super.initAttributes();
/*     */     
/*  94 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(6.0D);
/*     */   }
/*     */   
/*     */   public boolean isAsleep() {
/*  98 */     return ((((Byte)this.datawatcher.<Byte>get(a)).byteValue() & 0x1) != 0);
/*     */   }
/*     */   
/*     */   public void setAsleep(boolean paramBoolean) {
/* 102 */     byte b = ((Byte)this.datawatcher.<Byte>get(a)).byteValue();
/* 103 */     if (paramBoolean) {
/* 104 */       this.datawatcher.set(a, Byte.valueOf((byte)(b | 0x1)));
/*     */     } else {
/* 106 */       this.datawatcher.set(a, Byte.valueOf((byte)(b & 0xFFFFFFFE)));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void B_() {
/* 112 */     super.B_();
/*     */     
/* 114 */     if (isAsleep()) {
/* 115 */       this.motX = 0.0D;
/* 116 */       this.motY = 0.0D;
/* 117 */       this.motZ = 0.0D;
/* 118 */       this.locY = MathHelper.floor(this.locY) + 1.0D - this.length;
/*     */     } else {
/* 120 */       this.motY *= 0.6000000238418579D;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void M() {
/* 126 */     super.M();
/*     */     
/* 128 */     BlockPosition blockPosition1 = new BlockPosition(this);
/* 129 */     BlockPosition blockPosition2 = blockPosition1.up();
/*     */     
/* 131 */     if (isAsleep()) {
/* 132 */       if (this.world.getType(blockPosition2).l()) {
/* 133 */         if (this.random.nextInt(200) == 0) {
/* 134 */           this.aP = this.random.nextInt(360);
/*     */         }
/*     */         
/* 137 */         if (this.world.b(this, 4.0D) != null) {
/* 138 */           setAsleep(false);
/* 139 */           this.world.a((EntityHuman)null, 1025, blockPosition1, 0);
/*     */         } 
/*     */       } else {
/* 142 */         setAsleep(false);
/* 143 */         this.world.a((EntityHuman)null, 1025, blockPosition1, 0);
/*     */       } 
/*     */     } else {
/* 146 */       if (this.b != null && (!this.world.isEmpty(this.b) || this.b.getY() < 1)) {
/* 147 */         this.b = null;
/*     */       }
/* 149 */       if (this.b == null || this.random.nextInt(30) == 0 || this.b.distanceSquared((int)this.locX, (int)this.locY, (int)this.locZ) < 4.0D) {
/* 150 */         this.b = new BlockPosition((int)this.locX + this.random.nextInt(7) - this.random.nextInt(7), (int)this.locY + this.random.nextInt(6) - 2, (int)this.locZ + this.random.nextInt(7) - this.random.nextInt(7));
/*     */       }
/*     */       
/* 153 */       double d1 = this.b.getX() + 0.5D - this.locX;
/* 154 */       double d2 = this.b.getY() + 0.1D - this.locY;
/* 155 */       double d3 = this.b.getZ() + 0.5D - this.locZ;
/*     */       
/* 157 */       this.motX += (Math.signum(d1) * 0.5D - this.motX) * 0.10000000149011612D;
/* 158 */       this.motY += (Math.signum(d2) * 0.699999988079071D - this.motY) * 0.10000000149011612D;
/* 159 */       this.motZ += (Math.signum(d3) * 0.5D - this.motZ) * 0.10000000149011612D;
/*     */       
/* 161 */       float f1 = (float)(MathHelper.c(this.motZ, this.motX) * 57.2957763671875D) - 90.0F;
/* 162 */       float f2 = MathHelper.g(f1 - this.yaw);
/* 163 */       this.bg = 0.5F;
/* 164 */       this.yaw += f2;
/*     */       
/* 166 */       if (this.random.nextInt(100) == 0 && this.world.getType(blockPosition2).l()) {
/* 167 */         setAsleep(true);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean playStepSound() {
/* 174 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void e(float paramFloat1, float paramFloat2) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(double paramDouble, boolean paramBoolean, IBlockData paramIBlockData, BlockPosition paramBlockPosition) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isIgnoreBlockTrigger() {
/* 189 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource paramDamageSource, float paramFloat) {
/* 194 */     if (isInvulnerable(paramDamageSource)) {
/* 195 */       return false;
/*     */     }
/* 197 */     if (!this.world.isClientSide && 
/* 198 */       isAsleep()) {
/* 199 */       setAsleep(false);
/*     */     }
/*     */ 
/*     */     
/* 203 */     return super.damageEntity(paramDamageSource, paramFloat);
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager paramDataConverterManager) {
/* 207 */     EntityInsentient.a(paramDataConverterManager, EntityBat.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(NBTTagCompound paramNBTTagCompound) {
/* 212 */     super.a(paramNBTTagCompound);
/*     */     
/* 214 */     this.datawatcher.set(a, Byte.valueOf(paramNBTTagCompound.getByte("BatFlags")));
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(NBTTagCompound paramNBTTagCompound) {
/* 219 */     super.b(paramNBTTagCompound);
/*     */     
/* 221 */     paramNBTTagCompound.setByte("BatFlags", ((Byte)this.datawatcher.<Byte>get(a)).byteValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean P() {
/* 226 */     BlockPosition blockPosition = new BlockPosition(this.locX, (getBoundingBox()).b, this.locZ);
/* 227 */     if (blockPosition.getY() >= this.world.getSeaLevel()) {
/* 228 */       return false;
/*     */     }
/*     */     
/* 231 */     int i = this.world.getLightLevel(blockPosition);
/* 232 */     byte b = 4;
/*     */     
/* 234 */     if (a(this.world.ae())) {
/* 235 */       b = 7;
/* 236 */     } else if (this.random.nextBoolean()) {
/* 237 */       return false;
/*     */     } 
/*     */     
/* 240 */     if (i > this.random.nextInt(b)) {
/* 241 */       return false;
/*     */     }
/*     */     
/* 244 */     return super.P();
/*     */   }
/*     */   
/*     */   private boolean a(Calendar paramCalendar) {
/* 248 */     return ((paramCalendar.get(2) + 1 == 10 && paramCalendar.get(5) >= 20) || (paramCalendar.get(2) + 1 == 11 && paramCalendar.get(5) <= 3));
/*     */   }
/*     */ 
/*     */   
/*     */   public float getHeadHeight() {
/* 253 */     return this.length / 2.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/* 259 */     return LootTables.ag;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityBat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */