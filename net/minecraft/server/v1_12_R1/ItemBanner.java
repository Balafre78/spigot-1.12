/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import javax.annotation.Nullable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemBanner
/*     */   extends ItemBlock
/*     */ {
/*     */   public ItemBanner() {
/*  31 */     super(Blocks.STANDING_BANNER);
/*  32 */     this.maxStackSize = 16;
/*  33 */     b(CreativeModeTab.c);
/*  34 */     a(true);
/*  35 */     setMaxDurability(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult a(EntityHuman paramEntityHuman, World paramWorld, BlockPosition paramBlockPosition, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/*  40 */     IBlockData iBlockData = paramWorld.getType(paramBlockPosition);
/*  41 */     boolean bool = iBlockData.getBlock().a(paramWorld, paramBlockPosition);
/*     */     
/*  43 */     if (paramEnumDirection == EnumDirection.DOWN || (!iBlockData.getMaterial().isBuildable() && !bool) || (bool && paramEnumDirection != EnumDirection.UP)) {
/*  44 */       return EnumInteractionResult.FAIL;
/*     */     }
/*     */     
/*  47 */     paramBlockPosition = paramBlockPosition.shift(paramEnumDirection);
/*     */     
/*  49 */     ItemStack itemStack = paramEntityHuman.b(paramEnumHand);
/*  50 */     if (!paramEntityHuman.a(paramBlockPosition, paramEnumDirection, itemStack) || !Blocks.STANDING_BANNER.canPlace(paramWorld, paramBlockPosition)) {
/*  51 */       return EnumInteractionResult.FAIL;
/*     */     }
/*     */     
/*  54 */     if (paramWorld.isClientSide) {
/*  55 */       return EnumInteractionResult.SUCCESS;
/*     */     }
/*     */     
/*  58 */     paramBlockPosition = bool ? paramBlockPosition.down() : paramBlockPosition;
/*     */     
/*  60 */     if (paramEnumDirection == EnumDirection.UP) {
/*  61 */       int i = MathHelper.floor(((paramEntityHuman.yaw + 180.0F) * 16.0F / 360.0F) + 0.5D) & 0xF;
/*  62 */       paramWorld.setTypeAndData(paramBlockPosition, Blocks.STANDING_BANNER.getBlockData().set(BlockFloorSign.ROTATION, Integer.valueOf(i)), 3);
/*     */     } else {
/*  64 */       paramWorld.setTypeAndData(paramBlockPosition, Blocks.WALL_BANNER.getBlockData().set(BlockWallSign.FACING, paramEnumDirection), 3);
/*     */     } 
/*     */     
/*  67 */     TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/*  68 */     if (tileEntity instanceof TileEntityBanner) {
/*  69 */       ((TileEntityBanner)tileEntity).a(itemStack, false);
/*     */     }
/*  71 */     if (paramEntityHuman instanceof EntityPlayer) {
/*  72 */       CriterionTriggers.x.a((EntityPlayer)paramEntityHuman, paramBlockPosition, itemStack);
/*     */     }
/*  74 */     itemStack.subtract(1);
/*  75 */     return EnumInteractionResult.SUCCESS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String b(ItemStack paramItemStack) {
/*  80 */     String str = "item.banner.";
/*     */     
/*  82 */     EnumColor enumColor = c(paramItemStack);
/*  83 */     str = str + enumColor.d() + ".name";
/*  84 */     return LocaleI18n.get(str);
/*     */   }
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
/*     */   public static ItemStack a(EnumColor paramEnumColor, @Nullable NBTTagList paramNBTTagList) {
/* 120 */     ItemStack itemStack = new ItemStack(Items.BANNER, 1, paramEnumColor.getInvColorIndex());
/* 121 */     if (paramNBTTagList != null && !paramNBTTagList.isEmpty()) {
/* 122 */       itemStack.c("BlockEntityTag").set("Patterns", paramNBTTagList.d());
/*     */     }
/*     */     
/* 125 */     return itemStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static EnumColor c(ItemStack paramItemStack) {
/* 134 */     return EnumColor.fromInvColorIndex(paramItemStack.getData() & 0xF);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemBanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */