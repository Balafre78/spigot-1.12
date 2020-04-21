/*     */ package net.minecraft.server.v1_12_R1;
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
/*     */ public class ItemEnderEye
/*     */   extends Item
/*     */ {
/*     */   public ItemEnderEye() {
/*  29 */     b(CreativeModeTab.f);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult a(EntityHuman paramEntityHuman, World paramWorld, BlockPosition paramBlockPosition, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/*  34 */     IBlockData iBlockData = paramWorld.getType(paramBlockPosition);
/*     */     
/*  36 */     ItemStack itemStack = paramEntityHuman.b(paramEnumHand);
/*  37 */     if (!paramEntityHuman.a(paramBlockPosition.shift(paramEnumDirection), paramEnumDirection, itemStack) || iBlockData.getBlock() != Blocks.END_PORTAL_FRAME || ((Boolean)iBlockData.<Boolean>get(BlockEnderPortalFrame.EYE)).booleanValue()) {
/*  38 */       return EnumInteractionResult.FAIL;
/*     */     }
/*     */     
/*  41 */     if (paramWorld.isClientSide) {
/*  42 */       return EnumInteractionResult.SUCCESS;
/*     */     }
/*  44 */     paramWorld.setTypeAndData(paramBlockPosition, iBlockData.set(BlockEnderPortalFrame.EYE, Boolean.valueOf(true)), 2);
/*  45 */     paramWorld.updateAdjacentComparators(paramBlockPosition, Blocks.END_PORTAL_FRAME);
/*  46 */     itemStack.subtract(1);
/*     */     
/*  48 */     for (byte b = 0; b < 16; b++) {
/*  49 */       double d1 = (paramBlockPosition.getX() + (5.0F + j.nextFloat() * 6.0F) / 16.0F);
/*  50 */       double d2 = (paramBlockPosition.getY() + 0.8125F);
/*  51 */       double d3 = (paramBlockPosition.getZ() + (5.0F + j.nextFloat() * 6.0F) / 16.0F);
/*  52 */       double d4 = 0.0D;
/*  53 */       double d5 = 0.0D;
/*  54 */       double d6 = 0.0D;
/*     */       
/*  56 */       paramWorld.addParticle(EnumParticle.SMOKE_NORMAL, d1, d2, d3, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */     } 
/*  58 */     paramWorld.a(null, paramBlockPosition, SoundEffects.bp, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*     */ 
/*     */     
/*  61 */     ShapeDetector.ShapeDetectorCollection shapeDetectorCollection = BlockEnderPortalFrame.e().a(paramWorld, paramBlockPosition);
/*  62 */     if (shapeDetectorCollection != null) {
/*  63 */       BlockPosition blockPosition = shapeDetectorCollection.a().a(-3, 0, -3);
/*  64 */       for (byte b1 = 0; b1 < 3; b1++) {
/*  65 */         for (byte b2 = 0; b2 < 3; b2++) {
/*  66 */           paramWorld.setTypeAndData(blockPosition.a(b1, 0, b2), Blocks.END_PORTAL.getBlockData(), 2);
/*     */         }
/*     */       } 
/*  69 */       paramWorld.a(1038, blockPosition.a(1, 0, 1), 0);
/*     */     } 
/*     */     
/*  72 */     return EnumInteractionResult.SUCCESS;
/*     */   }
/*     */ 
/*     */   
/*     */   public InteractionResultWrapper<ItemStack> a(World paramWorld, EntityHuman paramEntityHuman, EnumHand paramEnumHand) {
/*  77 */     ItemStack itemStack = paramEntityHuman.b(paramEnumHand);
/*  78 */     MovingObjectPosition movingObjectPosition = a(paramWorld, paramEntityHuman, false);
/*  79 */     if (movingObjectPosition != null && movingObjectPosition.type == MovingObjectPosition.EnumMovingObjectType.BLOCK && 
/*  80 */       paramWorld.getType(movingObjectPosition.a()).getBlock() == Blocks.END_PORTAL_FRAME) {
/*  81 */       return new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemStack);
/*     */     }
/*     */ 
/*     */     
/*  85 */     paramEntityHuman.c(paramEnumHand);
/*  86 */     if (!paramWorld.isClientSide) {
/*  87 */       BlockPosition blockPosition = ((WorldServer)paramWorld).getChunkProviderServer().a(paramWorld, "Stronghold", new BlockPosition(paramEntityHuman), false);
/*  88 */       if (blockPosition != null) {
/*  89 */         EntityEnderSignal entityEnderSignal = new EntityEnderSignal(paramWorld, paramEntityHuman.locX, paramEntityHuman.locY + (paramEntityHuman.length / 2.0F), paramEntityHuman.locZ);
/*  90 */         entityEnderSignal.a(blockPosition);
/*  91 */         paramWorld.addEntity(entityEnderSignal);
/*     */         
/*  93 */         if (paramEntityHuman instanceof EntityPlayer) {
/*  94 */           CriterionTriggers.l.a((EntityPlayer)paramEntityHuman, blockPosition);
/*     */         }
/*     */         
/*  97 */         paramWorld.a((EntityHuman)null, paramEntityHuman.locX, paramEntityHuman.locY, paramEntityHuman.locZ, SoundEffects.bc, SoundCategory.NEUTRAL, 0.5F, 0.4F / (j.nextFloat() * 0.4F + 0.8F));
/*  98 */         paramWorld.a((EntityHuman)null, 1003, new BlockPosition(paramEntityHuman), 0);
/*  99 */         if (!paramEntityHuman.abilities.canInstantlyBuild) {
/* 100 */           itemStack.subtract(1);
/*     */         }
/* 102 */         paramEntityHuman.b(StatisticList.b(this));
/* 103 */         return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemStack);
/*     */       } 
/*     */     } 
/* 106 */     return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemStack);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemEnderEye.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */