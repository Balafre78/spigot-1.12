/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Map;
/*     */ import java.util.Random;
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
/*     */ public abstract class DefinedStructurePiece
/*     */   extends StructurePiece
/*     */ {
/*  19 */   private static final DefinedStructureInfo d = new DefinedStructureInfo();
/*     */   
/*     */   protected DefinedStructure a;
/*  22 */   protected DefinedStructureInfo b = d.a(true).a(Blocks.AIR);
/*     */   
/*     */   protected BlockPosition c;
/*     */ 
/*     */   
/*     */   public DefinedStructurePiece() {}
/*     */   
/*     */   public DefinedStructurePiece(int paramInt) {
/*  30 */     super(paramInt);
/*     */   }
/*     */   
/*     */   protected void a(DefinedStructure paramDefinedStructure, BlockPosition paramBlockPosition, DefinedStructureInfo paramDefinedStructureInfo) {
/*  34 */     this.a = paramDefinedStructure;
/*  35 */     a(EnumDirection.NORTH);
/*  36 */     this.c = paramBlockPosition;
/*  37 */     this.b = paramDefinedStructureInfo;
/*     */     
/*  39 */     b();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(NBTTagCompound paramNBTTagCompound) {
/*  44 */     paramNBTTagCompound.setInt("TPX", this.c.getX());
/*  45 */     paramNBTTagCompound.setInt("TPY", this.c.getY());
/*  46 */     paramNBTTagCompound.setInt("TPZ", this.c.getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(NBTTagCompound paramNBTTagCompound, DefinedStructureManager paramDefinedStructureManager) {
/*  51 */     this.c = new BlockPosition(paramNBTTagCompound.getInt("TPX"), paramNBTTagCompound.getInt("TPY"), paramNBTTagCompound.getInt("TPZ"));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox) {
/*  56 */     this.b.a(paramStructureBoundingBox);
/*     */     
/*  58 */     this.a.a(paramWorld, this.c, this.b, 18);
/*     */     
/*  60 */     Map<BlockPosition, String> map = this.a.a(this.c, this.b);
/*  61 */     for (Map.Entry<BlockPosition, String> entry : map.entrySet()) {
/*  62 */       String str = (String)entry.getValue();
/*  63 */       a(str, (BlockPosition)entry.getKey(), paramWorld, paramRandom, paramStructureBoundingBox);
/*     */     } 
/*     */     
/*  66 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void b() {
/*     */     BlockPosition blockPosition2;
/*  72 */     EnumBlockRotation enumBlockRotation = this.b.c();
/*  73 */     BlockPosition blockPosition1 = this.a.a(enumBlockRotation);
/*  74 */     EnumBlockMirror enumBlockMirror = this.b.b();
/*  75 */     this.l = new StructureBoundingBox(0, 0, 0, blockPosition1.getX(), blockPosition1.getY() - 1, blockPosition1.getZ());
/*     */     
/*  77 */     switch (null.a[enumBlockRotation.ordinal()]) {
/*     */ 
/*     */       
/*     */       case 2:
/*  81 */         this.l.a(-blockPosition1.getX(), 0, 0);
/*     */         break;
/*     */       case 3:
/*  84 */         this.l.a(0, 0, -blockPosition1.getZ());
/*     */         break;
/*     */       case 4:
/*  87 */         this.l.a(-blockPosition1.getX(), 0, -blockPosition1.getZ());
/*     */         break;
/*     */     } 
/*  90 */     switch (null.b[enumBlockMirror.ordinal()]) {
/*     */ 
/*     */       
/*     */       case 2:
/*  94 */         blockPosition2 = BlockPosition.ZERO;
/*  95 */         if (enumBlockRotation == EnumBlockRotation.CLOCKWISE_90 || enumBlockRotation == EnumBlockRotation.COUNTERCLOCKWISE_90) {
/*  96 */           blockPosition2 = blockPosition2.shift(enumBlockRotation.a(EnumDirection.WEST), blockPosition1.getZ());
/*  97 */         } else if (enumBlockRotation == EnumBlockRotation.CLOCKWISE_180) {
/*  98 */           blockPosition2 = blockPosition2.shift(EnumDirection.EAST, blockPosition1.getX());
/*     */         } else {
/* 100 */           blockPosition2 = blockPosition2.shift(EnumDirection.WEST, blockPosition1.getX());
/*     */         } 
/* 102 */         this.l.a(blockPosition2.getX(), 0, blockPosition2.getZ());
/*     */         break;
/*     */       
/*     */       case 3:
/* 106 */         blockPosition2 = BlockPosition.ZERO;
/* 107 */         if (enumBlockRotation == EnumBlockRotation.CLOCKWISE_90 || enumBlockRotation == EnumBlockRotation.COUNTERCLOCKWISE_90) {
/* 108 */           blockPosition2 = blockPosition2.shift(enumBlockRotation.a(EnumDirection.NORTH), blockPosition1.getX());
/* 109 */         } else if (enumBlockRotation == EnumBlockRotation.CLOCKWISE_180) {
/* 110 */           blockPosition2 = blockPosition2.shift(EnumDirection.SOUTH, blockPosition1.getZ());
/*     */         } else {
/* 112 */           blockPosition2 = blockPosition2.shift(EnumDirection.NORTH, blockPosition1.getZ());
/*     */         } 
/* 114 */         this.l.a(blockPosition2.getX(), 0, blockPosition2.getZ());
/*     */         break;
/*     */     } 
/*     */     
/* 118 */     this.l.a(this.c.getX(), this.c.getY(), this.c.getZ());
/*     */   }
/*     */   protected abstract void a(String paramString, BlockPosition paramBlockPosition, World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox);
/*     */   
/*     */   public void a(int paramInt1, int paramInt2, int paramInt3) {
/* 123 */     super.a(paramInt1, paramInt2, paramInt3);
/* 124 */     this.c = this.c.a(paramInt1, paramInt2, paramInt3);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DefinedStructurePiece.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */