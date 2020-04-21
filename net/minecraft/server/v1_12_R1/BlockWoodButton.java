/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockWoodButton
/*    */   extends BlockButtonAbstract
/*    */ {
/*    */   protected BlockWoodButton() {
/* 13 */     super(true);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(@Nullable EntityHuman paramEntityHuman, World paramWorld, BlockPosition paramBlockPosition) {
/* 18 */     paramWorld.a(paramEntityHuman, paramBlockPosition, SoundEffects.jb, SoundCategory.BLOCKS, 0.3F, 0.6F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void b(World paramWorld, BlockPosition paramBlockPosition) {
/* 23 */     paramWorld.a(null, paramBlockPosition, SoundEffects.ja, SoundCategory.BLOCKS, 0.3F, 0.5F);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockWoodButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */