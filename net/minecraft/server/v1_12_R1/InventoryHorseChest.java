/*   */ package net.minecraft.server.v1_12_R1;
/*   */ 
/*   */ import org.bukkit.inventory.InventoryHolder;
/*   */ 
/*   */ public class InventoryHorseChest extends InventorySubcontainer {
/*   */   public InventoryHorseChest(String s, int i, EntityHorseAbstract owner) {
/* 7 */     super(s, false, i, (InventoryHolder)owner.getBukkitEntity());
/*   */   }
/*   */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\InventoryHorseChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */