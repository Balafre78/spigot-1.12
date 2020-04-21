/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ 
/*     */ public class ItemMonsterEgg extends Item {
/*     */   public ItemMonsterEgg() {
/*  11 */     b(CreativeModeTab.f);
/*     */   }
/*     */   
/*     */   public String b(ItemStack itemstack) {
/*  15 */     String s = LocaleI18n.get(String.valueOf(getName()) + ".name").trim();
/*  16 */     String s1 = EntityTypes.a(h(itemstack));
/*     */     
/*  18 */     if (s1 != null) {
/*  19 */       s = String.valueOf(s) + " " + LocaleI18n.get("entity." + s1 + ".name");
/*     */     }
/*     */     
/*  22 */     return s;
/*     */   }
/*     */   
/*     */   public EnumInteractionResult a(EntityHuman entityhuman, World world, BlockPosition blockposition, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
/*  26 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/*  28 */     if (world.isClientSide)
/*  29 */       return EnumInteractionResult.SUCCESS; 
/*  30 */     if (!entityhuman.a(blockposition.shift(enumdirection), enumdirection, itemstack)) {
/*  31 */       return EnumInteractionResult.FAIL;
/*     */     }
/*  33 */     IBlockData iblockdata = world.getType(blockposition);
/*  34 */     Block block = iblockdata.getBlock();
/*     */     
/*  36 */     if (block == Blocks.MOB_SPAWNER) {
/*  37 */       TileEntity tileentity = world.getTileEntity(blockposition);
/*     */       
/*  39 */       if (tileentity instanceof TileEntityMobSpawner) {
/*  40 */         MobSpawnerAbstract mobspawnerabstract = ((TileEntityMobSpawner)tileentity).getSpawner();
/*     */         
/*  42 */         mobspawnerabstract.setMobName(h(itemstack));
/*  43 */         tileentity.update();
/*  44 */         world.notify(blockposition, iblockdata, iblockdata, 3);
/*  45 */         if (!entityhuman.abilities.canInstantlyBuild) {
/*  46 */           itemstack.subtract(1);
/*     */         }
/*     */         
/*  49 */         return EnumInteractionResult.SUCCESS;
/*     */       } 
/*     */     } 
/*     */     
/*  53 */     BlockPosition blockposition1 = blockposition.shift(enumdirection);
/*  54 */     double d0 = a(world, blockposition1);
/*  55 */     Entity entity = a(world, h(itemstack), blockposition1.getX() + 0.5D, blockposition1.getY() + d0, blockposition1.getZ() + 0.5D);
/*     */     
/*  57 */     if (entity != null) {
/*  58 */       if (entity instanceof EntityLiving && itemstack.hasName()) {
/*  59 */         entity.setCustomName(itemstack.getName());
/*     */       }
/*     */       
/*  62 */       a(world, entityhuman, itemstack, entity);
/*  63 */       if (!entityhuman.abilities.canInstantlyBuild) {
/*  64 */         itemstack.subtract(1);
/*     */       }
/*     */     } 
/*     */     
/*  68 */     return EnumInteractionResult.SUCCESS;
/*     */   }
/*     */ 
/*     */   
/*     */   protected double a(World world, BlockPosition blockposition) {
/*  73 */     AxisAlignedBB axisalignedbb = (new AxisAlignedBB(blockposition)).b(0.0D, -1.0D, 0.0D);
/*  74 */     List<AxisAlignedBB> list = world.getCubes(null, axisalignedbb);
/*     */     
/*  76 */     if (list.isEmpty()) {
/*  77 */       return 0.0D;
/*     */     }
/*  79 */     double d0 = axisalignedbb.b;
/*     */ 
/*     */ 
/*     */     
/*  83 */     for (Iterator<AxisAlignedBB> iterator = list.iterator(); iterator.hasNext(); d0 = Math.max(axisalignedbb1.e, d0)) {
/*  84 */       AxisAlignedBB axisalignedbb1 = iterator.next();
/*     */     }
/*     */     
/*  87 */     return d0 - blockposition.getY();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void a(World world, @Nullable EntityHuman entityhuman, ItemStack itemstack, @Nullable Entity entity) {
/*  92 */     MinecraftServer minecraftserver = world.getMinecraftServer();
/*     */     
/*  94 */     if (minecraftserver != null && entity != null) {
/*  95 */       NBTTagCompound nbttagcompound = itemstack.getTag();
/*     */       
/*  97 */       if (nbttagcompound != null && nbttagcompound.hasKeyOfType("EntityTag", 10)) {
/*  98 */         if (!world.isClientSide && entity.bC() && (entityhuman == null || !minecraftserver.getPlayerList().isOp(entityhuman.getProfile()))) {
/*     */           return;
/*     */         }
/*     */         
/* 102 */         NBTTagCompound nbttagcompound1 = entity.save(new NBTTagCompound());
/* 103 */         UUID uuid = entity.getUniqueID();
/*     */         
/* 105 */         nbttagcompound1.a(nbttagcompound.getCompound("EntityTag"));
/* 106 */         entity.a(uuid);
/* 107 */         entity.f(nbttagcompound1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
/* 114 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/* 116 */     if (world.isClientSide) {
/* 117 */       return new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemstack);
/*     */     }
/* 119 */     MovingObjectPosition movingobjectposition = a(world, entityhuman, true);
/*     */     
/* 121 */     if (movingobjectposition != null && movingobjectposition.type == MovingObjectPosition.EnumMovingObjectType.BLOCK) {
/* 122 */       BlockPosition blockposition = movingobjectposition.a();
/*     */       
/* 124 */       if (!(world.getType(blockposition).getBlock() instanceof BlockFluids))
/* 125 */         return new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemstack); 
/* 126 */       if (world.a(entityhuman, blockposition) && entityhuman.a(blockposition, movingobjectposition.direction, itemstack)) {
/* 127 */         Entity entity = a(world, h(itemstack), blockposition.getX() + 0.5D, blockposition.getY() + 0.5D, blockposition.getZ() + 0.5D);
/*     */         
/* 129 */         if (entity == null) {
/* 130 */           return new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemstack);
/*     */         }
/* 132 */         if (entity instanceof EntityLiving && itemstack.hasName()) {
/* 133 */           entity.setCustomName(itemstack.getName());
/*     */         }
/*     */         
/* 136 */         a(world, entityhuman, itemstack, entity);
/* 137 */         if (!entityhuman.abilities.canInstantlyBuild) {
/* 138 */           itemstack.subtract(1);
/*     */         }
/*     */         
/* 141 */         entityhuman.b(StatisticList.b(this));
/* 142 */         return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemstack);
/*     */       } 
/*     */       
/* 145 */       return new InteractionResultWrapper<>(EnumInteractionResult.FAIL, itemstack);
/*     */     } 
/*     */     
/* 148 */     return new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemstack);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static Entity a(World world, @Nullable MinecraftKey minecraftkey, double d0, double d1, double d2) {
/* 155 */     return spawnCreature(world, minecraftkey, d0, d1, d2, CreatureSpawnEvent.SpawnReason.SPAWNER_EGG);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static Entity spawnCreature(World world, @Nullable MinecraftKey minecraftkey, double d0, double d1, double d2, CreatureSpawnEvent.SpawnReason spawnReason) {
/* 160 */     if (minecraftkey != null && EntityTypes.eggInfo.containsKey(minecraftkey)) {
/* 161 */       Entity entity = null;
/*     */       
/* 163 */       for (int i = 0; i < 1; i++) {
/* 164 */         entity = EntityTypes.a(minecraftkey, world);
/* 165 */         if (entity instanceof EntityInsentient) {
/* 166 */           EntityInsentient entityinsentient = (EntityInsentient)entity;
/*     */           
/* 168 */           entity.setPositionRotation(d0, d1, d2, MathHelper.g(world.random.nextFloat() * 360.0F), 0.0F);
/* 169 */           entityinsentient.aP = entityinsentient.yaw;
/* 170 */           entityinsentient.aN = entityinsentient.yaw;
/* 171 */           entityinsentient.prepare(world.D(new BlockPosition(entityinsentient)), (GroupDataEntity)null);
/*     */           
/* 173 */           if (!world.addEntity(entity, spawnReason)) {
/* 174 */             entity = null;
/*     */           } else {
/* 176 */             entityinsentient.D();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 182 */       return entity;
/*     */     } 
/* 184 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static MinecraftKey h(ItemStack itemstack) {
/* 190 */     NBTTagCompound nbttagcompound = itemstack.getTag();
/*     */     
/* 192 */     if (nbttagcompound == null)
/* 193 */       return null; 
/* 194 */     if (!nbttagcompound.hasKeyOfType("EntityTag", 10)) {
/* 195 */       return null;
/*     */     }
/* 197 */     NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("EntityTag");
/*     */     
/* 199 */     if (!nbttagcompound1.hasKeyOfType("id", 8)) {
/* 200 */       return null;
/*     */     }
/* 202 */     String s = nbttagcompound1.getString("id");
/* 203 */     MinecraftKey minecraftkey = new MinecraftKey(s);
/*     */     
/* 205 */     if (!s.contains(":")) {
/* 206 */       nbttagcompound1.setString("id", minecraftkey.toString());
/*     */     }
/*     */     
/* 209 */     return minecraftkey;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemMonsterEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */