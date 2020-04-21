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
/*     */ public class ItemStep
/*     */   extends ItemBlock
/*     */ {
/*     */   private final BlockStepAbstract b;
/*     */   private final BlockStepAbstract c;
/*     */   
/*     */   public ItemStep(Block paramBlock, BlockStepAbstract paramBlockStepAbstract1, BlockStepAbstract paramBlockStepAbstract2) {
/*  24 */     super(paramBlock);
/*  25 */     this.b = paramBlockStepAbstract1;
/*  26 */     this.c = paramBlockStepAbstract2;
/*     */     
/*  28 */     setMaxDurability(0);
/*  29 */     a(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public int filterData(int paramInt) {
/*  34 */     return paramInt;
/*     */   }
/*     */ 
/*     */   
/*     */   public String a(ItemStack paramItemStack) {
/*  39 */     return this.b.b(paramItemStack.getData());
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult a(EntityHuman paramEntityHuman, World paramWorld, BlockPosition paramBlockPosition, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/*  44 */     ItemStack itemStack = paramEntityHuman.b(paramEnumHand);
/*  45 */     if (itemStack.isEmpty() || !paramEntityHuman.a(paramBlockPosition.shift(paramEnumDirection), paramEnumDirection, itemStack)) {
/*  46 */       return EnumInteractionResult.FAIL;
/*     */     }
/*     */     
/*  49 */     Comparable<?> comparable = this.b.a(itemStack);
/*  50 */     IBlockData iBlockData = paramWorld.getType(paramBlockPosition);
/*     */     
/*  52 */     if (iBlockData.getBlock() == this.b) {
/*  53 */       IBlockState<?> iBlockState = this.b.g();
/*  54 */       Comparable<?> comparable1 = (Comparable<?>)iBlockData.get((IBlockState)iBlockState);
/*  55 */       BlockStepAbstract.EnumSlabHalf enumSlabHalf = iBlockData.<BlockStepAbstract.EnumSlabHalf>get(BlockStepAbstract.HALF);
/*     */       
/*  57 */       if (((paramEnumDirection == EnumDirection.UP && enumSlabHalf == BlockStepAbstract.EnumSlabHalf.BOTTOM) || (paramEnumDirection == EnumDirection.DOWN && enumSlabHalf == BlockStepAbstract.EnumSlabHalf.TOP)) && comparable1 == comparable) {
/*  58 */         IBlockData iBlockData1 = a(iBlockState, comparable1);
/*     */         
/*  60 */         AxisAlignedBB axisAlignedBB = iBlockData1.d(paramWorld, paramBlockPosition);
/*  61 */         if (axisAlignedBB != Block.k && paramWorld.b(axisAlignedBB.a(paramBlockPosition)) && paramWorld.setTypeAndData(paramBlockPosition, iBlockData1, 11)) {
/*  62 */           SoundEffectType soundEffectType = this.c.getStepSound();
/*  63 */           paramWorld.a(paramEntityHuman, paramBlockPosition, soundEffectType.e(), SoundCategory.BLOCKS, (soundEffectType.a() + 1.0F) / 2.0F, soundEffectType.b() * 0.8F);
/*  64 */           itemStack.subtract(1);
/*  65 */           if (paramEntityHuman instanceof EntityPlayer) {
/*  66 */             CriterionTriggers.x.a((EntityPlayer)paramEntityHuman, paramBlockPosition, itemStack);
/*     */           }
/*     */         } 
/*  69 */         return EnumInteractionResult.SUCCESS;
/*     */       } 
/*     */     } 
/*     */     
/*  73 */     if (a(paramEntityHuman, itemStack, paramWorld, paramBlockPosition.shift(paramEnumDirection), comparable)) {
/*  74 */       return EnumInteractionResult.SUCCESS;
/*     */     }
/*     */     
/*  77 */     return super.a(paramEntityHuman, paramWorld, paramBlockPosition, paramEnumHand, paramEnumDirection, paramFloat1, paramFloat2, paramFloat3);
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
/*     */   private boolean a(EntityHuman paramEntityHuman, ItemStack paramItemStack, World paramWorld, BlockPosition paramBlockPosition, Object paramObject) {
/* 107 */     IBlockData iBlockData = paramWorld.getType(paramBlockPosition);
/* 108 */     if (iBlockData.getBlock() == this.b) {
/* 109 */       Comparable comparable = (Comparable)iBlockData.get((IBlockState)this.b.g());
/*     */       
/* 111 */       if (comparable == paramObject) {
/* 112 */         IBlockData iBlockData1 = a(this.b.g(), comparable);
/*     */         
/* 114 */         AxisAlignedBB axisAlignedBB = iBlockData1.d(paramWorld, paramBlockPosition);
/* 115 */         if (axisAlignedBB != Block.k && paramWorld.b(axisAlignedBB.a(paramBlockPosition)) && paramWorld.setTypeAndData(paramBlockPosition, iBlockData1, 11)) {
/* 116 */           SoundEffectType soundEffectType = this.c.getStepSound();
/* 117 */           paramWorld.a(paramEntityHuman, paramBlockPosition, soundEffectType.e(), SoundCategory.BLOCKS, (soundEffectType.a() + 1.0F) / 2.0F, soundEffectType.b() * 0.8F);
/* 118 */           paramItemStack.subtract(1);
/*     */         } 
/*     */         
/* 121 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 125 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected <T extends Comparable<T>> IBlockData a(IBlockState<T> paramIBlockState, Comparable<?> paramComparable) {
/* 130 */     return this.c.getBlockData().set(paramIBlockState, paramComparable);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemStep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */