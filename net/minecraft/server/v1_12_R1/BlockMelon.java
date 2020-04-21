/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockMelon
/*    */   extends Block
/*    */ {
/*    */   protected BlockMelon() {
/* 14 */     super(Material.PUMPKIN, MaterialMapColor.v);
/* 15 */     a(CreativeModeTab.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public Item getDropType(IBlockData paramIBlockData, Random paramRandom, int paramInt) {
/* 20 */     return Items.MELON;
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(Random paramRandom) {
/* 25 */     return 3 + paramRandom.nextInt(5);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getDropCount(int paramInt, Random paramRandom) {
/* 30 */     return Math.min(9, a(paramRandom) + paramRandom.nextInt(1 + paramInt));
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockMelon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */