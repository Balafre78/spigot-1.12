/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public class ItemBlock
/*    */   extends Item {
/*    */   protected final Block a;
/*    */   
/*    */   public ItemBlock(Block block) {
/* 10 */     this.a = block;
/*    */   }
/*    */   
/*    */   public EnumInteractionResult a(EntityHuman entityhuman, World world, BlockPosition blockposition, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
/* 14 */     IBlockData iblockdata = world.getType(blockposition);
/* 15 */     Block block = iblockdata.getBlock();
/*    */     
/* 17 */     if (!block.a(world, blockposition)) {
/* 18 */       blockposition = blockposition.shift(enumdirection);
/*    */     }
/*    */     
/* 21 */     ItemStack itemstack = entityhuman.b(enumhand);
/*    */     
/* 23 */     if (!itemstack.isEmpty() && entityhuman.a(blockposition, enumdirection, itemstack) && world.a(this.a, blockposition, false, enumdirection, (Entity)null)) {
/* 24 */       int i = filterData(itemstack.getData());
/* 25 */       IBlockData iblockdata1 = this.a.getPlacedState(world, blockposition, enumdirection, f, f1, f2, i, entityhuman);
/*    */       
/* 27 */       if (world.setTypeAndData(blockposition, iblockdata1, 11)) {
/* 28 */         iblockdata1 = world.getType(blockposition);
/* 29 */         if (iblockdata1.getBlock() == this.a) {
/* 30 */           a(world, entityhuman, blockposition, itemstack);
/* 31 */           this.a.postPlace(world, blockposition, iblockdata1, entityhuman, itemstack);
/* 32 */           if (entityhuman instanceof EntityPlayer) {
/* 33 */             CriterionTriggers.x.a((EntityPlayer)entityhuman, blockposition, itemstack);
/*    */           }
/*    */         } 
/*    */         
/* 37 */         this.a.getStepSound();
/*    */ 
/*    */         
/* 40 */         itemstack.subtract(1);
/*    */       } 
/*    */       
/* 43 */       return EnumInteractionResult.SUCCESS;
/*    */     } 
/* 45 */     return EnumInteractionResult.FAIL;
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean a(World world, @Nullable EntityHuman entityhuman, BlockPosition blockposition, ItemStack itemstack) {
/* 50 */     MinecraftServer minecraftserver = world.getMinecraftServer();
/*    */     
/* 52 */     if (minecraftserver == null) {
/* 53 */       return false;
/*    */     }
/* 55 */     NBTTagCompound nbttagcompound = itemstack.d("BlockEntityTag");
/*    */     
/* 57 */     if (nbttagcompound != null) {
/* 58 */       TileEntity tileentity = world.getTileEntity(blockposition);
/*    */       
/* 60 */       if (tileentity != null) {
/* 61 */         if (!world.isClientSide && tileentity.isFilteredNBT() && (entityhuman == null || !entityhuman.isCreativeAndOp())) {
/* 62 */           return false;
/*    */         }
/*    */         
/* 65 */         NBTTagCompound nbttagcompound1 = tileentity.save(new NBTTagCompound());
/* 66 */         NBTTagCompound nbttagcompound2 = nbttagcompound1.g();
/*    */         
/* 68 */         nbttagcompound1.a(nbttagcompound);
/* 69 */         nbttagcompound1.setInt("x", blockposition.getX());
/* 70 */         nbttagcompound1.setInt("y", blockposition.getY());
/* 71 */         nbttagcompound1.setInt("z", blockposition.getZ());
/* 72 */         if (!nbttagcompound1.equals(nbttagcompound2)) {
/* 73 */           tileentity.a(nbttagcompound1);
/* 74 */           tileentity.update();
/* 75 */           return true;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 80 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String a(ItemStack itemstack) {
/* 85 */     return this.a.a();
/*    */   }
/*    */   
/*    */   public String getName() {
/* 89 */     return this.a.a();
/*    */   }
/*    */   
/*    */   public Block getBlock() {
/* 93 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */