/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
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
/*     */ public class DefinedStructure
/*     */ {
/*  59 */   private final List<BlockInfo> a = Lists.newArrayList();
/*  60 */   private final List<EntityInfo> b = Lists.newArrayList();
/*  61 */   private BlockPosition c = BlockPosition.ZERO;
/*  62 */   private String d = "?";
/*     */   
/*     */   public BlockPosition a() {
/*  65 */     return this.c;
/*     */   }
/*     */   
/*     */   public void a(String paramString) {
/*  69 */     this.d = paramString;
/*     */   }
/*     */   
/*     */   public String b() {
/*  73 */     return this.d;
/*     */   }
/*     */   
/*     */   public void a(World paramWorld, BlockPosition paramBlockPosition1, BlockPosition paramBlockPosition2, boolean paramBoolean, @Nullable Block paramBlock) {
/*  77 */     if (paramBlockPosition2.getX() < 1 || paramBlockPosition2.getY() < 1 || paramBlockPosition2.getZ() < 1) {
/*     */       return;
/*     */     }
/*  80 */     BlockPosition blockPosition1 = paramBlockPosition1.a(paramBlockPosition2).a(-1, -1, -1);
/*  81 */     ArrayList<BlockInfo> arrayList1 = Lists.newArrayList();
/*  82 */     ArrayList<BlockInfo> arrayList2 = Lists.newArrayList();
/*  83 */     ArrayList<BlockInfo> arrayList3 = Lists.newArrayList();
/*     */     
/*  85 */     BlockPosition blockPosition2 = new BlockPosition(Math.min(paramBlockPosition1.getX(), blockPosition1.getX()), Math.min(paramBlockPosition1.getY(), blockPosition1.getY()), Math.min(paramBlockPosition1.getZ(), blockPosition1.getZ()));
/*  86 */     BlockPosition blockPosition3 = new BlockPosition(Math.max(paramBlockPosition1.getX(), blockPosition1.getX()), Math.max(paramBlockPosition1.getY(), blockPosition1.getY()), Math.max(paramBlockPosition1.getZ(), blockPosition1.getZ()));
/*  87 */     this.c = paramBlockPosition2;
/*     */     
/*  89 */     for (BlockPosition.MutableBlockPosition mutableBlockPosition : BlockPosition.b(blockPosition2, blockPosition3)) {
/*  90 */       BlockPosition blockPosition = mutableBlockPosition.b(blockPosition2);
/*  91 */       IBlockData iBlockData = paramWorld.getType(mutableBlockPosition);
/*  92 */       if (paramBlock != null && paramBlock == iBlockData.getBlock()) {
/*     */         continue;
/*     */       }
/*  95 */       TileEntity tileEntity = paramWorld.getTileEntity(mutableBlockPosition);
/*  96 */       if (tileEntity != null) {
/*  97 */         NBTTagCompound nBTTagCompound = tileEntity.save(new NBTTagCompound());
/*  98 */         nBTTagCompound.remove("x");
/*  99 */         nBTTagCompound.remove("y");
/* 100 */         nBTTagCompound.remove("z");
/* 101 */         arrayList2.add(new BlockInfo(blockPosition, iBlockData, nBTTagCompound)); continue;
/* 102 */       }  if (iBlockData.b() || iBlockData.g()) {
/* 103 */         arrayList1.add(new BlockInfo(blockPosition, iBlockData, null)); continue;
/*     */       } 
/* 105 */       arrayList3.add(new BlockInfo(blockPosition, iBlockData, null));
/*     */     } 
/*     */ 
/*     */     
/* 109 */     this.a.clear();
/* 110 */     this.a.addAll(arrayList1);
/* 111 */     this.a.addAll(arrayList2);
/* 112 */     this.a.addAll(arrayList3);
/*     */     
/* 114 */     if (paramBoolean) {
/* 115 */       a(paramWorld, blockPosition2, blockPosition3.a(1, 1, 1));
/*     */     } else {
/* 117 */       this.b.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a(World paramWorld, BlockPosition paramBlockPosition1, BlockPosition paramBlockPosition2) {
/* 122 */     List<Entity> list = paramWorld.a(Entity.class, new AxisAlignedBB(paramBlockPosition1, paramBlockPosition2), new Predicate<Entity>(this)
/*     */         {
/*     */           public boolean a(@Nullable Entity param1Entity) {
/* 125 */             return !(param1Entity instanceof EntityHuman);
/*     */           }
/*     */         });
/* 128 */     this.b.clear();
/* 129 */     for (Entity entity : list) {
/* 130 */       BlockPosition blockPosition; Vec3D vec3D = new Vec3D(entity.locX - paramBlockPosition1.getX(), entity.locY - paramBlockPosition1.getY(), entity.locZ - paramBlockPosition1.getZ());
/* 131 */       NBTTagCompound nBTTagCompound = new NBTTagCompound();
/* 132 */       entity.d(nBTTagCompound);
/*     */       
/* 134 */       if (entity instanceof EntityPainting) {
/* 135 */         blockPosition = ((EntityPainting)entity).getBlockPosition().b(paramBlockPosition1);
/*     */       } else {
/* 137 */         blockPosition = new BlockPosition(vec3D);
/*     */       } 
/* 139 */       this.b.add(new EntityInfo(vec3D, blockPosition, nBTTagCompound));
/*     */     } 
/*     */   }
/*     */   
/*     */   public Map<BlockPosition, String> a(BlockPosition paramBlockPosition, DefinedStructureInfo paramDefinedStructureInfo) {
/* 144 */     HashMap<BlockPosition, String> hashMap = Maps.newHashMap();
/* 145 */     StructureBoundingBox structureBoundingBox = paramDefinedStructureInfo.i();
/* 146 */     for (BlockInfo blockInfo : this.a) {
/* 147 */       BlockPosition blockPosition = a(paramDefinedStructureInfo, blockInfo.a).a(paramBlockPosition);
/* 148 */       if (structureBoundingBox != null && !structureBoundingBox.b(blockPosition)) {
/*     */         continue;
/*     */       }
/* 151 */       IBlockData iBlockData = blockInfo.b;
/* 152 */       if (iBlockData.getBlock() != Blocks.STRUCTURE_BLOCK || blockInfo.c == null) {
/*     */         continue;
/*     */       }
/* 155 */       TileEntityStructure.UsageMode usageMode = TileEntityStructure.UsageMode.valueOf(blockInfo.c.getString("mode"));
/* 156 */       if (usageMode != TileEntityStructure.UsageMode.DATA) {
/*     */         continue;
/*     */       }
/* 159 */       hashMap.put(blockPosition, blockInfo.c.getString("metadata"));
/*     */     } 
/* 161 */     return hashMap;
/*     */   }
/*     */   
/*     */   public BlockPosition a(DefinedStructureInfo paramDefinedStructureInfo1, BlockPosition paramBlockPosition1, DefinedStructureInfo paramDefinedStructureInfo2, BlockPosition paramBlockPosition2) {
/* 165 */     BlockPosition blockPosition1 = a(paramDefinedStructureInfo1, paramBlockPosition1);
/* 166 */     BlockPosition blockPosition2 = a(paramDefinedStructureInfo2, paramBlockPosition2);
/* 167 */     return blockPosition1.b(blockPosition2);
/*     */   }
/*     */   
/*     */   public static BlockPosition a(DefinedStructureInfo paramDefinedStructureInfo, BlockPosition paramBlockPosition) {
/* 171 */     return b(paramBlockPosition, paramDefinedStructureInfo.b(), paramDefinedStructureInfo.c());
/*     */   }
/*     */   
/*     */   public void a(World paramWorld, BlockPosition paramBlockPosition, DefinedStructureInfo paramDefinedStructureInfo) {
/* 175 */     paramDefinedStructureInfo.k();
/* 176 */     b(paramWorld, paramBlockPosition, paramDefinedStructureInfo);
/*     */   }
/*     */   
/*     */   public void b(World paramWorld, BlockPosition paramBlockPosition, DefinedStructureInfo paramDefinedStructureInfo) {
/* 180 */     a(paramWorld, paramBlockPosition, new DefinedStructureProcessorRotation(paramBlockPosition, paramDefinedStructureInfo), paramDefinedStructureInfo, 2);
/*     */   }
/*     */   
/*     */   public void a(World paramWorld, BlockPosition paramBlockPosition, DefinedStructureInfo paramDefinedStructureInfo, int paramInt) {
/* 184 */     a(paramWorld, paramBlockPosition, new DefinedStructureProcessorRotation(paramBlockPosition, paramDefinedStructureInfo), paramDefinedStructureInfo, paramInt);
/*     */   }
/*     */   
/*     */   public void a(World paramWorld, BlockPosition paramBlockPosition, @Nullable DefinedStructureProcessor paramDefinedStructureProcessor, DefinedStructureInfo paramDefinedStructureInfo, int paramInt) {
/* 188 */     if ((this.a.isEmpty() && (paramDefinedStructureInfo.g() || this.b.isEmpty())) || this.c.getX() < 1 || this.c.getY() < 1 || this.c.getZ() < 1) {
/*     */       return;
/*     */     }
/*     */     
/* 192 */     Block block = paramDefinedStructureInfo.h();
/* 193 */     StructureBoundingBox structureBoundingBox = paramDefinedStructureInfo.i();
/*     */     
/* 195 */     for (BlockInfo blockInfo1 : this.a) {
/* 196 */       BlockPosition blockPosition = a(paramDefinedStructureInfo, blockInfo1.a).a(paramBlockPosition);
/* 197 */       BlockInfo blockInfo2 = (paramDefinedStructureProcessor != null) ? paramDefinedStructureProcessor.a(paramWorld, blockPosition, blockInfo1) : blockInfo1;
/*     */       
/* 199 */       if (blockInfo2 == null) {
/*     */         continue;
/*     */       }
/*     */       
/* 203 */       Block block1 = blockInfo2.b.getBlock();
/* 204 */       if (block != null && block == block1) {
/*     */         continue;
/*     */       }
/* 207 */       if (paramDefinedStructureInfo.j() && block1 == Blocks.STRUCTURE_BLOCK) {
/*     */         continue;
/*     */       }
/*     */       
/* 211 */       if (structureBoundingBox != null && !structureBoundingBox.b(blockPosition)) {
/*     */         continue;
/*     */       }
/*     */       
/* 215 */       IBlockData iBlockData1 = blockInfo2.b.a(paramDefinedStructureInfo.b());
/* 216 */       IBlockData iBlockData2 = iBlockData1.a(paramDefinedStructureInfo.c());
/* 217 */       if (blockInfo2.c != null) {
/* 218 */         TileEntity tileEntity = paramWorld.getTileEntity(blockPosition);
/* 219 */         if (tileEntity != null) {
/* 220 */           if (tileEntity instanceof IInventory) {
/* 221 */             ((IInventory)tileEntity).clear();
/*     */           }
/* 223 */           paramWorld.setTypeAndData(blockPosition, Blocks.BARRIER.getBlockData(), 4);
/*     */         } 
/*     */       } 
/* 226 */       if (paramWorld.setTypeAndData(blockPosition, iBlockData2, paramInt) && 
/* 227 */         blockInfo2.c != null) {
/* 228 */         TileEntity tileEntity = paramWorld.getTileEntity(blockPosition);
/* 229 */         if (tileEntity != null) {
/* 230 */           blockInfo2.c.setInt("x", blockPosition.getX());
/* 231 */           blockInfo2.c.setInt("y", blockPosition.getY());
/* 232 */           blockInfo2.c.setInt("z", blockPosition.getZ());
/* 233 */           tileEntity.a(blockInfo2.c);
/* 234 */           tileEntity.a(paramDefinedStructureInfo.b());
/* 235 */           tileEntity.a(paramDefinedStructureInfo.c());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 241 */     for (BlockInfo blockInfo : this.a) {
/* 242 */       if (block != null && block == blockInfo.b.getBlock()) {
/*     */         continue;
/*     */       }
/* 245 */       BlockPosition blockPosition = a(paramDefinedStructureInfo, blockInfo.a).a(paramBlockPosition);
/* 246 */       if (structureBoundingBox != null && !structureBoundingBox.b(blockPosition)) {
/*     */         continue;
/*     */       }
/* 249 */       paramWorld.update(blockPosition, blockInfo.b.getBlock(), false);
/* 250 */       if (blockInfo.c != null) {
/* 251 */         TileEntity tileEntity = paramWorld.getTileEntity(blockPosition);
/* 252 */         if (tileEntity != null) {
/* 253 */           tileEntity.update();
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 258 */     if (!paramDefinedStructureInfo.g()) {
/* 259 */       a(paramWorld, paramBlockPosition, paramDefinedStructureInfo.b(), paramDefinedStructureInfo.c(), structureBoundingBox);
/*     */     }
/*     */   }
/*     */   
/*     */   private void a(World paramWorld, BlockPosition paramBlockPosition, EnumBlockMirror paramEnumBlockMirror, EnumBlockRotation paramEnumBlockRotation, @Nullable StructureBoundingBox paramStructureBoundingBox) {
/* 264 */     for (EntityInfo entityInfo : this.b) {
/* 265 */       Entity entity; BlockPosition blockPosition = b(entityInfo.b, paramEnumBlockMirror, paramEnumBlockRotation).a(paramBlockPosition);
/* 266 */       if (paramStructureBoundingBox != null && !paramStructureBoundingBox.b(blockPosition)) {
/*     */         continue;
/*     */       }
/*     */       
/* 270 */       NBTTagCompound nBTTagCompound = entityInfo.c;
/* 271 */       Vec3D vec3D1 = a(entityInfo.a, paramEnumBlockMirror, paramEnumBlockRotation);
/* 272 */       Vec3D vec3D2 = vec3D1.add(paramBlockPosition.getX(), paramBlockPosition.getY(), paramBlockPosition.getZ());
/*     */       
/* 274 */       NBTTagList nBTTagList = new NBTTagList();
/* 275 */       nBTTagList.add(new NBTTagDouble(vec3D2.x));
/* 276 */       nBTTagList.add(new NBTTagDouble(vec3D2.y));
/* 277 */       nBTTagList.add(new NBTTagDouble(vec3D2.z));
/* 278 */       nBTTagCompound.set("Pos", nBTTagList);
/*     */       
/* 280 */       nBTTagCompound.a("UUID", UUID.randomUUID());
/*     */ 
/*     */       
/*     */       try {
/* 284 */         entity = EntityTypes.a(nBTTagCompound, paramWorld);
/* 285 */       } catch (Exception exception) {
/* 286 */         entity = null;
/*     */       } 
/* 288 */       if (entity != null) {
/* 289 */         float f = entity.a(paramEnumBlockMirror);
/* 290 */         f += entity.yaw - entity.a(paramEnumBlockRotation);
/* 291 */         entity.setPositionRotation(vec3D2.x, vec3D2.y, vec3D2.z, f, entity.pitch);
/* 292 */         paramWorld.addEntity(entity);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public BlockPosition a(EnumBlockRotation paramEnumBlockRotation) {
/* 298 */     switch (null.a[paramEnumBlockRotation.ordinal()]) {
/*     */       case 1:
/*     */       case 2:
/* 301 */         return new BlockPosition(this.c.getZ(), this.c.getY(), this.c.getX());
/*     */     } 
/* 303 */     return this.c;
/*     */   }
/*     */ 
/*     */   
/*     */   private static BlockPosition b(BlockPosition paramBlockPosition, EnumBlockMirror paramEnumBlockMirror, EnumBlockRotation paramEnumBlockRotation) {
/* 308 */     int i = paramBlockPosition.getX();
/* 309 */     int j = paramBlockPosition.getY();
/* 310 */     int k = paramBlockPosition.getZ();
/*     */     
/* 312 */     boolean bool = true;
/* 313 */     switch (null.b[paramEnumBlockMirror.ordinal()]) {
/*     */       case 1:
/* 315 */         k = -k;
/*     */         break;
/*     */       case 2:
/* 318 */         i = -i;
/*     */         break;
/*     */       default:
/* 321 */         bool = false;
/*     */         break;
/*     */     } 
/*     */     
/* 325 */     switch (null.a[paramEnumBlockRotation.ordinal()]) {
/*     */       case 3:
/* 327 */         return new BlockPosition(-i, j, -k);
/*     */       case 1:
/* 329 */         return new BlockPosition(k, j, -i);
/*     */       case 2:
/* 331 */         return new BlockPosition(-k, j, i);
/*     */     } 
/* 333 */     return bool ? new BlockPosition(i, j, k) : paramBlockPosition;
/*     */   }
/*     */ 
/*     */   
/*     */   private static Vec3D a(Vec3D paramVec3D, EnumBlockMirror paramEnumBlockMirror, EnumBlockRotation paramEnumBlockRotation) {
/* 338 */     double d1 = paramVec3D.x;
/* 339 */     double d2 = paramVec3D.y;
/* 340 */     double d3 = paramVec3D.z;
/*     */     
/* 342 */     boolean bool = true;
/* 343 */     switch (null.b[paramEnumBlockMirror.ordinal()]) {
/*     */       case 1:
/* 345 */         d3 = 1.0D - d3;
/*     */         break;
/*     */       case 2:
/* 348 */         d1 = 1.0D - d1;
/*     */         break;
/*     */       default:
/* 351 */         bool = false;
/*     */         break;
/*     */     } 
/*     */     
/* 355 */     switch (null.a[paramEnumBlockRotation.ordinal()]) {
/*     */       case 3:
/* 357 */         return new Vec3D(1.0D - d1, d2, 1.0D - d3);
/*     */       case 1:
/* 359 */         return new Vec3D(d3, d2, 1.0D - d1);
/*     */       case 2:
/* 361 */         return new Vec3D(1.0D - d3, d2, d1);
/*     */     } 
/* 363 */     return bool ? new Vec3D(d1, d2, d3) : paramVec3D;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosition a(BlockPosition paramBlockPosition, EnumBlockMirror paramEnumBlockMirror, EnumBlockRotation paramEnumBlockRotation) {
/* 368 */     return a(paramBlockPosition, paramEnumBlockMirror, paramEnumBlockRotation, a().getX(), a().getZ());
/*     */   }
/*     */   
/*     */   public static BlockPosition a(BlockPosition paramBlockPosition, EnumBlockMirror paramEnumBlockMirror, EnumBlockRotation paramEnumBlockRotation, int paramInt1, int paramInt2) {
/* 372 */     paramInt1--;
/* 373 */     paramInt2--;
/*     */     
/* 375 */     byte b1 = (paramEnumBlockMirror == EnumBlockMirror.FRONT_BACK) ? paramInt1 : 0;
/* 376 */     byte b2 = (paramEnumBlockMirror == EnumBlockMirror.LEFT_RIGHT) ? paramInt2 : 0;
/*     */     
/* 378 */     BlockPosition blockPosition = paramBlockPosition;
/*     */     
/* 380 */     switch (null.a[paramEnumBlockRotation.ordinal()]) {
/*     */       case 4:
/* 382 */         blockPosition = paramBlockPosition.a(b1, 0, b2);
/*     */         break;
/*     */       case 2:
/* 385 */         blockPosition = paramBlockPosition.a(paramInt2 - b2, 0, b1);
/*     */         break;
/*     */       case 3:
/* 388 */         blockPosition = paramBlockPosition.a(paramInt1 - b1, 0, paramInt2 - b2);
/*     */         break;
/*     */       case 1:
/* 391 */         blockPosition = paramBlockPosition.a(b2, 0, paramInt1 - b1);
/*     */         break;
/*     */     } 
/* 394 */     return blockPosition;
/*     */   }
/*     */   
/*     */   static class a implements Iterable<IBlockData> {
/* 398 */     public static final IBlockData a = Blocks.AIR.getBlockData();
/*     */     
/* 400 */     final RegistryBlockID<IBlockData> b = new RegistryBlockID<>(16);
/*     */     private int c;
/*     */     
/*     */     public int a(IBlockData param1IBlockData) {
/* 404 */       int i = this.b.getId(param1IBlockData);
/* 405 */       if (i == -1) {
/* 406 */         i = this.c++;
/* 407 */         this.b.a(param1IBlockData, i);
/*     */       } 
/*     */       
/* 410 */       return i;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public IBlockData a(int param1Int) {
/* 415 */       IBlockData iBlockData = this.b.fromId(param1Int);
/* 416 */       return (iBlockData == null) ? a : iBlockData;
/*     */     }
/*     */ 
/*     */     
/*     */     public Iterator<IBlockData> iterator() {
/* 421 */       return this.b.iterator();
/*     */     }
/*     */     
/*     */     public void a(IBlockData param1IBlockData, int param1Int) {
/* 425 */       this.b.a(param1IBlockData, param1Int);
/*     */     }
/*     */     private a() {} }
/*     */   
/*     */   public static void a(DataConverterManager paramDataConverterManager) {
/* 430 */     paramDataConverterManager.a(DataConverterTypes.STRUCTURE, new DataInspector()
/*     */         {
/*     */           public NBTTagCompound a(DataConverter param1DataConverter, NBTTagCompound param1NBTTagCompound, int param1Int) {
/* 433 */             if (param1NBTTagCompound.hasKeyOfType("entities", 9)) {
/* 434 */               NBTTagList nBTTagList = param1NBTTagCompound.getList("entities", 10);
/* 435 */               for (byte b = 0; b < nBTTagList.size(); b++) {
/* 436 */                 NBTTagCompound nBTTagCompound = (NBTTagCompound)nBTTagList.i(b);
/* 437 */                 if (nBTTagCompound.hasKeyOfType("nbt", 10)) {
/* 438 */                   nBTTagCompound.set("nbt", param1DataConverter.a(DataConverterTypes.ENTITY, nBTTagCompound.getCompound("nbt"), param1Int));
/*     */                 }
/*     */               } 
/*     */             } 
/*     */             
/* 443 */             if (param1NBTTagCompound.hasKeyOfType("blocks", 9)) {
/* 444 */               NBTTagList nBTTagList = param1NBTTagCompound.getList("blocks", 10);
/* 445 */               for (byte b = 0; b < nBTTagList.size(); b++) {
/* 446 */                 NBTTagCompound nBTTagCompound = (NBTTagCompound)nBTTagList.i(b);
/* 447 */                 if (nBTTagCompound.hasKeyOfType("nbt", 10)) {
/* 448 */                   nBTTagCompound.set("nbt", param1DataConverter.a(DataConverterTypes.BLOCK_ENTITY, nBTTagCompound.getCompound("nbt"), param1Int));
/*     */                 }
/*     */               } 
/*     */             } 
/*     */             
/* 453 */             return param1NBTTagCompound;
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 459 */     a a = new a();
/*     */     
/* 461 */     NBTTagList nBTTagList1 = new NBTTagList();
/* 462 */     for (BlockInfo blockInfo : this.a) {
/* 463 */       NBTTagCompound nBTTagCompound = new NBTTagCompound();
/* 464 */       nBTTagCompound.set("pos", a(new int[] { blockInfo.a.getX(), blockInfo.a.getY(), blockInfo.a.getZ() }));
/* 465 */       nBTTagCompound.setInt("state", a.a(blockInfo.b));
/* 466 */       if (blockInfo.c != null) {
/* 467 */         nBTTagCompound.set("nbt", blockInfo.c);
/*     */       }
/* 469 */       nBTTagList1.add(nBTTagCompound);
/*     */     } 
/*     */     
/* 472 */     NBTTagList nBTTagList2 = new NBTTagList();
/* 473 */     for (EntityInfo entityInfo : this.b) {
/* 474 */       NBTTagCompound nBTTagCompound = new NBTTagCompound();
/* 475 */       nBTTagCompound.set("pos", a(new double[] { entityInfo.a.x, entityInfo.a.y, entityInfo.a.z }));
/* 476 */       nBTTagCompound.set("blockPos", a(new int[] { entityInfo.b.getX(), entityInfo.b.getY(), entityInfo.b.getZ() }));
/* 477 */       if (entityInfo.c != null) {
/* 478 */         nBTTagCompound.set("nbt", entityInfo.c);
/*     */       }
/* 480 */       nBTTagList2.add(nBTTagCompound);
/*     */     } 
/*     */     
/* 483 */     NBTTagList nBTTagList3 = new NBTTagList();
/* 484 */     for (IBlockData iBlockData : a) {
/* 485 */       nBTTagList3.add(GameProfileSerializer.a(new NBTTagCompound(), iBlockData));
/*     */     }
/*     */     
/* 488 */     paramNBTTagCompound.set("palette", nBTTagList3);
/* 489 */     paramNBTTagCompound.set("blocks", nBTTagList1);
/* 490 */     paramNBTTagCompound.set("entities", nBTTagList2);
/* 491 */     paramNBTTagCompound.set("size", a(new int[] { this.c.getX(), this.c.getY(), this.c.getZ() }));
/* 492 */     paramNBTTagCompound.setString("author", this.d);
/* 493 */     paramNBTTagCompound.setInt("DataVersion", 1139);
/*     */     
/* 495 */     return paramNBTTagCompound;
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound paramNBTTagCompound) {
/* 499 */     this.a.clear();
/* 500 */     this.b.clear();
/*     */     
/* 502 */     NBTTagList nBTTagList1 = paramNBTTagCompound.getList("size", 3);
/* 503 */     this.c = new BlockPosition(nBTTagList1.c(0), nBTTagList1.c(1), nBTTagList1.c(2));
/* 504 */     this.d = paramNBTTagCompound.getString("author");
/*     */     
/* 506 */     a a = new a();
/* 507 */     NBTTagList nBTTagList2 = paramNBTTagCompound.getList("palette", 10);
/* 508 */     for (byte b1 = 0; b1 < nBTTagList2.size(); b1++) {
/* 509 */       a.a(GameProfileSerializer.d(nBTTagList2.get(b1)), b1);
/*     */     }
/*     */     
/* 512 */     NBTTagList nBTTagList3 = paramNBTTagCompound.getList("blocks", 10);
/* 513 */     for (byte b2 = 0; b2 < nBTTagList3.size(); b2++) {
/* 514 */       NBTTagCompound nBTTagCompound2, nBTTagCompound1 = nBTTagList3.get(b2);
/* 515 */       NBTTagList nBTTagList = nBTTagCompound1.getList("pos", 3);
/* 516 */       BlockPosition blockPosition = new BlockPosition(nBTTagList.c(0), nBTTagList.c(1), nBTTagList.c(2));
/* 517 */       IBlockData iBlockData = a.a(nBTTagCompound1.getInt("state"));
/*     */       
/* 519 */       if (nBTTagCompound1.hasKey("nbt")) {
/* 520 */         nBTTagCompound2 = nBTTagCompound1.getCompound("nbt");
/*     */       } else {
/* 522 */         nBTTagCompound2 = null;
/*     */       } 
/* 524 */       this.a.add(new BlockInfo(blockPosition, iBlockData, nBTTagCompound2));
/*     */     } 
/*     */     
/* 527 */     NBTTagList nBTTagList4 = paramNBTTagCompound.getList("entities", 10);
/* 528 */     for (byte b3 = 0; b3 < nBTTagList4.size(); b3++) {
/* 529 */       NBTTagCompound nBTTagCompound = nBTTagList4.get(b3);
/* 530 */       NBTTagList nBTTagList5 = nBTTagCompound.getList("pos", 6);
/* 531 */       Vec3D vec3D = new Vec3D(nBTTagList5.f(0), nBTTagList5.f(1), nBTTagList5.f(2));
/* 532 */       NBTTagList nBTTagList6 = nBTTagCompound.getList("blockPos", 3);
/* 533 */       BlockPosition blockPosition = new BlockPosition(nBTTagList6.c(0), nBTTagList6.c(1), nBTTagList6.c(2));
/* 534 */       if (nBTTagCompound.hasKey("nbt")) {
/* 535 */         NBTTagCompound nBTTagCompound1 = nBTTagCompound.getCompound("nbt");
/* 536 */         this.b.add(new EntityInfo(vec3D, blockPosition, nBTTagCompound1));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private NBTTagList a(int... paramVarArgs) {
/* 542 */     NBTTagList nBTTagList = new NBTTagList();
/* 543 */     for (int i : paramVarArgs) {
/* 544 */       nBTTagList.add(new NBTTagInt(i));
/*     */     }
/* 546 */     return nBTTagList;
/*     */   }
/*     */   
/*     */   private NBTTagList a(double... paramVarArgs) {
/* 550 */     NBTTagList nBTTagList = new NBTTagList();
/* 551 */     for (double d : paramVarArgs) {
/* 552 */       nBTTagList.add(new NBTTagDouble(d));
/*     */     }
/* 554 */     return nBTTagList;
/*     */   }
/*     */   
/*     */   public static class BlockInfo {
/*     */     public final BlockPosition a;
/*     */     public final IBlockData b;
/*     */     public final NBTTagCompound c;
/*     */     
/*     */     public BlockInfo(BlockPosition param1BlockPosition, IBlockData param1IBlockData, @Nullable NBTTagCompound param1NBTTagCompound) {
/* 563 */       this.a = param1BlockPosition;
/* 564 */       this.b = param1IBlockData;
/* 565 */       this.c = param1NBTTagCompound;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class EntityInfo {
/*     */     public final Vec3D a;
/*     */     public final BlockPosition b;
/*     */     public final NBTTagCompound c;
/*     */     
/*     */     public EntityInfo(Vec3D param1Vec3D, BlockPosition param1BlockPosition, NBTTagCompound param1NBTTagCompound) {
/* 575 */       this.a = param1Vec3D;
/* 576 */       this.b = param1BlockPosition;
/* 577 */       this.c = param1NBTTagCompound;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DefinedStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */