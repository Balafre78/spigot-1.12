/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Items
/*     */ {
/*     */   static {
/*   8 */     if (!DispenserRegistry.a()) {
/*   9 */       throw new RuntimeException("Accessed Items before Bootstrap!");
/*     */     }
/*     */   }
/*     */   
/*  13 */   public static final Item a = get("air");
/*     */   
/*  15 */   public static final Item IRON_SHOVEL = get("iron_shovel");
/*  16 */   public static final Item IRON_PICKAXE = get("iron_pickaxe");
/*  17 */   public static final Item IRON_AXE = get("iron_axe");
/*  18 */   public static final Item FLINT_AND_STEEL = get("flint_and_steel");
/*  19 */   public static final Item APPLE = get("apple");
/*  20 */   public static final ItemBow BOW = (ItemBow)get("bow");
/*  21 */   public static final Item ARROW = get("arrow");
/*  22 */   public static final Item SPECTRAL_ARROW = get("spectral_arrow");
/*  23 */   public static final Item TIPPED_ARROW = get("tipped_arrow");
/*  24 */   public static final Item COAL = get("coal");
/*  25 */   public static final Item DIAMOND = get("diamond");
/*  26 */   public static final Item IRON_INGOT = get("iron_ingot");
/*  27 */   public static final Item GOLD_INGOT = get("gold_ingot");
/*  28 */   public static final Item IRON_SWORD = get("iron_sword");
/*  29 */   public static final Item WOODEN_SWORD = get("wooden_sword");
/*  30 */   public static final Item WOODEN_SHOVEL = get("wooden_shovel");
/*  31 */   public static final Item WOODEN_PICKAXE = get("wooden_pickaxe");
/*  32 */   public static final Item WOODEN_AXE = get("wooden_axe");
/*  33 */   public static final Item STONE_SWORD = get("stone_sword");
/*  34 */   public static final Item STONE_SHOVEL = get("stone_shovel");
/*  35 */   public static final Item STONE_PICKAXE = get("stone_pickaxe");
/*  36 */   public static final Item STONE_AXE = get("stone_axe");
/*  37 */   public static final Item DIAMOND_SWORD = get("diamond_sword");
/*  38 */   public static final Item DIAMOND_SHOVEL = get("diamond_shovel");
/*  39 */   public static final Item DIAMOND_PICKAXE = get("diamond_pickaxe");
/*  40 */   public static final Item DIAMOND_AXE = get("diamond_axe");
/*  41 */   public static final Item STICK = get("stick");
/*  42 */   public static final Item BOWL = get("bowl");
/*  43 */   public static final Item MUSHROOM_STEW = get("mushroom_stew");
/*  44 */   public static final Item GOLDEN_SWORD = get("golden_sword");
/*  45 */   public static final Item GOLDEN_SHOVEL = get("golden_shovel");
/*  46 */   public static final Item GOLDEN_PICKAXE = get("golden_pickaxe");
/*  47 */   public static final Item GOLDEN_AXE = get("golden_axe");
/*  48 */   public static final Item STRING = get("string");
/*  49 */   public static final Item FEATHER = get("feather");
/*  50 */   public static final Item GUNPOWDER = get("gunpowder");
/*  51 */   public static final Item WOODEN_HOE = get("wooden_hoe");
/*  52 */   public static final Item STONE_HOE = get("stone_hoe");
/*  53 */   public static final Item IRON_HOE = get("iron_hoe");
/*  54 */   public static final Item DIAMOND_HOE = get("diamond_hoe");
/*  55 */   public static final Item GOLDEN_HOE = get("golden_hoe");
/*  56 */   public static final Item WHEAT_SEEDS = get("wheat_seeds");
/*  57 */   public static final Item WHEAT = get("wheat");
/*  58 */   public static final Item BREAD = get("bread");
/*  59 */   public static final ItemArmor LEATHER_HELMET = (ItemArmor)get("leather_helmet");
/*  60 */   public static final ItemArmor LEATHER_CHESTPLATE = (ItemArmor)get("leather_chestplate");
/*  61 */   public static final ItemArmor LEATHER_LEGGINGS = (ItemArmor)get("leather_leggings");
/*  62 */   public static final ItemArmor LEATHER_BOOTS = (ItemArmor)get("leather_boots");
/*  63 */   public static final ItemArmor CHAINMAIL_HELMET = (ItemArmor)get("chainmail_helmet");
/*  64 */   public static final ItemArmor CHAINMAIL_CHESTPLATE = (ItemArmor)get("chainmail_chestplate");
/*  65 */   public static final ItemArmor CHAINMAIL_LEGGINGS = (ItemArmor)get("chainmail_leggings");
/*  66 */   public static final ItemArmor CHAINMAIL_BOOTS = (ItemArmor)get("chainmail_boots");
/*  67 */   public static final ItemArmor IRON_HELMET = (ItemArmor)get("iron_helmet");
/*  68 */   public static final ItemArmor IRON_CHESTPLATE = (ItemArmor)get("iron_chestplate");
/*  69 */   public static final ItemArmor IRON_LEGGINGS = (ItemArmor)get("iron_leggings");
/*  70 */   public static final ItemArmor IRON_BOOTS = (ItemArmor)get("iron_boots");
/*  71 */   public static final ItemArmor DIAMOND_HELMET = (ItemArmor)get("diamond_helmet");
/*  72 */   public static final ItemArmor DIAMOND_CHESTPLATE = (ItemArmor)get("diamond_chestplate");
/*  73 */   public static final ItemArmor DIAMOND_LEGGINGS = (ItemArmor)get("diamond_leggings");
/*  74 */   public static final ItemArmor DIAMOND_BOOTS = (ItemArmor)get("diamond_boots");
/*  75 */   public static final ItemArmor GOLDEN_HELMET = (ItemArmor)get("golden_helmet");
/*  76 */   public static final ItemArmor GOLDEN_CHESTPLATE = (ItemArmor)get("golden_chestplate");
/*  77 */   public static final ItemArmor GOLDEN_LEGGINGS = (ItemArmor)get("golden_leggings");
/*  78 */   public static final ItemArmor GOLDEN_BOOTS = (ItemArmor)get("golden_boots");
/*  79 */   public static final Item FLINT = get("flint");
/*  80 */   public static final Item PORKCHOP = get("porkchop");
/*  81 */   public static final Item COOKED_PORKCHOP = get("cooked_porkchop");
/*  82 */   public static final Item PAINTING = get("painting");
/*  83 */   public static final Item GOLDEN_APPLE = get("golden_apple");
/*  84 */   public static final Item SIGN = get("sign");
/*  85 */   public static final Item WOODEN_DOOR = get("wooden_door");
/*  86 */   public static final Item SPRUCE_DOOR = get("spruce_door");
/*  87 */   public static final Item BIRCH_DOOR = get("birch_door");
/*  88 */   public static final Item JUNGLE_DOOR = get("jungle_door");
/*  89 */   public static final Item ACACIA_DOOR = get("acacia_door");
/*  90 */   public static final Item DARK_OAK_DOOR = get("dark_oak_door");
/*  91 */   public static final Item BUCKET = get("bucket");
/*  92 */   public static final Item WATER_BUCKET = get("water_bucket");
/*  93 */   public static final Item LAVA_BUCKET = get("lava_bucket");
/*  94 */   public static final Item MINECART = get("minecart");
/*  95 */   public static final Item SADDLE = get("saddle");
/*  96 */   public static final Item IRON_DOOR = get("iron_door");
/*  97 */   public static final Item REDSTONE = get("redstone");
/*  98 */   public static final Item SNOWBALL = get("snowball");
/*  99 */   public static final Item aH = get("boat");
/* 100 */   public static final Item aI = get("spruce_boat");
/* 101 */   public static final Item aJ = get("birch_boat");
/* 102 */   public static final Item aK = get("jungle_boat");
/* 103 */   public static final Item aL = get("acacia_boat");
/* 104 */   public static final Item aM = get("dark_oak_boat");
/* 105 */   public static final Item LEATHER = get("leather");
/* 106 */   public static final Item MILK_BUCKET = get("milk_bucket");
/* 107 */   public static final Item BRICK = get("brick");
/* 108 */   public static final Item CLAY_BALL = get("clay_ball");
/* 109 */   public static final Item REEDS = get("reeds");
/* 110 */   public static final Item PAPER = get("paper");
/* 111 */   public static final Item BOOK = get("book");
/* 112 */   public static final Item SLIME = get("slime_ball");
/* 113 */   public static final Item CHEST_MINECART = get("chest_minecart");
/* 114 */   public static final Item FURNACE_MINECART = get("furnace_minecart");
/* 115 */   public static final Item EGG = get("egg");
/* 116 */   public static final Item COMPASS = get("compass");
/* 117 */   public static final ItemFishingRod FISHING_ROD = (ItemFishingRod)get("fishing_rod");
/* 118 */   public static final Item CLOCK = get("clock");
/* 119 */   public static final Item GLOWSTONE_DUST = get("glowstone_dust");
/* 120 */   public static final Item FISH = get("fish");
/* 121 */   public static final Item COOKED_FISH = get("cooked_fish");
/* 122 */   public static final Item DYE = get("dye");
/* 123 */   public static final Item BONE = get("bone");
/* 124 */   public static final Item SUGAR = get("sugar");
/* 125 */   public static final Item CAKE = get("cake");
/* 126 */   public static final Item BED = get("bed");
/* 127 */   public static final Item REPEATER = get("repeater");
/* 128 */   public static final Item COOKIE = get("cookie");
/* 129 */   public static final ItemWorldMap FILLED_MAP = (ItemWorldMap)get("filled_map");
/* 130 */   public static final ItemShears SHEARS = (ItemShears)get("shears");
/* 131 */   public static final Item MELON = get("melon");
/* 132 */   public static final Item PUMPKIN_SEEDS = get("pumpkin_seeds");
/* 133 */   public static final Item MELON_SEEDS = get("melon_seeds");
/* 134 */   public static final Item BEEF = get("beef");
/* 135 */   public static final Item COOKED_BEEF = get("cooked_beef");
/* 136 */   public static final Item CHICKEN = get("chicken");
/* 137 */   public static final Item COOKED_CHICKEN = get("cooked_chicken");
/* 138 */   public static final Item MUTTON = get("mutton");
/* 139 */   public static final Item COOKED_MUTTON = get("cooked_mutton");
/* 140 */   public static final Item RABBIT = get("rabbit");
/* 141 */   public static final Item COOKED_RABBIT = get("cooked_rabbit");
/* 142 */   public static final Item RABBIT_STEW = get("rabbit_stew");
/* 143 */   public static final Item RABBIT_FOOT = get("rabbit_foot");
/* 144 */   public static final Item RABBIT_HIDE = get("rabbit_hide");
/* 145 */   public static final Item ROTTEN_FLESH = get("rotten_flesh");
/* 146 */   public static final Item ENDER_PEARL = get("ender_pearl");
/* 147 */   public static final Item BLAZE_ROD = get("blaze_rod");
/* 148 */   public static final Item GHAST_TEAR = get("ghast_tear");
/* 149 */   public static final Item GOLD_NUGGET = get("gold_nugget");
/* 150 */   public static final Item NETHER_WART = get("nether_wart");
/* 151 */   public static final ItemPotion POTION = (ItemPotion)get("potion");
/* 152 */   public static final ItemPotion SPLASH_POTION = (ItemPotion)get("splash_potion");
/* 153 */   public static final ItemPotion LINGERING_POTION = (ItemPotion)get("lingering_potion");
/* 154 */   public static final Item GLASS_BOTTLE = get("glass_bottle");
/* 155 */   public static final Item DRAGON_BREATH = get("dragon_breath");
/* 156 */   public static final Item SPIDER_EYE = get("spider_eye");
/* 157 */   public static final Item FERMENTED_SPIDER_EYE = get("fermented_spider_eye");
/* 158 */   public static final Item BLAZE_POWDER = get("blaze_powder");
/* 159 */   public static final Item MAGMA_CREAM = get("magma_cream");
/* 160 */   public static final Item BREWING_STAND = get("brewing_stand");
/* 161 */   public static final Item CAULDRON = get("cauldron");
/* 162 */   public static final Item ENDER_EYE = get("ender_eye");
/* 163 */   public static final Item SPECKLED_MELON = get("speckled_melon");
/* 164 */   public static final Item SPAWN_EGG = get("spawn_egg");
/* 165 */   public static final Item EXPERIENCE_BOTTLE = get("experience_bottle");
/* 166 */   public static final Item FIRE_CHARGE = get("fire_charge");
/* 167 */   public static final Item WRITABLE_BOOK = get("writable_book");
/* 168 */   public static final Item WRITTEN_BOOK = get("written_book");
/* 169 */   public static final Item EMERALD = get("emerald");
/* 170 */   public static final Item ITEM_FRAME = get("item_frame");
/* 171 */   public static final Item FLOWER_POT = get("flower_pot");
/* 172 */   public static final Item CARROT = get("carrot");
/* 173 */   public static final Item POTATO = get("potato");
/* 174 */   public static final Item BAKED_POTATO = get("baked_potato");
/* 175 */   public static final Item POISONOUS_POTATO = get("poisonous_potato");
/* 176 */   public static final ItemMapEmpty MAP = (ItemMapEmpty)get("map");
/* 177 */   public static final Item GOLDEN_CARROT = get("golden_carrot");
/* 178 */   public static final Item SKULL = get("skull");
/* 179 */   public static final Item CARROT_ON_A_STICK = get("carrot_on_a_stick");
/* 180 */   public static final Item NETHER_STAR = get("nether_star");
/* 181 */   public static final Item PUMPKIN_PIE = get("pumpkin_pie");
/* 182 */   public static final Item FIREWORKS = get("fireworks");
/* 183 */   public static final Item FIREWORK_CHARGE = get("firework_charge");
/* 184 */   public static final Item ENCHANTED_BOOK = get("enchanted_book");
/* 185 */   public static final Item COMPARATOR = get("comparator");
/* 186 */   public static final Item NETHERBRICK = get("netherbrick");
/* 187 */   public static final Item QUARTZ = get("quartz");
/* 188 */   public static final Item TNT_MINECART = get("tnt_minecart");
/* 189 */   public static final Item HOPPER_MINECART = get("hopper_minecart");
/* 190 */   public static final ItemArmorStand ARMOR_STAND = (ItemArmorStand)get("armor_stand");
/* 191 */   public static final Item IRON_HORSE_ARMOR = get("iron_horse_armor");
/* 192 */   public static final Item GOLDEN_HORSE_ARMOR = get("golden_horse_armor");
/* 193 */   public static final Item DIAMOND_HORSE_ARMOR = get("diamond_horse_armor");
/* 194 */   public static final Item LEAD = get("lead");
/* 195 */   public static final Item NAME_TAG = get("name_tag");
/* 196 */   public static final Item COMMAND_BLOCK_MINECART = get("command_block_minecart");
/* 197 */   public static final Item RECORD_13 = get("record_13");
/* 198 */   public static final Item RECORD_CAT = get("record_cat");
/* 199 */   public static final Item RECORD_BLOCKS = get("record_blocks");
/* 200 */   public static final Item RECORD_CHIRP = get("record_chirp");
/* 201 */   public static final Item RECORD_FAR = get("record_far");
/* 202 */   public static final Item RECORD_MALL = get("record_mall");
/* 203 */   public static final Item RECORD_MELLOHI = get("record_mellohi");
/* 204 */   public static final Item RECORD_STAL = get("record_stal");
/* 205 */   public static final Item RECORD_STRAD = get("record_strad");
/* 206 */   public static final Item RECORD_WARD = get("record_ward");
/* 207 */   public static final Item RECORD_11 = get("record_11");
/* 208 */   public static final Item RECORD_WAIT = get("record_wait");
/* 209 */   public static final Item PRISMARINE_SHARD = get("prismarine_shard");
/* 210 */   public static final Item PRISMARINE_CRYSTALS = get("prismarine_crystals");
/* 211 */   public static final Item BANNER = get("banner");
/* 212 */   public static final Item cQ = get("end_crystal");
/* 213 */   public static final Item SHIELD = get("shield");
/* 214 */   public static final Item cS = get("elytra");
/* 215 */   public static final Item CHORUS_FRUIT = get("chorus_fruit");
/* 216 */   public static final Item CHORUS_FRUIT_POPPED = get("chorus_fruit_popped");
/* 217 */   public static final Item BEETROOT_SEEDS = get("beetroot_seeds");
/* 218 */   public static final Item BEETROOT = get("beetroot");
/* 219 */   public static final Item BEETROOT_SOUP = get("beetroot_soup");
/* 220 */   public static final Item cY = get("totem_of_undying");
/* 221 */   public static final Item cZ = get("shulker_shell");
/* 222 */   public static final Item da = get("iron_nugget");
/* 223 */   public static final Item db = get("knowledge_book");
/*     */   
/*     */   private static Item get(String paramString) {
/* 226 */     Item item = Item.REGISTRY.get(new MinecraftKey(paramString));
/* 227 */     if (item == null) {
/* 228 */       throw new IllegalStateException("Invalid Item requested: " + paramString);
/*     */     }
/* 230 */     return item;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\Items.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */