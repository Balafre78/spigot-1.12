/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import java.util.Random;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockRedstoneEvent;
/*     */ 
/*     */ public class BlockDoor extends Block {
/*   9 */   public static final BlockStateDirection FACING = BlockFacingHorizontal.FACING;
/*  10 */   public static final BlockStateBoolean OPEN = BlockStateBoolean.of("open");
/*  11 */   public static final BlockStateEnum<EnumDoorHinge> HINGE = BlockStateEnum.of("hinge", EnumDoorHinge.class);
/*  12 */   public static final BlockStateBoolean POWERED = BlockStateBoolean.of("powered");
/*  13 */   public static final BlockStateEnum<EnumDoorHalf> HALF = BlockStateEnum.of("half", EnumDoorHalf.class);
/*  14 */   protected static final AxisAlignedBB f = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.1875D);
/*  15 */   protected static final AxisAlignedBB g = new AxisAlignedBB(0.0D, 0.0D, 0.8125D, 1.0D, 1.0D, 1.0D);
/*  16 */   protected static final AxisAlignedBB B = new AxisAlignedBB(0.8125D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*  17 */   protected static final AxisAlignedBB C = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.1875D, 1.0D, 1.0D);
/*     */   
/*     */   protected BlockDoor(Material material) {
/*  20 */     super(material);
/*  21 */     w(this.blockStateList.getBlockData().<Comparable, EnumDirection>set(FACING, EnumDirection.NORTH).<Comparable, Boolean>set(OPEN, Boolean.valueOf(false)).<EnumDoorHinge, EnumDoorHinge>set(HINGE, EnumDoorHinge.LEFT).<Comparable, Boolean>set(POWERED, Boolean.valueOf(false)).set(HALF, EnumDoorHalf.LOWER));
/*     */   }
/*     */   
/*     */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  25 */     iblockdata = iblockdata.c(iblockaccess, blockposition);
/*  26 */     EnumDirection enumdirection = iblockdata.<EnumDirection>get(FACING);
/*  27 */     boolean flag = !((Boolean)iblockdata.<Boolean>get(OPEN)).booleanValue();
/*  28 */     boolean flag1 = (iblockdata.get(HINGE) == EnumDoorHinge.RIGHT);
/*     */     
/*  30 */     switch (enumdirection) {
/*     */       
/*     */       default:
/*  33 */         return flag ? C : (flag1 ? g : f);
/*     */       
/*     */       case SOUTH:
/*  36 */         return flag ? f : (flag1 ? C : B);
/*     */       
/*     */       case WEST:
/*  39 */         return flag ? B : (flag1 ? f : g);
/*     */       case NORTH:
/*     */         break;
/*  42 */     }  return flag ? g : (flag1 ? B : C);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  47 */     return LocaleI18n.get((String.valueOf(a()) + ".name").replaceAll("tile", "item"));
/*     */   }
/*     */   
/*     */   public boolean b(IBlockData iblockdata) {
/*  51 */     return false;
/*     */   }
/*     */   
/*     */   public boolean b(IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  55 */     return g(c(iblockaccess, blockposition));
/*     */   }
/*     */   
/*     */   public boolean c(IBlockData iblockdata) {
/*  59 */     return false;
/*     */   }
/*     */   
/*     */   private int e() {
/*  63 */     return (this.material == Material.ORE) ? 1011 : 1012;
/*     */   }
/*     */   
/*     */   private int g() {
/*  67 */     return (this.material == Material.ORE) ? 1005 : 1006;
/*     */   }
/*     */   
/*     */   public MaterialMapColor c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  71 */     return (iblockdata.getBlock() == Blocks.IRON_DOOR) ? MaterialMapColor.i : ((iblockdata.getBlock() == Blocks.WOODEN_DOOR) ? BlockWood.EnumLogVariant.OAK.c() : ((iblockdata.getBlock() == Blocks.SPRUCE_DOOR) ? BlockWood.EnumLogVariant.SPRUCE.c() : ((iblockdata.getBlock() == Blocks.BIRCH_DOOR) ? BlockWood.EnumLogVariant.BIRCH.c() : ((iblockdata.getBlock() == Blocks.JUNGLE_DOOR) ? BlockWood.EnumLogVariant.JUNGLE.c() : ((iblockdata.getBlock() == Blocks.ACACIA_DOOR) ? BlockWood.EnumLogVariant.ACACIA.c() : ((iblockdata.getBlock() == Blocks.DARK_OAK_DOOR) ? BlockWood.EnumLogVariant.DARK_OAK.c() : super.c(iblockdata, iblockaccess, blockposition)))))));
/*     */   }
/*     */   
/*     */   public boolean interact(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
/*  75 */     if (this.material == Material.ORE) {
/*  76 */       return false;
/*     */     }
/*  78 */     BlockPosition blockposition1 = (iblockdata.get(HALF) == EnumDoorHalf.LOWER) ? blockposition : blockposition.down();
/*  79 */     IBlockData iblockdata1 = blockposition.equals(blockposition1) ? iblockdata : world.getType(blockposition1);
/*     */     
/*  81 */     if (iblockdata1.getBlock() != this) {
/*  82 */       return false;
/*     */     }
/*  84 */     iblockdata = iblockdata1.a(OPEN);
/*  85 */     world.setTypeAndData(blockposition1, iblockdata, 10);
/*  86 */     world.b(blockposition1, blockposition);
/*  87 */     world.a(entityhuman, ((Boolean)iblockdata.<Boolean>get(OPEN)).booleanValue() ? g() : e(), blockposition, 0);
/*  88 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDoor(World world, BlockPosition blockposition, boolean flag) {
/*  94 */     IBlockData iblockdata = world.getType(blockposition);
/*     */     
/*  96 */     if (iblockdata.getBlock() == this) {
/*  97 */       BlockPosition blockposition1 = (iblockdata.get(HALF) == EnumDoorHalf.LOWER) ? blockposition : blockposition.down();
/*  98 */       IBlockData iblockdata1 = (blockposition == blockposition1) ? iblockdata : world.getType(blockposition1);
/*     */       
/* 100 */       if (iblockdata1.getBlock() == this && ((Boolean)iblockdata1.<Boolean>get(OPEN)).booleanValue() != flag) {
/* 101 */         world.setTypeAndData(blockposition1, iblockdata1.set(OPEN, Boolean.valueOf(flag)), 10);
/* 102 */         world.b(blockposition1, blockposition);
/* 103 */         world.a((EntityHuman)null, flag ? g() : e(), blockposition, 0);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
/* 110 */     if (iblockdata.get(HALF) == EnumDoorHalf.UPPER) {
/* 111 */       BlockPosition blockposition2 = blockposition.down();
/* 112 */       IBlockData iblockdata1 = world.getType(blockposition2);
/*     */       
/* 114 */       if (iblockdata1.getBlock() != this) {
/* 115 */         world.setAir(blockposition);
/* 116 */       } else if (block != this) {
/* 117 */         iblockdata1.doPhysics(world, blockposition2, block, blockposition1);
/*     */       } 
/*     */     } else {
/* 120 */       boolean flag = false;
/* 121 */       BlockPosition blockposition3 = blockposition.up();
/* 122 */       IBlockData iblockdata2 = world.getType(blockposition3);
/*     */       
/* 124 */       if (iblockdata2.getBlock() != this) {
/* 125 */         world.setAir(blockposition);
/* 126 */         flag = true;
/*     */       } 
/*     */       
/* 129 */       if (!world.getType(blockposition.down()).q()) {
/* 130 */         world.setAir(blockposition);
/* 131 */         flag = true;
/* 132 */         if (iblockdata2.getBlock() == this) {
/* 133 */           world.setAir(blockposition3);
/*     */         }
/*     */       } 
/*     */       
/* 137 */       if (flag) {
/* 138 */         if (!world.isClientSide) {
/* 139 */           b(world, blockposition, iblockdata, 0);
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 144 */         CraftWorld craftWorld = world.getWorld();
/* 145 */         Block bukkitBlock = craftWorld.getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 146 */         Block blockTop = craftWorld.getBlockAt(blockposition3.getX(), blockposition3.getY(), blockposition3.getZ());
/*     */         
/* 148 */         int power = bukkitBlock.getBlockPower();
/* 149 */         int powerTop = blockTop.getBlockPower();
/* 150 */         if (powerTop > power) power = powerTop; 
/* 151 */         int oldPower = ((Boolean)iblockdata2.<Boolean>get(POWERED)).booleanValue() ? 15 : 0;
/*     */         
/* 153 */         if ((((oldPower == 0) ? 1 : 0) ^ ((power == 0) ? 1 : 0)) != 0) {
/* 154 */           BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(bukkitBlock, oldPower, power);
/* 155 */           world.getServer().getPluginManager().callEvent((Event)eventRedstone);
/*     */           
/* 157 */           boolean flag1 = (eventRedstone.getNewCurrent() > 0);
/*     */           
/* 159 */           world.setTypeAndData(blockposition3, iblockdata2.set(POWERED, Boolean.valueOf(flag1)), 2);
/* 160 */           if (flag1 != ((Boolean)iblockdata.<Boolean>get(OPEN)).booleanValue()) {
/* 161 */             world.setTypeAndData(blockposition, iblockdata.set(OPEN, Boolean.valueOf(flag1)), 2);
/* 162 */             world.b(blockposition, blockposition);
/* 163 */             world.a((EntityHuman)null, flag1 ? g() : e(), blockposition, 0);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getDropType(IBlockData iblockdata, Random random, int i) {
/* 172 */     return (iblockdata.get(HALF) == EnumDoorHalf.UPPER) ? Items.a : h();
/*     */   }
/*     */   
/*     */   public boolean canPlace(World world, BlockPosition blockposition) {
/* 176 */     return (blockposition.getY() >= 255) ? false : ((world.getType(blockposition.down()).q() && super.canPlace(world, blockposition) && super.canPlace(world, blockposition.up())));
/*     */   }
/*     */   
/*     */   public EnumPistonReaction h(IBlockData iblockdata) {
/* 180 */     return EnumPistonReaction.DESTROY;
/*     */   }
/*     */   
/*     */   public static int c(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 184 */     IBlockData iblockdata = iblockaccess.getType(blockposition);
/* 185 */     int i = iblockdata.getBlock().toLegacyData(iblockdata);
/* 186 */     boolean flag = i(i);
/* 187 */     IBlockData iblockdata1 = iblockaccess.getType(blockposition.down());
/* 188 */     int j = iblockdata1.getBlock().toLegacyData(iblockdata1);
/* 189 */     int k = flag ? j : i;
/* 190 */     IBlockData iblockdata2 = iblockaccess.getType(blockposition.up());
/* 191 */     int l = iblockdata2.getBlock().toLegacyData(iblockdata2);
/* 192 */     int i1 = flag ? i : l;
/* 193 */     boolean flag1 = ((i1 & 0x1) != 0);
/* 194 */     boolean flag2 = ((i1 & 0x2) != 0);
/*     */     
/* 196 */     return b(k) | (flag ? 8 : 0) | (flag1 ? 16 : 0) | (flag2 ? 32 : 0);
/*     */   }
/*     */   
/*     */   public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 200 */     return new ItemStack(h());
/*     */   }
/*     */   
/*     */   private Item h() {
/* 204 */     return (this == Blocks.IRON_DOOR) ? Items.IRON_DOOR : ((this == Blocks.SPRUCE_DOOR) ? Items.SPRUCE_DOOR : ((this == Blocks.BIRCH_DOOR) ? Items.BIRCH_DOOR : ((this == Blocks.JUNGLE_DOOR) ? Items.JUNGLE_DOOR : ((this == Blocks.ACACIA_DOOR) ? Items.ACACIA_DOOR : ((this == Blocks.DARK_OAK_DOOR) ? Items.DARK_OAK_DOOR : Items.WOODEN_DOOR)))));
/*     */   }
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman) {
/* 208 */     BlockPosition blockposition1 = blockposition.down();
/* 209 */     BlockPosition blockposition2 = blockposition.up();
/*     */     
/* 211 */     if (entityhuman.abilities.canInstantlyBuild && iblockdata.get(HALF) == EnumDoorHalf.UPPER && world.getType(blockposition1).getBlock() == this) {
/* 212 */       world.setAir(blockposition1);
/*     */     }
/*     */     
/* 215 */     if (iblockdata.get(HALF) == EnumDoorHalf.LOWER && world.getType(blockposition2).getBlock() == this) {
/* 216 */       if (entityhuman.abilities.canInstantlyBuild) {
/* 217 */         world.setAir(blockposition);
/*     */       }
/*     */       
/* 220 */       world.setAir(blockposition2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 228 */     if (iblockdata.get(HALF) == EnumDoorHalf.LOWER) {
/* 229 */       IBlockData iblockdata1 = iblockaccess.getType(blockposition.up());
/* 230 */       if (iblockdata1.getBlock() == this) {
/* 231 */         iblockdata = iblockdata.<EnumDoorHinge, EnumDoorHinge>set(HINGE, iblockdata1.<EnumDoorHinge>get(HINGE)).set(POWERED, iblockdata1.<Boolean>get(POWERED));
/*     */       }
/*     */     } else {
/* 234 */       IBlockData iblockdata1 = iblockaccess.getType(blockposition.down());
/* 235 */       if (iblockdata1.getBlock() == this) {
/* 236 */         iblockdata = iblockdata.<Comparable, EnumDirection>set(FACING, iblockdata1.<EnumDirection>get(FACING)).set(OPEN, iblockdata1.<Boolean>get(OPEN));
/*     */       }
/*     */     } 
/*     */     
/* 240 */     return iblockdata;
/*     */   }
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 244 */     return (iblockdata.get(HALF) != EnumDoorHalf.LOWER) ? iblockdata : iblockdata.<Comparable, EnumDirection>set(FACING, enumblockrotation.a(iblockdata.<EnumDirection>get(FACING)));
/*     */   }
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/* 248 */     return (enumblockmirror == EnumBlockMirror.NONE) ? iblockdata : iblockdata.a(enumblockmirror.a(iblockdata.<EnumDirection>get(FACING))).<EnumDoorHinge>a(HINGE);
/*     */   }
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/* 252 */     return ((i & 0x8) > 0) ? getBlockData().<EnumDoorHalf, EnumDoorHalf>set(HALF, EnumDoorHalf.UPPER).<EnumDoorHinge, EnumDoorHinge>set(HINGE, ((i & 0x1) > 0) ? EnumDoorHinge.RIGHT : EnumDoorHinge.LEFT).<Comparable, Boolean>set(POWERED, Boolean.valueOf(((i & 0x2) > 0))) : getBlockData().<EnumDoorHalf, EnumDoorHalf>set(HALF, EnumDoorHalf.LOWER).<Comparable, EnumDirection>set(FACING, EnumDirection.fromType2(i & 0x3).f()).<Comparable, Boolean>set(OPEN, Boolean.valueOf(((i & 0x4) > 0)));
/*     */   }
/*     */   public int toLegacyData(IBlockData iblockdata) {
/*     */     int i;
/* 256 */     byte b0 = 0;
/*     */ 
/*     */     
/* 259 */     if (iblockdata.get(HALF) == EnumDoorHalf.UPPER) {
/* 260 */       i = b0 | 0x8;
/* 261 */       if (iblockdata.get(HINGE) == EnumDoorHinge.RIGHT) {
/* 262 */         i |= 0x1;
/*     */       }
/*     */       
/* 265 */       if (((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue()) {
/* 266 */         i |= 0x2;
/*     */       }
/*     */     } else {
/* 269 */       i = b0 | ((EnumDirection)iblockdata.<EnumDirection>get(FACING)).e().get2DRotationValue();
/* 270 */       if (((Boolean)iblockdata.<Boolean>get(OPEN)).booleanValue()) {
/* 271 */         i |= 0x4;
/*     */       }
/*     */     } 
/*     */     
/* 275 */     return i;
/*     */   }
/*     */   
/*     */   protected static int b(int i) {
/* 279 */     return i & 0x7;
/*     */   }
/*     */   
/*     */   public static boolean d(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 283 */     return g(c(iblockaccess, blockposition));
/*     */   }
/*     */   
/*     */   public static EnumDirection f(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 287 */     return f(c(iblockaccess, blockposition));
/*     */   }
/*     */   
/*     */   public static EnumDirection f(int i) {
/* 291 */     return EnumDirection.fromType2(i & 0x3).f();
/*     */   }
/*     */   
/*     */   protected static boolean g(int i) {
/* 295 */     return ((i & 0x4) != 0);
/*     */   }
/*     */   
/*     */   protected static boolean i(int i) {
/* 299 */     return ((i & 0x8) != 0);
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 303 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { HALF, FACING, OPEN, HINGE, POWERED });
/*     */   }
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
/* 307 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */   
/*     */   public enum EnumDoorHinge
/*     */     implements INamable {
/* 312 */     LEFT, RIGHT;
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 317 */       return getName();
/*     */     }
/*     */     
/*     */     public String getName() {
/* 321 */       return (this == LEFT) ? "left" : "right";
/*     */     }
/*     */   }
/*     */   
/*     */   public enum EnumDoorHalf
/*     */     implements INamable {
/* 327 */     UPPER, LOWER;
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 332 */       return getName();
/*     */     }
/*     */     
/*     */     public String getName() {
/* 336 */       return (this == UPPER) ? "upper" : "lower";
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockDoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */