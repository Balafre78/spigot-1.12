/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class ItemDoor
/*    */   extends Item {
/*    */   private final Block a;
/*    */   
/*    */   public ItemDoor(Block block) {
/*  8 */     this.a = block;
/*  9 */     b(CreativeModeTab.d);
/*    */   }
/*    */   
/*    */   public EnumInteractionResult a(EntityHuman entityhuman, World world, BlockPosition blockposition, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
/* 13 */     if (enumdirection != EnumDirection.UP) {
/* 14 */       return EnumInteractionResult.FAIL;
/*    */     }
/* 16 */     IBlockData iblockdata = world.getType(blockposition);
/* 17 */     Block block = iblockdata.getBlock();
/*    */     
/* 19 */     if (!block.a(world, blockposition)) {
/* 20 */       blockposition = blockposition.shift(enumdirection);
/*    */     }
/*    */     
/* 23 */     ItemStack itemstack = entityhuman.b(enumhand);
/*    */     
/* 25 */     if (entityhuman.a(blockposition, enumdirection, itemstack) && this.a.canPlace(world, blockposition)) {
/* 26 */       EnumDirection enumdirection1 = EnumDirection.fromAngle(entityhuman.yaw);
/* 27 */       int i = enumdirection1.getAdjacentX();
/* 28 */       int j = enumdirection1.getAdjacentZ();
/* 29 */       boolean flag = !((i >= 0 || f2 >= 0.5F) && (i <= 0 || f2 <= 0.5F) && (j >= 0 || f <= 0.5F) && (j <= 0 || f >= 0.5F));
/*    */       
/* 31 */       a(world, blockposition, enumdirection1, this.a, flag);
/* 32 */       SoundEffectType soundeffecttype = this.a.getStepSound();
/*    */       
/* 34 */       world.a(entityhuman, blockposition, soundeffecttype.e(), SoundCategory.BLOCKS, (soundeffecttype.a() + 1.0F) / 2.0F, soundeffecttype.b() * 0.8F);
/* 35 */       itemstack.subtract(1);
/* 36 */       return EnumInteractionResult.SUCCESS;
/*    */     } 
/* 38 */     return EnumInteractionResult.FAIL;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void a(World world, BlockPosition blockposition, EnumDirection enumdirection, Block block, boolean flag) {
/* 44 */     BlockPosition blockposition1 = blockposition.shift(enumdirection.e());
/* 45 */     BlockPosition blockposition2 = blockposition.shift(enumdirection.f());
/* 46 */     int i = (world.getType(blockposition2).l() ? 1 : 0) + (world.getType(blockposition2.up()).l() ? 1 : 0);
/* 47 */     int j = (world.getType(blockposition1).l() ? 1 : 0) + (world.getType(blockposition1.up()).l() ? 1 : 0);
/* 48 */     boolean flag1 = !(world.getType(blockposition2).getBlock() != block && world.getType(blockposition2.up()).getBlock() != block);
/* 49 */     boolean flag2 = !(world.getType(blockposition1).getBlock() != block && world.getType(blockposition1.up()).getBlock() != block);
/*    */     
/* 51 */     if ((!flag1 || flag2) && j <= i) {
/* 52 */       if ((flag2 && !flag1) || j < i) {
/* 53 */         flag = false;
/*    */       }
/*    */     } else {
/* 56 */       flag = true;
/*    */     } 
/*    */     
/* 59 */     BlockPosition blockposition3 = blockposition.up();
/* 60 */     boolean flag3 = !(!world.isBlockIndirectlyPowered(blockposition) && !world.isBlockIndirectlyPowered(blockposition3));
/* 61 */     IBlockData iblockdata = block.getBlockData().<Comparable, EnumDirection>set(BlockDoor.FACING, enumdirection).<BlockDoor.EnumDoorHinge, BlockDoor.EnumDoorHinge>set(BlockDoor.HINGE, flag ? BlockDoor.EnumDoorHinge.RIGHT : BlockDoor.EnumDoorHinge.LEFT).<Comparable, Boolean>set(BlockDoor.POWERED, Boolean.valueOf(flag3)).set(BlockDoor.OPEN, Boolean.valueOf(flag3));
/*    */ 
/*    */     
/* 64 */     world.setTypeAndData(blockposition, iblockdata.set(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER), 3);
/* 65 */     world.setTypeAndData(blockposition3, iblockdata.set(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER), 3);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemDoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */