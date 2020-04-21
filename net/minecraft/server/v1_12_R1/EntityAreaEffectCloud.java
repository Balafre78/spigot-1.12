/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftLivingEntity;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.event.entity.AreaEffectCloudApplyEvent;
/*     */ 
/*     */ 
/*     */ public class EntityAreaEffectCloud
/*     */   extends Entity
/*     */ {
/*  20 */   private static final DataWatcherObject<Float> a = DataWatcher.a((Class)EntityAreaEffectCloud.class, DataWatcherRegistry.c);
/*  21 */   private static final DataWatcherObject<Integer> b = DataWatcher.a((Class)EntityAreaEffectCloud.class, DataWatcherRegistry.b);
/*  22 */   private static final DataWatcherObject<Boolean> c = DataWatcher.a((Class)EntityAreaEffectCloud.class, DataWatcherRegistry.h);
/*  23 */   private static final DataWatcherObject<Integer> d = DataWatcher.a((Class)EntityAreaEffectCloud.class, DataWatcherRegistry.b);
/*  24 */   private static final DataWatcherObject<Integer> e = DataWatcher.a((Class)EntityAreaEffectCloud.class, DataWatcherRegistry.b);
/*  25 */   private static final DataWatcherObject<Integer> f = DataWatcher.a((Class)EntityAreaEffectCloud.class, DataWatcherRegistry.b);
/*     */   private PotionRegistry potionRegistry;
/*     */   public List<MobEffect> effects;
/*     */   private final Map<Entity, Integer> at;
/*     */   private int au;
/*     */   public int waitTime;
/*     */   public int reapplicationDelay;
/*     */   private boolean hasColor;
/*     */   public int durationOnUse;
/*     */   public float radiusOnUse;
/*     */   public float radiusPerTick;
/*     */   private EntityLiving aB;
/*     */   private UUID aC;
/*     */   
/*     */   public EntityAreaEffectCloud(World world) {
/*  40 */     super(world);
/*  41 */     this.potionRegistry = Potions.EMPTY;
/*  42 */     this.effects = Lists.newArrayList();
/*  43 */     this.at = Maps.newHashMap();
/*  44 */     this.au = 600;
/*  45 */     this.waitTime = 20;
/*  46 */     this.reapplicationDelay = 20;
/*  47 */     this.noclip = true;
/*  48 */     this.fireProof = true;
/*  49 */     setRadius(3.0F);
/*     */   }
/*     */   
/*     */   public EntityAreaEffectCloud(World world, double d0, double d1, double d2) {
/*  53 */     this(world);
/*  54 */     setPosition(d0, d1, d2);
/*     */   }
/*     */   
/*     */   protected void i() {
/*  58 */     getDataWatcher().register(b, Integer.valueOf(0));
/*  59 */     getDataWatcher().register(a, Float.valueOf(0.5F));
/*  60 */     getDataWatcher().register(c, Boolean.valueOf(false));
/*  61 */     getDataWatcher().register(d, Integer.valueOf(EnumParticle.SPELL_MOB.c()));
/*  62 */     getDataWatcher().register(e, Integer.valueOf(0));
/*  63 */     getDataWatcher().register(f, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   public void setRadius(float f) {
/*  67 */     double d0 = this.locX;
/*  68 */     double d1 = this.locY;
/*  69 */     double d2 = this.locZ;
/*     */     
/*  71 */     setSize(f * 2.0F, 0.5F);
/*  72 */     setPosition(d0, d1, d2);
/*  73 */     if (!this.world.isClientSide) {
/*  74 */       getDataWatcher().set(a, Float.valueOf(f));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public float getRadius() {
/*  80 */     return ((Float)getDataWatcher().<Float>get(a)).floatValue();
/*     */   }
/*     */   
/*     */   public void a(PotionRegistry potionregistry) {
/*  84 */     this.potionRegistry = potionregistry;
/*  85 */     if (!this.hasColor) {
/*  86 */       C();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void C() {
/*  92 */     if (this.potionRegistry == Potions.EMPTY && this.effects.isEmpty()) {
/*  93 */       getDataWatcher().set(b, Integer.valueOf(0));
/*     */     } else {
/*  95 */       getDataWatcher().set(b, Integer.valueOf(PotionUtil.a(PotionUtil.a(this.potionRegistry, this.effects))));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(MobEffect mobeffect) {
/* 101 */     this.effects.add(mobeffect);
/* 102 */     if (!this.hasColor) {
/* 103 */       C();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void refreshEffects() {
/* 110 */     if (!this.hasColor) {
/* 111 */       getDataWatcher().set(b, Integer.valueOf(PotionUtil.a(PotionUtil.a(this.potionRegistry, this.effects))));
/*     */     }
/*     */   }
/*     */   
/*     */   public String getType() {
/* 116 */     return ((MinecraftKey)PotionRegistry.a.b(this.potionRegistry)).toString();
/*     */   }
/*     */   
/*     */   public void setType(String string) {
/* 120 */     a(PotionRegistry.a.get(new MinecraftKey(string)));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColor() {
/* 125 */     return ((Integer)getDataWatcher().<Integer>get(b)).intValue();
/*     */   }
/*     */   
/*     */   public void setColor(int i) {
/* 129 */     this.hasColor = true;
/* 130 */     getDataWatcher().set(b, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public EnumParticle getParticle() {
/* 134 */     return EnumParticle.a(((Integer)getDataWatcher().<Integer>get(d)).intValue());
/*     */   }
/*     */   
/*     */   public void setParticle(EnumParticle enumparticle) {
/* 138 */     getDataWatcher().set(d, Integer.valueOf(enumparticle.c()));
/*     */   }
/*     */   
/*     */   public int n() {
/* 142 */     return ((Integer)getDataWatcher().<Integer>get(e)).intValue();
/*     */   }
/*     */   
/*     */   public void c(int i) {
/* 146 */     getDataWatcher().set(e, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public int p() {
/* 150 */     return ((Integer)getDataWatcher().<Integer>get(f)).intValue();
/*     */   }
/*     */   
/*     */   public void d(int i) {
/* 154 */     getDataWatcher().set(f, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   protected void a(boolean flag) {
/* 158 */     getDataWatcher().set(c, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public boolean q() {
/* 162 */     return ((Boolean)getDataWatcher().<Boolean>get(c)).booleanValue();
/*     */   }
/*     */   
/*     */   public int getDuration() {
/* 166 */     return this.au;
/*     */   }
/*     */   
/*     */   public void setDuration(int i) {
/* 170 */     this.au = i;
/*     */   }
/*     */   
/*     */   public void B_() {
/* 174 */     super.B_();
/* 175 */     boolean flag = q();
/* 176 */     float f = getRadius();
/*     */     
/* 178 */     if (this.world.isClientSide) {
/* 179 */       EnumParticle enumparticle = getParticle();
/* 180 */       int[] aint = new int[enumparticle.d()];
/*     */       
/* 182 */       if (aint.length > 0) {
/* 183 */         aint[0] = n();
/*     */       }
/*     */       
/* 186 */       if (aint.length > 1) {
/* 187 */         aint[1] = p();
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 197 */       if (flag) {
/* 198 */         if (this.random.nextBoolean()) {
/* 199 */           for (int l = 0; l < 2; l++) {
/* 200 */             float f4 = this.random.nextFloat() * 6.2831855F;
/*     */             
/* 202 */             float f1 = MathHelper.c(this.random.nextFloat()) * 0.2F;
/* 203 */             float f2 = MathHelper.cos(f4) * f1;
/* 204 */             float f3 = MathHelper.sin(f4) * f1;
/* 205 */             if (enumparticle == EnumParticle.SPELL_MOB) {
/* 206 */               int i1 = this.random.nextBoolean() ? 16777215 : getColor();
/*     */               
/* 208 */               int i = i1 >> 16 & 0xFF;
/* 209 */               int j = i1 >> 8 & 0xFF;
/* 210 */               int k = i1 & 0xFF;
/* 211 */               this.world.a(EnumParticle.SPELL_MOB.c(), this.locX + f2, this.locY, this.locZ + f3, (i / 255.0F), (j / 255.0F), (k / 255.0F), new int[0]);
/*     */             } else {
/* 213 */               this.world.a(enumparticle.c(), this.locX + f2, this.locY, this.locZ + f3, 0.0D, 0.0D, 0.0D, aint);
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } else {
/* 218 */         float f5 = 3.1415927F * f * f;
/*     */         
/* 220 */         for (int j1 = 0; j1 < f5; j1++) {
/* 221 */           float f1 = this.random.nextFloat() * 6.2831855F;
/* 222 */           float f2 = MathHelper.c(this.random.nextFloat()) * f;
/* 223 */           float f3 = MathHelper.cos(f1) * f2;
/* 224 */           float f6 = MathHelper.sin(f1) * f2;
/*     */           
/* 226 */           if (enumparticle == EnumParticle.SPELL_MOB) {
/* 227 */             int i = getColor();
/* 228 */             int j = i >> 16 & 0xFF;
/* 229 */             int k = i >> 8 & 0xFF;
/* 230 */             int k1 = i & 0xFF;
/*     */             
/* 232 */             this.world.a(EnumParticle.SPELL_MOB.c(), this.locX + f3, this.locY, this.locZ + f6, (j / 255.0F), (k / 255.0F), (k1 / 255.0F), new int[0]);
/*     */           } else {
/* 234 */             this.world.a(enumparticle.c(), this.locX + f3, this.locY, this.locZ + f6, (0.5D - this.random.nextDouble()) * 0.15D, 0.009999999776482582D, (0.5D - this.random.nextDouble()) * 0.15D, aint);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } else {
/* 239 */       if (this.ticksLived >= this.waitTime + this.au) {
/* 240 */         die();
/*     */         
/*     */         return;
/*     */       } 
/* 244 */       boolean flag1 = (this.ticksLived < this.waitTime);
/*     */       
/* 246 */       if (flag != flag1) {
/* 247 */         a(flag1);
/*     */       }
/*     */       
/* 250 */       if (flag1) {
/*     */         return;
/*     */       }
/*     */       
/* 254 */       if (this.radiusPerTick != 0.0F) {
/* 255 */         f += this.radiusPerTick;
/* 256 */         if (f < 0.5F) {
/* 257 */           die();
/*     */           
/*     */           return;
/*     */         } 
/* 261 */         setRadius(f);
/*     */       } 
/*     */       
/* 264 */       if (this.ticksLived % 5 == 0) {
/* 265 */         Iterator<Map.Entry> iterator = this.at.entrySet().iterator();
/*     */         
/* 267 */         while (iterator.hasNext()) {
/* 268 */           Map.Entry entry = iterator.next();
/*     */           
/* 270 */           if (this.ticksLived >= ((Integer)entry.getValue()).intValue()) {
/* 271 */             iterator.remove();
/*     */           }
/*     */         } 
/*     */         
/* 275 */         ArrayList<MobEffect> arraylist = Lists.newArrayList();
/* 276 */         Iterator<MobEffect> iterator1 = this.potionRegistry.a().iterator();
/*     */         
/* 278 */         while (iterator1.hasNext()) {
/* 279 */           MobEffect mobeffect = iterator1.next();
/*     */           
/* 281 */           arraylist.add(new MobEffect(mobeffect.getMobEffect(), mobeffect.getDuration() / 4, mobeffect.getAmplifier(), mobeffect.isAmbient(), mobeffect.isShowParticles()));
/*     */         } 
/*     */         
/* 284 */         arraylist.addAll(this.effects);
/* 285 */         if (arraylist.isEmpty()) {
/* 286 */           this.at.clear();
/*     */         } else {
/* 288 */           List<EntityLiving> list = this.world.a(EntityLiving.class, getBoundingBox());
/*     */           
/* 290 */           if (!list.isEmpty()) {
/* 291 */             Iterator<EntityLiving> iterator2 = list.iterator();
/*     */             
/* 293 */             List<LivingEntity> entities = new ArrayList<>();
/* 294 */             while (iterator2.hasNext()) {
/* 295 */               EntityLiving entityliving = iterator2.next();
/*     */               
/* 297 */               if (!this.at.containsKey(entityliving) && entityliving.cR()) {
/* 298 */                 double d0 = entityliving.locX - this.locX;
/* 299 */                 double d1 = entityliving.locZ - this.locZ;
/* 300 */                 double d2 = d0 * d0 + d1 * d1;
/*     */                 
/* 302 */                 if (d2 <= (f * f))
/*     */                 {
/* 304 */                   entities.add((LivingEntity)entityliving.getBukkitEntity());
/*     */                 }
/*     */               } 
/*     */             } 
/* 308 */             AreaEffectCloudApplyEvent event = CraftEventFactory.callAreaEffectCloudApplyEvent(this, entities);
/*     */             
/* 310 */             for (LivingEntity entity : event.getAffectedEntities()) {
/* 311 */               if (entity instanceof CraftLivingEntity) {
/* 312 */                 EntityLiving entityliving = ((CraftLivingEntity)entity).getHandle();
/*     */                 
/* 314 */                 this.at.put(entityliving, Integer.valueOf(this.ticksLived + this.reapplicationDelay));
/* 315 */                 Iterator<MobEffect> iterator3 = arraylist.iterator();
/*     */                 
/* 317 */                 while (iterator3.hasNext()) {
/* 318 */                   MobEffect mobeffect1 = iterator3.next();
/*     */                   
/* 320 */                   if (mobeffect1.getMobEffect().isInstant()) {
/* 321 */                     mobeffect1.getMobEffect().applyInstantEffect(this, y(), entityliving, mobeffect1.getAmplifier(), 0.5D); continue;
/*     */                   } 
/* 323 */                   entityliving.addEffect(new MobEffect(mobeffect1));
/*     */                 } 
/*     */ 
/*     */                 
/* 327 */                 if (this.radiusOnUse != 0.0F) {
/* 328 */                   f += this.radiusOnUse;
/* 329 */                   if (f < 0.5F) {
/* 330 */                     die();
/*     */                     
/*     */                     return;
/*     */                   } 
/* 334 */                   setRadius(f);
/*     */                 } 
/*     */                 
/* 337 */                 if (this.durationOnUse != 0) {
/* 338 */                   this.au += this.durationOnUse;
/* 339 */                   if (this.au <= 0) {
/* 340 */                     die();
/*     */                     return;
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRadiusOnUse(float f) {
/* 355 */     this.radiusOnUse = f;
/*     */   }
/*     */   
/*     */   public void setRadiusPerTick(float f) {
/* 359 */     this.radiusPerTick = f;
/*     */   }
/*     */   
/*     */   public void setWaitTime(int i) {
/* 363 */     this.waitTime = i;
/*     */   }
/*     */   
/*     */   public void setSource(@Nullable EntityLiving entityliving) {
/* 367 */     this.aB = entityliving;
/* 368 */     this.aC = (entityliving == null) ? null : entityliving.getUniqueID();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public EntityLiving y() {
/* 373 */     if (this.aB == null && this.aC != null && this.world instanceof WorldServer) {
/* 374 */       Entity entity = ((WorldServer)this.world).getEntity(this.aC);
/*     */       
/* 376 */       if (entity instanceof EntityLiving) {
/* 377 */         this.aB = (EntityLiving)entity;
/*     */       }
/*     */     } 
/*     */     
/* 381 */     return this.aB;
/*     */   }
/*     */   
/*     */   protected void a(NBTTagCompound nbttagcompound) {
/* 385 */     this.ticksLived = nbttagcompound.getInt("Age");
/* 386 */     this.au = nbttagcompound.getInt("Duration");
/* 387 */     this.waitTime = nbttagcompound.getInt("WaitTime");
/* 388 */     this.reapplicationDelay = nbttagcompound.getInt("ReapplicationDelay");
/* 389 */     this.durationOnUse = nbttagcompound.getInt("DurationOnUse");
/* 390 */     this.radiusOnUse = nbttagcompound.getFloat("RadiusOnUse");
/* 391 */     this.radiusPerTick = nbttagcompound.getFloat("RadiusPerTick");
/* 392 */     setRadius(nbttagcompound.getFloat("Radius"));
/* 393 */     this.aC = nbttagcompound.a("OwnerUUID");
/* 394 */     if (nbttagcompound.hasKeyOfType("Particle", 8)) {
/* 395 */       EnumParticle enumparticle = EnumParticle.a(nbttagcompound.getString("Particle"));
/*     */       
/* 397 */       if (enumparticle != null) {
/* 398 */         setParticle(enumparticle);
/* 399 */         c(nbttagcompound.getInt("ParticleParam1"));
/* 400 */         d(nbttagcompound.getInt("ParticleParam2"));
/*     */       } 
/*     */     } 
/*     */     
/* 404 */     if (nbttagcompound.hasKeyOfType("Color", 99)) {
/* 405 */       setColor(nbttagcompound.getInt("Color"));
/*     */     }
/*     */     
/* 408 */     if (nbttagcompound.hasKeyOfType("Potion", 8)) {
/* 409 */       a(PotionUtil.c(nbttagcompound));
/*     */     }
/*     */     
/* 412 */     if (nbttagcompound.hasKeyOfType("Effects", 9)) {
/* 413 */       NBTTagList nbttaglist = nbttagcompound.getList("Effects", 10);
/*     */       
/* 415 */       this.effects.clear();
/*     */       
/* 417 */       for (int i = 0; i < nbttaglist.size(); i++) {
/* 418 */         MobEffect mobeffect = MobEffect.b(nbttaglist.get(i));
/*     */         
/* 420 */         if (mobeffect != null) {
/* 421 */           a(mobeffect);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(NBTTagCompound nbttagcompound) {
/* 429 */     nbttagcompound.setInt("Age", this.ticksLived);
/* 430 */     nbttagcompound.setInt("Duration", this.au);
/* 431 */     nbttagcompound.setInt("WaitTime", this.waitTime);
/* 432 */     nbttagcompound.setInt("ReapplicationDelay", this.reapplicationDelay);
/* 433 */     nbttagcompound.setInt("DurationOnUse", this.durationOnUse);
/* 434 */     nbttagcompound.setFloat("RadiusOnUse", this.radiusOnUse);
/* 435 */     nbttagcompound.setFloat("RadiusPerTick", this.radiusPerTick);
/* 436 */     nbttagcompound.setFloat("Radius", getRadius());
/* 437 */     nbttagcompound.setString("Particle", getParticle().b());
/* 438 */     nbttagcompound.setInt("ParticleParam1", n());
/* 439 */     nbttagcompound.setInt("ParticleParam2", p());
/* 440 */     if (this.aC != null) {
/* 441 */       nbttagcompound.a("OwnerUUID", this.aC);
/*     */     }
/*     */     
/* 444 */     if (this.hasColor) {
/* 445 */       nbttagcompound.setInt("Color", getColor());
/*     */     }
/*     */     
/* 448 */     if (this.potionRegistry != Potions.EMPTY && this.potionRegistry != null) {
/* 449 */       nbttagcompound.setString("Potion", ((MinecraftKey)PotionRegistry.a.b(this.potionRegistry)).toString());
/*     */     }
/*     */     
/* 452 */     if (!this.effects.isEmpty()) {
/* 453 */       NBTTagList nbttaglist = new NBTTagList();
/* 454 */       Iterator<MobEffect> iterator = this.effects.iterator();
/*     */       
/* 456 */       while (iterator.hasNext()) {
/* 457 */         MobEffect mobeffect = iterator.next();
/*     */         
/* 459 */         nbttaglist.add(mobeffect.a(new NBTTagCompound()));
/*     */       } 
/*     */       
/* 462 */       nbttagcompound.set("Effects", nbttaglist);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(DataWatcherObject<?> datawatcherobject) {
/* 468 */     if (a.equals(datawatcherobject)) {
/* 469 */       setRadius(getRadius());
/*     */     }
/*     */     
/* 472 */     super.a(datawatcherobject);
/*     */   }
/*     */   
/*     */   public EnumPistonReaction o_() {
/* 476 */     return EnumPistonReaction.IGNORE;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityAreaEffectCloud.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */