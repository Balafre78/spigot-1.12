/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ 
/*     */ public class EntityFireworks
/*     */   extends Entity {
/*   9 */   public static final DataWatcherObject<ItemStack> FIREWORK_ITEM = DataWatcher.a((Class)EntityFireworks.class, DataWatcherRegistry.f);
/*  10 */   private static final DataWatcherObject<Integer> b = DataWatcher.a((Class)EntityFireworks.class, DataWatcherRegistry.b);
/*     */   private int ticksFlown;
/*     */   public int expectedLifespan;
/*     */   private EntityLiving e;
/*     */   
/*     */   public EntityFireworks(World world) {
/*  16 */     super(world);
/*  17 */     setSize(0.25F, 0.25F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void inactiveTick() {
/*  23 */     this.ticksFlown++;
/*  24 */     super.inactiveTick();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void i() {
/*  29 */     this.datawatcher.register(FIREWORK_ITEM, ItemStack.a);
/*  30 */     this.datawatcher.register(b, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   public EntityFireworks(World world, double d0, double d1, double d2, ItemStack itemstack) {
/*  34 */     super(world);
/*  35 */     this.ticksFlown = 0;
/*  36 */     setSize(0.25F, 0.25F);
/*  37 */     setPosition(d0, d1, d2);
/*  38 */     int i = 1;
/*     */     
/*  40 */     if (!itemstack.isEmpty() && itemstack.hasTag()) {
/*  41 */       this.datawatcher.set(FIREWORK_ITEM, itemstack.cloneItemStack());
/*  42 */       NBTTagCompound nbttagcompound = itemstack.getTag();
/*  43 */       NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Fireworks");
/*     */       
/*  45 */       i += nbttagcompound1.getByte("Flight");
/*     */     } 
/*     */     
/*  48 */     this.motX = this.random.nextGaussian() * 0.001D;
/*  49 */     this.motZ = this.random.nextGaussian() * 0.001D;
/*  50 */     this.motY = 0.05D;
/*  51 */     this.expectedLifespan = 10 * i + this.random.nextInt(6) + this.random.nextInt(7);
/*     */   }
/*     */   
/*     */   public EntityFireworks(World world, ItemStack itemstack, EntityLiving entityliving) {
/*  55 */     this(world, entityliving.locX, entityliving.locY, entityliving.locZ, itemstack);
/*  56 */     this.datawatcher.set(b, Integer.valueOf(entityliving.getId()));
/*  57 */     this.e = entityliving;
/*     */   }
/*     */   
/*     */   public void B_() {
/*  61 */     this.M = this.locX;
/*  62 */     this.N = this.locY;
/*  63 */     this.O = this.locZ;
/*  64 */     super.B_();
/*  65 */     if (j()) {
/*  66 */       if (this.e == null) {
/*  67 */         Entity entity = this.world.getEntity(((Integer)this.datawatcher.<Integer>get(b)).intValue());
/*     */         
/*  69 */         if (entity instanceof EntityLiving) {
/*  70 */           this.e = (EntityLiving)entity;
/*     */         }
/*     */       } 
/*     */       
/*  74 */       if (this.e != null) {
/*  75 */         if (this.e.cP()) {
/*  76 */           Vec3D vec3d = this.e.aJ();
/*     */ 
/*     */ 
/*     */           
/*  80 */           this.e.motX += vec3d.x * 0.1D + (vec3d.x * 1.5D - this.e.motX) * 0.5D;
/*  81 */           this.e.motY += vec3d.y * 0.1D + (vec3d.y * 1.5D - this.e.motY) * 0.5D;
/*  82 */           this.e.motZ += vec3d.z * 0.1D + (vec3d.z * 1.5D - this.e.motZ) * 0.5D;
/*     */         } 
/*     */         
/*  85 */         setPosition(this.e.locX, this.e.locY, this.e.locZ);
/*  86 */         this.motX = this.e.motX;
/*  87 */         this.motY = this.e.motY;
/*  88 */         this.motZ = this.e.motZ;
/*     */       } 
/*     */     } else {
/*  91 */       this.motX *= 1.15D;
/*  92 */       this.motZ *= 1.15D;
/*  93 */       this.motY += 0.04D;
/*  94 */       move(EnumMoveType.SELF, this.motX, this.motY, this.motZ);
/*     */     } 
/*     */     
/*  97 */     float f = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/*     */     
/*  99 */     this.yaw = (float)(MathHelper.c(this.motX, this.motZ) * 57.2957763671875D);
/*     */     
/* 101 */     for (this.pitch = (float)(MathHelper.c(this.motY, f) * 57.2957763671875D); this.pitch - this.lastPitch < -180.0F; this.lastPitch -= 360.0F);
/*     */ 
/*     */ 
/*     */     
/* 105 */     while (this.pitch - this.lastPitch >= 180.0F) {
/* 106 */       this.lastPitch += 360.0F;
/*     */     }
/*     */     
/* 109 */     while (this.yaw - this.lastYaw < -180.0F) {
/* 110 */       this.lastYaw -= 360.0F;
/*     */     }
/*     */     
/* 113 */     while (this.yaw - this.lastYaw >= 180.0F) {
/* 114 */       this.lastYaw += 360.0F;
/*     */     }
/*     */     
/* 117 */     this.pitch = this.lastPitch + (this.pitch - this.lastPitch) * 0.2F;
/* 118 */     this.yaw = this.lastYaw + (this.yaw - this.lastYaw) * 0.2F;
/* 119 */     if (this.ticksFlown == 0 && !isSilent()) {
/* 120 */       this.world.a((EntityHuman)null, this.locX, this.locY, this.locZ, SoundEffects.bI, SoundCategory.AMBIENT, 3.0F, 1.0F);
/*     */     }
/*     */     
/* 123 */     this.ticksFlown++;
/* 124 */     if (this.world.isClientSide && this.ticksFlown % 2 < 2) {
/* 125 */       this.world.addParticle(EnumParticle.FIREWORKS_SPARK, this.locX, this.locY - 0.3D, this.locZ, this.random.nextGaussian() * 0.05D, -this.motY * 0.5D, this.random.nextGaussian() * 0.05D, new int[0]);
/*     */     }
/*     */     
/* 128 */     if (!this.world.isClientSide && this.ticksFlown > this.expectedLifespan) {
/*     */       
/* 130 */       if (!CraftEventFactory.callFireworkExplodeEvent(this).isCancelled()) {
/* 131 */         this.world.broadcastEntityEffect(this, (byte)17);
/* 132 */         k();
/*     */       } 
/*     */       
/* 135 */       die();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void k() {
/* 141 */     float f = 0.0F;
/* 142 */     ItemStack itemstack = this.datawatcher.<ItemStack>get(FIREWORK_ITEM);
/* 143 */     NBTTagCompound nbttagcompound = itemstack.isEmpty() ? null : itemstack.d("Fireworks");
/* 144 */     NBTTagList nbttaglist = (nbttagcompound != null) ? nbttagcompound.getList("Explosions", 10) : null;
/*     */     
/* 146 */     if (nbttaglist != null && !nbttaglist.isEmpty()) {
/* 147 */       f = (5 + nbttaglist.size() * 2);
/*     */     }
/*     */     
/* 150 */     if (f > 0.0F) {
/* 151 */       if (this.e != null) {
/* 152 */         CraftEventFactory.entityDamage = this;
/* 153 */         this.e.damageEntity(DamageSource.t, (5 + nbttaglist.size() * 2));
/* 154 */         CraftEventFactory.entityDamage = null;
/*     */       } 
/*     */ 
/*     */       
/* 158 */       Vec3D vec3d = new Vec3D(this.locX, this.locY, this.locZ);
/* 159 */       List<EntityLiving> list = this.world.a(EntityLiving.class, getBoundingBox().g(5.0D));
/* 160 */       Iterator<EntityLiving> iterator = list.iterator();
/*     */       
/* 162 */       while (iterator.hasNext()) {
/* 163 */         EntityLiving entityliving = iterator.next();
/*     */         
/* 165 */         if (entityliving != this.e && h(entityliving) <= 25.0D) {
/* 166 */           boolean flag = false;
/*     */           
/* 168 */           for (int i = 0; i < 2; i++) {
/* 169 */             MovingObjectPosition movingobjectposition = this.world.rayTrace(vec3d, new Vec3D(entityliving.locX, entityliving.locY + entityliving.length * 0.5D * i, entityliving.locZ), false, true, false);
/*     */             
/* 171 */             if (movingobjectposition == null || movingobjectposition.type == MovingObjectPosition.EnumMovingObjectType.MISS) {
/* 172 */               flag = true;
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/* 177 */           if (flag) {
/* 178 */             float f1 = f * (float)Math.sqrt((5.0D - g(entityliving)) / 5.0D);
/*     */             
/* 180 */             CraftEventFactory.entityDamage = this;
/* 181 */             entityliving.damageEntity(DamageSource.t, f1);
/* 182 */             CraftEventFactory.entityDamage = null;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean j() {
/* 191 */     return (((Integer)this.datawatcher.<Integer>get(b)).intValue() > 0);
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {
/* 195 */     dataconvertermanager.a(DataConverterTypes.ENTITY, new DataInspectorItem(EntityFireworks.class, new String[] { "FireworksItem" }));
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/* 199 */     nbttagcompound.setInt("Life", this.ticksFlown);
/* 200 */     nbttagcompound.setInt("LifeTime", this.expectedLifespan);
/* 201 */     ItemStack itemstack = this.datawatcher.<ItemStack>get(FIREWORK_ITEM);
/*     */     
/* 203 */     if (!itemstack.isEmpty()) {
/* 204 */       nbttagcompound.set("FireworksItem", itemstack.save(new NBTTagCompound()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/* 210 */     this.ticksFlown = nbttagcompound.getInt("Life");
/* 211 */     this.expectedLifespan = nbttagcompound.getInt("LifeTime");
/* 212 */     NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("FireworksItem");
/*     */     
/* 214 */     if (nbttagcompound1 != null) {
/* 215 */       ItemStack itemstack = new ItemStack(nbttagcompound1);
/*     */       
/* 217 */       if (!itemstack.isEmpty()) {
/* 218 */         this.datawatcher.set(FIREWORK_ITEM, itemstack);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean bd() {
/* 225 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityFireworks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */