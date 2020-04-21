/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.collect.Iterators;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.io.Files;
/*     */ import com.mojang.authlib.Agent;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.ProfileLookupCallback;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.text.ParseException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.spigotmc.SpigotConfig;
/*     */ 
/*     */ public class NameReferencingFileConverter {
/*  32 */   private static final Logger e = LogManager.getLogger();
/*  33 */   public static final File a = new File("banned-ips.txt");
/*  34 */   public static final File b = new File("banned-players.txt");
/*  35 */   public static final File c = new File("ops.txt");
/*  36 */   public static final File d = new File("white-list.txt");
/*     */   
/*     */   static List<String> a(File file, Map<String, String[]> map) throws IOException {
/*  39 */     List<String> list = Files.readLines(file, StandardCharsets.UTF_8);
/*  40 */     Iterator<String> iterator = list.iterator();
/*     */     
/*  42 */     while (iterator.hasNext()) {
/*  43 */       String s = iterator.next();
/*     */       
/*  45 */       s = s.trim();
/*  46 */       if (!s.startsWith("#") && s.length() >= 1) {
/*  47 */         String[] astring = s.split("\\|");
/*     */         
/*  49 */         map.put(astring[0].toLowerCase(Locale.ROOT), astring);
/*     */       } 
/*     */     } 
/*     */     
/*  53 */     return list;
/*     */   }
/*     */   
/*     */   private static void a(MinecraftServer minecraftserver, Collection<String> collection, ProfileLookupCallback profilelookupcallback) {
/*  57 */     String[] astring = (String[])Iterators.toArray((Iterator)Iterators.filter(collection.iterator(), new Predicate() {
/*     */             public boolean a(@Nullable String s) {
/*  59 */               return !UtilColor.b(s);
/*     */             }
/*     */             
/*     */             public boolean apply(@Nullable Object object) {
/*  63 */               return a((String)object);
/*     */             }
/*  65 */           }), String.class);
/*     */     
/*  67 */     if (minecraftserver.getOnlineMode() || SpigotConfig.bungee) {
/*  68 */       minecraftserver.getGameProfileRepository().findProfilesByNames(astring, Agent.MINECRAFT, profilelookupcallback);
/*     */     } else {
/*  70 */       String[] astring1 = astring;
/*  71 */       int i = astring.length;
/*     */       
/*  73 */       for (int j = 0; j < i; j++) {
/*  74 */         String s = astring1[j];
/*  75 */         UUID uuid = EntityHuman.a(new GameProfile(null, s));
/*  76 */         GameProfile gameprofile = new GameProfile(uuid, s);
/*     */         
/*  78 */         profilelookupcallback.onProfileLookupSucceeded(gameprofile);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean a(final MinecraftServer minecraftserver) {
/*  85 */     final GameProfileBanList gameprofilebanlist = new GameProfileBanList(PlayerList.a);
/*     */     
/*  87 */     if (b.exists() && b.isFile()) {
/*  88 */       if (gameprofilebanlist.c().exists()) {
/*     */         try {
/*  90 */           gameprofilebanlist.load();
/*     */         }
/*  92 */         catch (IOException iOException) {
/*  93 */           e.warn("Could not load existing file {}", gameprofilebanlist.c().getName());
/*     */         } 
/*     */       }
/*     */       
/*     */       try {
/*  98 */         final HashMap<String, String[]> hashmap = Maps.newHashMap();
/*     */         
/* 100 */         a(b, (Map<String, String[]>)hashmap);
/* 101 */         ProfileLookupCallback profilelookupcallback = new ProfileLookupCallback() {
/*     */             public void onProfileLookupSucceeded(GameProfile gameprofile) {
/* 103 */               minecraftserver.getUserCache().a(gameprofile);
/* 104 */               String[] astring = (String[])hashmap.get(gameprofile.getName().toLowerCase(Locale.ROOT));
/*     */               
/* 106 */               if (astring == null) {
/* 107 */                 NameReferencingFileConverter.e.warn("Could not convert user banlist entry for {}", gameprofile.getName());
/* 108 */                 throw new NameReferencingFileConverter.FileConversionException("Profile not in the conversionlist", null, null);
/*     */               } 
/* 110 */               Date date = (astring.length > 1) ? NameReferencingFileConverter.b(astring[1], null) : null;
/* 111 */               String s = (astring.length > 2) ? astring[2] : null;
/* 112 */               Date date1 = (astring.length > 3) ? NameReferencingFileConverter.b(astring[3], null) : null;
/* 113 */               String s1 = (astring.length > 4) ? astring[4] : null;
/*     */               
/* 115 */               gameprofilebanlist.add(new GameProfileBanEntry(gameprofile, date, s, date1, s1));
/*     */             }
/*     */ 
/*     */             
/*     */             public void onProfileLookupFailed(GameProfile gameprofile, Exception exception) {
/* 120 */               NameReferencingFileConverter.e.warn("Could not lookup user banlist entry for {}", gameprofile.getName(), exception);
/* 121 */               if (!(exception instanceof com.mojang.authlib.yggdrasil.ProfileNotFoundException)) {
/* 122 */                 throw new NameReferencingFileConverter.FileConversionException("Could not request user " + gameprofile.getName() + " from backend systems", exception, null);
/*     */               }
/*     */             }
/*     */           };
/*     */         
/* 127 */         a(minecraftserver, hashmap.keySet(), profilelookupcallback);
/* 128 */         gameprofilebanlist.save();
/* 129 */         c(b);
/* 130 */         return true;
/* 131 */       } catch (IOException ioexception) {
/* 132 */         e.warn("Could not read old user banlist to convert it!", ioexception);
/* 133 */         return false;
/* 134 */       } catch (FileConversionException namereferencingfileconverter_fileconversionexception) {
/* 135 */         e.error("Conversion failed, please try again later", namereferencingfileconverter_fileconversionexception);
/* 136 */         return false;
/*     */       } 
/*     */     } 
/* 139 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean b(MinecraftServer minecraftserver) {
/* 144 */     IpBanList ipbanlist = new IpBanList(PlayerList.b);
/*     */     
/* 146 */     if (a.exists() && a.isFile()) {
/* 147 */       if (ipbanlist.c().exists()) {
/*     */         try {
/* 149 */           ipbanlist.load();
/*     */         }
/* 151 */         catch (IOException iOException) {
/* 152 */           e.warn("Could not load existing file {}", ipbanlist.c().getName());
/*     */         } 
/*     */       }
/*     */       
/*     */       try {
/* 157 */         HashMap<String, String[]> hashmap = Maps.newHashMap();
/*     */         
/* 159 */         a(a, (Map<String, String[]>)hashmap);
/* 160 */         Iterator<String> iterator = hashmap.keySet().iterator();
/*     */         
/* 162 */         while (iterator.hasNext()) {
/* 163 */           String s = iterator.next();
/* 164 */           String[] astring = hashmap.get(s);
/* 165 */           Date date = (astring.length > 1) ? b(astring[1], null) : null;
/* 166 */           String s1 = (astring.length > 2) ? astring[2] : null;
/* 167 */           Date date1 = (astring.length > 3) ? b(astring[3], null) : null;
/* 168 */           String s2 = (astring.length > 4) ? astring[4] : null;
/*     */           
/* 170 */           ipbanlist.add(new IpBanEntry(s, date, s1, date1, s2));
/*     */         } 
/*     */         
/* 173 */         ipbanlist.save();
/* 174 */         c(a);
/* 175 */         return true;
/* 176 */       } catch (IOException ioexception) {
/* 177 */         e.warn("Could not parse old ip banlist to convert it!", ioexception);
/* 178 */         return false;
/*     */       } 
/*     */     } 
/* 181 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean c(final MinecraftServer minecraftserver) {
/* 186 */     final OpList oplist = new OpList(PlayerList.c);
/*     */     
/* 188 */     if (c.exists() && c.isFile()) {
/* 189 */       if (oplist.c().exists()) {
/*     */         try {
/* 191 */           oplist.load();
/*     */         }
/* 193 */         catch (IOException iOException) {
/* 194 */           e.warn("Could not load existing file {}", oplist.c().getName());
/*     */         } 
/*     */       }
/*     */       
/*     */       try {
/* 199 */         List<String> list = Files.readLines(c, StandardCharsets.UTF_8);
/* 200 */         ProfileLookupCallback profilelookupcallback = new ProfileLookupCallback() {
/*     */             public void onProfileLookupSucceeded(GameProfile gameprofile) {
/* 202 */               minecraftserver.getUserCache().a(gameprofile);
/* 203 */               oplist.add(new OpListEntry(gameprofile, minecraftserver.q(), false));
/*     */             }
/*     */             
/*     */             public void onProfileLookupFailed(GameProfile gameprofile, Exception exception) {
/* 207 */               NameReferencingFileConverter.e.warn("Could not lookup oplist entry for {}", gameprofile.getName(), exception);
/* 208 */               if (!(exception instanceof com.mojang.authlib.yggdrasil.ProfileNotFoundException)) {
/* 209 */                 throw new NameReferencingFileConverter.FileConversionException("Could not request user " + gameprofile.getName() + " from backend systems", exception, null);
/*     */               }
/*     */             }
/*     */           };
/*     */         
/* 214 */         a(minecraftserver, list, profilelookupcallback);
/* 215 */         oplist.save();
/* 216 */         c(c);
/* 217 */         return true;
/* 218 */       } catch (IOException ioexception) {
/* 219 */         e.warn("Could not read old oplist to convert it!", ioexception);
/* 220 */         return false;
/* 221 */       } catch (FileConversionException namereferencingfileconverter_fileconversionexception) {
/* 222 */         e.error("Conversion failed, please try again later", namereferencingfileconverter_fileconversionexception);
/* 223 */         return false;
/*     */       } 
/*     */     } 
/* 226 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean d(final MinecraftServer minecraftserver) {
/* 231 */     final WhiteList whitelist = new WhiteList(PlayerList.d);
/*     */     
/* 233 */     if (d.exists() && d.isFile()) {
/* 234 */       if (whitelist.c().exists()) {
/*     */         try {
/* 236 */           whitelist.load();
/*     */         }
/* 238 */         catch (IOException iOException) {
/* 239 */           e.warn("Could not load existing file {}", whitelist.c().getName());
/*     */         } 
/*     */       }
/*     */       
/*     */       try {
/* 244 */         List<String> list = Files.readLines(d, StandardCharsets.UTF_8);
/* 245 */         ProfileLookupCallback profilelookupcallback = new ProfileLookupCallback() {
/*     */             public void onProfileLookupSucceeded(GameProfile gameprofile) {
/* 247 */               minecraftserver.getUserCache().a(gameprofile);
/* 248 */               whitelist.add(new WhiteListEntry(gameprofile));
/*     */             }
/*     */             
/*     */             public void onProfileLookupFailed(GameProfile gameprofile, Exception exception) {
/* 252 */               NameReferencingFileConverter.e.warn("Could not lookup user whitelist entry for {}", gameprofile.getName(), exception);
/* 253 */               if (!(exception instanceof com.mojang.authlib.yggdrasil.ProfileNotFoundException)) {
/* 254 */                 throw new NameReferencingFileConverter.FileConversionException("Could not request user " + gameprofile.getName() + " from backend systems", exception, null);
/*     */               }
/*     */             }
/*     */           };
/*     */         
/* 259 */         a(minecraftserver, list, profilelookupcallback);
/* 260 */         whitelist.save();
/* 261 */         c(d);
/* 262 */         return true;
/* 263 */       } catch (IOException ioexception) {
/* 264 */         e.warn("Could not read old whitelist to convert it!", ioexception);
/* 265 */         return false;
/* 266 */       } catch (FileConversionException namereferencingfileconverter_fileconversionexception) {
/* 267 */         e.error("Conversion failed, please try again later", namereferencingfileconverter_fileconversionexception);
/* 268 */         return false;
/*     */       } 
/*     */     } 
/* 271 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String a(final MinecraftServer minecraftserver, String s) {
/* 276 */     if (!UtilColor.b(s) && s.length() <= 16) {
/* 277 */       GameProfile gameprofile = minecraftserver.getUserCache().getProfile(s);
/*     */       
/* 279 */       if (gameprofile != null && gameprofile.getId() != null)
/* 280 */         return gameprofile.getId().toString(); 
/* 281 */       if (!minecraftserver.R() && minecraftserver.getOnlineMode()) {
/* 282 */         final ArrayList<GameProfile> arraylist = Lists.newArrayList();
/* 283 */         ProfileLookupCallback profilelookupcallback = new ProfileLookupCallback() {
/*     */             public void onProfileLookupSucceeded(GameProfile gameprofile) {
/* 285 */               minecraftserver.getUserCache().a(gameprofile);
/* 286 */               arraylist.add(gameprofile);
/*     */             }
/*     */             
/*     */             public void onProfileLookupFailed(GameProfile gameprofile, Exception exception) {
/* 290 */               NameReferencingFileConverter.e.warn("Could not lookup user whitelist entry for {}", gameprofile.getName(), exception);
/*     */             }
/*     */           };
/*     */         
/* 294 */         a(minecraftserver, Lists.newArrayList((Object[])new String[] { s }, ), profilelookupcallback);
/* 295 */         return (!arraylist.isEmpty() && ((GameProfile)arraylist.get(0)).getId() != null) ? ((GameProfile)arraylist.get(0)).getId().toString() : "";
/*     */       } 
/* 297 */       return EntityHuman.a(new GameProfile(null, s)).toString();
/*     */     } 
/*     */     
/* 300 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean a(final DedicatedServer dedicatedserver, PropertyManager propertymanager) {
/* 305 */     final File file = d(propertymanager);
/*     */     
/* 307 */     final File file2 = new File(file.getParentFile(), "unknownplayers");
/*     */     
/* 309 */     if (file.exists() && file.isDirectory()) {
/* 310 */       File[] afile = file.listFiles();
/* 311 */       ArrayList<String> arraylist = Lists.newArrayList();
/* 312 */       File[] afile1 = afile;
/* 313 */       int i = afile.length;
/*     */       
/* 315 */       for (int j = 0; j < i; j++) {
/* 316 */         File file3 = afile1[j];
/* 317 */         String s = file3.getName();
/*     */         
/* 319 */         if (s.toLowerCase(Locale.ROOT).endsWith(".dat")) {
/* 320 */           String s1 = s.substring(0, s.length() - ".dat".length());
/*     */           
/* 322 */           if (!s1.isEmpty()) {
/* 323 */             arraylist.add(s1);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/*     */       try {
/* 329 */         final String[] astring = arraylist.<String>toArray(new String[arraylist.size()]);
/* 330 */         ProfileLookupCallback profilelookupcallback = new ProfileLookupCallback() {
/*     */             public void onProfileLookupSucceeded(GameProfile gameprofile) {
/* 332 */               dedicatedserver.getUserCache().a(gameprofile);
/* 333 */               UUID uuid = gameprofile.getId();
/*     */               
/* 335 */               if (uuid == null) {
/* 336 */                 throw new NameReferencingFileConverter.FileConversionException("Missing UUID for user profile " + gameprofile.getName(), null, null);
/*     */               }
/* 338 */               a(file, a(gameprofile), uuid.toString());
/*     */             }
/*     */ 
/*     */             
/*     */             public void onProfileLookupFailed(GameProfile gameprofile, Exception exception) {
/* 343 */               NameReferencingFileConverter.e.warn("Could not lookup user uuid for {}", gameprofile.getName(), exception);
/* 344 */               if (exception instanceof com.mojang.authlib.yggdrasil.ProfileNotFoundException) {
/* 345 */                 String s = a(gameprofile);
/*     */                 
/* 347 */                 a(file, s, s);
/*     */               } else {
/* 349 */                 throw new NameReferencingFileConverter.FileConversionException("Could not request user " + gameprofile.getName() + " from backend systems", exception, null);
/*     */               } 
/*     */             }
/*     */             
/*     */             private void a(File file, String s, String s1) {
/* 354 */               File file1 = new File(file2, String.valueOf(s) + ".dat");
/* 355 */               File file3 = new File(file, String.valueOf(s1) + ".dat");
/*     */ 
/*     */               
/* 358 */               NBTTagCompound root = null;
/*     */               
/*     */               try {
/* 361 */                 root = NBTCompressedStreamTools.a(new FileInputStream(file1));
/* 362 */               } catch (Exception exception) {
/* 363 */                 exception.printStackTrace();
/*     */               } 
/*     */               
/* 366 */               if (root != null) {
/* 367 */                 if (!root.hasKey("bukkit")) {
/* 368 */                   root.set("bukkit", new NBTTagCompound());
/*     */                 }
/* 370 */                 NBTTagCompound data = root.getCompound("bukkit");
/* 371 */                 data.setString("lastKnownName", s);
/*     */                 
/*     */                 try {
/* 374 */                   NBTCompressedStreamTools.a(root, new FileOutputStream(file2));
/* 375 */                 } catch (Exception exception) {
/* 376 */                   exception.printStackTrace();
/*     */                 } 
/*     */               } 
/*     */ 
/*     */               
/* 381 */               NameReferencingFileConverter.b(file);
/* 382 */               if (!file1.renameTo(file3)) {
/* 383 */                 throw new NameReferencingFileConverter.FileConversionException("Could not convert file for " + s, null, null);
/*     */               }
/*     */             }
/*     */             
/*     */             private String a(GameProfile gameprofile) {
/* 388 */               String s = null;
/*     */               
/* 390 */               int i = astring.length;
/*     */               
/* 392 */               for (int j = 0; j < i; j++) {
/* 393 */                 String s1 = astring[j];
/*     */                 
/* 395 */                 if (s1 != null && s1.equalsIgnoreCase(gameprofile.getName())) {
/* 396 */                   s = s1;
/*     */                   
/*     */                   break;
/*     */                 } 
/*     */               } 
/* 401 */               if (s == null) {
/* 402 */                 throw new NameReferencingFileConverter.FileConversionException("Could not find the filename for " + gameprofile.getName() + " anymore", null, null);
/*     */               }
/* 404 */               return s;
/*     */             }
/*     */           };
/*     */ 
/*     */         
/* 409 */         a(dedicatedserver, Lists.newArrayList((Object[])astring), profilelookupcallback);
/* 410 */         return true;
/* 411 */       } catch (FileConversionException namereferencingfileconverter_fileconversionexception) {
/* 412 */         e.error("Conversion failed, please try again later", namereferencingfileconverter_fileconversionexception);
/* 413 */         return false;
/*     */       } 
/*     */     } 
/* 416 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void b(File file) {
/* 421 */     if (file.exists()) {
/* 422 */       if (!file.isDirectory()) {
/* 423 */         throw new FileConversionException("Can't create directory " + file.getName() + " in world save directory.", null, null);
/*     */       }
/* 425 */     } else if (!file.mkdirs()) {
/* 426 */       throw new FileConversionException("Can't create directory " + file.getName() + " in world save directory.", null, null);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean a(PropertyManager propertymanager) {
/* 431 */     boolean flag = b(propertymanager);
/*     */     
/* 433 */     flag = (flag && c(propertymanager));
/* 434 */     return flag;
/*     */   }
/*     */   
/*     */   private static boolean b(PropertyManager propertymanager) {
/* 438 */     boolean flag = false;
/*     */     
/* 440 */     if (b.exists() && b.isFile()) {
/* 441 */       flag = true;
/*     */     }
/*     */     
/* 444 */     boolean flag1 = false;
/*     */     
/* 446 */     if (a.exists() && a.isFile()) {
/* 447 */       flag1 = true;
/*     */     }
/*     */     
/* 450 */     boolean flag2 = false;
/*     */     
/* 452 */     if (c.exists() && c.isFile()) {
/* 453 */       flag2 = true;
/*     */     }
/*     */     
/* 456 */     boolean flag3 = false;
/*     */     
/* 458 */     if (d.exists() && d.isFile()) {
/* 459 */       flag3 = true;
/*     */     }
/*     */     
/* 462 */     if (!flag && !flag1 && !flag2 && !flag3) {
/* 463 */       return true;
/*     */     }
/* 465 */     e.warn("**** FAILED TO START THE SERVER AFTER ACCOUNT CONVERSION!");
/* 466 */     e.warn("** please remove the following files and restart the server:");
/* 467 */     if (flag) {
/* 468 */       e.warn("* {}", b.getName());
/*     */     }
/*     */     
/* 471 */     if (flag1) {
/* 472 */       e.warn("* {}", a.getName());
/*     */     }
/*     */     
/* 475 */     if (flag2) {
/* 476 */       e.warn("* {}", c.getName());
/*     */     }
/*     */     
/* 479 */     if (flag3) {
/* 480 */       e.warn("* {}", d.getName());
/*     */     }
/*     */     
/* 483 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean c(PropertyManager propertymanager) {
/* 488 */     File file = d(propertymanager);
/*     */     
/* 490 */     if (file.exists() && file.isDirectory() && ((file.list()).length > 0 || !file.delete())) {
/* 491 */       e.warn("**** DETECTED OLD PLAYER DIRECTORY IN THE WORLD SAVE");
/* 492 */       e.warn("**** THIS USUALLY HAPPENS WHEN THE AUTOMATIC CONVERSION FAILED IN SOME WAY");
/* 493 */       e.warn("** please restart the server and if the problem persists, remove the directory '{}'", file.getPath());
/* 494 */       return false;
/*     */     } 
/* 496 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private static File d(PropertyManager propertymanager) {
/* 501 */     String s = propertymanager.getString("level-name", "world");
/* 502 */     File file = new File((MinecraftServer.getServer()).server.getWorldContainer(), s);
/*     */     
/* 504 */     return new File(file, "players");
/*     */   }
/*     */   
/*     */   private static void c(File file) {
/* 508 */     File file1 = new File(String.valueOf(file.getName()) + ".converted");
/*     */     
/* 510 */     file.renameTo(file1);
/*     */   }
/*     */ 
/*     */   
/*     */   private static Date b(String s, Date date) {
/*     */     Date date1;
/*     */     try {
/* 517 */       date1 = ExpirableListEntry.a.parse(s);
/* 518 */     } catch (ParseException parseException) {
/* 519 */       date1 = date;
/*     */     } 
/*     */     
/* 522 */     return date1;
/*     */   }
/*     */   
/*     */   static class FileConversionException
/*     */     extends RuntimeException {
/*     */     private FileConversionException(String s, Throwable throwable) {
/* 528 */       super(s, throwable);
/*     */     }
/*     */     
/*     */     private FileConversionException(String s) {
/* 532 */       super(s);
/*     */     }
/*     */     
/*     */     FileConversionException(String s, Object object) {
/* 536 */       this(s);
/*     */     }
/*     */     
/*     */     FileConversionException(String s, Throwable throwable, Object object) {
/* 540 */       this(s, throwable);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\NameReferencingFileConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */