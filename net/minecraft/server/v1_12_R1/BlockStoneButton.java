/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockStoneButton
/*    */   extends BlockButtonAbstract
/*    */ {
/*    */   protected BlockStoneButton() {
/* 13 */     super(false);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(@Nullable EntityHuman paramEntityHuman, World paramWorld, BlockPosition paramBlockPosition) {
/* 18 */     paramWorld.a(paramEntityHuman, paramBlockPosition, SoundEffects.hK, SoundCategory.BLOCKS, 0.3F, 0.6F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void b(World paramWorld, BlockPosition paramBlockPosition) {
/* 23 */     paramWorld.a(null, paramBlockPosition, SoundEffects.hJ, SoundCategory.BLOCKS, 0.3F, 0.5F);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockStoneButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */