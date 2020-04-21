/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Random;
/*    */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*    */ 
/*    */ public class BlockMagma extends Block {
/*    */   public BlockMagma() {
/*  8 */     super(Material.STONE);
/*  9 */     a(CreativeModeTab.b);
/* 10 */     a(0.2F);
/* 11 */     a(true);
/*    */   }
/*    */   
/*    */   public MaterialMapColor c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 15 */     return MaterialMapColor.L;
/*    */   }
/*    */   
/*    */   public void stepOn(World world, BlockPosition blockposition, Entity entity) {
/* 19 */     if (!entity.isFireProof() && entity instanceof EntityLiving && !EnchantmentManager.i((EntityLiving)entity)) {
/* 20 */       CraftEventFactory.blockDamage = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 21 */       entity.damageEntity(DamageSource.HOT_FLOOR, 1.0F);
/* 22 */       CraftEventFactory.blockDamage = null;
/*    */     } 
/*    */     
/* 25 */     super.stepOn(world, blockposition, entity);
/*    */   }
/*    */   
/*    */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/* 29 */     BlockPosition blockposition1 = blockposition.up();
/* 30 */     IBlockData iblockdata1 = world.getType(blockposition1);
/*    */     
/* 32 */     if (iblockdata1.getBlock() == Blocks.WATER || iblockdata1.getBlock() == Blocks.FLOWING_WATER) {
/* 33 */       world.setAir(blockposition1);
/* 34 */       world.a(null, blockposition, SoundEffects.bN, SoundCategory.BLOCKS, 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);
/* 35 */       if (world instanceof WorldServer) {
/* 36 */         ((WorldServer)world).a(EnumParticle.SMOKE_LARGE, blockposition1.getX() + 0.5D, blockposition1.getY() + 0.25D, blockposition1.getZ() + 0.5D, 8, 0.5D, 0.25D, 0.5D, 0.0D, new int[0]);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockData iblockdata, Entity entity) {
/* 43 */     return entity.isFireProof();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockMagma.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */