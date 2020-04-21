/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.player.PlayerShearEntityEvent;
/*     */ 
/*     */ public class EntitySnowman extends EntityGolem implements IRangedEntity {
/*  11 */   private static final DataWatcherObject<Byte> a = DataWatcher.a((Class)EntitySnowman.class, DataWatcherRegistry.a);
/*     */   
/*     */   public EntitySnowman(World world) {
/*  14 */     super(world);
/*  15 */     setSize(0.7F, 1.9F);
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {
/*  19 */     EntityInsentient.a(dataconvertermanager, EntitySnowman.class);
/*     */   }
/*     */   
/*     */   protected void r() {
/*  23 */     this.goalSelector.a(1, new PathfinderGoalArrowAttack(this, 1.25D, 20, 10.0F));
/*  24 */     this.goalSelector.a(2, new PathfinderGoalRandomStrollLand(this, 1.0D, 1.0000001E-5F));
/*  25 */     this.goalSelector.a(3, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 6.0F));
/*  26 */     this.goalSelector.a(4, new PathfinderGoalRandomLookaround(this));
/*  27 */     this.targetSelector.a(1, new PathfinderGoalNearestAttackableTarget<>(this, (Class)EntityInsentient.class, 10, true, false, IMonster.d));
/*     */   }
/*     */   
/*     */   protected void initAttributes() {
/*  31 */     super.initAttributes();
/*  32 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(4.0D);
/*  33 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.20000000298023224D);
/*     */   }
/*     */   
/*     */   protected void i() {
/*  37 */     super.i();
/*  38 */     this.datawatcher.register(a, Byte.valueOf((byte)16));
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/*  42 */     super.b(nbttagcompound);
/*  43 */     nbttagcompound.setBoolean("Pumpkin", hasPumpkin());
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/*  47 */     super.a(nbttagcompound);
/*  48 */     if (nbttagcompound.hasKey("Pumpkin")) {
/*  49 */       setHasPumpkin(nbttagcompound.getBoolean("Pumpkin"));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void n() {
/*  55 */     super.n();
/*  56 */     if (!this.world.isClientSide) {
/*  57 */       int i = MathHelper.floor(this.locX);
/*  58 */       int j = MathHelper.floor(this.locY);
/*  59 */       int k = MathHelper.floor(this.locZ);
/*     */       
/*  61 */       if (an()) {
/*  62 */         damageEntity(DamageSource.DROWN, 1.0F);
/*     */       }
/*     */       
/*  65 */       if (this.world.getBiome(new BlockPosition(i, 0, k)).a(new BlockPosition(i, j, k)) > 1.0F) {
/*  66 */         damageEntity(CraftEventFactory.MELTING, 1.0F);
/*     */       }
/*     */       
/*  69 */       if (!this.world.getGameRules().getBoolean("mobGriefing")) {
/*     */         return;
/*     */       }
/*     */       
/*  73 */       for (int l = 0; l < 4; l++) {
/*  74 */         i = MathHelper.floor(this.locX + ((l % 2 * 2 - 1) * 0.25F));
/*  75 */         j = MathHelper.floor(this.locY);
/*  76 */         k = MathHelper.floor(this.locZ + ((l / 2 % 2 * 2 - 1) * 0.25F));
/*  77 */         BlockPosition blockposition = new BlockPosition(i, j, k);
/*     */         
/*  79 */         if (this.world.getType(blockposition).getMaterial() == Material.AIR && this.world.getBiome(blockposition).a(blockposition) < 0.8F && Blocks.SNOW_LAYER.canPlace(this.world, blockposition)) {
/*  80 */           CraftEventFactory.handleBlockFormEvent(this.world, blockposition, Blocks.SNOW_LAYER.getBlockData(), this);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/*  89 */     return LootTables.B;
/*     */   }
/*     */   
/*     */   public void a(EntityLiving entityliving, float f) {
/*  93 */     EntitySnowball entitysnowball = new EntitySnowball(this.world, this);
/*  94 */     double d0 = entityliving.locY + entityliving.getHeadHeight() - 1.100000023841858D;
/*  95 */     double d1 = entityliving.locX - this.locX;
/*  96 */     double d2 = d0 - entitysnowball.locY;
/*  97 */     double d3 = entityliving.locZ - this.locZ;
/*  98 */     float f1 = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
/*     */     
/* 100 */     entitysnowball.shoot(d1, d2 + f1, d3, 1.6F, 12.0F);
/* 101 */     a(SoundEffects.ht, 1.0F, 1.0F / (getRandom().nextFloat() * 0.4F + 0.8F));
/* 102 */     this.world.addEntity(entitysnowball);
/*     */   }
/*     */   
/*     */   public float getHeadHeight() {
/* 106 */     return 1.7F;
/*     */   }
/*     */   
/*     */   protected boolean a(EntityHuman entityhuman, EnumHand enumhand) {
/* 110 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/* 112 */     if (itemstack.getItem() == Items.SHEARS && hasPumpkin() && !this.world.isClientSide) {
/*     */       
/* 114 */       PlayerShearEntityEvent event = new PlayerShearEntityEvent((Player)entityhuman.getBukkitEntity(), (Entity)getBukkitEntity());
/* 115 */       this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */       
/* 117 */       if (event.isCancelled()) {
/* 118 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 122 */       setHasPumpkin(false);
/* 123 */       itemstack.damage(1, entityhuman);
/*     */     } 
/*     */     
/* 126 */     return super.a(entityhuman, enumhand);
/*     */   }
/*     */   
/*     */   public boolean hasPumpkin() {
/* 130 */     return ((((Byte)this.datawatcher.<Byte>get(a)).byteValue() & 0x10) != 0);
/*     */   }
/*     */   
/*     */   public void setHasPumpkin(boolean flag) {
/* 134 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(a)).byteValue();
/*     */     
/* 136 */     if (flag) {
/* 137 */       this.datawatcher.set(a, Byte.valueOf((byte)(b0 | 0x10)));
/*     */     } else {
/* 139 */       this.datawatcher.set(a, Byte.valueOf((byte)(b0 & 0xFFFFFFEF)));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect F() {
/* 146 */     return SoundEffects.hq;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect d(DamageSource damagesource) {
/* 151 */     return SoundEffects.hs;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect cf() {
/* 156 */     return SoundEffects.hr;
/*     */   }
/*     */   
/*     */   public void p(boolean flag) {}
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntitySnowman.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */