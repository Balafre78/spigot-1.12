/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BiomeHell
/*    */   extends BiomeBase
/*    */ {
/*    */   public BiomeHell(BiomeBase.a parama) {
/* 10 */     super(parama);
/* 11 */     this.t.clear();
/* 12 */     this.u.clear();
/* 13 */     this.v.clear();
/* 14 */     this.w.clear();
/*    */     
/* 16 */     this.t.add(new BiomeBase.BiomeMeta((Class)EntityGhast.class, 50, 4, 4));
/* 17 */     this.t.add(new BiomeBase.BiomeMeta((Class)EntityPigZombie.class, 100, 4, 4));
/* 18 */     this.t.add(new BiomeBase.BiomeMeta((Class)EntityMagmaCube.class, 2, 4, 4));
/* 19 */     this.t.add(new BiomeBase.BiomeMeta((Class)EntityEnderman.class, 1, 4, 4));
/*    */     
/* 21 */     this.s = new BiomeDecoratorHell();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BiomeHell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */