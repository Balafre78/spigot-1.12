/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.HashMultiset;
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.Multiset;
/*     */ import com.google.common.collect.Multisets;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.server.MapInitializeEvent;
/*     */ import org.bukkit.map.MapView;
/*     */ 
/*     */ public class ItemWorldMap extends ItemWorldMapBase {
/*     */   protected ItemWorldMap() {
/*  16 */     a(true);
/*     */   }
/*     */   
/*     */   public static ItemStack a(World world, double d0, double d1, byte b0, boolean flag, boolean flag1) {
/*  20 */     World worldMain = (world.getServer().getServer()).worlds.get(0);
/*  21 */     ItemStack itemstack = new ItemStack(Items.FILLED_MAP, 1, worldMain.b("map"));
/*  22 */     String s = "map_" + itemstack.getData();
/*  23 */     WorldMap worldmap = new WorldMap(s);
/*     */     
/*  25 */     worldMain.a(s, worldmap);
/*  26 */     worldmap.scale = b0;
/*  27 */     worldmap.a(d0, d1, worldmap.scale);
/*  28 */     worldmap.map = (byte)((WorldServer)world).dimension;
/*  29 */     worldmap.track = flag;
/*  30 */     worldmap.unlimitedTracking = flag1;
/*  31 */     worldmap.c();
/*  32 */     CraftEventFactory.callEvent((Event)new MapInitializeEvent((MapView)worldmap.mapView));
/*  33 */     return itemstack;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public WorldMap getSavedMap(ItemStack itemstack, World world) {
/*  38 */     World worldMain = (world.getServer().getServer()).worlds.get(0);
/*  39 */     String s = "map_" + itemstack.getData();
/*  40 */     WorldMap worldmap = (WorldMap)worldMain.a((Class)WorldMap.class, s);
/*     */     
/*  42 */     if (worldmap == null && !world.isClientSide) {
/*  43 */       itemstack.setData(worldMain.b("map"));
/*  44 */       s = "map_" + itemstack.getData();
/*  45 */       worldmap = new WorldMap(s);
/*  46 */       worldmap.scale = 3;
/*  47 */       worldmap.a(world.getWorldData().b(), world.getWorldData().d(), worldmap.scale);
/*  48 */       worldmap.map = (byte)((WorldServer)world).dimension;
/*  49 */       worldmap.c();
/*  50 */       worldMain.a(s, worldmap);
/*     */ 
/*     */       
/*  53 */       MapInitializeEvent event = new MapInitializeEvent((MapView)worldmap.mapView);
/*  54 */       Bukkit.getServer().getPluginManager().callEvent((Event)event);
/*     */     } 
/*     */ 
/*     */     
/*  58 */     return worldmap;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World world, Entity entity, WorldMap worldmap) {
/*  63 */     if (((WorldServer)world).dimension == worldmap.map && entity instanceof EntityHuman) {
/*  64 */       int i = 1 << worldmap.scale;
/*  65 */       int j = worldmap.centerX;
/*  66 */       int k = worldmap.centerZ;
/*  67 */       int l = MathHelper.floor(entity.locX - j) / i + 64;
/*  68 */       int i1 = MathHelper.floor(entity.locZ - k) / i + 64;
/*  69 */       int j1 = 128 / i;
/*     */       
/*  71 */       if (world.worldProvider.n()) {
/*  72 */         j1 /= 2;
/*     */       }
/*     */       
/*  75 */       WorldMap.WorldMapHumanTracker worldmap_worldmaphumantracker = worldmap.a((EntityHuman)entity);
/*     */       
/*  77 */       worldmap_worldmaphumantracker.b++;
/*  78 */       boolean flag = false;
/*     */       
/*  80 */       for (int k1 = l - j1 + 1; k1 < l + j1; k1++) {
/*  81 */         if ((k1 & 0xF) == (worldmap_worldmaphumantracker.b & 0xF) || flag) {
/*  82 */           flag = false;
/*  83 */           double d0 = 0.0D;
/*     */           
/*  85 */           for (int l1 = i1 - j1 - 1; l1 < i1 + j1; l1++) {
/*  86 */             if (k1 >= 0 && l1 >= -1 && k1 < 128 && l1 < 128) {
/*  87 */               int i2 = k1 - l;
/*  88 */               int j2 = l1 - i1;
/*  89 */               boolean flag1 = (i2 * i2 + j2 * j2 > (j1 - 2) * (j1 - 2));
/*  90 */               int k2 = (j / i + k1 - 64) * i;
/*  91 */               int l2 = (k / i + l1 - 64) * i;
/*  92 */               HashMultiset hashmultiset = HashMultiset.create();
/*  93 */               Chunk chunk = world.getChunkAtWorldCoords(new BlockPosition(k2, 0, l2));
/*     */               
/*  95 */               if (!chunk.isEmpty()) {
/*  96 */                 int i3 = k2 & 0xF;
/*  97 */                 int j3 = l2 & 0xF;
/*  98 */                 int k3 = 0;
/*  99 */                 double d1 = 0.0D;
/*     */                 
/* 101 */                 if (world.worldProvider.n()) {
/* 102 */                   int l3 = k2 + l2 * 231871;
/*     */                   
/* 104 */                   l3 = l3 * l3 * 31287121 + l3 * 11;
/* 105 */                   if ((l3 >> 20 & 0x1) == 0) {
/* 106 */                     hashmultiset.add(Blocks.DIRT.getBlockData().<BlockDirt.EnumDirtVariant, BlockDirt.EnumDirtVariant>set(BlockDirt.VARIANT, BlockDirt.EnumDirtVariant.DIRT).a(world, BlockPosition.ZERO), 10);
/*     */                   } else {
/* 108 */                     hashmultiset.add(Blocks.STONE.getBlockData().<BlockStone.EnumStoneVariant, BlockStone.EnumStoneVariant>set(BlockStone.VARIANT, BlockStone.EnumStoneVariant.STONE).a(world, BlockPosition.ZERO), 100);
/*     */                   } 
/*     */                   
/* 111 */                   d1 = 100.0D;
/*     */                 } else {
/* 113 */                   BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/*     */                   
/* 115 */                   for (int i4 = 0; i4 < i; i4++) {
/* 116 */                     for (int j4 = 0; j4 < i; j4++) {
/* 117 */                       int k4 = chunk.b(i4 + i3, j4 + j3) + 1;
/* 118 */                       IBlockData iblockdata = Blocks.AIR.getBlockData();
/*     */                       
/* 120 */                       if (k4 > 1) {
/*     */                         do {
/* 122 */                           k4--;
/* 123 */                           iblockdata = chunk.a(i4 + i3, k4, j4 + j3);
/* 124 */                           blockposition_mutableblockposition.c((chunk.locX << 4) + i4 + i3, k4, (chunk.locZ << 4) + j4 + j3);
/* 125 */                         } while (iblockdata.a(world, blockposition_mutableblockposition) == MaterialMapColor.c && k4 > 0);
/*     */                         
/* 127 */                         if (k4 > 0 && iblockdata.getMaterial().isLiquid()) {
/* 128 */                           IBlockData iblockdata1; int l4 = k4 - 1;
/*     */ 
/*     */ 
/*     */                           
/*     */                           do {
/* 133 */                             iblockdata1 = chunk.a(i4 + i3, l4--, j4 + j3);
/* 134 */                             k3++;
/* 135 */                           } while (l4 > 0 && iblockdata1.getMaterial().isLiquid());
/*     */                         } 
/*     */                       } else {
/* 138 */                         iblockdata = Blocks.BEDROCK.getBlockData();
/*     */                       } 
/*     */                       
/* 141 */                       d1 += k4 / (i * i);
/* 142 */                       hashmultiset.add(iblockdata.a(world, blockposition_mutableblockposition));
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */                 
/* 147 */                 k3 /= i * i;
/* 148 */                 double d2 = (d1 - d0) * 4.0D / (i + 4) + ((k1 + l1 & 0x1) - 0.5D) * 0.4D;
/* 149 */                 byte b0 = 1;
/*     */                 
/* 151 */                 if (d2 > 0.6D) {
/* 152 */                   b0 = 2;
/*     */                 }
/*     */                 
/* 155 */                 if (d2 < -0.6D) {
/* 156 */                   b0 = 0;
/*     */                 }
/*     */                 
/* 159 */                 MaterialMapColor materialmapcolor = (MaterialMapColor)Iterables.getFirst((Iterable)Multisets.copyHighestCountFirst((Multiset)hashmultiset), MaterialMapColor.c);
/*     */                 
/* 161 */                 if (materialmapcolor == MaterialMapColor.o) {
/* 162 */                   d2 = k3 * 0.1D + (k1 + l1 & 0x1) * 0.2D;
/* 163 */                   b0 = 1;
/* 164 */                   if (d2 < 0.5D) {
/* 165 */                     b0 = 2;
/*     */                   }
/*     */                   
/* 168 */                   if (d2 > 0.9D) {
/* 169 */                     b0 = 0;
/*     */                   }
/*     */                 } 
/*     */                 
/* 173 */                 d0 = d1;
/* 174 */                 if (l1 >= 0 && i2 * i2 + j2 * j2 < j1 * j1 && (!flag1 || (k1 + l1 & 0x1) != 0)) {
/* 175 */                   byte b1 = worldmap.colors[k1 + l1 * 128];
/* 176 */                   byte b2 = (byte)(materialmapcolor.ad * 4 + b0);
/*     */                   
/* 178 */                   if (b1 != b2) {
/* 179 */                     worldmap.colors[k1 + l1 * 128] = b2;
/* 180 */                     worldmap.flagDirty(k1, l1);
/* 181 */                     flag = true;
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void a(World world, ItemStack itemstack) {
/* 194 */     if (itemstack.getItem() == Items.FILLED_MAP) {
/* 195 */       WorldMap worldmap = Items.FILLED_MAP.getSavedMap(itemstack, world);
/*     */       
/* 197 */       if (worldmap != null && 
/* 198 */         world.worldProvider.getDimensionManager().getDimensionID() == worldmap.map) {
/* 199 */         int i = 1 << worldmap.scale;
/* 200 */         int j = worldmap.centerX;
/* 201 */         int k = worldmap.centerZ;
/* 202 */         BiomeBase[] abiomebase = world.getWorldChunkManager().a(null, (j / i - 64) * i, (k / i - 64) * i, 128 * i, 128 * i, false);
/*     */         
/* 204 */         for (int l = 0; l < 128; l++) {
/* 205 */           for (int i1 = 0; i1 < 128; i1++) {
/* 206 */             int j1 = l * i;
/* 207 */             int k1 = i1 * i;
/* 208 */             BiomeBase biomebase = abiomebase[j1 + k1 * 128 * i];
/* 209 */             MaterialMapColor materialmapcolor = MaterialMapColor.c;
/* 210 */             int l1 = 3;
/* 211 */             int i2 = 8;
/*     */             
/* 213 */             if (l > 0 && i1 > 0 && l < 127 && i1 < 127) {
/* 214 */               if (abiomebase[(l - 1) * i + (i1 - 1) * i * 128 * i].j() >= 0.0F) {
/* 215 */                 i2--;
/*     */               }
/*     */               
/* 218 */               if (abiomebase[(l - 1) * i + (i1 + 1) * i * 128 * i].j() >= 0.0F) {
/* 219 */                 i2--;
/*     */               }
/*     */               
/* 222 */               if (abiomebase[(l - 1) * i + i1 * i * 128 * i].j() >= 0.0F) {
/* 223 */                 i2--;
/*     */               }
/*     */               
/* 226 */               if (abiomebase[(l + 1) * i + (i1 - 1) * i * 128 * i].j() >= 0.0F) {
/* 227 */                 i2--;
/*     */               }
/*     */               
/* 230 */               if (abiomebase[(l + 1) * i + (i1 + 1) * i * 128 * i].j() >= 0.0F) {
/* 231 */                 i2--;
/*     */               }
/*     */               
/* 234 */               if (abiomebase[(l + 1) * i + i1 * i * 128 * i].j() >= 0.0F) {
/* 235 */                 i2--;
/*     */               }
/*     */               
/* 238 */               if (abiomebase[l * i + (i1 - 1) * i * 128 * i].j() >= 0.0F) {
/* 239 */                 i2--;
/*     */               }
/*     */               
/* 242 */               if (abiomebase[l * i + (i1 + 1) * i * 128 * i].j() >= 0.0F) {
/* 243 */                 i2--;
/*     */               }
/*     */               
/* 246 */               if (biomebase.j() < 0.0F) {
/* 247 */                 materialmapcolor = MaterialMapColor.r;
/* 248 */                 if (i2 > 7 && i1 % 2 == 0) {
/* 249 */                   l1 = (l + (int)(MathHelper.sin(i1 + 0.0F) * 7.0F)) / 8 % 5;
/* 250 */                   if (l1 == 3) {
/* 251 */                     l1 = 1;
/* 252 */                   } else if (l1 == 4) {
/* 253 */                     l1 = 0;
/*     */                   } 
/* 255 */                 } else if (i2 > 7) {
/* 256 */                   materialmapcolor = MaterialMapColor.c;
/* 257 */                 } else if (i2 > 5) {
/* 258 */                   l1 = 1;
/* 259 */                 } else if (i2 > 3) {
/* 260 */                   l1 = 0;
/* 261 */                 } else if (i2 > 1) {
/* 262 */                   l1 = 0;
/*     */                 } 
/* 264 */               } else if (i2 > 0) {
/* 265 */                 materialmapcolor = MaterialMapColor.C;
/* 266 */                 if (i2 > 3) {
/* 267 */                   l1 = 1;
/*     */                 } else {
/* 269 */                   l1 = 3;
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */             
/* 274 */             if (materialmapcolor != MaterialMapColor.c) {
/* 275 */               worldmap.colors[l + i1 * 128] = (byte)(materialmapcolor.ad * 4 + l1);
/* 276 */               worldmap.flagDirty(l, i1);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {
/* 287 */     if (!world.isClientSide) {
/* 288 */       WorldMap worldmap = getSavedMap(itemstack, world);
/*     */       
/* 290 */       if (entity instanceof EntityHuman) {
/* 291 */         EntityHuman entityhuman = (EntityHuman)entity;
/*     */         
/* 293 */         worldmap.a(entityhuman, itemstack);
/*     */       } 
/*     */       
/* 296 */       if (flag || (entity instanceof EntityHuman && ((EntityHuman)entity).getItemInOffHand() == itemstack)) {
/* 297 */         a(world, entity, worldmap);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Packet<?> a(ItemStack itemstack, World world, EntityHuman entityhuman) {
/* 305 */     return getSavedMap(itemstack, world).a(itemstack, world, entityhuman);
/*     */   }
/*     */   
/*     */   public void b(ItemStack itemstack, World world, EntityHuman entityhuman) {
/* 309 */     NBTTagCompound nbttagcompound = itemstack.getTag();
/*     */     
/* 311 */     if (nbttagcompound != null) {
/* 312 */       if (nbttagcompound.hasKeyOfType("map_scale_direction", 99)) {
/* 313 */         a(itemstack, world, nbttagcompound.getInt("map_scale_direction"));
/* 314 */         nbttagcompound.remove("map_scale_direction");
/* 315 */       } else if (nbttagcompound.getBoolean("map_tracking_position")) {
/* 316 */         b(itemstack, world);
/* 317 */         nbttagcompound.remove("map_tracking_position");
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void a(ItemStack itemstack, World world, int i) {
/* 324 */     WorldMap worldmap = Items.FILLED_MAP.getSavedMap(itemstack, world);
/*     */     
/* 326 */     world = (world.getServer().getServer()).worlds.get(0);
/* 327 */     itemstack.setData(world.b("map"));
/* 328 */     WorldMap worldmap1 = new WorldMap("map_" + itemstack.getData());
/*     */     
/* 330 */     if (worldmap != null) {
/* 331 */       worldmap1.scale = (byte)MathHelper.clamp(worldmap.scale + i, 0, 4);
/* 332 */       worldmap1.track = worldmap.track;
/* 333 */       worldmap1.a(worldmap.centerX, worldmap.centerZ, worldmap1.scale);
/* 334 */       worldmap1.map = worldmap.map;
/* 335 */       worldmap1.c();
/* 336 */       world.a("map_" + itemstack.getData(), worldmap1);
/*     */       
/* 338 */       MapInitializeEvent event = new MapInitializeEvent((MapView)worldmap1.mapView);
/* 339 */       Bukkit.getServer().getPluginManager().callEvent((Event)event);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static void b(ItemStack itemstack, World world) {
/* 346 */     WorldMap worldmap = Items.FILLED_MAP.getSavedMap(itemstack, world);
/*     */     
/* 348 */     world = (world.getServer().getServer()).worlds.get(0);
/* 349 */     itemstack.setData(world.b("map"));
/* 350 */     WorldMap worldmap1 = new WorldMap("map_" + itemstack.getData());
/*     */     
/* 352 */     worldmap1.track = true;
/* 353 */     if (worldmap != null) {
/* 354 */       worldmap1.centerX = worldmap.centerX;
/* 355 */       worldmap1.centerZ = worldmap.centerZ;
/* 356 */       worldmap1.scale = worldmap.scale;
/* 357 */       worldmap1.map = worldmap.map;
/* 358 */       worldmap1.c();
/* 359 */       world.a("map_" + itemstack.getData(), worldmap1);
/*     */       
/* 361 */       MapInitializeEvent event = new MapInitializeEvent((MapView)worldmap1.mapView);
/* 362 */       Bukkit.getServer().getPluginManager().callEvent((Event)event);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemWorldMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */