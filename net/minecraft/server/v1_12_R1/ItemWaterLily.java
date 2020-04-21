/*    */ package net.minecraft.server.v1_12_R1;
/*    */ import org.bukkit.craftbukkit.v1_12_R1.block.CraftBlockState;
/*    */ 
/*    */ public class ItemWaterLily extends ItemWithAuxData {
/*    */   public ItemWaterLily(Block block) {
/*  6 */     super(block, false);
/*    */   }
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
/* 10 */     ItemStack itemstack = entityhuman.b(enumhand);
/* 11 */     MovingObjectPosition movingobjectposition = a(world, entityhuman, true);
/*    */     
/* 13 */     if (movingobjectposition == null) {
/* 14 */       return new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemstack);
/*    */     }
/* 16 */     if (movingobjectposition.type == MovingObjectPosition.EnumMovingObjectType.BLOCK) {
/* 17 */       BlockPosition blockposition = movingobjectposition.a();
/*    */       
/* 19 */       if (!world.a(entityhuman, blockposition) || !entityhuman.a(blockposition.shift(movingobjectposition.direction), movingobjectposition.direction, itemstack)) {
/* 20 */         return new InteractionResultWrapper<>(EnumInteractionResult.FAIL, itemstack);
/*    */       }
/*    */       
/* 23 */       BlockPosition blockposition1 = blockposition.up();
/* 24 */       IBlockData iblockdata = world.getType(blockposition);
/*    */       
/* 26 */       if (iblockdata.getMaterial() == Material.WATER && ((Integer)iblockdata.<Integer>get(BlockFluids.LEVEL)).intValue() == 0 && world.isEmpty(blockposition1)) {
/*    */         
/* 28 */         CraftBlockState craftBlockState = CraftBlockState.getBlockState(world, blockposition1.getX(), blockposition1.getY(), blockposition1.getZ());
/* 29 */         world.setTypeAndData(blockposition1, Blocks.WATERLILY.getBlockData(), 11);
/* 30 */         BlockPlaceEvent placeEvent = CraftEventFactory.callBlockPlaceEvent(world, entityhuman, enumhand, (BlockState)craftBlockState, blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 31 */         if (placeEvent != null && (placeEvent.isCancelled() || !placeEvent.canBuild())) {
/* 32 */           craftBlockState.update(true, false);
/* 33 */           return new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemstack);
/*    */         } 
/*    */         
/* 36 */         if (entityhuman instanceof EntityPlayer) {
/* 37 */           CriterionTriggers.x.a((EntityPlayer)entityhuman, blockposition1, itemstack);
/*    */         }
/*    */         
/* 40 */         if (!entityhuman.abilities.canInstantlyBuild) {
/* 41 */           itemstack.subtract(1);
/*    */         }
/*    */         
/* 44 */         entityhuman.b(StatisticList.b(this));
/* 45 */         world.a(entityhuman, blockposition, SoundEffects.it, SoundCategory.BLOCKS, 1.0F, 1.0F);
/* 46 */         return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemstack);
/*    */       } 
/*    */     } 
/*    */     
/* 50 */     return new InteractionResultWrapper<>(EnumInteractionResult.FAIL, itemstack);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemWaterLily.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */