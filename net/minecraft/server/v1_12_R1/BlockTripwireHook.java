/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.MoreObjects;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockRedstoneEvent;
/*     */ 
/*     */ public class BlockTripwireHook extends Block {
/*  12 */   public static final BlockStateDirection FACING = BlockFacingHorizontal.FACING;
/*  13 */   public static final BlockStateBoolean POWERED = BlockStateBoolean.of("powered");
/*  14 */   public static final BlockStateBoolean ATTACHED = BlockStateBoolean.of("attached");
/*  15 */   protected static final AxisAlignedBB d = new AxisAlignedBB(0.3125D, 0.0D, 0.625D, 0.6875D, 0.625D, 1.0D);
/*  16 */   protected static final AxisAlignedBB e = new AxisAlignedBB(0.3125D, 0.0D, 0.0D, 0.6875D, 0.625D, 0.375D);
/*  17 */   protected static final AxisAlignedBB f = new AxisAlignedBB(0.625D, 0.0D, 0.3125D, 1.0D, 0.625D, 0.6875D);
/*  18 */   protected static final AxisAlignedBB g = new AxisAlignedBB(0.0D, 0.0D, 0.3125D, 0.375D, 0.625D, 0.6875D);
/*     */   
/*     */   public BlockTripwireHook() {
/*  21 */     super(Material.ORIENTABLE);
/*  22 */     w(this.blockStateList.getBlockData().<Comparable, EnumDirection>set(FACING, EnumDirection.NORTH).<Comparable, Boolean>set(POWERED, Boolean.valueOf(false)).set(ATTACHED, Boolean.valueOf(false)));
/*  23 */     a(CreativeModeTab.d);
/*  24 */     a(true);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  28 */     switch ((EnumDirection)iblockdata.get(FACING)) {
/*     */       
/*     */       default:
/*  31 */         return g;
/*     */       
/*     */       case WEST:
/*  34 */         return f;
/*     */       
/*     */       case SOUTH:
/*  37 */         return e;
/*     */       case NORTH:
/*     */         break;
/*  40 */     }  return d;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public AxisAlignedBB a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  46 */     return k;
/*     */   }
/*     */   
/*     */   public boolean b(IBlockData iblockdata) {
/*  50 */     return false;
/*     */   }
/*     */   
/*     */   public boolean c(IBlockData iblockdata) {
/*  54 */     return false;
/*     */   }
/*     */   
/*     */   public boolean canPlace(World world, BlockPosition blockposition, EnumDirection enumdirection) {
/*  58 */     EnumDirection enumdirection1 = enumdirection.opposite();
/*  59 */     BlockPosition blockposition1 = blockposition.shift(enumdirection1);
/*  60 */     IBlockData iblockdata = world.getType(blockposition1);
/*  61 */     boolean flag = c(iblockdata.getBlock());
/*     */     
/*  63 */     return (!flag && enumdirection.k().c() && iblockdata.d(world, blockposition1, enumdirection) == EnumBlockFaceShape.SOLID && !iblockdata.m());
/*     */   }
/*     */   public boolean canPlace(World world, BlockPosition blockposition) {
/*     */     EnumDirection enumdirection;
/*  67 */     Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/*  72 */       if (!iterator.hasNext()) {
/*  73 */         return false;
/*     */       }
/*     */       
/*  76 */       enumdirection = iterator.next();
/*  77 */     } while (!canPlace(world, blockposition, enumdirection));
/*     */     
/*  79 */     return true;
/*     */   }
/*     */   
/*     */   public IBlockData getPlacedState(World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2, int i, EntityLiving entityliving) {
/*  83 */     IBlockData iblockdata = getBlockData().<Comparable, Boolean>set(POWERED, Boolean.valueOf(false)).set(ATTACHED, Boolean.valueOf(false));
/*     */     
/*  85 */     if (enumdirection.k().c()) {
/*  86 */       iblockdata = iblockdata.set(FACING, enumdirection);
/*     */     }
/*     */     
/*  89 */     return iblockdata;
/*     */   }
/*     */   
/*     */   public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, EntityLiving entityliving, ItemStack itemstack) {
/*  93 */     a(world, blockposition, iblockdata, false, false, -1, (IBlockData)null);
/*     */   }
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
/*  97 */     if (block != this && 
/*  98 */       e(world, blockposition, iblockdata)) {
/*  99 */       EnumDirection enumdirection = iblockdata.<EnumDirection>get(FACING);
/*     */       
/* 101 */       if (!canPlace(world, blockposition, enumdirection)) {
/* 102 */         b(world, blockposition, iblockdata, 0);
/* 103 */         world.setAir(blockposition);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, boolean flag, boolean flag1, int i, @Nullable IBlockData iblockdata1) {
/*     */     int n;
/* 111 */     EnumDirection enumdirection = iblockdata.<EnumDirection>get(FACING);
/* 112 */     int flag2 = ((Boolean)iblockdata.<Boolean>get(ATTACHED)).booleanValue();
/* 113 */     boolean flag3 = ((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue();
/* 114 */     boolean flag4 = !flag;
/* 115 */     boolean flag5 = false;
/* 116 */     int j = 0;
/* 117 */     IBlockData[] aiblockdata = new IBlockData[42];
/*     */ 
/*     */ 
/*     */     
/* 121 */     for (int k = 1; k < 42; k++) {
/* 122 */       BlockPosition blockposition1 = blockposition.shift(enumdirection, k);
/* 123 */       IBlockData iblockdata2 = world.getType(blockposition1);
/*     */       
/* 125 */       if (iblockdata2.getBlock() == Blocks.TRIPWIRE_HOOK) {
/* 126 */         if (iblockdata2.get(FACING) == enumdirection.opposite()) {
/* 127 */           j = k;
/*     */         }
/*     */         
/*     */         break;
/*     */       } 
/* 132 */       if (iblockdata2.getBlock() != Blocks.TRIPWIRE && k != i) {
/* 133 */         aiblockdata[k] = null;
/* 134 */         flag4 = false;
/*     */       } else {
/* 136 */         if (k == i) {
/* 137 */           iblockdata2 = (IBlockData)MoreObjects.firstNonNull(iblockdata1, iblockdata2);
/*     */         }
/*     */         
/* 140 */         boolean flag6 = !((Boolean)iblockdata2.<Boolean>get(BlockTripwire.DISARMED)).booleanValue();
/* 141 */         boolean flag7 = ((Boolean)iblockdata2.<Boolean>get(BlockTripwire.POWERED)).booleanValue();
/*     */         
/* 143 */         n = flag5 | ((flag6 && flag7) ? 1 : 0);
/* 144 */         aiblockdata[k] = iblockdata2;
/* 145 */         if (k == i) {
/* 146 */           world.a(blockposition, this, a(world));
/* 147 */           flag4 &= flag6;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 152 */     int m = flag4 & ((j > 1) ? 1 : 0);
/* 153 */     n &= m;
/* 154 */     IBlockData iblockdata3 = getBlockData().<Comparable, Boolean>set(ATTACHED, Boolean.valueOf(m)).set(POWERED, Boolean.valueOf(n));
/*     */     
/* 156 */     if (j > 0) {
/* 157 */       BlockPosition blockposition1 = blockposition.shift(enumdirection, j);
/* 158 */       EnumDirection enumdirection1 = enumdirection.opposite();
/*     */       
/* 160 */       world.setTypeAndData(blockposition1, iblockdata3.set(FACING, enumdirection1), 3);
/* 161 */       a(world, blockposition1, enumdirection1);
/* 162 */       a(world, blockposition1, m, n, flag2, flag3);
/*     */     } 
/*     */ 
/*     */     
/* 166 */     Block block = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*     */     
/* 168 */     BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, 15, 0);
/* 169 */     world.getServer().getPluginManager().callEvent((Event)eventRedstone);
/*     */     
/* 171 */     if (eventRedstone.getNewCurrent() > 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 176 */     a(world, blockposition, m, n, flag2, flag3);
/* 177 */     if (!flag) {
/* 178 */       world.setTypeAndData(blockposition, iblockdata3.set(FACING, enumdirection), 3);
/* 179 */       if (flag1) {
/* 180 */         a(world, blockposition, enumdirection);
/*     */       }
/*     */     } 
/*     */     
/* 184 */     if (flag2 != m) {
/* 185 */       for (int l = 1; l < j; l++) {
/* 186 */         BlockPosition blockposition2 = blockposition.shift(enumdirection, l);
/* 187 */         IBlockData iblockdata4 = aiblockdata[l];
/*     */         
/* 189 */         if (iblockdata4 != null && world.getType(blockposition2).getMaterial() != Material.AIR) {
/* 190 */           world.setTypeAndData(blockposition2, iblockdata4.set(ATTACHED, Boolean.valueOf(m)), 3);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {}
/*     */   
/*     */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/* 200 */     a(world, blockposition, iblockdata, false, true, -1, (IBlockData)null);
/*     */   }
/*     */   
/*     */   private void a(World world, BlockPosition blockposition, boolean flag, boolean flag1, boolean flag2, boolean flag3) {
/* 204 */     if (flag1 && !flag3) {
/* 205 */       world.a(null, blockposition, SoundEffects.ia, SoundCategory.BLOCKS, 0.4F, 0.6F);
/* 206 */     } else if (!flag1 && flag3) {
/* 207 */       world.a(null, blockposition, SoundEffects.hZ, SoundCategory.BLOCKS, 0.4F, 0.5F);
/* 208 */     } else if (flag && !flag2) {
/* 209 */       world.a(null, blockposition, SoundEffects.hY, SoundCategory.BLOCKS, 0.4F, 0.7F);
/* 210 */     } else if (!flag && flag2) {
/* 211 */       world.a(null, blockposition, SoundEffects.ib, SoundCategory.BLOCKS, 0.4F, 1.2F / (world.random.nextFloat() * 0.2F + 0.9F));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(World world, BlockPosition blockposition, EnumDirection enumdirection) {
/* 217 */     world.applyPhysics(blockposition, this, false);
/* 218 */     world.applyPhysics(blockposition.shift(enumdirection.opposite()), this, false);
/*     */   }
/*     */   
/*     */   private boolean e(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 222 */     if (!canPlace(world, blockposition)) {
/* 223 */       b(world, blockposition, iblockdata, 0);
/* 224 */       world.setAir(blockposition);
/* 225 */       return false;
/*     */     } 
/* 227 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 232 */     boolean flag = ((Boolean)iblockdata.<Boolean>get(ATTACHED)).booleanValue();
/* 233 */     boolean flag1 = ((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue();
/*     */     
/* 235 */     if (flag || flag1) {
/* 236 */       a(world, blockposition, iblockdata, true, false, -1, (IBlockData)null);
/*     */     }
/*     */     
/* 239 */     if (flag1) {
/* 240 */       world.applyPhysics(blockposition, this, false);
/* 241 */       world.applyPhysics(blockposition.shift(((EnumDirection)iblockdata.<EnumDirection>get(FACING)).opposite()), this, false);
/*     */     } 
/*     */     
/* 244 */     super.remove(world, blockposition, iblockdata);
/*     */   }
/*     */   
/*     */   public int b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 248 */     return ((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue() ? 15 : 0;
/*     */   }
/*     */   
/*     */   public int c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 252 */     return !((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue() ? 0 : ((iblockdata.get(FACING) == enumdirection) ? 15 : 0);
/*     */   }
/*     */   
/*     */   public boolean isPowerSource(IBlockData iblockdata) {
/* 256 */     return true;
/*     */   }
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/* 260 */     return getBlockData().<Comparable, EnumDirection>set(FACING, EnumDirection.fromType2(i & 0x3)).<Comparable, Boolean>set(POWERED, Boolean.valueOf(((i & 0x8) > 0))).set(ATTACHED, Boolean.valueOf(((i & 0x4) > 0)));
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 264 */     byte b0 = 0;
/* 265 */     int i = b0 | ((EnumDirection)iblockdata.<EnumDirection>get(FACING)).get2DRotationValue();
/*     */     
/* 267 */     if (((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue()) {
/* 268 */       i |= 0x8;
/*     */     }
/*     */     
/* 271 */     if (((Boolean)iblockdata.<Boolean>get(ATTACHED)).booleanValue()) {
/* 272 */       i |= 0x4;
/*     */     }
/*     */     
/* 275 */     return i;
/*     */   }
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 279 */     return iblockdata.set(FACING, enumblockrotation.a(iblockdata.<EnumDirection>get(FACING)));
/*     */   }
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/* 283 */     return iblockdata.a(enumblockmirror.a(iblockdata.<EnumDirection>get(FACING)));
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 287 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { FACING, POWERED, ATTACHED });
/*     */   }
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
/* 291 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockTripwireHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */