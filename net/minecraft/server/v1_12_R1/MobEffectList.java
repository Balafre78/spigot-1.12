/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.potion.CraftPotionEffectType;
/*     */ import org.bukkit.event.entity.EntityRegainHealthEvent;
/*     */ import org.bukkit.event.entity.FoodLevelChangeEvent;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ 
/*     */ public class MobEffectList
/*     */ {
/*  17 */   public static final RegistryMaterials<MinecraftKey, MobEffectList> REGISTRY = new RegistryMaterials<>();
/*  18 */   private final Map<IAttribute, AttributeModifier> a = Maps.newHashMap();
/*     */   private final boolean c;
/*     */   private final int d;
/*  21 */   private String e = "";
/*  22 */   private int f = -1;
/*     */   public double durationModifier;
/*     */   private boolean h;
/*     */   
/*     */   @Nullable
/*     */   public static MobEffectList fromId(int i) {
/*  28 */     return REGISTRY.getId(i);
/*     */   }
/*     */   
/*     */   public static int getId(MobEffectList mobeffectlist) {
/*  32 */     return REGISTRY.a(mobeffectlist);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static MobEffectList getByName(String s) {
/*  37 */     return REGISTRY.get(new MinecraftKey(s));
/*     */   }
/*     */   
/*     */   protected MobEffectList(boolean flag, int i) {
/*  41 */     this.c = flag;
/*  42 */     if (flag) {
/*  43 */       this.durationModifier = 0.5D;
/*     */     } else {
/*  45 */       this.durationModifier = 1.0D;
/*     */     } 
/*     */     
/*  48 */     this.d = i;
/*     */   }
/*     */   
/*     */   protected MobEffectList b(int i, int j) {
/*  52 */     this.f = i + j * 8;
/*  53 */     return this;
/*     */   }
/*     */   
/*     */   public void tick(EntityLiving entityliving, int i) {
/*  57 */     if (this == MobEffects.REGENERATION) {
/*  58 */       if (entityliving.getHealth() < entityliving.getMaxHealth()) {
/*  59 */         entityliving.heal(1.0F, EntityRegainHealthEvent.RegainReason.MAGIC_REGEN);
/*     */       }
/*  61 */     } else if (this == MobEffects.POISON) {
/*  62 */       if (entityliving.getHealth() > 1.0F) {
/*  63 */         entityliving.damageEntity(CraftEventFactory.POISON, 1.0F);
/*     */       }
/*  65 */     } else if (this == MobEffects.WITHER) {
/*  66 */       entityliving.damageEntity(DamageSource.WITHER, 1.0F);
/*  67 */     } else if (this == MobEffects.HUNGER && entityliving instanceof EntityHuman) {
/*  68 */       ((EntityHuman)entityliving).applyExhaustion(0.005F * (i + 1));
/*  69 */     } else if (this == MobEffects.SATURATION && entityliving instanceof EntityHuman) {
/*  70 */       if (!entityliving.world.isClientSide)
/*     */       {
/*  72 */         EntityHuman entityhuman = (EntityHuman)entityliving;
/*  73 */         int oldFoodLevel = (entityhuman.getFoodData()).foodLevel;
/*     */         
/*  75 */         FoodLevelChangeEvent event = CraftEventFactory.callFoodLevelChangeEvent(entityhuman, i + 1 + oldFoodLevel);
/*     */         
/*  77 */         if (!event.isCancelled()) {
/*  78 */           entityhuman.getFoodData().eat(event.getFoodLevel() - oldFoodLevel, 1.0F);
/*     */         }
/*     */         
/*  81 */         ((EntityPlayer)entityhuman).playerConnection.sendPacket(new PacketPlayOutUpdateHealth(((EntityPlayer)entityhuman).getBukkitEntity().getScaledHealth(), (entityhuman.getFoodData()).foodLevel, (entityhuman.getFoodData()).saturationLevel));
/*     */       }
/*     */     
/*  84 */     } else if ((this != MobEffects.HEAL || entityliving.cc()) && (this != MobEffects.HARM || !entityliving.cc())) {
/*  85 */       if ((this == MobEffects.HARM && !entityliving.cc()) || (this == MobEffects.HEAL && entityliving.cc())) {
/*  86 */         entityliving.damageEntity(DamageSource.MAGIC, (6 << i));
/*     */       }
/*     */     } else {
/*  89 */       entityliving.heal(Math.max(4 << i, 0), EntityRegainHealthEvent.RegainReason.MAGIC);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void applyInstantEffect(@Nullable Entity entity, @Nullable Entity entity1, EntityLiving entityliving, int i, double d0) {
/*  97 */     if ((this != MobEffects.HEAL || entityliving.cc()) && (this != MobEffects.HARM || !entityliving.cc())) {
/*  98 */       if ((this == MobEffects.HARM && !entityliving.cc()) || (this == MobEffects.HEAL && entityliving.cc())) {
/*  99 */         int j = (int)(d0 * (6 << i) + 0.5D);
/* 100 */         if (entity == null) {
/* 101 */           entityliving.damageEntity(DamageSource.MAGIC, j);
/*     */         } else {
/* 103 */           entityliving.damageEntity(DamageSource.b(entity, entity1), j);
/*     */         } 
/*     */       } 
/*     */     } else {
/* 107 */       int j = (int)(d0 * (4 << i) + 0.5D);
/* 108 */       entityliving.heal(j, EntityRegainHealthEvent.RegainReason.MAGIC);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(int i, int j) {
/* 116 */     if (this == MobEffects.REGENERATION) {
/* 117 */       int k = 50 >> j;
/* 118 */       return (k > 0) ? ((i % k == 0)) : true;
/* 119 */     }  if (this == MobEffects.POISON) {
/* 120 */       int k = 25 >> j;
/* 121 */       return (k > 0) ? ((i % k == 0)) : true;
/* 122 */     }  if (this == MobEffects.WITHER) {
/* 123 */       int k = 40 >> j;
/* 124 */       return (k > 0) ? ((i % k == 0)) : true;
/*     */     } 
/* 126 */     return (this == MobEffects.HUNGER);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInstant() {
/* 131 */     return false;
/*     */   }
/*     */   
/*     */   public MobEffectList c(String s) {
/* 135 */     this.e = s;
/* 136 */     return this;
/*     */   }
/*     */   
/*     */   public String a() {
/* 140 */     return this.e;
/*     */   }
/*     */   
/*     */   protected MobEffectList a(double d0) {
/* 144 */     this.durationModifier = d0;
/* 145 */     return this;
/*     */   }
/*     */   
/*     */   public int getColor() {
/* 149 */     return this.d;
/*     */   }
/*     */   
/*     */   public MobEffectList a(IAttribute iattribute, String s, double d0, int i) {
/* 153 */     AttributeModifier attributemodifier = new AttributeModifier(UUID.fromString(s), a(), d0, i);
/*     */     
/* 155 */     this.a.put(iattribute, attributemodifier);
/* 156 */     return this;
/*     */   }
/*     */   
/*     */   public void a(EntityLiving entityliving, AttributeMapBase attributemapbase, int i) {
/* 160 */     Iterator<Map.Entry> iterator = this.a.entrySet().iterator();
/*     */     
/* 162 */     while (iterator.hasNext()) {
/* 163 */       Map.Entry entry = iterator.next();
/* 164 */       AttributeInstance attributeinstance = attributemapbase.a((IAttribute)entry.getKey());
/*     */       
/* 166 */       if (attributeinstance != null) {
/* 167 */         attributeinstance.c((AttributeModifier)entry.getValue());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(EntityLiving entityliving, AttributeMapBase attributemapbase, int i) {
/* 174 */     Iterator<Map.Entry> iterator = this.a.entrySet().iterator();
/*     */     
/* 176 */     while (iterator.hasNext()) {
/* 177 */       Map.Entry entry = iterator.next();
/* 178 */       AttributeInstance attributeinstance = attributemapbase.a((IAttribute)entry.getKey());
/*     */       
/* 180 */       if (attributeinstance != null) {
/* 181 */         AttributeModifier attributemodifier = (AttributeModifier)entry.getValue();
/*     */         
/* 183 */         attributeinstance.c(attributemodifier);
/* 184 */         attributeinstance.b(new AttributeModifier(attributemodifier.a(), String.valueOf(a()) + " " + i, a(i, attributemodifier), attributemodifier.c()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public double a(int i, AttributeModifier attributemodifier) {
/* 191 */     return attributemodifier.d() * (i + 1);
/*     */   }
/*     */   
/*     */   public MobEffectList j() {
/* 195 */     this.h = true;
/* 196 */     return this;
/*     */   }
/*     */   
/*     */   public static void k() {
/* 200 */     REGISTRY.a(1, new MinecraftKey("speed"), (new MobEffectList(false, 8171462)).c("effect.moveSpeed").b(0, 0).a(GenericAttributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070635", 0.20000000298023224D, 2).j());
/* 201 */     REGISTRY.a(2, new MinecraftKey("slowness"), (new MobEffectList(true, 5926017)).c("effect.moveSlowdown").b(1, 0).a(GenericAttributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.15000000596046448D, 2));
/* 202 */     REGISTRY.a(3, new MinecraftKey("haste"), (new MobEffectList(false, 14270531)).c("effect.digSpeed").b(2, 0).a(1.5D).j().a(GenericAttributes.g, "AF8B6E3F-3328-4C0A-AA36-5BA2BB9DBEF3", 0.10000000149011612D, 2));
/* 203 */     REGISTRY.a(4, new MinecraftKey("mining_fatigue"), (new MobEffectList(true, 4866583)).c("effect.digSlowDown").b(3, 0).a(GenericAttributes.g, "55FCED67-E92A-486E-9800-B47F202C4386", -0.10000000149011612D, 2));
/* 204 */     REGISTRY.a(5, new MinecraftKey("strength"), (new MobEffectAttackDamage(false, 9643043, 3.0D)).c("effect.damageBoost").b(4, 0).a(GenericAttributes.ATTACK_DAMAGE, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 0.0D, 0).j());
/* 205 */     REGISTRY.a(6, new MinecraftKey("instant_health"), (new InstantMobEffect(false, 16262179)).c("effect.heal").j());
/* 206 */     REGISTRY.a(7, new MinecraftKey("instant_damage"), (new InstantMobEffect(true, 4393481)).c("effect.harm").j());
/* 207 */     REGISTRY.a(8, new MinecraftKey("jump_boost"), (new MobEffectList(false, 2293580)).c("effect.jump").b(2, 1).j());
/* 208 */     REGISTRY.a(9, new MinecraftKey("nausea"), (new MobEffectList(true, 5578058)).c("effect.confusion").b(3, 1).a(0.25D));
/* 209 */     REGISTRY.a(10, new MinecraftKey("regeneration"), (new MobEffectList(false, 13458603)).c("effect.regeneration").b(7, 0).a(0.25D).j());
/* 210 */     REGISTRY.a(11, new MinecraftKey("resistance"), (new MobEffectList(false, 10044730)).c("effect.resistance").b(6, 1).j());
/* 211 */     REGISTRY.a(12, new MinecraftKey("fire_resistance"), (new MobEffectList(false, 14981690)).c("effect.fireResistance").b(7, 1).j());
/* 212 */     REGISTRY.a(13, new MinecraftKey("water_breathing"), (new MobEffectList(false, 3035801)).c("effect.waterBreathing").b(0, 2).j());
/* 213 */     REGISTRY.a(14, new MinecraftKey("invisibility"), (new MobEffectList(false, 8356754)).c("effect.invisibility").b(0, 1).j());
/* 214 */     REGISTRY.a(15, new MinecraftKey("blindness"), (new MobEffectList(true, 2039587)).c("effect.blindness").b(5, 1).a(0.25D));
/* 215 */     REGISTRY.a(16, new MinecraftKey("night_vision"), (new MobEffectList(false, 2039713)).c("effect.nightVision").b(4, 1).j());
/* 216 */     REGISTRY.a(17, new MinecraftKey("hunger"), (new MobEffectList(true, 5797459)).c("effect.hunger").b(1, 1));
/* 217 */     REGISTRY.a(18, new MinecraftKey("weakness"), (new MobEffectAttackDamage(true, 4738376, -4.0D)).c("effect.weakness").b(5, 0).a(GenericAttributes.ATTACK_DAMAGE, "22653B89-116E-49DC-9B6B-9971489B5BE5", 0.0D, 0));
/* 218 */     REGISTRY.a(19, new MinecraftKey("poison"), (new MobEffectList(true, 5149489)).c("effect.poison").b(6, 0).a(0.25D));
/* 219 */     REGISTRY.a(20, new MinecraftKey("wither"), (new MobEffectList(true, 3484199)).c("effect.wither").b(1, 2).a(0.25D));
/* 220 */     REGISTRY.a(21, new MinecraftKey("health_boost"), (new MobEffectHealthBoost(false, 16284963)).c("effect.healthBoost").b(7, 2).a(GenericAttributes.maxHealth, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", 4.0D, 0).j());
/* 221 */     REGISTRY.a(22, new MinecraftKey("absorption"), (new MobEffectAbsorption(false, 2445989)).c("effect.absorption").b(2, 2).j());
/* 222 */     REGISTRY.a(23, new MinecraftKey("saturation"), (new InstantMobEffect(false, 16262179)).c("effect.saturation").j());
/* 223 */     REGISTRY.a(24, new MinecraftKey("glowing"), (new MobEffectList(false, 9740385)).c("effect.glowing").b(4, 2));
/* 224 */     REGISTRY.a(25, new MinecraftKey("levitation"), (new MobEffectList(true, 13565951)).c("effect.levitation").b(3, 2));
/* 225 */     REGISTRY.a(26, new MinecraftKey("luck"), (new MobEffectList(false, 3381504)).c("effect.luck").b(5, 2).j().a(GenericAttributes.j, "03C3C89D-7037-4B42-869F-B146BCB64D2E", 1.0D, 0));
/* 226 */     REGISTRY.a(27, new MinecraftKey("unluck"), (new MobEffectList(true, 12624973)).c("effect.unluck").b(6, 2).a(GenericAttributes.j, "CC5AF142-2BD2-4215-B636-2605AED11727", -1.0D, 0));
/*     */     
/* 228 */     for (Object effect : REGISTRY)
/* 229 */       PotionEffectType.registerPotionEffectType((PotionEffectType)new CraftPotionEffectType((MobEffectList)effect)); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\MobEffectList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */