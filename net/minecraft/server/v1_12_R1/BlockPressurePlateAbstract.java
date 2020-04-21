/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockRedstoneEvent;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ 
/*     */ public abstract class BlockPressurePlateAbstract extends Block {
/*  10 */   protected static final AxisAlignedBB a = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.03125D, 0.9375D);
/*  11 */   protected static final AxisAlignedBB b = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.0625D, 0.9375D);
/*  12 */   protected static final AxisAlignedBB c = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.25D, 0.875D);
/*     */   
/*     */   protected BlockPressurePlateAbstract(Material material) {
/*  15 */     this(material, material.r());
/*     */   }
/*     */   
/*     */   protected BlockPressurePlateAbstract(Material material, MaterialMapColor materialmapcolor) {
/*  19 */     super(material, materialmapcolor);
/*  20 */     a(CreativeModeTab.d);
/*  21 */     a(true);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  25 */     boolean flag = (getPower(iblockdata) > 0);
/*     */     
/*  27 */     return flag ? a : b;
/*     */   }
/*     */   
/*     */   public int a(World world) {
/*  31 */     return 20;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public AxisAlignedBB a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  36 */     return k;
/*     */   }
/*     */   
/*     */   public boolean b(IBlockData iblockdata) {
/*  40 */     return false;
/*     */   }
/*     */   
/*     */   public boolean c(IBlockData iblockdata) {
/*  44 */     return false;
/*     */   }
/*     */   
/*     */   public boolean b(IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  48 */     return true;
/*     */   }
/*     */   
/*     */   public boolean d() {
/*  52 */     return true;
/*     */   }
/*     */   
/*     */   public boolean canPlace(World world, BlockPosition blockposition) {
/*  56 */     return i(world, blockposition.down());
/*     */   }
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
/*  60 */     if (!i(world, blockposition.down())) {
/*  61 */       b(world, blockposition, iblockdata, 0);
/*  62 */       world.setAir(blockposition);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean i(World world, BlockPosition blockposition) {
/*  68 */     return !(!world.getType(blockposition).q() && !(world.getType(blockposition).getBlock() instanceof BlockFence));
/*     */   }
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {}
/*     */   
/*     */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/*  74 */     if (!world.isClientSide) {
/*  75 */       int i = getPower(iblockdata);
/*     */       
/*  77 */       if (i > 0) {
/*  78 */         a(world, blockposition, iblockdata, i);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, Entity entity) {
/*  85 */     if (!world.isClientSide) {
/*  86 */       int i = getPower(iblockdata);
/*     */       
/*  88 */       if (i == 0) {
/*  89 */         a(world, blockposition, iblockdata, i);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(World world, BlockPosition blockposition, IBlockData iblockdata, int i) {
/*  96 */     int j = e(world, blockposition);
/*  97 */     boolean flag = (i > 0);
/*  98 */     boolean flag1 = (j > 0);
/*     */ 
/*     */     
/* 101 */     CraftWorld craftWorld = world.getWorld();
/* 102 */     PluginManager manager = world.getServer().getPluginManager();
/*     */     
/* 104 */     if (flag != flag1) {
/* 105 */       BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(craftWorld.getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()), i, j);
/* 106 */       manager.callEvent((Event)eventRedstone);
/*     */       
/* 108 */       flag1 = (eventRedstone.getNewCurrent() > 0);
/* 109 */       j = eventRedstone.getNewCurrent();
/*     */     } 
/*     */ 
/*     */     
/* 113 */     if (i != j) {
/* 114 */       iblockdata = a(iblockdata, j);
/* 115 */       world.setTypeAndData(blockposition, iblockdata, 2);
/* 116 */       d(world, blockposition);
/* 117 */       world.b(blockposition, blockposition);
/*     */     } 
/*     */     
/* 120 */     if (!flag1 && flag) {
/* 121 */       c(world, blockposition);
/* 122 */     } else if (flag1 && !flag) {
/* 123 */       b(world, blockposition);
/*     */     } 
/*     */     
/* 126 */     if (flag1) {
/* 127 */       world.a(new BlockPosition(blockposition), this, a(world));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract void b(World paramWorld, BlockPosition paramBlockPosition);
/*     */   
/*     */   protected abstract void c(World paramWorld, BlockPosition paramBlockPosition);
/*     */   
/*     */   public void remove(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 137 */     if (getPower(iblockdata) > 0) {
/* 138 */       d(world, blockposition);
/*     */     }
/*     */     
/* 141 */     super.remove(world, blockposition, iblockdata);
/*     */   }
/*     */   
/*     */   protected void d(World world, BlockPosition blockposition) {
/* 145 */     world.applyPhysics(blockposition, this, false);
/* 146 */     world.applyPhysics(blockposition.down(), this, false);
/*     */   }
/*     */   
/*     */   public int b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 150 */     return getPower(iblockdata);
/*     */   }
/*     */   
/*     */   public int c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 154 */     return (enumdirection == EnumDirection.UP) ? getPower(iblockdata) : 0;
/*     */   }
/*     */   
/*     */   public boolean isPowerSource(IBlockData iblockdata) {
/* 158 */     return true;
/*     */   }
/*     */   
/*     */   public EnumPistonReaction h(IBlockData iblockdata) {
/* 162 */     return EnumPistonReaction.DESTROY;
/*     */   }
/*     */   
/*     */   protected abstract int e(World paramWorld, BlockPosition paramBlockPosition);
/*     */   
/*     */   protected abstract int getPower(IBlockData paramIBlockData);
/*     */   
/*     */   protected abstract IBlockData a(IBlockData paramIBlockData, int paramInt);
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
/* 172 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockPressurePlateAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */