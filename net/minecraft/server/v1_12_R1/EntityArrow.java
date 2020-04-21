/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.base.Predicates;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.entity.Arrow;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityCombustByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityCombustEvent;
/*     */ import org.bukkit.event.player.PlayerPickupArrowEvent;
/*     */ import org.bukkit.projectiles.ProjectileSource;
/*     */ 
/*     */ public abstract class EntityArrow extends Entity implements IProjectile {
/*  17 */   private static final Predicate<Entity> f = Predicates.and(new Predicate[] { IEntitySelector.e, IEntitySelector.a, new Predicate() {
/*     */           public boolean a(@Nullable Entity entity) {
/*  19 */             return entity.isInteractable();
/*     */           }
/*     */           
/*     */           public boolean apply(@Nullable Object object) {
/*  23 */             return a((Entity)object);
/*     */           }
/*     */         } });
/*  26 */   private static final DataWatcherObject<Byte> g = DataWatcher.a((Class)EntityArrow.class, DataWatcherRegistry.a);
/*     */   
/*     */   private int h;
/*     */   
/*     */   private int at;
/*     */   
/*     */   private int au;
/*     */   private Block av;
/*     */   private int aw;
/*     */   public boolean inGround;
/*     */   protected int b;
/*     */   public PickupStatus fromPlayer;
/*     */   public int shake;
/*     */   public Entity shooter;
/*     */   private int ax;
/*     */   private int ay;
/*     */   private double damage;
/*     */   public int knockbackStrength;
/*     */   
/*     */   public void inactiveTick() {
/*  46 */     if (this.inGround)
/*     */     {
/*  48 */       this.ax++;
/*     */     }
/*  50 */     super.inactiveTick();
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityArrow(World world) {
/*  55 */     super(world);
/*  56 */     this.h = -1;
/*  57 */     this.at = -1;
/*  58 */     this.au = -1;
/*  59 */     this.fromPlayer = PickupStatus.DISALLOWED;
/*  60 */     this.damage = 2.0D;
/*  61 */     setSize(0.5F, 0.5F);
/*     */   }
/*     */   
/*     */   public EntityArrow(World world, double d0, double d1, double d2) {
/*  65 */     this(world);
/*  66 */     setPosition(d0, d1, d2);
/*     */   }
/*     */   
/*     */   public EntityArrow(World world, EntityLiving entityliving) {
/*  70 */     this(world, entityliving.locX, entityliving.locY + entityliving.getHeadHeight() - 0.10000000149011612D, entityliving.locZ);
/*  71 */     this.shooter = entityliving;
/*  72 */     this.projectileSource = (ProjectileSource)entityliving.getBukkitEntity();
/*  73 */     if (entityliving instanceof EntityHuman) {
/*  74 */       this.fromPlayer = PickupStatus.ALLOWED;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void i() {
/*  80 */     this.datawatcher.register(g, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */   public void a(Entity entity, float f, float f1, float f2, float f3, float f4) {
/*  84 */     float f5 = -MathHelper.sin(f1 * 0.017453292F) * MathHelper.cos(f * 0.017453292F);
/*  85 */     float f6 = -MathHelper.sin(f * 0.017453292F);
/*  86 */     float f7 = MathHelper.cos(f1 * 0.017453292F) * MathHelper.cos(f * 0.017453292F);
/*     */     
/*  88 */     shoot(f5, f6, f7, f3, f4);
/*  89 */     this.motX += entity.motX;
/*  90 */     this.motZ += entity.motZ;
/*  91 */     if (!entity.onGround) {
/*  92 */       this.motY += entity.motY;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void shoot(double d0, double d1, double d2, float f, float f1) {
/*  98 */     float f2 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
/*     */     
/* 100 */     d0 /= f2;
/* 101 */     d1 /= f2;
/* 102 */     d2 /= f2;
/* 103 */     d0 += this.random.nextGaussian() * 0.007499999832361937D * f1;
/* 104 */     d1 += this.random.nextGaussian() * 0.007499999832361937D * f1;
/* 105 */     d2 += this.random.nextGaussian() * 0.007499999832361937D * f1;
/* 106 */     d0 *= f;
/* 107 */     d1 *= f;
/* 108 */     d2 *= f;
/* 109 */     this.motX = d0;
/* 110 */     this.motY = d1;
/* 111 */     this.motZ = d2;
/* 112 */     float f3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
/*     */     
/* 114 */     this.yaw = (float)(MathHelper.c(d0, d2) * 57.2957763671875D);
/* 115 */     this.pitch = (float)(MathHelper.c(d1, f3) * 57.2957763671875D);
/* 116 */     this.lastYaw = this.yaw;
/* 117 */     this.lastPitch = this.pitch;
/* 118 */     this.ax = 0;
/*     */   }
/*     */   
/*     */   public void B_() {
/* 122 */     super.B_();
/* 123 */     if (this.lastPitch == 0.0F && this.lastYaw == 0.0F) {
/* 124 */       float f = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/*     */       
/* 126 */       this.yaw = (float)(MathHelper.c(this.motX, this.motZ) * 57.2957763671875D);
/* 127 */       this.pitch = (float)(MathHelper.c(this.motY, f) * 57.2957763671875D);
/* 128 */       this.lastYaw = this.yaw;
/* 129 */       this.lastPitch = this.pitch;
/*     */     } 
/*     */     
/* 132 */     BlockPosition blockposition = new BlockPosition(this.h, this.at, this.au);
/* 133 */     IBlockData iblockdata = this.world.getType(blockposition);
/* 134 */     Block block = iblockdata.getBlock();
/*     */     
/* 136 */     if (iblockdata.getMaterial() != Material.AIR) {
/* 137 */       AxisAlignedBB axisalignedbb = iblockdata.d(this.world, blockposition);
/*     */       
/* 139 */       if (axisalignedbb != Block.k && axisalignedbb.a(blockposition).b(new Vec3D(this.locX, this.locY, this.locZ))) {
/* 140 */         this.inGround = true;
/*     */       }
/*     */     } 
/*     */     
/* 144 */     if (this.shake > 0) {
/* 145 */       this.shake--;
/*     */     }
/*     */     
/* 148 */     if (this.inGround) {
/* 149 */       int i = block.toLegacyData(iblockdata);
/*     */       
/* 151 */       if ((block != this.av || i != this.aw) && !this.world.a(getBoundingBox().g(0.05D))) {
/* 152 */         this.inGround = false;
/* 153 */         this.motX *= (this.random.nextFloat() * 0.2F);
/* 154 */         this.motY *= (this.random.nextFloat() * 0.2F);
/* 155 */         this.motZ *= (this.random.nextFloat() * 0.2F);
/* 156 */         this.ax = 0;
/* 157 */         this.ay = 0;
/*     */       } else {
/* 159 */         this.ax++;
/* 160 */         if (this.ax >= this.world.spigotConfig.arrowDespawnRate) {
/* 161 */           die();
/*     */         }
/*     */       } 
/*     */       
/* 165 */       this.b++;
/*     */     } else {
/* 167 */       this.b = 0;
/* 168 */       this.ay++;
/* 169 */       Vec3D vec3d = new Vec3D(this.locX, this.locY, this.locZ);
/* 170 */       Vec3D vec3d1 = new Vec3D(this.locX + this.motX, this.locY + this.motY, this.locZ + this.motZ);
/* 171 */       MovingObjectPosition movingobjectposition = this.world.rayTrace(vec3d, vec3d1, false, true, false);
/*     */       
/* 173 */       vec3d = new Vec3D(this.locX, this.locY, this.locZ);
/* 174 */       vec3d1 = new Vec3D(this.locX + this.motX, this.locY + this.motY, this.locZ + this.motZ);
/* 175 */       if (movingobjectposition != null) {
/* 176 */         vec3d1 = new Vec3D(movingobjectposition.pos.x, movingobjectposition.pos.y, movingobjectposition.pos.z);
/*     */       }
/*     */       
/* 179 */       Entity entity = a(vec3d, vec3d1);
/*     */       
/* 181 */       if (entity != null) {
/* 182 */         movingobjectposition = new MovingObjectPosition(entity);
/*     */       }
/*     */       
/* 185 */       if (movingobjectposition != null && movingobjectposition.entity instanceof EntityHuman) {
/* 186 */         EntityHuman entityhuman = (EntityHuman)movingobjectposition.entity;
/*     */         
/* 188 */         if (this.shooter instanceof EntityHuman && !((EntityHuman)this.shooter).a(entityhuman)) {
/* 189 */           movingobjectposition = null;
/*     */         }
/*     */       } 
/*     */       
/* 193 */       if (movingobjectposition != null) {
/* 194 */         a(movingobjectposition);
/*     */       }
/*     */       
/* 197 */       if (isCritical()) {
/* 198 */         for (int j = 0; j < 4; j++) {
/* 199 */           this.world.addParticle(EnumParticle.CRIT, this.locX + this.motX * j / 4.0D, this.locY + this.motY * j / 4.0D, this.locZ + this.motZ * j / 4.0D, -this.motX, -this.motY + 0.2D, -this.motZ, new int[0]);
/*     */         }
/*     */       }
/*     */       
/* 203 */       this.locX += this.motX;
/* 204 */       this.locY += this.motY;
/* 205 */       this.locZ += this.motZ;
/* 206 */       float f1 = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/*     */       
/* 208 */       this.yaw = (float)(MathHelper.c(this.motX, this.motZ) * 57.2957763671875D);
/*     */       
/* 210 */       for (this.pitch = (float)(MathHelper.c(this.motY, f1) * 57.2957763671875D); this.pitch - this.lastPitch < -180.0F; this.lastPitch -= 360.0F);
/*     */ 
/*     */ 
/*     */       
/* 214 */       while (this.pitch - this.lastPitch >= 180.0F) {
/* 215 */         this.lastPitch += 360.0F;
/*     */       }
/*     */       
/* 218 */       while (this.yaw - this.lastYaw < -180.0F) {
/* 219 */         this.lastYaw -= 360.0F;
/*     */       }
/*     */       
/* 222 */       while (this.yaw - this.lastYaw >= 180.0F) {
/* 223 */         this.lastYaw += 360.0F;
/*     */       }
/*     */       
/* 226 */       this.pitch = this.lastPitch + (this.pitch - this.lastPitch) * 0.2F;
/* 227 */       this.yaw = this.lastYaw + (this.yaw - this.lastYaw) * 0.2F;
/* 228 */       float f2 = 0.99F;
/*     */ 
/*     */       
/* 231 */       if (isInWater()) {
/* 232 */         for (int k = 0; k < 4; k++)
/*     */         {
/*     */           
/* 235 */           this.world.addParticle(EnumParticle.WATER_BUBBLE, this.locX - this.motX * 0.25D, this.locY - this.motY * 0.25D, this.locZ - this.motZ * 0.25D, this.motX, this.motY, this.motZ, new int[0]);
/*     */         }
/*     */         
/* 238 */         f2 = 0.6F;
/*     */       } 
/*     */       
/* 241 */       if (an()) {
/* 242 */         extinguish();
/*     */       }
/*     */       
/* 245 */       this.motX *= f2;
/* 246 */       this.motY *= f2;
/* 247 */       this.motZ *= f2;
/* 248 */       if (!isNoGravity()) {
/* 249 */         this.motY -= 0.05000000074505806D;
/*     */       }
/*     */       
/* 252 */       setPosition(this.locX, this.locY, this.locZ);
/* 253 */       checkBlockCollisions();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void a(MovingObjectPosition movingobjectposition) {
/* 258 */     Entity entity = movingobjectposition.entity;
/* 259 */     CraftEventFactory.callProjectileHitEvent(this, movingobjectposition);
/* 260 */     if (entity != null) {
/* 261 */       DamageSource damagesource; float f = MathHelper.sqrt(this.motX * this.motX + this.motY * this.motY + this.motZ * this.motZ);
/* 262 */       int i = MathHelper.f(f * this.damage);
/*     */       
/* 264 */       if (isCritical()) {
/* 265 */         i += this.random.nextInt(i / 2 + 2);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 270 */       if (this.shooter == null) {
/* 271 */         damagesource = DamageSource.arrow(this, this);
/*     */       } else {
/* 273 */         damagesource = DamageSource.arrow(this, this.shooter);
/*     */       } 
/*     */       
/* 276 */       if (isBurning() && !(entity instanceof EntityEnderman)) {
/*     */         
/* 278 */         EntityCombustByEntityEvent combustEvent = new EntityCombustByEntityEvent((Entity)getBukkitEntity(), (Entity)entity.getBukkitEntity(), 5);
/* 279 */         Bukkit.getPluginManager().callEvent((Event)combustEvent);
/* 280 */         if (!combustEvent.isCancelled()) {
/* 281 */           entity.setOnFire(combustEvent.getDuration());
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 286 */       if (entity.damageEntity(damagesource, i)) {
/* 287 */         if (entity instanceof EntityLiving) {
/* 288 */           EntityLiving entityliving = (EntityLiving)entity;
/*     */           
/* 290 */           if (!this.world.isClientSide) {
/* 291 */             entityliving.setArrowCount(entityliving.getArrowCount() + 1);
/*     */           }
/*     */           
/* 294 */           if (this.knockbackStrength > 0) {
/* 295 */             float f1 = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/*     */             
/* 297 */             if (f1 > 0.0F) {
/* 298 */               entityliving.f(this.motX * this.knockbackStrength * 0.6000000238418579D / f1, 0.1D, this.motZ * this.knockbackStrength * 0.6000000238418579D / f1);
/*     */             }
/*     */           } 
/*     */           
/* 302 */           if (this.shooter instanceof EntityLiving) {
/* 303 */             EnchantmentManager.a(entityliving, this.shooter);
/* 304 */             EnchantmentManager.b((EntityLiving)this.shooter, entityliving);
/*     */           } 
/*     */           
/* 307 */           a(entityliving);
/* 308 */           if (this.shooter != null && entityliving != this.shooter && entityliving instanceof EntityHuman && this.shooter instanceof EntityPlayer) {
/* 309 */             ((EntityPlayer)this.shooter).playerConnection.sendPacket(new PacketPlayOutGameStateChange(6, 0.0F));
/*     */           }
/*     */         } 
/*     */         
/* 313 */         a(SoundEffects.u, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
/* 314 */         if (!(entity instanceof EntityEnderman)) {
/* 315 */           die();
/*     */         }
/*     */       } else {
/* 318 */         this.motX *= -0.10000000149011612D;
/* 319 */         this.motY *= -0.10000000149011612D;
/* 320 */         this.motZ *= -0.10000000149011612D;
/* 321 */         this.yaw += 180.0F;
/* 322 */         this.lastYaw += 180.0F;
/* 323 */         this.ay = 0;
/* 324 */         if (!this.world.isClientSide && this.motX * this.motX + this.motY * this.motY + this.motZ * this.motZ < 0.0010000000474974513D) {
/* 325 */           if (this.fromPlayer == PickupStatus.ALLOWED) {
/* 326 */             a(j(), 0.1F);
/*     */           }
/*     */           
/* 329 */           die();
/*     */         } 
/*     */       } 
/*     */     } else {
/* 333 */       BlockPosition blockposition = movingobjectposition.a();
/*     */       
/* 335 */       this.h = blockposition.getX();
/* 336 */       this.at = blockposition.getY();
/* 337 */       this.au = blockposition.getZ();
/* 338 */       IBlockData iblockdata = this.world.getType(blockposition);
/*     */       
/* 340 */       this.av = iblockdata.getBlock();
/* 341 */       this.aw = this.av.toLegacyData(iblockdata);
/* 342 */       this.motX = (float)(movingobjectposition.pos.x - this.locX);
/* 343 */       this.motY = (float)(movingobjectposition.pos.y - this.locY);
/* 344 */       this.motZ = (float)(movingobjectposition.pos.z - this.locZ);
/* 345 */       float f2 = MathHelper.sqrt(this.motX * this.motX + this.motY * this.motY + this.motZ * this.motZ);
/*     */       
/* 347 */       this.locX -= this.motX / f2 * 0.05000000074505806D;
/* 348 */       this.locY -= this.motY / f2 * 0.05000000074505806D;
/* 349 */       this.locZ -= this.motZ / f2 * 0.05000000074505806D;
/* 350 */       a(SoundEffects.u, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
/* 351 */       this.inGround = true;
/* 352 */       this.shake = 7;
/* 353 */       setCritical(false);
/* 354 */       if (iblockdata.getMaterial() != Material.AIR) {
/* 355 */         this.av.a(this.world, blockposition, iblockdata, this);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void move(EnumMoveType enummovetype, double d0, double d1, double d2) {
/* 362 */     super.move(enummovetype, d0, d1, d2);
/* 363 */     if (this.inGround) {
/* 364 */       this.h = MathHelper.floor(this.locX);
/* 365 */       this.at = MathHelper.floor(this.locY);
/* 366 */       this.au = MathHelper.floor(this.locZ);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(EntityLiving entityliving) {}
/*     */   
/*     */   @Nullable
/*     */   protected Entity a(Vec3D vec3d, Vec3D vec3d1) {
/* 375 */     Entity entity = null;
/* 376 */     List<Entity> list = this.world.getEntities(this, getBoundingBox().b(this.motX, this.motY, this.motZ).g(1.0D), f);
/* 377 */     double d0 = 0.0D;
/*     */     
/* 379 */     for (int i = 0; i < list.size(); i++) {
/* 380 */       Entity entity1 = list.get(i);
/*     */       
/* 382 */       if (entity1 != this.shooter || this.ay >= 5) {
/* 383 */         AxisAlignedBB axisalignedbb = entity1.getBoundingBox().g(0.30000001192092896D);
/* 384 */         MovingObjectPosition movingobjectposition = axisalignedbb.b(vec3d, vec3d1);
/*     */         
/* 386 */         if (movingobjectposition != null) {
/* 387 */           double d1 = vec3d.distanceSquared(movingobjectposition.pos);
/*     */           
/* 389 */           if (d1 < d0 || d0 == 0.0D) {
/* 390 */             entity = entity1;
/* 391 */             d0 = d1;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 397 */     return entity;
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager, String s) {}
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {
/* 403 */     a(dataconvertermanager, "Arrow");
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/* 407 */     nbttagcompound.setInt("xTile", this.h);
/* 408 */     nbttagcompound.setInt("yTile", this.at);
/* 409 */     nbttagcompound.setInt("zTile", this.au);
/* 410 */     nbttagcompound.setShort("life", (short)this.ax);
/* 411 */     MinecraftKey minecraftkey = Block.REGISTRY.b(this.av);
/*     */     
/* 413 */     nbttagcompound.setString("inTile", (minecraftkey == null) ? "" : minecraftkey.toString());
/* 414 */     nbttagcompound.setByte("inData", (byte)this.aw);
/* 415 */     nbttagcompound.setByte("shake", (byte)this.shake);
/* 416 */     nbttagcompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
/* 417 */     nbttagcompound.setByte("pickup", (byte)this.fromPlayer.ordinal());
/* 418 */     nbttagcompound.setDouble("damage", this.damage);
/* 419 */     nbttagcompound.setBoolean("crit", isCritical());
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/* 423 */     this.h = nbttagcompound.getInt("xTile");
/* 424 */     this.at = nbttagcompound.getInt("yTile");
/* 425 */     this.au = nbttagcompound.getInt("zTile");
/* 426 */     this.ax = nbttagcompound.getShort("life");
/* 427 */     if (nbttagcompound.hasKeyOfType("inTile", 8)) {
/* 428 */       this.av = Block.getByName(nbttagcompound.getString("inTile"));
/*     */     } else {
/* 430 */       this.av = Block.getById(nbttagcompound.getByte("inTile") & 0xFF);
/*     */     } 
/*     */     
/* 433 */     this.aw = nbttagcompound.getByte("inData") & 0xFF;
/* 434 */     this.shake = nbttagcompound.getByte("shake") & 0xFF;
/* 435 */     this.inGround = (nbttagcompound.getByte("inGround") == 1);
/* 436 */     if (nbttagcompound.hasKeyOfType("damage", 99)) {
/* 437 */       this.damage = nbttagcompound.getDouble("damage");
/*     */     }
/*     */     
/* 440 */     if (nbttagcompound.hasKeyOfType("pickup", 99)) {
/* 441 */       this.fromPlayer = PickupStatus.a(nbttagcompound.getByte("pickup"));
/* 442 */     } else if (nbttagcompound.hasKeyOfType("player", 99)) {
/* 443 */       this.fromPlayer = nbttagcompound.getBoolean("player") ? PickupStatus.ALLOWED : PickupStatus.DISALLOWED;
/*     */     } 
/*     */     
/* 446 */     setCritical(nbttagcompound.getBoolean("crit"));
/*     */   }
/*     */   
/*     */   public void d(EntityHuman entityhuman) {
/* 450 */     if (!this.world.isClientSide && this.inGround && this.shake <= 0) {
/*     */       
/* 452 */       ItemStack itemstack = j();
/* 453 */       EntityItem item = new EntityItem(this.world, this.locX, this.locY, this.locZ, itemstack);
/* 454 */       if (this.fromPlayer == PickupStatus.ALLOWED && entityhuman.inventory.canHold(itemstack) > 0) {
/* 455 */         PlayerPickupArrowEvent event = new PlayerPickupArrowEvent((Player)entityhuman.getBukkitEntity(), (Item)new CraftItem(this.world.getServer(), this, item), (Arrow)getBukkitEntity());
/*     */         
/* 457 */         this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */         
/* 459 */         if (event.isCancelled()) {
/*     */           return;
/*     */         }
/*     */       } 
/* 463 */       boolean flag = !(this.fromPlayer != PickupStatus.ALLOWED && (this.fromPlayer != PickupStatus.CREATIVE_ONLY || !entityhuman.abilities.canInstantlyBuild));
/*     */       
/* 465 */       if (this.fromPlayer == PickupStatus.ALLOWED && !entityhuman.inventory.pickup(item.getItemStack()))
/*     */       {
/* 467 */         flag = false;
/*     */       }
/*     */       
/* 470 */       if (flag) {
/* 471 */         entityhuman.receive(this, 1);
/* 472 */         die();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract ItemStack j();
/*     */   
/*     */   protected boolean playStepSound() {
/* 481 */     return false;
/*     */   }
/*     */   
/*     */   public void c(double d0) {
/* 485 */     this.damage = d0;
/*     */   }
/*     */   
/*     */   public double k() {
/* 489 */     return this.damage;
/*     */   }
/*     */   
/*     */   public void setKnockbackStrength(int i) {
/* 493 */     this.knockbackStrength = i;
/*     */   }
/*     */   
/*     */   public boolean bd() {
/* 497 */     return false;
/*     */   }
/*     */   
/*     */   public float getHeadHeight() {
/* 501 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public void setCritical(boolean flag) {
/* 505 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(g)).byteValue();
/*     */     
/* 507 */     if (flag) {
/* 508 */       this.datawatcher.set(g, Byte.valueOf((byte)(b0 | 0x1)));
/*     */     } else {
/* 510 */       this.datawatcher.set(g, Byte.valueOf((byte)(b0 & 0xFFFFFFFE)));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCritical() {
/* 516 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(g)).byteValue();
/*     */     
/* 518 */     return ((b0 & 0x1) != 0);
/*     */   }
/*     */   
/*     */   public void a(EntityLiving entityliving, float f) {
/* 522 */     int i = EnchantmentManager.a(Enchantments.ARROW_DAMAGE, entityliving);
/* 523 */     int j = EnchantmentManager.a(Enchantments.ARROW_KNOCKBACK, entityliving);
/*     */     
/* 525 */     c((f * 2.0F) + this.random.nextGaussian() * 0.25D + (this.world.getDifficulty().a() * 0.11F));
/* 526 */     if (i > 0) {
/* 527 */       c(k() + i * 0.5D + 0.5D);
/*     */     }
/*     */     
/* 530 */     if (j > 0) {
/* 531 */       setKnockbackStrength(j);
/*     */     }
/*     */     
/* 534 */     if (EnchantmentManager.a(Enchantments.ARROW_FIRE, entityliving) > 0) {
/*     */       
/* 536 */       EntityCombustEvent event = new EntityCombustEvent((Entity)getBukkitEntity(), 100);
/* 537 */       this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */       
/* 539 */       if (!event.isCancelled()) {
/* 540 */         setOnFire(event.getDuration());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public enum PickupStatus
/*     */   {
/* 549 */     DISALLOWED, ALLOWED, CREATIVE_ONLY;
/*     */ 
/*     */ 
/*     */     
/*     */     public static PickupStatus a(int i) {
/* 554 */       if (i < 0 || i > (values()).length) {
/* 555 */         i = 0;
/*     */       }
/*     */       
/* 558 */       return values()[i];
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityArrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */