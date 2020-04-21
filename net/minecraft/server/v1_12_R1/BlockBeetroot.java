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
/*    */ public class BlockBeetroot
/*    */   extends BlockCrops
/*    */ {
/* 17 */   public static final BlockStateInteger a = BlockStateInteger.of("age", 0, 3);
/*    */   
/* 19 */   private static final AxisAlignedBB[] d = new AxisAlignedBB[] { new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D) };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected BlockStateInteger e() {
/* 28 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public int g() {
/* 33 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   protected Item h() {
/* 38 */     return Items.BEETROOT_SEEDS;
/*    */   }
/*    */ 
/*    */   
/*    */   protected Item i() {
/* 43 */     return Items.BEETROOT;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void b(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, Random paramRandom) {
/* 49 */     if (paramRandom.nextInt(3) == 0) {
/*    */       
/* 51 */       e(paramWorld, paramBlockPosition, paramIBlockData);
/*    */     } else {
/* 53 */       super.b(paramWorld, paramBlockPosition, paramIBlockData, paramRandom);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected int b(World paramWorld) {
/* 59 */     return super.b(paramWorld) / 3;
/*    */   }
/*    */ 
/*    */   
/*    */   protected BlockStateList getStateList() {
/* 64 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { a });
/*    */   }
/*    */ 
/*    */   
/*    */   public AxisAlignedBB b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 69 */     return d[((Integer)paramIBlockData.get(e())).intValue()];
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockBeetroot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */