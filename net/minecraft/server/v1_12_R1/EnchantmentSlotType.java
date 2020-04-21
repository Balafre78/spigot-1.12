/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum EnchantmentSlotType
/*    */ {
/* 16 */   ALL
/*    */   {
/*    */     public boolean canEnchant(Item param1Item) {
/* 19 */       for (EnchantmentSlotType enchantmentSlotType : values()) {
/* 20 */         if (enchantmentSlotType != EnchantmentSlotType.ALL)
/*    */         {
/*    */ 
/*    */           
/* 24 */           if (enchantmentSlotType.canEnchant(param1Item)) {
/* 25 */             return true;
/*    */           }
/*    */         }
/*    */       } 
/* 29 */       return false;
/*    */     }
/*    */   },
/*    */   
/* 33 */   ARMOR
/*    */   {
/*    */     public boolean canEnchant(Item param1Item) {
/* 36 */       return param1Item instanceof ItemArmor;
/*    */     }
/*    */   },
/* 39 */   ARMOR_FEET
/*    */   {
/*    */     public boolean canEnchant(Item param1Item) {
/* 42 */       return (param1Item instanceof ItemArmor && ((ItemArmor)param1Item).c == EnumItemSlot.FEET);
/*    */     }
/*    */   },
/* 45 */   ARMOR_LEGS
/*    */   {
/*    */     public boolean canEnchant(Item param1Item) {
/* 48 */       return (param1Item instanceof ItemArmor && ((ItemArmor)param1Item).c == EnumItemSlot.LEGS);
/*    */     }
/*    */   },
/* 51 */   ARMOR_CHEST
/*    */   {
/*    */     public boolean canEnchant(Item param1Item) {
/* 54 */       return (param1Item instanceof ItemArmor && ((ItemArmor)param1Item).c == EnumItemSlot.CHEST);
/*    */     }
/*    */   },
/* 57 */   ARMOR_HEAD
/*    */   {
/*    */     public boolean canEnchant(Item param1Item) {
/* 60 */       return (param1Item instanceof ItemArmor && ((ItemArmor)param1Item).c == EnumItemSlot.HEAD);
/*    */     }
/*    */   },
/*    */   
/* 64 */   WEAPON
/*    */   {
/*    */     public boolean canEnchant(Item param1Item) {
/* 67 */       return param1Item instanceof ItemSword;
/*    */     }
/*    */   },
/* 70 */   DIGGER
/*    */   {
/*    */     public boolean canEnchant(Item param1Item) {
/* 73 */       return param1Item instanceof ItemTool;
/*    */     }
/*    */   },
/* 76 */   FISHING_ROD
/*    */   {
/*    */     public boolean canEnchant(Item param1Item) {
/* 79 */       return param1Item instanceof ItemFishingRod;
/*    */     }
/*    */   },
/* 82 */   BREAKABLE
/*    */   {
/*    */     public boolean canEnchant(Item param1Item) {
/* 85 */       return param1Item.usesDurability();
/*    */     }
/*    */   },
/* 88 */   BOW
/*    */   {
/*    */     public boolean canEnchant(Item param1Item) {
/* 91 */       return param1Item instanceof ItemBow;
/*    */     }
/*    */   },
/* 94 */   WEARABLE
/*    */   {
/*    */     public boolean canEnchant(Item param1Item) {
/* 97 */       boolean bool = (param1Item instanceof ItemBlock && ((ItemBlock)param1Item).getBlock() instanceof BlockPumpkin) ? true : false;
/* 98 */       return (param1Item instanceof ItemArmor || param1Item instanceof ItemElytra || param1Item instanceof ItemSkull || bool);
/*    */     }
/*    */   };
/*    */   
/*    */   public abstract boolean canEnchant(Item paramItem);
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnchantmentSlotType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */