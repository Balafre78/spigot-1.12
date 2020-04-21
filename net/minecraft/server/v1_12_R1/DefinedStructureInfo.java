/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefinedStructureInfo
/*     */ {
/*  15 */   private EnumBlockMirror a = EnumBlockMirror.NONE; private boolean c; @Nullable private Block d; @Nullable
/*  16 */   private ChunkCoordIntPair e; private EnumBlockRotation b = EnumBlockRotation.NONE;
/*     */   
/*     */   @Nullable
/*     */   private StructureBoundingBox f;
/*     */   
/*     */   private boolean g = true;
/*  22 */   private float h = 1.0F; @Nullable
/*     */   private Random i; @Nullable
/*     */   private Long j;
/*     */   
/*     */   public DefinedStructureInfo a() {
/*  27 */     DefinedStructureInfo definedStructureInfo = new DefinedStructureInfo();
/*  28 */     definedStructureInfo.a = this.a;
/*  29 */     definedStructureInfo.b = this.b;
/*  30 */     definedStructureInfo.c = this.c;
/*  31 */     definedStructureInfo.d = this.d;
/*  32 */     definedStructureInfo.e = this.e;
/*  33 */     definedStructureInfo.f = this.f;
/*  34 */     definedStructureInfo.g = this.g;
/*  35 */     definedStructureInfo.h = this.h;
/*  36 */     definedStructureInfo.i = this.i;
/*  37 */     definedStructureInfo.j = this.j;
/*  38 */     return definedStructureInfo;
/*     */   }
/*     */   
/*     */   public DefinedStructureInfo a(EnumBlockMirror paramEnumBlockMirror) {
/*  42 */     this.a = paramEnumBlockMirror;
/*  43 */     return this;
/*     */   }
/*     */   
/*     */   public DefinedStructureInfo a(EnumBlockRotation paramEnumBlockRotation) {
/*  47 */     this.b = paramEnumBlockRotation;
/*  48 */     return this;
/*     */   }
/*     */   
/*     */   public DefinedStructureInfo a(boolean paramBoolean) {
/*  52 */     this.c = paramBoolean;
/*  53 */     return this;
/*     */   }
/*     */   
/*     */   public DefinedStructureInfo a(Block paramBlock) {
/*  57 */     this.d = paramBlock;
/*  58 */     return this;
/*     */   }
/*     */   
/*     */   public DefinedStructureInfo a(ChunkCoordIntPair paramChunkCoordIntPair) {
/*  62 */     this.e = paramChunkCoordIntPair;
/*  63 */     return this;
/*     */   }
/*     */   
/*     */   public DefinedStructureInfo a(StructureBoundingBox paramStructureBoundingBox) {
/*  67 */     this.f = paramStructureBoundingBox;
/*  68 */     return this;
/*     */   }
/*     */   
/*     */   public DefinedStructureInfo a(@Nullable Long paramLong) {
/*  72 */     this.j = paramLong;
/*  73 */     return this;
/*     */   }
/*     */   
/*     */   public DefinedStructureInfo a(@Nullable Random paramRandom) {
/*  77 */     this.i = paramRandom;
/*  78 */     return this;
/*     */   }
/*     */   
/*     */   public DefinedStructureInfo a(float paramFloat) {
/*  82 */     this.h = paramFloat;
/*  83 */     return this;
/*     */   }
/*     */   
/*     */   public EnumBlockMirror b() {
/*  87 */     return this.a;
/*     */   }
/*     */   
/*     */   public DefinedStructureInfo b(boolean paramBoolean) {
/*  91 */     this.g = paramBoolean;
/*  92 */     return this;
/*     */   }
/*     */   
/*     */   public EnumBlockRotation c() {
/*  96 */     return this.b;
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
/*     */   public Random a(@Nullable BlockPosition paramBlockPosition) {
/* 110 */     if (this.i != null) {
/* 111 */       return this.i;
/*     */     }
/*     */     
/* 114 */     if (this.j != null) {
/* 115 */       if (this.j.longValue() == 0L) {
/* 116 */         return new Random(System.currentTimeMillis());
/*     */       }
/* 118 */       return new Random(this.j.longValue());
/*     */     } 
/*     */     
/* 121 */     if (paramBlockPosition == null) {
/* 122 */       return new Random(System.currentTimeMillis());
/*     */     }
/*     */     
/* 125 */     int i = paramBlockPosition.getX();
/* 126 */     int j = paramBlockPosition.getZ();
/* 127 */     return new Random((i * i * 4987142 + i * 5947611) + (j * j) * 4392871L + (j * 389711) ^ 0x3AD8025FL);
/*     */   }
/*     */   
/*     */   public float f() {
/* 131 */     return this.h;
/*     */   }
/*     */   
/*     */   public boolean g() {
/* 135 */     return this.c;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Block h() {
/* 140 */     return this.d;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public StructureBoundingBox i() {
/* 145 */     if (this.f == null && this.e != null) {
/* 146 */       k();
/*     */     }
/* 148 */     return this.f;
/*     */   }
/*     */   
/*     */   public boolean j() {
/* 152 */     return this.g;
/*     */   }
/*     */   
/*     */   void k() {
/* 156 */     this.f = b(this.e);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private StructureBoundingBox b(@Nullable ChunkCoordIntPair paramChunkCoordIntPair) {
/* 161 */     if (paramChunkCoordIntPair == null) {
/* 162 */       return null;
/*     */     }
/* 164 */     int i = paramChunkCoordIntPair.x * 16;
/* 165 */     int j = paramChunkCoordIntPair.z * 16;
/* 166 */     return new StructureBoundingBox(i, 0, j, i + 16 - 1, 255, j + 16 - 1);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DefinedStructureInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */