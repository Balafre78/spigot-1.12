/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockNoDrop
/*    */   extends Block
/*    */ {
/*    */   public BlockNoDrop(Material paramMaterial) {
/* 12 */     super(paramMaterial);
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(Random paramRandom) {
/* 17 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public Item getDropType(IBlockData paramIBlockData, Random paramRandom, int paramInt) {
/* 22 */     return Items.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockNoDrop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */