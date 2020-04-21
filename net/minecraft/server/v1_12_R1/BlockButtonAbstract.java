/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockRedstoneEvent;
/*     */ import org.bukkit.event.entity.EntityInteractEvent;
/*     */ 
/*     */ public abstract class BlockButtonAbstract
/*     */   extends BlockDirectional {
/*  14 */   public static final BlockStateBoolean POWERED = BlockStateBoolean.of("powered");
/*  15 */   protected static final AxisAlignedBB b = new AxisAlignedBB(0.3125D, 0.875D, 0.375D, 0.6875D, 1.0D, 0.625D);
/*  16 */   protected static final AxisAlignedBB c = new AxisAlignedBB(0.3125D, 0.0D, 0.375D, 0.6875D, 0.125D, 0.625D);
/*  17 */   protected static final AxisAlignedBB d = new AxisAlignedBB(0.3125D, 0.375D, 0.875D, 0.6875D, 0.625D, 1.0D);
/*  18 */   protected static final AxisAlignedBB e = new AxisAlignedBB(0.3125D, 0.375D, 0.0D, 0.6875D, 0.625D, 0.125D);
/*  19 */   protected static final AxisAlignedBB f = new AxisAlignedBB(0.875D, 0.375D, 0.3125D, 1.0D, 0.625D, 0.6875D);
/*  20 */   protected static final AxisAlignedBB g = new AxisAlignedBB(0.0D, 0.375D, 0.3125D, 0.125D, 0.625D, 0.6875D);
/*  21 */   protected static final AxisAlignedBB B = new AxisAlignedBB(0.3125D, 0.9375D, 0.375D, 0.6875D, 1.0D, 0.625D);
/*  22 */   protected static final AxisAlignedBB C = new AxisAlignedBB(0.3125D, 0.0D, 0.375D, 0.6875D, 0.0625D, 0.625D);
/*  23 */   protected static final AxisAlignedBB D = new AxisAlignedBB(0.3125D, 0.375D, 0.9375D, 0.6875D, 0.625D, 1.0D);
/*  24 */   protected static final AxisAlignedBB E = new AxisAlignedBB(0.3125D, 0.375D, 0.0D, 0.6875D, 0.625D, 0.0625D);
/*  25 */   protected static final AxisAlignedBB F = new AxisAlignedBB(0.9375D, 0.375D, 0.3125D, 1.0D, 0.625D, 0.6875D);
/*  26 */   protected static final AxisAlignedBB G = new AxisAlignedBB(0.0D, 0.375D, 0.3125D, 0.0625D, 0.625D, 0.6875D);
/*     */   private final boolean I;
/*     */   
/*     */   protected BlockButtonAbstract(boolean flag) {
/*  30 */     super(Material.ORIENTABLE);
/*  31 */     w(this.blockStateList.getBlockData().<Comparable, EnumDirection>set(FACING, EnumDirection.NORTH).set(POWERED, Boolean.valueOf(false)));
/*  32 */     a(true);
/*  33 */     a(CreativeModeTab.d);
/*  34 */     this.I = flag;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public AxisAlignedBB a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  39 */     return k;
/*     */   }
/*     */   
/*     */   public int a(World world) {
/*  43 */     return this.I ? 30 : 20;
/*     */   }
/*     */   
/*     */   public boolean b(IBlockData iblockdata) {
/*  47 */     return false;
/*     */   }
/*     */   
/*     */   public boolean c(IBlockData iblockdata) {
/*  51 */     return false;
/*     */   }
/*     */   
/*     */   public boolean canPlace(World world, BlockPosition blockposition, EnumDirection enumdirection) {
/*  55 */     return a(world, blockposition, enumdirection);
/*     */   }
/*     */   
/*     */   public boolean canPlace(World world, BlockPosition blockposition) {
/*  59 */     EnumDirection[] aenumdirection = EnumDirection.values();
/*  60 */     int i = aenumdirection.length;
/*     */     
/*  62 */     for (int j = 0; j < i; j++) {
/*  63 */       EnumDirection enumdirection = aenumdirection[j];
/*     */       
/*  65 */       if (a(world, blockposition, enumdirection)) {
/*  66 */         return true;
/*     */       }
/*     */     } 
/*     */     
/*  70 */     return false;
/*     */   }
/*     */   
/*     */   protected static boolean a(World world, BlockPosition blockposition, EnumDirection enumdirection) {
/*  74 */     BlockPosition blockposition1 = blockposition.shift(enumdirection.opposite());
/*  75 */     IBlockData iblockdata = world.getType(blockposition1);
/*  76 */     boolean flag = (iblockdata.d(world, blockposition1, enumdirection) == EnumBlockFaceShape.SOLID);
/*  77 */     Block block = iblockdata.getBlock();
/*     */     
/*  79 */     return (enumdirection == EnumDirection.UP) ? (!(block != Blocks.HOPPER && (b(block) || !flag))) : ((!c(block) && flag));
/*     */   }
/*     */   
/*     */   public IBlockData getPlacedState(World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2, int i, EntityLiving entityliving) {
/*  83 */     return a(world, blockposition, enumdirection) ? getBlockData().<Comparable, EnumDirection>set(FACING, enumdirection).<Comparable, Boolean>set(POWERED, Boolean.valueOf(false)) : getBlockData().<Comparable, EnumDirection>set(FACING, EnumDirection.DOWN).<Comparable, Boolean>set(POWERED, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
/*  87 */     if (e(world, blockposition, iblockdata) && !a(world, blockposition, iblockdata.<EnumDirection>get(FACING))) {
/*  88 */       b(world, blockposition, iblockdata, 0);
/*  89 */       world.setAir(blockposition);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean e(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  95 */     if (canPlace(world, blockposition)) {
/*  96 */       return true;
/*     */     }
/*  98 */     b(world, blockposition, iblockdata, 0);
/*  99 */     world.setAir(blockposition);
/* 100 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 105 */     EnumDirection enumdirection = iblockdata.<EnumDirection>get(FACING);
/* 106 */     boolean flag = ((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue();
/*     */     
/* 108 */     switch (enumdirection) {
/*     */       case EAST:
/* 110 */         return flag ? G : g;
/*     */       
/*     */       case WEST:
/* 113 */         return flag ? F : f;
/*     */       
/*     */       case SOUTH:
/* 116 */         return flag ? E : e;
/*     */ 
/*     */       
/*     */       default:
/* 120 */         return flag ? D : d;
/*     */       
/*     */       case UP:
/* 123 */         return flag ? C : c;
/*     */       case null:
/*     */         break;
/* 126 */     }  return flag ? B : b;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean interact(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
/* 131 */     if (((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue()) {
/* 132 */       return true;
/*     */     }
/*     */     
/* 135 */     boolean powered = ((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue();
/* 136 */     Block block = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 137 */     int old = powered ? 15 : 0;
/* 138 */     int current = !powered ? 15 : 0;
/*     */     
/* 140 */     BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, old, current);
/* 141 */     world.getServer().getPluginManager().callEvent((Event)eventRedstone);
/*     */     
/* 143 */     if (((eventRedstone.getNewCurrent() > 0) ? true : false) != (powered ? false : true)) {
/* 144 */       return true;
/*     */     }
/*     */     
/* 147 */     world.setTypeAndData(blockposition, iblockdata.set(POWERED, Boolean.valueOf(true)), 3);
/* 148 */     world.b(blockposition, blockposition);
/* 149 */     a(entityhuman, world, blockposition);
/* 150 */     c(world, blockposition, iblockdata.<EnumDirection>get(FACING));
/* 151 */     world.a(blockposition, this, a(world));
/* 152 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 161 */     if (((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue()) {
/* 162 */       c(world, blockposition, iblockdata.<EnumDirection>get(FACING));
/*     */     }
/*     */     
/* 165 */     super.remove(world, blockposition, iblockdata);
/*     */   }
/*     */   
/*     */   public int b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 169 */     return ((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue() ? 15 : 0;
/*     */   }
/*     */   
/*     */   public int c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 173 */     return !((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue() ? 0 : ((iblockdata.get(FACING) == enumdirection) ? 15 : 0);
/*     */   }
/*     */   
/*     */   public boolean isPowerSource(IBlockData iblockdata) {
/* 177 */     return true;
/*     */   }
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {}
/*     */   
/*     */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/* 183 */     if (!world.isClientSide && (
/* 184 */       (Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue()) {
/* 185 */       if (this.I) {
/* 186 */         d(iblockdata, world, blockposition);
/*     */       } else {
/*     */         
/* 189 */         Block block = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*     */         
/* 191 */         BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, 15, 0);
/* 192 */         world.getServer().getPluginManager().callEvent((Event)eventRedstone);
/*     */         
/* 194 */         if (eventRedstone.getNewCurrent() > 0) {
/*     */           return;
/*     */         }
/*     */         
/* 198 */         world.setTypeUpdate(blockposition, iblockdata.set(POWERED, Boolean.valueOf(false)));
/* 199 */         c(world, blockposition, iblockdata.<EnumDirection>get(FACING));
/* 200 */         b(world, blockposition);
/* 201 */         world.b(blockposition, blockposition);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, Entity entity) {
/* 209 */     if (!world.isClientSide && 
/* 210 */       this.I && 
/* 211 */       !((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue()) {
/* 212 */       d(iblockdata, world, blockposition);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void d(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 219 */     List<EntityArrow> list = world.a(EntityArrow.class, iblockdata.e(world, blockposition).a(blockposition));
/* 220 */     boolean flag = !list.isEmpty();
/* 221 */     boolean flag1 = ((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue();
/*     */ 
/*     */     
/* 224 */     if (flag1 != flag && flag) {
/* 225 */       Block block = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 226 */       boolean allowed = false;
/*     */ 
/*     */       
/* 229 */       for (EntityArrow object : list) {
/* 230 */         if (object != null) {
/* 231 */           EntityInteractEvent event = new EntityInteractEvent((Entity)((Entity)object).getBukkitEntity(), block);
/* 232 */           world.getServer().getPluginManager().callEvent((Event)event);
/*     */           
/* 234 */           if (!event.isCancelled()) {
/* 235 */             allowed = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 241 */       if (!allowed) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 247 */     if (flag && !flag1) {
/*     */       
/* 249 */       Block block = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*     */       
/* 251 */       BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, 0, 15);
/* 252 */       world.getServer().getPluginManager().callEvent((Event)eventRedstone);
/*     */       
/* 254 */       if (eventRedstone.getNewCurrent() <= 0) {
/*     */         return;
/*     */       }
/*     */       
/* 258 */       world.setTypeUpdate(blockposition, iblockdata.set(POWERED, Boolean.valueOf(true)));
/* 259 */       c(world, blockposition, iblockdata.<EnumDirection>get(FACING));
/* 260 */       world.b(blockposition, blockposition);
/* 261 */       a((EntityHuman)null, world, blockposition);
/*     */     } 
/*     */     
/* 264 */     if (!flag && flag1) {
/*     */       
/* 266 */       Block block = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*     */       
/* 268 */       BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, 15, 0);
/* 269 */       world.getServer().getPluginManager().callEvent((Event)eventRedstone);
/*     */       
/* 271 */       if (eventRedstone.getNewCurrent() > 0) {
/*     */         return;
/*     */       }
/*     */       
/* 275 */       world.setTypeUpdate(blockposition, iblockdata.set(POWERED, Boolean.valueOf(false)));
/* 276 */       c(world, blockposition, iblockdata.<EnumDirection>get(FACING));
/* 277 */       world.b(blockposition, blockposition);
/* 278 */       b(world, blockposition);
/*     */     } 
/*     */     
/* 281 */     if (flag) {
/* 282 */       world.a(new BlockPosition(blockposition), this, a(world));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void c(World world, BlockPosition blockposition, EnumDirection enumdirection) {
/* 288 */     world.applyPhysics(blockposition, this, false);
/* 289 */     world.applyPhysics(blockposition.shift(enumdirection.opposite()), this, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/*     */     EnumDirection enumdirection;
/* 295 */     switch (i & 0x7) {
/*     */       case 0:
/* 297 */         enumdirection = EnumDirection.DOWN;
/*     */         break;
/*     */       
/*     */       case 1:
/* 301 */         enumdirection = EnumDirection.EAST;
/*     */         break;
/*     */       
/*     */       case 2:
/* 305 */         enumdirection = EnumDirection.WEST;
/*     */         break;
/*     */       
/*     */       case 3:
/* 309 */         enumdirection = EnumDirection.SOUTH;
/*     */         break;
/*     */       
/*     */       case 4:
/* 313 */         enumdirection = EnumDirection.NORTH;
/*     */         break;
/*     */ 
/*     */       
/*     */       default:
/* 318 */         enumdirection = EnumDirection.UP;
/*     */         break;
/*     */     } 
/* 321 */     return getBlockData().<Comparable, EnumDirection>set(FACING, enumdirection).set(POWERED, Boolean.valueOf(((i & 0x8) > 0)));
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/*     */     int i;
/* 327 */     switch ((EnumDirection)iblockdata.get(FACING)) {
/*     */       case EAST:
/* 329 */         i = 1;
/*     */         break;
/*     */       
/*     */       case WEST:
/* 333 */         i = 2;
/*     */         break;
/*     */       
/*     */       case SOUTH:
/* 337 */         i = 3;
/*     */         break;
/*     */       
/*     */       case NORTH:
/* 341 */         i = 4;
/*     */         break;
/*     */ 
/*     */       
/*     */       default:
/* 346 */         i = 5;
/*     */         break;
/*     */       
/*     */       case null:
/* 350 */         i = 0;
/*     */         break;
/*     */     } 
/* 353 */     if (((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue()) {
/* 354 */       i |= 0x8;
/*     */     }
/*     */     
/* 357 */     return i;
/*     */   }
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 361 */     return iblockdata.set(FACING, enumblockrotation.a(iblockdata.<EnumDirection>get(FACING)));
/*     */   }
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/* 365 */     return iblockdata.a(enumblockmirror.a(iblockdata.<EnumDirection>get(FACING)));
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 369 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { FACING, POWERED });
/*     */   }
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
/* 373 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */   
/*     */   protected abstract void a(@Nullable EntityHuman paramEntityHuman, World paramWorld, BlockPosition paramBlockPosition);
/*     */   
/*     */   protected abstract void b(World paramWorld, BlockPosition paramBlockPosition);
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockButtonAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */