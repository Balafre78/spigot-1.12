/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.commons.lang3.Validate;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Hanging;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.hanging.HangingBreakByEntityEvent;
/*     */ import org.bukkit.event.hanging.HangingBreakEvent;
/*     */ 
/*     */ public abstract class EntityHanging
/*     */   extends Entity
/*     */ {
/*  15 */   private static final Predicate<Entity> c = new Predicate() {
/*     */       public boolean a(@Nullable Entity entity) {
/*  17 */         return entity instanceof EntityHanging;
/*     */       }
/*     */       
/*     */       public boolean apply(@Nullable Object object) {
/*  21 */         return a((Entity)object);
/*     */       }
/*     */     };
/*     */ 
/*     */   
/*     */   private int d;
/*     */ 
/*     */   
/*     */   public EntityHanging(World world) {
/*  30 */     super(world);
/*  31 */     setSize(0.5F, 0.5F);
/*     */   } public BlockPosition blockPosition; @Nullable
/*     */   public EnumDirection direction;
/*     */   public EntityHanging(World world, BlockPosition blockposition) {
/*  35 */     this(world);
/*  36 */     this.blockPosition = blockposition;
/*     */   }
/*     */   
/*     */   protected void i() {}
/*     */   
/*     */   public void setDirection(EnumDirection enumdirection) {
/*  42 */     Validate.notNull(enumdirection);
/*  43 */     Validate.isTrue(enumdirection.k().c());
/*  44 */     this.direction = enumdirection;
/*  45 */     this.yaw = (this.direction.get2DRotationValue() * 90);
/*  46 */     this.lastYaw = this.yaw;
/*  47 */     updateBoundingBox();
/*     */   }
/*     */ 
/*     */   
/*     */   public static AxisAlignedBB calculateBoundingBox(Entity entity, BlockPosition blockPosition, EnumDirection direction, int width, int height) {
/*  52 */     double d0 = blockPosition.getX() + 0.5D;
/*  53 */     double d1 = blockPosition.getY() + 0.5D;
/*  54 */     double d2 = blockPosition.getZ() + 0.5D;
/*     */     
/*  56 */     double d4 = a(width);
/*  57 */     double d5 = a(height);
/*     */     
/*  59 */     d0 -= direction.getAdjacentX() * 0.46875D;
/*  60 */     d2 -= direction.getAdjacentZ() * 0.46875D;
/*  61 */     d1 += d5;
/*  62 */     EnumDirection enumdirection = direction.f();
/*     */     
/*  64 */     d0 += d4 * enumdirection.getAdjacentX();
/*  65 */     d2 += d4 * enumdirection.getAdjacentZ();
/*  66 */     if (entity != null) {
/*  67 */       entity.locX = d0;
/*  68 */       entity.locY = d1;
/*  69 */       entity.locZ = d2;
/*     */     } 
/*  71 */     double d6 = width;
/*  72 */     double d7 = height;
/*  73 */     double d8 = width;
/*     */     
/*  75 */     if (direction.k() == EnumDirection.EnumAxis.Z) {
/*  76 */       d8 = 1.0D;
/*     */     } else {
/*  78 */       d6 = 1.0D;
/*     */     } 
/*     */     
/*  81 */     d6 /= 32.0D;
/*  82 */     d7 /= 32.0D;
/*  83 */     d8 /= 32.0D;
/*  84 */     return new AxisAlignedBB(d0 - d6, d1 - d7, d2 - d8, d0 + d6, d1 + d7, d2 + d8);
/*     */   }
/*     */   
/*     */   protected void updateBoundingBox() {
/*  88 */     if (this.direction != null)
/*     */     {
/*  90 */       a(calculateBoundingBox(this, this.blockPosition, this.direction, getWidth(), getHeight()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static double a(int i) {
/*  96 */     return (i % 32 == 0) ? 0.5D : 0.0D;
/*     */   }
/*     */   
/*     */   public void B_() {
/* 100 */     this.lastX = this.locX;
/* 101 */     this.lastY = this.locY;
/* 102 */     this.lastZ = this.locZ;
/* 103 */     if (this.d++ == this.world.spigotConfig.hangingTickFrequency && !this.world.isClientSide) {
/* 104 */       this.d = 0;
/* 105 */       if (!this.dead && !survives()) {
/*     */         HangingBreakEvent.RemoveCause cause;
/* 107 */         Material material = this.world.getType(new BlockPosition(this)).getMaterial();
/*     */ 
/*     */         
/* 110 */         if (!material.equals(Material.AIR)) {
/*     */           
/* 112 */           cause = HangingBreakEvent.RemoveCause.OBSTRUCTION;
/*     */         } else {
/* 114 */           cause = HangingBreakEvent.RemoveCause.PHYSICS;
/*     */         } 
/*     */         
/* 117 */         HangingBreakEvent event = new HangingBreakEvent((Hanging)getBukkitEntity(), cause);
/* 118 */         this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */         
/* 120 */         if (this.dead || event.isCancelled()) {
/*     */           return;
/*     */         }
/*     */         
/* 124 */         die();
/* 125 */         a((Entity)null);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean survives() {
/* 132 */     if (!this.world.getCubes(this, getBoundingBox()).isEmpty()) {
/* 133 */       return false;
/*     */     }
/* 135 */     int i = Math.max(1, getWidth() / 16);
/* 136 */     int j = Math.max(1, getHeight() / 16);
/* 137 */     BlockPosition blockposition = this.blockPosition.shift(this.direction.opposite());
/* 138 */     EnumDirection enumdirection = this.direction.f();
/* 139 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/*     */     
/* 141 */     for (int k = 0; k < i; k++) {
/* 142 */       for (int l = 0; l < j; l++) {
/* 143 */         int i1 = (i - 1) / -2;
/* 144 */         int j1 = (j - 1) / -2;
/*     */         
/* 146 */         blockposition_mutableblockposition.g(blockposition).c(enumdirection, k + i1).c(EnumDirection.UP, l + j1);
/* 147 */         IBlockData iblockdata = this.world.getType(blockposition_mutableblockposition);
/*     */         
/* 149 */         if (!iblockdata.getMaterial().isBuildable() && !BlockDiodeAbstract.isDiode(iblockdata)) {
/* 150 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 155 */     return this.world.getEntities(this, getBoundingBox(), c).isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInteractable() {
/* 160 */     return true;
/*     */   }
/*     */   
/*     */   public boolean t(Entity entity) {
/* 164 */     return (entity instanceof EntityHuman) ? damageEntity(DamageSource.playerAttack((EntityHuman)entity), 0.0F) : false;
/*     */   }
/*     */   
/*     */   public EnumDirection getDirection() {
/* 168 */     return this.direction;
/*     */   }
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 172 */     if (isInvulnerable(damagesource)) {
/* 173 */       return false;
/*     */     }
/* 175 */     if (!this.dead && !this.world.isClientSide) {
/*     */       
/* 177 */       HangingBreakEvent event = new HangingBreakEvent((Hanging)getBukkitEntity(), HangingBreakEvent.RemoveCause.DEFAULT);
/* 178 */       if (damagesource.getEntity() != null) {
/* 179 */         HangingBreakByEntityEvent hangingBreakByEntityEvent = new HangingBreakByEntityEvent((Hanging)getBukkitEntity(), (damagesource.getEntity() == null) ? null : (Entity)damagesource.getEntity().getBukkitEntity(), damagesource.isExplosion() ? HangingBreakEvent.RemoveCause.EXPLOSION : HangingBreakEvent.RemoveCause.ENTITY);
/* 180 */       } else if (damagesource.isExplosion()) {
/* 181 */         event = new HangingBreakEvent((Hanging)getBukkitEntity(), HangingBreakEvent.RemoveCause.EXPLOSION);
/*     */       } 
/*     */       
/* 184 */       this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */       
/* 186 */       if (this.dead || event.isCancelled()) {
/* 187 */         return true;
/*     */       }
/*     */ 
/*     */       
/* 191 */       die();
/* 192 */       ax();
/* 193 */       a(damagesource.getEntity());
/*     */     } 
/*     */     
/* 196 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void move(EnumMoveType enummovetype, double d0, double d1, double d2) {
/* 201 */     if (!this.world.isClientSide && !this.dead && d0 * d0 + d1 * d1 + d2 * d2 > 0.0D) {
/* 202 */       if (this.dead) {
/*     */         return;
/*     */       }
/*     */       
/* 206 */       HangingBreakEvent event = new HangingBreakEvent((Hanging)getBukkitEntity(), HangingBreakEvent.RemoveCause.PHYSICS);
/* 207 */       this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */       
/* 209 */       if (this.dead || event.isCancelled()) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 214 */       die();
/* 215 */       a((Entity)null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void f(double d0, double d1, double d2) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/* 229 */     nbttagcompound.setByte("Facing", (byte)this.direction.get2DRotationValue());
/* 230 */     BlockPosition blockposition = getBlockPosition();
/*     */     
/* 232 */     nbttagcompound.setInt("TileX", blockposition.getX());
/* 233 */     nbttagcompound.setInt("TileY", blockposition.getY());
/* 234 */     nbttagcompound.setInt("TileZ", blockposition.getZ());
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/* 238 */     this.blockPosition = new BlockPosition(nbttagcompound.getInt("TileX"), nbttagcompound.getInt("TileY"), nbttagcompound.getInt("TileZ"));
/* 239 */     setDirection(EnumDirection.fromType2(nbttagcompound.getByte("Facing")));
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
/*     */   public EntityItem a(ItemStack itemstack, float f) {
/* 251 */     EntityItem entityitem = new EntityItem(this.world, this.locX + (this.direction.getAdjacentX() * 0.15F), this.locY + f, this.locZ + (this.direction.getAdjacentZ() * 0.15F), itemstack);
/*     */     
/* 253 */     entityitem.q();
/* 254 */     this.world.addEntity(entityitem);
/* 255 */     return entityitem;
/*     */   }
/*     */   
/*     */   protected boolean aA() {
/* 259 */     return false;
/*     */   }
/*     */   
/*     */   public void setPosition(double d0, double d1, double d2) {
/* 263 */     this.blockPosition = new BlockPosition(d0, d1, d2);
/* 264 */     updateBoundingBox();
/* 265 */     this.impulse = true;
/*     */   }
/*     */   
/*     */   public BlockPosition getBlockPosition() {
/* 269 */     return this.blockPosition;
/*     */   }
/*     */   
/*     */   public float a(EnumBlockRotation enumblockrotation) {
/* 273 */     if (this.direction != null && this.direction.k() != EnumDirection.EnumAxis.Y) {
/* 274 */       switch (enumblockrotation) {
/*     */         case null:
/* 276 */           this.direction = this.direction.opposite();
/*     */           break;
/*     */         
/*     */         case COUNTERCLOCKWISE_90:
/* 280 */           this.direction = this.direction.f();
/*     */           break;
/*     */         
/*     */         case CLOCKWISE_90:
/* 284 */           this.direction = this.direction.e();
/*     */           break;
/*     */       } 
/*     */     }
/* 288 */     float f = MathHelper.g(this.yaw);
/*     */     
/* 290 */     switch (enumblockrotation) {
/*     */       case null:
/* 292 */         return f + 180.0F;
/*     */       
/*     */       case COUNTERCLOCKWISE_90:
/* 295 */         return f + 90.0F;
/*     */       
/*     */       case CLOCKWISE_90:
/* 298 */         return f + 270.0F;
/*     */     } 
/*     */     
/* 301 */     return f;
/*     */   }
/*     */ 
/*     */   
/*     */   public float a(EnumBlockMirror enumblockmirror) {
/* 306 */     return a(enumblockmirror.a(this.direction));
/*     */   }
/*     */   
/*     */   public void onLightningStrike(EntityLightning entitylightning) {}
/*     */   
/*     */   public abstract int getWidth();
/*     */   
/*     */   public abstract int getHeight();
/*     */   
/*     */   public abstract void a(@Nullable Entity paramEntity);
/*     */   
/*     */   public abstract void p();
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityHanging.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */