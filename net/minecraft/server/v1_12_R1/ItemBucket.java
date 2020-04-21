/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
/*     */ import org.bukkit.event.player.PlayerBucketEmptyEvent;
/*     */ import org.bukkit.event.player.PlayerBucketFillEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class ItemBucket
/*     */   extends Item
/*     */ {
/*     */   private final Block a;
/*     */   
/*     */   public ItemBucket(Block block) {
/*  16 */     this.maxStackSize = 1;
/*  17 */     this.a = block;
/*  18 */     b(CreativeModeTab.f);
/*     */   }
/*     */   
/*     */   public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
/*  22 */     boolean flag = (this.a == Blocks.AIR);
/*  23 */     ItemStack itemstack = entityhuman.b(enumhand);
/*  24 */     MovingObjectPosition movingobjectposition = a(world, entityhuman, flag);
/*     */     
/*  26 */     if (movingobjectposition == null)
/*  27 */       return new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemstack); 
/*  28 */     if (movingobjectposition.type != MovingObjectPosition.EnumMovingObjectType.BLOCK) {
/*  29 */       return new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemstack);
/*     */     }
/*  31 */     BlockPosition blockposition = movingobjectposition.a();
/*     */     
/*  33 */     if (!world.a(entityhuman, blockposition))
/*  34 */       return new InteractionResultWrapper<>(EnumInteractionResult.FAIL, itemstack); 
/*  35 */     if (flag) {
/*  36 */       if (!entityhuman.a(blockposition.shift(movingobjectposition.direction), movingobjectposition.direction, itemstack)) {
/*  37 */         return new InteractionResultWrapper<>(EnumInteractionResult.FAIL, itemstack);
/*     */       }
/*  39 */       IBlockData iblockdata = world.getType(blockposition);
/*  40 */       Material material = iblockdata.getMaterial();
/*     */       
/*  42 */       if (material == Material.WATER && ((Integer)iblockdata.<Integer>get(BlockFluids.LEVEL)).intValue() == 0) {
/*     */         
/*  44 */         PlayerBucketFillEvent event = CraftEventFactory.callPlayerBucketFillEvent(entityhuman, blockposition.getX(), blockposition.getY(), blockposition.getZ(), null, itemstack, Items.WATER_BUCKET);
/*     */         
/*  46 */         if (event.isCancelled()) {
/*  47 */           return new InteractionResultWrapper<>(EnumInteractionResult.FAIL, itemstack);
/*     */         }
/*     */         
/*  50 */         world.setTypeAndData(blockposition, Blocks.AIR.getBlockData(), 11);
/*  51 */         entityhuman.b(StatisticList.b(this));
/*  52 */         entityhuman.a(SoundEffects.S, 1.0F, 1.0F);
/*  53 */         return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, a(itemstack, entityhuman, Items.WATER_BUCKET, event.getItemStack()));
/*  54 */       }  if (material == Material.LAVA && ((Integer)iblockdata.<Integer>get(BlockFluids.LEVEL)).intValue() == 0) {
/*     */         
/*  56 */         PlayerBucketFillEvent event = CraftEventFactory.callPlayerBucketFillEvent(entityhuman, blockposition.getX(), blockposition.getY(), blockposition.getZ(), null, itemstack, Items.LAVA_BUCKET);
/*     */         
/*  58 */         if (event.isCancelled()) {
/*  59 */           return new InteractionResultWrapper<>(EnumInteractionResult.FAIL, itemstack);
/*     */         }
/*     */         
/*  62 */         entityhuman.a(SoundEffects.T, 1.0F, 1.0F);
/*  63 */         world.setTypeAndData(blockposition, Blocks.AIR.getBlockData(), 11);
/*  64 */         entityhuman.b(StatisticList.b(this));
/*  65 */         return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, a(itemstack, entityhuman, Items.LAVA_BUCKET, event.getItemStack()));
/*     */       } 
/*  67 */       return new InteractionResultWrapper<>(EnumInteractionResult.FAIL, itemstack);
/*     */     } 
/*     */ 
/*     */     
/*  71 */     boolean flag1 = world.getType(blockposition).getBlock().a(world, blockposition);
/*  72 */     BlockPosition blockposition1 = (flag1 && movingobjectposition.direction == EnumDirection.UP) ? blockposition : blockposition.shift(movingobjectposition.direction);
/*     */     
/*  74 */     if (!entityhuman.a(blockposition1, movingobjectposition.direction, itemstack))
/*  75 */       return new InteractionResultWrapper<>(EnumInteractionResult.FAIL, itemstack); 
/*  76 */     if (a(entityhuman, world, blockposition1, movingobjectposition.direction, blockposition, itemstack)) {
/*  77 */       if (entityhuman instanceof EntityPlayer) {
/*  78 */         CriterionTriggers.x.a((EntityPlayer)entityhuman, blockposition1, itemstack);
/*     */       }
/*     */       
/*  81 */       entityhuman.b(StatisticList.b(this));
/*  82 */       return !entityhuman.abilities.canInstantlyBuild ? new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, new ItemStack(Items.BUCKET)) : new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemstack);
/*     */     } 
/*  84 */     return new InteractionResultWrapper<>(EnumInteractionResult.FAIL, itemstack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ItemStack a(ItemStack itemstack, EntityHuman entityhuman, Item item, ItemStack result) {
/*  92 */     if (entityhuman.abilities.canInstantlyBuild) {
/*  93 */       return itemstack;
/*     */     }
/*  95 */     itemstack.subtract(1);
/*  96 */     if (itemstack.isEmpty())
/*     */     {
/*  98 */       return CraftItemStack.asNMSCopy(result);
/*     */     }
/* 100 */     if (!entityhuman.inventory.pickup(CraftItemStack.asNMSCopy(result))) {
/* 101 */       entityhuman.drop(CraftItemStack.asNMSCopy(result), false);
/*     */     }
/*     */ 
/*     */     
/* 105 */     return itemstack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(@Nullable EntityHuman entityhuman, World world, BlockPosition blockposition) {
/* 112 */     return a(entityhuman, world, blockposition, (EnumDirection)null, blockposition, (ItemStack)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman entityhuman, World world, BlockPosition blockposition, EnumDirection enumdirection, BlockPosition clicked, ItemStack itemstack) {
/* 117 */     if (this.a == Blocks.AIR) {
/* 118 */       return false;
/*     */     }
/* 120 */     IBlockData iblockdata = world.getType(blockposition);
/* 121 */     Material material = iblockdata.getMaterial();
/* 122 */     boolean flag = !material.isBuildable();
/* 123 */     boolean flag1 = iblockdata.getBlock().a(world, blockposition);
/*     */     
/* 125 */     if (!world.isEmpty(blockposition) && !flag && !flag1) {
/* 126 */       return false;
/*     */     }
/*     */     
/* 129 */     if (entityhuman != null) {
/* 130 */       PlayerBucketEmptyEvent event = CraftEventFactory.callPlayerBucketEmptyEvent(entityhuman, clicked.getX(), clicked.getY(), clicked.getZ(), enumdirection, itemstack);
/* 131 */       if (event.isCancelled())
/*     */       {
/* 133 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 137 */     if (world.worldProvider.l() && this.a == Blocks.FLOWING_WATER) {
/* 138 */       int i = blockposition.getX();
/* 139 */       int j = blockposition.getY();
/* 140 */       int k = blockposition.getZ();
/*     */       
/* 142 */       world.a(entityhuman, blockposition, SoundEffects.bN, SoundCategory.BLOCKS, 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);
/*     */       
/* 144 */       for (int l = 0; l < 8; l++) {
/* 145 */         world.addParticle(EnumParticle.SMOKE_LARGE, i + Math.random(), j + Math.random(), k + Math.random(), 0.0D, 0.0D, 0.0D, new int[0]);
/*     */       }
/*     */     } else {
/* 148 */       if (!world.isClientSide && (flag || flag1) && !material.isLiquid()) {
/* 149 */         world.setAir(blockposition, true);
/*     */       }
/*     */       
/* 152 */       SoundEffect soundeffect = (this.a == Blocks.FLOWING_LAVA) ? SoundEffects.R : SoundEffects.Q;
/*     */       
/* 154 */       world.a(entityhuman, blockposition, soundeffect, SoundCategory.BLOCKS, 1.0F, 1.0F);
/* 155 */       world.setTypeAndData(blockposition, this.a.getBlockData(), 11);
/*     */     } 
/*     */     
/* 158 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemBucket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */