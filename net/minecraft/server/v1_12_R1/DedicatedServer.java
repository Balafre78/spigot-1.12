/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import com.mojang.authlib.GameProfileRepository;
/*     */ import com.mojang.authlib.minecraft.MinecraftSessionService;
/*     */ import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.net.InetAddress;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.ExecutionException;
/*     */ import java.util.logging.Handler;
/*     */ import java.util.logging.Logger;
/*     */ import java.util.regex.Pattern;
/*     */ import org.apache.logging.log4j.Level;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.apache.logging.log4j.core.Appender;
/*     */ import org.apache.logging.log4j.core.Logger;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.craftbukkit.Main;
/*     */ import org.bukkit.craftbukkit.libs.jline.console.ConsoleReader;
/*     */ import org.bukkit.craftbukkit.libs.joptsimple.OptionSet;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.LoggerOutputStream;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.SpigotTimings;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.util.Waitable;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.server.RemoteServerCommandEvent;
/*     */ import org.bukkit.event.server.ServerCommandEvent;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.PluginLoadOrder;
/*     */ import org.spigotmc.SpigotConfig;
/*     */ 
/*     */ public class DedicatedServer extends MinecraftServer implements IMinecraftServer {
/*  35 */   private static final Logger LOGGER = LogManager.getLogger();
/*  36 */   private static final Pattern l = Pattern.compile("^[a-fA-F0-9]{40}$");
/*  37 */   private final List<ServerCommand> serverCommandQueue = Collections.synchronizedList(Lists.newArrayList());
/*     */   private RemoteStatusListener n;
/*  39 */   public final RemoteControlCommandListener remoteControlCommandListener = new RemoteControlCommandListener(this);
/*     */   
/*     */   private RemoteControlListener p;
/*     */   public PropertyManager propertyManager;
/*     */   private EULA r;
/*     */   private boolean generateStructures;
/*     */   private EnumGamemode t;
/*     */   private boolean u;
/*     */   
/*     */   public DedicatedServer(OptionSet options, DataConverterManager dataconvertermanager, YggdrasilAuthenticationService yggdrasilauthenticationservice, MinecraftSessionService minecraftsessionservice, GameProfileRepository gameprofilerepository, UserCache usercache) {
/*  49 */     super(options, Proxy.NO_PROXY, dataconvertermanager, yggdrasilauthenticationservice, minecraftsessionservice, gameprofilerepository, usercache);
/*     */   }
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
/*     */   public boolean init() throws IOException {
/*  70 */     Thread thread = new Thread("Server console handler")
/*     */       {
/*     */         public void run() {
/*  73 */           if (!Main.useConsole) {
/*     */             return;
/*     */           }
/*  76 */           ConsoleReader bufferedreader = DedicatedServer.this.reader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           try {
/*  83 */             while (!DedicatedServer.this.isStopped() && DedicatedServer.this.isRunning()) {
/*  84 */               String s; if (Main.useJline) {
/*  85 */                 s = bufferedreader.readLine(">", null);
/*     */               } else {
/*  87 */                 s = bufferedreader.readLine();
/*     */               } 
/*  89 */               if (s != null && s.trim().length() > 0) {
/*  90 */                 DedicatedServer.this.issueCommand(s, DedicatedServer.this);
/*     */               }
/*     */             }
/*     */           
/*  94 */           } catch (IOException ioexception) {
/*  95 */             DedicatedServer.LOGGER.error("Exception handling console input", ioexception);
/*     */           } 
/*     */         }
/*     */       };
/*     */ 
/*     */ 
/*     */     
/* 102 */     Logger global = Logger.getLogger("");
/* 103 */     global.setUseParentHandlers(false); Handler[] arrayOfHandler; int m; byte b;
/* 104 */     for (m = (arrayOfHandler = global.getHandlers()).length, b = 0; b < m; ) { Handler handler = arrayOfHandler[b];
/* 105 */       global.removeHandler(handler); b++; }
/*     */     
/* 107 */     global.addHandler((Handler)new ForwardLogHandler());
/*     */     
/* 109 */     Logger logger = (Logger)LogManager.getRootLogger();
/* 110 */     for (Appender appender : logger.getAppenders().values()) {
/* 111 */       if (appender instanceof org.apache.logging.log4j.core.appender.ConsoleAppender) {
/* 112 */         logger.removeAppender(appender);
/*     */       }
/*     */     } 
/*     */     
/* 116 */     (new Thread((Runnable)new TerminalConsoleWriterThread(System.out, this.reader))).start();
/*     */     
/* 118 */     System.setOut(new PrintStream((OutputStream)new LoggerOutputStream((Logger)logger, Level.INFO), true));
/* 119 */     System.setErr(new PrintStream((OutputStream)new LoggerOutputStream((Logger)logger, Level.WARN), true));
/*     */ 
/*     */     
/* 122 */     thread.setDaemon(true);
/* 123 */     thread.start();
/* 124 */     LOGGER.info("Starting minecraft server version 1.12");
/* 125 */     if (Runtime.getRuntime().maxMemory() / 1024L / 1024L < 512L) {
/* 126 */       LOGGER.warn("To start the server with more ram, launch it as \"java -Xmx1024M -Xms1024M -jar minecraft_server.jar\"");
/*     */     }
/*     */     
/* 129 */     LOGGER.info("Loading properties");
/* 130 */     this.propertyManager = new PropertyManager(this.options);
/* 131 */     this.r = new EULA(new File("eula.txt"));
/*     */     
/* 133 */     boolean eulaAgreed = Boolean.getBoolean("com.mojang.eula.agree");
/* 134 */     if (eulaAgreed) {
/*     */       
/* 136 */       System.err.println("You have used the Spigot command line EULA agreement flag.");
/* 137 */       System.err.println("By using this setting you are indicating your agreement to Mojang's EULA (https://account.mojang.com/documents/minecraft_eula).");
/* 138 */       System.err.println("If you do not agree to the above EULA please stop your server and remove this flag immediately.");
/*     */     } 
/*     */     
/* 141 */     if (!this.r.a() && !eulaAgreed) {
/* 142 */       LOGGER.info("You need to agree to the EULA in order to run the server. Go to eula.txt for more info.");
/* 143 */       this.r.b();
/* 144 */       return false;
/*     */     } 
/* 146 */     if (R()) {
/* 147 */       c("127.0.0.1");
/*     */     } else {
/* 149 */       setOnlineMode(this.propertyManager.getBoolean("online-mode", true));
/* 150 */       e(this.propertyManager.getBoolean("prevent-proxy-connections", false));
/* 151 */       c(this.propertyManager.getString("server-ip", ""));
/*     */     } 
/*     */     
/* 154 */     setSpawnAnimals(this.propertyManager.getBoolean("spawn-animals", true));
/* 155 */     setSpawnNPCs(this.propertyManager.getBoolean("spawn-npcs", true));
/* 156 */     setPVP(this.propertyManager.getBoolean("pvp", true));
/* 157 */     setAllowFlight(this.propertyManager.getBoolean("allow-flight", false));
/* 158 */     setResourcePack(this.propertyManager.getString("resource-pack", ""), aO());
/* 159 */     setMotd(this.propertyManager.getString("motd", "A Minecraft Server"));
/* 160 */     setForceGamemode(this.propertyManager.getBoolean("force-gamemode", false));
/* 161 */     setIdleTimeout(this.propertyManager.getInt("player-idle-timeout", 0));
/* 162 */     if (this.propertyManager.getInt("difficulty", 1) < 0) {
/* 163 */       this.propertyManager.setProperty("difficulty", Integer.valueOf(0));
/* 164 */     } else if (this.propertyManager.getInt("difficulty", 1) > 3) {
/* 165 */       this.propertyManager.setProperty("difficulty", Integer.valueOf(3));
/*     */     } 
/*     */     
/* 168 */     this.generateStructures = this.propertyManager.getBoolean("generate-structures", true);
/* 169 */     int i = this.propertyManager.getInt("gamemode", EnumGamemode.SURVIVAL.getId());
/*     */     
/* 171 */     this.t = WorldSettings.a(i);
/* 172 */     LOGGER.info("Default game type: {}", this.t);
/* 173 */     InetAddress inetaddress = null;
/*     */     
/* 175 */     if (!getServerIp().isEmpty()) {
/* 176 */       inetaddress = InetAddress.getByName(getServerIp());
/*     */     }
/*     */     
/* 179 */     if (P() < 0) {
/* 180 */       setPort(this.propertyManager.getInt("server-port", 25565));
/*     */     }
/*     */     
/* 183 */     a(new DedicatedPlayerList(this));
/* 184 */     SpigotConfig.init((File)this.options.valueOf("spigot-settings"));
/* 185 */     SpigotConfig.registerCommands();
/*     */ 
/*     */     
/* 188 */     LOGGER.info("Generating keypair");
/* 189 */     a(MinecraftEncryption.b());
/* 190 */     LOGGER.info("Starting Minecraft server on {}:{}", getServerIp().isEmpty() ? "*" : getServerIp(), Integer.valueOf(P()));
/*     */     
/* 192 */     if (!SpigotConfig.lateBind) {
/*     */       try {
/* 194 */         an().a(inetaddress, P());
/* 195 */       } catch (IOException ioexception) {
/* 196 */         LOGGER.warn("**** FAILED TO BIND TO PORT!");
/* 197 */         LOGGER.warn("The exception was: {}", ioexception.toString());
/* 198 */         LOGGER.warn("Perhaps a server is already running on that port?");
/* 199 */         return false;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 205 */     this.server.loadPlugins();
/* 206 */     this.server.enablePlugins(PluginLoadOrder.STARTUP);
/*     */ 
/*     */     
/* 209 */     if (!getOnlineMode()) {
/* 210 */       LOGGER.warn("**** SERVER IS RUNNING IN OFFLINE/INSECURE MODE!");
/* 211 */       LOGGER.warn("The server will make no attempt to authenticate usernames. Beware.");
/*     */       
/* 213 */       if (SpigotConfig.bungee) {
/* 214 */         LOGGER.warn("Whilst this makes it possible to use BungeeCord, unless access to your server is properly restricted, it also opens up the ability for hackers to connect with any username they choose.");
/* 215 */         LOGGER.warn("Please see http://www.spigotmc.org/wiki/firewall-guide/ for further information.");
/*     */       } else {
/* 217 */         LOGGER.warn("While this makes the game possible to play without internet access, it also opens up the ability for hackers to connect with any username they choose.");
/*     */       } 
/*     */       
/* 220 */       LOGGER.warn("To change this, set \"online-mode\" to \"true\" in the server.properties file.");
/*     */     } 
/*     */     
/* 223 */     if (aS()) {
/* 224 */       getUserCache().c();
/*     */     }
/*     */     
/* 227 */     if (!NameReferencingFileConverter.a(this.propertyManager)) {
/* 228 */       return false;
/*     */     }
/* 230 */     this.convertable = new WorldLoaderServer(this.server.getWorldContainer(), this.dataConverterManager);
/* 231 */     long j = System.nanoTime();
/*     */     
/* 233 */     if (S() == null) {
/* 234 */       setWorld(this.propertyManager.getString("level-name", "world"));
/*     */     }
/*     */     
/* 237 */     String s = this.propertyManager.getString("level-seed", "");
/* 238 */     String s1 = this.propertyManager.getString("level-type", "DEFAULT");
/* 239 */     String s2 = this.propertyManager.getString("generator-settings", "");
/* 240 */     long k = (new Random()).nextLong();
/*     */     
/* 242 */     if (!s.isEmpty()) {
/*     */       try {
/* 244 */         long l = Long.parseLong(s);
/*     */         
/* 246 */         if (l != 0L) {
/* 247 */           k = l;
/*     */         }
/* 249 */       } catch (NumberFormatException numberFormatException) {
/* 250 */         k = s.hashCode();
/*     */       } 
/*     */     }
/*     */     
/* 254 */     WorldType worldtype = WorldType.getType(s1);
/*     */     
/* 256 */     if (worldtype == null) {
/* 257 */       worldtype = WorldType.NORMAL;
/*     */     }
/*     */     
/* 260 */     getEnableCommandBlock();
/* 261 */     q();
/* 262 */     getSnooperEnabled();
/* 263 */     aG();
/* 264 */     c(this.propertyManager.getInt("max-build-height", 256));
/* 265 */     c((getMaxBuildHeight() + 8) / 16 * 16);
/* 266 */     c(MathHelper.clamp(getMaxBuildHeight(), 64, 256));
/* 267 */     this.propertyManager.setProperty("max-build-height", Integer.valueOf(getMaxBuildHeight()));
/* 268 */     TileEntitySkull.a(getUserCache());
/* 269 */     TileEntitySkull.a(az());
/* 270 */     UserCache.a(getOnlineMode());
/* 271 */     LOGGER.info("Preparing level \"{}\"", S());
/* 272 */     a(S(), S(), k, worldtype, s2);
/* 273 */     long i1 = System.nanoTime() - j;
/* 274 */     String s3 = String.format("%.3fs", new Object[] { Double.valueOf(i1 / 1.0E9D) });
/*     */     
/* 276 */     LOGGER.info("Done ({})! For help, type \"help\" or \"?\"", s3);
/* 277 */     if (this.propertyManager.a("announce-player-achievements")) {
/* 278 */       ((WorldServer)this.worlds.get(0)).getGameRules().set("announceAdvancements", this.propertyManager.getBoolean("announce-player-achievements", true) ? "true" : "false");
/* 279 */       this.propertyManager.b("announce-player-achievements");
/* 280 */       this.propertyManager.savePropertiesFile();
/*     */     } 
/*     */     
/* 283 */     if (this.propertyManager.getBoolean("enable-query", false)) {
/* 284 */       LOGGER.info("Starting GS4 status listener");
/* 285 */       this.n = new RemoteStatusListener(this);
/* 286 */       this.n.a();
/*     */     } 
/*     */     
/* 289 */     if (this.propertyManager.getBoolean("enable-rcon", false)) {
/* 290 */       LOGGER.info("Starting remote control listener");
/* 291 */       this.p = new RemoteControlListener(this);
/* 292 */       this.p.a();
/* 293 */       this.remoteConsole = (RemoteConsoleCommandSender)new CraftRemoteConsoleCommandSender(this.remoteControlCommandListener);
/*     */     } 
/*     */ 
/*     */     
/* 297 */     if (this.server.getBukkitSpawnRadius() > -1) {
/* 298 */       LOGGER.info("'settings.spawn-radius' in bukkit.yml has been moved to 'spawn-protection' in server.properties. I will move your config for you.");
/* 299 */       this.propertyManager.properties.remove("spawn-protection");
/* 300 */       this.propertyManager.getInt("spawn-protection", this.server.getBukkitSpawnRadius());
/* 301 */       this.server.removeBukkitSpawnRadius();
/* 302 */       this.propertyManager.savePropertiesFile();
/*     */     } 
/*     */ 
/*     */     
/* 306 */     if (SpigotConfig.lateBind) {
/*     */       try {
/* 308 */         an().a(inetaddress, P());
/* 309 */       } catch (IOException ioexception) {
/* 310 */         LOGGER.warn("**** FAILED TO BIND TO PORT!");
/* 311 */         LOGGER.warn("The exception was: {}", new Object[] { ioexception.toString() });
/* 312 */         LOGGER.warn("Perhaps a server is already running on that port?");
/* 313 */         return false;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 325 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String aO() {
/* 331 */     if (this.propertyManager.a("resource-pack-hash")) {
/* 332 */       if (this.propertyManager.a("resource-pack-sha1")) {
/* 333 */         LOGGER.warn("resource-pack-hash is deprecated and found along side resource-pack-sha1. resource-pack-hash will be ignored.");
/*     */       } else {
/* 335 */         LOGGER.warn("resource-pack-hash is deprecated. Please use resource-pack-sha1 instead.");
/* 336 */         this.propertyManager.getString("resource-pack-sha1", this.propertyManager.getString("resource-pack-hash", ""));
/* 337 */         this.propertyManager.b("resource-pack-hash");
/*     */       } 
/*     */     }
/*     */     
/* 341 */     String s = this.propertyManager.getString("resource-pack-sha1", "");
/*     */     
/* 343 */     if (!s.isEmpty() && !l.matcher(s).matches()) {
/* 344 */       LOGGER.warn("Invalid sha1 for ressource-pack-sha1");
/*     */     }
/*     */     
/* 347 */     if (!this.propertyManager.getString("resource-pack", "").isEmpty() && s.isEmpty()) {
/* 348 */       LOGGER.warn("You specified a resource pack without providing a sha1 hash. Pack will be updated on the client only if you change the name of the pack.");
/*     */     }
/*     */     
/* 351 */     return s;
/*     */   }
/*     */   
/*     */   public void setGamemode(EnumGamemode enumgamemode) {
/* 355 */     super.setGamemode(enumgamemode);
/* 356 */     this.t = enumgamemode;
/*     */   }
/*     */   
/*     */   public boolean getGenerateStructures() {
/* 360 */     return this.generateStructures;
/*     */   }
/*     */   
/*     */   public EnumGamemode getGamemode() {
/* 364 */     return this.t;
/*     */   }
/*     */   
/*     */   public EnumDifficulty getDifficulty() {
/* 368 */     return EnumDifficulty.getById(this.propertyManager.getInt("difficulty", EnumDifficulty.NORMAL.a()));
/*     */   }
/*     */   
/*     */   public boolean isHardcore() {
/* 372 */     return this.propertyManager.getBoolean("hardcore", false);
/*     */   }
/*     */   
/*     */   public CrashReport b(CrashReport crashreport) {
/* 376 */     crashreport = super.b(crashreport);
/* 377 */     crashreport.g().a("Is Modded", new CrashReportCallable<String>() {
/*     */           public String a() throws Exception {
/* 379 */             String s = DedicatedServer.this.getServerModName();
/*     */             
/* 381 */             return !"vanilla".equals(s) ? ("Definitely; Server brand changed to '" + s + "'") : "Unknown (can't tell)";
/*     */           }
/*     */           
/*     */           public Object call() throws Exception {
/* 385 */             return a();
/*     */           }
/*     */         });
/* 388 */     crashreport.g().a("Type", new CrashReportCallable<String>() {
/*     */           public String a() throws Exception {
/* 390 */             return "Dedicated Server (map_server.txt)";
/*     */           }
/*     */           
/*     */           public Object call() throws Exception {
/* 394 */             return a();
/*     */           }
/*     */         });
/* 397 */     return crashreport;
/*     */   }
/*     */   
/*     */   public void B() {
/* 401 */     System.exit(0);
/*     */   }
/*     */   
/*     */   public void D() {
/* 405 */     super.D();
/* 406 */     aP();
/*     */   }
/*     */   
/*     */   public boolean getAllowNether() {
/* 410 */     return this.propertyManager.getBoolean("allow-nether", true);
/*     */   }
/*     */   
/*     */   public boolean getSpawnMonsters() {
/* 414 */     return this.propertyManager.getBoolean("spawn-monsters", true);
/*     */   }
/*     */   
/*     */   public void a(MojangStatisticsGenerator mojangstatisticsgenerator) {
/* 418 */     mojangstatisticsgenerator.a("whitelist_enabled", Boolean.valueOf(aQ().getHasWhitelist()));
/* 419 */     mojangstatisticsgenerator.a("whitelist_count", Integer.valueOf((aQ().getWhitelisted()).length));
/* 420 */     super.a(mojangstatisticsgenerator);
/*     */   }
/*     */   
/*     */   public boolean getSnooperEnabled() {
/* 424 */     return this.propertyManager.getBoolean("snooper-enabled", true);
/*     */   }
/*     */   
/*     */   public void issueCommand(String s, ICommandListener icommandlistener) {
/* 428 */     this.serverCommandQueue.add(new ServerCommand(s, icommandlistener));
/*     */   }
/*     */   
/*     */   public void aP() {
/* 432 */     SpigotTimings.serverCommandTimer.startTiming();
/* 433 */     while (!this.serverCommandQueue.isEmpty()) {
/* 434 */       ServerCommand servercommand = this.serverCommandQueue.remove(0);
/*     */ 
/*     */       
/* 437 */       ServerCommandEvent event = new ServerCommandEvent((CommandSender)this.console, servercommand.command);
/* 438 */       this.server.getPluginManager().callEvent((Event)event);
/* 439 */       if (event.isCancelled())
/* 440 */         continue;  servercommand = new ServerCommand(event.getCommand(), servercommand.source);
/*     */ 
/*     */       
/* 443 */       this.server.dispatchServerCommand((CommandSender)this.console, servercommand);
/*     */     } 
/*     */ 
/*     */     
/* 447 */     SpigotTimings.serverCommandTimer.stopTiming();
/*     */   }
/*     */   
/*     */   public boolean aa() {
/* 451 */     return true;
/*     */   }
/*     */   
/*     */   public boolean af() {
/* 455 */     return this.propertyManager.getBoolean("use-native-transport", true);
/*     */   }
/*     */   
/*     */   public DedicatedPlayerList aQ() {
/* 459 */     return (DedicatedPlayerList)super.getPlayerList();
/*     */   }
/*     */   
/*     */   public int a(String s, int i) {
/* 463 */     return this.propertyManager.getInt(s, i);
/*     */   }
/*     */   
/*     */   public String a(String s, String s1) {
/* 467 */     return this.propertyManager.getString(s, s1);
/*     */   }
/*     */   
/*     */   public boolean a(String s, boolean flag) {
/* 471 */     return this.propertyManager.getBoolean(s, flag);
/*     */   }
/*     */   
/*     */   public void a(String s, Object object) {
/* 475 */     this.propertyManager.setProperty(s, object);
/*     */   }
/*     */   
/*     */   public void a() {
/* 479 */     this.propertyManager.savePropertiesFile();
/*     */   }
/*     */   
/*     */   public String b() {
/* 483 */     File file = this.propertyManager.c();
/*     */     
/* 485 */     return (file != null) ? file.getAbsolutePath() : "No settings file";
/*     */   }
/*     */   
/*     */   public String d_() {
/* 489 */     return getServerIp();
/*     */   }
/*     */   
/*     */   public int e_() {
/* 493 */     return P();
/*     */   }
/*     */   
/*     */   public String f_() {
/* 497 */     return getMotd();
/*     */   }
/*     */   
/*     */   public void aR() {
/* 501 */     ServerGUI.a(this);
/* 502 */     this.u = true;
/*     */   }
/*     */   
/*     */   public boolean ap() {
/* 506 */     return this.u;
/*     */   }
/*     */   
/*     */   public String a(EnumGamemode enumgamemode, boolean flag) {
/* 510 */     return "";
/*     */   }
/*     */   
/*     */   public boolean getEnableCommandBlock() {
/* 514 */     return this.propertyManager.getBoolean("enable-command-block", false);
/*     */   }
/*     */   
/*     */   public int getSpawnProtection() {
/* 518 */     return this.propertyManager.getInt("spawn-protection", super.getSpawnProtection());
/*     */   }
/*     */   
/*     */   public boolean a(World world, BlockPosition blockposition, EntityHuman entityhuman) {
/* 522 */     if (world.worldProvider.getDimensionManager().getDimensionID() != 0)
/* 523 */       return false; 
/* 524 */     if (aQ().getOPs().isEmpty())
/* 525 */       return false; 
/* 526 */     if (aQ().isOp(entityhuman.getProfile()))
/* 527 */       return false; 
/* 528 */     if (getSpawnProtection() <= 0) {
/* 529 */       return false;
/*     */     }
/* 531 */     BlockPosition blockposition1 = world.getSpawn();
/* 532 */     int i = MathHelper.a(blockposition.getX() - blockposition1.getX());
/* 533 */     int j = MathHelper.a(blockposition.getZ() - blockposition1.getZ());
/* 534 */     int k = Math.max(i, j);
/*     */     
/* 536 */     return (k <= getSpawnProtection());
/*     */   }
/*     */ 
/*     */   
/*     */   public int q() {
/* 541 */     return this.propertyManager.getInt("op-permission-level", 4);
/*     */   }
/*     */   
/*     */   public void setIdleTimeout(int i) {
/* 545 */     super.setIdleTimeout(i);
/* 546 */     this.propertyManager.setProperty("player-idle-timeout", Integer.valueOf(i));
/* 547 */     a();
/*     */   }
/*     */   
/*     */   public boolean r() {
/* 551 */     return this.propertyManager.getBoolean("broadcast-rcon-to-ops", true);
/*     */   }
/*     */   
/*     */   public boolean s() {
/* 555 */     return this.propertyManager.getBoolean("broadcast-console-to-ops", true);
/*     */   }
/*     */   
/*     */   public int aE() {
/* 559 */     int i = this.propertyManager.getInt("max-world-size", super.aE());
/*     */     
/* 561 */     if (i < 1) {
/* 562 */       i = 1;
/* 563 */     } else if (i > super.aE()) {
/* 564 */       i = super.aE();
/*     */     } 
/*     */     
/* 567 */     return i;
/*     */   }
/*     */   
/*     */   public int aG() {
/* 571 */     return this.propertyManager.getInt("network-compression-threshold", super.aG());
/*     */   }
/*     */   
/*     */   protected boolean aS() {
/* 575 */     this.server.getLogger().info("**** Beginning UUID conversion, this may take A LONG time ****");
/* 576 */     boolean flag = false;
/*     */     
/*     */     int i;
/*     */     
/* 580 */     for (i = 0; !flag && i <= 2; i++) {
/* 581 */       if (i > 0) {
/* 582 */         LOGGER.warn("Encountered a problem while converting the user banlist, retrying in a few seconds");
/* 583 */         aV();
/*     */       } 
/*     */       
/* 586 */       flag = NameReferencingFileConverter.a(this);
/*     */     } 
/*     */     
/* 589 */     boolean flag1 = false;
/*     */     
/* 591 */     for (i = 0; !flag1 && i <= 2; i++) {
/* 592 */       if (i > 0) {
/* 593 */         LOGGER.warn("Encountered a problem while converting the ip banlist, retrying in a few seconds");
/* 594 */         aV();
/*     */       } 
/*     */       
/* 597 */       flag1 = NameReferencingFileConverter.b(this);
/*     */     } 
/*     */     
/* 600 */     boolean flag2 = false;
/*     */     
/* 602 */     for (i = 0; !flag2 && i <= 2; i++) {
/* 603 */       if (i > 0) {
/* 604 */         LOGGER.warn("Encountered a problem while converting the op list, retrying in a few seconds");
/* 605 */         aV();
/*     */       } 
/*     */       
/* 608 */       flag2 = NameReferencingFileConverter.c(this);
/*     */     } 
/*     */     
/* 611 */     boolean flag3 = false;
/*     */     
/* 613 */     for (i = 0; !flag3 && i <= 2; i++) {
/* 614 */       if (i > 0) {
/* 615 */         LOGGER.warn("Encountered a problem while converting the whitelist, retrying in a few seconds");
/* 616 */         aV();
/*     */       } 
/*     */       
/* 619 */       flag3 = NameReferencingFileConverter.d(this);
/*     */     } 
/*     */     
/* 622 */     boolean flag4 = false;
/*     */     
/* 624 */     for (i = 0; !flag4 && i <= 2; i++) {
/* 625 */       if (i > 0) {
/* 626 */         LOGGER.warn("Encountered a problem while converting the player save files, retrying in a few seconds");
/* 627 */         aV();
/*     */       } 
/*     */       
/* 630 */       flag4 = NameReferencingFileConverter.a(this, this.propertyManager);
/*     */     } 
/*     */     
/* 633 */     return !(!flag && !flag1 && !flag2 && !flag3 && !flag4);
/*     */   }
/*     */   
/*     */   private void aV() {
/*     */     try {
/* 638 */       Thread.sleep(5000L);
/* 639 */     } catch (InterruptedException interruptedException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long aT() {
/* 645 */     return this.propertyManager.getLong("max-tick-time", TimeUnit.MINUTES.toMillis(1L));
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPlugins() {
/* 650 */     StringBuilder result = new StringBuilder();
/* 651 */     Plugin[] plugins = this.server.getPluginManager().getPlugins();
/*     */     
/* 653 */     result.append(this.server.getName());
/* 654 */     result.append(" on Bukkit ");
/* 655 */     result.append(this.server.getBukkitVersion());
/*     */     
/* 657 */     if (plugins.length > 0 && this.server.getQueryPlugins()) {
/* 658 */       result.append(": ");
/*     */       
/* 660 */       for (int i = 0; i < plugins.length; i++) {
/* 661 */         if (i > 0) {
/* 662 */           result.append("; ");
/*     */         }
/*     */         
/* 665 */         result.append(plugins[i].getDescription().getName());
/* 666 */         result.append(" ");
/* 667 */         result.append(plugins[i].getDescription().getVersion().replaceAll(";", ","));
/*     */       } 
/*     */     } 
/*     */     
/* 671 */     return result.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String executeRemoteCommand(final String s) {
/* 677 */     Waitable<String> waitable = new Waitable<String>()
/*     */       {
/*     */         protected String evaluate() {
/* 680 */           DedicatedServer.this.remoteControlCommandListener.clearMessages();
/*     */           
/* 682 */           RemoteServerCommandEvent event = new RemoteServerCommandEvent((CommandSender)DedicatedServer.this.remoteConsole, s);
/* 683 */           DedicatedServer.this.server.getPluginManager().callEvent((Event)event);
/* 684 */           if (event.isCancelled()) {
/* 685 */             return "";
/*     */           }
/*     */           
/* 688 */           ServerCommand serverCommand = new ServerCommand(event.getCommand(), DedicatedServer.this.remoteControlCommandListener);
/* 689 */           DedicatedServer.this.server.dispatchServerCommand((CommandSender)DedicatedServer.this.remoteConsole, serverCommand);
/* 690 */           return DedicatedServer.this.remoteControlCommandListener.getMessages();
/*     */         }
/*     */       };
/* 693 */     this.processQueue.add(waitable);
/*     */     try {
/* 695 */       return (String)waitable.get();
/* 696 */     } catch (ExecutionException e) {
/* 697 */       throw new RuntimeException("Exception processing rcon command " + s, e.getCause());
/* 698 */     } catch (InterruptedException e) {
/* 699 */       Thread.currentThread().interrupt();
/* 700 */       throw new RuntimeException("Interrupted processing rcon command " + s, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public PlayerList getPlayerList() {
/* 706 */     return aQ();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PropertyManager getPropertyManager() {
/* 712 */     return this.propertyManager;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DedicatedServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */