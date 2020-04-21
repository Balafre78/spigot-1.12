/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BiomeTaiga
/*     */   extends BiomeBase
/*     */ {
/*  23 */   private static final WorldGenTaiga1 x = new WorldGenTaiga1();
/*  24 */   private static final WorldGenTaiga2 y = new WorldGenTaiga2(false);
/*  25 */   private static final WorldGenMegaTree z = new WorldGenMegaTree(false, false);
/*  26 */   private static final WorldGenMegaTree A = new WorldGenMegaTree(false, true); private final Type C;
/*  27 */   private static final WorldGenTaigaStructure B = new WorldGenTaigaStructure(Blocks.MOSSY_COBBLESTONE, 0);
/*     */   
/*     */   public enum Type {
/*  30 */     NORMAL, MEGA, MEGA_SPRUCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BiomeTaiga(Type paramType, BiomeBase.a parama) {
/*  36 */     super(parama);
/*  37 */     this.C = paramType;
/*     */     
/*  39 */     this.u.add(new BiomeBase.BiomeMeta((Class)EntityWolf.class, 8, 4, 4));
/*  40 */     this.u.add(new BiomeBase.BiomeMeta((Class)EntityRabbit.class, 4, 2, 3));
/*     */     
/*  42 */     this.s.z = 10;
/*  43 */     if (paramType == Type.MEGA || paramType == Type.MEGA_SPRUCE) {
/*  44 */       this.s.C = 7;
/*  45 */       this.s.D = 1;
/*  46 */       this.s.E = 3;
/*     */     } else {
/*  48 */       this.s.C = 1;
/*  49 */       this.s.E = 1;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldGenTreeAbstract a(Random paramRandom) {
/*  55 */     if ((this.C == Type.MEGA || this.C == Type.MEGA_SPRUCE) && paramRandom.nextInt(3) == 0) {
/*  56 */       if (this.C == Type.MEGA_SPRUCE || paramRandom.nextInt(13) == 0) {
/*  57 */         return A;
/*     */       }
/*  59 */       return z;
/*     */     } 
/*  61 */     if (paramRandom.nextInt(3) == 0) {
/*  62 */       return x;
/*     */     }
/*  64 */     return y;
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldGenerator b(Random paramRandom) {
/*  69 */     if (paramRandom.nextInt(5) > 0) {
/*  70 */       return new WorldGenGrass(BlockLongGrass.EnumTallGrassType.FERN);
/*     */     }
/*  72 */     return new WorldGenGrass(BlockLongGrass.EnumTallGrassType.GRASS);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World paramWorld, Random paramRandom, BlockPosition paramBlockPosition) {
/*  77 */     if (this.C == Type.MEGA || this.C == Type.MEGA_SPRUCE) {
/*  78 */       int i = paramRandom.nextInt(3);
/*  79 */       for (byte b1 = 0; b1 < i; b1++) {
/*  80 */         int j = paramRandom.nextInt(16) + 8;
/*  81 */         int k = paramRandom.nextInt(16) + 8;
/*  82 */         BlockPosition blockPosition = paramWorld.getHighestBlockYAt(paramBlockPosition.a(j, 0, k));
/*  83 */         B.generate(paramWorld, paramRandom, blockPosition);
/*     */       } 
/*     */     } 
/*     */     
/*  87 */     l.a(BlockTallPlant.EnumTallFlowerVariants.FERN);
/*  88 */     for (byte b = 0; b < 7; b++) {
/*  89 */       int i = paramRandom.nextInt(16) + 8;
/*  90 */       int j = paramRandom.nextInt(16) + 8;
/*  91 */       int k = paramRandom.nextInt(paramWorld.getHighestBlockYAt(paramBlockPosition.a(i, 0, j)).getY() + 32);
/*  92 */       l.generate(paramWorld, paramRandom, paramBlockPosition.a(i, k, j));
/*     */     } 
/*  94 */     super.a(paramWorld, paramRandom, paramBlockPosition);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World paramWorld, Random paramRandom, ChunkSnapshot paramChunkSnapshot, int paramInt1, int paramInt2, double paramDouble) {
/*  99 */     if (this.C == Type.MEGA || this.C == Type.MEGA_SPRUCE) {
/* 100 */       this.q = Blocks.GRASS.getBlockData();
/* 101 */       this.r = Blocks.DIRT.getBlockData();
/* 102 */       if (paramDouble > 1.75D) {
/* 103 */         this.q = Blocks.DIRT.getBlockData().set(BlockDirt.VARIANT, BlockDirt.EnumDirtVariant.COARSE_DIRT);
/* 104 */       } else if (paramDouble > -0.95D) {
/* 105 */         this.q = Blocks.DIRT.getBlockData().set(BlockDirt.VARIANT, BlockDirt.EnumDirtVariant.PODZOL);
/*     */       } 
/*     */     } 
/* 108 */     b(paramWorld, paramRandom, paramChunkSnapshot, paramInt1, paramInt2, paramDouble);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BiomeTaiga.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */