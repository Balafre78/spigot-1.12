/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class DamageSource
/*     */ {
/*   7 */   public static final DamageSource FIRE = (new DamageSource("inFire")).setExplosion();
/*   8 */   public static final DamageSource LIGHTNING = new DamageSource("lightningBolt");
/*   9 */   public static final DamageSource BURN = (new DamageSource("onFire")).setIgnoreArmor().setExplosion();
/*  10 */   public static final DamageSource LAVA = (new DamageSource("lava")).setExplosion();
/*  11 */   public static final DamageSource HOT_FLOOR = (new DamageSource("hotFloor")).setExplosion();
/*  12 */   public static final DamageSource STUCK = (new DamageSource("inWall")).setIgnoreArmor();
/*  13 */   public static final DamageSource CRAMMING = (new DamageSource("cramming")).setIgnoreArmor();
/*  14 */   public static final DamageSource DROWN = (new DamageSource("drown")).setIgnoreArmor();
/*  15 */   public static final DamageSource STARVE = (new DamageSource("starve")).setIgnoreArmor().m();
/*  16 */   public static final DamageSource CACTUS = new DamageSource("cactus");
/*  17 */   public static final DamageSource FALL = (new DamageSource("fall")).setIgnoreArmor();
/*  18 */   public static final DamageSource FLY_INTO_WALL = (new DamageSource("flyIntoWall")).setIgnoreArmor();
/*  19 */   public static final DamageSource OUT_OF_WORLD = (new DamageSource("outOfWorld")).setIgnoreArmor().l();
/*  20 */   public static final DamageSource GENERIC = (new DamageSource("generic")).setIgnoreArmor();
/*  21 */   public static final DamageSource MAGIC = (new DamageSource("magic")).setIgnoreArmor().setMagic();
/*  22 */   public static final DamageSource WITHER = (new DamageSource("wither")).setIgnoreArmor();
/*  23 */   public static final DamageSource ANVIL = new DamageSource("anvil");
/*  24 */   public static final DamageSource FALLING_BLOCK = new DamageSource("fallingBlock");
/*  25 */   public static final DamageSource DRAGON_BREATH = (new DamageSource("dragonBreath")).setIgnoreArmor();
/*  26 */   public static final DamageSource t = (new DamageSource("fireworks")).d();
/*     */   private boolean v;
/*     */   private boolean w;
/*     */   private boolean x;
/*  30 */   private float y = 0.1F;
/*     */   
/*     */   private boolean z;
/*     */   private boolean A;
/*     */   private boolean B;
/*     */   private boolean C;
/*     */   private boolean D;
/*     */   public String translationIndex;
/*     */   private boolean sweep;
/*     */   
/*     */   public boolean isSweep() {
/*  41 */     return this.sweep;
/*     */   }
/*     */   
/*     */   public DamageSource sweep() {
/*  45 */     this.sweep = true;
/*  46 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public static DamageSource mobAttack(EntityLiving entityliving) {
/*  51 */     return new EntityDamageSource("mob", entityliving);
/*     */   }
/*     */   
/*     */   public static DamageSource a(Entity entity, EntityLiving entityliving) {
/*  55 */     return new EntityDamageSourceIndirect("mob", entity, entityliving);
/*     */   }
/*     */   
/*     */   public static DamageSource playerAttack(EntityHuman entityhuman) {
/*  59 */     return new EntityDamageSource("player", entityhuman);
/*     */   }
/*     */   
/*     */   public static DamageSource arrow(EntityArrow entityarrow, @Nullable Entity entity) {
/*  63 */     return (new EntityDamageSourceIndirect("arrow", entityarrow, entity)).b();
/*     */   }
/*     */   
/*     */   public static DamageSource fireball(EntityFireball entityfireball, @Nullable Entity entity) {
/*  67 */     return (entity == null) ? (new EntityDamageSourceIndirect("onFire", entityfireball, entityfireball)).setExplosion().b() : (new EntityDamageSourceIndirect("fireball", entityfireball, entity)).setExplosion().b();
/*     */   }
/*     */   
/*     */   public static DamageSource projectile(Entity entity, @Nullable Entity entity1) {
/*  71 */     return (new EntityDamageSourceIndirect("thrown", entity, entity1)).b();
/*     */   }
/*     */   
/*     */   public static DamageSource b(Entity entity, @Nullable Entity entity1) {
/*  75 */     return (new EntityDamageSourceIndirect("indirectMagic", entity, entity1)).setIgnoreArmor().setMagic();
/*     */   }
/*     */   
/*     */   public static DamageSource a(Entity entity) {
/*  79 */     return (new EntityDamageSource("thorns", entity)).w().setMagic();
/*     */   }
/*     */   
/*     */   public static DamageSource explosion(@Nullable Explosion explosion) {
/*  83 */     return (explosion != null && explosion.getSource() != null) ? (new EntityDamageSource("explosion.player", explosion.getSource())).q().d() : (new DamageSource("explosion")).q().d();
/*     */   }
/*     */   
/*     */   public static DamageSource b(@Nullable EntityLiving entityliving) {
/*  87 */     return (entityliving != null) ? (new EntityDamageSource("explosion.player", entityliving)).q().d() : (new DamageSource("explosion")).q().d();
/*     */   }
/*     */   
/*     */   public boolean a() {
/*  91 */     return this.A;
/*     */   }
/*     */   
/*     */   public DamageSource b() {
/*  95 */     this.A = true;
/*  96 */     return this;
/*     */   }
/*     */   
/*     */   public boolean isExplosion() {
/* 100 */     return this.D;
/*     */   }
/*     */   
/*     */   public DamageSource d() {
/* 104 */     this.D = true;
/* 105 */     return this;
/*     */   }
/*     */   
/*     */   public boolean ignoresArmor() {
/* 109 */     return this.v;
/*     */   }
/*     */   
/*     */   public float getExhaustionCost() {
/* 113 */     return this.y;
/*     */   }
/*     */   
/*     */   public boolean ignoresInvulnerability() {
/* 117 */     return this.w;
/*     */   }
/*     */   
/*     */   public boolean isStarvation() {
/* 121 */     return this.x;
/*     */   }
/*     */   
/*     */   protected DamageSource(String s) {
/* 125 */     this.translationIndex = s;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Entity i() {
/* 130 */     return getEntity();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Entity getEntity() {
/* 135 */     return null;
/*     */   }
/*     */   
/*     */   protected DamageSource setIgnoreArmor() {
/* 139 */     this.v = true;
/* 140 */     this.y = 0.0F;
/* 141 */     return this;
/*     */   }
/*     */   
/*     */   protected DamageSource l() {
/* 145 */     this.w = true;
/* 146 */     return this;
/*     */   }
/*     */   
/*     */   protected DamageSource m() {
/* 150 */     this.x = true;
/* 151 */     this.y = 0.0F;
/* 152 */     return this;
/*     */   }
/*     */   
/*     */   protected DamageSource setExplosion() {
/* 156 */     this.z = true;
/* 157 */     return this;
/*     */   }
/*     */   
/*     */   public IChatBaseComponent getLocalizedDeathMessage(EntityLiving entityliving) {
/* 161 */     EntityLiving entityliving1 = entityliving.ci();
/* 162 */     String s = "death.attack." + this.translationIndex;
/* 163 */     String s1 = String.valueOf(s) + ".player";
/*     */     
/* 165 */     return (entityliving1 != null && LocaleI18n.c(s1)) ? new ChatMessage(s1, new Object[] { entityliving.getScoreboardDisplayName(), entityliving1.getScoreboardDisplayName() }) : new ChatMessage(s, new Object[] { entityliving.getScoreboardDisplayName() });
/*     */   }
/*     */   
/*     */   public boolean o() {
/* 169 */     return this.z;
/*     */   }
/*     */   
/*     */   public String p() {
/* 173 */     return this.translationIndex;
/*     */   }
/*     */   
/*     */   public DamageSource q() {
/* 177 */     this.B = true;
/* 178 */     return this;
/*     */   }
/*     */   
/*     */   public boolean r() {
/* 182 */     return this.B;
/*     */   }
/*     */   
/*     */   public boolean isMagic() {
/* 186 */     return this.C;
/*     */   }
/*     */   
/*     */   public DamageSource setMagic() {
/* 190 */     this.C = true;
/* 191 */     return this;
/*     */   }
/*     */   
/*     */   public boolean u() {
/* 195 */     Entity entity = getEntity();
/*     */     
/* 197 */     return (entity instanceof EntityHuman && ((EntityHuman)entity).abilities.canInstantlyBuild);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Vec3D v() {
/* 202 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DamageSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */