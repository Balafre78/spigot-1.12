/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ItemRecord
/*    */   extends Item {
/*  8 */   private static final Map<SoundEffect, ItemRecord> a = Maps.newHashMap();
/*    */   private final SoundEffect b;
/*    */   private final String c;
/*    */   
/*    */   protected ItemRecord(String s, SoundEffect soundeffect) {
/* 13 */     this.c = "item.record." + s + ".desc";
/* 14 */     this.b = soundeffect;
/* 15 */     this.maxStackSize = 1;
/* 16 */     b(CreativeModeTab.f);
/* 17 */     a.put(this.b, this);
/*    */   }
/*    */   
/*    */   public EnumInteractionResult a(EntityHuman entityhuman, World world, BlockPosition blockposition, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
/* 21 */     IBlockData iblockdata = world.getType(blockposition);
/*    */     
/* 23 */     if (iblockdata.getBlock() == Blocks.JUKEBOX && !((Boolean)iblockdata.<Boolean>get(BlockJukeBox.HAS_RECORD)).booleanValue()) {
/* 24 */       if (!world.isClientSide) {
/* 25 */         return EnumInteractionResult.SUCCESS;
/*    */       }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 34 */       return EnumInteractionResult.SUCCESS;
/*    */     } 
/* 36 */     return EnumInteractionResult.PASS;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumItemRarity g(ItemStack itemstack) {
/* 41 */     return EnumItemRarity.RARE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */