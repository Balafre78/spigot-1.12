/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*    */ 
/*    */ 
/*    */ public class EnchantmentFrostWalker
/*    */   extends Enchantment
/*    */ {
/*    */   public EnchantmentFrostWalker(Enchantment.Rarity enchantment_rarity, EnumItemSlot... aenumitemslot) {
/* 11 */     super(enchantment_rarity, EnchantmentSlotType.ARMOR_FEET, aenumitemslot);
/* 12 */     c("frostWalker");
/*    */   }
/*    */   
/*    */   public int a(int i) {
/* 16 */     return i * 10;
/*    */   }
/*    */   
/*    */   public int b(int i) {
/* 20 */     return a(i) + 15;
/*    */   }
/*    */   
/*    */   public boolean isTreasure() {
/* 24 */     return true;
/*    */   }
/*    */   
/*    */   public int getMaxLevel() {
/* 28 */     return 2;
/*    */   }
/*    */   
/*    */   public static void a(EntityLiving entityliving, World world, BlockPosition blockposition, int i) {
/* 32 */     if (entityliving.onGround) {
/* 33 */       float f = Math.min(16, 2 + i);
/* 34 */       BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition(0, 0, 0);
/* 35 */       Iterator<BlockPosition.MutableBlockPosition> iterator = BlockPosition.b(blockposition.a(-f, -1.0D, -f), blockposition.a(f, -1.0D, f)).iterator();
/*    */       
/* 37 */       while (iterator.hasNext()) {
/* 38 */         BlockPosition.MutableBlockPosition blockposition_mutableblockposition1 = iterator.next();
/*    */         
/* 40 */         if (blockposition_mutableblockposition1.g(entityliving.locX, entityliving.locY, entityliving.locZ) <= (f * f)) {
/* 41 */           blockposition_mutableblockposition.c(blockposition_mutableblockposition1.getX(), blockposition_mutableblockposition1.getY() + 1, blockposition_mutableblockposition1.getZ());
/* 42 */           IBlockData iblockdata = world.getType(blockposition_mutableblockposition);
/*    */           
/* 44 */           if (iblockdata.getMaterial() == Material.AIR) {
/* 45 */             IBlockData iblockdata1 = world.getType(blockposition_mutableblockposition1);
/*    */             
/* 47 */             if (iblockdata1.getMaterial() == Material.WATER && ((Integer)iblockdata1.<Integer>get(BlockFluids.LEVEL)).intValue() == 0 && world.a(Blocks.FROSTED_ICE, blockposition_mutableblockposition1, false, EnumDirection.DOWN, (Entity)null))
/*    */             {
/* 49 */               if (CraftEventFactory.handleBlockFormEvent(world, blockposition_mutableblockposition1, Blocks.FROSTED_ICE.getBlockData(), entityliving)) {
/* 50 */                 world.a(blockposition_mutableblockposition1.h(), Blocks.FROSTED_ICE, MathHelper.nextInt(entityliving.getRandom(), 60, 120));
/*    */               }
/*    */             }
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean a(Enchantment enchantment) {
/* 62 */     return (super.a(enchantment) && enchantment != Enchantments.DEPTH_STRIDER);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnchantmentFrostWalker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */