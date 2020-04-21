/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IntHashMap<V>
/*     */ {
/*  18 */   private final float d = 0.75F;
/*  19 */   private int c = 12;
/*  20 */   private transient IntHashMapEntry<V>[] a = (IntHashMapEntry<V>[])new IntHashMapEntry[16];
/*     */   private transient int b;
/*     */   
/*     */   private static int g(int paramInt) {
/*  24 */     paramInt ^= paramInt >>> 20 ^ paramInt >>> 12;
/*  25 */     return paramInt ^ paramInt >>> 7 ^ paramInt >>> 4;
/*     */   }
/*     */   
/*     */   private static int a(int paramInt1, int paramInt2) {
/*  29 */     return paramInt1 & paramInt2 - 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public V get(int paramInt) {
/*  42 */     int i = g(paramInt);
/*  43 */     for (IntHashMapEntry<V> intHashMapEntry = this.a[a(i, this.a.length)]; intHashMapEntry != null; intHashMapEntry = intHashMapEntry.c) {
/*  44 */       if (intHashMapEntry.a == paramInt) {
/*  45 */         return intHashMapEntry.b;
/*     */       }
/*     */     } 
/*  48 */     return null;
/*     */   }
/*     */   
/*     */   public boolean b(int paramInt) {
/*  52 */     return (c(paramInt) != null);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   final IntHashMapEntry<V> c(int paramInt) {
/*  57 */     int i = g(paramInt);
/*  58 */     for (IntHashMapEntry<V> intHashMapEntry = this.a[a(i, this.a.length)]; intHashMapEntry != null; intHashMapEntry = intHashMapEntry.c) {
/*  59 */       if (intHashMapEntry.a == paramInt) {
/*  60 */         return intHashMapEntry;
/*     */       }
/*     */     } 
/*  63 */     return null;
/*     */   }
/*     */   
/*     */   public void a(int paramInt, V paramV) {
/*  67 */     int i = g(paramInt);
/*  68 */     int j = a(i, this.a.length);
/*  69 */     for (IntHashMapEntry<V> intHashMapEntry = this.a[j]; intHashMapEntry != null; intHashMapEntry = intHashMapEntry.c) {
/*  70 */       if (intHashMapEntry.a == paramInt) {
/*  71 */         intHashMapEntry.b = paramV;
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*  76 */     a(i, paramInt, paramV, j);
/*     */   }
/*     */ 
/*     */   
/*     */   private void h(int paramInt) {
/*  81 */     IntHashMapEntry<V>[] arrayOfIntHashMapEntry = this.a;
/*  82 */     int i = arrayOfIntHashMapEntry.length;
/*  83 */     if (i == 1073741824) {
/*  84 */       this.c = Integer.MAX_VALUE;
/*     */       
/*     */       return;
/*     */     } 
/*  88 */     IntHashMapEntry[] arrayOfIntHashMapEntry1 = new IntHashMapEntry[paramInt];
/*  89 */     a((IntHashMapEntry<V>[])arrayOfIntHashMapEntry1);
/*  90 */     this.a = (IntHashMapEntry<V>[])arrayOfIntHashMapEntry1;
/*  91 */     this.c = (int)(paramInt * this.d);
/*     */   }
/*     */   
/*     */   private void a(IntHashMapEntry<V>[] paramArrayOfIntHashMapEntry) {
/*  95 */     IntHashMapEntry<V>[] arrayOfIntHashMapEntry = this.a;
/*  96 */     int i = paramArrayOfIntHashMapEntry.length;
/*  97 */     for (byte b = 0; b < arrayOfIntHashMapEntry.length; b++) {
/*  98 */       IntHashMapEntry<V> intHashMapEntry = arrayOfIntHashMapEntry[b];
/*  99 */       if (intHashMapEntry != null) {
/* 100 */         arrayOfIntHashMapEntry[b] = null;
/*     */         do {
/* 102 */           IntHashMapEntry<V> intHashMapEntry1 = intHashMapEntry.c;
/* 103 */           int j = a(intHashMapEntry.d, i);
/* 104 */           intHashMapEntry.c = paramArrayOfIntHashMapEntry[j];
/* 105 */           paramArrayOfIntHashMapEntry[j] = intHashMapEntry;
/* 106 */           intHashMapEntry = intHashMapEntry1;
/* 107 */         } while (intHashMapEntry != null);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public V d(int paramInt) {
/* 114 */     IntHashMapEntry<V> intHashMapEntry = e(paramInt);
/* 115 */     return (intHashMapEntry == null) ? null : intHashMapEntry.b;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   final IntHashMapEntry<V> e(int paramInt) {
/* 120 */     int i = g(paramInt);
/* 121 */     int j = a(i, this.a.length);
/* 122 */     IntHashMapEntry<V> intHashMapEntry1 = this.a[j];
/* 123 */     IntHashMapEntry<V> intHashMapEntry2 = intHashMapEntry1;
/*     */     
/* 125 */     while (intHashMapEntry2 != null) {
/* 126 */       IntHashMapEntry<V> intHashMapEntry = intHashMapEntry2.c;
/* 127 */       if (intHashMapEntry2.a == paramInt) {
/* 128 */         this.b--;
/* 129 */         if (intHashMapEntry1 == intHashMapEntry2) {
/* 130 */           this.a[j] = intHashMapEntry;
/*     */         } else {
/* 132 */           intHashMapEntry1.c = intHashMapEntry;
/*     */         } 
/* 134 */         return intHashMapEntry2;
/*     */       } 
/* 136 */       intHashMapEntry1 = intHashMapEntry2;
/* 137 */       intHashMapEntry2 = intHashMapEntry;
/*     */     } 
/*     */     
/* 140 */     return intHashMapEntry2;
/*     */   }
/*     */   
/*     */   public void c() {
/* 144 */     IntHashMapEntry<V>[] arrayOfIntHashMapEntry = this.a;
/* 145 */     for (byte b = 0; b < arrayOfIntHashMapEntry.length; b++) {
/* 146 */       arrayOfIntHashMapEntry[b] = null;
/*     */     }
/* 148 */     this.b = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class IntHashMapEntry<V>
/*     */   {
/*     */     final int a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     V b;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     IntHashMapEntry<V> c;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     final int d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     IntHashMapEntry(int param1Int1, int param1Int2, V param1V, IntHashMapEntry<V> param1IntHashMapEntry) {
/* 195 */       this.b = param1V;
/* 196 */       this.c = param1IntHashMapEntry;
/* 197 */       this.a = param1Int2;
/* 198 */       this.d = param1Int1;
/*     */     }
/*     */     
/*     */     public final int a() {
/* 202 */       return this.a;
/*     */     }
/*     */     
/*     */     public final V b() {
/* 206 */       return this.b;
/*     */     }
/*     */ 
/*     */     
/*     */     public final boolean equals(Object param1Object) {
/* 211 */       if (!(param1Object instanceof IntHashMapEntry)) {
/* 212 */         return false;
/*     */       }
/* 214 */       IntHashMapEntry<Object> intHashMapEntry = (IntHashMapEntry)param1Object;
/* 215 */       if (this.a == intHashMapEntry.a) {
/* 216 */         V v1 = b();
/* 217 */         V v2 = (V)intHashMapEntry.b();
/* 218 */         if (v1 == v2 || (v1 != null && v1.equals(v2))) {
/* 219 */           return true;
/*     */         }
/*     */       } 
/* 222 */       return false;
/*     */     }
/*     */     
/*     */     public final int hashCode() {
/* 226 */       return IntHashMap.f(this.a);
/*     */     }
/*     */     
/*     */     public final String toString() {
/* 230 */       return a() + "=" + b();
/*     */     }
/*     */   }
/*     */   
/*     */   private void a(int paramInt1, int paramInt2, V paramV, int paramInt3) {
/* 235 */     IntHashMapEntry<V> intHashMapEntry = this.a[paramInt3];
/* 236 */     this.a[paramInt3] = new IntHashMapEntry<>(paramInt1, paramInt2, paramV, intHashMapEntry);
/* 237 */     if (this.b++ >= this.c)
/* 238 */       h(2 * this.a.length); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\IntHashMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */