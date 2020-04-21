/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockIceFrost
/*    */   extends BlockIce
/*    */ {
/* 17 */   public static final BlockStateInteger a = BlockStateInteger.of("age", 0, 3);
/*    */   
/*    */   public BlockIceFrost() {
/* 20 */     w(this.blockStateList.getBlockData().set(a, Integer.valueOf(0)));
/*    */   }
/*    */ 
/*    */   
/*    */   public int toLegacyData(IBlockData paramIBlockData) {
/* 25 */     return ((Integer)paramIBlockData.<Integer>get(a)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData fromLegacyData(int paramInt) {
/* 30 */     return getBlockData().set(a, Integer.valueOf(MathHelper.clamp(paramInt, 0, 3)));
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, Random paramRandom) {
/* 35 */     if ((paramRandom.nextInt(3) == 0 || c(paramWorld, paramBlockPosition) < 4) && paramWorld.getLightLevel(paramBlockPosition) > 11 - ((Integer)paramIBlockData.get(a)).intValue() - paramIBlockData.c()) {
/* 36 */       a(paramWorld, paramBlockPosition, paramIBlockData, paramRandom, true);
/*    */     } else {
/* 38 */       paramWorld.a(paramBlockPosition, this, MathHelper.nextInt(paramRandom, 20, 40));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition1, Block paramBlock, BlockPosition paramBlockPosition2) {
/* 44 */     if (paramBlock == this) {
/* 45 */       int i = c(paramWorld, paramBlockPosition1);
/* 46 */       if (i < 2) {
/* 47 */         b(paramWorld, paramBlockPosition1);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   private int c(World paramWorld, BlockPosition paramBlockPosition) {
/* 53 */     byte b = 0;
/* 54 */     for (EnumDirection enumDirection : EnumDirection.values()) {
/*    */       
/* 56 */       b++;
/* 57 */       if (paramWorld.getType(paramBlockPosition.shift(enumDirection)).getBlock() == this && b >= 4) {
/* 58 */         return b;
/*    */       }
/*    */     } 
/*    */     
/* 62 */     return b;
/*    */   }
/*    */   
/*    */   protected void a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, Random paramRandom, boolean paramBoolean) {
/* 66 */     int i = ((Integer)paramIBlockData.<Integer>get(a)).intValue();
/* 67 */     if (i < 3) {
/* 68 */       paramWorld.setTypeAndData(paramBlockPosition, paramIBlockData.set(a, Integer.valueOf(i + 1)), 2);
/* 69 */       paramWorld.a(paramBlockPosition, this, MathHelper.nextInt(paramRandom, 20, 40));
/*    */     } else {
/* 71 */       b(paramWorld, paramBlockPosition);
/*    */       
/* 73 */       if (paramBoolean) {
/* 74 */         for (EnumDirection enumDirection : EnumDirection.values()) {
/* 75 */           BlockPosition blockPosition = paramBlockPosition.shift(enumDirection);
/* 76 */           IBlockData iBlockData = paramWorld.getType(blockPosition);
/* 77 */           if (iBlockData.getBlock() == this) {
/* 78 */             a(paramWorld, blockPosition, iBlockData, paramRandom, false);
/*    */           }
/*    */         } 
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected BlockStateList getStateList() {
/* 87 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { a });
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 92 */     return ItemStack.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockIceFrost.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */