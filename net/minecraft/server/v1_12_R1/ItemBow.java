/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.entity.EntityCombustEvent;
/*     */ import org.bukkit.event.entity.EntityShootBowEvent;
/*     */ 
/*     */ public class ItemBow extends Item {
/*     */   public ItemBow() {
/*   8 */     this.maxStackSize = 1;
/*   9 */     setMaxDurability(384);
/*  10 */     b(CreativeModeTab.j);
/*  11 */     a(new MinecraftKey("pull"), new IDynamicTexture() {  }
/*     */       );
/*  13 */     a(new MinecraftKey("pulling"), new IDynamicTexture() {  }
/*     */       );
/*     */   }
/*     */   
/*     */   private ItemStack a(EntityHuman entityhuman) {
/*  18 */     if (d(entityhuman.b(EnumHand.OFF_HAND)))
/*  19 */       return entityhuman.b(EnumHand.OFF_HAND); 
/*  20 */     if (d(entityhuman.b(EnumHand.MAIN_HAND))) {
/*  21 */       return entityhuman.b(EnumHand.MAIN_HAND);
/*     */     }
/*  23 */     for (int i = 0; i < entityhuman.inventory.getSize(); i++) {
/*  24 */       ItemStack itemstack = entityhuman.inventory.getItem(i);
/*     */       
/*  26 */       if (d(itemstack)) {
/*  27 */         return itemstack;
/*     */       }
/*     */     } 
/*     */     
/*  31 */     return ItemStack.a;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean d(ItemStack itemstack) {
/*  36 */     return itemstack.getItem() instanceof ItemArrow;
/*     */   }
/*     */   
/*     */   public void a(ItemStack itemstack, World world, EntityLiving entityliving, int i) {
/*  40 */     if (entityliving instanceof EntityHuman) {
/*  41 */       EntityHuman entityhuman = (EntityHuman)entityliving;
/*  42 */       boolean flag = !(!entityhuman.abilities.canInstantlyBuild && EnchantmentManager.getEnchantmentLevel(Enchantments.ARROW_INFINITE, itemstack) <= 0);
/*  43 */       ItemStack itemstack1 = a(entityhuman);
/*     */       
/*  45 */       if (!itemstack1.isEmpty() || flag) {
/*  46 */         if (itemstack1.isEmpty()) {
/*  47 */           itemstack1 = new ItemStack(Items.ARROW);
/*     */         }
/*     */         
/*  50 */         int j = e(itemstack) - i;
/*  51 */         float f = b(j);
/*     */         
/*  53 */         if (f >= 0.1D) {
/*  54 */           boolean flag1 = (flag && itemstack1.getItem() == Items.ARROW);
/*     */           
/*  56 */           if (!world.isClientSide) {
/*  57 */             ItemArrow itemarrow = (itemstack1.getItem() instanceof ItemArrow) ? (ItemArrow)itemstack1.getItem() : (ItemArrow)Items.ARROW;
/*  58 */             EntityArrow entityarrow = itemarrow.a(world, itemstack1, entityhuman);
/*     */             
/*  60 */             entityarrow.a(entityhuman, entityhuman.pitch, entityhuman.yaw, 0.0F, f * 3.0F, 1.0F);
/*  61 */             if (f == 1.0F) {
/*  62 */               entityarrow.setCritical(true);
/*     */             }
/*     */             
/*  65 */             int k = EnchantmentManager.getEnchantmentLevel(Enchantments.ARROW_DAMAGE, itemstack);
/*     */             
/*  67 */             if (k > 0) {
/*  68 */               entityarrow.c(entityarrow.k() + k * 0.5D + 0.5D);
/*     */             }
/*     */             
/*  71 */             int l = EnchantmentManager.getEnchantmentLevel(Enchantments.ARROW_KNOCKBACK, itemstack);
/*     */             
/*  73 */             if (l > 0) {
/*  74 */               entityarrow.setKnockbackStrength(l);
/*     */             }
/*     */             
/*  77 */             if (EnchantmentManager.getEnchantmentLevel(Enchantments.ARROW_FIRE, itemstack) > 0) {
/*     */               
/*  79 */               EntityCombustEvent entityCombustEvent = new EntityCombustEvent((Entity)entityarrow.getBukkitEntity(), 100);
/*  80 */               entityarrow.world.getServer().getPluginManager().callEvent((Event)entityCombustEvent);
/*     */               
/*  82 */               if (!entityCombustEvent.isCancelled()) {
/*  83 */                 entityarrow.setOnFire(entityCombustEvent.getDuration());
/*     */               }
/*     */             } 
/*     */ 
/*     */             
/*  88 */             EntityShootBowEvent event = CraftEventFactory.callEntityShootBowEvent(entityhuman, itemstack, entityarrow, f);
/*  89 */             if (event.isCancelled()) {
/*  90 */               event.getProjectile().remove();
/*     */               
/*     */               return;
/*     */             } 
/*  94 */             itemstack.damage(1, entityhuman);
/*  95 */             if (flag1 || (entityhuman.abilities.canInstantlyBuild && (itemstack1.getItem() == Items.SPECTRAL_ARROW || itemstack1.getItem() == Items.TIPPED_ARROW))) {
/*  96 */               entityarrow.fromPlayer = EntityArrow.PickupStatus.CREATIVE_ONLY;
/*     */             }
/*     */             
/*  99 */             if (event.getProjectile() == entityarrow.getBukkitEntity() && 
/* 100 */               !world.addEntity(entityarrow)) {
/* 101 */               if (entityhuman instanceof EntityPlayer) {
/* 102 */                 ((EntityPlayer)entityhuman).getBukkitEntity().updateInventory();
/*     */               }
/*     */ 
/*     */               
/*     */               return;
/*     */             } 
/*     */           } 
/*     */           
/* 110 */           world.a((EntityHuman)null, entityhuman.locX, entityhuman.locY, entityhuman.locZ, SoundEffects.w, SoundCategory.PLAYERS, 1.0F, 1.0F / (ItemBow.j.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
/* 111 */           if (!flag1 && !entityhuman.abilities.canInstantlyBuild) {
/* 112 */             itemstack1.subtract(1);
/* 113 */             if (itemstack1.isEmpty()) {
/* 114 */               entityhuman.inventory.f(itemstack1);
/*     */             }
/*     */           } 
/*     */           
/* 118 */           entityhuman.b(StatisticList.b(this));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static float b(int i) {
/* 125 */     float f = i / 20.0F;
/*     */     
/* 127 */     f = (f * f + f * 2.0F) / 3.0F;
/* 128 */     if (f > 1.0F) {
/* 129 */       f = 1.0F;
/*     */     }
/*     */     
/* 132 */     return f;
/*     */   }
/*     */   
/*     */   public int e(ItemStack itemstack) {
/* 136 */     return 72000;
/*     */   }
/*     */   
/*     */   public EnumAnimation f(ItemStack itemstack) {
/* 140 */     return EnumAnimation.BOW;
/*     */   }
/*     */   
/*     */   public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
/* 144 */     ItemStack itemstack = entityhuman.b(enumhand);
/* 145 */     boolean flag = !a(entityhuman).isEmpty();
/*     */     
/* 147 */     if (!entityhuman.abilities.canInstantlyBuild && !flag) {
/* 148 */       return flag ? new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemstack) : new InteractionResultWrapper<>(EnumInteractionResult.FAIL, itemstack);
/*     */     }
/* 150 */     entityhuman.c(enumhand);
/* 151 */     return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemstack);
/*     */   }
/*     */ 
/*     */   
/*     */   public int c() {
/* 156 */     return 1;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemBow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */