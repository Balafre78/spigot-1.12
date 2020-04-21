/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockHalfTransparent
/*    */   extends Block
/*    */ {
/*    */   private final boolean a;
/*    */   
/*    */   protected BlockHalfTransparent(Material paramMaterial, boolean paramBoolean) {
/* 14 */     this(paramMaterial, paramBoolean, paramMaterial.r());
/*    */   }
/*    */   
/*    */   protected BlockHalfTransparent(Material paramMaterial, boolean paramBoolean, MaterialMapColor paramMaterialMapColor) {
/* 18 */     super(paramMaterial, paramMaterialMapColor);
/* 19 */     this.a = paramBoolean;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b(IBlockData paramIBlockData) {
/* 24 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockHalfTransparent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */