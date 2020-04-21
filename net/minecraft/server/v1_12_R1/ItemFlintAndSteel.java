/*    */ package net.minecraft.server.v1_12_R1;
/*    */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*    */ 
/*    */ public class ItemFlintAndSteel extends Item {
/*    */   public ItemFlintAndSteel() {
/*  6 */     this.maxStackSize = 1;
/*  7 */     setMaxDurability(64);
/*  8 */     b(CreativeModeTab.i);
/*    */   }
/*    */   
/*    */   public EnumInteractionResult a(EntityHuman entityhuman, World world, BlockPosition blockposition, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
/* 12 */     blockposition = blockposition.shift(enumdirection);
/* 13 */     ItemStack itemstack = entityhuman.b(enumhand);
/*    */     
/* 15 */     if (!entityhuman.a(blockposition, enumdirection, itemstack)) {
/* 16 */       return EnumInteractionResult.FAIL;
/*    */     }
/* 18 */     if (world.getType(blockposition).getMaterial() == Material.AIR) {
/*    */       
/* 20 */       if (CraftEventFactory.callBlockIgniteEvent(world, blockposition.getX(), blockposition.getY(), blockposition.getZ(), BlockIgniteEvent.IgniteCause.FLINT_AND_STEEL, entityhuman).isCancelled()) {
/* 21 */         itemstack.damage(1, entityhuman);
/* 22 */         return EnumInteractionResult.PASS;
/*    */       } 
/*    */       
/* 25 */       world.a(entityhuman, blockposition, SoundEffects.bO, SoundCategory.BLOCKS, 1.0F, j.nextFloat() * 0.4F + 0.8F);
/* 26 */       world.setTypeAndData(blockposition, Blocks.FIRE.getBlockData(), 11);
/*    */     } 
/*    */     
/* 29 */     if (entityhuman instanceof EntityPlayer) {
/* 30 */       CriterionTriggers.x.a((EntityPlayer)entityhuman, blockposition, itemstack);
/*    */     }
/*    */     
/* 33 */     itemstack.damage(1, entityhuman);
/* 34 */     return EnumInteractionResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemFlintAndSteel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */