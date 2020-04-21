/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class AdvancementTree
/*     */ {
/*     */   private final Advancement a;
/*     */   private final AdvancementTree b;
/*     */   private final AdvancementTree c;
/*     */   private final int d;
/*  13 */   private final List<AdvancementTree> e = Lists.newArrayList();
/*     */   private AdvancementTree f;
/*     */   private AdvancementTree g;
/*     */   private int h;
/*     */   private float i;
/*     */   private float j;
/*     */   private float k;
/*     */   private float l;
/*     */   
/*     */   public AdvancementTree(Advancement paramAdvancement, @Nullable AdvancementTree paramAdvancementTree1, @Nullable AdvancementTree paramAdvancementTree2, int paramInt1, int paramInt2) {
/*  23 */     if (paramAdvancement.c() == null) {
/*  24 */       throw new IllegalArgumentException("Can't position an invisible advancement!");
/*     */     }
/*  26 */     this.a = paramAdvancement;
/*  27 */     this.b = paramAdvancementTree1;
/*  28 */     this.c = paramAdvancementTree2;
/*  29 */     this.d = paramInt1;
/*  30 */     this.f = this;
/*  31 */     this.h = paramInt2;
/*  32 */     this.i = -1.0F;
/*     */     
/*  34 */     AdvancementTree advancementTree = null;
/*  35 */     for (Advancement advancement : paramAdvancement.e()) {
/*  36 */       advancementTree = a(advancement, advancementTree);
/*     */     }
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private AdvancementTree a(Advancement paramAdvancement, @Nullable AdvancementTree paramAdvancementTree) {
/*  42 */     if (paramAdvancement.c() != null) {
/*  43 */       paramAdvancementTree = new AdvancementTree(paramAdvancement, this, paramAdvancementTree, this.e.size() + 1, this.h + 1);
/*  44 */       this.e.add(paramAdvancementTree);
/*     */     } else {
/*  46 */       for (Advancement advancement : paramAdvancement.e()) {
/*  47 */         paramAdvancementTree = a(advancement, paramAdvancementTree);
/*     */       }
/*     */     } 
/*  50 */     return paramAdvancementTree;
/*     */   }
/*     */   
/*     */   private void a() {
/*  54 */     if (this.e.isEmpty()) {
/*  55 */       if (this.c != null) {
/*  56 */         this.c.i++;
/*     */       } else {
/*  58 */         this.i = 0.0F;
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/*  63 */     AdvancementTree advancementTree = null;
/*  64 */     for (AdvancementTree advancementTree1 : this.e) {
/*  65 */       advancementTree1.a();
/*  66 */       advancementTree = advancementTree1.a((advancementTree == null) ? advancementTree1 : advancementTree);
/*     */     } 
/*  68 */     b();
/*     */     
/*  70 */     float f = (((AdvancementTree)this.e.get(0)).i + ((AdvancementTree)this.e.get(this.e.size() - 1)).i) / 2.0F;
/*  71 */     if (this.c != null) {
/*  72 */       this.c.i++;
/*  73 */       this.j = this.i - f;
/*     */     } else {
/*  75 */       this.i = f;
/*     */     } 
/*     */   }
/*     */   
/*     */   private float a(float paramFloat1, int paramInt, float paramFloat2) {
/*  80 */     this.i += paramFloat1;
/*  81 */     this.h = paramInt;
/*     */     
/*  83 */     if (this.i < paramFloat2) {
/*  84 */       paramFloat2 = this.i;
/*     */     }
/*     */     
/*  87 */     for (AdvancementTree advancementTree : this.e) {
/*  88 */       paramFloat2 = advancementTree.a(paramFloat1 + this.j, paramInt + 1, paramFloat2);
/*     */     }
/*     */     
/*  91 */     return paramFloat2;
/*     */   }
/*     */   
/*     */   private void a(float paramFloat) {
/*  95 */     this.i += paramFloat;
/*  96 */     for (AdvancementTree advancementTree : this.e) {
/*  97 */       advancementTree.a(paramFloat);
/*     */     }
/*     */   }
/*     */   
/*     */   private void b() {
/* 102 */     float f1 = 0.0F;
/* 103 */     float f2 = 0.0F;
/* 104 */     for (int i = this.e.size() - 1; i >= 0; i--) {
/* 105 */       AdvancementTree advancementTree = this.e.get(i);
/* 106 */       advancementTree.i += f1;
/* 107 */       advancementTree.j += f1;
/* 108 */       f2 += advancementTree.k;
/* 109 */       f1 += advancementTree.l + f2;
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private AdvancementTree c() {
/* 115 */     if (this.g != null) {
/* 116 */       return this.g;
/*     */     }
/* 118 */     if (!this.e.isEmpty()) {
/* 119 */       return this.e.get(0);
/*     */     }
/* 121 */     return null;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private AdvancementTree d() {
/* 126 */     if (this.g != null) {
/* 127 */       return this.g;
/*     */     }
/* 129 */     if (!this.e.isEmpty()) {
/* 130 */       return this.e.get(this.e.size() - 1);
/*     */     }
/* 132 */     return null;
/*     */   }
/*     */   
/*     */   private AdvancementTree a(AdvancementTree paramAdvancementTree) {
/* 136 */     if (this.c == null) {
/* 137 */       return paramAdvancementTree;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 142 */     AdvancementTree advancementTree1 = this;
/* 143 */     AdvancementTree advancementTree2 = this;
/* 144 */     AdvancementTree advancementTree3 = this.c;
/* 145 */     AdvancementTree advancementTree4 = this.b.e.get(0);
/* 146 */     float f1 = this.j;
/* 147 */     float f2 = this.j;
/* 148 */     float f3 = advancementTree3.j;
/* 149 */     float f4 = advancementTree4.j;
/*     */     
/* 151 */     while (advancementTree3.d() != null && advancementTree1.c() != null) {
/* 152 */       advancementTree3 = advancementTree3.d();
/* 153 */       advancementTree1 = advancementTree1.c();
/* 154 */       advancementTree4 = advancementTree4.c();
/* 155 */       advancementTree2 = advancementTree2.d();
/* 156 */       advancementTree2.f = this;
/* 157 */       float f = advancementTree3.i + f3 - advancementTree1.i + f1 + 1.0F;
/* 158 */       if (f > 0.0F) {
/* 159 */         advancementTree3.a(this, paramAdvancementTree).a(this, f);
/* 160 */         f1 += f;
/* 161 */         f2 += f;
/*     */       } 
/* 163 */       f3 += advancementTree3.j;
/* 164 */       f1 += advancementTree1.j;
/* 165 */       f4 += advancementTree4.j;
/* 166 */       f2 += advancementTree2.j;
/*     */     } 
/* 168 */     if (advancementTree3.d() != null && advancementTree2.d() == null) {
/* 169 */       advancementTree2.g = advancementTree3.d();
/* 170 */       advancementTree2.j += f3 - f2;
/*     */     } else {
/* 172 */       if (advancementTree1.c() != null && advancementTree4.c() == null) {
/* 173 */         advancementTree4.g = advancementTree1.c();
/* 174 */         advancementTree4.j += f1 - f4;
/*     */       } 
/* 176 */       paramAdvancementTree = this;
/*     */     } 
/*     */     
/* 179 */     return paramAdvancementTree;
/*     */   }
/*     */   
/*     */   private void a(AdvancementTree paramAdvancementTree, float paramFloat) {
/* 183 */     float f = (paramAdvancementTree.d - this.d);
/* 184 */     if (f != 0.0F) {
/* 185 */       paramAdvancementTree.k -= paramFloat / f;
/* 186 */       this.k += paramFloat / f;
/*     */     } 
/* 188 */     paramAdvancementTree.l += paramFloat;
/* 189 */     paramAdvancementTree.i += paramFloat;
/* 190 */     paramAdvancementTree.j += paramFloat;
/*     */   }
/*     */   
/*     */   private AdvancementTree a(AdvancementTree paramAdvancementTree1, AdvancementTree paramAdvancementTree2) {
/* 194 */     if (this.f != null && paramAdvancementTree1.b.e.contains(this.f)) {
/* 195 */       return this.f;
/*     */     }
/* 197 */     return paramAdvancementTree2;
/*     */   }
/*     */ 
/*     */   
/*     */   private void e() {
/* 202 */     if (this.a.c() != null) {
/* 203 */       this.a.c().a(this.h, this.i);
/*     */     }
/*     */     
/* 206 */     if (!this.e.isEmpty()) {
/* 207 */       for (AdvancementTree advancementTree : this.e) {
/* 208 */         advancementTree.e();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void a(Advancement paramAdvancement) {
/* 214 */     if (paramAdvancement.c() == null) {
/* 215 */       throw new IllegalArgumentException("Can't position children of an invisible root!");
/*     */     }
/* 217 */     AdvancementTree advancementTree = new AdvancementTree(paramAdvancement, null, null, 1, 0);
/* 218 */     advancementTree.a();
/* 219 */     float f = advancementTree.a(0.0F, 0, advancementTree.i);
/* 220 */     if (f < 0.0F) {
/* 221 */       advancementTree.a(-f);
/*     */     }
/* 223 */     advancementTree.e();
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\AdvancementTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */