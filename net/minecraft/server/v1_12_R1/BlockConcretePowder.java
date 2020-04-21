/*    */ package net.minecraft.server.v1_12_R1;
/*    */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*    */ 
/*    */ public class BlockConcretePowder extends BlockFalling {
/*  5 */   public static final BlockStateEnum<EnumColor> a = BlockStateEnum.of("color", EnumColor.class);
/*    */   
/*    */   public BlockConcretePowder() {
/*  8 */     super(Material.SAND);
/*  9 */     w(this.blockStateList.getBlockData().set(a, EnumColor.WHITE));
/* 10 */     a(CreativeModeTab.b);
/*    */   }
/*    */   
/*    */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, IBlockData iblockdata1) {
/* 14 */     if (iblockdata1.getMaterial().isLiquid() && world.getType(blockposition).getBlock() != Blocks.dR) {
/* 15 */       CraftEventFactory.handleBlockFormEvent(world, blockposition, Blocks.dR.getBlockData().set(BlockCloth.COLOR, iblockdata.<EnumColor>get(a)), null);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean e(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 21 */     boolean flag = false;
/* 22 */     EnumDirection[] aenumdirection = EnumDirection.values();
/* 23 */     int i = aenumdirection.length;
/*    */     
/* 25 */     for (int j = 0; j < i; j++) {
/* 26 */       EnumDirection enumdirection = aenumdirection[j];
/*    */       
/* 28 */       if (enumdirection != EnumDirection.DOWN) {
/* 29 */         BlockPosition blockposition1 = blockposition.shift(enumdirection);
/*    */         
/* 31 */         if (world.getType(blockposition1).getMaterial() == Material.WATER) {
/* 32 */           flag = true;
/*    */           
/*    */           break;
/*    */         } 
/*    */       } 
/*    */     } 
/* 38 */     if (flag) {
/* 39 */       CraftEventFactory.handleBlockFormEvent(world, blockposition, Blocks.dR.getBlockData().set(BlockCloth.COLOR, iblockdata.<EnumColor>get(a)), null);
/*    */     }
/*    */     
/* 42 */     return flag;
/*    */   }
/*    */   
/*    */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
/* 46 */     if (!e(world, blockposition, iblockdata)) {
/* 47 */       super.a(iblockdata, world, blockposition, block, blockposition1);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onPlace(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 53 */     if (!e(world, blockposition, iblockdata)) {
/* 54 */       super.onPlace(world, blockposition, iblockdata);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getDropData(IBlockData iblockdata) {
/* 60 */     return ((EnumColor)iblockdata.<EnumColor>get(a)).getColorIndex();
/*    */   }
/*    */   
/*    */   public MaterialMapColor c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 64 */     return MaterialMapColor.a(iblockdata.<EnumColor>get(a));
/*    */   }
/*    */   
/*    */   public IBlockData fromLegacyData(int i) {
/* 68 */     return getBlockData().set(a, EnumColor.fromColorIndex(i));
/*    */   }
/*    */   
/*    */   public int toLegacyData(IBlockData iblockdata) {
/* 72 */     return ((EnumColor)iblockdata.<EnumColor>get(a)).getColorIndex();
/*    */   }
/*    */   
/*    */   protected BlockStateList getStateList() {
/* 76 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { a });
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockConcretePowder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */