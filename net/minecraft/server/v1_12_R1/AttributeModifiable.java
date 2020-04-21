/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class AttributeModifiable
/*     */   implements AttributeInstance {
/*     */   private final AttributeMapBase a;
/*     */   private final IAttribute b;
/*  16 */   private final Map<Integer, Set<AttributeModifier>> c = Maps.newHashMap();
/*  17 */   private final Map<String, Set<AttributeModifier>> d = Maps.newHashMap();
/*  18 */   private final Map<UUID, AttributeModifier> e = Maps.newHashMap();
/*     */   private double f;
/*     */   private boolean g = true;
/*     */   private double h;
/*     */   
/*     */   public AttributeModifiable(AttributeMapBase paramAttributeMapBase, IAttribute paramIAttribute) {
/*  24 */     this.a = paramAttributeMapBase;
/*  25 */     this.b = paramIAttribute;
/*  26 */     this.f = paramIAttribute.getDefault();
/*     */     
/*  28 */     for (byte b = 0; b < 3; b++) {
/*  29 */       this.c.put(Integer.valueOf(b), Sets.newHashSet());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public IAttribute getAttribute() {
/*  35 */     return this.b;
/*     */   }
/*     */ 
/*     */   
/*     */   public double b() {
/*  40 */     return this.f;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValue(double paramDouble) {
/*  45 */     if (paramDouble == b()) {
/*     */       return;
/*     */     }
/*  48 */     this.f = paramDouble;
/*  49 */     f();
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<AttributeModifier> a(int paramInt) {
/*  54 */     return this.c.get(Integer.valueOf(paramInt));
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<AttributeModifier> c() {
/*  59 */     HashSet<AttributeModifier> hashSet = Sets.newHashSet();
/*     */     
/*  61 */     for (byte b = 0; b < 3; b++) {
/*  62 */       hashSet.addAll(a(b));
/*     */     }
/*     */     
/*  65 */     return hashSet;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public AttributeModifier a(UUID paramUUID) {
/*  71 */     return this.e.get(paramUUID);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(AttributeModifier paramAttributeModifier) {
/*  76 */     return (this.e.get(paramAttributeModifier.a()) != null);
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
/*     */   public void b(AttributeModifier paramAttributeModifier) {
/*  88 */     if (a(paramAttributeModifier.a()) != null) {
/*  89 */       throw new IllegalArgumentException("Modifier is already applied on this attribute!");
/*     */     }
/*     */     
/*  92 */     Set<AttributeModifier> set = this.d.get(paramAttributeModifier.b());
/*     */     
/*  94 */     if (set == null) {
/*  95 */       set = Sets.newHashSet();
/*  96 */       this.d.put(paramAttributeModifier.b(), set);
/*     */     } 
/*     */     
/*  99 */     ((Set<AttributeModifier>)this.c.get(Integer.valueOf(paramAttributeModifier.c()))).add(paramAttributeModifier);
/* 100 */     set.add(paramAttributeModifier);
/* 101 */     this.e.put(paramAttributeModifier.a(), paramAttributeModifier);
/*     */     
/* 103 */     f();
/*     */   }
/*     */   
/*     */   protected void f() {
/* 107 */     this.g = true;
/* 108 */     this.a.a(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void c(AttributeModifier paramAttributeModifier) {
/* 113 */     for (byte b = 0; b < 3; b++) {
/* 114 */       Set set1 = this.c.get(Integer.valueOf(b));
/* 115 */       set1.remove(paramAttributeModifier);
/*     */     } 
/*     */     
/* 118 */     Set set = this.d.get(paramAttributeModifier.b());
/*     */     
/* 120 */     if (set != null) {
/* 121 */       set.remove(paramAttributeModifier);
/*     */       
/* 123 */       if (set.isEmpty()) {
/* 124 */         this.d.remove(paramAttributeModifier.b());
/*     */       }
/*     */     } 
/*     */     
/* 128 */     this.e.remove(paramAttributeModifier.a());
/* 129 */     f();
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
/*     */   public void b(UUID paramUUID) {
/* 153 */     AttributeModifier attributeModifier = a(paramUUID);
/* 154 */     if (attributeModifier != null) {
/* 155 */       c(attributeModifier);
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getValue() {
/* 175 */     if (this.g) {
/* 176 */       this.h = g();
/* 177 */       this.g = false;
/*     */     } 
/*     */     
/* 180 */     return this.h;
/*     */   }
/*     */   
/*     */   private double g() {
/* 184 */     double d1 = b();
/*     */     
/* 186 */     for (AttributeModifier attributeModifier : b(0)) {
/* 187 */       d1 += attributeModifier.d();
/*     */     }
/*     */     
/* 190 */     double d2 = d1;
/*     */     
/* 192 */     for (AttributeModifier attributeModifier : b(1)) {
/* 193 */       d2 += d1 * attributeModifier.d();
/*     */     }
/*     */     
/* 196 */     for (AttributeModifier attributeModifier : b(2)) {
/* 197 */       d2 *= 1.0D + attributeModifier.d();
/*     */     }
/*     */     
/* 200 */     return this.b.a(d2);
/*     */   }
/*     */   
/*     */   private Collection<AttributeModifier> b(int paramInt) {
/* 204 */     HashSet<AttributeModifier> hashSet = Sets.newHashSet(a(paramInt));
/*     */     
/* 206 */     IAttribute iAttribute = this.b.d();
/* 207 */     while (iAttribute != null) {
/* 208 */       AttributeInstance attributeInstance = this.a.a(iAttribute);
/* 209 */       if (attributeInstance != null) {
/* 210 */         hashSet.addAll(attributeInstance.a(paramInt));
/*     */       }
/* 212 */       iAttribute = iAttribute.d();
/*     */     } 
/*     */     
/* 215 */     return hashSet;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\AttributeModifiable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */