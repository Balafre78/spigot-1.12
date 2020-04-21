/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.List;
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
/*     */ public class EntityWitch
/*     */   extends EntityMonster
/*     */   implements IRangedEntity
/*     */ {
/*  45 */   private static final UUID a = UUID.fromString("5CD17E52-A79A-43D3-A529-90FDE04B181E");
/*  46 */   private static final AttributeModifier b = (new AttributeModifier(a, "Drinking speed penalty", -0.25D, 0)).a(false);
/*     */   
/*  48 */   private static final DataWatcherObject<Boolean> c = DataWatcher.a((Class)EntityWitch.class, DataWatcherRegistry.h);
/*     */   
/*     */   private int bx;
/*     */   
/*     */   public EntityWitch(World paramWorld) {
/*  53 */     super(paramWorld);
/*  54 */     setSize(0.6F, 1.95F);
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager paramDataConverterManager) {
/*  58 */     EntityInsentient.a(paramDataConverterManager, EntityWitch.class);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void r() {
/*  63 */     this.goalSelector.a(1, new PathfinderGoalFloat(this));
/*  64 */     this.goalSelector.a(2, new PathfinderGoalArrowAttack(this, 1.0D, 60, 10.0F));
/*  65 */     this.goalSelector.a(2, new PathfinderGoalRandomStrollLand(this, 1.0D));
/*  66 */     this.goalSelector.a(3, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
/*  67 */     this.goalSelector.a(3, new PathfinderGoalRandomLookaround(this));
/*     */     
/*  69 */     this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, false, new Class[0]));
/*  70 */     this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void i() {
/*  75 */     super.i();
/*     */     
/*  77 */     getDataWatcher().register(c, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect F() {
/*  82 */     return SoundEffects.ix;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect d(DamageSource paramDamageSource) {
/*  87 */     return SoundEffects.iA;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect cf() {
/*  92 */     return SoundEffects.iy;
/*     */   }
/*     */   
/*     */   public void a(boolean paramBoolean) {
/*  96 */     getDataWatcher().set(c, Boolean.valueOf(paramBoolean));
/*     */   }
/*     */   
/*     */   public boolean p() {
/* 100 */     return ((Boolean)getDataWatcher().<Boolean>get(c)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initAttributes() {
/* 105 */     super.initAttributes();
/*     */     
/* 107 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(26.0D);
/* 108 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.25D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void n() {
/* 113 */     if (!this.world.isClientSide) {
/* 114 */       if (p()) {
/* 115 */         if (this.bx-- <= 0) {
/* 116 */           a(false);
/* 117 */           ItemStack itemStack = getItemInMainHand();
/* 118 */           setSlot(EnumItemSlot.MAINHAND, ItemStack.a);
/*     */           
/* 120 */           if (itemStack.getItem() == Items.POTION) {
/* 121 */             List<MobEffect> list = PotionUtil.getEffects(itemStack);
/* 122 */             if (list != null) {
/* 123 */               for (MobEffect mobEffect : list) {
/* 124 */                 addEffect(new MobEffect(mobEffect));
/*     */               }
/*     */             }
/*     */           } 
/*     */           
/* 129 */           getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).c(b);
/*     */         } 
/*     */       } else {
/* 132 */         PotionRegistry potionRegistry = null;
/*     */         
/* 134 */         if (this.random.nextFloat() < 0.15F && a(Material.WATER) && !hasEffect(MobEffects.WATER_BREATHING)) {
/* 135 */           potionRegistry = Potions.t;
/* 136 */         } else if (this.random.nextFloat() < 0.15F && (isBurning() || (ce() != null && ce().o())) && !hasEffect(MobEffects.FIRE_RESISTANCE)) {
/* 137 */           potionRegistry = Potions.m;
/* 138 */         } else if (this.random.nextFloat() < 0.05F && getHealth() < getMaxHealth()) {
/* 139 */           potionRegistry = Potions.v;
/* 140 */         } else if (this.random.nextFloat() < 0.5F && getGoalTarget() != null && !hasEffect(MobEffects.FASTER_MOVEMENT) && getGoalTarget().h(this) > 121.0D) {
/* 141 */           potionRegistry = Potions.o;
/*     */         } 
/*     */         
/* 144 */         if (potionRegistry != null) {
/* 145 */           setSlot(EnumItemSlot.MAINHAND, PotionUtil.a(new ItemStack(Items.POTION), potionRegistry));
/* 146 */           this.bx = getItemInMainHand().m();
/* 147 */           a(true);
/* 148 */           this.world.a((EntityHuman)null, this.locX, this.locY, this.locZ, SoundEffects.iz, bK(), 1.0F, 0.8F + this.random.nextFloat() * 0.4F);
/* 149 */           AttributeInstance attributeInstance = getAttributeInstance(GenericAttributes.MOVEMENT_SPEED);
/* 150 */           attributeInstance.c(b);
/* 151 */           attributeInstance.b(b);
/*     */         } 
/*     */       } 
/*     */       
/* 155 */       if (this.random.nextFloat() < 7.5E-4F) {
/* 156 */         this.world.broadcastEntityEffect(this, (byte)15);
/*     */       }
/*     */     } 
/*     */     
/* 160 */     super.n();
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
/*     */   
/*     */   protected float applyMagicModifier(DamageSource paramDamageSource, float paramFloat) {
/* 176 */     paramFloat = super.applyMagicModifier(paramDamageSource, paramFloat);
/*     */     
/* 178 */     if (paramDamageSource.getEntity() == this) {
/* 179 */       paramFloat = 0.0F;
/*     */     }
/* 181 */     if (paramDamageSource.isMagic()) {
/* 182 */       paramFloat = (float)(paramFloat * 0.15D);
/*     */     }
/*     */     
/* 185 */     return paramFloat;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/* 191 */     return LootTables.p;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(EntityLiving paramEntityLiving, float paramFloat) {
/* 196 */     if (p()) {
/*     */       return;
/*     */     }
/*     */     
/* 200 */     double d1 = paramEntityLiving.locY + paramEntityLiving.getHeadHeight() - 1.100000023841858D;
/* 201 */     double d2 = paramEntityLiving.locX + paramEntityLiving.motX - this.locX;
/* 202 */     double d3 = d1 - this.locY;
/* 203 */     double d4 = paramEntityLiving.locZ + paramEntityLiving.motZ - this.locZ;
/* 204 */     float f = MathHelper.sqrt(d2 * d2 + d4 * d4);
/*     */     
/* 206 */     PotionRegistry potionRegistry = Potions.x;
/* 207 */     if (f >= 8.0F && !paramEntityLiving.hasEffect(MobEffects.SLOWER_MOVEMENT)) {
/* 208 */       potionRegistry = Potions.r;
/* 209 */     } else if (paramEntityLiving.getHealth() >= 8.0F && !paramEntityLiving.hasEffect(MobEffects.POISON)) {
/* 210 */       potionRegistry = Potions.z;
/* 211 */     } else if (f <= 3.0F && !paramEntityLiving.hasEffect(MobEffects.WEAKNESS) && this.random.nextFloat() < 0.25F) {
/* 212 */       potionRegistry = Potions.I;
/*     */     } 
/*     */     
/* 215 */     EntityPotion entityPotion = new EntityPotion(this.world, this, PotionUtil.a(new ItemStack(Items.SPLASH_POTION), potionRegistry));
/* 216 */     entityPotion.pitch -= -20.0F;
/* 217 */     entityPotion.shoot(d2, d3 + (f * 0.2F), d4, 0.75F, 8.0F);
/* 218 */     this.world.a((EntityHuman)null, this.locX, this.locY, this.locZ, SoundEffects.iB, bK(), 1.0F, 0.8F + this.random.nextFloat() * 0.4F);
/*     */     
/* 220 */     this.world.addEntity(entityPotion);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getHeadHeight() {
/* 225 */     return 1.62F;
/*     */   }
/*     */   
/*     */   public void p(boolean paramBoolean) {}
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityWitch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */