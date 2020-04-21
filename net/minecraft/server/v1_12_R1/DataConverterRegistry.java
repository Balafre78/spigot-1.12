/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataConverterRegistry
/*     */ {
/*     */   private static void a(DataConverterManager paramDataConverterManager) {
/* 130 */     paramDataConverterManager.a(DataConverterTypes.ENTITY, new DataConverterEquipment());
/* 131 */     paramDataConverterManager.a(DataConverterTypes.BLOCK_ENTITY, new DataConverterSignText());
/* 132 */     paramDataConverterManager.a(DataConverterTypes.ITEM_INSTANCE, new DataConverterMaterialId());
/* 133 */     paramDataConverterManager.a(DataConverterTypes.ITEM_INSTANCE, new DataConverterPotionId());
/* 134 */     paramDataConverterManager.a(DataConverterTypes.ITEM_INSTANCE, new DataConverterSpawnEgg());
/* 135 */     paramDataConverterManager.a(DataConverterTypes.ENTITY, new DataConverterMinecart());
/* 136 */     paramDataConverterManager.a(DataConverterTypes.BLOCK_ENTITY, new DataConverterMobSpawner());
/* 137 */     paramDataConverterManager.a(DataConverterTypes.ENTITY, new DataConverterUUID());
/* 138 */     paramDataConverterManager.a(DataConverterTypes.ENTITY, new DataConverterHealth());
/* 139 */     paramDataConverterManager.a(DataConverterTypes.ENTITY, new DataConverterSaddle());
/* 140 */     paramDataConverterManager.a(DataConverterTypes.ENTITY, new DataConverterHanging());
/* 141 */     paramDataConverterManager.a(DataConverterTypes.ENTITY, new DataConverterDropChances());
/* 142 */     paramDataConverterManager.a(DataConverterTypes.ENTITY, new DataConverterRiding());
/* 143 */     paramDataConverterManager.a(DataConverterTypes.ENTITY, new DataConverterArmorStand());
/* 144 */     paramDataConverterManager.a(DataConverterTypes.ITEM_INSTANCE, new DataConverterBook());
/* 145 */     paramDataConverterManager.a(DataConverterTypes.ITEM_INSTANCE, new DataConverterCookedFish());
/* 146 */     paramDataConverterManager.a(DataConverterTypes.ENTITY, new DataConverterZombie());
/* 147 */     paramDataConverterManager.a(DataConverterTypes.OPTIONS, new DataConverterVBO());
/* 148 */     paramDataConverterManager.a(DataConverterTypes.ENTITY, new DataConverterGuardian());
/* 149 */     paramDataConverterManager.a(DataConverterTypes.ENTITY, new DataConverterSkeleton());
/* 150 */     paramDataConverterManager.a(DataConverterTypes.ENTITY, new DataConverterZombieType());
/* 151 */     paramDataConverterManager.a(DataConverterTypes.ENTITY, new DataConverterHorse());
/* 152 */     paramDataConverterManager.a(DataConverterTypes.BLOCK_ENTITY, new DataConverterTileEntity());
/* 153 */     paramDataConverterManager.a(DataConverterTypes.ENTITY, new DataConverterEntity());
/* 154 */     paramDataConverterManager.a(DataConverterTypes.ITEM_INSTANCE, new DataConverterBanner());
/* 155 */     paramDataConverterManager.a(DataConverterTypes.ITEM_INSTANCE, new DataConverterPotionWater());
/* 156 */     paramDataConverterManager.a(DataConverterTypes.ENTITY, new DataConverterShulker());
/* 157 */     paramDataConverterManager.a(DataConverterTypes.ITEM_INSTANCE, new DataConverterShulkerBoxItem());
/* 158 */     paramDataConverterManager.a(DataConverterTypes.BLOCK_ENTITY, new DataConverterShulkerBoxBlock());
/* 159 */     paramDataConverterManager.a(DataConverterTypes.OPTIONS, new DataConverterLang());
/* 160 */     paramDataConverterManager.a(DataConverterTypes.ITEM_INSTANCE, new DataConverterTotem());
/* 161 */     paramDataConverterManager.a(DataConverterTypes.CHUNK, new DataConverterBedBlock());
/* 162 */     paramDataConverterManager.a(DataConverterTypes.ITEM_INSTANCE, new DataConverterBedItem());
/*     */   }
/*     */   
/*     */   public static DataConverterManager a() {
/* 166 */     DataConverterManager dataConverterManager = new DataConverterManager(1139);
/*     */     
/* 168 */     WorldData.a(dataConverterManager);
/* 169 */     EntityPlayer.a(dataConverterManager);
/* 170 */     EntityHuman.c(dataConverterManager);
/* 171 */     ChunkRegionLoader.a(dataConverterManager);
/* 172 */     ItemStack.a(dataConverterManager);
/* 173 */     DefinedStructure.a(dataConverterManager);
/*     */ 
/*     */     
/* 176 */     Entity.b(dataConverterManager);
/* 177 */     EntityArmorStand.a(dataConverterManager);
/* 178 */     EntityArrow.a(dataConverterManager);
/* 179 */     EntityBat.a(dataConverterManager);
/* 180 */     EntityBlaze.a(dataConverterManager);
/* 181 */     EntityCaveSpider.a(dataConverterManager);
/* 182 */     EntityChicken.a(dataConverterManager);
/* 183 */     EntityCow.a(dataConverterManager);
/* 184 */     EntityCreeper.a(dataConverterManager);
/* 185 */     EntityHorseDonkey.a(dataConverterManager);
/* 186 */     EntityDragonFireball.a(dataConverterManager);
/* 187 */     EntityGuardianElder.a(dataConverterManager);
/* 188 */     EntityEnderDragon.a(dataConverterManager);
/* 189 */     EntityEnderman.a(dataConverterManager);
/* 190 */     EntityEndermite.a(dataConverterManager);
/* 191 */     EntityEvoker.a(dataConverterManager);
/* 192 */     EntityFallingBlock.a(dataConverterManager);
/* 193 */     EntityFireworks.a(dataConverterManager);
/* 194 */     EntityGhast.a(dataConverterManager);
/* 195 */     EntityGiantZombie.a(dataConverterManager);
/* 196 */     EntityGuardian.c(dataConverterManager);
/* 197 */     EntityHorse.a(dataConverterManager);
/* 198 */     EntityZombieHusk.a(dataConverterManager);
/* 199 */     EntityItem.a(dataConverterManager);
/* 200 */     EntityItemFrame.a(dataConverterManager);
/* 201 */     EntityLargeFireball.a(dataConverterManager);
/* 202 */     EntityMagmaCube.a(dataConverterManager);
/* 203 */     EntityMinecartChest.a(dataConverterManager);
/* 204 */     EntityMinecartCommandBlock.a(dataConverterManager);
/* 205 */     EntityMinecartFurnace.a(dataConverterManager);
/* 206 */     EntityMinecartHopper.a(dataConverterManager);
/* 207 */     EntityMinecartRideable.a(dataConverterManager);
/* 208 */     EntityMinecartMobSpawner.a(dataConverterManager);
/* 209 */     EntityMinecartTNT.a(dataConverterManager);
/* 210 */     EntityHorseMule.a(dataConverterManager);
/* 211 */     EntityMushroomCow.c(dataConverterManager);
/* 212 */     EntityOcelot.a(dataConverterManager);
/* 213 */     EntityPig.a(dataConverterManager);
/* 214 */     EntityPigZombie.a(dataConverterManager);
/* 215 */     EntityRabbit.a(dataConverterManager);
/* 216 */     EntitySheep.a(dataConverterManager);
/* 217 */     EntityShulker.a(dataConverterManager);
/* 218 */     EntitySilverfish.a(dataConverterManager);
/* 219 */     EntitySkeleton.a(dataConverterManager);
/* 220 */     EntityHorseSkeleton.a(dataConverterManager);
/* 221 */     EntitySlime.c(dataConverterManager);
/* 222 */     EntitySmallFireball.a(dataConverterManager);
/* 223 */     EntitySnowman.a(dataConverterManager);
/* 224 */     EntitySnowball.a(dataConverterManager);
/* 225 */     EntitySpectralArrow.c(dataConverterManager);
/* 226 */     EntitySpider.c(dataConverterManager);
/* 227 */     EntitySquid.a(dataConverterManager);
/* 228 */     EntitySkeletonStray.a(dataConverterManager);
/* 229 */     EntityEgg.a(dataConverterManager);
/* 230 */     EntityEnderPearl.a(dataConverterManager);
/* 231 */     EntityThrownExpBottle.a(dataConverterManager);
/* 232 */     EntityPotion.a(dataConverterManager);
/* 233 */     EntityTippedArrow.c(dataConverterManager);
/* 234 */     EntityVex.a(dataConverterManager);
/* 235 */     EntityVillager.a(dataConverterManager);
/* 236 */     EntityIronGolem.a(dataConverterManager);
/* 237 */     EntityVindicator.a(dataConverterManager);
/* 238 */     EntityWitch.a(dataConverterManager);
/* 239 */     EntityWither.a(dataConverterManager);
/* 240 */     EntitySkeletonWither.a(dataConverterManager);
/* 241 */     EntityWitherSkull.a(dataConverterManager);
/* 242 */     EntityWolf.a(dataConverterManager);
/* 243 */     EntityZombie.c(dataConverterManager);
/* 244 */     EntityHorseZombie.a(dataConverterManager);
/* 245 */     EntityZombieVillager.a(dataConverterManager);
/*     */ 
/*     */     
/* 248 */     TileEntityPiston.a(dataConverterManager);
/* 249 */     TileEntityFlowerPot.a(dataConverterManager);
/* 250 */     TileEntityFurnace.a(dataConverterManager);
/* 251 */     TileEntityChest.a(dataConverterManager);
/* 252 */     TileEntityDispenser.a(dataConverterManager);
/* 253 */     TileEntityDropper.b(dataConverterManager);
/* 254 */     TileEntityBrewingStand.a(dataConverterManager);
/* 255 */     TileEntityHopper.a(dataConverterManager);
/* 256 */     BlockJukeBox.a(dataConverterManager);
/* 257 */     TileEntityMobSpawner.a(dataConverterManager);
/* 258 */     TileEntityShulkerBox.a(dataConverterManager);
/*     */     
/* 260 */     a(dataConverterManager);
/*     */     
/* 262 */     return dataConverterManager;
/*     */   }
/*     */   
/*     */   public static NBTTagCompound a(DataConverter paramDataConverter, NBTTagCompound paramNBTTagCompound, int paramInt, String paramString) {
/* 266 */     if (paramNBTTagCompound.hasKeyOfType(paramString, 10)) {
/* 267 */       paramNBTTagCompound.set(paramString, paramDataConverter.a(DataConverterTypes.ITEM_INSTANCE, paramNBTTagCompound.getCompound(paramString), paramInt));
/*     */     }
/*     */     
/* 270 */     return paramNBTTagCompound;
/*     */   }
/*     */   
/*     */   public static NBTTagCompound b(DataConverter paramDataConverter, NBTTagCompound paramNBTTagCompound, int paramInt, String paramString) {
/* 274 */     if (paramNBTTagCompound.hasKeyOfType(paramString, 9)) {
/* 275 */       NBTTagList nBTTagList = paramNBTTagCompound.getList(paramString, 10);
/* 276 */       for (byte b = 0; b < nBTTagList.size(); b++) {
/* 277 */         nBTTagList.a(b, paramDataConverter.a(DataConverterTypes.ITEM_INSTANCE, nBTTagList.get(b), paramInt));
/*     */       }
/*     */     } 
/*     */     
/* 281 */     return paramNBTTagCompound;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */