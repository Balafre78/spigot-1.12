/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ public class BlockJukeBox
/*     */   extends BlockTileEntity {
/*   5 */   public static final BlockStateBoolean HAS_RECORD = BlockStateBoolean.of("has_record");
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {
/*   8 */     dataconvertermanager.a(DataConverterTypes.BLOCK_ENTITY, new DataInspectorItem(TileEntityRecordPlayer.class, new String[] { "RecordItem" }));
/*     */   }
/*     */   
/*     */   protected BlockJukeBox() {
/*  12 */     super(Material.WOOD, MaterialMapColor.m);
/*  13 */     w(this.blockStateList.getBlockData().set(HAS_RECORD, Boolean.valueOf(false)));
/*  14 */     a(CreativeModeTab.c);
/*     */   }
/*     */   
/*     */   public boolean interact(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
/*  18 */     if (((Boolean)iblockdata.<Boolean>get(HAS_RECORD)).booleanValue()) {
/*  19 */       dropRecord(world, blockposition, iblockdata);
/*  20 */       iblockdata = iblockdata.set(HAS_RECORD, Boolean.valueOf(false));
/*  21 */       world.setTypeAndData(blockposition, iblockdata, 2);
/*  22 */       return true;
/*     */     } 
/*  24 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, ItemStack itemstack) {
/*  29 */     TileEntity tileentity = world.getTileEntity(blockposition);
/*     */     
/*  31 */     if (tileentity instanceof TileEntityRecordPlayer) {
/*  32 */       ((TileEntityRecordPlayer)tileentity).setRecord(itemstack.cloneItemStack());
/*  33 */       world.setTypeAndData(blockposition, iblockdata.set(HAS_RECORD, Boolean.valueOf(true)), 2);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void dropRecord(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  38 */     if (!world.isClientSide) {
/*  39 */       TileEntity tileentity = world.getTileEntity(blockposition);
/*     */       
/*  41 */       if (tileentity instanceof TileEntityRecordPlayer) {
/*  42 */         TileEntityRecordPlayer blockjukebox_tileentityrecordplayer = (TileEntityRecordPlayer)tileentity;
/*  43 */         ItemStack itemstack = blockjukebox_tileentityrecordplayer.getRecord();
/*     */         
/*  45 */         if (!itemstack.isEmpty()) {
/*  46 */           world.triggerEffect(1010, blockposition, 0);
/*  47 */           world.a(blockposition, (SoundEffect)null);
/*  48 */           blockjukebox_tileentityrecordplayer.setRecord(ItemStack.a);
/*     */           
/*  50 */           double d0 = (world.random.nextFloat() * 0.7F) + 0.15000000596046448D;
/*  51 */           double d1 = (world.random.nextFloat() * 0.7F) + 0.06000000238418579D + 0.6D;
/*  52 */           double d2 = (world.random.nextFloat() * 0.7F) + 0.15000000596046448D;
/*  53 */           ItemStack itemstack1 = itemstack.cloneItemStack();
/*  54 */           EntityItem entityitem = new EntityItem(world, blockposition.getX() + d0, blockposition.getY() + d1, blockposition.getZ() + d2, itemstack1);
/*     */           
/*  56 */           entityitem.q();
/*  57 */           world.addEntity(entityitem);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void remove(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  64 */     dropRecord(world, blockposition, iblockdata);
/*  65 */     super.remove(world, blockposition, iblockdata);
/*     */   }
/*     */   
/*     */   public void dropNaturally(World world, BlockPosition blockposition, IBlockData iblockdata, float f, int i) {
/*  69 */     if (!world.isClientSide) {
/*  70 */       super.dropNaturally(world, blockposition, iblockdata, f, 0);
/*     */     }
/*     */   }
/*     */   
/*     */   public TileEntity a(World world, int i) {
/*  75 */     return new TileEntityRecordPlayer();
/*     */   }
/*     */   
/*     */   public boolean isComplexRedstone(IBlockData iblockdata) {
/*  79 */     return true;
/*     */   }
/*     */   
/*     */   public int c(IBlockData iblockdata, World world, BlockPosition blockposition) {
/*  83 */     TileEntity tileentity = world.getTileEntity(blockposition);
/*     */     
/*  85 */     if (tileentity instanceof TileEntityRecordPlayer) {
/*  86 */       ItemStack itemstack = ((TileEntityRecordPlayer)tileentity).getRecord();
/*     */       
/*  88 */       if (!itemstack.isEmpty()) {
/*  89 */         return Item.getId(itemstack.getItem()) + 1 - Item.getId(Items.RECORD_13);
/*     */       }
/*     */     } 
/*     */     
/*  93 */     return 0;
/*     */   }
/*     */   
/*     */   public EnumRenderType a(IBlockData iblockdata) {
/*  97 */     return EnumRenderType.MODEL;
/*     */   }
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/* 101 */     return getBlockData().set(HAS_RECORD, Boolean.valueOf((i > 0)));
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 105 */     return ((Boolean)iblockdata.<Boolean>get(HAS_RECORD)).booleanValue() ? 1 : 0;
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 109 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { HAS_RECORD });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class TileEntityRecordPlayer
/*     */     extends TileEntity
/*     */   {
/* 117 */     private ItemStack record = ItemStack.a;
/*     */ 
/*     */     
/*     */     public void a(NBTTagCompound nbttagcompound) {
/* 121 */       super.a(nbttagcompound);
/* 122 */       if (nbttagcompound.hasKeyOfType("RecordItem", 10)) {
/* 123 */         setRecord(new ItemStack(nbttagcompound.getCompound("RecordItem")));
/* 124 */       } else if (nbttagcompound.getInt("Record") > 0) {
/* 125 */         setRecord(new ItemStack(Item.getById(nbttagcompound.getInt("Record"))));
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public NBTTagCompound save(NBTTagCompound nbttagcompound) {
/* 131 */       super.save(nbttagcompound);
/* 132 */       if (!getRecord().isEmpty()) {
/* 133 */         nbttagcompound.set("RecordItem", getRecord().save(new NBTTagCompound()));
/*     */       }
/*     */       
/* 136 */       return nbttagcompound;
/*     */     }
/*     */     
/*     */     public ItemStack getRecord() {
/* 140 */       return this.record;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setRecord(ItemStack itemstack) {
/* 145 */       if (!itemstack.isEmpty()) {
/* 146 */         itemstack.setCount(1);
/*     */       }
/*     */       
/* 149 */       this.record = itemstack;
/* 150 */       update();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockJukeBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */