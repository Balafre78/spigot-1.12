/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.projectiles.ProjectileSource;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityShulkerBullet
/*     */   extends Entity
/*     */ {
/*     */   private EntityLiving shooter;
/*     */   private Entity target;
/*     */   @Nullable
/*     */   private EnumDirection c;
/*     */   private int d;
/*     */   private double e;
/*     */   
/*     */   public EntityShulkerBullet(World world) {
/*  28 */     super(world);
/*  29 */     setSize(0.3125F, 0.3125F);
/*  30 */     this.noclip = true;
/*     */   } private double f; private double g; @Nullable
/*     */   private UUID h; private BlockPosition at; @Nullable
/*     */   private UUID au; private BlockPosition av; public SoundCategory bK() {
/*  34 */     return SoundCategory.HOSTILE;
/*     */   }
/*     */   
/*     */   public EntityShulkerBullet(World world, EntityLiving entityliving, Entity entity, EnumDirection.EnumAxis enumdirection_enumaxis) {
/*  38 */     this(world);
/*  39 */     this.shooter = entityliving;
/*  40 */     BlockPosition blockposition = new BlockPosition(entityliving);
/*  41 */     double d0 = blockposition.getX() + 0.5D;
/*  42 */     double d1 = blockposition.getY() + 0.5D;
/*  43 */     double d2 = blockposition.getZ() + 0.5D;
/*     */     
/*  45 */     setPositionRotation(d0, d1, d2, this.yaw, this.pitch);
/*  46 */     this.target = entity;
/*  47 */     this.c = EnumDirection.UP;
/*  48 */     a(enumdirection_enumaxis);
/*  49 */     this.projectileSource = (ProjectileSource)entityliving.getBukkitEntity();
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityLiving getShooter() {
/*  54 */     return this.shooter;
/*     */   }
/*     */   
/*     */   public void setShooter(EntityLiving e) {
/*  58 */     this.shooter = e;
/*     */   }
/*     */   
/*     */   public Entity getTarget() {
/*  62 */     return this.target;
/*     */   }
/*     */   
/*     */   public void setTarget(Entity e) {
/*  66 */     this.target = e;
/*  67 */     this.c = EnumDirection.UP;
/*  68 */     a(EnumDirection.EnumAxis.X);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void b(NBTTagCompound nbttagcompound) {
/*  76 */     if (this.shooter != null) {
/*  77 */       BlockPosition blockposition = new BlockPosition(this.shooter);
/*  78 */       NBTTagCompound nbttagcompound1 = GameProfileSerializer.a(this.shooter.getUniqueID());
/*  79 */       nbttagcompound1.setInt("X", blockposition.getX());
/*  80 */       nbttagcompound1.setInt("Y", blockposition.getY());
/*  81 */       nbttagcompound1.setInt("Z", blockposition.getZ());
/*  82 */       nbttagcompound.set("Owner", nbttagcompound1);
/*     */     } 
/*     */     
/*  85 */     if (this.target != null) {
/*  86 */       BlockPosition blockposition = new BlockPosition(this.target);
/*  87 */       NBTTagCompound nbttagcompound1 = GameProfileSerializer.a(this.target.getUniqueID());
/*  88 */       nbttagcompound1.setInt("X", blockposition.getX());
/*  89 */       nbttagcompound1.setInt("Y", blockposition.getY());
/*  90 */       nbttagcompound1.setInt("Z", blockposition.getZ());
/*  91 */       nbttagcompound.set("Target", nbttagcompound1);
/*     */     } 
/*     */     
/*  94 */     if (this.c != null) {
/*  95 */       nbttagcompound.setInt("Dir", this.c.a());
/*     */     }
/*     */     
/*  98 */     nbttagcompound.setInt("Steps", this.d);
/*  99 */     nbttagcompound.setDouble("TXD", this.e);
/* 100 */     nbttagcompound.setDouble("TYD", this.f);
/* 101 */     nbttagcompound.setDouble("TZD", this.g);
/*     */   }
/*     */   
/*     */   protected void a(NBTTagCompound nbttagcompound) {
/* 105 */     this.d = nbttagcompound.getInt("Steps");
/* 106 */     this.e = nbttagcompound.getDouble("TXD");
/* 107 */     this.f = nbttagcompound.getDouble("TYD");
/* 108 */     this.g = nbttagcompound.getDouble("TZD");
/* 109 */     if (nbttagcompound.hasKeyOfType("Dir", 99)) {
/* 110 */       this.c = EnumDirection.fromType1(nbttagcompound.getInt("Dir"));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 115 */     if (nbttagcompound.hasKeyOfType("Owner", 10)) {
/* 116 */       NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Owner");
/* 117 */       this.h = GameProfileSerializer.b(nbttagcompound1);
/* 118 */       this.at = new BlockPosition(nbttagcompound1.getInt("X"), nbttagcompound1.getInt("Y"), nbttagcompound1.getInt("Z"));
/*     */     } 
/*     */     
/* 121 */     if (nbttagcompound.hasKeyOfType("Target", 10)) {
/* 122 */       NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Target");
/* 123 */       this.au = GameProfileSerializer.b(nbttagcompound1);
/* 124 */       this.av = new BlockPosition(nbttagcompound1.getInt("X"), nbttagcompound1.getInt("Y"), nbttagcompound1.getInt("Z"));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void i() {}
/*     */   
/*     */   private void a(@Nullable EnumDirection enumdirection) {
/* 132 */     this.c = enumdirection;
/*     */   }
/*     */   private void a(@Nullable EnumDirection.EnumAxis enumdirection_enumaxis) {
/*     */     BlockPosition blockposition;
/* 136 */     double d0 = 0.5D;
/*     */ 
/*     */     
/* 139 */     if (this.target == null) {
/* 140 */       blockposition = (new BlockPosition(this)).down();
/*     */     } else {
/* 142 */       d0 = this.target.length * 0.5D;
/* 143 */       blockposition = new BlockPosition(this.target.locX, this.target.locY + d0, this.target.locZ);
/*     */     } 
/*     */     
/* 146 */     double d1 = blockposition.getX() + 0.5D;
/* 147 */     double d2 = blockposition.getY() + d0;
/* 148 */     double d3 = blockposition.getZ() + 0.5D;
/* 149 */     EnumDirection enumdirection = null;
/*     */     
/* 151 */     if (blockposition.g(this.locX, this.locY, this.locZ) >= 4.0D) {
/* 152 */       BlockPosition blockposition1 = new BlockPosition(this);
/* 153 */       ArrayList<EnumDirection> arraylist = Lists.newArrayList();
/*     */       
/* 155 */       if (enumdirection_enumaxis != EnumDirection.EnumAxis.X) {
/* 156 */         if (blockposition1.getX() < blockposition.getX() && this.world.isEmpty(blockposition1.east())) {
/* 157 */           arraylist.add(EnumDirection.EAST);
/* 158 */         } else if (blockposition1.getX() > blockposition.getX() && this.world.isEmpty(blockposition1.west())) {
/* 159 */           arraylist.add(EnumDirection.WEST);
/*     */         } 
/*     */       }
/*     */       
/* 163 */       if (enumdirection_enumaxis != EnumDirection.EnumAxis.Y) {
/* 164 */         if (blockposition1.getY() < blockposition.getY() && this.world.isEmpty(blockposition1.up())) {
/* 165 */           arraylist.add(EnumDirection.UP);
/* 166 */         } else if (blockposition1.getY() > blockposition.getY() && this.world.isEmpty(blockposition1.down())) {
/* 167 */           arraylist.add(EnumDirection.DOWN);
/*     */         } 
/*     */       }
/*     */       
/* 171 */       if (enumdirection_enumaxis != EnumDirection.EnumAxis.Z) {
/* 172 */         if (blockposition1.getZ() < blockposition.getZ() && this.world.isEmpty(blockposition1.south())) {
/* 173 */           arraylist.add(EnumDirection.SOUTH);
/* 174 */         } else if (blockposition1.getZ() > blockposition.getZ() && this.world.isEmpty(blockposition1.north())) {
/* 175 */           arraylist.add(EnumDirection.NORTH);
/*     */         } 
/*     */       }
/*     */       
/* 179 */       enumdirection = EnumDirection.a(this.random);
/* 180 */       if (arraylist.isEmpty()) {
/* 181 */         for (int i = 5; !this.world.isEmpty(blockposition1.shift(enumdirection)) && i > 0; i--) {
/* 182 */           enumdirection = EnumDirection.a(this.random);
/*     */         }
/*     */       } else {
/* 185 */         enumdirection = arraylist.get(this.random.nextInt(arraylist.size()));
/*     */       } 
/*     */       
/* 188 */       d1 = this.locX + enumdirection.getAdjacentX();
/* 189 */       d2 = this.locY + enumdirection.getAdjacentY();
/* 190 */       d3 = this.locZ + enumdirection.getAdjacentZ();
/*     */     } 
/*     */     
/* 193 */     a(enumdirection);
/* 194 */     double d4 = d1 - this.locX;
/* 195 */     double d5 = d2 - this.locY;
/* 196 */     double d6 = d3 - this.locZ;
/* 197 */     double d7 = MathHelper.sqrt(d4 * d4 + d5 * d5 + d6 * d6);
/*     */     
/* 199 */     if (d7 == 0.0D) {
/* 200 */       this.e = 0.0D;
/* 201 */       this.f = 0.0D;
/* 202 */       this.g = 0.0D;
/*     */     } else {
/* 204 */       this.e = d4 / d7 * 0.15D;
/* 205 */       this.f = d5 / d7 * 0.15D;
/* 206 */       this.g = d6 / d7 * 0.15D;
/*     */     } 
/*     */     
/* 209 */     this.impulse = true;
/* 210 */     this.d = 10 + this.random.nextInt(5) * 10;
/*     */   }
/*     */   
/*     */   public void B_() {
/* 214 */     if (!this.world.isClientSide && this.world.getDifficulty() == EnumDifficulty.PEACEFUL) {
/* 215 */       die();
/*     */     } else {
/* 217 */       super.B_();
/* 218 */       if (!this.world.isClientSide) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 223 */         if (this.target == null && this.au != null) {
/* 224 */           List<EntityLiving> list = this.world.a(EntityLiving.class, new AxisAlignedBB(this.av.a(-2, -2, -2), this.av.a(2, 2, 2)));
/* 225 */           Iterator<EntityLiving> iterator = list.iterator();
/*     */           
/* 227 */           while (iterator.hasNext()) {
/* 228 */             EntityLiving entityliving = iterator.next();
/* 229 */             if (entityliving.getUniqueID().equals(this.au)) {
/* 230 */               this.target = entityliving;
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/* 235 */           this.au = null;
/*     */         } 
/*     */         
/* 238 */         if (this.shooter == null && this.h != null) {
/* 239 */           List<EntityLiving> list = this.world.a(EntityLiving.class, new AxisAlignedBB(this.at.a(-2, -2, -2), this.at.a(2, 2, 2)));
/* 240 */           Iterator<EntityLiving> iterator = list.iterator();
/*     */           
/* 242 */           while (iterator.hasNext()) {
/* 243 */             EntityLiving entityliving = iterator.next();
/* 244 */             if (entityliving.getUniqueID().equals(this.h)) {
/* 245 */               this.shooter = entityliving;
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/* 250 */           this.h = null;
/*     */         } 
/*     */         
/* 253 */         if (this.target != null && this.target.isAlive() && (!(this.target instanceof EntityHuman) || !((EntityHuman)this.target).isSpectator())) {
/* 254 */           this.e = MathHelper.a(this.e * 1.025D, -1.0D, 1.0D);
/* 255 */           this.f = MathHelper.a(this.f * 1.025D, -1.0D, 1.0D);
/* 256 */           this.g = MathHelper.a(this.g * 1.025D, -1.0D, 1.0D);
/* 257 */           this.motX += (this.e - this.motX) * 0.2D;
/* 258 */           this.motY += (this.f - this.motY) * 0.2D;
/* 259 */           this.motZ += (this.g - this.motZ) * 0.2D;
/* 260 */         } else if (!isNoGravity()) {
/* 261 */           this.motY -= 0.04D;
/*     */         } 
/*     */         
/* 264 */         MovingObjectPosition movingobjectposition = ProjectileHelper.a(this, true, false, this.shooter);
/*     */         
/* 266 */         if (movingobjectposition != null) {
/* 267 */           a(movingobjectposition);
/*     */         }
/*     */       } 
/*     */       
/* 271 */       setPosition(this.locX + this.motX, this.locY + this.motY, this.locZ + this.motZ);
/* 272 */       ProjectileHelper.a(this, 0.5F);
/* 273 */       if (this.world.isClientSide) {
/* 274 */         this.world.addParticle(EnumParticle.END_ROD, this.locX - this.motX, this.locY - this.motY + 0.15D, this.locZ - this.motZ, 0.0D, 0.0D, 0.0D, new int[0]);
/* 275 */       } else if (this.target != null && !this.target.dead) {
/* 276 */         if (this.d > 0) {
/* 277 */           this.d--;
/* 278 */           if (this.d == 0) {
/* 279 */             a((this.c == null) ? null : this.c.k());
/*     */           }
/*     */         } 
/*     */         
/* 283 */         if (this.c != null) {
/* 284 */           BlockPosition blockposition = new BlockPosition(this);
/* 285 */           EnumDirection.EnumAxis enumdirection_enumaxis = this.c.k();
/*     */           
/* 287 */           if (this.world.d(blockposition.shift(this.c), false)) {
/* 288 */             a(enumdirection_enumaxis);
/*     */           } else {
/* 290 */             BlockPosition blockposition1 = new BlockPosition(this.target);
/*     */             
/* 292 */             if ((enumdirection_enumaxis == EnumDirection.EnumAxis.X && blockposition.getX() == blockposition1.getX()) || (enumdirection_enumaxis == EnumDirection.EnumAxis.Z && blockposition.getZ() == blockposition1.getZ()) || (enumdirection_enumaxis == EnumDirection.EnumAxis.Y && blockposition.getY() == blockposition1.getY())) {
/* 293 */               a(enumdirection_enumaxis);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBurning() {
/* 303 */     return false;
/*     */   }
/*     */   
/*     */   public float aw() {
/* 307 */     return 1.0F;
/*     */   }
/*     */   
/*     */   protected void a(MovingObjectPosition movingobjectposition) {
/* 311 */     CraftEventFactory.callProjectileHitEvent(this, movingobjectposition);
/* 312 */     if (movingobjectposition.entity == null) {
/* 313 */       ((WorldServer)this.world).a(EnumParticle.EXPLOSION_LARGE, this.locX, this.locY, this.locZ, 2, 0.2D, 0.2D, 0.2D, 0.0D, new int[0]);
/* 314 */       a(SoundEffects.gD, 1.0F, 1.0F);
/*     */     } else {
/* 316 */       boolean flag = movingobjectposition.entity.damageEntity(DamageSource.a(this, this.shooter).b(), 4.0F);
/*     */       
/* 318 */       if (flag) {
/* 319 */         a(this.shooter, movingobjectposition.entity);
/* 320 */         if (movingobjectposition.entity instanceof EntityLiving) {
/* 321 */           ((EntityLiving)movingobjectposition.entity).addEffect(new MobEffect(MobEffects.LEVITATION, 200));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 326 */     die();
/*     */   }
/*     */   
/*     */   public boolean isInteractable() {
/* 330 */     return true;
/*     */   }
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 334 */     if (!this.world.isClientSide) {
/* 335 */       a(SoundEffects.gE, 1.0F, 1.0F);
/* 336 */       ((WorldServer)this.world).a(EnumParticle.CRIT, this.locX, this.locY, this.locZ, 15, 0.2D, 0.2D, 0.2D, 0.0D, new int[0]);
/* 337 */       die();
/*     */     } 
/*     */     
/* 340 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityShulkerBullet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */