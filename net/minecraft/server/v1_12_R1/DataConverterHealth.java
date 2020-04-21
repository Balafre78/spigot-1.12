/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Sets;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterHealth
/*    */   implements IDataConverter
/*    */ {
/* 11 */   private static final Set<String> a = Sets.newHashSet((Object[])new String[] { "ArmorStand", "Bat", "Blaze", "CaveSpider", "Chicken", "Cow", "Creeper", "EnderDragon", "Enderman", "Endermite", "EntityHorse", "Ghast", "Giant", "Guardian", "LavaSlime", "MushroomCow", "Ozelot", "Pig", "PigZombie", "Rabbit", "Sheep", "Shulker", "Silverfish", "Skeleton", "Slime", "SnowMan", "Spider", "Squid", "Villager", "VillagerGolem", "Witch", "WitherBoss", "Wolf", "Zombie" });
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
/*    */ 
/*    */   
/*    */   public int a() {
/* 51 */     return 109;
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 56 */     if (a.contains(paramNBTTagCompound.getString("id"))) {
/*    */       float f;
/* 58 */       if (paramNBTTagCompound.hasKeyOfType("HealF", 99)) {
/* 59 */         f = paramNBTTagCompound.getFloat("HealF");
/* 60 */         paramNBTTagCompound.remove("HealF");
/* 61 */       } else if (paramNBTTagCompound.hasKeyOfType("Health", 99)) {
/* 62 */         f = paramNBTTagCompound.getFloat("Health");
/*    */       } else {
/* 64 */         return paramNBTTagCompound;
/*    */       } 
/* 66 */       paramNBTTagCompound.setFloat("Health", f);
/*    */     } 
/*    */     
/* 69 */     return paramNBTTagCompound;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterHealth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */