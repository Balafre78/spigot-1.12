/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import org.bukkit.DyeColor;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.SheepDyeWoolEvent;
/*     */ 
/*     */ public class ItemDye extends Item {
/*   7 */   public static final int[] a = new int[] { 1973019, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799, 11250603, 4408131, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 15790320 };
/*     */   
/*     */   public ItemDye() {
/*  10 */     a(true);
/*  11 */     setMaxDurability(0);
/*  12 */     b(CreativeModeTab.l);
/*     */   }
/*     */   
/*     */   public String a(ItemStack itemstack) {
/*  16 */     int i = itemstack.getData();
/*     */     
/*  18 */     return String.valueOf(getName()) + "." + EnumColor.fromInvColorIndex(i).d();
/*     */   }
/*     */   
/*     */   public EnumInteractionResult a(EntityHuman entityhuman, World world, BlockPosition blockposition, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
/*  22 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/*  24 */     if (!entityhuman.a(blockposition.shift(enumdirection), enumdirection, itemstack)) {
/*  25 */       return EnumInteractionResult.FAIL;
/*     */     }
/*  27 */     EnumColor enumcolor = EnumColor.fromInvColorIndex(itemstack.getData());
/*     */     
/*  29 */     if (enumcolor == EnumColor.WHITE) {
/*  30 */       if (a(itemstack, world, blockposition)) {
/*  31 */         if (!world.isClientSide) {
/*  32 */           world.triggerEffect(2005, blockposition, 0);
/*     */         }
/*     */         
/*  35 */         return EnumInteractionResult.SUCCESS;
/*     */       } 
/*  37 */     } else if (enumcolor == EnumColor.BROWN) {
/*  38 */       IBlockData iblockdata = world.getType(blockposition);
/*  39 */       Block block = iblockdata.getBlock();
/*     */       
/*  41 */       if (block == Blocks.LOG && iblockdata.get(BlockLog1.VARIANT) == BlockWood.EnumLogVariant.JUNGLE) {
/*  42 */         if (enumdirection == EnumDirection.DOWN || enumdirection == EnumDirection.UP) {
/*  43 */           return EnumInteractionResult.FAIL;
/*     */         }
/*     */         
/*  46 */         blockposition = blockposition.shift(enumdirection);
/*  47 */         if (world.isEmpty(blockposition)) {
/*  48 */           IBlockData iblockdata1 = Blocks.COCOA.getPlacedState(world, blockposition, enumdirection, f, f1, f2, 0, entityhuman);
/*     */           
/*  50 */           world.setTypeAndData(blockposition, iblockdata1, 10);
/*  51 */           if (!entityhuman.abilities.canInstantlyBuild) {
/*  52 */             itemstack.subtract(1);
/*     */           }
/*     */           
/*  55 */           return EnumInteractionResult.SUCCESS;
/*     */         } 
/*     */       } 
/*     */       
/*  59 */       return EnumInteractionResult.FAIL;
/*     */     } 
/*     */     
/*  62 */     return EnumInteractionResult.PASS;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean a(ItemStack itemstack, World world, BlockPosition blockposition) {
/*  67 */     IBlockData iblockdata = world.getType(blockposition);
/*     */     
/*  69 */     if (iblockdata.getBlock() instanceof IBlockFragilePlantElement) {
/*  70 */       IBlockFragilePlantElement iblockfragileplantelement = (IBlockFragilePlantElement)iblockdata.getBlock();
/*     */       
/*  72 */       if (iblockfragileplantelement.a(world, blockposition, iblockdata, world.isClientSide)) {
/*  73 */         if (!world.isClientSide) {
/*  74 */           if (iblockfragileplantelement.a(world, world.random, blockposition, iblockdata)) {
/*  75 */             iblockfragileplantelement.b(world, world.random, blockposition, iblockdata);
/*     */           }
/*     */           
/*  78 */           itemstack.subtract(1);
/*     */         } 
/*     */         
/*  81 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/*  85 */     return false;
/*     */   }
/*     */   
/*     */   public boolean a(ItemStack itemstack, EntityHuman entityhuman, EntityLiving entityliving, EnumHand enumhand) {
/*  89 */     if (entityliving instanceof EntitySheep) {
/*  90 */       EntitySheep entitysheep = (EntitySheep)entityliving;
/*  91 */       EnumColor enumcolor = EnumColor.fromInvColorIndex(itemstack.getData());
/*     */       
/*  93 */       if (!entitysheep.isSheared() && entitysheep.getColor() != enumcolor) {
/*     */         
/*  95 */         byte bColor = (byte)enumcolor.getColorIndex();
/*  96 */         SheepDyeWoolEvent event = new SheepDyeWoolEvent((Sheep)entitysheep.getBukkitEntity(), DyeColor.getByWoolData(bColor));
/*  97 */         entitysheep.world.getServer().getPluginManager().callEvent((Event)event);
/*     */         
/*  99 */         if (event.isCancelled()) {
/* 100 */           return false;
/*     */         }
/*     */         
/* 103 */         enumcolor = EnumColor.fromColorIndex(event.getColor().getWoolData());
/*     */         
/* 105 */         entitysheep.setColor(enumcolor);
/* 106 */         itemstack.subtract(1);
/*     */       } 
/*     */       
/* 109 */       return true;
/*     */     } 
/* 111 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemDye.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */