/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class MaterialLiquid extends Material {
/*    */   public MaterialLiquid(MaterialMapColor paramMaterialMapColor) {
/*  5 */     super(paramMaterialMapColor);
/*  6 */     i();
/*  7 */     n();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isLiquid() {
/* 12 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isSolid() {
/* 17 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBuildable() {
/* 22 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\MaterialLiquid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */