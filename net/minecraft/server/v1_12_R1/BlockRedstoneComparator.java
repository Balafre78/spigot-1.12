/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.List;
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
/*     */ public class BlockRedstoneComparator
/*     */   extends BlockDiodeAbstract
/*     */   implements ITileEntity
/*     */ {
/*  35 */   public static final BlockStateBoolean POWERED = BlockStateBoolean.of("powered");
/*  36 */   public static final BlockStateEnum<EnumComparatorMode> MODE = BlockStateEnum.of("mode", EnumComparatorMode.class);
/*     */   
/*     */   public BlockRedstoneComparator(boolean paramBoolean) {
/*  39 */     super(paramBoolean);
/*  40 */     w(this.blockStateList.getBlockData().<Comparable, EnumDirection>set(FACING, EnumDirection.NORTH).<Comparable, Boolean>set(POWERED, Boolean.valueOf(false)).set(MODE, EnumComparatorMode.COMPARE));
/*  41 */     this.isTileEntity = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  46 */     return LocaleI18n.get("item.comparator.name");
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getDropType(IBlockData paramIBlockData, Random paramRandom, int paramInt) {
/*  51 */     return Items.COMPARATOR;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/*  56 */     return new ItemStack(Items.COMPARATOR);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int x(IBlockData paramIBlockData) {
/*  61 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected IBlockData y(IBlockData paramIBlockData) {
/*  66 */     Boolean bool = paramIBlockData.<Boolean>get(POWERED);
/*  67 */     EnumComparatorMode enumComparatorMode = paramIBlockData.<EnumComparatorMode>get(MODE);
/*  68 */     EnumDirection enumDirection = paramIBlockData.<EnumDirection>get(FACING);
/*     */     
/*  70 */     return Blocks.POWERED_COMPARATOR.getBlockData().<Comparable, EnumDirection>set(FACING, enumDirection).<Comparable, Boolean>set(POWERED, bool).set(MODE, enumComparatorMode);
/*     */   }
/*     */ 
/*     */   
/*     */   protected IBlockData z(IBlockData paramIBlockData) {
/*  75 */     Boolean bool = paramIBlockData.<Boolean>get(POWERED);
/*  76 */     EnumComparatorMode enumComparatorMode = paramIBlockData.<EnumComparatorMode>get(MODE);
/*  77 */     EnumDirection enumDirection = paramIBlockData.<EnumDirection>get(FACING);
/*     */     
/*  79 */     return Blocks.UNPOWERED_COMPARATOR.getBlockData().<Comparable, EnumDirection>set(FACING, enumDirection).<Comparable, Boolean>set(POWERED, bool).set(MODE, enumComparatorMode);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean A(IBlockData paramIBlockData) {
/*  84 */     return (this.d || ((Boolean)paramIBlockData.<Boolean>get(POWERED)).booleanValue());
/*     */   }
/*     */ 
/*     */   
/*     */   protected int a(IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/*  89 */     TileEntity tileEntity = paramIBlockAccess.getTileEntity(paramBlockPosition);
/*  90 */     if (tileEntity instanceof TileEntityComparator) {
/*  91 */       return ((TileEntityComparator)tileEntity).a();
/*     */     }
/*     */     
/*  94 */     return 0;
/*     */   }
/*     */   
/*     */   private int j(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/*  98 */     if (paramIBlockData.get(MODE) == EnumComparatorMode.SUBTRACT) {
/*  99 */       return Math.max(f(paramWorld, paramBlockPosition, paramIBlockData) - c(paramWorld, paramBlockPosition, paramIBlockData), 0);
/*     */     }
/*     */     
/* 102 */     return f(paramWorld, paramBlockPosition, paramIBlockData);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean e(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 107 */     int i = f(paramWorld, paramBlockPosition, paramIBlockData);
/* 108 */     if (i >= 15) {
/* 109 */       return true;
/*     */     }
/* 111 */     if (i == 0) {
/* 112 */       return false;
/*     */     }
/*     */     
/* 115 */     int j = c(paramWorld, paramBlockPosition, paramIBlockData);
/* 116 */     if (j == 0) {
/* 117 */       return true;
/*     */     }
/*     */     
/* 120 */     return (i >= j);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int f(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 125 */     int i = super.f(paramWorld, paramBlockPosition, paramIBlockData);
/*     */     
/* 127 */     EnumDirection enumDirection = paramIBlockData.<EnumDirection>get(FACING);
/* 128 */     BlockPosition blockPosition = paramBlockPosition.shift(enumDirection);
/* 129 */     IBlockData iBlockData = paramWorld.getType(blockPosition);
/*     */     
/* 131 */     if (iBlockData.n()) {
/* 132 */       i = iBlockData.a(paramWorld, blockPosition);
/* 133 */     } else if (i < 15 && iBlockData.l()) {
/* 134 */       blockPosition = blockPosition.shift(enumDirection);
/* 135 */       iBlockData = paramWorld.getType(blockPosition);
/*     */       
/* 137 */       if (iBlockData.n()) {
/* 138 */         i = iBlockData.a(paramWorld, blockPosition);
/* 139 */       } else if (iBlockData.getMaterial() == Material.AIR) {
/* 140 */         EntityItemFrame entityItemFrame = a(paramWorld, enumDirection, blockPosition);
/* 141 */         if (entityItemFrame != null) {
/* 142 */           i = entityItemFrame.t();
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 147 */     return i;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private EntityItemFrame a(World paramWorld, EnumDirection paramEnumDirection, BlockPosition paramBlockPosition) {
/* 152 */     List<Entity> list = paramWorld.a((Class)EntityItemFrame.class, new AxisAlignedBB(paramBlockPosition.getX(), paramBlockPosition.getY(), paramBlockPosition.getZ(), (paramBlockPosition.getX() + 1), (paramBlockPosition.getY() + 1), (paramBlockPosition.getZ() + 1)), new Predicate<Entity>(this, paramEnumDirection)
/*     */         {
/*     */           public boolean a(@Nullable Entity param1Entity) {
/* 155 */             return (param1Entity != null && param1Entity.getDirection() == this.a);
/*     */           }
/*     */         });
/*     */     
/* 159 */     if (list.size() == 1) {
/* 160 */       return (EntityItemFrame)list.get(0);
/*     */     }
/*     */     
/* 163 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean interact(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, EntityHuman paramEntityHuman, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/* 168 */     if (!paramEntityHuman.abilities.mayBuild) {
/* 169 */       return false;
/*     */     }
/*     */     
/* 172 */     paramIBlockData = paramIBlockData.a(MODE);
/* 173 */     float f = (paramIBlockData.get(MODE) == EnumComparatorMode.SUBTRACT) ? 0.55F : 0.5F;
/* 174 */     paramWorld.a(paramEntityHuman, paramBlockPosition, SoundEffects.aq, SoundCategory.BLOCKS, 0.3F, f);
/*     */     
/* 176 */     paramWorld.setTypeAndData(paramBlockPosition, paramIBlockData, 2);
/* 177 */     k(paramWorld, paramBlockPosition, paramIBlockData);
/* 178 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void g(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 183 */     if (paramWorld.a(paramBlockPosition, this)) {
/*     */       return;
/*     */     }
/*     */     
/* 187 */     int i = j(paramWorld, paramBlockPosition, paramIBlockData);
/* 188 */     TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/* 189 */     byte b = (tileEntity instanceof TileEntityComparator) ? ((TileEntityComparator)tileEntity).a() : 0;
/*     */     
/* 191 */     if (i != b || A(paramIBlockData) != e(paramWorld, paramBlockPosition, paramIBlockData))
/*     */     {
/* 193 */       if (i(paramWorld, paramBlockPosition, paramIBlockData)) {
/* 194 */         paramWorld.a(paramBlockPosition, this, 2, -1);
/*     */       } else {
/* 196 */         paramWorld.a(paramBlockPosition, this, 2, 0);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private void k(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 202 */     int i = j(paramWorld, paramBlockPosition, paramIBlockData);
/*     */     
/* 204 */     TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/* 205 */     int j = 0;
/* 206 */     if (tileEntity instanceof TileEntityComparator) {
/* 207 */       TileEntityComparator tileEntityComparator = (TileEntityComparator)tileEntity;
/*     */       
/* 209 */       j = tileEntityComparator.a();
/* 210 */       tileEntityComparator.a(i);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 215 */     if (j != i || paramIBlockData.get(MODE) == EnumComparatorMode.COMPARE) {
/* 216 */       boolean bool1 = e(paramWorld, paramBlockPosition, paramIBlockData);
/* 217 */       boolean bool2 = A(paramIBlockData);
/*     */       
/* 219 */       if (bool2 && !bool1) {
/* 220 */         paramWorld.setTypeAndData(paramBlockPosition, paramIBlockData.set(POWERED, Boolean.valueOf(false)), 2);
/* 221 */       } else if (!bool2 && bool1) {
/* 222 */         paramWorld.setTypeAndData(paramBlockPosition, paramIBlockData.set(POWERED, Boolean.valueOf(true)), 2);
/*     */       } 
/*     */       
/* 225 */       h(paramWorld, paramBlockPosition, paramIBlockData);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, Random paramRandom) {
/* 231 */     if (this.d)
/*     */     {
/* 233 */       paramWorld.setTypeAndData(paramBlockPosition, z(paramIBlockData).set(POWERED, Boolean.valueOf(true)), 4);
/*     */     }
/* 235 */     k(paramWorld, paramBlockPosition, paramIBlockData);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 240 */     super.onPlace(paramWorld, paramBlockPosition, paramIBlockData);
/* 241 */     paramWorld.setTileEntity(paramBlockPosition, a(paramWorld, 0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 246 */     super.remove(paramWorld, paramBlockPosition, paramIBlockData);
/* 247 */     paramWorld.s(paramBlockPosition);
/*     */     
/* 249 */     h(paramWorld, paramBlockPosition, paramIBlockData);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition, int paramInt1, int paramInt2) {
/* 254 */     super.a(paramIBlockData, paramWorld, paramBlockPosition, paramInt1, paramInt2);
/*     */     
/* 256 */     TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/* 257 */     if (tileEntity == null) {
/* 258 */       return false;
/*     */     }
/*     */     
/* 261 */     return tileEntity.c(paramInt1, paramInt2);
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity a(World paramWorld, int paramInt) {
/* 266 */     return new TileEntityComparator();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/* 271 */     return getBlockData()
/* 272 */       .<Comparable, EnumDirection>set(FACING, EnumDirection.fromType2(paramInt))
/* 273 */       .<Comparable, Boolean>set(POWERED, Boolean.valueOf(((paramInt & 0x8) > 0)))
/* 274 */       .set(MODE, ((paramInt & 0x4) > 0) ? EnumComparatorMode.SUBTRACT : EnumComparatorMode.COMPARE);
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/* 279 */     int i = 0;
/*     */     
/* 281 */     i |= ((EnumDirection)paramIBlockData.<EnumDirection>get(FACING)).get2DRotationValue();
/*     */     
/* 283 */     if (((Boolean)paramIBlockData.<Boolean>get(POWERED)).booleanValue()) {
/* 284 */       i |= 0x8;
/*     */     }
/*     */     
/* 287 */     if (paramIBlockData.get(MODE) == EnumComparatorMode.SUBTRACT) {
/* 288 */       i |= 0x4;
/*     */     }
/*     */     
/* 291 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockRotation paramEnumBlockRotation) {
/* 296 */     return paramIBlockData.set(FACING, paramEnumBlockRotation.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockMirror paramEnumBlockMirror) {
/* 301 */     return paramIBlockData.a(paramEnumBlockMirror.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 306 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { FACING, MODE, POWERED });
/*     */   }
/*     */   
/*     */   public enum EnumComparatorMode implements INamable {
/* 310 */     COMPARE("compare"),
/* 311 */     SUBTRACT("subtract");
/*     */     
/*     */     private final String c;
/*     */ 
/*     */     
/*     */     EnumComparatorMode(String param1String1) {
/* 317 */       this.c = param1String1;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 322 */       return this.c;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 327 */       return this.c;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(World paramWorld, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt, EntityLiving paramEntityLiving) {
/* 333 */     return getBlockData().<Comparable, EnumDirection>set(FACING, paramEntityLiving.getDirection().opposite()).<Comparable, Boolean>set(POWERED, Boolean.valueOf(false)).set(MODE, EnumComparatorMode.COMPARE);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockRedstoneComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */