/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class MaterialPortal extends Material {
/*    */   public MaterialPortal(MaterialMapColor paramMaterialMapColor) {
/*  5 */     super(paramMaterialMapColor);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBuildable() {
/* 10 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean blocksLight() {
/* 15 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isSolid() {
/* 20 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\MaterialPortal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */