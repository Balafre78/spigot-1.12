/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.collect.Collections2;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Collection;
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
/*     */ public abstract class BlockFlowers
/*     */   extends BlockPlant
/*     */ {
/*     */   protected BlockStateEnum<EnumFlowerVarient> TYPE;
/*     */   
/*     */   protected BlockFlowers() {
/*  25 */     w(this.blockStateList.getBlockData().set(g(), (e() == EnumFlowerType.RED) ? EnumFlowerVarient.POPPY : EnumFlowerVarient.DANDELION));
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  30 */     return super.b(paramIBlockData, paramIBlockAccess, paramBlockPosition).a(paramIBlockData.f(paramIBlockAccess, paramBlockPosition));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDropData(IBlockData paramIBlockData) {
/*  35 */     return ((EnumFlowerVarient)paramIBlockData.<EnumFlowerVarient>get(g())).b();
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
/*     */   public IBlockData fromLegacyData(int paramInt) {
/*  47 */     return getBlockData()
/*  48 */       .set(g(), EnumFlowerVarient.a(e(), paramInt));
/*     */   }
/*     */   
/*     */   public abstract EnumFlowerType e();
/*     */   
/*     */   public IBlockState<EnumFlowerVarient> g() {
/*  54 */     if (this.TYPE == null) {
/*  55 */       this.TYPE = BlockStateEnum.a("type", EnumFlowerVarient.class, new Predicate<EnumFlowerVarient>(this)
/*     */           {
/*     */             public boolean a(@Nullable BlockFlowers.EnumFlowerVarient param1EnumFlowerVarient) {
/*  58 */               return (param1EnumFlowerVarient.a() == this.a.e());
/*     */             }
/*     */           });
/*     */     }
/*  62 */     return this.TYPE;
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/*  67 */     return ((EnumFlowerVarient)paramIBlockData.<EnumFlowerVarient>get(g())).b();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/*  72 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { g() });
/*     */   }
/*     */   
/*     */   public enum EnumFlowerType {
/*  76 */     YELLOW,
/*  77 */     RED;
/*     */ 
/*     */     
/*     */     public BlockFlowers a() {
/*  81 */       if (this == YELLOW) {
/*  82 */         return Blocks.YELLOW_FLOWER;
/*     */       }
/*  84 */       return Blocks.RED_FLOWER;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum EnumFlowerVarient
/*     */     implements INamable {
/*  90 */     DANDELION((String)BlockFlowers.EnumFlowerType.YELLOW, 0, (BlockFlowers.EnumFlowerType)"dandelion"),
/*  91 */     POPPY((String)BlockFlowers.EnumFlowerType.RED, 0, (BlockFlowers.EnumFlowerType)"poppy"),
/*  92 */     BLUE_ORCHID((String)BlockFlowers.EnumFlowerType.RED, 1, (BlockFlowers.EnumFlowerType)"blue_orchid", "blueOrchid"),
/*  93 */     ALLIUM((String)BlockFlowers.EnumFlowerType.RED, 2, (BlockFlowers.EnumFlowerType)"allium"),
/*  94 */     HOUSTONIA((String)BlockFlowers.EnumFlowerType.RED, 3, (BlockFlowers.EnumFlowerType)"houstonia"),
/*  95 */     RED_TULIP((String)BlockFlowers.EnumFlowerType.RED, 4, (BlockFlowers.EnumFlowerType)"red_tulip", "tulipRed"),
/*  96 */     ORANGE_TULIP((String)BlockFlowers.EnumFlowerType.RED, 5, (BlockFlowers.EnumFlowerType)"orange_tulip", "tulipOrange"),
/*  97 */     WHITE_TULIP((String)BlockFlowers.EnumFlowerType.RED, 6, (BlockFlowers.EnumFlowerType)"white_tulip", "tulipWhite"),
/*  98 */     PINK_TULIP((String)BlockFlowers.EnumFlowerType.RED, 7, (BlockFlowers.EnumFlowerType)"pink_tulip", "tulipPink"),
/*  99 */     OXEYE_DAISY((String)BlockFlowers.EnumFlowerType.RED, 8, (BlockFlowers.EnumFlowerType)"oxeye_daisy", "oxeyeDaisy");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 109 */     private static final EnumFlowerVarient[][] k = new EnumFlowerVarient[(BlockFlowers.EnumFlowerType.values()).length][];
/*     */     static {
/* 111 */       for (BlockFlowers.EnumFlowerType enumFlowerType : BlockFlowers.EnumFlowerType.values()) {
/* 112 */         Collection collection = Collections2.filter(Lists.newArrayList((Object[])values()), new BlockFlowers$a$1(enumFlowerType));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 119 */         k[enumFlowerType.ordinal()] = (EnumFlowerVarient[])collection.toArray((Object[])new EnumFlowerVarient[collection.size()]);
/*     */       } 
/*     */     }
/*     */     private final BlockFlowers.EnumFlowerType l;
/*     */     private final int m;
/*     */     private final String n;
/*     */     private final String o;
/*     */     
/*     */     EnumFlowerVarient(BlockFlowers.EnumFlowerType param1EnumFlowerType, int param1Int1, String param1String1, String param1String2) {
/* 128 */       this.l = param1EnumFlowerType;
/* 129 */       this.m = param1Int1;
/* 130 */       this.n = param1String1;
/* 131 */       this.o = param1String2;
/*     */     }
/*     */     
/*     */     public BlockFlowers.EnumFlowerType a() {
/* 135 */       return this.l;
/*     */     }
/*     */     
/*     */     public int b() {
/* 139 */       return this.m;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static EnumFlowerVarient a(BlockFlowers.EnumFlowerType param1EnumFlowerType, int param1Int) {
/* 147 */       EnumFlowerVarient[] arrayOfEnumFlowerVarient = k[param1EnumFlowerType.ordinal()];
/* 148 */       if (param1Int < 0 || param1Int >= arrayOfEnumFlowerVarient.length) {
/* 149 */         param1Int = 0;
/*     */       }
/* 151 */       return arrayOfEnumFlowerVarient[param1Int];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 160 */       return this.n;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 165 */       return this.n;
/*     */     }
/*     */     
/*     */     public String d() {
/* 169 */       return this.o;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Block.EnumRandomOffset u() {
/* 175 */     return Block.EnumRandomOffset.XZ;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockFlowers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */