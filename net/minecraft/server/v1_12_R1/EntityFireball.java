/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.projectiles.ProjectileSource;
/*     */ 
/*     */ public abstract class EntityFireball extends Entity {
/*     */   public EntityLiving shooter;
/*     */   private int e;
/*     */   private int f;
/*     */   public double dirX;
/*     */   public double dirY;
/*     */   public double dirZ;
/*  13 */   public float bukkitYield = 1.0F;
/*     */   public boolean isIncendiary = true;
/*     */   
/*     */   public EntityFireball(World world) {
/*  17 */     super(world);
/*  18 */     setSize(1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   protected void i() {}
/*     */   
/*     */   public EntityFireball(World world, double d0, double d1, double d2, double d3, double d4, double d5) {
/*  24 */     super(world);
/*  25 */     setSize(1.0F, 1.0F);
/*  26 */     setPositionRotation(d0, d1, d2, this.yaw, this.pitch);
/*  27 */     setPosition(d0, d1, d2);
/*  28 */     double d6 = MathHelper.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
/*     */     
/*  30 */     this.dirX = d3 / d6 * 0.1D;
/*  31 */     this.dirY = d4 / d6 * 0.1D;
/*  32 */     this.dirZ = d5 / d6 * 0.1D;
/*     */   }
/*     */   
/*     */   public EntityFireball(World world, EntityLiving entityliving, double d0, double d1, double d2) {
/*  36 */     super(world);
/*  37 */     this.shooter = entityliving;
/*  38 */     this.projectileSource = (ProjectileSource)entityliving.getBukkitEntity();
/*  39 */     setSize(1.0F, 1.0F);
/*  40 */     setPositionRotation(entityliving.locX, entityliving.locY, entityliving.locZ, entityliving.yaw, entityliving.pitch);
/*  41 */     setPosition(this.locX, this.locY, this.locZ);
/*  42 */     this.motX = 0.0D;
/*  43 */     this.motY = 0.0D;
/*  44 */     this.motZ = 0.0D;
/*     */     
/*  46 */     setDirection(d0, d1, d2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDirection(double d0, double d1, double d2) {
/*  51 */     d0 += this.random.nextGaussian() * 0.4D;
/*  52 */     d1 += this.random.nextGaussian() * 0.4D;
/*  53 */     d2 += this.random.nextGaussian() * 0.4D;
/*  54 */     double d3 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
/*     */     
/*  56 */     this.dirX = d0 / d3 * 0.1D;
/*  57 */     this.dirY = d1 / d3 * 0.1D;
/*  58 */     this.dirZ = d2 / d3 * 0.1D;
/*     */   }
/*     */   
/*     */   public void B_() {
/*  62 */     if (!this.world.isClientSide && ((this.shooter != null && this.shooter.dead) || !this.world.isLoaded(new BlockPosition(this)))) {
/*  63 */       die();
/*     */     } else {
/*  65 */       super.B_();
/*  66 */       if (k()) {
/*  67 */         setOnFire(1);
/*     */       }
/*     */       
/*  70 */       this.f++;
/*  71 */       MovingObjectPosition movingobjectposition = ProjectileHelper.a(this, true, (this.f >= 25), this.shooter);
/*     */       
/*  73 */       if (movingobjectposition != null) {
/*  74 */         a(movingobjectposition);
/*     */ 
/*     */         
/*  77 */         if (this.dead) {
/*  78 */           CraftEventFactory.callProjectileHitEvent(this, movingobjectposition);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*  83 */       this.locX += this.motX;
/*  84 */       this.locY += this.motY;
/*  85 */       this.locZ += this.motZ;
/*  86 */       ProjectileHelper.a(this, 0.2F);
/*  87 */       float f = l();
/*     */       
/*  89 */       if (isInWater()) {
/*  90 */         for (int i = 0; i < 4; i++)
/*     */         {
/*     */           
/*  93 */           this.world.addParticle(EnumParticle.WATER_BUBBLE, this.locX - this.motX * 0.25D, this.locY - this.motY * 0.25D, this.locZ - this.motZ * 0.25D, this.motX, this.motY, this.motZ, new int[0]);
/*     */         }
/*     */         
/*  96 */         f = 0.8F;
/*     */       } 
/*     */       
/*  99 */       this.motX += this.dirX;
/* 100 */       this.motY += this.dirY;
/* 101 */       this.motZ += this.dirZ;
/* 102 */       this.motX *= f;
/* 103 */       this.motY *= f;
/* 104 */       this.motZ *= f;
/* 105 */       this.world.addParticle(j(), this.locX, this.locY + 0.5D, this.locZ, 0.0D, 0.0D, 0.0D, new int[0]);
/* 106 */       setPosition(this.locX, this.locY, this.locZ);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean k() {
/* 111 */     return true;
/*     */   }
/*     */   
/*     */   protected EnumParticle j() {
/* 115 */     return EnumParticle.SMOKE_NORMAL;
/*     */   }
/*     */   
/*     */   protected float l() {
/* 119 */     return 0.95F;
/*     */   }
/*     */   
/*     */   protected abstract void a(MovingObjectPosition paramMovingObjectPosition);
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager, String s) {}
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/* 127 */     nbttagcompound.set("direction", a(new double[] { this.motX, this.motY, this.motZ }));
/* 128 */     nbttagcompound.set("power", a(new double[] { this.dirX, this.dirY, this.dirZ }));
/* 129 */     nbttagcompound.setInt("life", this.e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/* 135 */     if (nbttagcompound.hasKeyOfType("power", 9)) {
/* 136 */       NBTTagList nbttaglist = nbttagcompound.getList("power", 6);
/* 137 */       if (nbttaglist.size() == 3) {
/* 138 */         this.dirX = nbttaglist.f(0);
/* 139 */         this.dirY = nbttaglist.f(1);
/* 140 */         this.dirZ = nbttaglist.f(2);
/*     */       } 
/*     */     } 
/*     */     
/* 144 */     this.e = nbttagcompound.getInt("life");
/* 145 */     if (nbttagcompound.hasKeyOfType("direction", 9) && nbttagcompound.getList("direction", 6).size() == 3) {
/* 146 */       NBTTagList nbttaglist = nbttagcompound.getList("direction", 6);
/* 147 */       this.motX = nbttaglist.f(0);
/* 148 */       this.motY = nbttaglist.f(1);
/* 149 */       this.motZ = nbttaglist.f(2);
/*     */     } else {
/* 151 */       die();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInteractable() {
/* 157 */     return true;
/*     */   }
/*     */   
/*     */   public float aI() {
/* 161 */     return 1.0F;
/*     */   }
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 165 */     if (isInvulnerable(damagesource)) {
/* 166 */       return false;
/*     */     }
/* 168 */     ax();
/* 169 */     if (damagesource.getEntity() != null) {
/*     */       
/* 171 */       if (CraftEventFactory.handleNonLivingEntityDamageEvent(this, damagesource, f)) {
/* 172 */         return false;
/*     */       }
/*     */       
/* 175 */       Vec3D vec3d = damagesource.getEntity().aJ();
/*     */       
/* 177 */       if (vec3d != null) {
/* 178 */         this.motX = vec3d.x;
/* 179 */         this.motY = vec3d.y;
/* 180 */         this.motZ = vec3d.z;
/* 181 */         this.dirX = this.motX * 0.1D;
/* 182 */         this.dirY = this.motY * 0.1D;
/* 183 */         this.dirZ = this.motZ * 0.1D;
/*     */       } 
/*     */       
/* 186 */       if (damagesource.getEntity() instanceof EntityLiving) {
/* 187 */         this.shooter = (EntityLiving)damagesource.getEntity();
/* 188 */         this.projectileSource = (ProjectileSource)this.shooter.getBukkitEntity();
/*     */       } 
/*     */       
/* 191 */       return true;
/*     */     } 
/* 193 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float aw() {
/* 199 */     return 1.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityFireball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */