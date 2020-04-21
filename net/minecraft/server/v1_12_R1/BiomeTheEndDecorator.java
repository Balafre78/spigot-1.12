/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.cache.CacheBuilder;
/*    */ import com.google.common.cache.CacheLoader;
/*    */ import com.google.common.cache.LoadingCache;
/*    */ import com.google.common.collect.ContiguousSet;
/*    */ import com.google.common.collect.DiscreteDomain;
/*    */ import com.google.common.collect.Lists;
/*    */ import com.google.common.collect.Range;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.Random;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BiomeTheEndDecorator
/*    */   extends BiomeDecorator
/*    */ {
/* 23 */   private static final LoadingCache<Long, WorldGenEnder.Spike[]> M = CacheBuilder.newBuilder().expireAfterWrite(5L, TimeUnit.MINUTES).build(new SpikeCache());
/* 24 */   private final WorldGenEnder N = new WorldGenEnder();
/*    */ 
/*    */   
/*    */   protected void a(BiomeBase paramBiomeBase, World paramWorld, Random paramRandom) {
/* 28 */     a(paramWorld, paramRandom);
/*    */     
/* 30 */     WorldGenEnder.Spike[] arrayOfSpike = a(paramWorld);
/* 31 */     for (WorldGenEnder.Spike spike : arrayOfSpike) {
/* 32 */       if (spike.a(this.b)) {
/* 33 */         this.N.a(spike);
/* 34 */         this.N.generate(paramWorld, paramRandom, new BlockPosition(spike.a(), 45, spike.b()));
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public static WorldGenEnder.Spike[] a(World paramWorld) {
/* 40 */     Random random = new Random(paramWorld.getSeed());
/* 41 */     long l = random.nextLong() & 0xFFFFL;
/* 42 */     return (WorldGenEnder.Spike[])M.getUnchecked(Long.valueOf(l));
/*    */   }
/*    */   
/*    */   static class SpikeCache
/*    */     extends CacheLoader<Long, WorldGenEnder.Spike[]> {
/*    */     public WorldGenEnder.Spike[] a(Long param1Long) throws Exception {
/* 48 */       ArrayList<?> arrayList = Lists.newArrayList((Iterable)ContiguousSet.create(Range.closedOpen(Integer.valueOf(0), Integer.valueOf(10)), DiscreteDomain.integers()));
/* 49 */       Collections.shuffle(arrayList, new Random(param1Long.longValue()));
/*    */       
/* 51 */       WorldGenEnder.Spike[] arrayOfSpike = new WorldGenEnder.Spike[10];
/* 52 */       for (byte b = 0; b < 10; b++) {
/* 53 */         int i = (int)(42.0D * Math.cos(2.0D * (-3.141592653589793D + 0.3141592653589793D * b)));
/* 54 */         int j = (int)(42.0D * Math.sin(2.0D * (-3.141592653589793D + 0.3141592653589793D * b)));
/* 55 */         int k = ((Integer)arrayList.get(b)).intValue();
/* 56 */         int m = 2 + k / 3;
/* 57 */         int n = 76 + k * 3;
/* 58 */         boolean bool = (k == 1 || k == 2) ? true : false;
/* 59 */         arrayOfSpike[b] = new WorldGenEnder.Spike(i, j, m, n, bool);
/*    */       } 
/* 61 */       return arrayOfSpike;
/*    */     }
/*    */     
/*    */     private SpikeCache() {}
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BiomeTheEndDecorator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */