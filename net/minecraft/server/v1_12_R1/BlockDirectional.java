/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class BlockDirectional
/*    */   extends Block
/*    */ {
/*  8 */   public static final BlockStateDirection FACING = BlockStateDirection.of("facing");
/*    */   
/*    */   protected BlockDirectional(Material paramMaterial) {
/* 11 */     super(paramMaterial);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockDirectional.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */