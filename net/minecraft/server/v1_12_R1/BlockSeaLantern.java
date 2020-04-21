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
/*    */ public class BlockSeaLantern
/*    */   extends Block
/*    */ {
/*    */   public BlockSeaLantern(Material paramMaterial) {
/* 17 */     super(paramMaterial);
/* 18 */     a(CreativeModeTab.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(Random paramRandom) {
/* 23 */     return 2 + paramRandom.nextInt(2);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getDropCount(int paramInt, Random paramRandom) {
/* 28 */     return MathHelper.clamp(a(paramRandom) + paramRandom.nextInt(paramInt + 1), 1, 5);
/*    */   }
/*    */ 
/*    */   
/*    */   public Item getDropType(IBlockData paramIBlockData, Random paramRandom, int paramInt) {
/* 33 */     return Items.PRISMARINE_CRYSTALS;
/*    */   }
/*    */ 
/*    */   
/*    */   public MaterialMapColor c(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 38 */     return MaterialMapColor.q;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean n() {
/* 43 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockSeaLantern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */