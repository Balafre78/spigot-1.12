/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.base.Predicates;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityRegainHealthEvent;
/*     */ import org.bukkit.event.entity.ExplosionPrimeEvent;
/*     */ 
/*     */ public class EntityWither
/*     */   extends EntityMonster
/*     */   implements IRangedEntity {
/*  16 */   private static final DataWatcherObject<Integer> a = DataWatcher.a((Class)EntityWither.class, DataWatcherRegistry.b);
/*  17 */   private static final DataWatcherObject<Integer> b = DataWatcher.a((Class)EntityWither.class, DataWatcherRegistry.b);
/*  18 */   private static final DataWatcherObject<Integer> c = DataWatcher.a((Class)EntityWither.class, DataWatcherRegistry.b);
/*  19 */   private static final DataWatcherObject<Integer>[] bx = new DataWatcherObject[] { a, b, c };
/*  20 */   private static final DataWatcherObject<Integer> by = DataWatcher.a((Class)EntityWither.class, DataWatcherRegistry.b);
/*  21 */   private final float[] bz = new float[2];
/*  22 */   private final float[] bA = new float[2];
/*  23 */   private final float[] bB = new float[2];
/*  24 */   private final float[] bC = new float[2];
/*  25 */   private final int[] bD = new int[2];
/*  26 */   private final int[] bE = new int[2];
/*     */   private int bF;
/*     */   
/*  29 */   private static final Predicate<Entity> bH = new Predicate() {
/*     */       public boolean a(@Nullable Entity entity) {
/*  31 */         return (entity instanceof EntityLiving && ((EntityLiving)entity).getMonsterType() != EnumMonsterType.UNDEAD && ((EntityLiving)entity).cS());
/*     */       }
/*     */       
/*     */       public boolean apply(@Nullable Object object) {
/*  35 */         return a((Entity)object);
/*     */       }
/*     */     };
/*     */   private final BossBattleServer bG;
/*     */   public EntityWither(World world) {
/*  40 */     super(world);
/*  41 */     this.bG = (BossBattleServer)(new BossBattleServer(getScoreboardDisplayName(), BossBattle.BarColor.PURPLE, BossBattle.BarStyle.PROGRESS)).setDarkenSky(true);
/*  42 */     setHealth(getMaxHealth());
/*  43 */     setSize(0.9F, 3.5F);
/*  44 */     this.fireProof = true;
/*  45 */     ((Navigation)getNavigation()).c(true);
/*  46 */     this.b_ = 50;
/*     */   }
/*     */   
/*     */   protected void r() {
/*  50 */     this.goalSelector.a(0, new a());
/*  51 */     this.goalSelector.a(1, new PathfinderGoalFloat(this));
/*  52 */     this.goalSelector.a(2, new PathfinderGoalArrowAttack(this, 1.0D, 40, 20.0F));
/*  53 */     this.goalSelector.a(5, new PathfinderGoalRandomStrollLand(this, 1.0D));
/*  54 */     this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
/*  55 */     this.goalSelector.a(7, new PathfinderGoalRandomLookaround(this));
/*  56 */     this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, false, new Class[0]));
/*  57 */     this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<>(this, (Class)EntityInsentient.class, 0, false, false, bH));
/*     */   }
/*     */   
/*     */   protected void i() {
/*  61 */     super.i();
/*  62 */     this.datawatcher.register(a, Integer.valueOf(0));
/*  63 */     this.datawatcher.register(b, Integer.valueOf(0));
/*  64 */     this.datawatcher.register(c, Integer.valueOf(0));
/*  65 */     this.datawatcher.register(by, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {
/*  69 */     EntityInsentient.a(dataconvertermanager, EntityWither.class);
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/*  73 */     super.b(nbttagcompound);
/*  74 */     nbttagcompound.setInt("Invul", dm());
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/*  78 */     super.a(nbttagcompound);
/*  79 */     g(nbttagcompound.getInt("Invul"));
/*  80 */     if (hasCustomName()) {
/*  81 */       this.bG.a(getScoreboardDisplayName());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustomName(String s) {
/*  87 */     super.setCustomName(s);
/*  88 */     this.bG.a(getScoreboardDisplayName());
/*     */   }
/*     */   
/*     */   protected SoundEffect F() {
/*  92 */     return SoundEffects.iC;
/*     */   }
/*     */   
/*     */   protected SoundEffect d(DamageSource damagesource) {
/*  96 */     return SoundEffects.iF;
/*     */   }
/*     */   
/*     */   protected SoundEffect cf() {
/* 100 */     return SoundEffects.iE;
/*     */   }
/*     */   
/*     */   public void n() {
/* 104 */     this.motY *= 0.6000000238418579D;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 109 */     if (!this.world.isClientSide && m(0) > 0) {
/* 110 */       Entity entity = this.world.getEntity(m(0));
/*     */       
/* 112 */       if (entity != null) {
/* 113 */         if (this.locY < entity.locY || (!dn() && this.locY < entity.locY + 5.0D)) {
/* 114 */           if (this.motY < 0.0D) {
/* 115 */             this.motY = 0.0D;
/*     */           }
/*     */           
/* 118 */           this.motY += (0.5D - this.motY) * 0.6000000238418579D;
/*     */         } 
/*     */         
/* 121 */         double d3 = entity.locX - this.locX;
/*     */         
/* 123 */         double d0 = entity.locZ - this.locZ;
/* 124 */         double d1 = d3 * d3 + d0 * d0;
/* 125 */         if (d1 > 9.0D) {
/* 126 */           double d2 = MathHelper.sqrt(d1);
/* 127 */           this.motX += (d3 / d2 * 0.5D - this.motX) * 0.6000000238418579D;
/* 128 */           this.motZ += (d0 / d2 * 0.5D - this.motZ) * 0.6000000238418579D;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 133 */     if (this.motX * this.motX + this.motZ * this.motZ > 0.05000000074505806D) {
/* 134 */       this.yaw = (float)MathHelper.c(this.motZ, this.motX) * 57.295776F - 90.0F;
/*     */     }
/*     */     
/* 137 */     super.n();
/*     */     
/*     */     int i;
/*     */     
/* 141 */     for (i = 0; i < 2; i++) {
/* 142 */       this.bC[i] = this.bA[i];
/* 143 */       this.bB[i] = this.bz[i];
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 148 */     for (i = 0; i < 2; i++) {
/* 149 */       int k = m(i + 1);
/* 150 */       Entity entity1 = null;
/*     */       
/* 152 */       if (k > 0) {
/* 153 */         entity1 = this.world.getEntity(k);
/*     */       }
/*     */       
/* 156 */       if (entity1 != null) {
/* 157 */         double d0 = n(i + 1);
/* 158 */         double d1 = o(i + 1);
/* 159 */         double d2 = p(i + 1);
/* 160 */         double d4 = entity1.locX - d0;
/* 161 */         double d5 = entity1.locY + entity1.getHeadHeight() - d1;
/* 162 */         double d6 = entity1.locZ - d2;
/* 163 */         double d7 = MathHelper.sqrt(d4 * d4 + d6 * d6);
/* 164 */         float f = (float)(MathHelper.c(d6, d4) * 57.2957763671875D) - 90.0F;
/* 165 */         float f1 = (float)-(MathHelper.c(d5, d7) * 57.2957763671875D);
/*     */         
/* 167 */         this.bz[i] = b(this.bz[i], f1, 40.0F);
/* 168 */         this.bA[i] = b(this.bA[i], f, 10.0F);
/*     */       } else {
/* 170 */         this.bA[i] = b(this.bA[i], this.aN, 10.0F);
/*     */       } 
/*     */     } 
/*     */     
/* 174 */     boolean flag = dn();
/*     */     int j;
/* 176 */     for (j = 0; j < 3; j++) {
/* 177 */       double d8 = n(j);
/* 178 */       double d9 = o(j);
/* 179 */       double d10 = p(j);
/*     */       
/* 181 */       this.world.addParticle(EnumParticle.SMOKE_NORMAL, d8 + this.random.nextGaussian() * 0.30000001192092896D, d9 + this.random.nextGaussian() * 0.30000001192092896D, d10 + this.random.nextGaussian() * 0.30000001192092896D, 0.0D, 0.0D, 0.0D, new int[0]);
/* 182 */       if (flag && this.world.random.nextInt(4) == 0) {
/* 183 */         this.world.addParticle(EnumParticle.SPELL_MOB, d8 + this.random.nextGaussian() * 0.30000001192092896D, d9 + this.random.nextGaussian() * 0.30000001192092896D, d10 + this.random.nextGaussian() * 0.30000001192092896D, 0.699999988079071D, 0.699999988079071D, 0.5D, new int[0]);
/*     */       }
/*     */     } 
/*     */     
/* 187 */     if (dm() > 0) {
/* 188 */       for (j = 0; j < 3; j++) {
/* 189 */         this.world.addParticle(EnumParticle.SPELL_MOB, this.locX + this.random.nextGaussian(), this.locY + (this.random.nextFloat() * 3.3F), this.locZ + this.random.nextGaussian(), 0.699999988079071D, 0.699999988079071D, 0.8999999761581421D, new int[0]);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void M() {
/* 198 */     if (dm() > 0) {
/* 199 */       int i = dm() - 1;
/* 200 */       if (i <= 0) {
/*     */ 
/*     */         
/* 203 */         ExplosionPrimeEvent event = new ExplosionPrimeEvent((Entity)getBukkitEntity(), 7.0F, false);
/* 204 */         this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */         
/* 206 */         if (!event.isCancelled()) {
/* 207 */           this.world.createExplosion(this, this.locX, this.locY + getHeadHeight(), this.locZ, event.getRadius(), event.getFire(), this.world.getGameRules().getBoolean("mobGriefing"));
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 213 */         int viewDistance = ((WorldServer)this.world).getServer().getViewDistance() * 16;
/* 214 */         for (EntityPlayer player : (MinecraftServer.getServer().getPlayerList()).players) {
/* 215 */           double deltaX = this.locX - player.locX;
/* 216 */           double deltaZ = this.locZ - player.locZ;
/* 217 */           double distanceSquared = deltaX * deltaX + deltaZ * deltaZ;
/* 218 */           if (this.world.spigotConfig.witherSpawnSoundRadius > 0 && distanceSquared > (this.world.spigotConfig.witherSpawnSoundRadius * this.world.spigotConfig.witherSpawnSoundRadius))
/* 219 */             continue;  if (distanceSquared > (viewDistance * viewDistance)) {
/* 220 */             double deltaLength = Math.sqrt(distanceSquared);
/* 221 */             double relativeX = player.locX + deltaX / deltaLength * viewDistance;
/* 222 */             double relativeZ = player.locZ + deltaZ / deltaLength * viewDistance;
/* 223 */             player.playerConnection.sendPacket(new PacketPlayOutWorldEvent(1023, new BlockPosition((int)relativeX, (int)this.locY, (int)relativeZ), 0, true)); continue;
/*     */           } 
/* 225 */           player.playerConnection.sendPacket(new PacketPlayOutWorldEvent(1023, new BlockPosition((int)this.locX, (int)this.locY, (int)this.locZ), 0, true));
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 231 */       g(i);
/* 232 */       if (this.ticksLived % 10 == 0) {
/* 233 */         heal(10.0F, EntityRegainHealthEvent.RegainReason.WITHER_SPAWN);
/*     */       }
/*     */     } else {
/*     */       
/* 237 */       super.M();
/*     */       
/*     */       int i;
/*     */       
/* 241 */       for (i = 1; i < 3; i++) {
/* 242 */         if (this.ticksLived >= this.bD[i - 1]) {
/* 243 */           this.bD[i - 1] = this.ticksLived + 10 + this.random.nextInt(10);
/* 244 */           if (this.world.getDifficulty() == EnumDifficulty.NORMAL || this.world.getDifficulty() == EnumDifficulty.HARD) {
/* 245 */             int k = i - 1;
/* 246 */             int l = this.bE[i - 1];
/*     */             
/* 248 */             this.bE[k] = this.bE[i - 1] + 1;
/* 249 */             if (l > 15) {
/*     */ 
/*     */               
/* 252 */               double d0 = MathHelper.a(this.random, this.locX - 10.0D, this.locX + 10.0D);
/* 253 */               double d1 = MathHelper.a(this.random, this.locY - 5.0D, this.locY + 5.0D);
/* 254 */               double d2 = MathHelper.a(this.random, this.locZ - 10.0D, this.locZ + 10.0D);
/*     */               
/* 256 */               a(i + 1, d0, d1, d2, true);
/* 257 */               this.bE[i - 1] = 0;
/*     */             } 
/*     */           } 
/*     */           
/* 261 */           int j = m(i);
/* 262 */           if (j > 0) {
/* 263 */             Entity entity = this.world.getEntity(j);
/*     */             
/* 265 */             if (entity != null && entity.isAlive() && h(entity) <= 900.0D && hasLineOfSight(entity)) {
/* 266 */               if (entity instanceof EntityHuman && ((EntityHuman)entity).abilities.isInvulnerable) {
/* 267 */                 a(i, 0);
/*     */               } else {
/* 269 */                 a(i + 1, (EntityLiving)entity);
/* 270 */                 this.bD[i - 1] = this.ticksLived + 40 + this.random.nextInt(20);
/* 271 */                 this.bE[i - 1] = 0;
/*     */               } 
/*     */             } else {
/* 274 */               a(i, 0);
/*     */             } 
/*     */           } else {
/* 277 */             List<EntityLiving> list = this.world.a(EntityLiving.class, getBoundingBox().grow(20.0D, 8.0D, 20.0D), Predicates.and(bH, IEntitySelector.e));
/*     */             
/* 279 */             for (int i1 = 0; i1 < 10 && !list.isEmpty(); i1++) {
/* 280 */               EntityLiving entityliving = list.get(this.random.nextInt(list.size()));
/*     */               
/* 282 */               if (entityliving != this && entityliving.isAlive() && hasLineOfSight(entityliving)) {
/* 283 */                 if (entityliving instanceof EntityHuman) {
/* 284 */                   if (!((EntityHuman)entityliving).abilities.isInvulnerable)
/* 285 */                     a(i, entityliving.getId()); 
/*     */                   break;
/*     */                 } 
/* 288 */                 a(i, entityliving.getId());
/*     */                 
/*     */                 break;
/*     */               } 
/*     */               
/* 293 */               list.remove(entityliving);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 299 */       if (getGoalTarget() != null) {
/* 300 */         a(0, getGoalTarget().getId());
/*     */       } else {
/* 302 */         a(0, 0);
/*     */       } 
/*     */       
/* 305 */       if (this.bF > 0) {
/* 306 */         this.bF--;
/* 307 */         if (this.bF == 0 && this.world.getGameRules().getBoolean("mobGriefing")) {
/* 308 */           i = MathHelper.floor(this.locY);
/* 309 */           int j = MathHelper.floor(this.locX);
/* 310 */           int j1 = MathHelper.floor(this.locZ);
/* 311 */           boolean flag = false;
/*     */           
/* 313 */           for (int k1 = -1; k1 <= 1; k1++) {
/* 314 */             for (int l1 = -1; l1 <= 1; l1++) {
/* 315 */               for (int i2 = 0; i2 <= 3; i2++) {
/* 316 */                 int j2 = j + k1;
/* 317 */                 int k2 = i + i2;
/* 318 */                 int l2 = j1 + l1;
/* 319 */                 BlockPosition blockposition = new BlockPosition(j2, k2, l2);
/* 320 */                 IBlockData iblockdata = this.world.getType(blockposition);
/* 321 */                 Block block = iblockdata.getBlock();
/*     */                 
/* 323 */                 if (iblockdata.getMaterial() != Material.AIR && a(block))
/*     */                 {
/* 325 */                   if (!CraftEventFactory.callEntityChangeBlockEvent(this, blockposition, Blocks.AIR, 0).isCancelled())
/*     */                   {
/*     */ 
/*     */                     
/* 329 */                     flag = !(!this.world.setAir(blockposition, true) && !flag);
/*     */                   }
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/* 335 */           if (flag) {
/* 336 */             this.world.a((EntityHuman)null, 1022, new BlockPosition(this), 0);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 341 */       if (this.ticksLived % 20 == 0) {
/* 342 */         heal(1.0F, EntityRegainHealthEvent.RegainReason.REGEN);
/*     */       }
/*     */       
/* 345 */       this.bG.setProgress(getHealth() / getMaxHealth());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean a(Block block) {
/* 350 */     return (block != Blocks.BEDROCK && block != Blocks.END_PORTAL && block != Blocks.END_PORTAL_FRAME && block != Blocks.COMMAND_BLOCK && block != Blocks.dc && block != Blocks.dd && block != Blocks.BARRIER && block != Blocks.STRUCTURE_BLOCK && block != Blocks.dj && block != Blocks.PISTON_EXTENSION && block != Blocks.END_GATEWAY);
/*     */   }
/*     */   
/*     */   public void p() {
/* 354 */     g(220);
/* 355 */     setHealth(getMaxHealth() / 3.0F);
/*     */   }
/*     */   
/*     */   public void ba() {}
/*     */   
/*     */   public void b(EntityPlayer entityplayer) {
/* 361 */     super.b(entityplayer);
/* 362 */     this.bG.addPlayer(entityplayer);
/*     */   }
/*     */   
/*     */   public void c(EntityPlayer entityplayer) {
/* 366 */     super.c(entityplayer);
/* 367 */     this.bG.removePlayer(entityplayer);
/*     */   }
/*     */   
/*     */   private double n(int i) {
/* 371 */     if (i <= 0) {
/* 372 */       return this.locX;
/*     */     }
/* 374 */     float f = (this.aN + (180 * (i - 1))) * 0.017453292F;
/* 375 */     float f1 = MathHelper.cos(f);
/*     */     
/* 377 */     return this.locX + f1 * 1.3D;
/*     */   }
/*     */ 
/*     */   
/*     */   private double o(int i) {
/* 382 */     return (i <= 0) ? (this.locY + 3.0D) : (this.locY + 2.2D);
/*     */   }
/*     */   
/*     */   private double p(int i) {
/* 386 */     if (i <= 0) {
/* 387 */       return this.locZ;
/*     */     }
/* 389 */     float f = (this.aN + (180 * (i - 1))) * 0.017453292F;
/* 390 */     float f1 = MathHelper.sin(f);
/*     */     
/* 392 */     return this.locZ + f1 * 1.3D;
/*     */   }
/*     */ 
/*     */   
/*     */   private float b(float f, float f1, float f2) {
/* 397 */     float f3 = MathHelper.g(f1 - f);
/*     */     
/* 399 */     if (f3 > f2) {
/* 400 */       f3 = f2;
/*     */     }
/*     */     
/* 403 */     if (f3 < -f2) {
/* 404 */       f3 = -f2;
/*     */     }
/*     */     
/* 407 */     return f + f3;
/*     */   }
/*     */   
/*     */   private void a(int i, EntityLiving entityliving) {
/* 411 */     a(i, entityliving.locX, entityliving.locY + entityliving.getHeadHeight() * 0.5D, entityliving.locZ, (i == 0 && this.random.nextFloat() < 0.001F));
/*     */   }
/*     */   
/*     */   private void a(int i, double d0, double d1, double d2, boolean flag) {
/* 415 */     this.world.a((EntityHuman)null, 1024, new BlockPosition(this), 0);
/* 416 */     double d3 = n(i);
/* 417 */     double d4 = o(i);
/* 418 */     double d5 = p(i);
/* 419 */     double d6 = d0 - d3;
/* 420 */     double d7 = d1 - d4;
/* 421 */     double d8 = d2 - d5;
/* 422 */     EntityWitherSkull entitywitherskull = new EntityWitherSkull(this.world, this, d6, d7, d8);
/*     */     
/* 424 */     if (flag) {
/* 425 */       entitywitherskull.setCharged(true);
/*     */     }
/*     */     
/* 428 */     entitywitherskull.locY = d4;
/* 429 */     entitywitherskull.locX = d3;
/* 430 */     entitywitherskull.locZ = d5;
/* 431 */     this.world.addEntity(entitywitherskull);
/*     */   }
/*     */   
/*     */   public void a(EntityLiving entityliving, float f) {
/* 435 */     a(0, entityliving);
/*     */   }
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 439 */     if (isInvulnerable(damagesource))
/* 440 */       return false; 
/* 441 */     if (damagesource != DamageSource.DROWN && !(damagesource.getEntity() instanceof EntityWither)) {
/* 442 */       if (dm() > 0 && damagesource != DamageSource.OUT_OF_WORLD) {
/* 443 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 447 */       if (dn()) {
/* 448 */         Entity entity1 = damagesource.i();
/* 449 */         if (entity1 instanceof EntityArrow) {
/* 450 */           return false;
/*     */         }
/*     */       } 
/*     */       
/* 454 */       Entity entity = damagesource.getEntity();
/* 455 */       if (entity != null && !(entity instanceof EntityHuman) && entity instanceof EntityLiving && ((EntityLiving)entity).getMonsterType() == getMonsterType()) {
/* 456 */         return false;
/*     */       }
/* 458 */       if (this.bF <= 0) {
/* 459 */         this.bF = 20;
/*     */       }
/*     */       
/* 462 */       for (int i = 0; i < this.bE.length; i++) {
/* 463 */         this.bE[i] = this.bE[i] + 3;
/*     */       }
/*     */       
/* 466 */       return super.damageEntity(damagesource, f);
/*     */     } 
/*     */ 
/*     */     
/* 470 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void dropDeathLoot(boolean flag, int i) {
/* 475 */     EntityItem entityitem = a(Items.NETHER_STAR, 1);
/*     */     
/* 477 */     if (entityitem != null) {
/* 478 */       entityitem.v();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void L() {
/* 484 */     this.ticksFarFromPlayer = 0;
/*     */   }
/*     */   
/*     */   public void e(float f, float f1) {}
/*     */   
/*     */   public void addEffect(MobEffect mobeffect) {}
/*     */   
/*     */   protected void initAttributes() {
/* 492 */     super.initAttributes();
/* 493 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(300.0D);
/* 494 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.6000000238418579D);
/* 495 */     getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(40.0D);
/* 496 */     getAttributeInstance(GenericAttributes.h).setValue(4.0D);
/*     */   }
/*     */   
/*     */   public int dm() {
/* 500 */     return ((Integer)this.datawatcher.<Integer>get(by)).intValue();
/*     */   }
/*     */   
/*     */   public void g(int i) {
/* 504 */     this.datawatcher.set(by, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public int m(int i) {
/* 508 */     return ((Integer)this.datawatcher.<Integer>get(bx[i])).intValue();
/*     */   }
/*     */   
/*     */   public void a(int i, int j) {
/* 512 */     this.datawatcher.set(bx[i], Integer.valueOf(j));
/*     */   }
/*     */   
/*     */   public boolean dn() {
/* 516 */     return (getHealth() <= getMaxHealth() / 2.0F);
/*     */   }
/*     */   
/*     */   public EnumMonsterType getMonsterType() {
/* 520 */     return EnumMonsterType.UNDEAD;
/*     */   }
/*     */   
/*     */   protected boolean n(Entity entity) {
/* 524 */     return false;
/*     */   }
/*     */   
/*     */   public boolean bf() {
/* 528 */     return false;
/*     */   }
/*     */   
/*     */   public void p(boolean flag) {}
/*     */   
/*     */   class a
/*     */     extends PathfinderGoal {
/*     */     public a() {
/* 536 */       a(7);
/*     */     }
/*     */     
/*     */     public boolean a() {
/* 540 */       return (EntityWither.this.dm() > 0);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityWither.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */