/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Random;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ 
/*     */ public abstract class BlockDiodeAbstract
/*     */   extends BlockFacingHorizontal
/*     */ {
/*   9 */   protected static final AxisAlignedBB c = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
/*     */   protected final boolean d;
/*     */   
/*     */   protected BlockDiodeAbstract(boolean flag) {
/*  13 */     super(Material.ORIENTABLE);
/*  14 */     this.d = flag;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  18 */     return c;
/*     */   }
/*     */   
/*     */   public boolean c(IBlockData iblockdata) {
/*  22 */     return false;
/*     */   }
/*     */   
/*     */   public boolean canPlace(World world, BlockPosition blockposition) {
/*  26 */     return world.getType(blockposition.down()).q() ? super.canPlace(world, blockposition) : false;
/*     */   }
/*     */   
/*     */   public boolean b(World world, BlockPosition blockposition) {
/*  30 */     return world.getType(blockposition.down()).q();
/*     */   }
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {}
/*     */   
/*     */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/*  36 */     if (!b(world, blockposition, iblockdata)) {
/*  37 */       boolean flag = e(world, blockposition, iblockdata);
/*     */       
/*  39 */       if (this.d && !flag) {
/*     */         
/*  41 */         if (CraftEventFactory.callRedstoneChange(world, blockposition.getX(), blockposition.getY(), blockposition.getZ(), 15, 0).getNewCurrent() != 0) {
/*     */           return;
/*     */         }
/*     */         
/*  45 */         world.setTypeAndData(blockposition, z(iblockdata), 2);
/*  46 */       } else if (!this.d) {
/*     */         
/*  48 */         if (CraftEventFactory.callRedstoneChange(world, blockposition.getX(), blockposition.getY(), blockposition.getZ(), 0, 15).getNewCurrent() != 15) {
/*     */           return;
/*     */         }
/*     */         
/*  52 */         world.setTypeAndData(blockposition, y(iblockdata), 2);
/*  53 */         if (!flag) {
/*  54 */           world.a(blockposition, y(iblockdata).getBlock(), E(iblockdata), -1);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean A(IBlockData iblockdata) {
/*  62 */     return this.d;
/*     */   }
/*     */   
/*     */   public int c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/*  66 */     return iblockdata.a(iblockaccess, blockposition, enumdirection);
/*     */   }
/*     */   
/*     */   public int b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/*  70 */     return !A(iblockdata) ? 0 : ((iblockdata.get(FACING) == enumdirection) ? a(iblockaccess, blockposition, iblockdata) : 0);
/*     */   }
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
/*  74 */     if (b(world, blockposition)) {
/*  75 */       g(world, blockposition, iblockdata);
/*     */     } else {
/*  77 */       b(world, blockposition, iblockdata, 0);
/*  78 */       world.setAir(blockposition);
/*  79 */       EnumDirection[] aenumdirection = EnumDirection.values();
/*  80 */       int i = aenumdirection.length;
/*     */       
/*  82 */       for (int j = 0; j < i; j++) {
/*  83 */         EnumDirection enumdirection = aenumdirection[j];
/*     */         
/*  85 */         world.applyPhysics(blockposition.shift(enumdirection), this, false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void g(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  92 */     if (!b(world, blockposition, iblockdata)) {
/*  93 */       boolean flag = e(world, blockposition, iblockdata);
/*     */       
/*  95 */       if (this.d != flag && !world.a(blockposition, this)) {
/*  96 */         byte b0 = -1;
/*     */         
/*  98 */         if (i(world, blockposition, iblockdata)) {
/*  99 */           b0 = -3;
/* 100 */         } else if (this.d) {
/* 101 */           b0 = -2;
/*     */         } 
/*     */         
/* 104 */         world.a(blockposition, this, x(iblockdata), b0);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata) {
/* 111 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean e(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 115 */     return (f(world, blockposition, iblockdata) > 0);
/*     */   }
/*     */   
/*     */   protected int f(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 119 */     EnumDirection enumdirection = iblockdata.<EnumDirection>get(FACING);
/* 120 */     BlockPosition blockposition1 = blockposition.shift(enumdirection);
/* 121 */     int i = world.getBlockFacePower(blockposition1, enumdirection);
/*     */     
/* 123 */     if (i >= 15) {
/* 124 */       return i;
/*     */     }
/* 126 */     IBlockData iblockdata1 = world.getType(blockposition1);
/*     */     
/* 128 */     return Math.max(i, (iblockdata1.getBlock() == Blocks.REDSTONE_WIRE) ? ((Integer)iblockdata1.<Integer>get(BlockRedstoneWire.POWER)).intValue() : 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int c(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata) {
/* 133 */     EnumDirection enumdirection = iblockdata.<EnumDirection>get(FACING);
/* 134 */     EnumDirection enumdirection1 = enumdirection.e();
/* 135 */     EnumDirection enumdirection2 = enumdirection.f();
/*     */     
/* 137 */     return Math.max(a(iblockaccess, blockposition.shift(enumdirection1), enumdirection1), a(iblockaccess, blockposition.shift(enumdirection2), enumdirection2));
/*     */   }
/*     */   
/*     */   protected int a(IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 141 */     IBlockData iblockdata = iblockaccess.getType(blockposition);
/* 142 */     Block block = iblockdata.getBlock();
/*     */     
/* 144 */     return B(iblockdata) ? ((block == Blocks.REDSTONE_BLOCK) ? 15 : ((block == Blocks.REDSTONE_WIRE) ? ((Integer)iblockdata.<Integer>get(BlockRedstoneWire.POWER)).intValue() : iblockaccess.getBlockPower(blockposition, enumdirection))) : 0;
/*     */   }
/*     */   
/*     */   public boolean isPowerSource(IBlockData iblockdata) {
/* 148 */     return true;
/*     */   }
/*     */   
/*     */   public IBlockData getPlacedState(World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2, int i, EntityLiving entityliving) {
/* 152 */     return getBlockData().set(FACING, entityliving.getDirection().opposite());
/*     */   }
/*     */   
/*     */   public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, EntityLiving entityliving, ItemStack itemstack) {
/* 156 */     if (e(world, blockposition, iblockdata)) {
/* 157 */       world.a(blockposition, this, 1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 163 */     h(world, blockposition, iblockdata);
/*     */   }
/*     */   
/*     */   protected void h(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 167 */     EnumDirection enumdirection = iblockdata.<EnumDirection>get(FACING);
/* 168 */     BlockPosition blockposition1 = blockposition.shift(enumdirection.opposite());
/*     */     
/* 170 */     world.a(blockposition1, this, blockposition);
/* 171 */     world.a(blockposition1, this, enumdirection);
/*     */   }
/*     */   
/*     */   public void postBreak(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 175 */     if (this.d) {
/* 176 */       EnumDirection[] aenumdirection = EnumDirection.values();
/* 177 */       int i = aenumdirection.length;
/*     */       
/* 179 */       for (int j = 0; j < i; j++) {
/* 180 */         EnumDirection enumdirection = aenumdirection[j];
/*     */         
/* 182 */         world.applyPhysics(blockposition.shift(enumdirection), this, false);
/*     */       } 
/*     */     } 
/*     */     
/* 186 */     super.postBreak(world, blockposition, iblockdata);
/*     */   }
/*     */   
/*     */   public boolean b(IBlockData iblockdata) {
/* 190 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean B(IBlockData iblockdata) {
/* 194 */     return iblockdata.m();
/*     */   }
/*     */   
/*     */   protected int a(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata) {
/* 198 */     return 15;
/*     */   }
/*     */   
/*     */   public static boolean isDiode(IBlockData iblockdata) {
/* 202 */     return !(!Blocks.UNPOWERED_REPEATER.D(iblockdata) && !Blocks.UNPOWERED_COMPARATOR.D(iblockdata));
/*     */   }
/*     */   
/*     */   public boolean D(IBlockData iblockdata) {
/* 206 */     Block block = iblockdata.getBlock();
/*     */     
/* 208 */     return !(block != y(getBlockData()).getBlock() && block != z(getBlockData()).getBlock());
/*     */   }
/*     */   
/*     */   public boolean i(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 212 */     EnumDirection enumdirection = ((EnumDirection)iblockdata.<EnumDirection>get(FACING)).opposite();
/* 213 */     BlockPosition blockposition1 = blockposition.shift(enumdirection);
/*     */     
/* 215 */     return isDiode(world.getType(blockposition1)) ? ((world.getType(blockposition1).get(FACING) != enumdirection)) : false;
/*     */   }
/*     */   
/*     */   protected int E(IBlockData iblockdata) {
/* 219 */     return x(iblockdata);
/*     */   }
/*     */   
/*     */   protected abstract int x(IBlockData paramIBlockData);
/*     */   
/*     */   protected abstract IBlockData y(IBlockData paramIBlockData);
/*     */   
/*     */   protected abstract IBlockData z(IBlockData paramIBlockData);
/*     */   
/*     */   public boolean d(Block block) {
/* 229 */     return D(block.getBlockData());
/*     */   }
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
/* 233 */     return (enumdirection == EnumDirection.DOWN) ? EnumBlockFaceShape.SOLID : EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockDiodeAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */