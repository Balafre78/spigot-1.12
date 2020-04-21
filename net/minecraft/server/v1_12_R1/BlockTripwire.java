/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.entity.EntityInteractEvent;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ 
/*     */ public class BlockTripwire extends Block {
/*  12 */   public static final BlockStateBoolean POWERED = BlockStateBoolean.of("powered");
/*  13 */   public static final BlockStateBoolean ATTACHED = BlockStateBoolean.of("attached");
/*  14 */   public static final BlockStateBoolean DISARMED = BlockStateBoolean.of("disarmed");
/*  15 */   public static final BlockStateBoolean NORTH = BlockStateBoolean.of("north");
/*  16 */   public static final BlockStateBoolean EAST = BlockStateBoolean.of("east");
/*  17 */   public static final BlockStateBoolean SOUTH = BlockStateBoolean.of("south");
/*  18 */   public static final BlockStateBoolean WEST = BlockStateBoolean.of("west");
/*  19 */   protected static final AxisAlignedBB B = new AxisAlignedBB(0.0D, 0.0625D, 0.0D, 1.0D, 0.15625D, 1.0D);
/*  20 */   protected static final AxisAlignedBB C = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
/*     */   
/*     */   public BlockTripwire() {
/*  23 */     super(Material.ORIENTABLE);
/*  24 */     w(this.blockStateList.getBlockData().<Comparable, Boolean>set(POWERED, Boolean.valueOf(false)).<Comparable, Boolean>set(ATTACHED, Boolean.valueOf(false)).<Comparable, Boolean>set(DISARMED, Boolean.valueOf(false)).<Comparable, Boolean>set(NORTH, Boolean.valueOf(false)).<Comparable, Boolean>set(EAST, Boolean.valueOf(false)).<Comparable, Boolean>set(SOUTH, Boolean.valueOf(false)).set(WEST, Boolean.valueOf(false)));
/*  25 */     a(true);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  29 */     return !((Boolean)iblockdata.<Boolean>get(ATTACHED)).booleanValue() ? C : B;
/*     */   }
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  33 */     return iblockdata.<Comparable, Boolean>set(NORTH, Boolean.valueOf(a(iblockaccess, blockposition, iblockdata, EnumDirection.NORTH))).<Comparable, Boolean>set(EAST, Boolean.valueOf(a(iblockaccess, blockposition, iblockdata, EnumDirection.EAST))).<Comparable, Boolean>set(SOUTH, Boolean.valueOf(a(iblockaccess, blockposition, iblockdata, EnumDirection.SOUTH))).set(WEST, Boolean.valueOf(a(iblockaccess, blockposition, iblockdata, EnumDirection.WEST)));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public AxisAlignedBB a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  38 */     return k;
/*     */   }
/*     */   
/*     */   public boolean b(IBlockData iblockdata) {
/*  42 */     return false;
/*     */   }
/*     */   
/*     */   public boolean c(IBlockData iblockdata) {
/*  46 */     return false;
/*     */   }
/*     */   
/*     */   public Item getDropType(IBlockData iblockdata, Random random, int i) {
/*  50 */     return Items.STRING;
/*     */   }
/*     */   
/*     */   public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  54 */     return new ItemStack(Items.STRING);
/*     */   }
/*     */   
/*     */   public void onPlace(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  58 */     world.setTypeAndData(blockposition, iblockdata, 3);
/*  59 */     e(world, blockposition, iblockdata);
/*     */   }
/*     */   
/*     */   public void remove(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  63 */     e(world, blockposition, iblockdata.set(POWERED, Boolean.valueOf(true)));
/*     */   }
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman) {
/*  67 */     if (!world.isClientSide && 
/*  68 */       !entityhuman.getItemInMainHand().isEmpty() && entityhuman.getItemInMainHand().getItem() == Items.SHEARS) {
/*  69 */       world.setTypeAndData(blockposition, iblockdata.set(DISARMED, Boolean.valueOf(true)), 4);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void e(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  76 */     EnumDirection[] aenumdirection = { EnumDirection.SOUTH, EnumDirection.WEST };
/*  77 */     int i = aenumdirection.length;
/*  78 */     int j = 0;
/*     */     
/*  80 */     while (j < i) {
/*  81 */       EnumDirection enumdirection = aenumdirection[j];
/*  82 */       int k = 1;
/*     */ 
/*     */       
/*  85 */       while (k < 42) {
/*  86 */         BlockPosition blockposition1 = blockposition.shift(enumdirection, k);
/*  87 */         IBlockData iblockdata1 = world.getType(blockposition1);
/*     */         
/*  89 */         if (iblockdata1.getBlock() == Blocks.TRIPWIRE_HOOK) {
/*  90 */           if (iblockdata1.get(BlockTripwireHook.FACING) == enumdirection.opposite())
/*  91 */             Blocks.TRIPWIRE_HOOK.a(world, blockposition1, iblockdata1, false, true, k, iblockdata);  break;
/*     */         } 
/*  93 */         if (iblockdata1.getBlock() == Blocks.TRIPWIRE) {
/*  94 */           k++;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*  99 */       j++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, Entity entity) {
/* 107 */     if (!world.isClientSide && 
/* 108 */       !((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue()) {
/* 109 */       b(world, blockposition);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {}
/*     */   
/*     */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/* 117 */     if (!world.isClientSide && (
/* 118 */       (Boolean)world.getType(blockposition).<Boolean>get(POWERED)).booleanValue()) {
/* 119 */       b(world, blockposition);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void b(World world, BlockPosition blockposition) {
/* 125 */     IBlockData iblockdata = world.getType(blockposition);
/* 126 */     boolean flag = ((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue();
/* 127 */     boolean flag1 = false;
/* 128 */     List<Entity> list = world.getEntities(null, iblockdata.e(world, blockposition).a(blockposition));
/*     */     
/* 130 */     if (!list.isEmpty()) {
/* 131 */       Iterator<Entity> iterator = list.iterator();
/*     */       
/* 133 */       while (iterator.hasNext()) {
/* 134 */         Entity entity = iterator.next();
/*     */         
/* 136 */         if (!entity.isIgnoreBlockTrigger()) {
/* 137 */           flag1 = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 144 */     if (flag != flag1 && flag1 && ((Boolean)iblockdata.<Boolean>get(ATTACHED)).booleanValue()) {
/* 145 */       CraftWorld craftWorld = world.getWorld();
/* 146 */       PluginManager manager = world.getServer().getPluginManager();
/* 147 */       Block block = craftWorld.getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 148 */       boolean allowed = false;
/*     */ 
/*     */       
/* 151 */       for (Entity object : list) {
/* 152 */         if (object != null) {
/*     */           EntityInteractEvent entityInteractEvent;
/*     */           
/* 155 */           if (object instanceof EntityHuman) {
/* 156 */             PlayerInteractEvent playerInteractEvent = CraftEventFactory.callPlayerInteractEvent((EntityHuman)object, Action.PHYSICAL, blockposition, null, null, null);
/* 157 */           } else if (object instanceof Entity) {
/* 158 */             entityInteractEvent = new EntityInteractEvent((Entity)((Entity)object).getBukkitEntity(), block);
/* 159 */             manager.callEvent((Event)entityInteractEvent);
/*     */           } else {
/*     */             continue;
/*     */           } 
/*     */           
/* 164 */           if (!entityInteractEvent.isCancelled()) {
/* 165 */             allowed = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 171 */       if (!allowed) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 177 */     if (flag1 != flag) {
/* 178 */       iblockdata = iblockdata.set(POWERED, Boolean.valueOf(flag1));
/* 179 */       world.setTypeAndData(blockposition, iblockdata, 3);
/* 180 */       e(world, blockposition, iblockdata);
/*     */     } 
/*     */     
/* 183 */     if (flag1) {
/* 184 */       world.a(new BlockPosition(blockposition), this, a(world));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, EnumDirection enumdirection) {
/* 190 */     BlockPosition blockposition1 = blockposition.shift(enumdirection);
/* 191 */     IBlockData iblockdata1 = iblockaccess.getType(blockposition1);
/* 192 */     Block block = iblockdata1.getBlock();
/*     */     
/* 194 */     if (block == Blocks.TRIPWIRE_HOOK) {
/* 195 */       EnumDirection enumdirection1 = enumdirection.opposite();
/*     */       
/* 197 */       return (iblockdata1.get(BlockTripwireHook.FACING) == enumdirection1);
/*     */     } 
/* 199 */     return (block == Blocks.TRIPWIRE);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/* 204 */     return getBlockData().<Comparable, Boolean>set(POWERED, Boolean.valueOf(((i & 0x1) > 0))).<Comparable, Boolean>set(ATTACHED, Boolean.valueOf(((i & 0x4) > 0))).set(DISARMED, Boolean.valueOf(((i & 0x8) > 0)));
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 208 */     int i = 0;
/*     */     
/* 210 */     if (((Boolean)iblockdata.<Boolean>get(POWERED)).booleanValue()) {
/* 211 */       i |= 0x1;
/*     */     }
/*     */     
/* 214 */     if (((Boolean)iblockdata.<Boolean>get(ATTACHED)).booleanValue()) {
/* 215 */       i |= 0x4;
/*     */     }
/*     */     
/* 218 */     if (((Boolean)iblockdata.<Boolean>get(DISARMED)).booleanValue()) {
/* 219 */       i |= 0x8;
/*     */     }
/*     */     
/* 222 */     return i;
/*     */   }
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 226 */     switch (enumblockrotation) {
/*     */       case null:
/* 228 */         return iblockdata.<Comparable, Boolean>set(NORTH, iblockdata.<Boolean>get(SOUTH)).<Comparable, Boolean>set(EAST, iblockdata.<Boolean>get(WEST)).<Comparable, Boolean>set(SOUTH, iblockdata.<Boolean>get(NORTH)).set(WEST, iblockdata.<Boolean>get(EAST));
/*     */       
/*     */       case COUNTERCLOCKWISE_90:
/* 231 */         return iblockdata.<Comparable, Boolean>set(NORTH, iblockdata.<Boolean>get(EAST)).<Comparable, Boolean>set(EAST, iblockdata.<Boolean>get(SOUTH)).<Comparable, Boolean>set(SOUTH, iblockdata.<Boolean>get(WEST)).set(WEST, iblockdata.<Boolean>get(NORTH));
/*     */       
/*     */       case CLOCKWISE_90:
/* 234 */         return iblockdata.<Comparable, Boolean>set(NORTH, iblockdata.<Boolean>get(WEST)).<Comparable, Boolean>set(EAST, iblockdata.<Boolean>get(NORTH)).<Comparable, Boolean>set(SOUTH, iblockdata.<Boolean>get(EAST)).set(WEST, iblockdata.<Boolean>get(SOUTH));
/*     */     } 
/*     */     
/* 237 */     return iblockdata;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/* 242 */     switch (enumblockmirror) {
/*     */       case LEFT_RIGHT:
/* 244 */         return iblockdata.<Comparable, Boolean>set(NORTH, iblockdata.<Boolean>get(SOUTH)).set(SOUTH, iblockdata.<Boolean>get(NORTH));
/*     */       
/*     */       case null:
/* 247 */         return iblockdata.<Comparable, Boolean>set(EAST, iblockdata.<Boolean>get(WEST)).set(WEST, iblockdata.<Boolean>get(EAST));
/*     */     } 
/*     */     
/* 250 */     return super.a(iblockdata, enumblockmirror);
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 255 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { POWERED, ATTACHED, DISARMED, NORTH, EAST, WEST, SOUTH });
/*     */   }
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
/* 259 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockTripwire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */