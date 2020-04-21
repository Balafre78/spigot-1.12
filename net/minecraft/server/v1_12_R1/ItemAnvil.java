/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class ItemAnvil
/*    */   extends ItemMultiTexture
/*    */ {
/*    */   public ItemAnvil(Block paramBlock) {
/*  7 */     super(paramBlock, paramBlock, new String[] { "intact", "slightlyDamaged", "veryDamaged" });
/*    */   }
/*    */ 
/*    */   
/*    */   public int filterData(int paramInt) {
/* 12 */     return paramInt << 2;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemAnvil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */