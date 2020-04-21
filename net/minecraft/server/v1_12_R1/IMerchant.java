package net.minecraft.server.v1_12_R1;

import javax.annotation.Nullable;

public interface IMerchant {
  void setTradingPlayer(@Nullable EntityHuman paramEntityHuman);
  
  @Nullable
  EntityHuman getTrader();
  
  @Nullable
  MerchantRecipeList getOffers(EntityHuman paramEntityHuman);
  
  void a(MerchantRecipe paramMerchantRecipe);
  
  void a(ItemStack paramItemStack);
  
  IChatBaseComponent getScoreboardDisplayName();
  
  World u_();
  
  BlockPosition v_();
}


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\IMerchant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */