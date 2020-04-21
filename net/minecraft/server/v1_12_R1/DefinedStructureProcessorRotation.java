/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Random;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ public class DefinedStructureProcessorRotation
/*    */   implements DefinedStructureProcessor
/*    */ {
/*    */   private final float a;
/*    */   private final Random b;
/*    */   
/*    */   public DefinedStructureProcessorRotation(BlockPosition paramBlockPosition, DefinedStructureInfo paramDefinedStructureInfo) {
/* 14 */     this.a = paramDefinedStructureInfo.f();
/* 15 */     this.b = paramDefinedStructureInfo.a(paramBlockPosition);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public DefinedStructure.BlockInfo a(World paramWorld, BlockPosition paramBlockPosition, DefinedStructure.BlockInfo paramBlockInfo) {
/* 21 */     if (this.a >= 1.0F || this.b.nextFloat() <= this.a) {
/* 22 */       return paramBlockInfo;
/*    */     }
/* 24 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DefinedStructureProcessorRotation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */