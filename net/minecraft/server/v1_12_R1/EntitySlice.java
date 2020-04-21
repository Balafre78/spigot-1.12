/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Iterators;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class EntitySlice<T>
/*     */   extends AbstractSet<T> {
/*  16 */   private static final Set<Class<?>> a = Sets.newConcurrentHashSet();
/*  17 */   private final Map<Class<?>, List<T>> b = Maps.newHashMap();
/*  18 */   private final Set<Class<?>> c = Sets.newIdentityHashSet();
/*     */   private final Class<T> d;
/*  20 */   private final List<T> e = Lists.newArrayList();
/*     */   
/*     */   public EntitySlice(Class<T> oclass) {
/*  23 */     this.d = oclass;
/*  24 */     this.c.add(oclass);
/*  25 */     this.b.put(oclass, this.e);
/*  26 */     Iterator<Class<?>> iterator = a.iterator();
/*     */     
/*  28 */     while (iterator.hasNext()) {
/*  29 */       Class<?> oclass1 = iterator.next();
/*     */       
/*  31 */       a(oclass1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(Class<?> oclass) {
/*  37 */     a.add(oclass);
/*  38 */     Iterator<T> iterator = this.e.iterator();
/*     */     
/*  40 */     while (iterator.hasNext()) {
/*  41 */       Object object = iterator.next();
/*     */       
/*  43 */       if (oclass.isAssignableFrom(object.getClass())) {
/*  44 */         a((T)object, oclass);
/*     */       }
/*     */     } 
/*     */     
/*  48 */     this.c.add(oclass);
/*     */   }
/*     */   
/*     */   protected Class<?> b(Class<?> oclass) {
/*  52 */     if (this.d.isAssignableFrom(oclass)) {
/*  53 */       if (!this.c.contains(oclass)) {
/*  54 */         a(oclass);
/*     */       }
/*     */       
/*  57 */       return oclass;
/*     */     } 
/*  59 */     throw new IllegalArgumentException("Don't know how to search for " + oclass);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(T t0) {
/*  64 */     Iterator<Class<?>> iterator = this.c.iterator();
/*     */     
/*  66 */     while (iterator.hasNext()) {
/*  67 */       Class<?> oclass = iterator.next();
/*     */       
/*  69 */       if (oclass.isAssignableFrom(t0.getClass())) {
/*  70 */         a(t0, oclass);
/*     */       }
/*     */     } 
/*     */     
/*  74 */     return true;
/*     */   }
/*     */   
/*     */   private void a(T t0, Class<?> oclass) {
/*  78 */     List<T> list = this.b.get(oclass);
/*     */     
/*  80 */     if (list == null) {
/*  81 */       this.b.put(oclass, Lists.newArrayList(new Object[] { t0 }));
/*     */     } else {
/*  83 */       list.add(t0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean remove(Object object) {
/*  89 */     Object object1 = object;
/*  90 */     boolean flag = false;
/*  91 */     Iterator<Class<?>> iterator = this.c.iterator();
/*     */     
/*  93 */     while (iterator.hasNext()) {
/*  94 */       Class oclass = iterator.next();
/*     */       
/*  96 */       if (oclass.isAssignableFrom(object1.getClass())) {
/*  97 */         List list = this.b.get(oclass);
/*     */         
/*  99 */         if (list != null && list.remove(object1)) {
/* 100 */           flag = true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 105 */     return flag;
/*     */   }
/*     */   
/*     */   public boolean contains(Object object) {
/* 109 */     return Iterators.contains(c(object.getClass()).iterator(), object);
/*     */   }
/*     */   
/*     */   public <S> Iterable<S> c(final Class<S> oclass) {
/* 113 */     return new Iterable<S>() {
/*     */         public Iterator<S> iterator() {
/* 115 */           List list = (List)EntitySlice.this.b.get(EntitySlice.this.b(oclass));
/*     */           
/* 117 */           if (list == null) {
/* 118 */             return Collections.emptyIterator();
/*     */           }
/* 120 */           Iterator iterator = list.iterator();
/*     */           
/* 122 */           return (Iterator<S>)Iterators.filter(iterator, oclass);
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator<T> iterator() {
/* 129 */     return this.e.isEmpty() ? Collections.<T>emptyIterator() : (Iterator<T>)Iterators.unmodifiableIterator(this.e.iterator());
/*     */   }
/*     */   
/*     */   public int size() {
/* 133 */     return this.e.size();
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntitySlice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */