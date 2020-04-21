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
/*     */ public class BiomeForest
/*     */   extends BiomeBase
/*     */ {
/*     */   public enum Type
/*     */   {
/*  19 */     NORMAL, FLOWER, BIRCH, ROOFED;
/*     */   }
/*     */   
/*  22 */   protected static final WorldGenForest x = new WorldGenForest(false, true);
/*  23 */   protected static final WorldGenForest y = new WorldGenForest(false, false);
/*  24 */   protected static final WorldGenForestTree z = new WorldGenForestTree(false);
/*     */   
/*     */   private final Type A;
/*     */   
/*     */   public BiomeForest(Type paramType, BiomeBase.a parama) {
/*  29 */     super(parama);
/*  30 */     this.A = paramType;
/*  31 */     this.s.z = 10;
/*  32 */     this.s.C = 2;
/*     */     
/*  34 */     if (this.A == Type.FLOWER) {
/*  35 */       this.s.z = 6;
/*  36 */       this.s.B = 100;
/*  37 */       this.s.C = 1;
/*  38 */       this.u.add(new BiomeBase.BiomeMeta((Class)EntityRabbit.class, 4, 2, 3));
/*     */     } 
/*     */     
/*  41 */     if (this.A == Type.NORMAL) {
/*  42 */       this.u.add(new BiomeBase.BiomeMeta((Class)EntityWolf.class, 5, 4, 4));
/*     */     }
/*     */     
/*  45 */     if (this.A == Type.ROOFED) {
/*  46 */       this.s.z = -999;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldGenTreeAbstract a(Random paramRandom) {
/*  52 */     if (this.A == Type.ROOFED && paramRandom.nextInt(3) > 0) {
/*  53 */       return z;
/*     */     }
/*  55 */     if (this.A == Type.BIRCH || paramRandom.nextInt(5) == 0) {
/*  56 */       return y;
/*     */     }
/*     */     
/*  59 */     if (paramRandom.nextInt(10) == 0) {
/*  60 */       return n;
/*     */     }
/*  62 */     return m;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockFlowers.EnumFlowerVarient a(Random paramRandom, BlockPosition paramBlockPosition) {
/*  67 */     if (this.A == Type.FLOWER) {
/*  68 */       double d = MathHelper.a((1.0D + k.a(paramBlockPosition.getX() / 48.0D, paramBlockPosition.getZ() / 48.0D)) / 2.0D, 0.0D, 0.9999D);
/*  69 */       BlockFlowers.EnumFlowerVarient enumFlowerVarient = BlockFlowers.EnumFlowerVarient.values()[(int)(d * (BlockFlowers.EnumFlowerVarient.values()).length)];
/*  70 */       if (enumFlowerVarient == BlockFlowers.EnumFlowerVarient.BLUE_ORCHID) {
/*  71 */         return BlockFlowers.EnumFlowerVarient.POPPY;
/*     */       }
/*  73 */       return enumFlowerVarient;
/*     */     } 
/*     */     
/*  76 */     return super.a(paramRandom, paramBlockPosition);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World paramWorld, Random paramRandom, BlockPosition paramBlockPosition) {
/*  81 */     if (this.A == Type.ROOFED) {
/*  82 */       b(paramWorld, paramRandom, paramBlockPosition);
/*     */     }
/*  84 */     int i = paramRandom.nextInt(5) - 3;
/*  85 */     if (this.A == Type.FLOWER) {
/*  86 */       i += 2;
/*     */     }
/*  88 */     a(paramWorld, paramRandom, paramBlockPosition, i);
/*  89 */     super.a(paramWorld, paramRandom, paramBlockPosition);
/*     */   }
/*     */   
/*     */   protected void b(World paramWorld, Random paramRandom, BlockPosition paramBlockPosition) {
/*  93 */     for (byte b = 0; b < 4; b++) {
/*  94 */       for (byte b1 = 0; b1 < 4; b1++) {
/*  95 */         int i = b * 4 + 1 + 8 + paramRandom.nextInt(3);
/*  96 */         int j = b1 * 4 + 1 + 8 + paramRandom.nextInt(3);
/*     */         
/*  98 */         BlockPosition blockPosition = paramWorld.getHighestBlockYAt(paramBlockPosition.a(i, 0, j));
/*  99 */         if (paramRandom.nextInt(20) == 0) {
/* 100 */           WorldGenHugeMushroom worldGenHugeMushroom = new WorldGenHugeMushroom();
/* 101 */           worldGenHugeMushroom.generate(paramWorld, paramRandom, blockPosition);
/*     */         } else {
/* 103 */           WorldGenTreeAbstract worldGenTreeAbstract = a(paramRandom);
/* 104 */           worldGenTreeAbstract.e();
/* 105 */           if (worldGenTreeAbstract.generate(paramWorld, paramRandom, blockPosition)) {
/* 106 */             worldGenTreeAbstract.a(paramWorld, paramRandom, blockPosition);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void a(World paramWorld, Random paramRandom, BlockPosition paramBlockPosition, int paramInt) {
/* 114 */     for (byte b = 0; b < paramInt; b++) {
/* 115 */       int i = paramRandom.nextInt(3);
/* 116 */       if (i == 0) {
/* 117 */         l.a(BlockTallPlant.EnumTallFlowerVariants.SYRINGA);
/* 118 */       } else if (i == 1) {
/* 119 */         l.a(BlockTallPlant.EnumTallFlowerVariants.ROSE);
/* 120 */       } else if (i == 2) {
/* 121 */         l.a(BlockTallPlant.EnumTallFlowerVariants.PAEONIA);
/*     */       } 
/*     */       
/* 124 */       byte b1 = 0;
/* 125 */       int j = paramRandom.nextInt(16) + 8;
/* 126 */       int k = paramRandom.nextInt(16) + 8;
/* 127 */       int m = paramRandom.nextInt(paramWorld.getHighestBlockYAt(paramBlockPosition.a(j, 0, k)).getY() + 32);
/*     */       
/* 129 */       for (; b1 < 5 && !l.generate(paramWorld, paramRandom, new BlockPosition(paramBlockPosition.getX() + j, m, paramBlockPosition.getZ() + k)); b1++);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Class<? extends BiomeBase> g() {
/* 138 */     return (Class)BiomeForest.class;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BiomeForest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */