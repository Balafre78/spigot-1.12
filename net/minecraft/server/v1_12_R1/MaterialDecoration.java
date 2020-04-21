/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class MaterialDecoration extends Material {
/*    */   public MaterialDecoration(MaterialMapColor paramMaterialMapColor) {
/*  5 */     super(paramMaterialMapColor);
/*  6 */     p();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBuildable() {
/* 11 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean blocksLight() {
/* 16 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isSolid() {
/* 21 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\MaterialDecoration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */