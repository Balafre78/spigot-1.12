/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ public class BlockShulkerBox
/*     */   extends BlockTileEntity {
/*   5 */   public static final BlockStateEnum<EnumDirection> a = BlockStateDirection.of("facing");
/*     */   public final EnumColor color;
/*     */   
/*     */   public BlockShulkerBox(EnumColor enumcolor) {
/*   9 */     super(Material.STONE, MaterialMapColor.c);
/*  10 */     this.color = enumcolor;
/*  11 */     a(CreativeModeTab.c);
/*  12 */     w(this.blockStateList.getBlockData().set(a, EnumDirection.UP));
/*     */   }
/*     */   
/*     */   public TileEntity a(World world, int i) {
/*  16 */     return new TileEntityShulkerBox(this.color);
/*     */   }
/*     */   
/*     */   public boolean b(IBlockData iblockdata) {
/*  20 */     return false;
/*     */   }
/*     */   
/*     */   public boolean t(IBlockData iblockdata) {
/*  24 */     return true;
/*     */   }
/*     */   
/*     */   public boolean c(IBlockData iblockdata) {
/*  28 */     return false;
/*     */   }
/*     */   
/*     */   public EnumRenderType a(IBlockData iblockdata) {
/*  32 */     return EnumRenderType.ENTITYBLOCK_ANIMATED;
/*     */   }
/*     */   
/*     */   public boolean interact(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
/*  36 */     if (world.isClientSide)
/*  37 */       return true; 
/*  38 */     if (entityhuman.isSpectator()) {
/*  39 */       return true;
/*     */     }
/*  41 */     TileEntity tileentity = world.getTileEntity(blockposition);
/*     */     
/*  43 */     if (tileentity instanceof TileEntityShulkerBox) {
/*  44 */       boolean flag; EnumDirection enumdirection1 = iblockdata.<EnumDirection>get(a);
/*     */ 
/*     */       
/*  47 */       if (((TileEntityShulkerBox)tileentity).p() == TileEntityShulkerBox.AnimationPhase.CLOSED) {
/*  48 */         AxisAlignedBB axisalignedbb = j.b((0.5F * enumdirection1.getAdjacentX()), (0.5F * enumdirection1.getAdjacentY()), (0.5F * enumdirection1.getAdjacentZ())).a(enumdirection1.getAdjacentX(), enumdirection1.getAdjacentY(), enumdirection1.getAdjacentZ());
/*     */         
/*  50 */         flag = !world.a(axisalignedbb.a(blockposition.shift(enumdirection1)));
/*     */       } else {
/*  52 */         flag = true;
/*     */       } 
/*     */       
/*  55 */       if (flag) {
/*  56 */         entityhuman.b(StatisticList.ac);
/*  57 */         entityhuman.openContainer((IInventory)tileentity);
/*     */       } 
/*     */       
/*  60 */       return true;
/*     */     } 
/*  62 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2, int i, EntityLiving entityliving) {
/*  68 */     return getBlockData().set(a, enumdirection);
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/*  72 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { a });
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/*  76 */     return ((EnumDirection)iblockdata.<EnumDirection>get(a)).a();
/*     */   }
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/*  80 */     EnumDirection enumdirection = EnumDirection.fromType1(i);
/*     */     
/*  82 */     return getBlockData().set(a, enumdirection);
/*     */   }
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman) {
/*  86 */     if (world.getTileEntity(blockposition) instanceof TileEntityShulkerBox) {
/*  87 */       TileEntityShulkerBox tileentityshulkerbox = (TileEntityShulkerBox)world.getTileEntity(blockposition);
/*     */       
/*  89 */       tileentityshulkerbox.a(entityhuman.abilities.canInstantlyBuild);
/*  90 */       tileentityshulkerbox.d(entityhuman);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dropNaturally(World world, BlockPosition blockposition, IBlockData iblockdata, float f, int i) {
/*  97 */     TileEntity tileentity = world.getTileEntity(blockposition);
/*     */     
/*  99 */     if (tileentity instanceof TileEntityShulkerBox) {
/* 100 */       TileEntityShulkerBox tileentityshulkerbox = (TileEntityShulkerBox)tileentity;
/*     */       
/* 102 */       if (!tileentityshulkerbox.r() && tileentityshulkerbox.F()) {
/* 103 */         ItemStack itemstack = new ItemStack(Item.getItemOf(this));
/* 104 */         NBTTagCompound nbttagcompound = new NBTTagCompound();
/* 105 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*     */         
/* 107 */         nbttagcompound.set("BlockEntityTag", ((TileEntityShulkerBox)tileentity).f(nbttagcompound1));
/* 108 */         itemstack.setTag(nbttagcompound);
/* 109 */         if (tileentityshulkerbox.hasCustomName()) {
/* 110 */           itemstack.g(tileentityshulkerbox.getName());
/* 111 */           tileentityshulkerbox.setCustomName("");
/*     */         } 
/*     */         
/* 114 */         a(world, blockposition, itemstack);
/*     */       } 
/*     */       
/* 117 */       world.updateAdjacentComparators(blockposition, iblockdata.getBlock());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, EntityLiving entityliving, ItemStack itemstack) {
/* 123 */     if (itemstack.hasName()) {
/* 124 */       TileEntity tileentity = world.getTileEntity(blockposition);
/*     */       
/* 126 */       if (tileentity instanceof TileEntityShulkerBox) {
/* 127 */         ((TileEntityShulkerBox)tileentity).setCustomName(itemstack.getName());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 134 */     TileEntity tileentity = world.getTileEntity(blockposition);
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
/*     */     
/* 155 */     world.updateAdjacentComparators(blockposition, iblockdata.getBlock());
/*     */     
/* 157 */     super.remove(world, blockposition, iblockdata);
/*     */   }
/*     */   
/*     */   public EnumPistonReaction h(IBlockData iblockdata) {
/* 161 */     return EnumPistonReaction.DESTROY;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 165 */     TileEntity tileentity = iblockaccess.getTileEntity(blockposition);
/*     */     
/* 167 */     return (tileentity instanceof TileEntityShulkerBox) ? ((TileEntityShulkerBox)tileentity).a(iblockdata) : j;
/*     */   }
/*     */   
/*     */   public boolean isComplexRedstone(IBlockData iblockdata) {
/* 171 */     return true;
/*     */   }
/*     */   
/*     */   public int c(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 175 */     return Container.b((IInventory)world.getTileEntity(blockposition));
/*     */   }
/*     */   
/*     */   public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 179 */     ItemStack itemstack = super.a(world, blockposition, iblockdata);
/* 180 */     TileEntityShulkerBox tileentityshulkerbox = (TileEntityShulkerBox)world.getTileEntity(blockposition);
/* 181 */     NBTTagCompound nbttagcompound = tileentityshulkerbox.f(new NBTTagCompound());
/*     */     
/* 183 */     if (!nbttagcompound.isEmpty()) {
/* 184 */       itemstack.a("BlockEntityTag", nbttagcompound);
/*     */     }
/*     */     
/* 187 */     return itemstack;
/*     */   }
/*     */   
/*     */   public static Block a(EnumColor enumcolor) {
/* 191 */     switch (enumcolor) {
/*     */       case WHITE:
/* 193 */         return Blocks.WHITE_SHULKER_BOX;
/*     */       
/*     */       case ORANGE:
/* 196 */         return Blocks.dm;
/*     */       
/*     */       case MAGENTA:
/* 199 */         return Blocks.dn;
/*     */       
/*     */       case LIGHT_BLUE:
/* 202 */         return Blocks.LIGHT_BLUE_SHULKER_BOX;
/*     */       
/*     */       case YELLOW:
/* 205 */         return Blocks.dp;
/*     */       
/*     */       case LIME:
/* 208 */         return Blocks.dq;
/*     */       
/*     */       case PINK:
/* 211 */         return Blocks.dr;
/*     */       
/*     */       case GRAY:
/* 214 */         return Blocks.ds;
/*     */       
/*     */       case SILVER:
/* 217 */         return Blocks.dt;
/*     */       
/*     */       case CYAN:
/* 220 */         return Blocks.du;
/*     */ 
/*     */       
/*     */       default:
/* 224 */         return Blocks.dv;
/*     */       
/*     */       case BLUE:
/* 227 */         return Blocks.dw;
/*     */       
/*     */       case BROWN:
/* 230 */         return Blocks.dx;
/*     */       
/*     */       case GREEN:
/* 233 */         return Blocks.dy;
/*     */       
/*     */       case RED:
/* 236 */         return Blocks.dz;
/*     */       case null:
/*     */         break;
/* 239 */     }  return Blocks.dA;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemStack b(EnumColor enumcolor) {
/* 244 */     return new ItemStack(a(enumcolor));
/*     */   }
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 248 */     return iblockdata.set(a, enumblockrotation.a(iblockdata.<EnumDirection>get(a)));
/*     */   }
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/* 252 */     return iblockdata.a(enumblockmirror.a(iblockdata.<EnumDirection>get(a)));
/*     */   }
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
/* 256 */     iblockdata = updateState(iblockdata, iblockaccess, blockposition);
/* 257 */     EnumDirection enumdirection1 = iblockdata.<EnumDirection>get(a);
/* 258 */     TileEntityShulkerBox.AnimationPhase tileentityshulkerbox_animationphase = ((TileEntityShulkerBox)iblockaccess.getTileEntity(blockposition)).p();
/*     */     
/* 260 */     return (tileentityshulkerbox_animationphase != TileEntityShulkerBox.AnimationPhase.CLOSED && (tileentityshulkerbox_animationphase != TileEntityShulkerBox.AnimationPhase.OPENED || (enumdirection1 != enumdirection.opposite() && enumdirection1 != enumdirection))) ? EnumBlockFaceShape.UNDEFINED : EnumBlockFaceShape.SOLID;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockShulkerBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */