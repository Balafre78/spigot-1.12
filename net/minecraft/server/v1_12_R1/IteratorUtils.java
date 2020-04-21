/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Function;
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.NoSuchElementException;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ public class IteratorUtils
/*     */ {
/*     */   public static <T> Iterable<T[]> a(Class<T> paramClass, Iterable<? extends Iterable<? extends T>> paramIterable) {
/*  18 */     return new ClassIterable<>(paramClass, b(Iterable.class, (Iterable)paramIterable));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> Iterable<List<T>> a(Iterable<? extends Iterable<? extends T>> paramIterable) {
/*  26 */     return b(a(Object.class, paramIterable));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static <T> Iterable<List<T>> b(Iterable<Object[]> paramIterable) {
/*  34 */     return Iterables.transform(paramIterable, new ArrayToList());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static <T> T[] b(Class<? super T> paramClass, Iterable<? extends T> paramIterable) {
/*     */     // Byte code:
/*     */     //   0: invokestatic newArrayList : ()Ljava/util/ArrayList;
/*     */     //   3: astore_2
/*     */     //   4: aload_1
/*     */     //   5: invokeinterface iterator : ()Ljava/util/Iterator;
/*     */     //   10: astore_3
/*     */     //   11: aload_3
/*     */     //   12: invokeinterface hasNext : ()Z
/*     */     //   17: ifeq -> 40
/*     */     //   20: aload_3
/*     */     //   21: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   26: astore #4
/*     */     //   28: aload_2
/*     */     //   29: aload #4
/*     */     //   31: invokeinterface add : (Ljava/lang/Object;)Z
/*     */     //   36: pop
/*     */     //   37: goto -> 11
/*     */     //   40: aload_2
/*     */     //   41: aload_0
/*     */     //   42: aload_2
/*     */     //   43: invokeinterface size : ()I
/*     */     //   48: invokestatic b : (Ljava/lang/Class;I)[Ljava/lang/Object;
/*     */     //   51: invokeinterface toArray : ([Ljava/lang/Object;)[Ljava/lang/Object;
/*     */     //   56: checkcast [Ljava/lang/Object;
/*     */     //   59: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #38	-> 0
/*     */     //   #39	-> 4
/*     */     //   #40	-> 28
/*     */     //   #41	-> 37
/*     */     //   #43	-> 40
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static <T> T[] b(Class<? super T> paramClass, int paramInt) {
/*  48 */     return (T[])Array.newInstance(paramClass, paramInt);
/*     */   }
/*     */   
/*     */   static class ArrayToList<T> implements Function<Object[], List<T>> {
/*     */     private ArrayToList() {}
/*     */     
/*     */     public List<T> a(@Nullable Object[] param1ArrayOfObject) {
/*  55 */       return Arrays.asList((T[])param1ArrayOfObject);
/*     */     }
/*     */   }
/*     */   
/*     */   static class ClassIterable<T> implements Iterable<T[]> {
/*     */     private final Class<T> a;
/*     */     private final Iterable<? extends T>[] b;
/*     */     
/*     */     private ClassIterable(Class<T> param1Class, Iterable<? extends T>[] param1ArrayOfIterable) {
/*  64 */       this.a = param1Class;
/*  65 */       this.b = param1ArrayOfIterable;
/*     */     }
/*     */ 
/*     */     
/*     */     public Iterator<T[]> iterator() {
/*  70 */       if (this.b.length <= 0)
/*     */       {
/*  72 */         return Collections.<T[]>singletonList((T[])IteratorUtils.a(this.a, 0)).iterator();
/*     */       }
/*  74 */       return (Iterator<T[]>)new ClassIterator(this.a, (Iterable[])this.b);
/*     */     }
/*     */ 
/*     */     
/*     */     static class ClassIterator<T>
/*     */       extends UnmodifiableIterator<T[]>
/*     */     {
/*  81 */       private int a = -2;
/*     */       
/*     */       private final Iterable<? extends T>[] b;
/*     */       private final Iterator<? extends T>[] c;
/*     */       private final T[] d;
/*     */       
/*     */       private ClassIterator(Class<T> param2Class, Iterable<? extends T>[] param2ArrayOfIterable) {
/*  88 */         this.b = param2ArrayOfIterable;
/*  89 */         this.c = (Iterator<? extends T>[])IteratorUtils.a(Iterator.class, this.b.length);
/*  90 */         for (byte b = 0; b < this.b.length; b++) {
/*  91 */           this.c[b] = param2ArrayOfIterable[b].iterator();
/*     */         }
/*  93 */         this.d = (T[])IteratorUtils.a(param2Class, this.c.length);
/*     */       }
/*     */       
/*     */       private void b() {
/*  97 */         this.a = -1;
/*     */         
/*  99 */         Arrays.fill((Object[])this.c, (Object)null);
/* 100 */         Arrays.fill((Object[])this.d, (Object)null);
/*     */       }
/*     */ 
/*     */       
/*     */       public boolean hasNext() {
/* 105 */         if (this.a == -2) {
/* 106 */           this.a = 0;
/* 107 */           for (Iterator<? extends T> iterator : this.c) {
/* 108 */             if (!iterator.hasNext()) {
/* 109 */               b();
/*     */               break;
/*     */             } 
/*     */           } 
/* 113 */           return true;
/*     */         } 
/*     */         
/* 116 */         if (this.a >= this.c.length) {
/* 117 */           for (this.a = this.c.length - 1; this.a >= 0; this.a--) {
/*     */             
/* 119 */             Iterator<? extends T> iterator = this.c[this.a];
/* 120 */             if (iterator.hasNext()) {
/*     */               break;
/*     */             }
/*     */ 
/*     */             
/* 125 */             if (this.a == 0) {
/* 126 */               b();
/*     */               
/*     */               break;
/*     */             } 
/*     */             
/* 131 */             iterator = this.b[this.a].iterator();
/* 132 */             this.c[this.a] = iterator;
/*     */ 
/*     */             
/* 135 */             if (!iterator.hasNext()) {
/* 136 */               b();
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         }
/* 141 */         return (this.a >= 0);
/*     */       }
/*     */ 
/*     */       
/*     */       public T[] a() {
/* 146 */         if (!hasNext()) {
/* 147 */           throw new NoSuchElementException();
/*     */         }
/*     */         
/* 150 */         for (; this.a < this.c.length; this.a++) {
/* 151 */           this.d[this.a] = this.c[this.a].next();
/*     */         }
/* 153 */         return (T[])this.d.clone();
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\IteratorUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */