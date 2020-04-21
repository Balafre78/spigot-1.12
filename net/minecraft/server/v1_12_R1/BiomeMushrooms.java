/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ public class BiomeMushrooms
/*    */   extends BiomeBase
/*    */ {
/*    */   public BiomeMushrooms(BiomeBase.a parama) {
/*  8 */     super(parama);
/*  9 */     this.s.z = -100;
/* 10 */     this.s.B = -100;
/* 11 */     this.s.C = -100;
/*    */     
/* 13 */     this.s.E = 1;
/* 14 */     this.s.K = 1;
/*    */     
/* 16 */     this.q = Blocks.MYCELIUM.getBlockData();
/*    */     
/* 18 */     this.t.clear();
/* 19 */     this.u.clear();
/* 20 */     this.v.clear();
/*    */     
/* 22 */     this.u.add(new BiomeBase.BiomeMeta((Class)EntityMushroomCow.class, 8, 4, 8));
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BiomeMushrooms.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */