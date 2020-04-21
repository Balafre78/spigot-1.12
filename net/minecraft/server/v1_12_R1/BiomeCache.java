/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.longs.Long2ObjectMap;
/*    */ import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BiomeCache
/*    */ {
/*    */   private final WorldChunkManager a;
/*    */   private long b;
/*    */   
/*    */   public class a
/*    */   {
/* 20 */     public BiomeBase[] a = new BiomeBase[256];
/*    */     
/*    */     public int b;
/*    */     
/*    */     public a(BiomeCache this$0, int param1Int1, int param1Int2) {
/* 25 */       this.b = param1Int1;
/* 26 */       this.c = param1Int2;
/* 27 */       BiomeCache.a(this$0).a(this.a, param1Int1 << 4, param1Int2 << 4, 16, 16, false);
/*    */     }
/*    */     public int c; public long d;
/*    */     public BiomeBase a(int param1Int1, int param1Int2) {
/* 31 */       return this.a[param1Int1 & 0xF | (param1Int2 & 0xF) << 4];
/*    */     }
/*    */   }
/*    */   
/* 35 */   private final Long2ObjectMap<a> c = (Long2ObjectMap<a>)new Long2ObjectOpenHashMap(4096);
/* 36 */   private final List<a> d = Lists.newArrayList();
/*    */   
/*    */   public BiomeCache(WorldChunkManager paramWorldChunkManager) {
/* 39 */     this.a = paramWorldChunkManager;
/*    */   }
/*    */   
/*    */   public a a(int paramInt1, int paramInt2) {
/* 43 */     paramInt1 >>= 4;
/* 44 */     paramInt2 >>= 4;
/* 45 */     long l = paramInt1 & 0xFFFFFFFFL | (paramInt2 & 0xFFFFFFFFL) << 32L;
/* 46 */     a a = (a)this.c.get(l);
/* 47 */     if (a == null) {
/* 48 */       a = new a(this, paramInt1, paramInt2);
/* 49 */       this.c.put(l, a);
/* 50 */       this.d.add(a);
/*    */     } 
/* 52 */     a.d = MinecraftServer.aw();
/* 53 */     return a;
/*    */   }
/*    */   
/*    */   public BiomeBase a(int paramInt1, int paramInt2, BiomeBase paramBiomeBase) {
/* 57 */     BiomeBase biomeBase = a(paramInt1, paramInt2).a(paramInt1, paramInt2);
/* 58 */     return (biomeBase == null) ? paramBiomeBase : biomeBase;
/*    */   }
/*    */   
/*    */   public void a() {
/* 62 */     long l1 = MinecraftServer.aw();
/* 63 */     long l2 = l1 - this.b;
/* 64 */     if (l2 > 7500L || l2 < 0L) {
/* 65 */       this.b = l1;
/*    */       
/* 67 */       for (byte b = 0; b < this.d.size(); b++) {
/* 68 */         a a = this.d.get(b);
/* 69 */         long l = l1 - a.d;
/* 70 */         if (l > 30000L || l < 0L) {
/* 71 */           this.d.remove(b--);
/* 72 */           long l3 = a.b & 0xFFFFFFFFL | (a.c & 0xFFFFFFFFL) << 32L;
/* 73 */           this.c.remove(l3);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public BiomeBase[] b(int paramInt1, int paramInt2) {
/* 80 */     return (a(paramInt1, paramInt2)).a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BiomeCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */