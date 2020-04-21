/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BiomeDesert
/*    */   extends BiomeBase
/*    */ {
/*    */   public BiomeDesert(BiomeBase.a parama) {
/* 18 */     super(parama);
/*    */     
/* 20 */     this.u.clear();
/* 21 */     this.q = Blocks.SAND.getBlockData();
/* 22 */     this.r = Blocks.SAND.getBlockData();
/*    */     
/* 24 */     this.s.z = -999;
/* 25 */     this.s.D = 2;
/* 26 */     this.s.F = 50;
/* 27 */     this.s.G = 10;
/*    */     
/* 29 */     this.u.clear();
/* 30 */     this.u.add(new BiomeBase.BiomeMeta((Class)EntityRabbit.class, 4, 2, 3));
/*    */ 
/*    */     
/* 33 */     Iterator<BiomeBase.BiomeMeta> iterator = this.t.iterator();
/* 34 */     while (iterator.hasNext()) {
/* 35 */       BiomeBase.BiomeMeta biomeMeta = iterator.next();
/* 36 */       if (biomeMeta.b == EntityZombie.class || biomeMeta.b == EntityZombieVillager.class) {
/* 37 */         iterator.remove();
/*    */       }
/*    */     } 
/*    */     
/* 41 */     this.t.add(new BiomeBase.BiomeMeta((Class)EntityZombie.class, 19, 4, 4));
/* 42 */     this.t.add(new BiomeBase.BiomeMeta((Class)EntityZombieVillager.class, 1, 1, 1));
/* 43 */     this.t.add(new BiomeBase.BiomeMeta((Class)EntityZombieHusk.class, 80, 4, 4));
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(World paramWorld, Random paramRandom, BlockPosition paramBlockPosition) {
/* 48 */     super.a(paramWorld, paramRandom, paramBlockPosition);
/*    */     
/* 50 */     if (paramRandom.nextInt(1000) == 0) {
/* 51 */       int i = paramRandom.nextInt(16) + 8;
/* 52 */       int j = paramRandom.nextInt(16) + 8;
/* 53 */       BlockPosition blockPosition = paramWorld.getHighestBlockYAt(paramBlockPosition.a(i, 0, j)).up();
/*    */       
/* 55 */       (new WorldGenDesertWell()).generate(paramWorld, paramRandom, blockPosition);
/*    */     } 
/*    */     
/* 58 */     if (paramRandom.nextInt(64) == 0)
/* 59 */       (new WorldGenFossils()).generate(paramWorld, paramRandom, paramBlockPosition); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BiomeDesert.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */