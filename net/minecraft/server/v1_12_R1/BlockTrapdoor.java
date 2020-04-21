/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
/*     */ import org.bukkit.event.block.BlockRedstoneEvent;
/*     */ 
/*     */ public class BlockTrapdoor extends Block {
/*   8 */   public static final BlockStateDirection FACING = BlockFacingHorizontal.FACING;
/*   9 */   public static final BlockStateBoolean OPEN = BlockStateBoolean.of("open");
/*  10 */   public static final BlockStateEnum<EnumTrapdoorHalf> HALF = BlockStateEnum.of("half", EnumTrapdoorHalf.class);
/*  11 */   protected static final AxisAlignedBB d = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.1875D, 1.0D, 1.0D);
/*  12 */   protected static final AxisAlignedBB e = new AxisAlignedBB(0.8125D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*  13 */   protected static final AxisAlignedBB f = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.1875D);
/*  14 */   protected static final AxisAlignedBB g = new AxisAlignedBB(0.0D, 0.0D, 0.8125D, 1.0D, 1.0D, 1.0D);
/*  15 */   protected static final AxisAlignedBB B = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D);
/*  16 */   protected static final AxisAlignedBB C = new AxisAlignedBB(0.0D, 0.8125D, 0.0D, 1.0D, 1.0D, 1.0D);
/*     */   
/*     */   protected BlockTrapdoor(Material material) {
/*  19 */     super(material);
/*  20 */     w(this.blockStateList.getBlockData().<Comparable, EnumDirection>set(FACING, EnumDirection.NORTH).<Comparable, Boolean>set(OPEN, Boolean.valueOf(false)).set(HALF, EnumTrapdoorHalf.BOTTOM));
/*  21 */     a(CreativeModeTab.d);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*     */     AxisAlignedBB axisalignedbb;
/*  27 */     if (((Boolean)iblockdata.<Boolean>get(OPEN)).booleanValue())
/*  28 */     { switch ((EnumDirection)iblockdata.get(FACING))
/*     */       
/*     */       { default:
/*  31 */           axisalignedbb = g;
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
/*  51 */           return axisalignedbb;case SOUTH: axisalignedbb = f; return axisalignedbb;case WEST: axisalignedbb = e; return axisalignedbb;case EAST: break; }  axisalignedbb = d; } else if (iblockdata.get(HALF) == EnumTrapdoorHalf.TOP) { axisalignedbb = C; } else { axisalignedbb = B; }  return axisalignedbb;
/*     */   }
/*     */   
/*     */   public boolean b(IBlockData iblockdata) {
/*  55 */     return false;
/*     */   }
/*     */   
/*     */   public boolean c(IBlockData iblockdata) {
/*  59 */     return false;
/*     */   }
/*     */   
/*     */   public boolean b(IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  63 */     return !((Boolean)iblockaccess.getType(blockposition).<Boolean>get(OPEN)).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean interact(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
/*  67 */     if (this.material == Material.ORE) {
/*  68 */       return false;
/*     */     }
/*  70 */     iblockdata = iblockdata.a(OPEN);
/*  71 */     world.setTypeAndData(blockposition, iblockdata, 2);
/*  72 */     a(entityhuman, world, blockposition, ((Boolean)iblockdata.<Boolean>get(OPEN)).booleanValue());
/*  73 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(@Nullable EntityHuman entityhuman, World world, BlockPosition blockposition, boolean flag) {
/*  80 */     if (flag) {
/*  81 */       int i = (this.material == Material.ORE) ? 1037 : 1007;
/*  82 */       world.a(entityhuman, i, blockposition, 0);
/*     */     } else {
/*  84 */       int i = (this.material == Material.ORE) ? 1036 : 1013;
/*  85 */       world.a(entityhuman, i, blockposition, 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
/*  91 */     if (!world.isClientSide) {
/*  92 */       boolean flag = world.isBlockIndirectlyPowered(blockposition);
/*     */       
/*  94 */       if (flag || block.getBlockData().m()) {
/*     */         
/*  96 */         CraftWorld craftWorld = world.getWorld();
/*  97 */         Block bblock = craftWorld.getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*     */         
/*  99 */         int power = bblock.getBlockPower();
/* 100 */         int oldPower = ((Boolean)iblockdata.<Boolean>get(OPEN)).booleanValue() ? 15 : 0;
/*     */         
/* 102 */         if ((((oldPower == 0) ? 1 : 0) ^ ((power == 0) ? 1 : 0)) != 0 || block.getBlockData().n()) {
/* 103 */           BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(bblock, oldPower, power);
/* 104 */           world.getServer().getPluginManager().callEvent((Event)eventRedstone);
/* 105 */           flag = (eventRedstone.getNewCurrent() > 0);
/*     */         } 
/*     */         
/* 108 */         boolean flag1 = ((Boolean)iblockdata.<Boolean>get(OPEN)).booleanValue();
/*     */         
/* 110 */         if (flag1 != flag) {
/* 111 */           world.setTypeAndData(blockposition, iblockdata.set(OPEN, Boolean.valueOf(flag)), 2);
/* 112 */           a((EntityHuman)null, world, blockposition, flag);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2, int i, EntityLiving entityliving) {
/* 120 */     IBlockData iblockdata = getBlockData();
/*     */     
/* 122 */     if (enumdirection.k().c()) {
/* 123 */       iblockdata = iblockdata.<Comparable, EnumDirection>set(FACING, enumdirection).set(OPEN, Boolean.valueOf(false));
/* 124 */       iblockdata = iblockdata.set(HALF, (f1 > 0.5F) ? EnumTrapdoorHalf.TOP : EnumTrapdoorHalf.BOTTOM);
/*     */     } else {
/* 126 */       iblockdata = iblockdata.<Comparable, EnumDirection>set(FACING, entityliving.getDirection().opposite()).set(OPEN, Boolean.valueOf(false));
/* 127 */       iblockdata = iblockdata.set(HALF, (enumdirection == EnumDirection.UP) ? EnumTrapdoorHalf.BOTTOM : EnumTrapdoorHalf.TOP);
/*     */     } 
/*     */     
/* 130 */     if (world.isBlockIndirectlyPowered(blockposition)) {
/* 131 */       iblockdata = iblockdata.set(OPEN, Boolean.valueOf(true));
/*     */     }
/*     */     
/* 134 */     return iblockdata;
/*     */   }
/*     */   
/*     */   public boolean canPlace(World world, BlockPosition blockposition, EnumDirection enumdirection) {
/* 138 */     return true;
/*     */   }
/*     */   
/*     */   protected static EnumDirection b(int i) {
/* 142 */     switch (i & 0x3) {
/*     */       case 0:
/* 144 */         return EnumDirection.NORTH;
/*     */       
/*     */       case 1:
/* 147 */         return EnumDirection.SOUTH;
/*     */       
/*     */       case 2:
/* 150 */         return EnumDirection.WEST;
/*     */     } 
/*     */ 
/*     */     
/* 154 */     return EnumDirection.EAST;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static int a(EnumDirection enumdirection) {
/* 159 */     switch (enumdirection) {
/*     */       case NORTH:
/* 161 */         return 0;
/*     */       
/*     */       case SOUTH:
/* 164 */         return 1;
/*     */       
/*     */       case WEST:
/* 167 */         return 2;
/*     */     } 
/*     */ 
/*     */     
/* 171 */     return 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/* 176 */     return getBlockData().<Comparable, EnumDirection>set(FACING, b(i)).<Comparable, Boolean>set(OPEN, Boolean.valueOf(((i & 0x4) != 0))).set(HALF, ((i & 0x8) == 0) ? EnumTrapdoorHalf.BOTTOM : EnumTrapdoorHalf.TOP);
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 180 */     byte b0 = 0;
/* 181 */     int i = b0 | a(iblockdata.<EnumDirection>get(FACING));
/*     */     
/* 183 */     if (((Boolean)iblockdata.<Boolean>get(OPEN)).booleanValue()) {
/* 184 */       i |= 0x4;
/*     */     }
/*     */     
/* 187 */     if (iblockdata.get(HALF) == EnumTrapdoorHalf.TOP) {
/* 188 */       i |= 0x8;
/*     */     }
/*     */     
/* 191 */     return i;
/*     */   }
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 195 */     return iblockdata.set(FACING, enumblockrotation.a(iblockdata.<EnumDirection>get(FACING)));
/*     */   }
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/* 199 */     return iblockdata.a(enumblockmirror.a(iblockdata.<EnumDirection>get(FACING)));
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 203 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { FACING, OPEN, HALF });
/*     */   }
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
/* 207 */     return (((enumdirection == EnumDirection.UP && iblockdata.get(HALF) == EnumTrapdoorHalf.TOP) || (enumdirection == EnumDirection.DOWN && iblockdata.get(HALF) == EnumTrapdoorHalf.BOTTOM)) && !((Boolean)iblockdata.<Boolean>get(OPEN)).booleanValue()) ? EnumBlockFaceShape.SOLID : EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */   
/*     */   public enum EnumTrapdoorHalf
/*     */     implements INamable {
/* 212 */     TOP("top"), BOTTOM("bottom");
/*     */     
/*     */     private final String c;
/*     */     
/*     */     EnumTrapdoorHalf(String s) {
/* 217 */       this.c = s;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 221 */       return this.c;
/*     */     }
/*     */     
/*     */     public String getName() {
/* 225 */       return this.c;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockTrapdoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */