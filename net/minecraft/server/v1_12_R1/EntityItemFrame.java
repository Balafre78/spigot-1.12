/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.commons.codec.Charsets;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ 
/*     */ public class EntityItemFrame
/*     */   extends EntityHanging
/*     */ {
/*  11 */   private static final DataWatcherObject<ItemStack> c = DataWatcher.a((Class)EntityItemFrame.class, DataWatcherRegistry.f);
/*  12 */   private static final DataWatcherObject<Integer> d = DataWatcher.a((Class)EntityItemFrame.class, DataWatcherRegistry.b);
/*  13 */   private float e = 1.0F;
/*     */   
/*     */   public EntityItemFrame(World world) {
/*  16 */     super(world);
/*     */   }
/*     */   
/*     */   public EntityItemFrame(World world, BlockPosition blockposition, EnumDirection enumdirection) {
/*  20 */     super(world, blockposition);
/*  21 */     setDirection(enumdirection);
/*     */   }
/*     */   
/*     */   protected void i() {
/*  25 */     getDataWatcher().register(c, ItemStack.a);
/*  26 */     getDataWatcher().register(d, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   public float aI() {
/*  30 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/*  34 */     if (isInvulnerable(damagesource))
/*  35 */       return false; 
/*  36 */     if (!damagesource.isExplosion() && !getItem().isEmpty()) {
/*  37 */       if (!this.world.isClientSide) {
/*     */         
/*  39 */         if (CraftEventFactory.handleNonLivingEntityDamageEvent(this, damagesource, f, false) || this.dead) {
/*  40 */           return true;
/*     */         }
/*     */         
/*  43 */         b(damagesource.getEntity(), false);
/*  44 */         a(SoundEffects.du, 1.0F, 1.0F);
/*  45 */         setItem(ItemStack.a);
/*     */       } 
/*     */       
/*  48 */       return true;
/*     */     } 
/*  50 */     return super.damageEntity(damagesource, f);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWidth() {
/*  55 */     return 12;
/*     */   }
/*     */   
/*     */   public int getHeight() {
/*  59 */     return 12;
/*     */   }
/*     */   
/*     */   public void a(@Nullable Entity entity) {
/*  63 */     a(SoundEffects.ds, 1.0F, 1.0F);
/*  64 */     b(entity, true);
/*     */   }
/*     */   
/*     */   public void p() {
/*  68 */     a(SoundEffects.dt, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public void b(@Nullable Entity entity, boolean flag) {
/*  72 */     if (this.world.getGameRules().getBoolean("doEntityDrops")) {
/*  73 */       ItemStack itemstack = getItem();
/*     */       
/*  75 */       if (entity instanceof EntityHuman) {
/*  76 */         EntityHuman entityhuman = (EntityHuman)entity;
/*     */         
/*  78 */         if (entityhuman.abilities.canInstantlyBuild) {
/*  79 */           b(itemstack);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*  84 */       if (flag) {
/*  85 */         a(new ItemStack(Items.ITEM_FRAME), 0.0F);
/*     */       }
/*     */       
/*  88 */       if (!itemstack.isEmpty() && this.random.nextFloat() < this.e) {
/*  89 */         itemstack = itemstack.cloneItemStack();
/*  90 */         b(itemstack);
/*  91 */         a(itemstack, 0.0F);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void b(ItemStack itemstack) {
/*  98 */     if (!itemstack.isEmpty()) {
/*  99 */       if (itemstack.getItem() == Items.FILLED_MAP) {
/* 100 */         WorldMap worldmap = ((ItemWorldMap)itemstack.getItem()).getSavedMap(itemstack, this.world);
/*     */         
/* 102 */         worldmap.decorations.remove(UUID.nameUUIDFromBytes(("frame-" + getId()).getBytes(Charsets.US_ASCII)));
/*     */       } 
/*     */       
/* 105 */       itemstack.a((EntityItemFrame)null);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ItemStack getItem() {
/* 110 */     return getDataWatcher().<ItemStack>get(c);
/*     */   }
/*     */   
/*     */   public void setItem(ItemStack itemstack) {
/* 114 */     setItem(itemstack, true);
/*     */   }
/*     */   
/*     */   private void setItem(ItemStack itemstack, boolean flag) {
/* 118 */     if (!itemstack.isEmpty()) {
/* 119 */       itemstack = itemstack.cloneItemStack();
/* 120 */       itemstack.setCount(1);
/* 121 */       itemstack.a(this);
/*     */     } 
/*     */     
/* 124 */     getDataWatcher().set(c, itemstack);
/* 125 */     getDataWatcher().markDirty(c);
/* 126 */     if (!itemstack.isEmpty()) {
/* 127 */       a(SoundEffects.dr, 1.0F, 1.0F);
/*     */     }
/*     */     
/* 130 */     if (flag && this.blockPosition != null) {
/* 131 */       this.world.updateAdjacentComparators(this.blockPosition, Blocks.AIR);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(DataWatcherObject<?> datawatcherobject) {
/* 137 */     if (datawatcherobject.equals(c)) {
/* 138 */       ItemStack itemstack = getItem();
/*     */       
/* 140 */       if (!itemstack.isEmpty() && itemstack.A() != this) {
/* 141 */         itemstack.a(this);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRotation() {
/* 148 */     return ((Integer)getDataWatcher().<Integer>get(d)).intValue();
/*     */   }
/*     */   
/*     */   public void setRotation(int i) {
/* 152 */     setRotation(i, true);
/*     */   }
/*     */   
/*     */   private void setRotation(int i, boolean flag) {
/* 156 */     getDataWatcher().set(d, Integer.valueOf(i % 8));
/* 157 */     if (flag && this.blockPosition != null) {
/* 158 */       this.world.updateAdjacentComparators(this.blockPosition, Blocks.AIR);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {
/* 164 */     dataconvertermanager.a(DataConverterTypes.ENTITY, new DataInspectorItem(EntityItemFrame.class, new String[] { "Item" }));
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/* 168 */     if (!getItem().isEmpty()) {
/* 169 */       nbttagcompound.set("Item", getItem().save(new NBTTagCompound()));
/* 170 */       nbttagcompound.setByte("ItemRotation", (byte)getRotation());
/* 171 */       nbttagcompound.setFloat("ItemDropChance", this.e);
/*     */     } 
/*     */     
/* 174 */     super.b(nbttagcompound);
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/* 178 */     NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Item");
/*     */     
/* 180 */     if (nbttagcompound1 != null && !nbttagcompound1.isEmpty()) {
/* 181 */       setItem(new ItemStack(nbttagcompound1), false);
/* 182 */       setRotation(nbttagcompound.getByte("ItemRotation"), false);
/* 183 */       if (nbttagcompound.hasKeyOfType("ItemDropChance", 99)) {
/* 184 */         this.e = nbttagcompound.getFloat("ItemDropChance");
/*     */       }
/*     */     } 
/*     */     
/* 188 */     super.a(nbttagcompound);
/*     */   }
/*     */   
/*     */   public boolean b(EntityHuman entityhuman, EnumHand enumhand) {
/* 192 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/* 194 */     if (!this.world.isClientSide) {
/* 195 */       if (getItem().isEmpty()) {
/* 196 */         if (!itemstack.isEmpty()) {
/* 197 */           setItem(itemstack);
/* 198 */           if (!entityhuman.abilities.canInstantlyBuild) {
/* 199 */             itemstack.subtract(1);
/*     */           }
/*     */         } 
/*     */       } else {
/* 203 */         a(SoundEffects.dv, 1.0F, 1.0F);
/* 204 */         setRotation(getRotation() + 1);
/*     */       } 
/*     */     }
/*     */     
/* 208 */     return true;
/*     */   }
/*     */   
/*     */   public int t() {
/* 212 */     return getItem().isEmpty() ? 0 : (getRotation() % 8 + 1);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityItemFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */