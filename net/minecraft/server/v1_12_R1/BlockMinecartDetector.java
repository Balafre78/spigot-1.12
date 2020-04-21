/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockRedstoneEvent;
/*     */ 
/*     */ public class BlockMinecartDetector extends BlockMinecartTrackAbstract {
/*  13 */   public static final BlockStateEnum<BlockMinecartTrackAbstract.EnumTrackPosition> SHAPE = BlockStateEnum.a("shape", BlockMinecartTrackAbstract.EnumTrackPosition.class, new Predicate() {
/*     */         public boolean a(@Nullable BlockMinecartTrackAbstract.EnumTrackPosition blockminecarttrackabstract_enumtrackposition) {
/*  15 */           return (blockminecarttrackabstract_enumtrackposition != BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_EAST && blockminecarttrackabstract_enumtrackposition != BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_WEST && blockminecarttrackabstract_enumtrackposition != BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_EAST && blockminecarttrackabstract_enumtrackposition != BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_WEST);
/*     */         }
/*     */         
/*     */         public boolean apply(@Nullable Object object) {
/*  19 */           return a((BlockMinecartTrackAbstract.EnumTrackPosition)object);
/*     */         }
/*     */       });
/*  22 */   public static final BlockStateBoolean POWERED = BlockStateBoolean.of("powered");
/*     */   
/*     */   public BlockMinecartDetector() {
/*  25 */     super(true);
/*  26 */     w(this.blockStateList.getBlockData().<Comparable, Boolean>set(POWERED, Boolean.valueOf(false)).set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_SOUTH));
/*  27 */     a(true);
/*     */   }
/*     */   
/*     */   public int a(World world) {
/*  31 */     return 20;
/*     */   }
/*     */   
/*     */   public boolean isPowerSource(IBlockData iblockdata) {
/*  35 */     return true;
/*     */   }
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, Entity entity) {
/*  39 */     if (!world.isClientSide && 
/*  40 */       !((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue()) {
/*  41 */       e(world, blockposition, iblockdata);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {}
/*     */   
/*     */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/*  49 */     if (!world.isClientSide && ((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue()) {
/*  50 */       e(world, blockposition, iblockdata);
/*     */     }
/*     */   }
/*     */   
/*     */   public int b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/*  55 */     return ((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue() ? 15 : 0;
/*     */   }
/*     */   
/*     */   public int c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/*  59 */     return !((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue() ? 0 : ((enumdirection == EnumDirection.UP) ? 15 : 0);
/*     */   }
/*     */   
/*     */   private void e(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  63 */     boolean flag = ((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue();
/*  64 */     boolean flag1 = false;
/*  65 */     List<EntityMinecartAbstract> list = a(world, blockposition, EntityMinecartAbstract.class, (Predicate<Entity>[])new Predicate[0]);
/*     */     
/*  67 */     if (!list.isEmpty()) {
/*  68 */       flag1 = true;
/*     */     }
/*     */ 
/*     */     
/*  72 */     if (flag != flag1) {
/*  73 */       Block block = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*     */       
/*  75 */       BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, flag ? 15 : 0, flag1 ? 15 : 0);
/*  76 */       world.getServer().getPluginManager().callEvent((Event)eventRedstone);
/*     */       
/*  78 */       flag1 = (eventRedstone.getNewCurrent() > 0);
/*     */     } 
/*     */ 
/*     */     
/*  82 */     if (flag1 && !flag) {
/*  83 */       world.setTypeAndData(blockposition, iblockdata.set(POWERED, Boolean.valueOf(true)), 3);
/*  84 */       b(world, blockposition, iblockdata, true);
/*  85 */       world.applyPhysics(blockposition, this, false);
/*  86 */       world.applyPhysics(blockposition.down(), this, false);
/*  87 */       world.b(blockposition, blockposition);
/*     */     } 
/*     */     
/*  90 */     if (!flag1 && flag) {
/*  91 */       world.setTypeAndData(blockposition, iblockdata.set(POWERED, Boolean.valueOf(false)), 3);
/*  92 */       b(world, blockposition, iblockdata, false);
/*  93 */       world.applyPhysics(blockposition, this, false);
/*  94 */       world.applyPhysics(blockposition.down(), this, false);
/*  95 */       world.b(blockposition, blockposition);
/*     */     } 
/*     */     
/*  98 */     if (flag1) {
/*  99 */       world.a(new BlockPosition(blockposition), this, a(world));
/*     */     }
/*     */     
/* 102 */     world.updateAdjacentComparators(blockposition, this);
/*     */   }
/*     */   
/*     */   protected void b(World world, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
/* 106 */     BlockMinecartTrackAbstract.MinecartTrackLogic blockminecarttrackabstract_minecarttracklogic = new BlockMinecartTrackAbstract.MinecartTrackLogic(this, world, blockposition, iblockdata);
/* 107 */     List<BlockPosition> list = blockminecarttrackabstract_minecarttracklogic.a();
/* 108 */     Iterator<BlockPosition> iterator = list.iterator();
/*     */     
/* 110 */     while (iterator.hasNext()) {
/* 111 */       BlockPosition blockposition1 = iterator.next();
/* 112 */       IBlockData iblockdata1 = world.getType(blockposition1);
/*     */       
/* 114 */       if (iblockdata1 != null) {
/* 115 */         iblockdata1.doPhysics(world, blockposition1, iblockdata1.getBlock(), blockposition);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 122 */     super.onPlace(world, blockposition, iblockdata);
/* 123 */     e(world, blockposition, iblockdata);
/*     */   }
/*     */   
/*     */   public IBlockState<BlockMinecartTrackAbstract.EnumTrackPosition> g() {
/* 127 */     return SHAPE;
/*     */   }
/*     */   
/*     */   public boolean isComplexRedstone(IBlockData iblockdata) {
/* 131 */     return true;
/*     */   }
/*     */   
/*     */   public int c(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 135 */     if (((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue()) {
/* 136 */       List<EntityMinecartCommandBlock> list = a(world, blockposition, EntityMinecartCommandBlock.class, (Predicate<Entity>[])new Predicate[0]);
/*     */       
/* 138 */       if (!list.isEmpty()) {
/* 139 */         return ((EntityMinecartCommandBlock)list.get(0)).getCommandBlock().k();
/*     */       }
/*     */       
/* 142 */       List<EntityMinecartAbstract> list1 = a(world, blockposition, EntityMinecartAbstract.class, (Predicate<Entity>[])new Predicate[] { IEntitySelector.c });
/*     */       
/* 144 */       if (!list1.isEmpty()) {
/* 145 */         return Container.b((IInventory)list1.get(0));
/*     */       }
/*     */     } 
/*     */     
/* 149 */     return 0;
/*     */   }
/*     */   
/*     */   protected <T extends EntityMinecartAbstract> List<T> a(World world, BlockPosition blockposition, Class<T> oclass, Predicate... apredicate) {
/* 153 */     AxisAlignedBB axisalignedbb = a(blockposition);
/*     */     
/* 155 */     return (apredicate.length != 1) ? (List)world.<Entity>a((Class)oclass, axisalignedbb) : (List)world.<Entity>a((Class)oclass, axisalignedbb, apredicate[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private AxisAlignedBB a(BlockPosition blockposition) {
/* 161 */     return new AxisAlignedBB((blockposition.getX() + 0.2F), blockposition.getY(), (blockposition.getZ() + 0.2F), ((blockposition.getX() + 1) - 0.2F), ((blockposition.getY() + 1) - 0.2F), ((blockposition.getZ() + 1) - 0.2F));
/*     */   }
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/* 165 */     return getBlockData().<BlockMinecartTrackAbstract.EnumTrackPosition, BlockMinecartTrackAbstract.EnumTrackPosition>set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.a(i & 0x7)).set(POWERED, Boolean.valueOf(((i & 0x8) > 0)));
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 169 */     byte b0 = 0;
/* 170 */     int i = b0 | ((BlockMinecartTrackAbstract.EnumTrackPosition)iblockdata.<BlockMinecartTrackAbstract.EnumTrackPosition>get(SHAPE)).a();
/*     */     
/* 172 */     if (((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue()) {
/* 173 */       i |= 0x8;
/*     */     }
/*     */     
/* 176 */     return i;
/*     */   }
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 180 */     switch (enumblockrotation) {
/*     */       case null:
/* 182 */         switch ((BlockMinecartTrackAbstract.EnumTrackPosition)iblockdata.get((IBlockState)SHAPE)) {
/*     */           case null:
/* 184 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_WEST);
/*     */           
/*     */           case ASCENDING_WEST:
/* 187 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_EAST);
/*     */           
/*     */           case ASCENDING_NORTH:
/* 190 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_SOUTH);
/*     */           
/*     */           case ASCENDING_SOUTH:
/* 193 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_NORTH);
/*     */           
/*     */           case SOUTH_EAST:
/* 196 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_WEST);
/*     */           
/*     */           case SOUTH_WEST:
/* 199 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_EAST);
/*     */           
/*     */           case NORTH_WEST:
/* 202 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_EAST);
/*     */           
/*     */           case NORTH_EAST:
/* 205 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_WEST);
/*     */         } 
/*     */       
/*     */       case COUNTERCLOCKWISE_90:
/* 209 */         switch ((BlockMinecartTrackAbstract.EnumTrackPosition)iblockdata.get((IBlockState)SHAPE)) {
/*     */           case null:
/* 211 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_NORTH);
/*     */           
/*     */           case ASCENDING_WEST:
/* 214 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_SOUTH);
/*     */           
/*     */           case ASCENDING_NORTH:
/* 217 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_WEST);
/*     */           
/*     */           case ASCENDING_SOUTH:
/* 220 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_EAST);
/*     */           
/*     */           case SOUTH_EAST:
/* 223 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_EAST);
/*     */           
/*     */           case SOUTH_WEST:
/* 226 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_EAST);
/*     */           
/*     */           case NORTH_WEST:
/* 229 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_WEST);
/*     */           
/*     */           case NORTH_EAST:
/* 232 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_WEST);
/*     */           
/*     */           case NORTH_SOUTH:
/* 235 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.EAST_WEST);
/*     */           
/*     */           case EAST_WEST:
/* 238 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_SOUTH);
/*     */         } 
/*     */       
/*     */       case CLOCKWISE_90:
/* 242 */         switch ((BlockMinecartTrackAbstract.EnumTrackPosition)iblockdata.get((IBlockState)SHAPE)) {
/*     */           case null:
/* 244 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_SOUTH);
/*     */           
/*     */           case ASCENDING_WEST:
/* 247 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_NORTH);
/*     */           
/*     */           case ASCENDING_NORTH:
/* 250 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_EAST);
/*     */           
/*     */           case ASCENDING_SOUTH:
/* 253 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_WEST);
/*     */           
/*     */           case SOUTH_EAST:
/* 256 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_WEST);
/*     */           
/*     */           case SOUTH_WEST:
/* 259 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_WEST);
/*     */           
/*     */           case NORTH_WEST:
/* 262 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_EAST);
/*     */           
/*     */           case NORTH_EAST:
/* 265 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_EAST);
/*     */           
/*     */           case NORTH_SOUTH:
/* 268 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.EAST_WEST);
/*     */           
/*     */           case EAST_WEST:
/* 271 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_SOUTH);
/*     */         } 
/*     */         break;
/*     */     } 
/* 275 */     return iblockdata;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/* 280 */     BlockMinecartTrackAbstract.EnumTrackPosition blockminecarttrackabstract_enumtrackposition = iblockdata.<BlockMinecartTrackAbstract.EnumTrackPosition>get(SHAPE);
/*     */     
/* 282 */     switch (enumblockmirror) {
/*     */       case LEFT_RIGHT:
/* 284 */         switch (blockminecarttrackabstract_enumtrackposition) {
/*     */           case ASCENDING_NORTH:
/* 286 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_SOUTH);
/*     */           
/*     */           case ASCENDING_SOUTH:
/* 289 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_NORTH);
/*     */           
/*     */           case SOUTH_EAST:
/* 292 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_EAST);
/*     */           
/*     */           case SOUTH_WEST:
/* 295 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_WEST);
/*     */           
/*     */           case NORTH_WEST:
/* 298 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_WEST);
/*     */           
/*     */           case NORTH_EAST:
/* 301 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_EAST);
/*     */         } 
/*     */         
/* 304 */         return super.a(iblockdata, enumblockmirror);
/*     */ 
/*     */       
/*     */       case null:
/* 308 */         switch (blockminecarttrackabstract_enumtrackposition) {
/*     */           case null:
/* 310 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_WEST);
/*     */           
/*     */           case ASCENDING_WEST:
/* 313 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.ASCENDING_EAST);
/*     */ 
/*     */           
/*     */           default:
/*     */             break;
/*     */ 
/*     */           
/*     */           case SOUTH_EAST:
/* 321 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_WEST);
/*     */           
/*     */           case SOUTH_WEST:
/* 324 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.SOUTH_EAST);
/*     */           
/*     */           case NORTH_WEST:
/* 327 */             return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_EAST);
/*     */           case NORTH_EAST:
/*     */             break;
/* 330 */         }  return iblockdata.set(SHAPE, BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_WEST);
/*     */     } 
/*     */ 
/*     */     
/* 334 */     return super.a(iblockdata, enumblockmirror);
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 338 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { SHAPE, POWERED });
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockMinecartDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */