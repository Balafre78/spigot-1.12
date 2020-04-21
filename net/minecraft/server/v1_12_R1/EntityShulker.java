/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Optional;
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityTeleportEvent;
/*     */ 
/*     */ public class EntityShulker extends EntityGolem implements IMonster {
/*  16 */   private static final UUID bz = UUID.fromString("7E0292F2-9434-48D5-A29F-9583AF7DF27F");
/*  17 */   private static final AttributeModifier bA = (new AttributeModifier(bz, "Covered armor bonus", 20.0D, 0)).a(false);
/*  18 */   protected static final DataWatcherObject<EnumDirection> a = DataWatcher.a((Class)EntityShulker.class, DataWatcherRegistry.l);
/*  19 */   protected static final DataWatcherObject<Optional<BlockPosition>> b = DataWatcher.a((Class)EntityShulker.class, DataWatcherRegistry.k);
/*  20 */   protected static final DataWatcherObject<Byte> c = DataWatcher.a((Class)EntityShulker.class, DataWatcherRegistry.a);
/*  21 */   public static final DataWatcherObject<Byte> COLOR = DataWatcher.a((Class)EntityShulker.class, DataWatcherRegistry.a);
/*  22 */   public static final EnumColor by = EnumColor.PURPLE;
/*     */   private float bB;
/*     */   private float bC;
/*     */   private BlockPosition bD;
/*     */   private int bE;
/*     */   
/*     */   public EntityShulker(World world) {
/*  29 */     super(world);
/*  30 */     setSize(1.0F, 1.0F);
/*  31 */     this.aO = 180.0F;
/*  32 */     this.aN = 180.0F;
/*  33 */     this.fireProof = true;
/*  34 */     this.bD = null;
/*  35 */     this.b_ = 5;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, @Nullable GroupDataEntity groupdataentity) {
/*  40 */     this.aN = 180.0F;
/*  41 */     this.aO = 180.0F;
/*  42 */     this.yaw = 180.0F;
/*  43 */     this.lastYaw = 180.0F;
/*  44 */     this.aP = 180.0F;
/*  45 */     this.aQ = 180.0F;
/*  46 */     return super.prepare(difficultydamagescaler, groupdataentity);
/*     */   }
/*     */   
/*     */   protected void r() {
/*  50 */     this.goalSelector.a(1, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
/*  51 */     this.goalSelector.a(4, new a());
/*  52 */     this.goalSelector.a(7, new e(null));
/*  53 */     this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
/*  54 */     this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true, new Class[0]));
/*  55 */     this.targetSelector.a(2, new d(this));
/*  56 */     this.targetSelector.a(3, new c(this));
/*     */   }
/*     */   
/*     */   protected boolean playStepSound() {
/*  60 */     return false;
/*     */   }
/*     */   
/*     */   public SoundCategory bK() {
/*  64 */     return SoundCategory.HOSTILE;
/*     */   }
/*     */   
/*     */   protected SoundEffect F() {
/*  68 */     return SoundEffects.gA;
/*     */   }
/*     */   
/*     */   public void D() {
/*  72 */     if (!ds()) {
/*  73 */       super.D();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect cf() {
/*  79 */     return SoundEffects.gG;
/*     */   }
/*     */   
/*     */   protected SoundEffect d(DamageSource damagesource) {
/*  83 */     return ds() ? SoundEffects.gI : SoundEffects.gH;
/*     */   }
/*     */   
/*     */   protected void i() {
/*  87 */     super.i();
/*  88 */     this.datawatcher.register(a, EnumDirection.DOWN);
/*  89 */     this.datawatcher.register(b, Optional.absent());
/*  90 */     this.datawatcher.register(c, Byte.valueOf((byte)0));
/*  91 */     this.datawatcher.register(COLOR, Byte.valueOf((byte)by.getColorIndex()));
/*     */   }
/*     */   
/*     */   protected void initAttributes() {
/*  95 */     super.initAttributes();
/*  96 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(30.0D);
/*     */   }
/*     */   
/*     */   protected EntityAIBodyControl s() {
/* 100 */     return new b(this);
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {
/* 104 */     EntityInsentient.a(dataconvertermanager, EntityShulker.class);
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/* 108 */     super.a(nbttagcompound);
/* 109 */     this.datawatcher.set(a, EnumDirection.fromType1(nbttagcompound.getByte("AttachFace")));
/* 110 */     this.datawatcher.set(c, Byte.valueOf(nbttagcompound.getByte("Peek")));
/* 111 */     this.datawatcher.set(COLOR, Byte.valueOf(nbttagcompound.getByte("Color")));
/* 112 */     if (nbttagcompound.hasKey("APX")) {
/* 113 */       int i = nbttagcompound.getInt("APX");
/* 114 */       int j = nbttagcompound.getInt("APY");
/* 115 */       int k = nbttagcompound.getInt("APZ");
/*     */       
/* 117 */       this.datawatcher.set(b, Optional.of(new BlockPosition(i, j, k)));
/*     */     } else {
/* 119 */       this.datawatcher.set(b, Optional.absent());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/* 125 */     super.b(nbttagcompound);
/* 126 */     nbttagcompound.setByte("AttachFace", (byte)((EnumDirection)this.datawatcher.<EnumDirection>get(a)).a());
/* 127 */     nbttagcompound.setByte("Peek", ((Byte)this.datawatcher.<Byte>get(c)).byteValue());
/* 128 */     nbttagcompound.setByte("Color", ((Byte)this.datawatcher.<Byte>get(COLOR)).byteValue());
/* 129 */     BlockPosition blockposition = dm();
/*     */     
/* 131 */     if (blockposition != null) {
/* 132 */       nbttagcompound.setInt("APX", blockposition.getX());
/* 133 */       nbttagcompound.setInt("APY", blockposition.getY());
/* 134 */       nbttagcompound.setInt("APZ", blockposition.getZ());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void B_() {
/* 140 */     super.B_();
/* 141 */     BlockPosition blockposition = (BlockPosition)((Optional)this.datawatcher.<Optional>get((DataWatcherObject)b)).orNull();
/*     */     
/* 143 */     if (blockposition == null && !this.world.isClientSide) {
/* 144 */       blockposition = new BlockPosition(this);
/* 145 */       this.datawatcher.set(b, Optional.of(blockposition));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 150 */     if (isPassenger()) {
/* 151 */       blockposition = null;
/* 152 */       float f1 = (bJ()).yaw;
/* 153 */       this.yaw = f1;
/* 154 */       this.aN = f1;
/* 155 */       this.aO = f1;
/* 156 */       this.bE = 0;
/* 157 */     } else if (!this.world.isClientSide) {
/* 158 */       IBlockData iblockdata = this.world.getType(blockposition);
/*     */       
/* 160 */       if (iblockdata.getMaterial() != Material.AIR)
/*     */       {
/*     */         
/* 163 */         if (iblockdata.getBlock() == Blocks.PISTON_EXTENSION) {
/* 164 */           EnumDirection enumdirection = iblockdata.<EnumDirection>get(BlockPiston.FACING);
/* 165 */           if (this.world.isEmpty(blockposition.shift(enumdirection))) {
/* 166 */             blockposition = blockposition.shift(enumdirection);
/* 167 */             this.datawatcher.set(b, Optional.of(blockposition));
/*     */           } else {
/* 169 */             p();
/*     */           } 
/* 171 */         } else if (iblockdata.getBlock() == Blocks.PISTON_HEAD) {
/* 172 */           EnumDirection enumdirection = iblockdata.<EnumDirection>get(BlockPistonExtension.FACING);
/* 173 */           if (this.world.isEmpty(blockposition.shift(enumdirection))) {
/* 174 */             blockposition = blockposition.shift(enumdirection);
/* 175 */             this.datawatcher.set(b, Optional.of(blockposition));
/*     */           } else {
/* 177 */             p();
/*     */           } 
/*     */         } else {
/* 180 */           p();
/*     */         } 
/*     */       }
/*     */       
/* 184 */       BlockPosition blockposition1 = blockposition.shift(dl());
/*     */       
/* 186 */       if (!this.world.d(blockposition1, false)) {
/* 187 */         boolean flag = false;
/* 188 */         EnumDirection[] aenumdirection = EnumDirection.values();
/* 189 */         int i = aenumdirection.length;
/*     */         
/* 191 */         for (int j = 0; j < i; j++) {
/* 192 */           EnumDirection enumdirection1 = aenumdirection[j];
/*     */           
/* 194 */           blockposition1 = blockposition.shift(enumdirection1);
/* 195 */           if (this.world.d(blockposition1, false)) {
/* 196 */             this.datawatcher.set(a, enumdirection1);
/* 197 */             flag = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 202 */         if (!flag) {
/* 203 */           p();
/*     */         }
/*     */       } 
/*     */       
/* 207 */       BlockPosition blockposition2 = blockposition.shift(dl().opposite());
/*     */       
/* 209 */       if (this.world.d(blockposition2, false)) {
/* 210 */         p();
/*     */       }
/*     */     } 
/*     */     
/* 214 */     float f = dn() * 0.01F;
/* 215 */     this.bB = this.bC;
/* 216 */     if (this.bC > f) {
/* 217 */       this.bC = MathHelper.a(this.bC - 0.05F, f, 1.0F);
/* 218 */     } else if (this.bC < f) {
/* 219 */       this.bC = MathHelper.a(this.bC + 0.05F, 0.0F, f);
/*     */     } 
/*     */     
/* 222 */     if (blockposition != null) {
/* 223 */       if (this.world.isClientSide) {
/* 224 */         if (this.bE > 0 && this.bD != null) {
/* 225 */           this.bE--;
/*     */         } else {
/* 227 */           this.bD = blockposition;
/*     */         } 
/*     */       }
/*     */       
/* 231 */       this.locX = blockposition.getX() + 0.5D;
/* 232 */       this.locY = blockposition.getY();
/* 233 */       this.locZ = blockposition.getZ() + 0.5D;
/* 234 */       this.lastX = this.locX;
/* 235 */       this.lastY = this.locY;
/* 236 */       this.lastZ = this.locZ;
/* 237 */       this.M = this.locX;
/* 238 */       this.N = this.locY;
/* 239 */       this.O = this.locZ;
/* 240 */       double d0 = 0.5D - MathHelper.sin((0.5F + this.bC) * 3.1415927F) * 0.5D;
/* 241 */       double d1 = 0.5D - MathHelper.sin((0.5F + this.bB) * 3.1415927F) * 0.5D;
/* 242 */       double d2 = d0 - d1;
/* 243 */       double d3 = 0.0D;
/* 244 */       double d4 = 0.0D;
/* 245 */       double d5 = 0.0D;
/* 246 */       EnumDirection enumdirection2 = dl();
/*     */       
/* 248 */       switch (enumdirection2) {
/*     */         case null:
/* 250 */           a(new AxisAlignedBB(this.locX - 0.5D, this.locY, this.locZ - 0.5D, this.locX + 0.5D, this.locY + 1.0D + d0, this.locZ + 0.5D));
/* 251 */           d4 = d2;
/*     */           break;
/*     */         
/*     */         case UP:
/* 255 */           a(new AxisAlignedBB(this.locX - 0.5D, this.locY - d0, this.locZ - 0.5D, this.locX + 0.5D, this.locY + 1.0D, this.locZ + 0.5D));
/* 256 */           d4 = -d2;
/*     */           break;
/*     */         
/*     */         case NORTH:
/* 260 */           a(new AxisAlignedBB(this.locX - 0.5D, this.locY, this.locZ - 0.5D, this.locX + 0.5D, this.locY + 1.0D, this.locZ + 0.5D + d0));
/* 261 */           d5 = d2;
/*     */           break;
/*     */         
/*     */         case SOUTH:
/* 265 */           a(new AxisAlignedBB(this.locX - 0.5D, this.locY, this.locZ - 0.5D - d0, this.locX + 0.5D, this.locY + 1.0D, this.locZ + 0.5D));
/* 266 */           d5 = -d2;
/*     */           break;
/*     */         
/*     */         case WEST:
/* 270 */           a(new AxisAlignedBB(this.locX - 0.5D, this.locY, this.locZ - 0.5D, this.locX + 0.5D + d0, this.locY + 1.0D, this.locZ + 0.5D));
/* 271 */           d3 = d2;
/*     */           break;
/*     */         
/*     */         case EAST:
/* 275 */           a(new AxisAlignedBB(this.locX - 0.5D - d0, this.locY, this.locZ - 0.5D, this.locX + 0.5D, this.locY + 1.0D, this.locZ + 0.5D));
/* 276 */           d3 = -d2;
/*     */           break;
/*     */       } 
/* 279 */       if (d2 > 0.0D) {
/* 280 */         List<Entity> list = this.world.getEntities(this, getBoundingBox());
/*     */         
/* 282 */         if (!list.isEmpty()) {
/* 283 */           Iterator<Entity> iterator = list.iterator();
/*     */           
/* 285 */           while (iterator.hasNext()) {
/* 286 */             Entity entity = iterator.next();
/*     */             
/* 288 */             if (!(entity instanceof EntityShulker) && !entity.noclip) {
/* 289 */               entity.move(EnumMoveType.SHULKER, d3, d4, d5);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void move(EnumMoveType enummovetype, double d0, double d1, double d2) {
/* 299 */     if (enummovetype == EnumMoveType.SHULKER_BOX) {
/* 300 */       p();
/*     */     } else {
/* 302 */       super.move(enummovetype, d0, d1, d2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPosition(double d0, double d1, double d2) {
/* 308 */     super.setPosition(d0, d1, d2);
/* 309 */     if (this.datawatcher != null && this.ticksLived != 0) {
/* 310 */       Optional optional = this.datawatcher.<Optional>get((DataWatcherObject)b);
/* 311 */       Optional<BlockPosition> optional1 = Optional.of(new BlockPosition(d0, d1, d2));
/*     */       
/* 313 */       if (!optional1.equals(optional)) {
/* 314 */         this.datawatcher.set(b, optional1);
/* 315 */         this.datawatcher.set(c, Byte.valueOf((byte)0));
/* 316 */         this.impulse = true;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean p() {
/* 323 */     if (!isNoAI() && isAlive()) {
/* 324 */       BlockPosition blockposition = new BlockPosition(this);
/*     */       
/* 326 */       for (int i = 0; i < 5; i++) {
/* 327 */         BlockPosition blockposition1 = blockposition.a(8 - this.random.nextInt(17), 8 - this.random.nextInt(17), 8 - this.random.nextInt(17));
/*     */         
/* 329 */         if (blockposition1.getY() > 0 && this.world.isEmpty(blockposition1) && this.world.g(this) && this.world.getCubes(this, new AxisAlignedBB(blockposition1)).isEmpty()) {
/* 330 */           boolean flag = false;
/* 331 */           EnumDirection[] aenumdirection = EnumDirection.values();
/* 332 */           int j = aenumdirection.length;
/*     */           
/* 334 */           for (int k = 0; k < j; k++) {
/* 335 */             EnumDirection enumdirection = aenumdirection[k];
/*     */             
/* 337 */             if (this.world.d(blockposition1.shift(enumdirection), false)) {
/*     */               
/* 339 */               EntityTeleportEvent teleport = new EntityTeleportEvent((Entity)getBukkitEntity(), getBukkitEntity().getLocation(), new Location((World)this.world.getWorld(), blockposition1.getX(), blockposition1.getY(), blockposition1.getZ()));
/* 340 */               this.world.getServer().getPluginManager().callEvent((Event)teleport);
/* 341 */               if (!teleport.isCancelled()) {
/* 342 */                 Location to = teleport.getTo();
/* 343 */                 blockposition1 = new BlockPosition(to.getX(), to.getY(), to.getZ());
/*     */                 
/* 345 */                 this.datawatcher.set(a, enumdirection);
/* 346 */                 flag = true;
/*     */               } 
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */           
/* 353 */           if (flag) {
/* 354 */             a(SoundEffects.gL, 1.0F, 1.0F);
/* 355 */             this.datawatcher.set(b, Optional.of(blockposition1));
/* 356 */             this.datawatcher.set(c, Byte.valueOf((byte)0));
/* 357 */             setGoalTarget((EntityLiving)null);
/* 358 */             return true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 363 */       return false;
/*     */     } 
/* 365 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void n() {
/* 370 */     super.n();
/* 371 */     this.motX = 0.0D;
/* 372 */     this.motY = 0.0D;
/* 373 */     this.motZ = 0.0D;
/* 374 */     this.aO = 180.0F;
/* 375 */     this.aN = 180.0F;
/* 376 */     this.yaw = 180.0F;
/*     */   }
/*     */   
/*     */   public void a(DataWatcherObject<?> datawatcherobject) {
/* 380 */     if (b.equals(datawatcherobject) && this.world.isClientSide && !isPassenger()) {
/* 381 */       BlockPosition blockposition = dm();
/*     */       
/* 383 */       if (blockposition != null) {
/* 384 */         if (this.bD == null) {
/* 385 */           this.bD = blockposition;
/*     */         } else {
/* 387 */           this.bE = 6;
/*     */         } 
/*     */         
/* 390 */         this.locX = blockposition.getX() + 0.5D;
/* 391 */         this.locY = blockposition.getY();
/* 392 */         this.locZ = blockposition.getZ() + 0.5D;
/* 393 */         this.lastX = this.locX;
/* 394 */         this.lastY = this.locY;
/* 395 */         this.lastZ = this.locZ;
/* 396 */         this.M = this.locX;
/* 397 */         this.N = this.locY;
/* 398 */         this.O = this.locZ;
/*     */       } 
/*     */     } 
/*     */     
/* 402 */     super.a(datawatcherobject);
/*     */   }
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 406 */     if (ds()) {
/* 407 */       Entity entity = damagesource.i();
/*     */       
/* 409 */       if (entity instanceof EntityArrow) {
/* 410 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 414 */     if (super.damageEntity(damagesource, f)) {
/* 415 */       if (getHealth() < getMaxHealth() * 0.5D && this.random.nextInt(4) == 0) {
/* 416 */         p();
/*     */       }
/*     */       
/* 419 */       return true;
/*     */     } 
/* 421 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean ds() {
/* 426 */     return (dn() == 0);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public AxisAlignedBB al() {
/* 431 */     return isAlive() ? getBoundingBox() : null;
/*     */   }
/*     */   
/*     */   public EnumDirection dl() {
/* 435 */     return this.datawatcher.<EnumDirection>get(a);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public BlockPosition dm() {
/* 440 */     return (BlockPosition)((Optional)this.datawatcher.<Optional>get((DataWatcherObject)b)).orNull();
/*     */   }
/*     */   
/*     */   public void g(@Nullable BlockPosition blockposition) {
/* 444 */     this.datawatcher.set(b, Optional.fromNullable(blockposition));
/*     */   }
/*     */   
/*     */   public int dn() {
/* 448 */     return ((Byte)this.datawatcher.<Byte>get(c)).byteValue();
/*     */   }
/*     */   
/*     */   public void a(int i) {
/* 452 */     if (!this.world.isClientSide) {
/* 453 */       getAttributeInstance(GenericAttributes.h).c(bA);
/* 454 */       if (i == 0) {
/* 455 */         getAttributeInstance(GenericAttributes.h).b(bA);
/* 456 */         a(SoundEffects.gF, 1.0F, 1.0F);
/*     */       } else {
/* 458 */         a(SoundEffects.gJ, 1.0F, 1.0F);
/*     */       } 
/*     */     } 
/*     */     
/* 462 */     this.datawatcher.set(c, Byte.valueOf((byte)i));
/*     */   }
/*     */   
/*     */   public float getHeadHeight() {
/* 466 */     return 0.5F;
/*     */   }
/*     */   
/*     */   public int N() {
/* 470 */     return 180;
/*     */   }
/*     */   
/*     */   public int O() {
/* 474 */     return 180;
/*     */   }
/*     */   
/*     */   public void collide(Entity entity) {}
/*     */   
/*     */   public float aI() {
/* 480 */     return 0.0F;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/* 485 */     return LootTables.z;
/*     */   }
/*     */   
/*     */   static class c
/*     */     extends PathfinderGoalNearestAttackableTarget<EntityLiving> {
/*     */     public c(EntityShulker entityshulker) {
/* 491 */       super(entityshulker, EntityLiving.class, 10, true, false, new Predicate() {
/*     */             public boolean a(@Nullable EntityLiving entityliving) {
/* 493 */               return entityliving instanceof IMonster;
/*     */             }
/*     */             
/*     */             public boolean apply(@Nullable Object object) {
/* 497 */               return a((EntityLiving)object);
/*     */             }
/*     */           });
/*     */     }
/*     */     
/*     */     public boolean a() {
/* 503 */       return (this.e.aY() == null) ? false : super.a();
/*     */     }
/*     */     
/*     */     protected AxisAlignedBB a(double d0) {
/* 507 */       EnumDirection enumdirection = ((EntityShulker)this.e).dl();
/*     */       
/* 509 */       return (enumdirection.k() == EnumDirection.EnumAxis.X) ? this.e.getBoundingBox().grow(4.0D, d0, d0) : ((enumdirection.k() == EnumDirection.EnumAxis.Z) ? this.e.getBoundingBox().grow(d0, d0, 4.0D) : this.e.getBoundingBox().grow(d0, 4.0D, d0));
/*     */     }
/*     */   }
/*     */   
/*     */   class d
/*     */     extends PathfinderGoalNearestAttackableTarget<EntityHuman> {
/*     */     public d(EntityShulker entityshulker) {
/* 516 */       super(entityshulker, EntityHuman.class, true);
/*     */     }
/*     */     
/*     */     public boolean a() {
/* 520 */       return (EntityShulker.this.world.getDifficulty() == EnumDifficulty.PEACEFUL) ? false : super.a();
/*     */     }
/*     */     
/*     */     protected AxisAlignedBB a(double d0) {
/* 524 */       EnumDirection enumdirection = ((EntityShulker)this.e).dl();
/*     */       
/* 526 */       return (enumdirection.k() == EnumDirection.EnumAxis.X) ? this.e.getBoundingBox().grow(4.0D, d0, d0) : ((enumdirection.k() == EnumDirection.EnumAxis.Z) ? this.e.getBoundingBox().grow(d0, d0, 4.0D) : this.e.getBoundingBox().grow(d0, 4.0D, d0));
/*     */     }
/*     */   }
/*     */   
/*     */   class a
/*     */     extends PathfinderGoal {
/*     */     private int b;
/*     */     
/*     */     public a() {
/* 535 */       a(3);
/*     */     }
/*     */     
/*     */     public boolean a() {
/* 539 */       EntityLiving entityliving = EntityShulker.this.getGoalTarget();
/*     */       
/* 541 */       return (entityliving != null && entityliving.isAlive()) ? ((EntityShulker.this.world.getDifficulty() != EnumDifficulty.PEACEFUL)) : false;
/*     */     }
/*     */     
/*     */     public void c() {
/* 545 */       this.b = 20;
/* 546 */       EntityShulker.this.a(100);
/*     */     }
/*     */     
/*     */     public void d() {
/* 550 */       EntityShulker.this.a(0);
/*     */     }
/*     */     
/*     */     public void e() {
/* 554 */       if (EntityShulker.this.world.getDifficulty() != EnumDifficulty.PEACEFUL) {
/* 555 */         this.b--;
/* 556 */         EntityLiving entityliving = EntityShulker.this.getGoalTarget();
/*     */         
/* 558 */         EntityShulker.this.getControllerLook().a(entityliving, 180.0F, 180.0F);
/* 559 */         double d0 = EntityShulker.this.h(entityliving);
/*     */         
/* 561 */         if (d0 < 400.0D) {
/* 562 */           if (this.b <= 0) {
/* 563 */             this.b = 20 + EntityShulker.this.random.nextInt(10) * 20 / 2;
/* 564 */             EntityShulkerBullet entityshulkerbullet = new EntityShulkerBullet(EntityShulker.this.world, EntityShulker.this, entityliving, EntityShulker.this.dl().k());
/*     */             
/* 566 */             EntityShulker.this.world.addEntity(entityshulkerbullet);
/* 567 */             EntityShulker.this.a(SoundEffects.gK, 2.0F, (EntityShulker.this.random.nextFloat() - EntityShulker.this.random.nextFloat()) * 0.2F + 1.0F);
/*     */           } 
/*     */         } else {
/* 570 */           EntityShulker.this.setGoalTarget((EntityLiving)null);
/*     */         } 
/*     */         
/* 573 */         super.e();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   class e
/*     */     extends PathfinderGoal {
/*     */     private int b;
/*     */     
/*     */     private e() {}
/*     */     
/*     */     public boolean a() {
/* 585 */       return (EntityShulker.this.getGoalTarget() == null && EntityShulker.this.random.nextInt(40) == 0);
/*     */     }
/*     */     
/*     */     public boolean b() {
/* 589 */       return (EntityShulker.this.getGoalTarget() == null && this.b > 0);
/*     */     }
/*     */     
/*     */     public void c() {
/* 593 */       this.b = 20 * (1 + EntityShulker.this.random.nextInt(3));
/* 594 */       EntityShulker.this.a(30);
/*     */     }
/*     */     
/*     */     public void d() {
/* 598 */       if (EntityShulker.this.getGoalTarget() == null) {
/* 599 */         EntityShulker.this.a(0);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 605 */       this.b--;
/*     */     }
/*     */     
/*     */     e(Object object) {
/* 609 */       this();
/*     */     }
/*     */   }
/*     */   
/*     */   class b
/*     */     extends EntityAIBodyControl {
/*     */     public b(EntityLiving entityliving) {
/* 616 */       super(entityliving);
/*     */     }
/*     */     
/*     */     public void a() {}
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityShulker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */