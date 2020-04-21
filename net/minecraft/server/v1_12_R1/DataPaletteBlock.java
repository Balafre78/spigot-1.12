/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class DataPaletteBlock
/*     */   implements DataPaletteExpandable {
/*   7 */   private static final DataPalette d = new DataPaletteGlobal();
/*   8 */   protected static final IBlockData a = Blocks.AIR.getBlockData();
/*     */   protected DataBits b;
/*     */   protected DataPalette c;
/*     */   private int e;
/*     */   
/*     */   public DataPaletteBlock() {
/*  14 */     b(4);
/*     */   }
/*     */   
/*     */   private static int b(int i, int j, int k) {
/*  18 */     return j << 8 | k << 4 | i;
/*     */   }
/*     */   
/*     */   private void b(int i) {
/*  22 */     if (i != this.e) {
/*  23 */       this.e = i;
/*  24 */       if (this.e <= 4) {
/*  25 */         this.e = 4;
/*  26 */         this.c = new DataPaletteLinear(this.e, this);
/*  27 */       } else if (this.e <= 8) {
/*  28 */         this.c = new DataPaletteHash(this.e, this);
/*     */       } else {
/*  30 */         this.c = d;
/*  31 */         this.e = MathHelper.d(Block.REGISTRY_ID.a());
/*     */       } 
/*     */       
/*  34 */       this.c.a(a);
/*  35 */       this.b = new DataBits(this.e, 4096);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int a(int i, IBlockData iblockdata) {
/*  40 */     DataBits databits = this.b;
/*  41 */     DataPalette datapalette = this.c;
/*     */     
/*  43 */     b(i);
/*     */     
/*  45 */     for (int j = 0; j < databits.b(); j++) {
/*  46 */       IBlockData iblockdata1 = datapalette.a(databits.a(j));
/*     */       
/*  48 */       if (iblockdata1 != null) {
/*  49 */         setBlockIndex(j, iblockdata1);
/*     */       }
/*     */     } 
/*     */     
/*  53 */     return this.c.a(iblockdata);
/*     */   }
/*     */   
/*     */   public void setBlock(int i, int j, int k, IBlockData iblockdata) {
/*  57 */     setBlockIndex(b(i, j, k), iblockdata);
/*     */   }
/*     */   
/*     */   protected void setBlockIndex(int i, IBlockData iblockdata) {
/*  61 */     int j = this.c.a(iblockdata);
/*     */     
/*  63 */     this.b.a(i, j);
/*     */   }
/*     */   
/*     */   public IBlockData a(int i, int j, int k) {
/*  67 */     return a(b(i, j, k));
/*     */   }
/*     */   
/*     */   protected IBlockData a(int i) {
/*  71 */     IBlockData iblockdata = this.c.a(this.b.a(i));
/*     */     
/*  73 */     return (iblockdata == null) ? a : iblockdata;
/*     */   }
/*     */   
/*     */   public void b(PacketDataSerializer packetdataserializer) {
/*  77 */     packetdataserializer.writeByte(this.e);
/*  78 */     this.c.b(packetdataserializer);
/*  79 */     packetdataserializer.a(this.b.a());
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public NibbleArray exportData(byte[] abyte, NibbleArray nibblearray) {
/*  84 */     NibbleArray nibblearray1 = null;
/*     */     
/*  86 */     for (int i = 0; i < 4096; i++) {
/*  87 */       int j = Block.REGISTRY_ID.getId(a(i));
/*  88 */       int k = i & 0xF;
/*  89 */       int l = i >> 8 & 0xF;
/*  90 */       int i1 = i >> 4 & 0xF;
/*     */       
/*  92 */       if ((j >> 12 & 0xF) != 0) {
/*  93 */         if (nibblearray1 == null) {
/*  94 */           nibblearray1 = new NibbleArray();
/*     */         }
/*     */         
/*  97 */         nibblearray1.a(k, l, i1, j >> 12 & 0xF);
/*     */       } 
/*     */       
/* 100 */       abyte[i] = (byte)(j >> 4 & 0xFF);
/* 101 */       nibblearray.a(k, l, i1, j & 0xF);
/*     */     } 
/*     */     
/* 104 */     return nibblearray1;
/*     */   }
/*     */   
/*     */   public void a(byte[] abyte, NibbleArray nibblearray, @Nullable NibbleArray nibblearray1) {
/* 108 */     for (int i = 0; i < 4096; i++) {
/* 109 */       int j = i & 0xF;
/* 110 */       int k = i >> 8 & 0xF;
/* 111 */       int l = i >> 4 & 0xF;
/* 112 */       int i1 = (nibblearray1 == null) ? 0 : nibblearray1.a(j, k, l);
/* 113 */       int j1 = i1 << 12 | (abyte[i] & 0xFF) << 4 | nibblearray.a(j, k, l);
/*     */ 
/*     */       
/* 116 */       IBlockData data = Block.REGISTRY_ID.fromId(j1);
/* 117 */       if (data == null) {
/* 118 */         Block block = Block.getById(j1 >> 4);
/* 119 */         if (block != null) {
/*     */           try {
/* 121 */             data = block.fromLegacyData(j1 & 0xF);
/* 122 */           } catch (Exception exception) {
/* 123 */             data = block.getBlockData();
/*     */           } 
/*     */         }
/*     */       } 
/* 127 */       setBlockIndex(i, data);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a() {
/* 135 */     return 1 + this.c.a() + PacketDataSerializer.a(this.b.b()) + (this.b.a()).length * 8;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataPaletteBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */