/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.base.Predicate;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ public class BlockPredicate
/*    */   implements Predicate<IBlockData>
/*    */ {
/*    */   private final Block a;
/*    */   
/*    */   private BlockPredicate(Block paramBlock) {
/* 13 */     this.a = paramBlock;
/*    */   }
/*    */   
/*    */   public static BlockPredicate a(Block paramBlock) {
/* 17 */     return new BlockPredicate(paramBlock);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(@Nullable IBlockData paramIBlockData) {
/* 22 */     return (paramIBlockData != null && paramIBlockData.getBlock() == this.a);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockPredicate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */