/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Optional;
/*     */ import java.util.UUID;
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
/*     */ public abstract class EntityTameableAnimal
/*     */   extends EntityAnimal
/*     */   implements EntityOwnable
/*     */ {
/*  25 */   protected static final DataWatcherObject<Byte> bx = DataWatcher.a((Class)EntityTameableAnimal.class, DataWatcherRegistry.a);
/*  26 */   protected static final DataWatcherObject<Optional<UUID>> by = DataWatcher.a((Class)EntityTameableAnimal.class, DataWatcherRegistry.m);
/*     */   
/*     */   protected PathfinderGoalSit goalSit;
/*     */   
/*     */   public EntityTameableAnimal(World paramWorld) {
/*  31 */     super(paramWorld);
/*  32 */     dm();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void i() {
/*  37 */     super.i();
/*  38 */     this.datawatcher.register(bx, Byte.valueOf((byte)0));
/*  39 */     this.datawatcher.register(by, Optional.absent());
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(NBTTagCompound paramNBTTagCompound) {
/*  44 */     super.b(paramNBTTagCompound);
/*  45 */     if (getOwnerUUID() == null) {
/*  46 */       paramNBTTagCompound.setString("OwnerUUID", "");
/*     */     } else {
/*  48 */       paramNBTTagCompound.setString("OwnerUUID", getOwnerUUID().toString());
/*     */     } 
/*  50 */     paramNBTTagCompound.setBoolean("Sitting", isSitting());
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound paramNBTTagCompound) {
/*     */     String str;
/*  55 */     super.a(paramNBTTagCompound);
/*     */     
/*  57 */     if (paramNBTTagCompound.hasKeyOfType("OwnerUUID", 8)) {
/*  58 */       str = paramNBTTagCompound.getString("OwnerUUID");
/*     */     } else {
/*  60 */       String str1 = paramNBTTagCompound.getString("Owner");
/*  61 */       str = NameReferencingFileConverter.a(C_(), str1);
/*     */     } 
/*  63 */     if (!str.isEmpty()) {
/*     */       try {
/*  65 */         setOwnerUUID(UUID.fromString(str));
/*  66 */         setTamed(true);
/*  67 */       } catch (Throwable throwable) {
/*  68 */         setTamed(false);
/*     */       } 
/*     */     }
/*  71 */     if (this.goalSit != null) {
/*  72 */       this.goalSit.setSitting(paramNBTTagCompound.getBoolean("Sitting"));
/*     */     }
/*  74 */     setSitting(paramNBTTagCompound.getBoolean("Sitting"));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman paramEntityHuman) {
/*  79 */     return !isLeashed();
/*     */   }
/*     */   
/*     */   protected void p(boolean paramBoolean) {
/*  83 */     EnumParticle enumParticle = EnumParticle.HEART;
/*  84 */     if (!paramBoolean) {
/*  85 */       enumParticle = EnumParticle.SMOKE_NORMAL;
/*     */     }
/*  87 */     for (byte b = 0; b < 7; b++) {
/*  88 */       double d1 = this.random.nextGaussian() * 0.02D;
/*  89 */       double d2 = this.random.nextGaussian() * 0.02D;
/*  90 */       double d3 = this.random.nextGaussian() * 0.02D;
/*  91 */       this.world.addParticle(enumParticle, this.locX + (this.random.nextFloat() * this.width * 2.0F) - this.width, this.locY + 0.5D + (this.random.nextFloat() * this.length), this.locZ + (this.random.nextFloat() * this.width * 2.0F) - this.width, d1, d2, d3, new int[0]);
/*     */     } 
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
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTamed() {
/* 107 */     return ((((Byte)this.datawatcher.<Byte>get(bx)).byteValue() & 0x4) != 0);
/*     */   }
/*     */   
/*     */   public void setTamed(boolean paramBoolean) {
/* 111 */     byte b = ((Byte)this.datawatcher.<Byte>get(bx)).byteValue();
/* 112 */     if (paramBoolean) {
/* 113 */       this.datawatcher.set(bx, Byte.valueOf((byte)(b | 0x4)));
/*     */     } else {
/* 115 */       this.datawatcher.set(bx, Byte.valueOf((byte)(b & 0xFFFFFFFB)));
/*     */     } 
/*     */     
/* 118 */     dm();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void dm() {}
/*     */   
/*     */   public boolean isSitting() {
/* 125 */     return ((((Byte)this.datawatcher.<Byte>get(bx)).byteValue() & 0x1) != 0);
/*     */   }
/*     */   
/*     */   public void setSitting(boolean paramBoolean) {
/* 129 */     byte b = ((Byte)this.datawatcher.<Byte>get(bx)).byteValue();
/* 130 */     if (paramBoolean) {
/* 131 */       this.datawatcher.set(bx, Byte.valueOf((byte)(b | 0x1)));
/*     */     } else {
/* 133 */       this.datawatcher.set(bx, Byte.valueOf((byte)(b & 0xFFFFFFFE)));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public UUID getOwnerUUID() {
/* 140 */     return (UUID)((Optional)this.datawatcher.<Optional>get((DataWatcherObject)by)).orNull();
/*     */   }
/*     */   
/*     */   public void setOwnerUUID(@Nullable UUID paramUUID) {
/* 144 */     this.datawatcher.set(by, Optional.fromNullable(paramUUID));
/*     */   }
/*     */   
/*     */   public void c(EntityHuman paramEntityHuman) {
/* 148 */     setTamed(true);
/* 149 */     setOwnerUUID(paramEntityHuman.getUniqueID());
/* 150 */     if (paramEntityHuman instanceof EntityPlayer) {
/* 151 */       CriterionTriggers.w.a((EntityPlayer)paramEntityHuman, this);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public EntityLiving getOwner() {
/*     */     try {
/* 159 */       UUID uUID = getOwnerUUID();
/* 160 */       if (uUID == null) {
/* 161 */         return null;
/*     */       }
/* 163 */       return this.world.b(uUID);
/* 164 */     } catch (IllegalArgumentException illegalArgumentException) {
/* 165 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean e(EntityLiving paramEntityLiving) {
/* 170 */     return (paramEntityLiving == getOwner());
/*     */   }
/*     */   
/*     */   public PathfinderGoalSit getGoalSit() {
/* 174 */     return this.goalSit;
/*     */   }
/*     */   
/*     */   public boolean a(EntityLiving paramEntityLiving1, EntityLiving paramEntityLiving2) {
/* 178 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ScoreboardTeamBase aY() {
/* 183 */     if (isTamed()) {
/* 184 */       EntityLiving entityLiving = getOwner();
/* 185 */       if (entityLiving != null) {
/* 186 */         return entityLiving.aY();
/*     */       }
/*     */     } 
/* 189 */     return super.aY();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean r(Entity paramEntity) {
/* 194 */     if (isTamed()) {
/* 195 */       EntityLiving entityLiving = getOwner();
/* 196 */       if (paramEntity == entityLiving) {
/* 197 */         return true;
/*     */       }
/* 199 */       if (entityLiving != null) {
/* 200 */         return entityLiving.r(paramEntity);
/*     */       }
/*     */     } 
/* 203 */     return super.r(paramEntity);
/*     */   }
/*     */ 
/*     */   
/*     */   public void die(DamageSource paramDamageSource) {
/* 208 */     if (!this.world.isClientSide && this.world.getGameRules().getBoolean("showDeathMessages") && 
/* 209 */       getOwner() instanceof EntityPlayer) {
/* 210 */       getOwner().sendMessage(getCombatTracker().getDeathMessage());
/*     */     }
/*     */     
/* 213 */     super.die(paramDamageSource);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityTameableAnimal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */