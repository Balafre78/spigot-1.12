/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.annotations.VisibleForTesting;
/*     */ import com.google.common.base.Optional;
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.properties.Property;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
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
/*     */ public final class GameProfileSerializer
/*     */ {
/*  25 */   private static final Logger a = LogManager.getLogger();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static GameProfile deserialize(NBTTagCompound paramNBTTagCompound) {
/*  33 */     String str1 = null;
/*  34 */     String str2 = null;
/*     */     
/*  36 */     if (paramNBTTagCompound.hasKeyOfType("Name", 8)) {
/*  37 */       str1 = paramNBTTagCompound.getString("Name");
/*     */     }
/*  39 */     if (paramNBTTagCompound.hasKeyOfType("Id", 8)) {
/*  40 */       str2 = paramNBTTagCompound.getString("Id");
/*     */     }
/*     */     
/*     */     try {
/*     */       UUID uUID;
/*     */       try {
/*  46 */         uUID = UUID.fromString(str2);
/*  47 */       } catch (Throwable throwable) {
/*  48 */         uUID = null;
/*     */       } 
/*  50 */       GameProfile gameProfile = new GameProfile(uUID, str1);
/*     */       
/*  52 */       if (paramNBTTagCompound.hasKeyOfType("Properties", 10)) {
/*  53 */         NBTTagCompound nBTTagCompound = paramNBTTagCompound.getCompound("Properties");
/*     */         
/*  55 */         for (String str : nBTTagCompound.c()) {
/*  56 */           NBTTagList nBTTagList = nBTTagCompound.getList(str, 10);
/*  57 */           for (byte b = 0; b < nBTTagList.size(); b++) {
/*  58 */             NBTTagCompound nBTTagCompound1 = nBTTagList.get(b);
/*  59 */             String str3 = nBTTagCompound1.getString("Value");
/*     */             
/*  61 */             if (nBTTagCompound1.hasKeyOfType("Signature", 8)) {
/*  62 */               gameProfile.getProperties().put(str, new Property(str, str3, nBTTagCompound1.getString("Signature")));
/*     */             } else {
/*  64 */               gameProfile.getProperties().put(str, new Property(str, str3));
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  70 */       return gameProfile;
/*  71 */     } catch (Throwable throwable) {
/*     */       
/*  73 */       return null;
/*     */     } 
/*     */   }
/*     */   public static NBTTagCompound serialize(NBTTagCompound paramNBTTagCompound, GameProfile paramGameProfile) {
/*  77 */     if (!UtilColor.b(paramGameProfile.getName())) {
/*  78 */       paramNBTTagCompound.setString("Name", paramGameProfile.getName());
/*     */     }
/*  80 */     if (paramGameProfile.getId() != null) {
/*  81 */       paramNBTTagCompound.setString("Id", paramGameProfile.getId().toString());
/*     */     }
/*  83 */     if (!paramGameProfile.getProperties().isEmpty()) {
/*  84 */       NBTTagCompound nBTTagCompound = new NBTTagCompound();
/*  85 */       for (String str : paramGameProfile.getProperties().keySet()) {
/*  86 */         NBTTagList nBTTagList = new NBTTagList();
/*  87 */         for (Property property : paramGameProfile.getProperties().get(str)) {
/*  88 */           NBTTagCompound nBTTagCompound1 = new NBTTagCompound();
/*  89 */           nBTTagCompound1.setString("Value", property.getValue());
/*  90 */           if (property.hasSignature()) {
/*  91 */             nBTTagCompound1.setString("Signature", property.getSignature());
/*     */           }
/*  93 */           nBTTagList.add(nBTTagCompound1);
/*     */         } 
/*  95 */         nBTTagCompound.set(str, nBTTagList);
/*     */       } 
/*  97 */       paramNBTTagCompound.set("Properties", nBTTagCompound);
/*     */     } 
/*     */     
/* 100 */     return paramNBTTagCompound;
/*     */   }
/*     */   
/*     */   @VisibleForTesting
/*     */   public static boolean a(NBTBase paramNBTBase1, NBTBase paramNBTBase2, boolean paramBoolean) {
/* 105 */     if (paramNBTBase1 == paramNBTBase2) {
/* 106 */       return true;
/*     */     }
/* 108 */     if (paramNBTBase1 == null) {
/* 109 */       return true;
/*     */     }
/* 111 */     if (paramNBTBase2 == null) {
/* 112 */       return false;
/*     */     }
/* 114 */     if (!paramNBTBase1.getClass().equals(paramNBTBase2.getClass())) {
/* 115 */       return false;
/*     */     }
/*     */     
/* 118 */     if (paramNBTBase1 instanceof NBTTagCompound) {
/* 119 */       NBTTagCompound nBTTagCompound1 = (NBTTagCompound)paramNBTBase1;
/* 120 */       NBTTagCompound nBTTagCompound2 = (NBTTagCompound)paramNBTBase2;
/*     */       
/* 122 */       for (String str : nBTTagCompound1.c()) {
/* 123 */         NBTBase nBTBase = nBTTagCompound1.get(str);
/* 124 */         if (!a(nBTBase, nBTTagCompound2.get(str), paramBoolean)) {
/* 125 */           return false;
/*     */         }
/*     */       } 
/*     */       
/* 129 */       return true;
/* 130 */     }  if (paramNBTBase1 instanceof NBTTagList && paramBoolean) {
/* 131 */       NBTTagList nBTTagList1 = (NBTTagList)paramNBTBase1;
/* 132 */       NBTTagList nBTTagList2 = (NBTTagList)paramNBTBase2;
/*     */       
/* 134 */       if (nBTTagList1.isEmpty()) {
/* 135 */         return nBTTagList2.isEmpty();
/*     */       }
/*     */       
/* 138 */       for (byte b = 0; b < nBTTagList1.size(); b++) {
/* 139 */         NBTBase nBTBase = nBTTagList1.i(b);
/* 140 */         boolean bool = false;
/* 141 */         for (byte b1 = 0; b1 < nBTTagList2.size(); b1++) {
/* 142 */           if (a(nBTBase, nBTTagList2.i(b1), paramBoolean)) {
/* 143 */             bool = true;
/*     */             break;
/*     */           } 
/*     */         } 
/* 147 */         if (!bool) {
/* 148 */           return false;
/*     */         }
/*     */       } 
/*     */       
/* 152 */       return true;
/*     */     } 
/* 154 */     return paramNBTBase1.equals(paramNBTBase2);
/*     */   }
/*     */ 
/*     */   
/*     */   public static NBTTagCompound a(UUID paramUUID) {
/* 159 */     NBTTagCompound nBTTagCompound = new NBTTagCompound();
/* 160 */     nBTTagCompound.setLong("M", paramUUID.getMostSignificantBits());
/* 161 */     nBTTagCompound.setLong("L", paramUUID.getLeastSignificantBits());
/* 162 */     return nBTTagCompound;
/*     */   }
/*     */   
/*     */   public static UUID b(NBTTagCompound paramNBTTagCompound) {
/* 166 */     return new UUID(paramNBTTagCompound.getLong("M"), paramNBTTagCompound.getLong("L"));
/*     */   }
/*     */   
/*     */   public static BlockPosition c(NBTTagCompound paramNBTTagCompound) {
/* 170 */     return new BlockPosition(paramNBTTagCompound.getInt("X"), paramNBTTagCompound.getInt("Y"), paramNBTTagCompound.getInt("Z"));
/*     */   }
/*     */   
/*     */   public static NBTTagCompound a(BlockPosition paramBlockPosition) {
/* 174 */     NBTTagCompound nBTTagCompound = new NBTTagCompound();
/* 175 */     nBTTagCompound.setInt("X", paramBlockPosition.getX());
/* 176 */     nBTTagCompound.setInt("Y", paramBlockPosition.getY());
/* 177 */     nBTTagCompound.setInt("Z", paramBlockPosition.getZ());
/* 178 */     return nBTTagCompound;
/*     */   }
/*     */   
/*     */   public static IBlockData d(NBTTagCompound paramNBTTagCompound) {
/* 182 */     if (!paramNBTTagCompound.hasKeyOfType("Name", 8)) {
/* 183 */       return Blocks.AIR.getBlockData();
/*     */     }
/*     */     
/* 186 */     Block block = Block.REGISTRY.get(new MinecraftKey(paramNBTTagCompound.getString("Name")));
/* 187 */     IBlockData iBlockData = block.getBlockData();
/*     */     
/* 189 */     if (paramNBTTagCompound.hasKeyOfType("Properties", 10)) {
/* 190 */       NBTTagCompound nBTTagCompound = paramNBTTagCompound.getCompound("Properties");
/*     */       
/* 192 */       BlockStateList blockStateList = block.s();
/* 193 */       for (String str : nBTTagCompound.c()) {
/* 194 */         IBlockState<?> iBlockState = blockStateList.a(str);
/* 195 */         if (iBlockState != null) {
/* 196 */           iBlockData = a(iBlockData, iBlockState, str, nBTTagCompound, paramNBTTagCompound);
/*     */         }
/*     */       } 
/*     */     } 
/* 200 */     return iBlockData;
/*     */   }
/*     */ 
/*     */   
/*     */   private static <T extends Comparable<T>> IBlockData a(IBlockData paramIBlockData, IBlockState<T> paramIBlockState, String paramString, NBTTagCompound paramNBTTagCompound1, NBTTagCompound paramNBTTagCompound2) {
/* 205 */     Optional<T> optional = paramIBlockState.b(paramNBTTagCompound1.getString(paramString));
/* 206 */     if (optional.isPresent()) {
/* 207 */       return paramIBlockData.set(paramIBlockState, (Comparable)optional.get());
/*     */     }
/*     */     
/* 210 */     a.warn("Unable to read property: {} with value: {} for blockstate: {}", paramString, paramNBTTagCompound1.getString(paramString), paramNBTTagCompound2.toString());
/* 211 */     return paramIBlockData;
/*     */   }
/*     */   
/*     */   public static NBTTagCompound a(NBTTagCompound paramNBTTagCompound, IBlockData paramIBlockData) {
/* 215 */     paramNBTTagCompound.setString("Name", ((MinecraftKey)Block.REGISTRY.b(paramIBlockData.getBlock())).toString());
/*     */     
/* 217 */     if (!paramIBlockData.t().isEmpty()) {
/* 218 */       NBTTagCompound nBTTagCompound = new NBTTagCompound();
/*     */       
/* 220 */       for (UnmodifiableIterator<Map.Entry> unmodifiableIterator = paramIBlockData.t().entrySet().iterator(); unmodifiableIterator.hasNext(); ) { Map.Entry entry = unmodifiableIterator.next();
/* 221 */         IBlockState<Comparable> iBlockState = (IBlockState)entry.getKey();
/* 222 */         nBTTagCompound.setString(iBlockState.a(), a(iBlockState, (Comparable)entry.getValue())); }
/*     */       
/* 224 */       paramNBTTagCompound.set("Properties", nBTTagCompound);
/*     */     } 
/*     */     
/* 227 */     return paramNBTTagCompound;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static <T extends Comparable<T>> String a(IBlockState<T> paramIBlockState, Comparable<?> paramComparable) {
/* 233 */     return paramIBlockState.a((T)paramComparable);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\GameProfileSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */