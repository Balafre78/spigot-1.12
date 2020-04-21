/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataConverterSpawnEgg
/*     */   implements IDataConverter
/*     */ {
/*   8 */   private static final String[] a = new String[256];
/*     */   
/*     */   static {
/*  11 */     String[] arrayOfString = a;
/*     */ 
/*     */     
/*  14 */     arrayOfString[1] = "Item";
/*  15 */     arrayOfString[2] = "XPOrb";
/*     */     
/*  17 */     arrayOfString[7] = "ThrownEgg";
/*  18 */     arrayOfString[8] = "LeashKnot";
/*  19 */     arrayOfString[9] = "Painting";
/*  20 */     arrayOfString[10] = "Arrow";
/*  21 */     arrayOfString[11] = "Snowball";
/*  22 */     arrayOfString[12] = "Fireball";
/*  23 */     arrayOfString[13] = "SmallFireball";
/*  24 */     arrayOfString[14] = "ThrownEnderpearl";
/*  25 */     arrayOfString[15] = "EyeOfEnderSignal";
/*  26 */     arrayOfString[16] = "ThrownPotion";
/*  27 */     arrayOfString[17] = "ThrownExpBottle";
/*  28 */     arrayOfString[18] = "ItemFrame";
/*  29 */     arrayOfString[19] = "WitherSkull";
/*     */     
/*  31 */     arrayOfString[20] = "PrimedTnt";
/*  32 */     arrayOfString[21] = "FallingSand";
/*  33 */     arrayOfString[22] = "FireworksRocketEntity";
/*  34 */     arrayOfString[23] = "TippedArrow";
/*  35 */     arrayOfString[24] = "SpectralArrow";
/*  36 */     arrayOfString[25] = "ShulkerBullet";
/*  37 */     arrayOfString[26] = "DragonFireball";
/*     */     
/*  39 */     arrayOfString[30] = "ArmorStand";
/*     */     
/*  41 */     arrayOfString[41] = "Boat";
/*     */     
/*  43 */     arrayOfString[42] = "MinecartRideable";
/*  44 */     arrayOfString[43] = "MinecartChest";
/*  45 */     arrayOfString[44] = "MinecartFurnace";
/*  46 */     arrayOfString[45] = "MinecartTNT";
/*  47 */     arrayOfString[46] = "MinecartHopper";
/*  48 */     arrayOfString[47] = "MinecartSpawner";
/*  49 */     arrayOfString[40] = "MinecartCommandBlock";
/*     */     
/*  51 */     arrayOfString[48] = "Mob";
/*  52 */     arrayOfString[49] = "Monster";
/*     */     
/*  54 */     arrayOfString[50] = "Creeper";
/*  55 */     arrayOfString[51] = "Skeleton";
/*  56 */     arrayOfString[52] = "Spider";
/*  57 */     arrayOfString[53] = "Giant";
/*  58 */     arrayOfString[54] = "Zombie";
/*  59 */     arrayOfString[55] = "Slime";
/*  60 */     arrayOfString[56] = "Ghast";
/*  61 */     arrayOfString[57] = "PigZombie";
/*  62 */     arrayOfString[58] = "Enderman";
/*  63 */     arrayOfString[59] = "CaveSpider";
/*  64 */     arrayOfString[60] = "Silverfish";
/*  65 */     arrayOfString[61] = "Blaze";
/*  66 */     arrayOfString[62] = "LavaSlime";
/*  67 */     arrayOfString[63] = "EnderDragon";
/*  68 */     arrayOfString[64] = "WitherBoss";
/*  69 */     arrayOfString[65] = "Bat";
/*  70 */     arrayOfString[66] = "Witch";
/*  71 */     arrayOfString[67] = "Endermite";
/*  72 */     arrayOfString[68] = "Guardian";
/*  73 */     arrayOfString[69] = "Shulker";
/*     */     
/*  75 */     arrayOfString[90] = "Pig";
/*  76 */     arrayOfString[91] = "Sheep";
/*  77 */     arrayOfString[92] = "Cow";
/*  78 */     arrayOfString[93] = "Chicken";
/*  79 */     arrayOfString[94] = "Squid";
/*  80 */     arrayOfString[95] = "Wolf";
/*  81 */     arrayOfString[96] = "MushroomCow";
/*  82 */     arrayOfString[97] = "SnowMan";
/*  83 */     arrayOfString[98] = "Ozelot";
/*  84 */     arrayOfString[99] = "VillagerGolem";
/*  85 */     arrayOfString[100] = "EntityHorse";
/*  86 */     arrayOfString[101] = "Rabbit";
/*     */     
/*  88 */     arrayOfString[120] = "Villager";
/*     */     
/*  90 */     arrayOfString[200] = "EnderCrystal";
/*     */   }
/*     */ 
/*     */   
/*     */   public int a() {
/*  95 */     return 105;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 100 */     if ("minecraft:spawn_egg".equals(paramNBTTagCompound.getString("id"))) {
/* 101 */       NBTTagCompound nBTTagCompound1 = paramNBTTagCompound.getCompound("tag");
/* 102 */       NBTTagCompound nBTTagCompound2 = nBTTagCompound1.getCompound("EntityTag");
/* 103 */       short s = paramNBTTagCompound.getShort("Damage");
/*     */       
/* 105 */       if (!nBTTagCompound2.hasKeyOfType("id", 8)) {
/* 106 */         String str = a[s & 0xFF];
/* 107 */         if (str != null) {
/* 108 */           nBTTagCompound2.setString("id", str);
/* 109 */           nBTTagCompound1.set("EntityTag", nBTTagCompound2);
/* 110 */           paramNBTTagCompound.set("tag", nBTTagCompound1);
/*     */         } 
/*     */       } 
/*     */       
/* 114 */       if (s != 0) {
/* 115 */         paramNBTTagCompound.setShort("Damage", (short)0);
/*     */       }
/*     */     } 
/*     */     
/* 119 */     return paramNBTTagCompound;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterSpawnEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */