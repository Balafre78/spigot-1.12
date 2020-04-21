/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockRedstoneEvent;
/*     */ 
/*     */ public class BlockLever extends Block {
/*  10 */   public static final BlockStateEnum<EnumLeverPosition> FACING = BlockStateEnum.of("facing", EnumLeverPosition.class);
/*  11 */   public static final BlockStateBoolean POWERED = BlockStateBoolean.of("powered");
/*  12 */   protected static final AxisAlignedBB c = new AxisAlignedBB(0.3125D, 0.20000000298023224D, 0.625D, 0.6875D, 0.800000011920929D, 1.0D);
/*  13 */   protected static final AxisAlignedBB d = new AxisAlignedBB(0.3125D, 0.20000000298023224D, 0.0D, 0.6875D, 0.800000011920929D, 0.375D);
/*  14 */   protected static final AxisAlignedBB e = new AxisAlignedBB(0.625D, 0.20000000298023224D, 0.3125D, 1.0D, 0.800000011920929D, 0.6875D);
/*  15 */   protected static final AxisAlignedBB f = new AxisAlignedBB(0.0D, 0.20000000298023224D, 0.3125D, 0.375D, 0.800000011920929D, 0.6875D);
/*  16 */   protected static final AxisAlignedBB g = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.6000000238418579D, 0.75D);
/*  17 */   protected static final AxisAlignedBB B = new AxisAlignedBB(0.25D, 0.4000000059604645D, 0.25D, 0.75D, 1.0D, 0.75D);
/*     */   
/*     */   protected BlockLever() {
/*  20 */     super(Material.ORIENTABLE);
/*  21 */     w(this.blockStateList.getBlockData().<EnumLeverPosition, EnumLeverPosition>set(FACING, EnumLeverPosition.NORTH).set(POWERED, Boolean.valueOf(false)));
/*  22 */     a(CreativeModeTab.d);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public AxisAlignedBB a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  27 */     return k;
/*     */   }
/*     */   
/*     */   public boolean b(IBlockData iblockdata) {
/*  31 */     return false;
/*     */   }
/*     */   
/*     */   public boolean c(IBlockData iblockdata) {
/*  35 */     return false;
/*     */   }
/*     */   
/*     */   public boolean canPlace(World world, BlockPosition blockposition, EnumDirection enumdirection) {
/*  39 */     return a(world, blockposition, enumdirection);
/*     */   }
/*     */   
/*     */   public boolean canPlace(World world, BlockPosition blockposition) {
/*  43 */     EnumDirection[] aenumdirection = EnumDirection.values();
/*  44 */     int i = aenumdirection.length;
/*     */     
/*  46 */     for (int j = 0; j < i; j++) {
/*  47 */       EnumDirection enumdirection = aenumdirection[j];
/*     */       
/*  49 */       if (a(world, blockposition, enumdirection)) {
/*  50 */         return true;
/*     */       }
/*     */     } 
/*     */     
/*  54 */     return false;
/*     */   }
/*     */   
/*     */   protected static boolean a(World world, BlockPosition blockposition, EnumDirection enumdirection) {
/*  58 */     return BlockButtonAbstract.a(world, blockposition, enumdirection);
/*     */   }
/*     */   public IBlockData getPlacedState(World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2, int i, EntityLiving entityliving) {
/*     */     EnumDirection enumdirection1;
/*  62 */     IBlockData iblockdata = getBlockData().set(POWERED, Boolean.valueOf(false));
/*     */     
/*  64 */     if (a(world, blockposition, enumdirection)) {
/*  65 */       return iblockdata.set(FACING, EnumLeverPosition.a(enumdirection, entityliving.getDirection()));
/*     */     }
/*  67 */     Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/*  72 */       if (!iterator.hasNext()) {
/*  73 */         if (world.getType(blockposition.down()).q()) {
/*  74 */           return iblockdata.set(FACING, EnumLeverPosition.a(EnumDirection.UP, entityliving.getDirection()));
/*     */         }
/*     */         
/*  77 */         return iblockdata;
/*     */       } 
/*     */       
/*  80 */       enumdirection1 = iterator.next();
/*  81 */     } while (enumdirection1 == enumdirection || !a(world, blockposition, enumdirection1));
/*     */     
/*  83 */     return iblockdata.set(FACING, EnumLeverPosition.a(enumdirection1, entityliving.getDirection()));
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
/*  88 */     if (e(world, blockposition, iblockdata) && !a(world, blockposition, ((EnumLeverPosition)iblockdata.<EnumLeverPosition>get(FACING)).c())) {
/*  89 */       b(world, blockposition, iblockdata, 0);
/*  90 */       world.setAir(blockposition);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean e(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  96 */     if (canPlace(world, blockposition)) {
/*  97 */       return true;
/*     */     }
/*  99 */     b(world, blockposition, iblockdata, 0);
/* 100 */     world.setAir(blockposition);
/* 101 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 106 */     switch ((EnumLeverPosition)iblockdata.get((IBlockState)FACING)) {
/*     */       
/*     */       default:
/* 109 */         return f;
/*     */       
/*     */       case WEST:
/* 112 */         return e;
/*     */       
/*     */       case SOUTH:
/* 115 */         return d;
/*     */       
/*     */       case NORTH:
/* 118 */         return c;
/*     */       
/*     */       case UP_Z:
/*     */       case UP_X:
/* 122 */         return g;
/*     */       case null:
/*     */       case DOWN_Z:
/*     */         break;
/* 126 */     }  return B;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean interact(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
/* 131 */     if (world.isClientSide) {
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
/*     */     
/* 148 */     iblockdata = iblockdata.a(POWERED);
/* 149 */     world.setTypeAndData(blockposition, iblockdata, 3);
/* 150 */     float f3 = ((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue() ? 0.6F : 0.5F;
/*     */     
/* 152 */     world.a(null, blockposition, SoundEffects.dI, SoundCategory.BLOCKS, 0.3F, f3);
/* 153 */     world.applyPhysics(blockposition, this, false);
/* 154 */     EnumDirection enumdirection1 = ((EnumLeverPosition)iblockdata.<EnumLeverPosition>get(FACING)).c();
/*     */     
/* 156 */     world.applyPhysics(blockposition.shift(enumdirection1.opposite()), this, false);
/* 157 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 162 */     if (((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue()) {
/* 163 */       world.applyPhysics(blockposition, this, false);
/* 164 */       EnumDirection enumdirection = ((EnumLeverPosition)iblockdata.<EnumLeverPosition>get(FACING)).c();
/*     */       
/* 166 */       world.applyPhysics(blockposition.shift(enumdirection.opposite()), this, false);
/*     */     } 
/*     */     
/* 169 */     super.remove(world, blockposition, iblockdata);
/*     */   }
/*     */   
/*     */   public int b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 173 */     return ((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue() ? 15 : 0;
/*     */   }
/*     */   
/*     */   public int c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 177 */     return !((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue() ? 0 : ((((EnumLeverPosition)iblockdata.<EnumLeverPosition>get(FACING)).c() == enumdirection) ? 15 : 0);
/*     */   }
/*     */   
/*     */   public boolean isPowerSource(IBlockData iblockdata) {
/* 181 */     return true;
/*     */   }
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/* 185 */     return getBlockData().<EnumLeverPosition, EnumLeverPosition>set(FACING, EnumLeverPosition.a(i & 0x7)).set(POWERED, Boolean.valueOf(((i & 0x8) > 0)));
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 189 */     byte b0 = 0;
/* 190 */     int i = b0 | ((EnumLeverPosition)iblockdata.<EnumLeverPosition>get(FACING)).a();
/*     */     
/* 192 */     if (((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue()) {
/* 193 */       i |= 0x8;
/*     */     }
/*     */     
/* 196 */     return i;
/*     */   }
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 200 */     switch (enumblockrotation) {
/*     */       case null:
/* 202 */         switch ((EnumLeverPosition)iblockdata.get((IBlockState)FACING)) {
/*     */           case EAST:
/* 204 */             return iblockdata.set(FACING, EnumLeverPosition.WEST);
/*     */           
/*     */           case WEST:
/* 207 */             return iblockdata.set(FACING, EnumLeverPosition.EAST);
/*     */           
/*     */           case SOUTH:
/* 210 */             return iblockdata.set(FACING, EnumLeverPosition.NORTH);
/*     */           
/*     */           case NORTH:
/* 213 */             return iblockdata.set(FACING, EnumLeverPosition.SOUTH);
/*     */         } 
/*     */         
/* 216 */         return iblockdata;
/*     */ 
/*     */       
/*     */       case COUNTERCLOCKWISE_90:
/* 220 */         switch ((EnumLeverPosition)iblockdata.get((IBlockState)FACING)) {
/*     */           case EAST:
/* 222 */             return iblockdata.set(FACING, EnumLeverPosition.NORTH);
/*     */           
/*     */           case WEST:
/* 225 */             return iblockdata.set(FACING, EnumLeverPosition.SOUTH);
/*     */           
/*     */           case SOUTH:
/* 228 */             return iblockdata.set(FACING, EnumLeverPosition.EAST);
/*     */           
/*     */           case NORTH:
/* 231 */             return iblockdata.set(FACING, EnumLeverPosition.WEST);
/*     */           
/*     */           case UP_Z:
/* 234 */             return iblockdata.set(FACING, EnumLeverPosition.UP_X);
/*     */           
/*     */           case UP_X:
/* 237 */             return iblockdata.set(FACING, EnumLeverPosition.UP_Z);
/*     */           
/*     */           case null:
/* 240 */             return iblockdata.set(FACING, EnumLeverPosition.DOWN_Z);
/*     */           
/*     */           case DOWN_Z:
/* 243 */             return iblockdata.set(FACING, EnumLeverPosition.DOWN_X);
/*     */         } 
/*     */       
/*     */       case CLOCKWISE_90:
/* 247 */         switch ((EnumLeverPosition)iblockdata.get((IBlockState)FACING)) {
/*     */           case EAST:
/* 249 */             return iblockdata.set(FACING, EnumLeverPosition.SOUTH);
/*     */           
/*     */           case WEST:
/* 252 */             return iblockdata.set(FACING, EnumLeverPosition.NORTH);
/*     */           
/*     */           case SOUTH:
/* 255 */             return iblockdata.set(FACING, EnumLeverPosition.WEST);
/*     */           
/*     */           case NORTH:
/* 258 */             return iblockdata.set(FACING, EnumLeverPosition.EAST);
/*     */           
/*     */           case UP_Z:
/* 261 */             return iblockdata.set(FACING, EnumLeverPosition.UP_X);
/*     */           
/*     */           case UP_X:
/* 264 */             return iblockdata.set(FACING, EnumLeverPosition.UP_Z);
/*     */           
/*     */           case null:
/* 267 */             return iblockdata.set(FACING, EnumLeverPosition.DOWN_Z);
/*     */           
/*     */           case DOWN_Z:
/* 270 */             return iblockdata.set(FACING, EnumLeverPosition.DOWN_X);
/*     */         } 
/*     */         break;
/*     */     } 
/* 274 */     return iblockdata;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/* 279 */     return iblockdata.a(enumblockmirror.a(((EnumLeverPosition)iblockdata.<EnumLeverPosition>get(FACING)).c()));
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 283 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { FACING, POWERED });
/*     */   }
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
/* 287 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */   
/*     */   public enum EnumLeverPosition
/*     */     implements INamable {
/* 292 */     DOWN_X(0, "down_x", EnumDirection.DOWN), EAST(1, "east", EnumDirection.EAST), WEST(2, "west", EnumDirection.WEST), SOUTH(3, "south", EnumDirection.SOUTH), NORTH(4, "north", EnumDirection.NORTH), UP_Z(5, "up_z", EnumDirection.UP), UP_X(6, "up_x", EnumDirection.UP), DOWN_Z(7, "down_z", EnumDirection.DOWN);
/*     */     
/* 294 */     private static final EnumLeverPosition[] i = new EnumLeverPosition[(values()).length];
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
/*     */     private final int j;
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
/*     */     private final String k;
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
/*     */     private final EnumDirection l;
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
/*     */     static {
/* 373 */       EnumLeverPosition[] ablocklever_enumleverposition = values();
/* 374 */       int i = ablocklever_enumleverposition.length;
/*     */       
/* 376 */       for (int j = 0; j < i; j++) {
/* 377 */         EnumLeverPosition blocklever_enumleverposition = ablocklever_enumleverposition[j];
/*     */         
/* 379 */         EnumLeverPosition.i[blocklever_enumleverposition.a()] = blocklever_enumleverposition;
/*     */       } 
/*     */     }
/*     */     
/*     */     EnumLeverPosition(int i, String s, EnumDirection enumdirection) {
/*     */       this.j = i;
/*     */       this.k = s;
/*     */       this.l = enumdirection;
/*     */     }
/*     */     
/*     */     public int a() {
/*     */       return this.j;
/*     */     }
/*     */     
/*     */     public EnumDirection c() {
/*     */       return this.l;
/*     */     }
/*     */     
/*     */     public String toString() {
/*     */       return this.k;
/*     */     }
/*     */     
/*     */     public static EnumLeverPosition a(int i) {
/*     */       if (i < 0 || i >= EnumLeverPosition.i.length)
/*     */         i = 0; 
/*     */       return EnumLeverPosition.i[i];
/*     */     }
/*     */     
/*     */     public static EnumLeverPosition a(EnumDirection enumdirection, EnumDirection enumdirection1) {
/*     */       switch (enumdirection) {
/*     */         case null:
/*     */           switch (enumdirection1.k()) {
/*     */             case null:
/*     */               return DOWN_X;
/*     */             case Z:
/*     */               return DOWN_Z;
/*     */           } 
/*     */           throw new IllegalArgumentException("Invalid entityFacing " + enumdirection1 + " for facing " + enumdirection);
/*     */         case UP:
/*     */           switch (enumdirection1.k()) {
/*     */             case null:
/*     */               return UP_X;
/*     */             case Z:
/*     */               return UP_Z;
/*     */           } 
/*     */           throw new IllegalArgumentException("Invalid entityFacing " + enumdirection1 + " for facing " + enumdirection);
/*     */         case NORTH:
/*     */           return NORTH;
/*     */         case SOUTH:
/*     */           return SOUTH;
/*     */         case WEST:
/*     */           return WEST;
/*     */         case EAST:
/*     */           return EAST;
/*     */       } 
/*     */       throw new IllegalArgumentException("Invalid facing: " + enumdirection);
/*     */     }
/*     */     
/*     */     public String getName() {
/*     */       return this.k;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockLever.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */