/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.base.Predicate;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ public class MaterialPredicate
/*    */   implements Predicate<IBlockData>
/*    */ {
/*    */   private final Material a;
/*    */   
/*    */   private MaterialPredicate(Material paramMaterial) {
/* 13 */     this.a = paramMaterial;
/*    */   }
/*    */   
/*    */   public static MaterialPredicate a(Material paramMaterial) {
/* 17 */     return new MaterialPredicate(paramMaterial);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(@Nullable IBlockData paramIBlockData) {
/* 22 */     return (paramIBlockData != null && paramIBlockData.getMaterial() == this.a);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\MaterialPredicate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */