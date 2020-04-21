/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ 
/*     */ public class BlockPoweredRail
/*     */   extends BlockMinecartTrackAbstract
/*     */ {
/*  10 */   public static final BlockStateEnum<BlockMinecartTrackAbstract.EnumTrackPosition> SHAPE = BlockStateEnum.a("shape", BlockMinecartTrackAbstract.EnumTrackPosition.class, new Predicate() {
/*     */         public boolean a(@Nullable BlockMinecartTrackAbstract.EnumTrackPosition blockminecarttrackabstract_enumtrackposition) {
/*  12 */           return (blockminecarttrackabstract_enumtrackposition != BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_EAST && blockminecarttrackabstract_enumtrackposition != BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_WEST && blockminecarttrackabstract_enumtrackposition != BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_EAST && blockminecarttrackabstract_enumtrackposition != BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_WEST);
/*     */         }
/*     */         
/*     */         public boolean apply(@Nullable Object object) {
/*  16 */           return a((BlockMinecartTrackAbstract.EnumTrackPosition)object);
/*     */         }
/*     */       });
/*  19 */   public static final BlockStateBoolean POWERED = BlockStateBoolean.of("powered");
/*     */   
/*     */   protected BlockPoweredRail() {
/*  22 */     super(true);
/*  23 */     w(this.blockStateList.getBlockData().<BlockMinecartTrackAbstract.EnumTrackPosition, BlockMinecartTrackAbstract.EnumTrackPosition>set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_SOUTH).set(POWERED, Boolean.valueOf(false)));
/*     */   }
/*     */   
/*     */   protected boolean a(World world, BlockPosition blockposition, IBlockData iblockdata, boolean flag, int i) {
/*  27 */     if (i >= 8) {
/*  28 */       return false;
/*     */     }
/*  30 */     int j = blockposition.getX();
/*  31 */     int k = blockposition.getY();
/*  32 */     int l = blockposition.getZ();
/*  33 */     boolean flag1 = true;
/*  34 */     BlockMinecartTrackAbstract.EnumTrackPosition blockminecarttrackabstract_enumtrackposition = iblockdata.<BlockMinecartTrackAbstract.EnumTrackPosition>get(SHAPE);
/*     */     
/*  36 */     switch (blockminecarttrackabstract_enumtrackposition) {
/*     */       case NORTH_SOUTH:
/*  38 */         if (flag) {
/*  39 */           l++; break;
/*     */         } 
/*  41 */         l--;
/*     */         break;
/*     */ 
/*     */       
/*     */       case EAST_WEST:
/*  46 */         if (flag) {
/*  47 */           j--; break;
/*     */         } 
/*  49 */         j++;
/*     */         break;
/*     */ 
/*     */       
/*     */       case null:
/*  54 */         if (flag) {
/*  55 */           j--;
/*     */         } else {
/*  57 */           j++;
/*  58 */           k++;
/*  59 */           flag1 = false;
/*     */         } 
/*     */         
/*  62 */         blockminecarttrackabstract_enumtrackposition = BlockMinecartTrackAbstract.EnumTrackPosition.EAST_WEST;
/*     */         break;
/*     */       
/*     */       case ASCENDING_WEST:
/*  66 */         if (flag) {
/*  67 */           j--;
/*  68 */           k++;
/*  69 */           flag1 = false;
/*     */         } else {
/*  71 */           j++;
/*     */         } 
/*     */         
/*  74 */         blockminecarttrackabstract_enumtrackposition = BlockMinecartTrackAbstract.EnumTrackPosition.EAST_WEST;
/*     */         break;
/*     */       
/*     */       case ASCENDING_NORTH:
/*  78 */         if (flag) {
/*  79 */           l++;
/*     */         } else {
/*  81 */           l--;
/*  82 */           k++;
/*  83 */           flag1 = false;
/*     */         } 
/*     */         
/*  86 */         blockminecarttrackabstract_enumtrackposition = BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_SOUTH;
/*     */         break;
/*     */       
/*     */       case ASCENDING_SOUTH:
/*  90 */         if (flag) {
/*  91 */           l++;
/*  92 */           k++;
/*  93 */           flag1 = false;
/*     */         } else {
/*  95 */           l--;
/*     */         } 
/*     */         
/*  98 */         blockminecarttrackabstract_enumtrackposition = BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_SOUTH;
/*     */         break;
/*     */     } 
/* 101 */     return a(world, new BlockPosition(j, k, l), flag, i, blockminecarttrackabstract_enumtrackposition) ? true : ((flag1 && a(world, new BlockPosition(j, k - 1, l), flag, i, blockminecarttrackabstract_enumtrackposition)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean a(World world, BlockPosition blockposition, boolean flag, int i, BlockMinecartTrackAbstract.EnumTrackPosition blockminecarttrackabstract_enumtrackposition) {
/* 106 */     IBlockData iblockdata = world.getType(blockposition);
/*     */     
/* 108 */     if (iblockdata.getBlock() != this) {
/* 109 */       return false;
/*     */     }
/* 111 */     BlockMinecartTrackAbstract.EnumTrackPosition blockminecarttrackabstract_enumtrackposition1 = iblockdata.<BlockMinecartTrackAbstract.EnumTrackPosition>get(SHAPE);
/*     */     
/* 113 */     return (blockminecarttrackabstract_enumtrackposition == BlockMinecartTrackAbstract.EnumTrackPosition.EAST_WEST && (blockminecarttrackabstract_enumtrackposition1 == BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_SOUTH || blockminecarttrackabstract_enumtrackposition1 == BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_NORTH || blockminecarttrackabstract_enumtrackposition1 == BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_SOUTH)) ? false : ((blockminecarttrackabstract_enumtrackposition == BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_SOUTH && (blockminecarttrackabstract_enumtrackposition1 == BlockMinecartTrackAbstract.EnumTrackPosition.EAST_WEST || blockminecarttrackabstract_enumtrackposition1 == BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_EAST || blockminecarttrackabstract_enumtrackposition1 == BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_WEST)) ? false : (((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue() ? (world.isBlockIndirectlyPowered(blockposition) ? true : a(world, blockposition, iblockdata, flag, i + 1)) : false));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block) {
/* 118 */     boolean flag = ((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue();
/* 119 */     boolean flag1 = !(!world.isBlockIndirectlyPowered(blockposition) && !a(world, blockposition, iblockdata, true, 0) && !a(world, blockposition, iblockdata, false, 0));
/*     */     
/* 121 */     if (flag1 != flag) {
/*     */       
/* 123 */       int power = ((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue() ? 15 : 0;
/* 124 */       int newPower = CraftEventFactory.callRedstoneChange(world, blockposition.getX(), blockposition.getY(), blockposition.getZ(), power, 15 - power).getNewCurrent();
/* 125 */       if (newPower == power) {
/*     */         return;
/*     */       }
/*     */       
/* 129 */       world.setTypeAndData(blockposition, iblockdata.set(POWERED, Boolean.valueOf(flag1)), 3);
/* 130 */       world.applyPhysics(blockposition.down(), this, false);
/* 131 */       if (((BlockMinecartTrackAbstract.EnumTrackPosition)iblockdata.<BlockMinecartTrackAbstract.EnumTrackPosition>get(SHAPE)).c()) {
/* 132 */         world.applyPhysics(blockposition.up(), this, false);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState<BlockMinecartTrackAbstract.EnumTrackPosition> g() {
/* 139 */     return SHAPE;
/*     */   }
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/* 143 */     return getBlockData().<BlockMinecartTrackAbstract.EnumTrackPosition, BlockMinecartTrackAbstract.EnumTrackPosition>set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.a(i & 0x7)).set(POWERED, Boolean.valueOf(((i & 0x8) > 0)));
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 147 */     byte b0 = 0;
/* 148 */     int i = b0 | ((BlockMinecartTrackAbstract.EnumTrackPosition)iblockdata.<BlockMinecartTrackAbstract.EnumTrackPosition>get(SHAPE)).a();
/*     */     
/* 150 */     if (((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue()) {
/* 151 */       i |= 0x8;
/*     */     }
/*     */     
/* 154 */     return i;
/*     */   }
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 158 */     switch (enumblockrotation) {
/*     */       case null:
/* 160 */         switch ((BlockMinecartTrackAbstract.EnumTrackPosition)iblockdata.get((IBlockState)SHAPE)) {
/*     */           case null:
/* 162 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_WEST);
/*     */           
/*     */           case ASCENDING_WEST:
/* 165 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_EAST);
/*     */           
/*     */           case ASCENDING_NORTH:
/* 168 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_SOUTH);
/*     */           
/*     */           case ASCENDING_SOUTH:
/* 171 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_NORTH);
/*     */           
/*     */           case SOUTH_EAST:
/* 174 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_WEST);
/*     */           
/*     */           case SOUTH_WEST:
/* 177 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_EAST);
/*     */           
/*     */           case NORTH_WEST:
/* 180 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_EAST);
/*     */           
/*     */           case NORTH_EAST:
/* 183 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_WEST);
/*     */         } 
/*     */       
/*     */       case COUNTERCLOCKWISE_90:
/* 187 */         switch ((BlockMinecartTrackAbstract.EnumTrackPosition)iblockdata.get((IBlockState)SHAPE)) {
/*     */           case NORTH_SOUTH:
/* 189 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.EAST_WEST);
/*     */           
/*     */           case EAST_WEST:
/* 192 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_SOUTH);
/*     */           
/*     */           case null:
/* 195 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_NORTH);
/*     */           
/*     */           case ASCENDING_WEST:
/* 198 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_SOUTH);
/*     */           
/*     */           case ASCENDING_NORTH:
/* 201 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_WEST);
/*     */           
/*     */           case ASCENDING_SOUTH:
/* 204 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_EAST);
/*     */           
/*     */           case SOUTH_EAST:
/* 207 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_EAST);
/*     */           
/*     */           case SOUTH_WEST:
/* 210 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_EAST);
/*     */           
/*     */           case NORTH_WEST:
/* 213 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_WEST);
/*     */           
/*     */           case NORTH_EAST:
/* 216 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_WEST);
/*     */         } 
/*     */       
/*     */       case CLOCKWISE_90:
/* 220 */         switch ((BlockMinecartTrackAbstract.EnumTrackPosition)iblockdata.get((IBlockState)SHAPE)) {
/*     */           case NORTH_SOUTH:
/* 222 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.EAST_WEST);
/*     */           
/*     */           case EAST_WEST:
/* 225 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_SOUTH);
/*     */           
/*     */           case null:
/* 228 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_SOUTH);
/*     */           
/*     */           case ASCENDING_WEST:
/* 231 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_NORTH);
/*     */           
/*     */           case ASCENDING_NORTH:
/* 234 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_EAST);
/*     */           
/*     */           case ASCENDING_SOUTH:
/* 237 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_WEST);
/*     */           
/*     */           case SOUTH_EAST:
/* 240 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_WEST);
/*     */           
/*     */           case SOUTH_WEST:
/* 243 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_WEST);
/*     */           
/*     */           case NORTH_WEST:
/* 246 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_EAST);
/*     */           
/*     */           case NORTH_EAST:
/* 249 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_EAST);
/*     */         } 
/*     */         break;
/*     */     } 
/* 253 */     return iblockdata;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/* 258 */     BlockMinecartTrackAbstract.EnumTrackPosition blockminecarttrackabstract_enumtrackposition = iblockdata.<BlockMinecartTrackAbstract.EnumTrackPosition>get(SHAPE);
/*     */     
/* 260 */     switch (enumblockmirror) {
/*     */       case LEFT_RIGHT:
/* 262 */         switch (blockminecarttrackabstract_enumtrackposition) {
/*     */           case ASCENDING_NORTH:
/* 264 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_SOUTH);
/*     */           
/*     */           case ASCENDING_SOUTH:
/* 267 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_NORTH);
/*     */           
/*     */           case SOUTH_EAST:
/* 270 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_EAST);
/*     */           
/*     */           case SOUTH_WEST:
/* 273 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_WEST);
/*     */           
/*     */           case NORTH_WEST:
/* 276 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_WEST);
/*     */           
/*     */           case NORTH_EAST:
/* 279 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_EAST);
/*     */         } 
/*     */         
/* 282 */         return super.a(iblockdata, enumblockmirror);
/*     */ 
/*     */       
/*     */       case null:
/* 286 */         switch (blockminecarttrackabstract_enumtrackposition) {
/*     */           case null:
/* 288 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_WEST);
/*     */           
/*     */           case ASCENDING_WEST:
/* 291 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_EAST);
/*     */ 
/*     */           
/*     */           default:
/*     */             break;
/*     */ 
/*     */           
/*     */           case SOUTH_EAST:
/* 299 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_WEST);
/*     */           
/*     */           case SOUTH_WEST:
/* 302 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_EAST);
/*     */           
/*     */           case NORTH_WEST:
/* 305 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_EAST);
/*     */           case NORTH_EAST:
/*     */             break;
/* 308 */         }  return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_WEST);
/*     */     } 
/*     */ 
/*     */     
/* 312 */     return super.a(iblockdata, enumblockmirror);
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 316 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { SHAPE, POWERED });
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockPoweredRail.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */