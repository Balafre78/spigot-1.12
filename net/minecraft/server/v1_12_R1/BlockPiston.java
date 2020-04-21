/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.AbstractList;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.block.CraftBlock;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockPistonExtendEvent;
/*     */ import org.bukkit.event.block.BlockPistonRetractEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockPiston
/*     */   extends BlockDirectional
/*     */ {
/*  22 */   public static final BlockStateBoolean EXTENDED = BlockStateBoolean.of("extended");
/*  23 */   protected static final AxisAlignedBB b = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.75D, 1.0D, 1.0D);
/*  24 */   protected static final AxisAlignedBB c = new AxisAlignedBB(0.25D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*  25 */   protected static final AxisAlignedBB d = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.75D);
/*  26 */   protected static final AxisAlignedBB e = new AxisAlignedBB(0.0D, 0.0D, 0.25D, 1.0D, 1.0D, 1.0D);
/*  27 */   protected static final AxisAlignedBB f = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);
/*  28 */   protected static final AxisAlignedBB g = new AxisAlignedBB(0.0D, 0.25D, 0.0D, 1.0D, 1.0D, 1.0D);
/*     */   private final boolean sticky;
/*     */   
/*     */   public BlockPiston(boolean flag) {
/*  32 */     super(Material.PISTON);
/*  33 */     w(this.blockStateList.getBlockData().<Comparable, EnumDirection>set(FACING, EnumDirection.NORTH).set(EXTENDED, Boolean.valueOf(false)));
/*  34 */     this.sticky = flag;
/*  35 */     a(SoundEffectType.d);
/*  36 */     c(0.5F);
/*  37 */     a(CreativeModeTab.d);
/*     */   }
/*     */   
/*     */   public boolean t(IBlockData iblockdata) {
/*  41 */     return !((Boolean)iblockdata.<Boolean>get(EXTENDED)).booleanValue();
/*     */   }
/*     */   
/*     */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  45 */     if (((Boolean)iblockdata.<Boolean>get(EXTENDED)).booleanValue()) {
/*  46 */       switch ((EnumDirection)iblockdata.get(FACING)) {
/*     */         case null:
/*  48 */           return g;
/*     */ 
/*     */         
/*     */         default:
/*  52 */           return f;
/*     */         
/*     */         case NORTH:
/*  55 */           return e;
/*     */         
/*     */         case SOUTH:
/*  58 */           return d;
/*     */         
/*     */         case WEST:
/*  61 */           return c;
/*     */         case EAST:
/*     */           break;
/*  64 */       }  return b;
/*     */     } 
/*     */     
/*  67 */     return j;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean k(IBlockData iblockdata) {
/*  72 */     return !(((Boolean)iblockdata.<Boolean>get(EXTENDED)).booleanValue() && iblockdata.get(FACING) != EnumDirection.DOWN);
/*     */   }
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, AxisAlignedBB axisalignedbb, List<AxisAlignedBB> list, @Nullable Entity entity, boolean flag) {
/*  76 */     a(blockposition, axisalignedbb, list, iblockdata.e(world, blockposition));
/*     */   }
/*     */   
/*     */   public boolean b(IBlockData iblockdata) {
/*  80 */     return false;
/*     */   }
/*     */   
/*     */   public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, EntityLiving entityliving, ItemStack itemstack) {
/*  84 */     world.setTypeAndData(blockposition, iblockdata.set(FACING, EnumDirection.a(blockposition, entityliving)), 2);
/*  85 */     if (!world.isClientSide) {
/*  86 */       e(world, blockposition, iblockdata);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
/*  92 */     if (!world.isClientSide) {
/*  93 */       e(world, blockposition, iblockdata);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  99 */     if (!world.isClientSide && world.getTileEntity(blockposition) == null) {
/* 100 */       e(world, blockposition, iblockdata);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2, int i, EntityLiving entityliving) {
/* 106 */     return getBlockData().<Comparable, EnumDirection>set(FACING, EnumDirection.a(blockposition, entityliving)).set(EXTENDED, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   private void e(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 110 */     EnumDirection enumdirection = iblockdata.<EnumDirection>get(FACING);
/* 111 */     boolean flag = a(world, blockposition, enumdirection);
/*     */     
/* 113 */     if (flag && !((Boolean)iblockdata.<Boolean>get(EXTENDED)).booleanValue()) {
/* 114 */       if ((new PistonExtendsChecker(world, blockposition, enumdirection, true)).a()) {
/* 115 */         world.playBlockAction(blockposition, this, 0, enumdirection.a());
/*     */       }
/* 117 */     } else if (!flag && ((Boolean)iblockdata.<Boolean>get(EXTENDED)).booleanValue()) {
/*     */       
/* 119 */       if (!this.sticky) {
/* 120 */         Block block = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 121 */         BlockPistonRetractEvent event = new BlockPistonRetractEvent(block, (List)ImmutableList.of(), CraftBlock.notchToBlockFace(enumdirection));
/* 122 */         world.getServer().getPluginManager().callEvent((Event)event);
/*     */         
/* 124 */         if (event.isCancelled()) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 130 */       world.playBlockAction(blockposition, this, 1, enumdirection.a());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean a(World world, BlockPosition blockposition, EnumDirection enumdirection) {
/* 136 */     EnumDirection[] aenumdirection = EnumDirection.values();
/* 137 */     int i = aenumdirection.length;
/*     */     
/*     */     int j;
/*     */     
/* 141 */     for (j = 0; j < i; j++) {
/* 142 */       EnumDirection enumdirection1 = aenumdirection[j];
/*     */       
/* 144 */       if (enumdirection1 != enumdirection && world.isBlockFacePowered(blockposition.shift(enumdirection1), enumdirection1)) {
/* 145 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 149 */     if (world.isBlockFacePowered(blockposition, EnumDirection.DOWN)) {
/* 150 */       return true;
/*     */     }
/* 152 */     BlockPosition blockposition1 = blockposition.up();
/* 153 */     EnumDirection[] aenumdirection1 = EnumDirection.values();
/*     */     
/* 155 */     j = aenumdirection1.length;
/*     */     
/* 157 */     for (int k = 0; k < j; k++) {
/* 158 */       EnumDirection enumdirection2 = aenumdirection1[k];
/*     */       
/* 160 */       if (enumdirection2 != EnumDirection.DOWN && world.isBlockFacePowered(blockposition1.shift(enumdirection2), enumdirection2)) {
/* 161 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 165 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData iblockdata, World world, BlockPosition blockposition, int i, int j) {
/* 170 */     EnumDirection enumdirection = iblockdata.<EnumDirection>get(FACING);
/*     */     
/* 172 */     if (!world.isClientSide) {
/* 173 */       boolean flag = a(world, blockposition, enumdirection);
/*     */       
/* 175 */       if (flag && i == 1) {
/* 176 */         world.setTypeAndData(blockposition, iblockdata.set(EXTENDED, Boolean.valueOf(true)), 2);
/* 177 */         return false;
/*     */       } 
/*     */       
/* 180 */       if (!flag && i == 0) {
/* 181 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 185 */     if (i == 0) {
/* 186 */       if (!a(world, blockposition, enumdirection, true)) {
/* 187 */         return false;
/*     */       }
/*     */       
/* 190 */       world.setTypeAndData(blockposition, iblockdata.set(EXTENDED, Boolean.valueOf(true)), 3);
/* 191 */       world.a(null, blockposition, SoundEffects.fu, SoundCategory.BLOCKS, 0.5F, world.random.nextFloat() * 0.25F + 0.6F);
/* 192 */     } else if (i == 1) {
/* 193 */       TileEntity tileentity = world.getTileEntity(blockposition.shift(enumdirection));
/*     */       
/* 195 */       if (tileentity instanceof TileEntityPiston) {
/* 196 */         ((TileEntityPiston)tileentity).j();
/*     */       }
/*     */       
/* 199 */       world.setTypeAndData(blockposition, Blocks.PISTON_EXTENSION.getBlockData().<Comparable, EnumDirection>set(BlockPistonMoving.FACING, enumdirection).set(BlockPistonMoving.TYPE, this.sticky ? BlockPistonExtension.EnumPistonType.STICKY : BlockPistonExtension.EnumPistonType.DEFAULT), 3);
/* 200 */       world.setTileEntity(blockposition, BlockPistonMoving.a(fromLegacyData(j), enumdirection, false, true));
/* 201 */       if (this.sticky) {
/* 202 */         BlockPosition blockposition1 = blockposition.a(enumdirection.getAdjacentX() * 2, enumdirection.getAdjacentY() * 2, enumdirection.getAdjacentZ() * 2);
/* 203 */         IBlockData iblockdata1 = world.getType(blockposition1);
/* 204 */         Block block = iblockdata1.getBlock();
/* 205 */         boolean flag1 = false;
/*     */         
/* 207 */         if (block == Blocks.PISTON_EXTENSION) {
/* 208 */           TileEntity tileentity1 = world.getTileEntity(blockposition1);
/*     */           
/* 210 */           if (tileentity1 instanceof TileEntityPiston) {
/* 211 */             TileEntityPiston tileentitypiston = (TileEntityPiston)tileentity1;
/*     */             
/* 213 */             if (tileentitypiston.h() == enumdirection && tileentitypiston.f()) {
/* 214 */               tileentitypiston.j();
/* 215 */               flag1 = true;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 220 */         if (!flag1 && a(iblockdata1, world, blockposition1, enumdirection.opposite(), false, enumdirection) && (iblockdata1.o() == EnumPistonReaction.NORMAL || block == Blocks.PISTON || block == Blocks.STICKY_PISTON)) {
/* 221 */           a(world, blockposition, enumdirection, false);
/*     */         }
/*     */       } else {
/* 224 */         world.setAir(blockposition.shift(enumdirection));
/*     */       } 
/*     */       
/* 227 */       world.a(null, blockposition, SoundEffects.ft, SoundCategory.BLOCKS, 0.5F, world.random.nextFloat() * 0.15F + 0.6F);
/*     */     } 
/*     */     
/* 230 */     return true;
/*     */   }
/*     */   
/*     */   public boolean c(IBlockData iblockdata) {
/* 234 */     return false;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static EnumDirection b(int i) {
/* 239 */     int j = i & 0x7;
/*     */     
/* 241 */     return (j > 5) ? null : EnumDirection.fromType1(j);
/*     */   }
/*     */   
/*     */   public static boolean a(IBlockData iblockdata, World world, BlockPosition blockposition, EnumDirection enumdirection, boolean flag, EnumDirection enumdirection1) {
/* 245 */     Block block = iblockdata.getBlock();
/*     */     
/* 247 */     if (block == Blocks.OBSIDIAN)
/* 248 */       return false; 
/* 249 */     if (!world.getWorldBorder().a(blockposition))
/* 250 */       return false; 
/* 251 */     if (blockposition.getY() >= 0 && (enumdirection != EnumDirection.DOWN || blockposition.getY() != 0)) {
/* 252 */       if (blockposition.getY() <= world.getHeight() - 1 && (enumdirection != EnumDirection.UP || blockposition.getY() != world.getHeight() - 1)) {
/* 253 */         if (block != Blocks.PISTON && block != Blocks.STICKY_PISTON) {
/* 254 */           if (iblockdata.b(world, blockposition) == -1.0F) {
/* 255 */             return false;
/*     */           }
/*     */           
/* 258 */           switch (iblockdata.o()) {
/*     */             case null:
/* 260 */               return false;
/*     */             
/*     */             case DESTROY:
/* 263 */               return flag;
/*     */             
/*     */             case PUSH_ONLY:
/* 266 */               return (enumdirection == enumdirection1);
/*     */           } 
/* 268 */         } else if (((Boolean)iblockdata.<Boolean>get(EXTENDED)).booleanValue()) {
/* 269 */           return false;
/*     */         } 
/*     */         
/* 272 */         return !block.isTileEntity();
/*     */       } 
/* 274 */       return false;
/*     */     } 
/*     */     
/* 277 */     return false;
/*     */   }
/*     */   
/*     */   private boolean a(World world, BlockPosition blockposition, EnumDirection enumdirection, boolean flag) {
/*     */     BlockPistonRetractEvent blockPistonRetractEvent;
/* 282 */     if (!flag) {
/* 283 */       world.setAir(blockposition.shift(enumdirection));
/*     */     }
/*     */     
/* 286 */     PistonExtendsChecker pistonextendschecker = new PistonExtendsChecker(world, blockposition, enumdirection, flag);
/*     */     
/* 288 */     if (!pistonextendschecker.a()) {
/* 289 */       return false;
/*     */     }
/* 291 */     List<BlockPosition> list = pistonextendschecker.getMovedBlocks();
/* 292 */     ArrayList<IBlockData> arraylist = Lists.newArrayList();
/*     */     
/* 294 */     for (int i = 0; i < list.size(); i++) {
/* 295 */       BlockPosition blockposition1 = list.get(i);
/*     */       
/* 297 */       arraylist.add(world.getType(blockposition1).c(world, blockposition1));
/*     */     } 
/*     */     
/* 300 */     List<BlockPosition> list1 = pistonextendschecker.getBrokenBlocks();
/* 301 */     int j = list.size() + list1.size();
/* 302 */     IBlockData[] aiblockdata = new IBlockData[j];
/* 303 */     EnumDirection enumdirection1 = flag ? enumdirection : enumdirection.opposite();
/*     */     
/* 305 */     final Block bblock = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*     */     
/* 307 */     final List<BlockPosition> moved = pistonextendschecker.getMovedBlocks();
/* 308 */     final List<BlockPosition> broken = pistonextendschecker.getBrokenBlocks();
/*     */     
/* 310 */     List<Block> blocks = new AbstractList<Block>()
/*     */       {
/*     */         public int size()
/*     */         {
/* 314 */           return moved.size() + broken.size();
/*     */         }
/*     */ 
/*     */         
/*     */         public Block get(int index) {
/* 319 */           if (index >= size() || index < 0) {
/* 320 */             throw new ArrayIndexOutOfBoundsException(index);
/*     */           }
/* 322 */           BlockPosition pos = (index < moved.size()) ? moved.get(index) : broken.get(index - moved.size());
/* 323 */           return bblock.getWorld().getBlockAt(pos.getX(), pos.getY(), pos.getZ());
/*     */         }
/*     */       };
/*     */     
/* 327 */     if (flag) {
/* 328 */       BlockPistonExtendEvent blockPistonExtendEvent = new BlockPistonExtendEvent(bblock, blocks, CraftBlock.notchToBlockFace(enumdirection1));
/*     */     } else {
/* 330 */       blockPistonRetractEvent = new BlockPistonRetractEvent(bblock, blocks, CraftBlock.notchToBlockFace(enumdirection1));
/*     */     } 
/* 332 */     world.getServer().getPluginManager().callEvent((Event)blockPistonRetractEvent);
/*     */     
/* 334 */     if (blockPistonRetractEvent.isCancelled()) {
/* 335 */       for (BlockPosition b : broken) {
/* 336 */         world.notify(b, Blocks.AIR.getBlockData(), world.getType(b), 3);
/*     */       }
/* 338 */       for (BlockPosition b : moved) {
/* 339 */         world.notify(b, Blocks.AIR.getBlockData(), world.getType(b), 3);
/* 340 */         b = b.shift(enumdirection1);
/* 341 */         world.notify(b, Blocks.AIR.getBlockData(), world.getType(b), 3);
/*     */       } 
/* 343 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*     */     int k;
/*     */ 
/*     */     
/* 351 */     for (k = list1.size() - 1; k >= 0; k--) {
/* 352 */       BlockPosition blockposition2 = list1.get(k);
/* 353 */       IBlockData iblockdata = world.getType(blockposition2);
/* 354 */       iblockdata.getBlock().b(world, blockposition2, iblockdata, 0);
/* 355 */       world.setTypeAndData(blockposition2, Blocks.AIR.getBlockData(), 4);
/* 356 */       j--;
/* 357 */       aiblockdata[j] = iblockdata;
/*     */     } 
/*     */     
/* 360 */     for (k = list.size() - 1; k >= 0; k--) {
/* 361 */       BlockPosition blockposition2 = list.get(k);
/* 362 */       IBlockData iblockdata = world.getType(blockposition2);
/* 363 */       world.setTypeAndData(blockposition2, Blocks.AIR.getBlockData(), 2);
/* 364 */       blockposition2 = blockposition2.shift(enumdirection1);
/* 365 */       world.setTypeAndData(blockposition2, Blocks.PISTON_EXTENSION.getBlockData().set(FACING, enumdirection), 4);
/* 366 */       world.setTileEntity(blockposition2, BlockPistonMoving.a(arraylist.get(k), enumdirection, flag, false));
/* 367 */       j--;
/* 368 */       aiblockdata[j] = iblockdata;
/*     */     } 
/*     */     
/* 371 */     BlockPosition blockposition3 = blockposition.shift(enumdirection);
/*     */     
/* 373 */     if (flag) {
/* 374 */       BlockPistonExtension.EnumPistonType blockpistonextension_enumpistontype = this.sticky ? BlockPistonExtension.EnumPistonType.STICKY : BlockPistonExtension.EnumPistonType.DEFAULT;
/*     */       
/* 376 */       IBlockData iblockdata = Blocks.PISTON_HEAD.getBlockData().<Comparable, EnumDirection>set(BlockPistonExtension.FACING, enumdirection).set(BlockPistonExtension.TYPE, blockpistonextension_enumpistontype);
/* 377 */       IBlockData iblockdata1 = Blocks.PISTON_EXTENSION.getBlockData().<Comparable, EnumDirection>set(BlockPistonMoving.FACING, enumdirection).set(BlockPistonMoving.TYPE, this.sticky ? BlockPistonExtension.EnumPistonType.STICKY : BlockPistonExtension.EnumPistonType.DEFAULT);
/*     */       
/* 379 */       world.setTypeAndData(blockposition3, iblockdata1, 4);
/* 380 */       world.setTileEntity(blockposition3, BlockPistonMoving.a(iblockdata, enumdirection, true, true));
/*     */     } 
/*     */     
/*     */     int l;
/*     */     
/* 385 */     for (l = list1.size() - 1; l >= 0; l--) {
/* 386 */       world.applyPhysics(list1.get(l), aiblockdata[j++].getBlock(), false);
/*     */     }
/*     */     
/* 389 */     for (l = list.size() - 1; l >= 0; l--) {
/* 390 */       world.applyPhysics(list.get(l), aiblockdata[j++].getBlock(), false);
/*     */     }
/*     */     
/* 393 */     if (flag) {
/* 394 */       world.applyPhysics(blockposition3, Blocks.PISTON_HEAD, false);
/*     */     }
/*     */     
/* 397 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/* 402 */     return getBlockData().<Comparable, EnumDirection>set(FACING, b(i)).set(EXTENDED, Boolean.valueOf(((i & 0x8) > 0)));
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 406 */     byte b0 = 0;
/* 407 */     int i = b0 | ((EnumDirection)iblockdata.<EnumDirection>get(FACING)).a();
/*     */     
/* 409 */     if (((Boolean)iblockdata.<Boolean>get(EXTENDED)).booleanValue()) {
/* 410 */       i |= 0x8;
/*     */     }
/*     */     
/* 413 */     return i;
/*     */   }
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 417 */     return iblockdata.set(FACING, enumblockrotation.a(iblockdata.<EnumDirection>get(FACING)));
/*     */   }
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/* 421 */     return iblockdata.a(enumblockmirror.a(iblockdata.<EnumDirection>get(FACING)));
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 425 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { FACING, EXTENDED });
/*     */   }
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
/* 429 */     iblockdata = updateState(iblockdata, iblockaccess, blockposition);
/* 430 */     return (iblockdata.get(FACING) != enumdirection.opposite() && ((Boolean)iblockdata.<Boolean>get(EXTENDED)).booleanValue()) ? EnumBlockFaceShape.UNDEFINED : EnumBlockFaceShape.SOLID;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockPiston.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */