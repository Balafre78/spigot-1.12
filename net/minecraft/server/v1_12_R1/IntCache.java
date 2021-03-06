/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ import org.spigotmc.SpigotConfig;
/*    */ 
/*    */ public class IntCache {
/*  8 */   private static int a = 256;
/*  9 */   private static final List<int[]> b = Lists.newArrayList();
/* 10 */   private static final List<int[]> c = Lists.newArrayList();
/* 11 */   private static final List<int[]> d = Lists.newArrayList();
/* 12 */   private static final List<int[]> e = Lists.newArrayList();
/*    */ 
/*    */ 
/*    */   
/*    */   public static synchronized int[] a(int i) {
/* 17 */     if (i <= 256) {
/* 18 */       if (b.isEmpty()) {
/* 19 */         int[] arrayOfInt1 = new int[256];
/* 20 */         if (c.size() < SpigotConfig.intCacheLimit) c.add(arrayOfInt1); 
/* 21 */         return arrayOfInt1;
/*    */       } 
/* 23 */       int[] arrayOfInt = b.remove(b.size() - 1);
/* 24 */       if (c.size() < SpigotConfig.intCacheLimit) c.add(arrayOfInt); 
/* 25 */       return arrayOfInt;
/*    */     } 
/* 27 */     if (i > a) {
/* 28 */       a = i;
/* 29 */       d.clear();
/* 30 */       e.clear();
/* 31 */       int[] arrayOfInt = new int[a];
/* 32 */       if (e.size() < SpigotConfig.intCacheLimit) e.add(arrayOfInt); 
/* 33 */       return arrayOfInt;
/* 34 */     }  if (d.isEmpty()) {
/* 35 */       int[] arrayOfInt = new int[a];
/* 36 */       if (e.size() < SpigotConfig.intCacheLimit) e.add(arrayOfInt); 
/* 37 */       return arrayOfInt;
/*    */     } 
/* 39 */     int[] aint = d.remove(d.size() - 1);
/* 40 */     if (e.size() < SpigotConfig.intCacheLimit) e.add(aint); 
/* 41 */     return aint;
/*    */   }
/*    */ 
/*    */   
/*    */   public static synchronized void a() {
/* 46 */     if (!d.isEmpty()) {
/* 47 */       d.remove(d.size() - 1);
/*    */     }
/*    */     
/* 50 */     if (!b.isEmpty()) {
/* 51 */       b.remove(b.size() - 1);
/*    */     }
/*    */     
/* 54 */     d.addAll(e);
/* 55 */     b.addAll(c);
/* 56 */     e.clear();
/* 57 */     c.clear();
/*    */   }
/*    */   
/*    */   public static synchronized String b() {
/* 61 */     return "cache: " + d.size() + ", tcache: " + b.size() + ", allocated: " + e.size() + ", tallocated: " + c.size();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\IntCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */