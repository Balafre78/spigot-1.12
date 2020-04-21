/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.projectiles.ProjectileSource;
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class EntityProjectile
/*     */   extends Entity
/*     */   implements IProjectile
/*     */ {
/*     */   private int blockX;
/*     */   private int blockY;
/*     */   private int blockZ;
/*     */   private Block inBlockId;
/*     */   protected boolean inGround;
/*     */   public int shake;
/*     */   
/*     */   public EntityProjectile(World world) {
/*  23 */     super(world);
/*  24 */     this.blockX = -1;
/*  25 */     this.blockY = -1;
/*  26 */     this.blockZ = -1;
/*  27 */     setSize(0.25F, 0.25F);
/*     */   }
/*     */   public EntityLiving shooter; public String shooterName; private int au; private int av; public Entity c; private int aw;
/*     */   public EntityProjectile(World world, double d0, double d1, double d2) {
/*  31 */     this(world);
/*  32 */     setPosition(d0, d1, d2);
/*     */   }
/*     */   
/*     */   public EntityProjectile(World world, EntityLiving entityliving) {
/*  36 */     this(world, entityliving.locX, entityliving.locY + entityliving.getHeadHeight() - 0.10000000149011612D, entityliving.locZ);
/*  37 */     this.shooter = entityliving;
/*  38 */     this.projectileSource = (ProjectileSource)entityliving.getBukkitEntity();
/*     */   }
/*     */   
/*     */   protected void i() {}
/*     */   
/*     */   public void a(Entity entity, float f, float f1, float f2, float f3, float f4) {
/*  44 */     float f5 = -MathHelper.sin(f1 * 0.017453292F) * MathHelper.cos(f * 0.017453292F);
/*  45 */     float f6 = -MathHelper.sin((f + f2) * 0.017453292F);
/*  46 */     float f7 = MathHelper.cos(f1 * 0.017453292F) * MathHelper.cos(f * 0.017453292F);
/*     */     
/*  48 */     shoot(f5, f6, f7, f3, f4);
/*  49 */     this.motX += entity.motX;
/*  50 */     this.motZ += entity.motZ;
/*  51 */     if (!entity.onGround) {
/*  52 */       this.motY += entity.motY;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void shoot(double d0, double d1, double d2, float f, float f1) {
/*  58 */     float f2 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
/*     */     
/*  60 */     d0 /= f2;
/*  61 */     d1 /= f2;
/*  62 */     d2 /= f2;
/*  63 */     d0 += this.random.nextGaussian() * 0.007499999832361937D * f1;
/*  64 */     d1 += this.random.nextGaussian() * 0.007499999832361937D * f1;
/*  65 */     d2 += this.random.nextGaussian() * 0.007499999832361937D * f1;
/*  66 */     d0 *= f;
/*  67 */     d1 *= f;
/*  68 */     d2 *= f;
/*  69 */     this.motX = d0;
/*  70 */     this.motY = d1;
/*  71 */     this.motZ = d2;
/*  72 */     float f3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
/*     */     
/*  74 */     this.yaw = (float)(MathHelper.c(d0, d2) * 57.2957763671875D);
/*  75 */     this.pitch = (float)(MathHelper.c(d1, f3) * 57.2957763671875D);
/*  76 */     this.lastYaw = this.yaw;
/*  77 */     this.lastPitch = this.pitch;
/*  78 */     this.au = 0;
/*     */   }
/*     */   
/*     */   public void B_() {
/*  82 */     this.M = this.locX;
/*  83 */     this.N = this.locY;
/*  84 */     this.O = this.locZ;
/*  85 */     super.B_();
/*  86 */     if (this.shake > 0) {
/*  87 */       this.shake--;
/*     */     }
/*     */     
/*  90 */     if (this.inGround) {
/*  91 */       if (this.world.getType(new BlockPosition(this.blockX, this.blockY, this.blockZ)).getBlock() == this.inBlockId) {
/*  92 */         this.au++;
/*  93 */         if (this.au == 1200) {
/*  94 */           die();
/*     */         }
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 100 */       this.inGround = false;
/* 101 */       this.motX *= (this.random.nextFloat() * 0.2F);
/* 102 */       this.motY *= (this.random.nextFloat() * 0.2F);
/* 103 */       this.motZ *= (this.random.nextFloat() * 0.2F);
/* 104 */       this.au = 0;
/* 105 */       this.av = 0;
/*     */     } else {
/* 107 */       this.av++;
/*     */     } 
/*     */     
/* 110 */     Vec3D vec3d = new Vec3D(this.locX, this.locY, this.locZ);
/* 111 */     Vec3D vec3d1 = new Vec3D(this.locX + this.motX, this.locY + this.motY, this.locZ + this.motZ);
/* 112 */     MovingObjectPosition movingobjectposition = this.world.rayTrace(vec3d, vec3d1);
/*     */     
/* 114 */     vec3d = new Vec3D(this.locX, this.locY, this.locZ);
/* 115 */     vec3d1 = new Vec3D(this.locX + this.motX, this.locY + this.motY, this.locZ + this.motZ);
/* 116 */     if (movingobjectposition != null) {
/* 117 */       vec3d1 = new Vec3D(movingobjectposition.pos.x, movingobjectposition.pos.y, movingobjectposition.pos.z);
/*     */     }
/*     */     
/* 120 */     Entity entity = null;
/* 121 */     List<Entity> list = this.world.getEntities(this, getBoundingBox().b(this.motX, this.motY, this.motZ).g(1.0D));
/* 122 */     double d0 = 0.0D;
/* 123 */     boolean flag = false;
/*     */     
/* 125 */     for (int i = 0; i < list.size(); i++) {
/* 126 */       Entity entity1 = list.get(i);
/*     */       
/* 128 */       if (entity1.isInteractable()) {
/* 129 */         if (entity1 == this.c) {
/* 130 */           flag = true;
/* 131 */         } else if (this.shooter != null && this.ticksLived < 2 && this.c == null && this.shooter == entity1) {
/* 132 */           this.c = entity1;
/* 133 */           flag = true;
/*     */         } else {
/* 135 */           flag = false;
/* 136 */           AxisAlignedBB axisalignedbb = entity1.getBoundingBox().g(0.30000001192092896D);
/* 137 */           MovingObjectPosition movingobjectposition1 = axisalignedbb.b(vec3d, vec3d1);
/*     */           
/* 139 */           if (movingobjectposition1 != null) {
/* 140 */             double d1 = vec3d.distanceSquared(movingobjectposition1.pos);
/*     */             
/* 142 */             if (d1 < d0 || d0 == 0.0D) {
/* 143 */               entity = entity1;
/* 144 */               d0 = d1;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 151 */     if (this.c != null) {
/* 152 */       if (flag) {
/* 153 */         this.aw = 2;
/* 154 */       } else if (this.aw-- <= 0) {
/* 155 */         this.c = null;
/*     */       } 
/*     */     }
/*     */     
/* 159 */     if (entity != null) {
/* 160 */       movingobjectposition = new MovingObjectPosition(entity);
/*     */     }
/*     */     
/* 163 */     if (movingobjectposition != null) {
/* 164 */       if (movingobjectposition.type == MovingObjectPosition.EnumMovingObjectType.BLOCK && this.world.getType(movingobjectposition.a()).getBlock() == Blocks.PORTAL) {
/* 165 */         e(movingobjectposition.a());
/*     */       } else {
/* 167 */         a(movingobjectposition);
/*     */         
/* 169 */         if (this.dead) {
/* 170 */           CraftEventFactory.callProjectileHitEvent(this, movingobjectposition);
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 176 */     this.locX += this.motX;
/* 177 */     this.locY += this.motY;
/* 178 */     this.locZ += this.motZ;
/* 179 */     float f = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/*     */     
/* 181 */     this.yaw = (float)(MathHelper.c(this.motX, this.motZ) * 57.2957763671875D);
/*     */     
/* 183 */     for (this.pitch = (float)(MathHelper.c(this.motY, f) * 57.2957763671875D); this.pitch - this.lastPitch < -180.0F; this.lastPitch -= 360.0F);
/*     */ 
/*     */ 
/*     */     
/* 187 */     while (this.pitch - this.lastPitch >= 180.0F) {
/* 188 */       this.lastPitch += 360.0F;
/*     */     }
/*     */     
/* 191 */     while (this.yaw - this.lastYaw < -180.0F) {
/* 192 */       this.lastYaw -= 360.0F;
/*     */     }
/*     */     
/* 195 */     while (this.yaw - this.lastYaw >= 180.0F) {
/* 196 */       this.lastYaw += 360.0F;
/*     */     }
/*     */     
/* 199 */     this.pitch = this.lastPitch + (this.pitch - this.lastPitch) * 0.2F;
/* 200 */     this.yaw = this.lastYaw + (this.yaw - this.lastYaw) * 0.2F;
/* 201 */     float f1 = 0.99F;
/* 202 */     float f2 = j();
/*     */     
/* 204 */     if (isInWater()) {
/* 205 */       for (int j = 0; j < 4; j++)
/*     */       {
/*     */         
/* 208 */         this.world.addParticle(EnumParticle.WATER_BUBBLE, this.locX - this.motX * 0.25D, this.locY - this.motY * 0.25D, this.locZ - this.motZ * 0.25D, this.motX, this.motY, this.motZ, new int[0]);
/*     */       }
/*     */       
/* 211 */       f1 = 0.8F;
/*     */     } 
/*     */     
/* 214 */     this.motX *= f1;
/* 215 */     this.motY *= f1;
/* 216 */     this.motZ *= f1;
/* 217 */     if (!isNoGravity()) {
/* 218 */       this.motY -= f2;
/*     */     }
/*     */     
/* 221 */     setPosition(this.locX, this.locY, this.locZ);
/*     */   }
/*     */   
/*     */   protected float j() {
/* 225 */     return 0.03F;
/*     */   }
/*     */   
/*     */   protected abstract void a(MovingObjectPosition paramMovingObjectPosition);
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager, String s) {}
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/* 233 */     nbttagcompound.setInt("xTile", this.blockX);
/* 234 */     nbttagcompound.setInt("yTile", this.blockY);
/* 235 */     nbttagcompound.setInt("zTile", this.blockZ);
/* 236 */     MinecraftKey minecraftkey = Block.REGISTRY.b(this.inBlockId);
/*     */     
/* 238 */     nbttagcompound.setString("inTile", (minecraftkey == null) ? "" : minecraftkey.toString());
/* 239 */     nbttagcompound.setByte("shake", (byte)this.shake);
/* 240 */     nbttagcompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
/* 241 */     if ((this.shooterName == null || this.shooterName.isEmpty()) && this.shooter instanceof EntityHuman) {
/* 242 */       this.shooterName = this.shooter.getName();
/*     */     }
/*     */     
/* 245 */     nbttagcompound.setString("ownerName", (this.shooterName == null) ? "" : this.shooterName);
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/* 249 */     this.blockX = nbttagcompound.getInt("xTile");
/* 250 */     this.blockY = nbttagcompound.getInt("yTile");
/* 251 */     this.blockZ = nbttagcompound.getInt("zTile");
/* 252 */     if (nbttagcompound.hasKeyOfType("inTile", 8)) {
/* 253 */       this.inBlockId = Block.getByName(nbttagcompound.getString("inTile"));
/*     */     } else {
/* 255 */       this.inBlockId = Block.getById(nbttagcompound.getByte("inTile") & 0xFF);
/*     */     } 
/*     */     
/* 258 */     this.shake = nbttagcompound.getByte("shake") & 0xFF;
/* 259 */     this.inGround = (nbttagcompound.getByte("inGround") == 1);
/* 260 */     this.shooter = null;
/* 261 */     this.shooterName = nbttagcompound.getString("ownerName");
/* 262 */     if (this.shooterName != null && this.shooterName.isEmpty()) {
/* 263 */       this.shooterName = null;
/*     */     }
/*     */     
/* 266 */     this.shooter = getShooter();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public EntityLiving getShooter() {
/* 271 */     if (this.shooter == null && this.shooterName != null && !this.shooterName.isEmpty()) {
/* 272 */       this.shooter = this.world.a(this.shooterName);
/* 273 */       if (this.shooter == null && this.world instanceof WorldServer) {
/*     */         try {
/* 275 */           Entity entity = ((WorldServer)this.world).getEntity(UUID.fromString(this.shooterName));
/*     */           
/* 277 */           if (entity instanceof EntityLiving) {
/* 278 */             this.shooter = (EntityLiving)entity;
/*     */           }
/* 280 */         } catch (Throwable throwable) {
/* 281 */           this.shooter = null;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 286 */     return this.shooter;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */