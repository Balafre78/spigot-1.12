/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Functions;
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.base.Predicates;
/*     */ import com.google.common.base.Splitter;
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.primitives.Doubles;
/*     */ import com.google.gson.JsonParseException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.commons.lang3.exception.ExceptionUtils;
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
/*     */ public abstract class CommandAbstract
/*     */   implements ICommand
/*     */ {
/*     */   private static ICommandDispatcher a;
/*  47 */   private static final Splitter b = Splitter.on(',');
/*  48 */   private static final Splitter c = Splitter.on('=').limit(2);
/*     */   
/*     */   protected static ExceptionInvalidSyntax a(JsonParseException paramJsonParseException) {
/*  51 */     Throwable throwable = ExceptionUtils.getRootCause((Throwable)paramJsonParseException);
/*  52 */     String str = "";
/*  53 */     if (throwable != null) {
/*  54 */       str = throwable.getMessage();
/*  55 */       if (str.contains("setLenient")) {
/*  56 */         str = str.substring(str.indexOf("to accept ") + 10);
/*     */       }
/*     */     } 
/*     */     
/*  60 */     return new ExceptionInvalidSyntax("commands.tellraw.jsonException", new Object[] { str });
/*     */   }
/*     */   
/*     */   public static NBTTagCompound a(Entity paramEntity) {
/*  64 */     NBTTagCompound nBTTagCompound = paramEntity.save(new NBTTagCompound());
/*  65 */     if (paramEntity instanceof EntityHuman) {
/*  66 */       ItemStack itemStack = ((EntityHuman)paramEntity).inventory.getItemInHand();
/*  67 */       if (!itemStack.isEmpty()) {
/*  68 */         nBTTagCompound.set("SelectedItem", itemStack.save(new NBTTagCompound()));
/*     */       }
/*     */     } 
/*  71 */     return nBTTagCompound;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a() {
/*  76 */     return 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getAliases() {
/*  81 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canUse(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener) {
/*  86 */     return paramICommandListener.a(a(), getCommand());
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/*  91 */     return Collections.emptyList();
/*     */   }
/*     */   
/*     */   public static int a(String paramString) throws ExceptionInvalidNumber {
/*     */     try {
/*  96 */       return Integer.parseInt(paramString);
/*  97 */     } catch (NumberFormatException numberFormatException) {
/*  98 */       throw new ExceptionInvalidNumber("commands.generic.num.invalid", new Object[] { paramString });
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int a(String paramString, int paramInt) throws ExceptionInvalidNumber {
/* 103 */     return a(paramString, paramInt, 2147483647);
/*     */   }
/*     */   
/*     */   public static int a(String paramString, int paramInt1, int paramInt2) throws ExceptionInvalidNumber {
/* 107 */     int i = a(paramString);
/* 108 */     if (i < paramInt1)
/* 109 */       throw new ExceptionInvalidNumber("commands.generic.num.tooSmall", new Object[] { Integer.valueOf(i), Integer.valueOf(paramInt1) }); 
/* 110 */     if (i > paramInt2) {
/* 111 */       throw new ExceptionInvalidNumber("commands.generic.num.tooBig", new Object[] { Integer.valueOf(i), Integer.valueOf(paramInt2) });
/*     */     }
/* 113 */     return i;
/*     */   }
/*     */   
/*     */   public static long b(String paramString) throws ExceptionInvalidNumber {
/*     */     try {
/* 118 */       return Long.parseLong(paramString);
/* 119 */     } catch (NumberFormatException numberFormatException) {
/* 120 */       throw new ExceptionInvalidNumber("commands.generic.num.invalid", new Object[] { paramString });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long a(String paramString, long paramLong1, long paramLong2) throws ExceptionInvalidNumber {
/* 129 */     long l = b(paramString);
/* 130 */     if (l < paramLong1)
/* 131 */       throw new ExceptionInvalidNumber("commands.generic.num.tooSmall", new Object[] { Long.valueOf(l), Long.valueOf(paramLong1) }); 
/* 132 */     if (l > paramLong2) {
/* 133 */       throw new ExceptionInvalidNumber("commands.generic.num.tooBig", new Object[] { Long.valueOf(l), Long.valueOf(paramLong2) });
/*     */     }
/* 135 */     return l;
/*     */   }
/*     */   
/*     */   public static BlockPosition a(ICommandListener paramICommandListener, String[] paramArrayOfString, int paramInt, boolean paramBoolean) throws ExceptionInvalidNumber {
/* 139 */     BlockPosition blockPosition = paramICommandListener.getChunkCoordinates();
/* 140 */     return new BlockPosition(
/* 141 */         b(blockPosition.getX(), paramArrayOfString[paramInt], -30000000, 30000000, paramBoolean), 
/* 142 */         b(blockPosition.getY(), paramArrayOfString[paramInt + 1], 0, 256, false), 
/* 143 */         b(blockPosition.getZ(), paramArrayOfString[paramInt + 2], -30000000, 30000000, paramBoolean));
/*     */   }
/*     */ 
/*     */   
/*     */   public static double c(String paramString) throws ExceptionInvalidNumber {
/*     */     try {
/* 149 */       double d = Double.parseDouble(paramString);
/* 150 */       if (!Doubles.isFinite(d)) {
/* 151 */         throw new ExceptionInvalidNumber("commands.generic.num.invalid", new Object[] { paramString });
/*     */       }
/* 153 */       return d;
/* 154 */     } catch (NumberFormatException numberFormatException) {
/* 155 */       throw new ExceptionInvalidNumber("commands.generic.num.invalid", new Object[] { paramString });
/*     */     } 
/*     */   }
/*     */   
/*     */   public static double a(String paramString, double paramDouble) throws ExceptionInvalidNumber {
/* 160 */     return a(paramString, paramDouble, Double.MAX_VALUE);
/*     */   }
/*     */   
/*     */   public static double a(String paramString, double paramDouble1, double paramDouble2) throws ExceptionInvalidNumber {
/* 164 */     double d = c(paramString);
/* 165 */     if (d < paramDouble1)
/* 166 */       throw new ExceptionInvalidNumber("commands.generic.num.tooSmall", new Object[] { String.format("%.2f", new Object[] { Double.valueOf(d) }), String.format("%.2f", new Object[] { Double.valueOf(paramDouble1) }) }); 
/* 167 */     if (d > paramDouble2) {
/* 168 */       throw new ExceptionInvalidNumber("commands.generic.num.tooBig", new Object[] { String.format("%.2f", new Object[] { Double.valueOf(d) }), String.format("%.2f", new Object[] { Double.valueOf(paramDouble2) }) });
/*     */     }
/* 170 */     return d;
/*     */   }
/*     */   
/*     */   public static boolean d(String paramString) throws CommandException {
/* 174 */     if ("true".equals(paramString) || "1".equals(paramString))
/* 175 */       return true; 
/* 176 */     if ("false".equals(paramString) || "0".equals(paramString)) {
/* 177 */       return false;
/*     */     }
/* 179 */     throw new CommandException("commands.generic.boolean.invalid", new Object[] { paramString });
/*     */   }
/*     */ 
/*     */   
/*     */   public static EntityPlayer a(ICommandListener paramICommandListener) throws ExceptionPlayerNotFound {
/* 184 */     if (paramICommandListener instanceof EntityPlayer) {
/* 185 */       return (EntityPlayer)paramICommandListener;
/*     */     }
/* 187 */     throw new ExceptionPlayerNotFound("commands.generic.player.unspecified");
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<EntityPlayer> a(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String paramString) throws CommandException {
/* 192 */     List<EntityPlayer> list = PlayerSelector.b(paramICommandListener, paramString);
/*     */     
/* 194 */     if (list.isEmpty()) {
/* 195 */       return Lists.newArrayList((Object[])new EntityPlayer[] { a(paramMinecraftServer, (EntityPlayer)null, paramString) });
/*     */     }
/*     */     
/* 198 */     return list;
/*     */   }
/*     */   
/*     */   public static EntityPlayer b(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String paramString) throws CommandException {
/* 202 */     return a(paramMinecraftServer, PlayerSelector.getPlayer(paramICommandListener, paramString), paramString);
/*     */   }
/*     */   
/*     */   private static EntityPlayer a(MinecraftServer paramMinecraftServer, @Nullable EntityPlayer paramEntityPlayer, String paramString) throws CommandException {
/* 206 */     if (paramEntityPlayer == null) {
/*     */       try {
/* 208 */         paramEntityPlayer = paramMinecraftServer.getPlayerList().a(UUID.fromString(paramString));
/* 209 */       } catch (IllegalArgumentException illegalArgumentException) {}
/*     */     }
/*     */ 
/*     */     
/* 213 */     if (paramEntityPlayer == null) {
/* 214 */       paramEntityPlayer = paramMinecraftServer.getPlayerList().getPlayer(paramString);
/*     */     }
/*     */     
/* 217 */     if (paramEntityPlayer == null) {
/* 218 */       throw new ExceptionPlayerNotFound("commands.generic.player.notFound", new Object[] { paramString });
/*     */     }
/*     */     
/* 221 */     return paramEntityPlayer;
/*     */   }
/*     */   
/*     */   public static Entity c(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String paramString) throws CommandException {
/* 225 */     return a(paramMinecraftServer, paramICommandListener, paramString, Entity.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends Entity> T a(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String paramString, Class<? extends T> paramClass) throws CommandException {
/* 230 */     Entity entity = (Entity)PlayerSelector.getEntity(paramICommandListener, paramString, (Class)paramClass);
/*     */     
/* 232 */     if (entity == null) {
/* 233 */       entity = paramMinecraftServer.getPlayerList().getPlayer(paramString);
/*     */     }
/*     */     
/* 236 */     if (entity == null) {
/*     */       try {
/* 238 */         UUID uUID = UUID.fromString(paramString);
/* 239 */         entity = paramMinecraftServer.a(uUID);
/* 240 */         if (entity == null) {
/* 241 */           entity = paramMinecraftServer.getPlayerList().a(uUID);
/*     */         }
/* 243 */       } catch (IllegalArgumentException illegalArgumentException) {
/* 244 */         if ((paramString.split("-")).length == 5) {
/* 245 */           throw new ExceptionEntityNotFound("commands.generic.entity.invalidUuid", new Object[] { paramString });
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 250 */     if (entity == null || !paramClass.isAssignableFrom(entity.getClass())) {
/* 251 */       throw new ExceptionEntityNotFound(paramString);
/*     */     }
/*     */     
/* 254 */     return (T)entity;
/*     */   }
/*     */   
/*     */   public static List<Entity> d(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String paramString) throws CommandException {
/* 258 */     if (PlayerSelector.isPattern(paramString)) {
/* 259 */       return PlayerSelector.getPlayers(paramICommandListener, paramString, Entity.class);
/*     */     }
/* 261 */     return Lists.newArrayList((Object[])new Entity[] { c(paramMinecraftServer, paramICommandListener, paramString) });
/*     */   }
/*     */   
/*     */   public static String e(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String paramString) throws CommandException {
/*     */     try {
/* 266 */       return b(paramMinecraftServer, paramICommandListener, paramString).getName();
/* 267 */     } catch (CommandException commandException) {
/* 268 */       if (PlayerSelector.isPattern(paramString)) {
/* 269 */         throw commandException;
/*     */       }
/*     */ 
/*     */       
/* 273 */       return paramString;
/*     */     } 
/*     */   }
/*     */   public static String f(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String paramString) throws CommandException {
/*     */     try {
/* 278 */       return b(paramMinecraftServer, paramICommandListener, paramString).getName();
/* 279 */     } catch (ExceptionPlayerNotFound exceptionPlayerNotFound) {
/*     */       try {
/* 281 */         return c(paramMinecraftServer, paramICommandListener, paramString).bn();
/* 282 */       } catch (ExceptionEntityNotFound exceptionEntityNotFound) {
/* 283 */         if (PlayerSelector.isPattern(paramString)) {
/* 284 */           throw exceptionEntityNotFound;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 289 */         return paramString;
/*     */       } 
/*     */     } 
/*     */   } public static IChatBaseComponent a(ICommandListener paramICommandListener, String[] paramArrayOfString, int paramInt) throws CommandException {
/* 293 */     return b(paramICommandListener, paramArrayOfString, paramInt, false);
/*     */   }
/*     */   
/*     */   public static IChatBaseComponent b(ICommandListener paramICommandListener, String[] paramArrayOfString, int paramInt, boolean paramBoolean) throws CommandException {
/* 297 */     ChatComponentText chatComponentText = new ChatComponentText("");
/*     */     
/* 299 */     for (int i = paramInt; i < paramArrayOfString.length; i++) {
/* 300 */       if (i > paramInt) {
/* 301 */         chatComponentText.a(" ");
/*     */       }
/* 303 */       IChatBaseComponent iChatBaseComponent = new ChatComponentText(paramArrayOfString[i]);
/*     */       
/* 305 */       if (paramBoolean) {
/* 306 */         IChatBaseComponent iChatBaseComponent1 = PlayerSelector.getPlayerNames(paramICommandListener, paramArrayOfString[i]);
/*     */         
/* 308 */         if (iChatBaseComponent1 == null) {
/* 309 */           if (PlayerSelector.isPattern(paramArrayOfString[i])) {
/* 310 */             throw new ExceptionPlayerNotFound("commands.generic.selector.notFound", new Object[] { paramArrayOfString[i] });
/*     */           }
/*     */         } else {
/* 313 */           iChatBaseComponent = iChatBaseComponent1;
/*     */         } 
/*     */       } 
/*     */       
/* 317 */       chatComponentText.addSibling(iChatBaseComponent);
/*     */     } 
/*     */     
/* 320 */     return chatComponentText;
/*     */   }
/*     */   
/*     */   public static String a(String[] paramArrayOfString, int paramInt) {
/* 324 */     StringBuilder stringBuilder = new StringBuilder();
/*     */     
/* 326 */     for (int i = paramInt; i < paramArrayOfString.length; i++) {
/* 327 */       if (i > paramInt) {
/* 328 */         stringBuilder.append(" ");
/*     */       }
/* 330 */       String str = paramArrayOfString[i];
/*     */       
/* 332 */       stringBuilder.append(str);
/*     */     } 
/*     */     
/* 335 */     return stringBuilder.toString();
/*     */   }
/*     */   
/*     */   public static CommandNumber a(double paramDouble, String paramString, boolean paramBoolean) throws ExceptionInvalidNumber {
/* 339 */     return a(paramDouble, paramString, -30000000, 30000000, paramBoolean);
/*     */   }
/*     */   
/*     */   public static CommandNumber a(double paramDouble, String paramString, int paramInt1, int paramInt2, boolean paramBoolean) throws ExceptionInvalidNumber {
/* 343 */     boolean bool = paramString.startsWith("~");
/* 344 */     if (bool && Double.isNaN(paramDouble)) {
/* 345 */       throw new ExceptionInvalidNumber("commands.generic.num.invalid", new Object[] { Double.valueOf(paramDouble) });
/*     */     }
/* 347 */     double d1 = 0.0D;
/*     */     
/* 349 */     if (!bool || paramString.length() > 1) {
/* 350 */       boolean bool1 = paramString.contains(".");
/* 351 */       if (bool) {
/* 352 */         paramString = paramString.substring(1);
/*     */       }
/*     */       
/* 355 */       d1 += c(paramString);
/*     */       
/* 357 */       if (!bool1 && !bool && paramBoolean) {
/* 358 */         d1 += 0.5D;
/*     */       }
/*     */     } 
/*     */     
/* 362 */     double d2 = d1 + (bool ? paramDouble : 0.0D);
/* 363 */     if (paramInt1 != 0 || paramInt2 != 0) {
/* 364 */       if (d2 < paramInt1)
/* 365 */         throw new ExceptionInvalidNumber("commands.generic.num.tooSmall", new Object[] { String.format("%.2f", new Object[] { Double.valueOf(d2) }), Integer.valueOf(paramInt1) }); 
/* 366 */       if (d2 > paramInt2) {
/* 367 */         throw new ExceptionInvalidNumber("commands.generic.num.tooBig", new Object[] { String.format("%.2f", new Object[] { Double.valueOf(d2) }), Integer.valueOf(paramInt2) });
/*     */       }
/*     */     } 
/*     */     
/* 371 */     return new CommandNumber(d2, d1, bool);
/*     */   }
/*     */   
/*     */   public static double b(double paramDouble, String paramString, boolean paramBoolean) throws ExceptionInvalidNumber {
/* 375 */     return b(paramDouble, paramString, -30000000, 30000000, paramBoolean);
/*     */   }
/*     */   
/*     */   public static double b(double paramDouble, String paramString, int paramInt1, int paramInt2, boolean paramBoolean) throws ExceptionInvalidNumber {
/* 379 */     boolean bool = paramString.startsWith("~");
/* 380 */     if (bool && Double.isNaN(paramDouble)) {
/* 381 */       throw new ExceptionInvalidNumber("commands.generic.num.invalid", new Object[] { Double.valueOf(paramDouble) });
/*     */     }
/* 383 */     double d = bool ? paramDouble : 0.0D;
/*     */     
/* 385 */     if (!bool || paramString.length() > 1) {
/* 386 */       boolean bool1 = paramString.contains(".");
/* 387 */       if (bool) {
/* 388 */         paramString = paramString.substring(1);
/*     */       }
/*     */       
/* 391 */       d += c(paramString);
/*     */       
/* 393 */       if (!bool1 && !bool && paramBoolean) {
/* 394 */         d += 0.5D;
/*     */       }
/*     */     } 
/*     */     
/* 398 */     if (paramInt1 != 0 || paramInt2 != 0) {
/* 399 */       if (d < paramInt1)
/* 400 */         throw new ExceptionInvalidNumber("commands.generic.num.tooSmall", new Object[] { String.format("%.2f", new Object[] { Double.valueOf(d) }), Integer.valueOf(paramInt1) }); 
/* 401 */       if (d > paramInt2) {
/* 402 */         throw new ExceptionInvalidNumber("commands.generic.num.tooBig", new Object[] { String.format("%.2f", new Object[] { Double.valueOf(d) }), Integer.valueOf(paramInt2) });
/*     */       }
/*     */     } 
/*     */     
/* 406 */     return d;
/*     */   }
/*     */   
/*     */   public static class CommandNumber {
/*     */     private final double a;
/*     */     private final double b;
/*     */     private final boolean c;
/*     */     
/*     */     protected CommandNumber(double param1Double1, double param1Double2, boolean param1Boolean) {
/* 415 */       this.a = param1Double1;
/* 416 */       this.b = param1Double2;
/* 417 */       this.c = param1Boolean;
/*     */     }
/*     */     
/*     */     public double a() {
/* 421 */       return this.a;
/*     */     }
/*     */     
/*     */     public double b() {
/* 425 */       return this.b;
/*     */     }
/*     */     
/*     */     public boolean c() {
/* 429 */       return this.c;
/*     */     }
/*     */   }
/*     */   
/*     */   public static Item a(ICommandListener paramICommandListener, String paramString) throws ExceptionInvalidNumber {
/* 434 */     MinecraftKey minecraftKey = new MinecraftKey(paramString);
/* 435 */     Item item = Item.REGISTRY.get(minecraftKey);
/*     */     
/* 437 */     if (item == null) {
/* 438 */       throw new ExceptionInvalidNumber("commands.give.item.notFound", new Object[] { minecraftKey });
/*     */     }
/*     */     
/* 441 */     return item;
/*     */   }
/*     */   
/*     */   public static Block b(ICommandListener paramICommandListener, String paramString) throws ExceptionInvalidNumber {
/* 445 */     MinecraftKey minecraftKey = new MinecraftKey(paramString);
/* 446 */     if (!Block.REGISTRY.d(minecraftKey)) {
/* 447 */       throw new ExceptionInvalidNumber("commands.give.block.notFound", new Object[] { minecraftKey });
/*     */     }
/*     */     
/* 450 */     return Block.REGISTRY.get(minecraftKey);
/*     */   }
/*     */   
/*     */   public static IBlockData a(Block paramBlock, String paramString) throws ExceptionInvalidNumber, ExceptionInvalidBlockState {
/*     */     try {
/* 455 */       int i = Integer.parseInt(paramString);
/* 456 */       if (i < 0)
/* 457 */         throw new ExceptionInvalidNumber("commands.generic.num.tooSmall", new Object[] { Integer.valueOf(i), Integer.valueOf(0) }); 
/* 458 */       if (i > 15) {
/* 459 */         throw new ExceptionInvalidNumber("commands.generic.num.tooBig", new Object[] { Integer.valueOf(i), Integer.valueOf(15) });
/*     */       }
/* 461 */       return paramBlock.fromLegacyData(Integer.parseInt(paramString));
/* 462 */     } catch (RuntimeException runtimeException) {
/*     */       
/*     */       try {
/* 465 */         Map<IBlockState<?>, Comparable<?>> map = c(paramBlock, paramString);
/*     */         
/* 467 */         IBlockData iBlockData = paramBlock.getBlockData();
/* 468 */         for (Map.Entry<IBlockState<?>, Comparable<?>> entry : map.entrySet()) {
/* 469 */           iBlockData = a(iBlockData, (IBlockState<Comparable>)entry.getKey(), (Comparable)entry.getValue());
/*     */         }
/*     */         
/* 472 */         return iBlockData;
/* 473 */       } catch (RuntimeException runtimeException1) {
/* 474 */         throw new ExceptionInvalidBlockState("commands.generic.blockstate.invalid", new Object[] { paramString, Block.REGISTRY.b(paramBlock) });
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static <T extends Comparable<T>> IBlockData a(IBlockData paramIBlockData, IBlockState<T> paramIBlockState, Comparable<?> paramComparable) {
/* 481 */     return paramIBlockData.set(paramIBlockState, paramComparable);
/*     */   }
/*     */   
/*     */   public static Predicate<IBlockData> b(Block paramBlock, String paramString) throws ExceptionInvalidBlockState {
/* 485 */     if ("*".equals(paramString) || "-1".equals(paramString)) {
/* 486 */       return Predicates.alwaysTrue();
/*     */     }
/*     */     
/*     */     try {
/* 490 */       int i = Integer.parseInt(paramString);
/* 491 */       return new Predicate<IBlockData>(i)
/*     */         {
/*     */           public boolean a(@Nullable IBlockData param1IBlockData) {
/* 494 */             return (this.a == param1IBlockData.getBlock().toLegacyData(param1IBlockData));
/*     */           }
/*     */         };
/* 497 */     } catch (RuntimeException runtimeException) {
/*     */       
/* 499 */       Map<IBlockState<?>, Comparable<?>> map = c(paramBlock, paramString);
/*     */       
/* 501 */       return new Predicate<IBlockData>(paramBlock, map)
/*     */         {
/*     */           public boolean a(@Nullable IBlockData param1IBlockData) {
/* 504 */             if (param1IBlockData == null || this.a != param1IBlockData.getBlock()) {
/* 505 */               return false;
/*     */             }
/*     */             
/* 508 */             for (Map.Entry entry : this.b.entrySet()) {
/* 509 */               if (!param1IBlockData.<Comparable>get((IBlockState<Comparable>)entry.getKey()).equals(entry.getValue())) {
/* 510 */                 return false;
/*     */               }
/*     */             } 
/*     */             
/* 514 */             return true;
/*     */           }
/*     */         };
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Map<IBlockState<?>, Comparable<?>> c(Block paramBlock, String paramString) throws ExceptionInvalidBlockState {
/* 521 */     HashMap<IBlockState<?>, Comparable<?>> hashMap = Maps.newHashMap();
/* 522 */     if ("default".equals(paramString)) {
/* 523 */       return (Map<IBlockState<?>, Comparable<?>>)paramBlock.getBlockData().t();
/*     */     }
/*     */     
/* 526 */     BlockStateList blockStateList = paramBlock.s();
/*     */     
/* 528 */     for (String str : b.split(paramString)) {
/* 529 */       Iterator<String> iterator = c.split(str).iterator();
/* 530 */       if (iterator.hasNext()) {
/* 531 */         IBlockState<?> iBlockState = blockStateList.a(iterator.next());
/* 532 */         if (iBlockState != null && iterator.hasNext()) {
/* 533 */           Object object = a((IBlockState)iBlockState, iterator.next());
/* 534 */           if (object != null) {
/* 535 */             hashMap.put(iBlockState, object);
/*     */             continue;
/*     */           } 
/*     */         } 
/*     */       } 
/* 540 */       throw new ExceptionInvalidBlockState("commands.generic.blockstate.invalid", new Object[] { paramString, Block.REGISTRY.b(paramBlock) });
/*     */     } 
/* 542 */     return hashMap;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private static <T extends Comparable<T>> T a(IBlockState<T> paramIBlockState, String paramString) {
/* 548 */     return (T)paramIBlockState.b(paramString).orNull();
/*     */   }
/*     */   
/*     */   public static String a(Object[] paramArrayOfObject) {
/* 552 */     StringBuilder stringBuilder = new StringBuilder();
/*     */     
/* 554 */     for (byte b = 0; b < paramArrayOfObject.length; b++) {
/* 555 */       String str = paramArrayOfObject[b].toString();
/*     */       
/* 557 */       if (b > 0) {
/* 558 */         if (b == paramArrayOfObject.length - 1) {
/* 559 */           stringBuilder.append(" and ");
/*     */         } else {
/* 561 */           stringBuilder.append(", ");
/*     */         } 
/*     */       }
/*     */       
/* 565 */       stringBuilder.append(str);
/*     */     } 
/*     */     
/* 568 */     return stringBuilder.toString();
/*     */   }
/*     */   
/*     */   public static IChatBaseComponent a(List<IChatBaseComponent> paramList) {
/* 572 */     ChatComponentText chatComponentText = new ChatComponentText("");
/*     */     
/* 574 */     for (byte b = 0; b < paramList.size(); b++) {
/* 575 */       if (b > 0) {
/* 576 */         if (b == paramList.size() - 1) {
/* 577 */           chatComponentText.a(" and ");
/* 578 */         } else if (b > 0) {
/* 579 */           chatComponentText.a(", ");
/*     */         } 
/*     */       }
/*     */       
/* 583 */       chatComponentText.addSibling(paramList.get(b));
/*     */     } 
/*     */     
/* 586 */     return chatComponentText;
/*     */   }
/*     */   
/*     */   public static String a(Collection<String> paramCollection) {
/* 590 */     return a(paramCollection.toArray((Object[])new String[paramCollection.size()]));
/*     */   }
/*     */   public static List<String> a(String[] paramArrayOfString, int paramInt, @Nullable BlockPosition paramBlockPosition) {
/*     */     String str;
/* 594 */     if (paramBlockPosition == null) {
/* 595 */       return Lists.newArrayList((Object[])new String[] { "~" });
/*     */     }
/*     */ 
/*     */     
/* 599 */     int i = paramArrayOfString.length - 1;
/* 600 */     if (i == paramInt) {
/* 601 */       str = Integer.toString(paramBlockPosition.getX());
/* 602 */     } else if (i == paramInt + 1) {
/* 603 */       str = Integer.toString(paramBlockPosition.getY());
/* 604 */     } else if (i == paramInt + 2) {
/* 605 */       str = Integer.toString(paramBlockPosition.getZ());
/*     */     } else {
/* 607 */       return Collections.emptyList();
/*     */     } 
/* 609 */     return Lists.newArrayList((Object[])new String[] { str });
/*     */   }
/*     */   public static List<String> b(String[] paramArrayOfString, int paramInt, @Nullable BlockPosition paramBlockPosition) {
/*     */     String str;
/* 613 */     if (paramBlockPosition == null) {
/* 614 */       return Lists.newArrayList((Object[])new String[] { "~" });
/*     */     }
/*     */ 
/*     */     
/* 618 */     int i = paramArrayOfString.length - 1;
/* 619 */     if (i == paramInt) {
/* 620 */       str = Integer.toString(paramBlockPosition.getX());
/* 621 */     } else if (i == paramInt + 1) {
/* 622 */       str = Integer.toString(paramBlockPosition.getZ());
/*     */     } else {
/* 624 */       return Collections.emptyList();
/*     */     } 
/* 626 */     return Lists.newArrayList((Object[])new String[] { str });
/*     */   }
/*     */   
/*     */   public static boolean a(String paramString1, String paramString2) {
/* 630 */     return paramString2.regionMatches(true, 0, paramString1, 0, paramString1.length());
/*     */   }
/*     */   
/*     */   public static List<String> a(String[] paramArrayOfString1, String... paramVarArgs1) {
/* 634 */     return a(paramArrayOfString1, Arrays.asList((Object[])paramVarArgs1));
/*     */   }
/*     */   
/*     */   public static List<String> a(String[] paramArrayOfString, Collection<?> paramCollection) {
/* 638 */     String str = paramArrayOfString[paramArrayOfString.length - 1];
/* 639 */     ArrayList<String> arrayList = Lists.newArrayList();
/*     */     
/* 641 */     if (!paramCollection.isEmpty()) {
/* 642 */       for (String str1 : Iterables.transform(paramCollection, Functions.toStringFunction())) {
/* 643 */         if (a(str, str1)) {
/* 644 */           arrayList.add(str1);
/*     */         }
/*     */       } 
/*     */       
/* 648 */       if (arrayList.isEmpty()) {
/* 649 */         for (Object object : paramCollection) {
/* 650 */           if (object instanceof MinecraftKey && 
/* 651 */             a(str, ((MinecraftKey)object).getKey())) {
/* 652 */             arrayList.add(String.valueOf(object));
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 659 */     return arrayList;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isListStart(String[] paramArrayOfString, int paramInt) {
/* 664 */     return false;
/*     */   }
/*     */   
/*     */   public static void a(ICommandListener paramICommandListener, ICommand paramICommand, String paramString, Object... paramVarArgs) {
/* 668 */     a(paramICommandListener, paramICommand, 0, paramString, paramVarArgs);
/*     */   }
/*     */   
/*     */   public static void a(ICommandListener paramICommandListener, ICommand paramICommand, int paramInt, String paramString, Object... paramVarArgs) {
/* 672 */     if (a != null) {
/* 673 */       a.a(paramICommandListener, paramICommand, paramInt, paramString, paramVarArgs);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void a(ICommandDispatcher paramICommandDispatcher) {
/* 678 */     a = paramICommandDispatcher;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(ICommand paramICommand) {
/* 683 */     return getCommand().compareTo(paramICommand.getCommand());
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */