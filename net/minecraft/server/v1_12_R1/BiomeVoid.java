/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class BiomeVoid extends BiomeBase {
/*    */   public BiomeVoid(BiomeBase.a parama) {
/*  5 */     super(parama);
/*  6 */     this.t.clear();
/*  7 */     this.u.clear();
/*  8 */     this.v.clear();
/*  9 */     this.w.clear();
/*    */     
/* 11 */     this.s = new BiomeVoidDecorator();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean i() {
/* 16 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BiomeVoid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */