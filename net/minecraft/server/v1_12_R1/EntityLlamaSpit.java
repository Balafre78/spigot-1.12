/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ 
/*     */ public class EntityLlamaSpit extends Entity implements IProjectile {
/*     */   public EntityLiving shooter;
/*     */   private NBTTagCompound b;
/*     */   
/*     */   public EntityLlamaSpit(World world) {
/*  14 */     super(world);
/*     */   }
/*     */   
/*     */   public EntityLlamaSpit(World world, EntityLlama entityllama) {
/*  18 */     super(world);
/*  19 */     this.shooter = entityllama;
/*  20 */     setPosition(entityllama.locX - (entityllama.width + 1.0F) * 0.5D * MathHelper.sin(entityllama.aN * 0.017453292F), entityllama.locY + entityllama.getHeadHeight() - 0.10000000149011612D, entityllama.locZ + (entityllama.width + 1.0F) * 0.5D * MathHelper.cos(entityllama.aN * 0.017453292F));
/*  21 */     setSize(0.25F, 0.25F);
/*     */   }
/*     */   
/*     */   public void B_() {
/*  25 */     super.B_();
/*  26 */     if (this.b != null) {
/*  27 */       j();
/*     */     }
/*     */     
/*  30 */     Vec3D vec3d = new Vec3D(this.locX, this.locY, this.locZ);
/*  31 */     Vec3D vec3d1 = new Vec3D(this.locX + this.motX, this.locY + this.motY, this.locZ + this.motZ);
/*  32 */     MovingObjectPosition movingobjectposition = this.world.rayTrace(vec3d, vec3d1);
/*     */     
/*  34 */     vec3d = new Vec3D(this.locX, this.locY, this.locZ);
/*  35 */     vec3d1 = new Vec3D(this.locX + this.motX, this.locY + this.motY, this.locZ + this.motZ);
/*  36 */     if (movingobjectposition != null) {
/*  37 */       vec3d1 = new Vec3D(movingobjectposition.pos.x, movingobjectposition.pos.y, movingobjectposition.pos.z);
/*     */     }
/*     */     
/*  40 */     Entity entity = a(vec3d, vec3d1);
/*     */     
/*  42 */     if (entity != null) {
/*  43 */       movingobjectposition = new MovingObjectPosition(entity);
/*     */     }
/*     */     
/*  46 */     if (movingobjectposition != null) {
/*  47 */       a(movingobjectposition);
/*     */     }
/*     */     
/*  50 */     this.locX += this.motX;
/*  51 */     this.locY += this.motY;
/*  52 */     this.locZ += this.motZ;
/*  53 */     float f = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/*     */     
/*  55 */     this.yaw = (float)(MathHelper.c(this.motX, this.motZ) * 57.2957763671875D);
/*     */     
/*  57 */     for (this.pitch = (float)(MathHelper.c(this.motY, f) * 57.2957763671875D); this.pitch - this.lastPitch < -180.0F; this.lastPitch -= 360.0F);
/*     */ 
/*     */ 
/*     */     
/*  61 */     while (this.pitch - this.lastPitch >= 180.0F) {
/*  62 */       this.lastPitch += 360.0F;
/*     */     }
/*     */     
/*  65 */     while (this.yaw - this.lastYaw < -180.0F) {
/*  66 */       this.lastYaw -= 360.0F;
/*     */     }
/*     */     
/*  69 */     while (this.yaw - this.lastYaw >= 180.0F) {
/*  70 */       this.lastYaw += 360.0F;
/*     */     }
/*     */     
/*  73 */     this.pitch = this.lastPitch + (this.pitch - this.lastPitch) * 0.2F;
/*  74 */     this.yaw = this.lastYaw + (this.yaw - this.lastYaw) * 0.2F;
/*     */ 
/*     */ 
/*     */     
/*  78 */     if (!this.world.a(getBoundingBox(), Material.AIR)) {
/*  79 */       die();
/*  80 */     } else if (isInWater()) {
/*  81 */       die();
/*     */     } else {
/*  83 */       this.motX *= 0.9900000095367432D;
/*  84 */       this.motY *= 0.9900000095367432D;
/*  85 */       this.motZ *= 0.9900000095367432D;
/*  86 */       if (!isNoGravity()) {
/*  87 */         this.motY -= 0.05999999865889549D;
/*     */       }
/*     */       
/*  90 */       setPosition(this.locX, this.locY, this.locZ);
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private Entity a(Vec3D vec3d, Vec3D vec3d1) {
/*  96 */     Entity entity = null;
/*  97 */     List<Entity> list = this.world.getEntities(this, getBoundingBox().b(this.motX, this.motY, this.motZ).g(1.0D));
/*  98 */     double d0 = 0.0D;
/*  99 */     Iterator<Entity> iterator = list.iterator();
/*     */     
/* 101 */     while (iterator.hasNext()) {
/* 102 */       Entity entity1 = iterator.next();
/*     */       
/* 104 */       if (entity1 != this.shooter) {
/* 105 */         AxisAlignedBB axisalignedbb = entity1.getBoundingBox().g(0.30000001192092896D);
/* 106 */         MovingObjectPosition movingobjectposition = axisalignedbb.b(vec3d, vec3d1);
/*     */         
/* 108 */         if (movingobjectposition != null) {
/* 109 */           double d1 = vec3d.distanceSquared(movingobjectposition.pos);
/*     */           
/* 111 */           if (d1 < d0 || d0 == 0.0D) {
/* 112 */             entity = entity1;
/* 113 */             d0 = d1;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 119 */     return entity;
/*     */   }
/*     */   
/*     */   public void shoot(double d0, double d1, double d2, float f, float f1) {
/* 123 */     float f2 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
/*     */     
/* 125 */     d0 /= f2;
/* 126 */     d1 /= f2;
/* 127 */     d2 /= f2;
/* 128 */     d0 += this.random.nextGaussian() * 0.007499999832361937D * f1;
/* 129 */     d1 += this.random.nextGaussian() * 0.007499999832361937D * f1;
/* 130 */     d2 += this.random.nextGaussian() * 0.007499999832361937D * f1;
/* 131 */     d0 *= f;
/* 132 */     d1 *= f;
/* 133 */     d2 *= f;
/* 134 */     this.motX = d0;
/* 135 */     this.motY = d1;
/* 136 */     this.motZ = d2;
/* 137 */     float f3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
/*     */     
/* 139 */     this.yaw = (float)(MathHelper.c(d0, d2) * 57.2957763671875D);
/* 140 */     this.pitch = (float)(MathHelper.c(d1, f3) * 57.2957763671875D);
/* 141 */     this.lastYaw = this.yaw;
/* 142 */     this.lastPitch = this.pitch;
/*     */   }
/*     */   
/*     */   public void a(MovingObjectPosition movingobjectposition) {
/* 146 */     CraftEventFactory.callProjectileHitEvent(this, movingobjectposition);
/* 147 */     if (movingobjectposition.entity != null && this.shooter != null) {
/* 148 */       movingobjectposition.entity.damageEntity(DamageSource.a(this, this.shooter).b(), 1.0F);
/*     */     }
/*     */     
/* 151 */     if (!this.world.isClientSide) {
/* 152 */       die();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void i() {}
/*     */   
/*     */   protected void a(NBTTagCompound nbttagcompound) {
/* 160 */     if (nbttagcompound.hasKeyOfType("Owner", 10)) {
/* 161 */       this.b = nbttagcompound.getCompound("Owner");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(NBTTagCompound nbttagcompound) {
/* 167 */     if (this.shooter != null) {
/* 168 */       NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 169 */       UUID uuid = this.shooter.getUniqueID();
/*     */       
/* 171 */       nbttagcompound1.a("OwnerUUID", uuid);
/* 172 */       nbttagcompound.set("Owner", nbttagcompound1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void j() {
/* 178 */     if (this.b != null && this.b.b("OwnerUUID")) {
/* 179 */       UUID uuid = this.b.a("OwnerUUID");
/* 180 */       List<EntityLlama> list = this.world.a(EntityLlama.class, getBoundingBox().g(15.0D));
/* 181 */       Iterator<EntityLlama> iterator = list.iterator();
/*     */       
/* 183 */       while (iterator.hasNext()) {
/* 184 */         EntityLlama entityllama = iterator.next();
/*     */         
/* 186 */         if (entityllama.getUniqueID().equals(uuid)) {
/* 187 */           this.shooter = entityllama;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 193 */     this.b = null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityLlamaSpit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */