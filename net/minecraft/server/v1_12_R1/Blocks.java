/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Blocks
/*     */ {
/*     */   static {
/*  15 */     if (!DispenserRegistry.a()) {
/*  16 */       throw new RuntimeException("Accessed Blocks before Bootstrap!");
/*     */     }
/*     */   }
/*     */   
/*  20 */   private static final Set<Block> dU = Sets.newHashSet();
/*     */   
/*  22 */   public static final Block AIR = get("air");
/*  23 */   public static final Block STONE = get("stone");
/*  24 */   public static final BlockGrass GRASS = (BlockGrass)get("grass");
/*  25 */   public static final Block DIRT = get("dirt");
/*  26 */   public static final Block COBBLESTONE = get("cobblestone");
/*  27 */   public static final Block PLANKS = get("planks");
/*  28 */   public static final Block SAPLING = get("sapling");
/*  29 */   public static final Block BEDROCK = get("bedrock");
/*  30 */   public static final BlockFlowing FLOWING_WATER = (BlockFlowing)get("flowing_water");
/*  31 */   public static final BlockStationary WATER = (BlockStationary)get("water");
/*  32 */   public static final BlockFlowing FLOWING_LAVA = (BlockFlowing)get("flowing_lava");
/*  33 */   public static final BlockStationary LAVA = (BlockStationary)get("lava");
/*  34 */   public static final BlockSand SAND = (BlockSand)get("sand");
/*  35 */   public static final Block GRAVEL = get("gravel");
/*  36 */   public static final Block GOLD_ORE = get("gold_ore");
/*  37 */   public static final Block IRON_ORE = get("iron_ore");
/*  38 */   public static final Block COAL_ORE = get("coal_ore");
/*  39 */   public static final Block LOG = get("log");
/*  40 */   public static final Block LOG2 = get("log2");
/*  41 */   public static final BlockLeaves LEAVES = (BlockLeaves)get("leaves");
/*  42 */   public static final BlockLeaves LEAVES2 = (BlockLeaves)get("leaves2");
/*  43 */   public static final Block SPONGE = get("sponge");
/*  44 */   public static final Block GLASS = get("glass");
/*  45 */   public static final Block LAPIS_ORE = get("lapis_ore");
/*  46 */   public static final Block LAPIS_BLOCK = get("lapis_block");
/*  47 */   public static final Block DISPENSER = get("dispenser");
/*  48 */   public static final Block SANDSTONE = get("sandstone");
/*  49 */   public static final Block NOTEBLOCK = get("noteblock");
/*  50 */   public static final Block BED = get("bed");
/*  51 */   public static final Block GOLDEN_RAIL = get("golden_rail");
/*  52 */   public static final Block DETECTOR_RAIL = get("detector_rail");
/*  53 */   public static final BlockPiston STICKY_PISTON = (BlockPiston)get("sticky_piston");
/*  54 */   public static final Block WEB = get("web");
/*  55 */   public static final BlockLongGrass TALLGRASS = (BlockLongGrass)get("tallgrass");
/*  56 */   public static final BlockDeadBush DEADBUSH = (BlockDeadBush)get("deadbush");
/*  57 */   public static final BlockPiston PISTON = (BlockPiston)get("piston");
/*  58 */   public static final BlockPistonExtension PISTON_HEAD = (BlockPistonExtension)get("piston_head");
/*  59 */   public static final Block WOOL = get("wool");
/*  60 */   public static final BlockPistonMoving PISTON_EXTENSION = (BlockPistonMoving)get("piston_extension");
/*  61 */   public static final BlockFlowers YELLOW_FLOWER = (BlockFlowers)get("yellow_flower");
/*  62 */   public static final BlockFlowers RED_FLOWER = (BlockFlowers)get("red_flower");
/*  63 */   public static final BlockPlant BROWN_MUSHROOM = (BlockPlant)get("brown_mushroom");
/*  64 */   public static final BlockPlant RED_MUSHROOM = (BlockPlant)get("red_mushroom");
/*  65 */   public static final Block GOLD_BLOCK = get("gold_block");
/*  66 */   public static final Block IRON_BLOCK = get("iron_block");
/*  67 */   public static final BlockStepAbstract DOUBLE_STONE_SLAB = (BlockStepAbstract)get("double_stone_slab");
/*  68 */   public static final BlockStepAbstract STONE_SLAB = (BlockStepAbstract)get("stone_slab");
/*  69 */   public static final Block BRICK_BLOCK = get("brick_block");
/*  70 */   public static final Block TNT = get("tnt");
/*  71 */   public static final Block BOOKSHELF = get("bookshelf");
/*  72 */   public static final Block MOSSY_COBBLESTONE = get("mossy_cobblestone");
/*  73 */   public static final Block OBSIDIAN = get("obsidian");
/*  74 */   public static final Block TORCH = get("torch");
/*  75 */   public static final BlockFire FIRE = (BlockFire)get("fire");
/*  76 */   public static final Block MOB_SPAWNER = get("mob_spawner");
/*  77 */   public static final Block OAK_STAIRS = get("oak_stairs");
/*  78 */   public static final BlockChest CHEST = (BlockChest)get("chest");
/*  79 */   public static final BlockRedstoneWire REDSTONE_WIRE = (BlockRedstoneWire)get("redstone_wire");
/*  80 */   public static final Block DIAMOND_ORE = get("diamond_ore");
/*  81 */   public static final Block DIAMOND_BLOCK = get("diamond_block");
/*  82 */   public static final Block CRAFTING_TABLE = get("crafting_table");
/*  83 */   public static final Block WHEAT = get("wheat");
/*  84 */   public static final Block FARMLAND = get("farmland");
/*  85 */   public static final Block FURNACE = get("furnace");
/*  86 */   public static final Block LIT_FURNACE = get("lit_furnace");
/*  87 */   public static final Block STANDING_SIGN = get("standing_sign");
/*  88 */   public static final BlockDoor WOODEN_DOOR = (BlockDoor)get("wooden_door");
/*  89 */   public static final BlockDoor SPRUCE_DOOR = (BlockDoor)get("spruce_door");
/*  90 */   public static final BlockDoor BIRCH_DOOR = (BlockDoor)get("birch_door");
/*  91 */   public static final BlockDoor JUNGLE_DOOR = (BlockDoor)get("jungle_door");
/*  92 */   public static final BlockDoor ACACIA_DOOR = (BlockDoor)get("acacia_door");
/*  93 */   public static final BlockDoor DARK_OAK_DOOR = (BlockDoor)get("dark_oak_door");
/*  94 */   public static final Block LADDER = get("ladder");
/*  95 */   public static final Block RAIL = get("rail");
/*  96 */   public static final Block STONE_STAIRS = get("stone_stairs");
/*  97 */   public static final Block WALL_SIGN = get("wall_sign");
/*  98 */   public static final Block LEVER = get("lever");
/*  99 */   public static final Block STONE_PRESSURE_PLATE = get("stone_pressure_plate");
/* 100 */   public static final BlockDoor IRON_DOOR = (BlockDoor)get("iron_door");
/* 101 */   public static final Block WOODEN_PRESSURE_PLATE = get("wooden_pressure_plate");
/* 102 */   public static final Block REDSTONE_ORE = get("redstone_ore");
/* 103 */   public static final Block LIT_REDSTONE_ORE = get("lit_redstone_ore");
/* 104 */   public static final Block UNLIT_REDSTONE_TORCH = get("unlit_redstone_torch");
/* 105 */   public static final Block REDSTONE_TORCH = get("redstone_torch");
/* 106 */   public static final Block STONE_BUTTON = get("stone_button");
/* 107 */   public static final Block SNOW_LAYER = get("snow_layer");
/* 108 */   public static final Block ICE = get("ice");
/* 109 */   public static final Block SNOW = get("snow");
/* 110 */   public static final BlockCactus CACTUS = (BlockCactus)get("cactus");
/* 111 */   public static final Block CLAY = get("clay");
/* 112 */   public static final BlockReed REEDS = (BlockReed)get("reeds");
/* 113 */   public static final Block JUKEBOX = get("jukebox");
/* 114 */   public static final Block FENCE = get("fence");
/* 115 */   public static final Block SPRUCE_FENCE = get("spruce_fence");
/* 116 */   public static final Block BIRCH_FENCE = get("birch_fence");
/* 117 */   public static final Block JUNGLE_FENCE = get("jungle_fence");
/* 118 */   public static final Block DARK_OAK_FENCE = get("dark_oak_fence");
/* 119 */   public static final Block ACACIA_FENCE = get("acacia_fence");
/* 120 */   public static final Block PUMPKIN = get("pumpkin");
/* 121 */   public static final Block NETHERRACK = get("netherrack");
/* 122 */   public static final Block SOUL_SAND = get("soul_sand");
/* 123 */   public static final Block GLOWSTONE = get("glowstone");
/* 124 */   public static final BlockPortal PORTAL = (BlockPortal)get("portal");
/* 125 */   public static final Block LIT_PUMPKIN = get("lit_pumpkin");
/* 126 */   public static final Block CAKE = get("cake");
/* 127 */   public static final BlockRepeater UNPOWERED_REPEATER = (BlockRepeater)get("unpowered_repeater");
/* 128 */   public static final BlockRepeater POWERED_REPEATER = (BlockRepeater)get("powered_repeater");
/* 129 */   public static final Block TRAPDOOR = get("trapdoor");
/* 130 */   public static final Block MONSTER_EGG = get("monster_egg");
/* 131 */   public static final Block STONEBRICK = get("stonebrick");
/* 132 */   public static final Block BROWN_MUSHROOM_BLOCK = get("brown_mushroom_block");
/* 133 */   public static final Block RED_MUSHROOM_BLOCK = get("red_mushroom_block");
/* 134 */   public static final Block IRON_BARS = get("iron_bars");
/* 135 */   public static final Block GLASS_PANE = get("glass_pane");
/* 136 */   public static final Block MELON_BLOCK = get("melon_block");
/* 137 */   public static final Block PUMPKIN_STEM = get("pumpkin_stem");
/* 138 */   public static final Block MELON_STEM = get("melon_stem");
/* 139 */   public static final Block VINE = get("vine");
/* 140 */   public static final Block FENCE_GATE = get("fence_gate");
/* 141 */   public static final Block SPRUCE_FENCE_GATE = get("spruce_fence_gate");
/* 142 */   public static final Block BIRCH_FENCE_GATE = get("birch_fence_gate");
/* 143 */   public static final Block JUNGLE_FENCE_GATE = get("jungle_fence_gate");
/* 144 */   public static final Block DARK_OAK_FENCE_GATE = get("dark_oak_fence_gate");
/* 145 */   public static final Block ACACIA_FENCE_GATE = get("acacia_fence_gate");
/* 146 */   public static final Block BRICK_STAIRS = get("brick_stairs");
/* 147 */   public static final Block STONE_BRICK_STAIRS = get("stone_brick_stairs");
/* 148 */   public static final BlockMycel MYCELIUM = (BlockMycel)get("mycelium");
/* 149 */   public static final Block WATERLILY = get("waterlily");
/* 150 */   public static final Block NETHER_BRICK = get("nether_brick");
/* 151 */   public static final Block NETHER_BRICK_FENCE = get("nether_brick_fence");
/* 152 */   public static final Block NETHER_BRICK_STAIRS = get("nether_brick_stairs");
/* 153 */   public static final Block NETHER_WART = get("nether_wart");
/* 154 */   public static final Block ENCHANTING_TABLE = get("enchanting_table");
/* 155 */   public static final Block BREWING_STAND = get("brewing_stand");
/* 156 */   public static final BlockCauldron cauldron = (BlockCauldron)get("cauldron");
/* 157 */   public static final Block END_PORTAL = get("end_portal");
/* 158 */   public static final Block END_PORTAL_FRAME = get("end_portal_frame");
/* 159 */   public static final Block END_STONE = get("end_stone");
/* 160 */   public static final Block DRAGON_EGG = get("dragon_egg");
/* 161 */   public static final Block REDSTONE_LAMP = get("redstone_lamp");
/* 162 */   public static final Block LIT_REDSTONE_LAMP = get("lit_redstone_lamp");
/* 163 */   public static final BlockStepAbstract DOUBLE_WOODEN_SLAB = (BlockStepAbstract)get("double_wooden_slab");
/* 164 */   public static final BlockStepAbstract WOODEN_SLAB = (BlockStepAbstract)get("wooden_slab");
/* 165 */   public static final Block COCOA = get("cocoa");
/* 166 */   public static final Block SANDSTONE_STAIRS = get("sandstone_stairs");
/* 167 */   public static final Block EMERALD_ORE = get("emerald_ore");
/* 168 */   public static final Block ENDER_CHEST = get("ender_chest");
/* 169 */   public static final BlockTripwireHook TRIPWIRE_HOOK = (BlockTripwireHook)get("tripwire_hook");
/* 170 */   public static final Block TRIPWIRE = get("tripwire");
/* 171 */   public static final Block EMERALD_BLOCK = get("emerald_block");
/* 172 */   public static final Block SPRUCE_STAIRS = get("spruce_stairs");
/* 173 */   public static final Block BIRCH_STAIRS = get("birch_stairs");
/* 174 */   public static final Block JUNGLE_STAIRS = get("jungle_stairs");
/* 175 */   public static final Block COMMAND_BLOCK = get("command_block");
/* 176 */   public static final BlockBeacon BEACON = (BlockBeacon)get("beacon");
/* 177 */   public static final Block COBBLESTONE_WALL = get("cobblestone_wall");
/* 178 */   public static final Block FLOWER_POT = get("flower_pot");
/* 179 */   public static final Block CARROTS = get("carrots");
/* 180 */   public static final Block POTATOES = get("potatoes");
/* 181 */   public static final Block WOODEN_BUTTON = get("wooden_button");
/* 182 */   public static final BlockSkull SKULL = (BlockSkull)get("skull");
/* 183 */   public static final Block ANVIL = get("anvil");
/* 184 */   public static final Block TRAPPED_CHEST = get("trapped_chest");
/* 185 */   public static final Block LIGHT_WEIGHTED_PRESSURE_PLATE = get("light_weighted_pressure_plate");
/* 186 */   public static final Block HEAVY_WEIGHTED_PRESSURE_PLATE = get("heavy_weighted_pressure_plate");
/* 187 */   public static final BlockRedstoneComparator UNPOWERED_COMPARATOR = (BlockRedstoneComparator)get("unpowered_comparator");
/* 188 */   public static final BlockRedstoneComparator POWERED_COMPARATOR = (BlockRedstoneComparator)get("powered_comparator");
/* 189 */   public static final BlockDaylightDetector DAYLIGHT_DETECTOR = (BlockDaylightDetector)get("daylight_detector");
/* 190 */   public static final BlockDaylightDetector DAYLIGHT_DETECTOR_INVERTED = (BlockDaylightDetector)get("daylight_detector_inverted");
/* 191 */   public static final Block REDSTONE_BLOCK = get("redstone_block");
/* 192 */   public static final Block QUARTZ_ORE = get("quartz_ore");
/* 193 */   public static final BlockHopper HOPPER = (BlockHopper)get("hopper");
/* 194 */   public static final Block QUARTZ_BLOCK = get("quartz_block");
/* 195 */   public static final Block QUARTZ_STAIRS = get("quartz_stairs");
/* 196 */   public static final Block ACTIVATOR_RAIL = get("activator_rail");
/* 197 */   public static final Block DROPPER = get("dropper");
/* 198 */   public static final Block STAINED_HARDENED_CLAY = get("stained_hardened_clay");
/* 199 */   public static final Block BARRIER = get("barrier");
/* 200 */   public static final Block IRON_TRAPDOOR = get("iron_trapdoor");
/* 201 */   public static final Block HAY_BLOCK = get("hay_block");
/* 202 */   public static final Block CARPET = get("carpet");
/* 203 */   public static final Block HARDENED_CLAY = get("hardened_clay");
/* 204 */   public static final Block COAL_BLOCK = get("coal_block");
/* 205 */   public static final Block PACKED_ICE = get("packed_ice");
/* 206 */   public static final Block ACACIA_STAIRS = get("acacia_stairs");
/* 207 */   public static final Block DARK_OAK_STAIRS = get("dark_oak_stairs");
/* 208 */   public static final Block SLIME = get("slime");
/* 209 */   public static final BlockTallPlant DOUBLE_PLANT = (BlockTallPlant)get("double_plant");
/* 210 */   public static final BlockStainedGlass STAINED_GLASS = (BlockStainedGlass)get("stained_glass");
/* 211 */   public static final BlockStainedGlassPane STAINED_GLASS_PANE = (BlockStainedGlassPane)get("stained_glass_pane");
/* 212 */   public static final Block PRISMARINE = get("prismarine");
/* 213 */   public static final Block SEA_LANTERN = get("sea_lantern");
/* 214 */   public static final Block STANDING_BANNER = get("standing_banner");
/* 215 */   public static final Block WALL_BANNER = get("wall_banner");
/* 216 */   public static final Block RED_SANDSTONE = get("red_sandstone");
/* 217 */   public static final Block RED_SANDSTONE_STAIRS = get("red_sandstone_stairs");
/* 218 */   public static final BlockStepAbstract DOUBLE_STONE_SLAB2 = (BlockStepAbstract)get("double_stone_slab2");
/* 219 */   public static final BlockStepAbstract STONE_SLAB2 = (BlockStepAbstract)get("stone_slab2");
/* 220 */   public static final Block END_ROD = get("end_rod");
/* 221 */   public static final Block CHORUS_PLANT = get("chorus_plant");
/* 222 */   public static final Block CHORUS_FLOWER = get("chorus_flower");
/* 223 */   public static final Block PURPUR_BLOCK = get("purpur_block");
/* 224 */   public static final Block PURPUR_PILLAR = get("purpur_pillar");
/* 225 */   public static final Block PURPUR_STAIRS = get("purpur_stairs");
/* 226 */   public static final BlockStepAbstract PURPUR_DOUBLE_SLAB = (BlockStepAbstract)get("purpur_double_slab");
/* 227 */   public static final BlockStepAbstract PURPUR_SLAB = (BlockStepAbstract)get("purpur_slab");
/* 228 */   public static final Block END_BRICKS = get("end_bricks");
/* 229 */   public static final Block BEETROOT = get("beetroots");
/* 230 */   public static final Block GRASS_PATH = get("grass_path");
/* 231 */   public static final Block END_GATEWAY = get("end_gateway");
/* 232 */   public static final Block dc = get("repeating_command_block");
/* 233 */   public static final Block dd = get("chain_command_block");
/* 234 */   public static final Block FROSTED_ICE = get("frosted_ice");
/* 235 */   public static final Block df = get("magma");
/* 236 */   public static final Block dg = get("nether_wart_block");
/* 237 */   public static final Block dh = get("red_nether_brick");
/* 238 */   public static final Block di = get("bone_block");
/* 239 */   public static final Block dj = get("structure_void");
/* 240 */   public static final Block dk = get("observer");
/* 241 */   public static final Block WHITE_SHULKER_BOX = get("white_shulker_box");
/* 242 */   public static final Block dm = get("orange_shulker_box");
/* 243 */   public static final Block dn = get("magenta_shulker_box");
/* 244 */   public static final Block LIGHT_BLUE_SHULKER_BOX = get("light_blue_shulker_box");
/* 245 */   public static final Block dp = get("yellow_shulker_box");
/* 246 */   public static final Block dq = get("lime_shulker_box");
/* 247 */   public static final Block dr = get("pink_shulker_box");
/* 248 */   public static final Block ds = get("gray_shulker_box");
/* 249 */   public static final Block dt = get("silver_shulker_box");
/* 250 */   public static final Block du = get("cyan_shulker_box");
/* 251 */   public static final Block dv = get("purple_shulker_box");
/* 252 */   public static final Block dw = get("blue_shulker_box");
/* 253 */   public static final Block dx = get("brown_shulker_box");
/* 254 */   public static final Block dy = get("green_shulker_box");
/* 255 */   public static final Block dz = get("red_shulker_box");
/* 256 */   public static final Block dA = get("black_shulker_box");
/* 257 */   public static final Block dB = get("white_glazed_terracotta");
/* 258 */   public static final Block dC = get("orange_glazed_terracotta");
/* 259 */   public static final Block dD = get("magenta_glazed_terracotta");
/* 260 */   public static final Block dE = get("light_blue_glazed_terracotta");
/* 261 */   public static final Block dF = get("yellow_glazed_terracotta");
/* 262 */   public static final Block dG = get("lime_glazed_terracotta");
/* 263 */   public static final Block dH = get("pink_glazed_terracotta");
/* 264 */   public static final Block dI = get("gray_glazed_terracotta");
/* 265 */   public static final Block dJ = get("silver_glazed_terracotta");
/* 266 */   public static final Block dK = get("cyan_glazed_terracotta");
/* 267 */   public static final Block dL = get("purple_glazed_terracotta");
/* 268 */   public static final Block dM = get("blue_glazed_terracotta");
/* 269 */   public static final Block dN = get("brown_glazed_terracotta");
/* 270 */   public static final Block dO = get("green_glazed_terracotta");
/* 271 */   public static final Block dP = get("red_glazed_terracotta");
/* 272 */   public static final Block dQ = get("black_glazed_terracotta");
/* 273 */   public static final Block dR = get("concrete");
/* 274 */   public static final Block dS = get("concrete_powder");
/*     */   
/* 276 */   public static final Block STRUCTURE_BLOCK = get("structure_block");
/*     */   
/*     */   @Nullable
/*     */   private static Block get(String paramString) {
/* 280 */     Block block = Block.REGISTRY.get(new MinecraftKey(paramString));
/* 281 */     if (!dU.add(block)) {
/* 282 */       throw new IllegalStateException("Invalid Block requested: " + paramString);
/*     */     }
/* 284 */     return block;
/*     */   }
/*     */   
/*     */   static {
/* 288 */     dU.clear();
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\Blocks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */