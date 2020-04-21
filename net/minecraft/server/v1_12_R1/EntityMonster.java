/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityCombustByEntityEvent;
/*     */ 
/*     */ public abstract class EntityMonster extends EntityCreature implements IMonster {
/*     */   public EntityMonster(World world) {
/*   8 */     super(world);
/*   9 */     this.b_ = 5;
/*     */   }
/*     */   
/*     */   public SoundCategory bK() {
/*  13 */     return SoundCategory.HOSTILE;
/*     */   }
/*     */   
/*     */   public void n() {
/*  17 */     cl();
/*  18 */     float f = aw();
/*     */     
/*  20 */     if (f > 0.5F) {
/*  21 */       this.ticksFarFromPlayer += 2;
/*     */     }
/*     */     
/*  24 */     super.n();
/*     */   }
/*     */   
/*     */   public void B_() {
/*  28 */     super.B_();
/*  29 */     if (!this.world.isClientSide && this.world.getDifficulty() == EnumDifficulty.PEACEFUL) {
/*  30 */       die();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect ae() {
/*  36 */     return SoundEffects.cX;
/*     */   }
/*     */   
/*     */   protected SoundEffect af() {
/*  40 */     return SoundEffects.cW;
/*     */   }
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/*  44 */     return isInvulnerable(damagesource) ? false : super.damageEntity(damagesource, f);
/*     */   }
/*     */   
/*     */   protected SoundEffect d(DamageSource damagesource) {
/*  48 */     return SoundEffects.cU;
/*     */   }
/*     */   
/*     */   protected SoundEffect cf() {
/*  52 */     return SoundEffects.cT;
/*     */   }
/*     */   
/*     */   protected SoundEffect e(int i) {
/*  56 */     return (i > 4) ? SoundEffects.cS : SoundEffects.cV;
/*     */   }
/*     */   
/*     */   public boolean B(Entity entity) {
/*  60 */     float f = (float)getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).getValue();
/*  61 */     int i = 0;
/*     */     
/*  63 */     if (entity instanceof EntityLiving) {
/*  64 */       f += EnchantmentManager.a(getItemInMainHand(), ((EntityLiving)entity).getMonsterType());
/*  65 */       i += EnchantmentManager.b(this);
/*     */     } 
/*     */     
/*  68 */     boolean flag = entity.damageEntity(DamageSource.mobAttack(this), f);
/*     */     
/*  70 */     if (flag) {
/*  71 */       if (i > 0 && entity instanceof EntityLiving) {
/*  72 */         ((EntityLiving)entity).a(this, i * 0.5F, MathHelper.sin(this.yaw * 0.017453292F), -MathHelper.cos(this.yaw * 0.017453292F));
/*  73 */         this.motX *= 0.6D;
/*  74 */         this.motZ *= 0.6D;
/*     */       } 
/*     */       
/*  77 */       int j = EnchantmentManager.getFireAspectEnchantmentLevel(this);
/*     */       
/*  79 */       if (j > 0) {
/*     */         
/*  81 */         EntityCombustByEntityEvent combustEvent = new EntityCombustByEntityEvent((Entity)getBukkitEntity(), (Entity)entity.getBukkitEntity(), j * 4);
/*  82 */         Bukkit.getPluginManager().callEvent((Event)combustEvent);
/*     */         
/*  84 */         if (!combustEvent.isCancelled()) {
/*  85 */           entity.setOnFire(combustEvent.getDuration());
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*  90 */       if (entity instanceof EntityHuman) {
/*  91 */         EntityHuman entityhuman = (EntityHuman)entity;
/*  92 */         ItemStack itemstack = getItemInMainHand();
/*  93 */         ItemStack itemstack1 = entityhuman.isHandRaised() ? entityhuman.cJ() : ItemStack.a;
/*     */         
/*  95 */         if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.getItem() instanceof ItemAxe && itemstack1.getItem() == Items.SHIELD) {
/*  96 */           float f1 = 0.25F + EnchantmentManager.getDigSpeedEnchantmentLevel(this) * 0.05F;
/*     */           
/*  98 */           if (this.random.nextFloat() < f1) {
/*  99 */             entityhuman.getCooldownTracker().a(Items.SHIELD, 100);
/* 100 */             this.world.broadcastEntityEffect(entityhuman, (byte)30);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 105 */       a(this, entity);
/*     */     } 
/*     */     
/* 108 */     return flag;
/*     */   }
/*     */   
/*     */   public float a(BlockPosition blockposition) {
/* 112 */     return 0.5F - this.world.n(blockposition);
/*     */   }
/*     */   
/*     */   protected boolean s_() {
/* 116 */     BlockPosition blockposition = new BlockPosition(this.locX, (getBoundingBox()).b, this.locZ);
/*     */     
/* 118 */     if (this.world.getBrightness(EnumSkyBlock.SKY, blockposition) > this.random.nextInt(32)) {
/* 119 */       return false;
/*     */     }
/* 121 */     int i = this.world.getLightLevel(blockposition);
/*     */     
/* 123 */     if (this.world.X()) {
/* 124 */       int j = this.world.ah();
/*     */       
/* 126 */       this.world.c(10);
/* 127 */       i = this.world.getLightLevel(blockposition);
/* 128 */       this.world.c(j);
/*     */     } 
/*     */     
/* 131 */     return (i <= this.random.nextInt(8));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean P() {
/* 136 */     return (this.world.getDifficulty() != EnumDifficulty.PEACEFUL && s_() && super.P());
/*     */   }
/*     */   
/*     */   protected void initAttributes() {
/* 140 */     super.initAttributes();
/* 141 */     getAttributeMap().b(GenericAttributes.ATTACK_DAMAGE);
/*     */   }
/*     */   
/*     */   protected boolean isDropExperience() {
/* 145 */     return true;
/*     */   }
/*     */   
/*     */   public boolean c(EntityHuman entityhuman) {
/* 149 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityMonster.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */