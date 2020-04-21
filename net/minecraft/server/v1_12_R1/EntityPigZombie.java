/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityPigZombie
/*     */   extends EntityZombie
/*     */ {
/*  32 */   private static final UUID b = UUID.fromString("49455A49-7EC5-45BA-B886-3B90B23A1718");
/*  33 */   private static final AttributeModifier c = (new AttributeModifier(b, "Attacking speed boost", 0.05D, 0)).a(false);
/*     */   
/*     */   public int angerLevel;
/*     */   private int soundDelay;
/*     */   private UUID hurtBy;
/*     */   
/*     */   public EntityPigZombie(World paramWorld) {
/*  40 */     super(paramWorld);
/*  41 */     this.fireProof = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(@Nullable EntityLiving paramEntityLiving) {
/*  46 */     super.a(paramEntityLiving);
/*  47 */     if (paramEntityLiving != null) {
/*  48 */       this.hurtBy = paramEntityLiving.getUniqueID();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void do_() {
/*  54 */     this.targetSelector.a(1, new PathfinderGoalAngerOther(this));
/*  55 */     this.targetSelector.a(2, new PathfinderGoalAnger(this));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initAttributes() {
/*  60 */     super.initAttributes();
/*     */     
/*  62 */     getAttributeInstance(a).setValue(0.0D);
/*  63 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.23000000417232513D);
/*  64 */     getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(5.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void M() {
/*  69 */     AttributeInstance attributeInstance = getAttributeInstance(GenericAttributes.MOVEMENT_SPEED);
/*  70 */     if (dp()) {
/*  71 */       if (!isBaby() && !attributeInstance.a(c)) {
/*  72 */         attributeInstance.b(c);
/*     */       }
/*  74 */       this.angerLevel--;
/*  75 */     } else if (attributeInstance.a(c)) {
/*  76 */       attributeInstance.c(c);
/*     */     } 
/*     */     
/*  79 */     if (this.soundDelay > 0 && 
/*  80 */       --this.soundDelay == 0) {
/*  81 */       a(SoundEffects.jt, cq() * 2.0F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 1.8F);
/*     */     }
/*     */ 
/*     */     
/*  85 */     if (this.angerLevel > 0 && 
/*  86 */       this.hurtBy != null && getLastDamager() == null) {
/*  87 */       EntityHuman entityHuman = this.world.b(this.hurtBy);
/*  88 */       a(entityHuman);
/*  89 */       this.killer = entityHuman;
/*  90 */       this.lastDamageByPlayerTime = bT();
/*     */     } 
/*     */ 
/*     */     
/*  94 */     super.M();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean P() {
/*  99 */     return (this.world.getDifficulty() != EnumDifficulty.PEACEFUL);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawn() {
/* 104 */     return (this.world.a(getBoundingBox(), this) && this.world.getCubes(this, getBoundingBox()).isEmpty() && !this.world.containsLiquid(getBoundingBox()));
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager paramDataConverterManager) {
/* 108 */     EntityInsentient.a(paramDataConverterManager, EntityPigZombie.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(NBTTagCompound paramNBTTagCompound) {
/* 113 */     super.b(paramNBTTagCompound);
/* 114 */     paramNBTTagCompound.setShort("Anger", (short)this.angerLevel);
/* 115 */     if (this.hurtBy != null) {
/* 116 */       paramNBTTagCompound.setString("HurtBy", this.hurtBy.toString());
/*     */     } else {
/* 118 */       paramNBTTagCompound.setString("HurtBy", "");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(NBTTagCompound paramNBTTagCompound) {
/* 124 */     super.a(paramNBTTagCompound);
/* 125 */     this.angerLevel = paramNBTTagCompound.getShort("Anger");
/* 126 */     String str = paramNBTTagCompound.getString("HurtBy");
/* 127 */     if (!str.isEmpty()) {
/* 128 */       this.hurtBy = UUID.fromString(str);
/*     */       
/* 130 */       EntityHuman entityHuman = this.world.b(this.hurtBy);
/* 131 */       a(entityHuman);
/* 132 */       if (entityHuman != null) {
/* 133 */         this.killer = entityHuman;
/* 134 */         this.lastDamageByPlayerTime = bT();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource paramDamageSource, float paramFloat) {
/* 141 */     if (isInvulnerable(paramDamageSource)) {
/* 142 */       return false;
/*     */     }
/* 144 */     Entity entity = paramDamageSource.getEntity();
/* 145 */     if (entity instanceof EntityHuman) {
/* 146 */       a(entity);
/*     */     }
/* 148 */     return super.damageEntity(paramDamageSource, paramFloat);
/*     */   }
/*     */   
/*     */   private void a(Entity paramEntity) {
/* 152 */     this.angerLevel = 400 + this.random.nextInt(400);
/* 153 */     this.soundDelay = this.random.nextInt(40);
/* 154 */     if (paramEntity instanceof EntityLiving) {
/* 155 */       a((EntityLiving)paramEntity);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean dp() {
/* 160 */     return (this.angerLevel > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect F() {
/* 165 */     return SoundEffects.js;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect d(DamageSource paramDamageSource) {
/* 170 */     return SoundEffects.jv;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect cf() {
/* 175 */     return SoundEffects.ju;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/* 181 */     return LootTables.an;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman paramEntityHuman, EnumHand paramEnumHand) {
/* 186 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(DifficultyDamageScaler paramDifficultyDamageScaler) {
/* 191 */     setSlot(EnumItemSlot.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
/*     */   }
/*     */ 
/*     */   
/*     */   protected ItemStack dn() {
/* 196 */     return ItemStack.a;
/*     */   }
/*     */   
/*     */   static class PathfinderGoalAngerOther extends PathfinderGoalHurtByTarget {
/*     */     public PathfinderGoalAngerOther(EntityPigZombie param1EntityPigZombie) {
/* 201 */       super(param1EntityPigZombie, true, new Class[0]);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void a(EntityCreature param1EntityCreature, EntityLiving param1EntityLiving) {
/* 206 */       super.a(param1EntityCreature, param1EntityLiving);
/*     */       
/* 208 */       if (param1EntityCreature instanceof EntityPigZombie)
/* 209 */         EntityPigZombie.a((EntityPigZombie)param1EntityCreature, param1EntityLiving); 
/*     */     }
/*     */   }
/*     */   
/*     */   static class PathfinderGoalAnger
/*     */     extends PathfinderGoalNearestAttackableTarget<EntityHuman> {
/*     */     public PathfinderGoalAnger(EntityPigZombie param1EntityPigZombie) {
/* 216 */       super(param1EntityPigZombie, EntityHuman.class, true);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 221 */       return (((EntityPigZombie)this.e).dp() && super.a());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(EntityHuman paramEntityHuman) {
/* 227 */     return dp();
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityPigZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */