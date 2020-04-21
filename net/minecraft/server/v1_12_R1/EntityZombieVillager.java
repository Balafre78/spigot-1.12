/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ 
/*     */ public class EntityZombieVillager extends EntityZombie {
/*   8 */   private static final DataWatcherObject<Boolean> b = DataWatcher.a((Class)EntityZombieVillager.class, DataWatcherRegistry.h);
/*   9 */   private static final DataWatcherObject<Integer> c = DataWatcher.a((Class)EntityZombieVillager.class, DataWatcherRegistry.b);
/*     */   private int conversionTime;
/*     */   private UUID by;
/*  12 */   private int lastTick = MinecraftServer.currentTick;
/*     */   
/*     */   public EntityZombieVillager(World world) {
/*  15 */     super(world);
/*     */   }
/*     */   
/*     */   protected void i() {
/*  19 */     super.i();
/*  20 */     this.datawatcher.register(b, Boolean.valueOf(false));
/*  21 */     this.datawatcher.register(c, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   public void setProfession(int i) {
/*  25 */     this.datawatcher.set(c, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public int getProfession() {
/*  29 */     return Math.max(((Integer)this.datawatcher.<Integer>get(c)).intValue() % 6, 0);
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {
/*  33 */     EntityInsentient.a(dataconvertermanager, EntityZombieVillager.class);
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/*  37 */     super.b(nbttagcompound);
/*  38 */     nbttagcompound.setInt("Profession", getProfession());
/*  39 */     nbttagcompound.setInt("ConversionTime", isConverting() ? this.conversionTime : -1);
/*  40 */     if (this.by != null) {
/*  41 */       nbttagcompound.a("ConversionPlayer", this.by);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/*  47 */     super.a(nbttagcompound);
/*  48 */     setProfession(nbttagcompound.getInt("Profession"));
/*  49 */     if (nbttagcompound.hasKeyOfType("ConversionTime", 99) && nbttagcompound.getInt("ConversionTime") > -1) {
/*  50 */       a(nbttagcompound.b("ConversionPlayer") ? nbttagcompound.a("ConversionPlayer") : null, nbttagcompound.getInt("ConversionTime"));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, @Nullable GroupDataEntity groupdataentity) {
/*  57 */     setProfession(this.world.random.nextInt(6));
/*  58 */     return super.prepare(difficultydamagescaler, groupdataentity);
/*     */   }
/*     */   
/*     */   public void B_() {
/*  62 */     if (!this.world.isClientSide && isConverting()) {
/*  63 */       int i = du();
/*     */       
/*  65 */       int elapsedTicks = MinecraftServer.currentTick - this.lastTick;
/*  66 */       this.lastTick = MinecraftServer.currentTick;
/*  67 */       i *= elapsedTicks;
/*     */ 
/*     */       
/*  70 */       this.conversionTime -= i;
/*  71 */       if (this.conversionTime <= 0) {
/*  72 */         dt();
/*     */       }
/*     */     } 
/*     */     
/*  76 */     super.B_();
/*     */   }
/*     */   
/*     */   public boolean a(EntityHuman entityhuman, EnumHand enumhand) {
/*  80 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/*  82 */     if (itemstack.getItem() == Items.GOLDEN_APPLE && itemstack.getData() == 0 && hasEffect(MobEffects.WEAKNESS)) {
/*  83 */       if (!entityhuman.abilities.canInstantlyBuild) {
/*  84 */         itemstack.subtract(1);
/*     */       }
/*     */       
/*  87 */       if (!this.world.isClientSide) {
/*  88 */         a(entityhuman.getUniqueID(), this.random.nextInt(2401) + 3600);
/*     */       }
/*     */       
/*  91 */       return true;
/*     */     } 
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isTypeNotPersistent() {
/*  98 */     return !isConverting();
/*     */   }
/*     */   
/*     */   public boolean isConverting() {
/* 102 */     return ((Boolean)getDataWatcher().<Boolean>get(b)).booleanValue();
/*     */   }
/*     */   
/*     */   protected void a(@Nullable UUID uuid, int i) {
/* 106 */     this.by = uuid;
/* 107 */     this.conversionTime = i;
/* 108 */     getDataWatcher().set(b, Boolean.valueOf(true));
/* 109 */     removeEffect(MobEffects.WEAKNESS);
/* 110 */     addEffect(new MobEffect(MobEffects.INCREASE_DAMAGE, i, Math.min(this.world.getDifficulty().a() - 1, 0)));
/* 111 */     this.world.broadcastEntityEffect(this, (byte)16);
/*     */   }
/*     */   
/*     */   protected void dt() {
/* 115 */     EntityVillager entityvillager = new EntityVillager(this.world);
/*     */     
/* 117 */     entityvillager.u(this);
/* 118 */     entityvillager.setProfession(getProfession());
/* 119 */     entityvillager.a(this.world.D(new BlockPosition(entityvillager)), (GroupDataEntity)null, false);
/* 120 */     entityvillager.dp();
/* 121 */     if (isBaby()) {
/* 122 */       entityvillager.setAgeRaw(-24000);
/*     */     }
/*     */     
/* 125 */     this.world.kill(this);
/* 126 */     entityvillager.setNoAI(isNoAI());
/* 127 */     if (hasCustomName()) {
/* 128 */       entityvillager.setCustomName(getCustomName());
/* 129 */       entityvillager.setCustomNameVisible(getCustomNameVisible());
/*     */     } 
/*     */     
/* 132 */     this.world.addEntity(entityvillager, CreatureSpawnEvent.SpawnReason.CURED);
/* 133 */     if (this.by != null) {
/* 134 */       EntityHuman entityhuman = this.world.b(this.by);
/*     */       
/* 136 */       if (entityhuman instanceof EntityPlayer) {
/* 137 */         CriterionTriggers.q.a((EntityPlayer)entityhuman, this, entityvillager);
/*     */       }
/*     */     } 
/*     */     
/* 141 */     entityvillager.addEffect(new MobEffect(MobEffects.CONFUSION, 200, 0));
/* 142 */     this.world.a((EntityHuman)null, 1027, new BlockPosition((int)this.locX, (int)this.locY, (int)this.locZ), 0);
/*     */   }
/*     */   
/*     */   protected int du() {
/* 146 */     int i = 1;
/*     */     
/* 148 */     if (this.random.nextFloat() < 0.01F) {
/* 149 */       int j = 0;
/* 150 */       BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/*     */       
/* 152 */       for (int k = (int)this.locX - 4; k < (int)this.locX + 4 && j < 14; k++) {
/* 153 */         for (int l = (int)this.locY - 4; l < (int)this.locY + 4 && j < 14; l++) {
/* 154 */           for (int i1 = (int)this.locZ - 4; i1 < (int)this.locZ + 4 && j < 14; i1++) {
/* 155 */             Block block = this.world.getType(blockposition_mutableblockposition.c(k, l, i1)).getBlock();
/*     */             
/* 157 */             if (block == Blocks.IRON_BARS || block == Blocks.BED) {
/* 158 */               if (this.random.nextFloat() < 0.3F) {
/* 159 */                 i++;
/*     */               }
/*     */               
/* 162 */               j++;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 169 */     return i;
/*     */   }
/*     */   
/*     */   protected float cr() {
/* 173 */     return isBaby() ? ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 2.0F) : ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
/*     */   }
/*     */   
/*     */   public SoundEffect F() {
/* 177 */     return SoundEffects.jx;
/*     */   }
/*     */   
/*     */   public SoundEffect d(DamageSource damagesource) {
/* 181 */     return SoundEffects.jB;
/*     */   }
/*     */   
/*     */   public SoundEffect cf() {
/* 185 */     return SoundEffects.jA;
/*     */   }
/*     */   
/*     */   public SoundEffect dm() {
/* 189 */     return SoundEffects.jC;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/* 194 */     return LootTables.as;
/*     */   }
/*     */   
/*     */   protected ItemStack dn() {
/* 198 */     return ItemStack.a;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityZombieVillager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */