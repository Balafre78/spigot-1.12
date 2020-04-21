/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class BlockFacingHorizontal
/*    */   extends Block
/*    */ {
/*  9 */   public static final BlockStateDirection FACING = BlockStateDirection.of("facing", EnumDirection.EnumDirectionLimit.HORIZONTAL);
/*    */ 
/*    */   
/*    */   protected BlockFacingHorizontal(Material paramMaterial) {
/* 13 */     super(paramMaterial);
/*    */   }
/*    */   
/*    */   protected BlockFacingHorizontal(Material paramMaterial, MaterialMapColor paramMaterialMapColor) {
/* 17 */     super(paramMaterial, paramMaterialMapColor);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockFacingHorizontal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */