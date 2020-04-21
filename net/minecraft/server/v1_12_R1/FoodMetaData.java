/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import org.bukkit.event.entity.FoodLevelChangeEvent;
/*     */ 
/*     */ public class FoodMetaData {
/*   5 */   public int foodLevel = 20;
/*   6 */   public float saturationLevel = 5.0F;
/*     */   public float exhaustionLevel;
/*     */   private int foodTickTimer;
/*     */   private EntityHuman entityhuman;
/*  10 */   private int e = 20;
/*     */   public FoodMetaData() {
/*  12 */     throw new AssertionError("Whoopsie, we missed the bukkit.");
/*     */   }
/*     */   
/*     */   public FoodMetaData(EntityHuman entityhuman) {
/*  16 */     Validate.notNull(entityhuman);
/*  17 */     this.entityhuman = entityhuman;
/*     */   }
/*     */ 
/*     */   
/*     */   public void eat(int i, float f) {
/*  22 */     this.foodLevel = Math.min(i + this.foodLevel, 20);
/*  23 */     this.saturationLevel = Math.min(this.saturationLevel + i * f * 2.0F, this.foodLevel);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(ItemFood itemfood, ItemStack itemstack) {
/*  28 */     int oldFoodLevel = this.foodLevel;
/*     */     
/*  30 */     FoodLevelChangeEvent event = CraftEventFactory.callFoodLevelChangeEvent(this.entityhuman, itemfood.getNutrition(itemstack) + oldFoodLevel);
/*     */     
/*  32 */     if (!event.isCancelled()) {
/*  33 */       eat(event.getFoodLevel() - oldFoodLevel, itemfood.getSaturationModifier(itemstack));
/*     */     }
/*     */     
/*  36 */     ((EntityPlayer)this.entityhuman).getBukkitEntity().sendHealthUpdate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(EntityHuman entityhuman) {
/*  41 */     EnumDifficulty enumdifficulty = entityhuman.world.getDifficulty();
/*     */     
/*  43 */     this.e = this.foodLevel;
/*  44 */     if (this.exhaustionLevel > 4.0F) {
/*  45 */       this.exhaustionLevel -= 4.0F;
/*  46 */       if (this.saturationLevel > 0.0F) {
/*  47 */         this.saturationLevel = Math.max(this.saturationLevel - 1.0F, 0.0F);
/*  48 */       } else if (enumdifficulty != EnumDifficulty.PEACEFUL) {
/*     */         
/*  50 */         FoodLevelChangeEvent event = CraftEventFactory.callFoodLevelChangeEvent(entityhuman, Math.max(this.foodLevel - 1, 0));
/*     */         
/*  52 */         if (!event.isCancelled()) {
/*  53 */           this.foodLevel = event.getFoodLevel();
/*     */         }
/*     */         
/*  56 */         ((EntityPlayer)entityhuman).playerConnection.sendPacket(new PacketPlayOutUpdateHealth(((EntityPlayer)entityhuman).getBukkitEntity().getScaledHealth(), this.foodLevel, this.saturationLevel));
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  61 */     boolean flag = entityhuman.world.getGameRules().getBoolean("naturalRegeneration");
/*     */     
/*  63 */     if (flag && this.saturationLevel > 0.0F && entityhuman.dj() && this.foodLevel >= 20) {
/*  64 */       this.foodTickTimer++;
/*  65 */       if (this.foodTickTimer >= 10) {
/*  66 */         float f = Math.min(this.saturationLevel, 6.0F);
/*     */         
/*  68 */         entityhuman.heal(f / 6.0F, EntityRegainHealthEvent.RegainReason.SATIATED);
/*  69 */         a(f);
/*  70 */         this.foodTickTimer = 0;
/*     */       } 
/*  72 */     } else if (flag && this.foodLevel >= 18 && entityhuman.dj()) {
/*  73 */       this.foodTickTimer++;
/*  74 */       if (this.foodTickTimer >= 80) {
/*  75 */         entityhuman.heal(1.0F, EntityRegainHealthEvent.RegainReason.SATIATED);
/*  76 */         a(entityhuman.world.spigotConfig.regenExhaustion);
/*  77 */         this.foodTickTimer = 0;
/*     */       } 
/*  79 */     } else if (this.foodLevel <= 0) {
/*  80 */       this.foodTickTimer++;
/*  81 */       if (this.foodTickTimer >= 80) {
/*  82 */         if (entityhuman.getHealth() > 10.0F || enumdifficulty == EnumDifficulty.HARD || (entityhuman.getHealth() > 1.0F && enumdifficulty == EnumDifficulty.NORMAL)) {
/*  83 */           entityhuman.damageEntity(DamageSource.STARVE, 1.0F);
/*     */         }
/*     */         
/*  86 */         this.foodTickTimer = 0;
/*     */       } 
/*     */     } else {
/*  89 */       this.foodTickTimer = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/*  95 */     if (nbttagcompound.hasKeyOfType("foodLevel", 99)) {
/*  96 */       this.foodLevel = nbttagcompound.getInt("foodLevel");
/*  97 */       this.foodTickTimer = nbttagcompound.getInt("foodTickTimer");
/*  98 */       this.saturationLevel = nbttagcompound.getFloat("foodSaturationLevel");
/*  99 */       this.exhaustionLevel = nbttagcompound.getFloat("foodExhaustionLevel");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/* 105 */     nbttagcompound.setInt("foodLevel", this.foodLevel);
/* 106 */     nbttagcompound.setInt("foodTickTimer", this.foodTickTimer);
/* 107 */     nbttagcompound.setFloat("foodSaturationLevel", this.saturationLevel);
/* 108 */     nbttagcompound.setFloat("foodExhaustionLevel", this.exhaustionLevel);
/*     */   }
/*     */   
/*     */   public int getFoodLevel() {
/* 112 */     return this.foodLevel;
/*     */   }
/*     */   
/*     */   public boolean c() {
/* 116 */     return (this.foodLevel < 20);
/*     */   }
/*     */   
/*     */   public void a(float f) {
/* 120 */     this.exhaustionLevel = Math.min(this.exhaustionLevel + f, 40.0F);
/*     */   }
/*     */   
/*     */   public float getSaturationLevel() {
/* 124 */     return this.saturationLevel;
/*     */   }
/*     */   
/*     */   public void a(int i) {
/* 128 */     this.foodLevel = i;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\FoodMetaData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */