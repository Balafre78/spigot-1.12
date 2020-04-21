/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class BlockDispenser
/*     */   extends BlockTileEntity {
/*   7 */   public static final BlockStateDirection FACING = BlockDirectional.FACING;
/*   8 */   public static final BlockStateBoolean TRIGGERED = BlockStateBoolean.of("triggered");
/*   9 */   public static final RegistryDefault<Item, IDispenseBehavior> REGISTRY = new RegistryDefault<>(new DispenseBehaviorItem());
/*  10 */   protected Random d = new Random();
/*     */   public static boolean eventFired = false;
/*     */   
/*     */   protected BlockDispenser() {
/*  14 */     super(Material.STONE);
/*  15 */     w(this.blockStateList.getBlockData().<Comparable, EnumDirection>set(FACING, EnumDirection.NORTH).set(TRIGGERED, Boolean.valueOf(false)));
/*  16 */     a(CreativeModeTab.d);
/*     */   }
/*     */   
/*     */   public int a(World world) {
/*  20 */     return 4;
/*     */   }
/*     */   
/*     */   public void onPlace(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  24 */     super.onPlace(world, blockposition, iblockdata);
/*  25 */     e(world, blockposition, iblockdata);
/*     */   }
/*     */   
/*     */   private void e(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  29 */     if (!world.isClientSide) {
/*  30 */       EnumDirection enumdirection = iblockdata.<EnumDirection>get(FACING);
/*  31 */       boolean flag = world.getType(blockposition.north()).b();
/*  32 */       boolean flag1 = world.getType(blockposition.south()).b();
/*     */       
/*  34 */       if (enumdirection == EnumDirection.NORTH && flag && !flag1) {
/*  35 */         enumdirection = EnumDirection.SOUTH;
/*  36 */       } else if (enumdirection == EnumDirection.SOUTH && flag1 && !flag) {
/*  37 */         enumdirection = EnumDirection.NORTH;
/*     */       } else {
/*  39 */         boolean flag2 = world.getType(blockposition.west()).b();
/*  40 */         boolean flag3 = world.getType(blockposition.east()).b();
/*     */         
/*  42 */         if (enumdirection == EnumDirection.WEST && flag2 && !flag3) {
/*  43 */           enumdirection = EnumDirection.EAST;
/*  44 */         } else if (enumdirection == EnumDirection.EAST && flag3 && !flag2) {
/*  45 */           enumdirection = EnumDirection.WEST;
/*     */         } 
/*     */       } 
/*     */       
/*  49 */       world.setTypeAndData(blockposition, iblockdata.<Comparable, EnumDirection>set(FACING, enumdirection).set(TRIGGERED, Boolean.valueOf(false)), 2);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean interact(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
/*  54 */     if (world.isClientSide) {
/*  55 */       return true;
/*     */     }
/*  57 */     TileEntity tileentity = world.getTileEntity(blockposition);
/*     */     
/*  59 */     if (tileentity instanceof TileEntityDispenser) {
/*  60 */       entityhuman.openContainer((TileEntityDispenser)tileentity);
/*  61 */       if (tileentity instanceof TileEntityDropper) {
/*  62 */         entityhuman.b(StatisticList.O);
/*     */       } else {
/*  64 */         entityhuman.b(StatisticList.Q);
/*     */       } 
/*     */     } 
/*     */     
/*  68 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void dispense(World world, BlockPosition blockposition) {
/*  73 */     SourceBlock sourceblock = new SourceBlock(world, blockposition);
/*  74 */     TileEntityDispenser tileentitydispenser = sourceblock.<TileEntityDispenser>getTileEntity();
/*     */     
/*  76 */     if (tileentitydispenser != null) {
/*  77 */       int i = tileentitydispenser.o();
/*     */       
/*  79 */       if (i < 0) {
/*  80 */         world.triggerEffect(1001, blockposition, 0);
/*     */       } else {
/*  82 */         ItemStack itemstack = tileentitydispenser.getItem(i);
/*  83 */         IDispenseBehavior idispensebehavior = a(itemstack);
/*     */         
/*  85 */         if (idispensebehavior != IDispenseBehavior.NONE) {
/*  86 */           eventFired = false;
/*  87 */           tileentitydispenser.setItem(i, idispensebehavior.a(sourceblock, itemstack));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected IDispenseBehavior a(ItemStack itemstack) {
/*  95 */     return REGISTRY.get(itemstack.getItem());
/*     */   }
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
/*  99 */     boolean flag = !(!world.isBlockIndirectlyPowered(blockposition) && !world.isBlockIndirectlyPowered(blockposition.up()));
/* 100 */     boolean flag1 = ((Boolean)iblockdata.<Boolean>get(TRIGGERED)).booleanValue();
/*     */     
/* 102 */     if (flag && !flag1) {
/* 103 */       world.a(blockposition, this, a(world));
/* 104 */       world.setTypeAndData(blockposition, iblockdata.set(TRIGGERED, Boolean.valueOf(true)), 4);
/* 105 */     } else if (!flag && flag1) {
/* 106 */       world.setTypeAndData(blockposition, iblockdata.set(TRIGGERED, Boolean.valueOf(false)), 4);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/* 112 */     if (!world.isClientSide) {
/* 113 */       dispense(world, blockposition);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity a(World world, int i) {
/* 119 */     return new TileEntityDispenser();
/*     */   }
/*     */   
/*     */   public IBlockData getPlacedState(World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2, int i, EntityLiving entityliving) {
/* 123 */     return getBlockData().<Comparable, EnumDirection>set(FACING, EnumDirection.a(blockposition, entityliving)).set(TRIGGERED, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, EntityLiving entityliving, ItemStack itemstack) {
/* 127 */     world.setTypeAndData(blockposition, iblockdata.set(FACING, EnumDirection.a(blockposition, entityliving)), 2);
/* 128 */     if (itemstack.hasName()) {
/* 129 */       TileEntity tileentity = world.getTileEntity(blockposition);
/*     */       
/* 131 */       if (tileentity instanceof TileEntityDispenser) {
/* 132 */         ((TileEntityDispenser)tileentity).setCustomName(itemstack.getName());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 139 */     TileEntity tileentity = world.getTileEntity(blockposition);
/*     */     
/* 141 */     if (tileentity instanceof TileEntityDispenser) {
/* 142 */       InventoryUtils.dropInventory(world, blockposition, (TileEntityDispenser)tileentity);
/* 143 */       world.updateAdjacentComparators(blockposition, this);
/*     */     } 
/*     */     
/* 146 */     super.remove(world, blockposition, iblockdata);
/*     */   }
/*     */   
/*     */   public static IPosition a(ISourceBlock isourceblock) {
/* 150 */     EnumDirection enumdirection = isourceblock.e().<EnumDirection>get(FACING);
/* 151 */     double d0 = isourceblock.getX() + 0.7D * enumdirection.getAdjacentX();
/* 152 */     double d1 = isourceblock.getY() + 0.7D * enumdirection.getAdjacentY();
/* 153 */     double d2 = isourceblock.getZ() + 0.7D * enumdirection.getAdjacentZ();
/*     */     
/* 155 */     return new Position(d0, d1, d2);
/*     */   }
/*     */   
/*     */   public boolean isComplexRedstone(IBlockData iblockdata) {
/* 159 */     return true;
/*     */   }
/*     */   
/*     */   public int c(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 163 */     return Container.a(world.getTileEntity(blockposition));
/*     */   }
/*     */   
/*     */   public EnumRenderType a(IBlockData iblockdata) {
/* 167 */     return EnumRenderType.MODEL;
/*     */   }
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/* 171 */     return getBlockData().<Comparable, EnumDirection>set(FACING, EnumDirection.fromType1(i & 0x7)).set(TRIGGERED, Boolean.valueOf(((i & 0x8) > 0)));
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 175 */     byte b0 = 0;
/* 176 */     int i = b0 | ((EnumDirection)iblockdata.<EnumDirection>get(FACING)).a();
/*     */     
/* 178 */     if (((Boolean)iblockdata.<Boolean>get(TRIGGERED)).booleanValue()) {
/* 179 */       i |= 0x8;
/*     */     }
/*     */     
/* 182 */     return i;
/*     */   }
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 186 */     return iblockdata.set(FACING, enumblockrotation.a(iblockdata.<EnumDirection>get(FACING)));
/*     */   }
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/* 190 */     return iblockdata.a(enumblockmirror.a(iblockdata.<EnumDirection>get(FACING)));
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 194 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { FACING, TRIGGERED });
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockDispenser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */