/*      */ package net.minecraft.server.v1_12_R1;
/*      */ import com.google.common.util.concurrent.Futures;
/*      */ import com.google.common.util.concurrent.ListenableFuture;
/*      */ import com.google.common.util.concurrent.ListenableFutureTask;
/*      */ import com.mojang.authlib.GameProfile;
/*      */ import com.mojang.authlib.GameProfileRepository;
/*      */ import com.mojang.authlib.minecraft.MinecraftSessionService;
/*      */ import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
/*      */ import io.netty.buffer.ByteBuf;
/*      */ import io.netty.buffer.ByteBufOutputStream;
/*      */ import io.netty.handler.codec.base64.Base64;
/*      */ import io.netty.util.ResourceLeakDetector;
/*      */ import java.awt.GraphicsEnvironment;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.net.Proxy;
/*      */ import java.net.URLEncoder;
/*      */ import java.nio.charset.StandardCharsets;
/*      */ import java.security.KeyPair;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collections;
/*      */ import java.util.List;
/*      */ import java.util.Queue;
/*      */ import java.util.Random;
/*      */ import java.util.UUID;
/*      */ import java.util.concurrent.Callable;
/*      */ import java.util.concurrent.ConcurrentLinkedQueue;
/*      */ import java.util.concurrent.Executors;
/*      */ import java.util.concurrent.FutureTask;
/*      */ import javax.annotation.Nullable;
/*      */ import javax.imageio.ImageIO;
/*      */ import org.apache.commons.io.FileUtils;
/*      */ import org.apache.commons.lang3.Validate;
/*      */ import org.apache.logging.log4j.LogManager;
/*      */ import org.apache.logging.log4j.Logger;
/*      */ import org.bukkit.Bukkit;
/*      */ import org.bukkit.World;
/*      */ import org.bukkit.craftbukkit.Main;
/*      */ import org.bukkit.craftbukkit.libs.jline.console.ConsoleReader;
/*      */ import org.bukkit.craftbukkit.libs.joptsimple.OptionSet;
/*      */ import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
/*      */ import org.bukkit.craftbukkit.v1_12_R1.SpigotTimings;
/*      */ import org.bukkit.craftbukkit.v1_12_R1.util.ServerShutdownThread;
/*      */ import org.bukkit.event.Event;
/*      */ import org.bukkit.event.world.WorldLoadEvent;
/*      */ import org.bukkit.generator.ChunkGenerator;
/*      */ import org.spigotmc.CustomTimingsHandler;
/*      */ import org.spigotmc.SlackActivityAccountant;
/*      */ import org.spigotmc.WatchdogThread;
/*      */ 
/*      */ public abstract class MinecraftServer implements ICommandListener, Runnable, IAsyncTaskHandler, IMojangStatistics {
/*   55 */   public static final Logger LOGGER = LogManager.getLogger();
/*   56 */   public static final File a = new File("usercache.json");
/*      */   public Convertable convertable;
/*   58 */   private final MojangStatisticsGenerator m = new MojangStatisticsGenerator("server", this, aw());
/*      */   public File universe;
/*   60 */   private final List<ITickable> o = Lists.newArrayList();
/*      */   public final ICommandHandler b;
/*   62 */   public final MethodProfiler methodProfiler = new MethodProfiler();
/*      */   private ServerConnection p;
/*   64 */   private final ServerPing q = new ServerPing();
/*   65 */   private final Random r = new Random();
/*      */   public final DataConverterManager dataConverterManager;
/*      */   private String serverIp;
/*   68 */   private int u = -1;
/*      */   public WorldServer[] worldServer;
/*      */   private PlayerList v;
/*      */   private boolean isRunning = true;
/*      */   private boolean isStopped;
/*      */   private int ticks;
/*      */   protected final Proxy e;
/*      */   public String f;
/*      */   public int g;
/*      */   private boolean onlineMode;
/*      */   private boolean A;
/*      */   private boolean spawnAnimals;
/*      */   private boolean spawnNPCs;
/*      */   private boolean pvpMode;
/*      */   private boolean allowFlight;
/*      */   private String motd;
/*      */   private int G;
/*      */   private int H;
/*   86 */   public final long[] h = new long[100];
/*      */   public long[][] i;
/*      */   private KeyPair I;
/*      */   private String J;
/*      */   private String K;
/*      */   private boolean demoMode;
/*      */   private boolean N;
/*   93 */   private String O = "";
/*   94 */   private String P = "";
/*      */   private boolean Q;
/*      */   private long R;
/*      */   private String S;
/*      */   private boolean T;
/*      */   private boolean U;
/*      */   private final YggdrasilAuthenticationService V;
/*      */   private final MinecraftSessionService W;
/*      */   private final GameProfileRepository X;
/*      */   private final UserCache Y;
/*      */   private long Z;
/*  105 */   protected final Queue<FutureTask<?>> j = new ConcurrentLinkedQueue<>();
/*      */   private Thread serverThread;
/*  107 */   private long ab = aw();
/*      */ 
/*      */   
/*  110 */   public List<WorldServer> worlds = new ArrayList<>();
/*      */   public CraftServer server;
/*      */   public OptionSet options;
/*      */   public ConsoleCommandSender console;
/*      */   public RemoteConsoleCommandSender remoteConsole;
/*      */   public ConsoleReader reader;
/*  116 */   public static int currentTick = (int)(System.currentTimeMillis() / 50L);
/*      */   public final Thread primaryThread;
/*  118 */   public Queue<Runnable> processQueue = new ConcurrentLinkedQueue<>();
/*      */   
/*      */   public int autosavePeriod;
/*      */   
/*      */   public static final int TPS = 20;
/*      */   public static final int TICK_TIME = 50000000;
/*      */   private static final int SAMPLE_INTERVAL = 100;
/*  125 */   public final double[] recentTps = new double[3];
/*  126 */   public final SlackActivityAccountant slackActivityAccountant = new SlackActivityAccountant();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean hasStopped;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final Object stopLock;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected CommandDispatcher i() {
/*  173 */     return new CommandDispatcher(this);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void a(String s) {
/*  179 */     if (getConvertable().isConvertable(s)) {
/*  180 */       LOGGER.info("Converting map!");
/*  181 */       b("menu.convertingLevel");
/*  182 */       getConvertable().convert(s, new IProgressUpdate() {
/*  183 */             private long b = System.currentTimeMillis();
/*      */             
/*      */             public void a(String s) {}
/*      */             
/*      */             public void a(int i) {
/*  188 */               if (System.currentTimeMillis() - this.b >= 1000L) {
/*  189 */                 this.b = System.currentTimeMillis();
/*  190 */                 MinecraftServer.LOGGER.info("Converting... {}%", Integer.valueOf(i));
/*      */               } 
/*      */             }
/*      */ 
/*      */             
/*      */             public void c(String s) {}
/*      */           });
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected synchronized void b(String s) {
/*  202 */     this.S = s;
/*      */   }
/*      */   
/*      */   public void a(String s, String s1, long i, WorldType worldtype, String s2) {
/*  206 */     a(s);
/*  207 */     b("menu.loadingLevel");
/*  208 */     this.worldServer = new WorldServer[3];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  234 */     int worldCount = 3;
/*      */     
/*  236 */     for (int j = 0; j < worldCount; j++) {
/*      */       WorldServer world;
/*  238 */       byte dimension = 0;
/*      */       
/*  240 */       if (j == 1) {
/*  241 */         if (getAllowNether()) {
/*  242 */           dimension = -1;
/*      */         } else {
/*      */           continue;
/*      */         } 
/*      */       }
/*      */       
/*  248 */       if (j == 2) {
/*  249 */         if (this.server.getAllowEnd()) {
/*  250 */           dimension = 1;
/*      */         } else {
/*      */           continue;
/*      */         } 
/*      */       }
/*      */       
/*  256 */       String worldType = World.Environment.getEnvironment(dimension).toString().toLowerCase();
/*  257 */       String name = (dimension == 0) ? s : (String.valueOf(s) + "_" + worldType);
/*      */       
/*  259 */       ChunkGenerator gen = this.server.getGenerator(name);
/*  260 */       WorldSettings worldsettings = new WorldSettings(i, getGamemode(), getGenerateStructures(), isHardcore(), worldtype);
/*  261 */       worldsettings.setGeneratorSettings(s2);
/*      */       
/*  263 */       if (j == 0) {
/*  264 */         IDataManager idatamanager = new ServerNBTManager(this.server.getWorldContainer(), s1, true, this.dataConverterManager);
/*  265 */         WorldData worlddata = idatamanager.getWorldData();
/*  266 */         if (worlddata == null) {
/*  267 */           worlddata = new WorldData(worldsettings, s1);
/*      */         }
/*  269 */         worlddata.checkName(s1);
/*  270 */         if (V()) {
/*  271 */           world = (WorldServer)(new DemoWorldServer(this, idatamanager, worlddata, dimension, this.methodProfiler)).b();
/*      */         } else {
/*  273 */           world = (WorldServer)(new WorldServer(this, idatamanager, worlddata, dimension, this.methodProfiler, World.Environment.getEnvironment(dimension), gen)).b();
/*      */         } 
/*      */         
/*  276 */         world.a(worldsettings);
/*  277 */         this.server.scoreboardManager = new CraftScoreboardManager(this, world.getScoreboard());
/*      */       } else {
/*  279 */         String dim = "DIM" + dimension;
/*      */         
/*  281 */         File newWorld = new File(new File(name), dim);
/*  282 */         File oldWorld = new File(new File(s), dim);
/*      */         
/*  284 */         if (!newWorld.isDirectory() && oldWorld.isDirectory()) {
/*  285 */           LOGGER.info("---- Migration of old " + worldType + " folder required ----");
/*  286 */           LOGGER.info("Unfortunately due to the way that Minecraft implemented multiworld support in 1.6, Bukkit requires that you move your " + worldType + " folder to a new location in order to operate correctly.");
/*  287 */           LOGGER.info("We will move this folder for you, but it will mean that you need to move it back should you wish to stop using Bukkit in the future.");
/*  288 */           LOGGER.info("Attempting to move " + oldWorld + " to " + newWorld + "...");
/*      */           
/*  290 */           if (newWorld.exists()) {
/*  291 */             LOGGER.warn("A file or folder already exists at " + newWorld + "!");
/*  292 */             LOGGER.info("---- Migration of old " + worldType + " folder failed ----");
/*  293 */           } else if (newWorld.getParentFile().mkdirs()) {
/*  294 */             if (oldWorld.renameTo(newWorld)) {
/*  295 */               LOGGER.info("Success! To restore " + worldType + " in the future, simply move " + newWorld + " to " + oldWorld);
/*      */               
/*      */               try {
/*  298 */                 Files.copy(new File(new File(s), "level.dat"), new File(new File(name), "level.dat"));
/*  299 */                 FileUtils.copyDirectory(new File(new File(s), "data"), new File(new File(name), "data"));
/*  300 */               } catch (IOException iOException) {
/*  301 */                 LOGGER.warn("Unable to migrate world data.");
/*      */               } 
/*  303 */               LOGGER.info("---- Migration of old " + worldType + " folder complete ----");
/*      */             } else {
/*  305 */               LOGGER.warn("Could not move folder " + oldWorld + " to " + newWorld + "!");
/*  306 */               LOGGER.info("---- Migration of old " + worldType + " folder failed ----");
/*      */             } 
/*      */           } else {
/*  309 */             LOGGER.warn("Could not create path for " + newWorld + "!");
/*  310 */             LOGGER.info("---- Migration of old " + worldType + " folder failed ----");
/*      */           } 
/*      */         } 
/*      */         
/*  314 */         IDataManager idatamanager = new ServerNBTManager(this.server.getWorldContainer(), name, true, this.dataConverterManager);
/*      */         
/*  316 */         WorldData worlddata = idatamanager.getWorldData();
/*  317 */         if (worlddata == null) {
/*  318 */           worlddata = new WorldData(worldsettings, name);
/*      */         }
/*  320 */         worlddata.checkName(name);
/*  321 */         world = (WorldServer)(new SecondaryWorldServer(this, idatamanager, dimension, this.worlds.get(0), this.methodProfiler, worlddata, World.Environment.getEnvironment(dimension), gen)).b();
/*      */       } 
/*      */       
/*  324 */       this.server.getPluginManager().callEvent((Event)new WorldInitEvent((World)world.getWorld()));
/*      */       
/*  326 */       world.addIWorldAccess(new WorldManager(this, world));
/*  327 */       if (!R()) {
/*  328 */         world.getWorldData().setGameType(getGamemode());
/*      */       }
/*      */       
/*  331 */       this.worlds.add(world);
/*  332 */       getPlayerList().setPlayerFileData(this.worlds.<WorldServer>toArray(new WorldServer[this.worlds.size()]));
/*      */       continue;
/*      */     } 
/*  335 */     this.v.setPlayerFileData(this.worldServer);
/*  336 */     a(getDifficulty());
/*  337 */     l();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void l() {
/*  345 */     int i = 0;
/*      */     
/*  347 */     b("menu.generatingTerrain");
/*      */ 
/*      */ 
/*      */     
/*  351 */     for (int m = 0; m < this.worlds.size(); m++) {
/*  352 */       WorldServer worldserver = this.worlds.get(m);
/*  353 */       LOGGER.info("Preparing start region for level " + m + " (Seed: " + worldserver.getSeed() + ")");
/*      */       
/*  355 */       if (worldserver.getWorld().getKeepSpawnInMemory()) {
/*      */ 
/*      */ 
/*      */         
/*  359 */         BlockPosition blockposition = worldserver.getSpawn();
/*  360 */         long j = aw();
/*  361 */         i = 0;
/*      */         
/*  363 */         for (int k = -192; k <= 192 && isRunning(); k += 16) {
/*  364 */           for (int l = -192; l <= 192 && isRunning(); l += 16) {
/*  365 */             long i1 = aw();
/*      */             
/*  367 */             if (i1 - j > 1000L) {
/*  368 */               a_("Preparing spawn area", i * 100 / 625);
/*  369 */               j = i1;
/*      */             } 
/*      */             
/*  372 */             i++;
/*  373 */             worldserver.getChunkProviderServer().getChunkAt(blockposition.getX() + k >> 4, blockposition.getZ() + l >> 4);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*  378 */     for (WorldServer world : this.worlds) {
/*  379 */       this.server.getPluginManager().callEvent((Event)new WorldLoadEvent((World)world.getWorld()));
/*      */     }
/*      */     
/*  382 */     t();
/*      */   }
/*      */   
/*      */   protected void a(String s, IDataManager idatamanager) {
/*  386 */     File file = new File(idatamanager.getDirectory(), "resources.zip");
/*      */     
/*  388 */     if (file.isFile()) {
/*      */       try {
/*  390 */         setResourcePack("level://" + URLEncoder.encode(s, StandardCharsets.UTF_8.toString()) + "/" + "resources.zip", "");
/*  391 */       } catch (UnsupportedEncodingException unsupportedEncodingException) {
/*  392 */         LOGGER.warn("Something went wrong url encoding {}", s);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void a_(String s, int i) {
/*  413 */     this.f = s;
/*  414 */     this.g = i;
/*  415 */     LOGGER.info("{}: {}%", s, Integer.valueOf(i));
/*      */   }
/*      */   
/*      */   protected void t() {
/*  419 */     this.f = null;
/*  420 */     this.g = 0;
/*  421 */     this.server.enablePlugins(PluginLoadOrder.POSTWORLD);
/*      */   }
/*      */   
/*      */   protected void saveChunks(boolean flag) {
/*  425 */     WorldServer[] aworldserver = this.worldServer;
/*  426 */     aworldserver.length;
/*      */ 
/*      */     
/*  429 */     for (int j = 0; j < this.worlds.size(); j++) {
/*  430 */       WorldServer worldserver = this.worlds.get(j);
/*      */ 
/*      */       
/*  433 */       if (worldserver != null) {
/*  434 */         if (!flag) {
/*  435 */           LOGGER.info("Saving chunks for level '{}'/{}", worldserver.getWorldData().getName(), worldserver.worldProvider.getDimensionManager().b());
/*      */         }
/*      */         
/*      */         try {
/*  439 */           worldserver.save(true, (IProgressUpdate)null);
/*  440 */           worldserver.saveLevel();
/*  441 */         } catch (ExceptionWorldConflict exceptionworldconflict) {
/*  442 */           LOGGER.warn(exceptionworldconflict.getMessage());
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public MinecraftServer(OptionSet options, Proxy proxy, DataConverterManager dataconvertermanager, YggdrasilAuthenticationService yggdrasilauthenticationservice, MinecraftSessionService minecraftsessionservice, GameProfileRepository gameprofilerepository, UserCache usercache) {
/*  450 */     this.hasStopped = false;
/*  451 */     this.stopLock = new Object(); ResourceLeakDetector.setEnabled(false); this.e = proxy; this.V = yggdrasilauthenticationservice; this.W = minecraftsessionservice; this.X = gameprofilerepository; this.Y = usercache; this.b = i(); this.dataConverterManager = dataconvertermanager; this.options = options; if (System.console() == null && System.getProperty("org.bukkit.craftbukkit.libs.jline.terminal") == null) { System.setProperty("org.bukkit.craftbukkit.libs.jline.terminal", "org.bukkit.craftbukkit.libs.jline.UnsupportedTerminal"); Main.useJline = false; }
/*      */      try { this.reader = new ConsoleReader(System.in, System.out); this.reader.setExpandEvents(false); }
/*      */     catch (Throwable throwable) { try { System.setProperty("org.bukkit.craftbukkit.libs.jline.terminal", "org.bukkit.craftbukkit.libs.jline.UnsupportedTerminal"); System.setProperty("user.language", "en"); Main.useJline = false; this.reader = new ConsoleReader(System.in, System.out); this.reader.setExpandEvents(false); }
/*      */       catch (IOException ex) { LOGGER.warn(null, ex); }
/*      */        }
/*  456 */      Runtime.getRuntime().addShutdownHook((Thread)new ServerShutdownThread(this)); this.serverThread = this.primaryThread = new Thread(this, "Server thread"); } public void stop() throws ExceptionWorldConflict { synchronized (this.stopLock) {
/*  457 */       if (this.hasStopped)
/*  458 */         return;  this.hasStopped = true;
/*      */     } 
/*      */     
/*  461 */     LOGGER.info("Stopping server");
/*      */     
/*  463 */     if (this.server != null) {
/*  464 */       this.server.disablePlugins();
/*      */     }
/*      */     
/*  467 */     if (an() != null) {
/*  468 */       an().b();
/*      */     }
/*      */     
/*  471 */     if (this.v != null) {
/*  472 */       LOGGER.info("Saving players");
/*  473 */       this.v.savePlayers();
/*  474 */       this.v.u(); 
/*  475 */       try { Thread.sleep(100L); } catch (InterruptedException interruptedException) {}
/*      */     } 
/*      */     
/*  478 */     if (this.worldServer != null) {
/*  479 */       LOGGER.info("Saving worlds");
/*  480 */       WorldServer[] aworldserver = this.worldServer;
/*  481 */       int i = aworldserver.length;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  486 */       for (int j = 0; j < i; j++) {
/*  487 */         WorldServer worldserver = aworldserver[j];
/*  488 */         if (worldserver != null) {
/*  489 */           worldserver.savingDisabled = false;
/*      */         }
/*      */       } 
/*      */       
/*  493 */       saveChunks(false);
/*  494 */       aworldserver = this.worldServer;
/*  495 */       i = aworldserver.length;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  507 */     if (this.m.d()) {
/*  508 */       this.m.e();
/*      */     }
/*      */ 
/*      */     
/*  512 */     if (SpigotConfig.saveUserCacheOnStopOnly) {
/*  513 */       LOGGER.info("Saving usercache.json");
/*  514 */       this.Y.c();
/*      */     }  }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getServerIp() {
/*  520 */     return this.serverIp;
/*      */   }
/*      */   
/*      */   public void c(String s) {
/*  524 */     this.serverIp = s;
/*      */   }
/*      */   
/*      */   public boolean isRunning() {
/*  528 */     return this.isRunning;
/*      */   }
/*      */   
/*      */   public void safeShutdown() {
/*  532 */     this.isRunning = false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static double calcTps(double avg, double exp, double tps) {
/*  538 */     return avg * exp + tps * (1.0D - exp);
/*      */   }
/*      */ 
/*      */   
/*      */   public void run() {
/*      */     try {
/*  544 */       if (init()) {
/*  545 */         this.ab = aw();
/*      */ 
/*      */         
/*  548 */         this.q.setMOTD(new ChatComponentText(this.motd));
/*  549 */         this.q.setServerInfo(new ServerPing.ServerData("1.12", 335));
/*  550 */         a(this.q);
/*      */ 
/*      */         
/*  553 */         Arrays.fill(this.recentTps, 20.0D);
/*  554 */         long lastTick = System.nanoTime(), catchupTime = 0L, tickSection = lastTick;
/*  555 */         while (this.isRunning) {
/*  556 */           long curTime = System.nanoTime();
/*  557 */           long wait = 50000000L - curTime - lastTick - catchupTime;
/*  558 */           if (wait > 0L) {
/*  559 */             Thread.sleep(wait / 1000000L);
/*  560 */             catchupTime = 0L;
/*      */             continue;
/*      */           } 
/*  563 */           catchupTime = Math.min(1000000000L, Math.abs(wait));
/*      */ 
/*      */           
/*  566 */           if (currentTick++ % 100 == 0) {
/*      */             
/*  568 */             double currentTps = 1.0E9D / (curTime - tickSection) * 100.0D;
/*  569 */             this.recentTps[0] = calcTps(this.recentTps[0], 0.92D, currentTps);
/*  570 */             this.recentTps[1] = calcTps(this.recentTps[1], 0.9835D, currentTps);
/*  571 */             this.recentTps[2] = calcTps(this.recentTps[2], 0.9945D, currentTps);
/*  572 */             tickSection = curTime;
/*      */           } 
/*  574 */           lastTick = curTime;
/*      */           
/*  576 */           C();
/*  577 */           this.Q = true;
/*      */         } 
/*      */       } else {
/*      */         
/*  581 */         a((CrashReport)null);
/*      */       } 
/*  583 */     } catch (Throwable throwable) {
/*  584 */       LOGGER.error("Encountered an unexpected exception", throwable);
/*      */       
/*  586 */       if (throwable.getCause() != null)
/*      */       {
/*  588 */         LOGGER.error("\tCause of unexpected exception was", throwable.getCause());
/*      */       }
/*      */       
/*  591 */       CrashReport crashreport = null;
/*      */       
/*  593 */       if (throwable instanceof ReportedException) {
/*  594 */         crashreport = b(((ReportedException)throwable).a());
/*      */       } else {
/*  596 */         crashreport = b(new CrashReport("Exception in server tick loop", throwable));
/*      */       } 
/*      */       
/*  599 */       File file = new File(new File(A(), "crash-reports"), "crash-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + "-server.txt");
/*      */       
/*  601 */       if (crashreport.a(file)) {
/*  602 */         LOGGER.error("This crash report has been saved to: {}", file.getAbsolutePath());
/*      */       } else {
/*  604 */         LOGGER.error("We were unable to save this crash report to disk.");
/*      */       } 
/*      */       
/*  607 */       a(crashreport);
/*      */     } finally {
/*      */       try {
/*  610 */         WatchdogThread.doStop();
/*  611 */         this.isStopped = true;
/*  612 */         stop();
/*  613 */       } catch (Throwable throwable1) {
/*  614 */         LOGGER.error("Exception stopping the server", throwable1);
/*      */       } finally {
/*      */         
/*      */         try {
/*  618 */           this.reader.getTerminal().restore();
/*  619 */         } catch (Exception exception) {}
/*      */ 
/*      */         
/*  622 */         B();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(ServerPing serverping) {
/*  630 */     File file = d("server-icon.png");
/*      */     
/*  632 */     if (!file.exists()) {
/*  633 */       file = getConvertable().b(S(), "icon.png");
/*      */     }
/*      */     
/*  636 */     if (file.isFile()) {
/*  637 */       ByteBuf bytebuf = Unpooled.buffer();
/*      */       
/*      */       try {
/*  640 */         BufferedImage bufferedimage = ImageIO.read(file);
/*      */         
/*  642 */         Validate.validState((bufferedimage.getWidth() == 64), "Must be 64 pixels wide", new Object[0]);
/*  643 */         Validate.validState((bufferedimage.getHeight() == 64), "Must be 64 pixels high", new Object[0]);
/*  644 */         ImageIO.write(bufferedimage, "PNG", (OutputStream)new ByteBufOutputStream(bytebuf));
/*  645 */         ByteBuf bytebuf1 = Base64.encode(bytebuf);
/*      */         
/*  647 */         serverping.setFavicon("data:image/png;base64," + bytebuf1.toString(StandardCharsets.UTF_8));
/*  648 */       } catch (Exception exception) {
/*  649 */         LOGGER.error("Couldn't load server icon", exception);
/*      */       } finally {
/*  651 */         bytebuf.release();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public File A() {
/*  658 */     return new File(".");
/*      */   }
/*      */   
/*      */   protected void a(CrashReport crashreport) {}
/*      */   
/*      */   public void B() {}
/*      */   
/*      */   protected void C() throws ExceptionWorldConflict {
/*  666 */     SpigotTimings.serverTickTimer.startTiming();
/*  667 */     this.slackActivityAccountant.tickStarted();
/*  668 */     long i = System.nanoTime();
/*      */     
/*  670 */     this.ticks++;
/*  671 */     if (this.T) {
/*  672 */       this.T = false;
/*  673 */       this.methodProfiler.a = true;
/*  674 */       this.methodProfiler.a();
/*      */     } 
/*      */     
/*  677 */     this.methodProfiler.a("root");
/*  678 */     D();
/*  679 */     if (i - this.Z >= 5000000000L) {
/*  680 */       this.Z = i;
/*  681 */       this.q.setPlayerSample(new ServerPing.ServerPingPlayerSample(I(), H()));
/*  682 */       GameProfile[] agameprofile = new GameProfile[Math.min(H(), 12)];
/*  683 */       int j = MathHelper.nextInt(this.r, 0, H() - agameprofile.length);
/*      */       
/*  685 */       for (int k = 0; k < agameprofile.length; k++) {
/*  686 */         agameprofile[k] = ((EntityPlayer)this.v.v().get(j + k)).getProfile();
/*      */       }
/*      */       
/*  689 */       Collections.shuffle(Arrays.asList((Object[])agameprofile));
/*  690 */       this.q.b().a(agameprofile);
/*      */     } 
/*      */     
/*  693 */     if (this.autosavePeriod > 0 && this.ticks % this.autosavePeriod == 0) {
/*  694 */       SpigotTimings.worldSaveTimer.startTiming();
/*  695 */       this.methodProfiler.a("save");
/*  696 */       this.v.savePlayers();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  701 */       this.server.playerCommandState = true;
/*  702 */       for (World world : this.worlds) {
/*  703 */         world.getWorld().save(false);
/*      */       }
/*  705 */       this.server.playerCommandState = false;
/*      */ 
/*      */       
/*  708 */       this.methodProfiler.b();
/*  709 */       SpigotTimings.worldSaveTimer.stopTiming();
/*      */     } 
/*      */     
/*  712 */     this.methodProfiler.a("tallying");
/*      */ 
/*      */     
/*  715 */     long tickNanos = System.nanoTime() - i;
/*      */     
/*  717 */     this.methodProfiler.b();
/*  718 */     this.methodProfiler.a("snooper");
/*  719 */     if (getSnooperEnabled() && !this.m.d() && this.ticks > 100) {
/*  720 */       this.m.a();
/*      */     }
/*      */     
/*  723 */     if (getSnooperEnabled() && this.ticks % 6000 == 0) {
/*  724 */       this.m.b();
/*      */     }
/*      */     
/*  727 */     this.methodProfiler.b();
/*  728 */     this.methodProfiler.b();
/*      */     
/*  730 */     WatchdogThread.tick();
/*  731 */     this.slackActivityAccountant.tickEnded(tickNanos);
/*  732 */     SpigotTimings.serverTickTimer.stopTiming();
/*  733 */     CustomTimingsHandler.tick();
/*      */   }
/*      */   
/*      */   public void D() {
/*  737 */     SpigotTimings.schedulerTimer.startTiming();
/*  738 */     this.server.getScheduler().mainThreadHeartbeat(this.ticks);
/*  739 */     SpigotTimings.schedulerTimer.stopTiming();
/*  740 */     this.methodProfiler.a("jobs");
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  745 */     int count = this.j.size(); FutureTask<?> entry;
/*  746 */     while (count-- > 0 && (entry = this.j.poll()) != null) {
/*  747 */       SystemUtils.a(entry, LOGGER);
/*      */     }
/*      */ 
/*      */     
/*  751 */     this.methodProfiler.c("levels");
/*      */ 
/*      */ 
/*      */     
/*  755 */     SpigotTimings.processQueueTimer.startTiming();
/*  756 */     while (!this.processQueue.isEmpty()) {
/*  757 */       ((Runnable)this.processQueue.remove()).run();
/*      */     }
/*  759 */     SpigotTimings.processQueueTimer.stopTiming();
/*      */     
/*  761 */     SpigotTimings.chunkIOTickTimer.startTiming();
/*  762 */     ChunkIOExecutor.tick();
/*  763 */     SpigotTimings.chunkIOTickTimer.stopTiming();
/*      */     
/*  765 */     SpigotTimings.timeUpdateTimer.startTiming();
/*      */     
/*  767 */     if (this.ticks % 20 == 0) {
/*  768 */       for (int j = 0; j < (getPlayerList()).players.size(); j++) {
/*  769 */         EntityPlayer entityplayer = (getPlayerList()).players.get(j);
/*  770 */         entityplayer.playerConnection.sendPacket(new PacketPlayOutUpdateTime(entityplayer.world.getTime(), entityplayer.getPlayerTime(), entityplayer.world.getGameRules().getBoolean("doDaylightCycle")));
/*      */       } 
/*      */     }
/*  773 */     SpigotTimings.timeUpdateTimer.stopTiming();
/*      */     
/*      */     int i;
/*      */     
/*  777 */     for (i = 0; i < this.worlds.size(); i++) {
/*  778 */       System.nanoTime();
/*      */ 
/*      */       
/*  781 */       WorldServer worldserver = this.worlds.get(i);
/*      */       
/*  783 */       this.methodProfiler.a(worldserver.getWorldData().getName());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  792 */       this.methodProfiler.a("tick");
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  797 */         worldserver.timings.doTick.startTiming();
/*  798 */         worldserver.doTick();
/*  799 */         worldserver.timings.doTick.stopTiming();
/*  800 */       } catch (Throwable throwable) {
/*      */         CrashReport crashreport;
/*      */         try {
/*  803 */           crashreport = CrashReport.a(throwable, "Exception ticking world");
/*  804 */         } catch (Throwable t) {
/*  805 */           throw new RuntimeException("Error generating crash report", t);
/*      */         } 
/*      */         
/*  808 */         worldserver.a(crashreport);
/*  809 */         throw new ReportedException(crashreport);
/*      */       } 
/*      */       
/*      */       try {
/*  813 */         worldserver.timings.tickEntities.startTiming();
/*  814 */         worldserver.tickEntities();
/*  815 */         worldserver.timings.tickEntities.stopTiming();
/*  816 */       } catch (Throwable throwable1) {
/*      */         CrashReport crashreport;
/*      */         try {
/*  819 */           crashreport = CrashReport.a(throwable1, "Exception ticking world entities");
/*  820 */         } catch (Throwable t) {
/*  821 */           throw new RuntimeException("Error generating crash report", t);
/*      */         } 
/*      */         
/*  824 */         worldserver.a(crashreport);
/*  825 */         throw new ReportedException(crashreport);
/*      */       } 
/*      */       
/*  828 */       this.methodProfiler.b();
/*  829 */       this.methodProfiler.a("tracker");
/*  830 */       worldserver.timings.tracker.startTiming();
/*  831 */       worldserver.getTracker().updatePlayers();
/*  832 */       worldserver.timings.tracker.stopTiming();
/*  833 */       this.methodProfiler.b();
/*  834 */       this.methodProfiler.b();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  840 */     this.methodProfiler.c("connection");
/*  841 */     SpigotTimings.connectionTimer.startTiming();
/*  842 */     an().c();
/*  843 */     SpigotTimings.connectionTimer.stopTiming();
/*  844 */     this.methodProfiler.c("players");
/*  845 */     SpigotTimings.playerListTimer.startTiming();
/*  846 */     this.v.tick();
/*  847 */     SpigotTimings.playerListTimer.stopTiming();
/*  848 */     this.methodProfiler.c("commandFunctions");
/*  849 */     SpigotTimings.commandFunctionsTimer.startTiming();
/*  850 */     aL().e();
/*  851 */     SpigotTimings.commandFunctionsTimer.stopTiming();
/*  852 */     this.methodProfiler.c("tickables");
/*      */     
/*  854 */     SpigotTimings.tickablesTimer.startTiming();
/*  855 */     for (i = 0; i < this.o.size(); i++) {
/*  856 */       ((ITickable)this.o.get(i)).e();
/*      */     }
/*  858 */     SpigotTimings.tickablesTimer.stopTiming();
/*      */     
/*  860 */     this.methodProfiler.b();
/*      */   }
/*      */   
/*      */   public boolean getAllowNether() {
/*  864 */     return true;
/*      */   }
/*      */   
/*      */   public void a(ITickable itickable) {
/*  868 */     this.o.add(itickable);
/*      */   }
/*      */   
/*      */   public static void main(OptionSet options) {
/*  872 */     DispenserRegistry.c();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  922 */       String s1 = ".";
/*  923 */       YggdrasilAuthenticationService yggdrasilauthenticationservice = new YggdrasilAuthenticationService(Proxy.NO_PROXY, UUID.randomUUID().toString());
/*  924 */       MinecraftSessionService minecraftsessionservice = yggdrasilauthenticationservice.createMinecraftSessionService();
/*  925 */       GameProfileRepository gameprofilerepository = yggdrasilauthenticationservice.createProfileRepository();
/*  926 */       UserCache usercache = new UserCache(gameprofilerepository, new File(s1, a.getName()));
/*  927 */       DedicatedServer dedicatedserver = new DedicatedServer(options, DataConverterRegistry.a(), yggdrasilauthenticationservice, minecraftsessionservice, gameprofilerepository, usercache);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  962 */       if (options.has("port")) {
/*  963 */         int port = ((Integer)options.valueOf("port")).intValue();
/*  964 */         if (port > 0) {
/*  965 */           dedicatedserver.setPort(port);
/*      */         }
/*      */       } 
/*      */       
/*  969 */       if (options.has("universe")) {
/*  970 */         dedicatedserver.universe = (File)options.valueOf("universe");
/*      */       }
/*      */       
/*  973 */       if (options.has("world")) {
/*  974 */         dedicatedserver.setWorld((String)options.valueOf("world"));
/*      */       }
/*      */       
/*  977 */       dedicatedserver.primaryThread.start();
/*      */     }
/*  979 */     catch (Exception exception) {
/*  980 */       LOGGER.fatal("Failed to start the minecraft server", exception);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void F() {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public File d(String s) {
/*  993 */     return new File(A(), s);
/*      */   }
/*      */   
/*      */   public void info(String s) {
/*  997 */     LOGGER.info(s);
/*      */   }
/*      */   
/*      */   public void warning(String s) {
/* 1001 */     LOGGER.warn(s);
/*      */   }
/*      */ 
/*      */   
/*      */   public WorldServer getWorldServer(int i) {
/* 1006 */     for (WorldServer world : this.worlds) {
/* 1007 */       if (world.dimension == i) {
/* 1008 */         return world;
/*      */       }
/*      */     } 
/* 1011 */     return this.worlds.get(0);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getVersion() {
/* 1016 */     return "1.12";
/*      */   }
/*      */   
/*      */   public int H() {
/* 1020 */     return this.v.getPlayerCount();
/*      */   }
/*      */   
/*      */   public int I() {
/* 1024 */     return this.v.getMaxPlayers();
/*      */   }
/*      */   
/*      */   public String[] getPlayers() {
/* 1028 */     return this.v.f();
/*      */   }
/*      */   
/*      */   public GameProfile[] K() {
/* 1032 */     return this.v.g();
/*      */   }
/*      */   
/*      */   public boolean isDebugging() {
/* 1036 */     return getPropertyManager().getBoolean("debug", false);
/*      */   }
/*      */   
/*      */   public void g(String s) {
/* 1040 */     LOGGER.error(s);
/*      */   }
/*      */   
/*      */   public void h(String s) {
/* 1044 */     if (isDebugging()) {
/* 1045 */       LOGGER.info(s);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public String getServerModName() {
/* 1051 */     return "Spigot";
/*      */   }
/*      */   
/*      */   public CrashReport b(CrashReport crashreport) {
/* 1055 */     crashreport.g().a("Profiler Position", new CrashReportCallable<String>() {
/*      */           public String a() throws Exception {
/* 1057 */             return MinecraftServer.this.methodProfiler.a ? MinecraftServer.this.methodProfiler.c() : "N/A (disabled)";
/*      */           }
/*      */           
/*      */           public Object call() throws Exception {
/* 1061 */             return a();
/*      */           }
/*      */         });
/* 1064 */     if (this.v != null) {
/* 1065 */       crashreport.g().a("Player Count", new CrashReportCallable<String>() {
/*      */             public String a() {
/* 1067 */               return String.valueOf(MinecraftServer.this.v.getPlayerCount()) + " / " + MinecraftServer.this.v.getMaxPlayers() + "; " + MinecraftServer.this.v.v();
/*      */             }
/*      */             
/*      */             public Object call() throws Exception {
/* 1071 */               return a();
/*      */             }
/*      */           });
/*      */     }
/*      */     
/* 1076 */     return crashreport;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<String> tabCompleteCommand(ICommandListener icommandlistener, String s, @Nullable BlockPosition blockposition, boolean flag) {
/* 1124 */     return this.server.tabComplete(icommandlistener, s, blockposition, flag);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean M() {
/* 1129 */     return true;
/*      */   }
/*      */   
/*      */   public String getName() {
/* 1133 */     return "Server";
/*      */   }
/*      */   
/*      */   public void sendMessage(IChatBaseComponent ichatbasecomponent) {
/* 1137 */     LOGGER.info(ichatbasecomponent.toPlainText());
/*      */   }
/*      */   
/*      */   public boolean a(int i, String s) {
/* 1141 */     return true;
/*      */   }
/*      */   
/*      */   public ICommandHandler getCommandHandler() {
/* 1145 */     return this.b;
/*      */   }
/*      */   
/*      */   public KeyPair O() {
/* 1149 */     return this.I;
/*      */   }
/*      */   
/*      */   public int P() {
/* 1153 */     return this.u;
/*      */   }
/*      */   
/*      */   public void setPort(int i) {
/* 1157 */     this.u = i;
/*      */   }
/*      */   
/*      */   public String Q() {
/* 1161 */     return this.J;
/*      */   }
/*      */   
/*      */   public void i(String s) {
/* 1165 */     this.J = s;
/*      */   }
/*      */   
/*      */   public boolean R() {
/* 1169 */     return (this.J != null);
/*      */   }
/*      */   
/*      */   public String S() {
/* 1173 */     return this.K;
/*      */   }
/*      */   
/*      */   public void setWorld(String s) {
/* 1177 */     this.K = s;
/*      */   }
/*      */   
/*      */   public void a(KeyPair keypair) {
/* 1181 */     this.I = keypair;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(EnumDifficulty enumdifficulty) {
/* 1187 */     int i = this.worlds.size();
/*      */     
/* 1189 */     for (int j = 0; j < i; j++) {
/* 1190 */       WorldServer worldserver = this.worlds.get(j);
/*      */ 
/*      */       
/* 1193 */       if (worldserver != null) {
/* 1194 */         if (worldserver.getWorldData().isHardcore()) {
/* 1195 */           worldserver.getWorldData().setDifficulty(EnumDifficulty.HARD);
/* 1196 */           worldserver.setSpawnFlags(true, true);
/* 1197 */         } else if (R()) {
/* 1198 */           worldserver.getWorldData().setDifficulty(enumdifficulty);
/* 1199 */           worldserver.setSpawnFlags((worldserver.getDifficulty() != EnumDifficulty.PEACEFUL), true);
/*      */         } else {
/* 1201 */           worldserver.getWorldData().setDifficulty(enumdifficulty);
/* 1202 */           worldserver.setSpawnFlags(getSpawnMonsters(), this.spawnAnimals);
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getSpawnMonsters() {
/* 1210 */     return true;
/*      */   }
/*      */   
/*      */   public boolean V() {
/* 1214 */     return this.demoMode;
/*      */   }
/*      */   
/*      */   public void b(boolean flag) {
/* 1218 */     this.demoMode = flag;
/*      */   }
/*      */   
/*      */   public void c(boolean flag) {
/* 1222 */     this.N = flag;
/*      */   }
/*      */   
/*      */   public Convertable getConvertable() {
/* 1226 */     return this.convertable;
/*      */   }
/*      */   
/*      */   public String getResourcePack() {
/* 1230 */     return this.O;
/*      */   }
/*      */   
/*      */   public String getResourcePackHash() {
/* 1234 */     return this.P;
/*      */   }
/*      */   
/*      */   public void setResourcePack(String s, String s1) {
/* 1238 */     this.O = s;
/* 1239 */     this.P = s1;
/*      */   }
/*      */   
/*      */   public void a(MojangStatisticsGenerator mojangstatisticsgenerator) {
/* 1243 */     mojangstatisticsgenerator.a("whitelist_enabled", Boolean.valueOf(false));
/* 1244 */     mojangstatisticsgenerator.a("whitelist_count", Integer.valueOf(0));
/* 1245 */     if (this.v != null) {
/* 1246 */       mojangstatisticsgenerator.a("players_current", Integer.valueOf(H()));
/* 1247 */       mojangstatisticsgenerator.a("players_max", Integer.valueOf(I()));
/* 1248 */       mojangstatisticsgenerator.a("players_seen", Integer.valueOf((this.v.getSeenPlayers()).length));
/*      */     } 
/*      */     
/* 1251 */     mojangstatisticsgenerator.a("uses_auth", Boolean.valueOf(this.onlineMode));
/* 1252 */     mojangstatisticsgenerator.a("gui_state", ap() ? "enabled" : "disabled");
/* 1253 */     mojangstatisticsgenerator.a("run_time", Long.valueOf((aw() - mojangstatisticsgenerator.g()) / 60L * 1000L));
/* 1254 */     mojangstatisticsgenerator.a("avg_tick_ms", Integer.valueOf((int)(MathHelper.a(this.h) * 1.0E-6D)));
/* 1255 */     int i = 0;
/*      */     
/* 1257 */     if (this.worldServer != null)
/*      */     {
/* 1259 */       for (int j = 0; j < this.worlds.size(); j++) {
/* 1260 */         WorldServer worldserver = this.worlds.get(j);
/* 1261 */         if (worldserver != null) {
/*      */           
/* 1263 */           WorldData worlddata = worldserver.getWorldData();
/*      */           
/* 1265 */           mojangstatisticsgenerator.a("world[" + i + "][dimension]", Integer.valueOf(worldserver.worldProvider.getDimensionManager().getDimensionID()));
/* 1266 */           mojangstatisticsgenerator.a("world[" + i + "][mode]", worlddata.getGameType());
/* 1267 */           mojangstatisticsgenerator.a("world[" + i + "][difficulty]", worldserver.getDifficulty());
/* 1268 */           mojangstatisticsgenerator.a("world[" + i + "][hardcore]", Boolean.valueOf(worlddata.isHardcore()));
/* 1269 */           mojangstatisticsgenerator.a("world[" + i + "][generator_name]", worlddata.getType().name());
/* 1270 */           mojangstatisticsgenerator.a("world[" + i + "][generator_version]", Integer.valueOf(worlddata.getType().getVersion()));
/* 1271 */           mojangstatisticsgenerator.a("world[" + i + "][height]", Integer.valueOf(this.G));
/* 1272 */           mojangstatisticsgenerator.a("world[" + i + "][chunks_loaded]", Integer.valueOf(worldserver.getChunkProviderServer().g()));
/* 1273 */           i++;
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/* 1278 */     mojangstatisticsgenerator.a("worlds", Integer.valueOf(i));
/*      */   }
/*      */   
/*      */   public void b(MojangStatisticsGenerator mojangstatisticsgenerator) {
/* 1282 */     mojangstatisticsgenerator.b("singleplayer", Boolean.valueOf(R()));
/* 1283 */     mojangstatisticsgenerator.b("server_brand", getServerModName());
/* 1284 */     mojangstatisticsgenerator.b("gui_supported", GraphicsEnvironment.isHeadless() ? "headless" : "supported");
/* 1285 */     mojangstatisticsgenerator.b("dedicated", Boolean.valueOf(aa()));
/*      */   }
/*      */   
/*      */   public boolean getSnooperEnabled() {
/* 1289 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getOnlineMode() {
/* 1295 */     return this.server.getOnlineMode();
/*      */   }
/*      */   
/*      */   public void setOnlineMode(boolean flag) {
/* 1299 */     this.onlineMode = flag;
/*      */   }
/*      */   
/*      */   public boolean ac() {
/* 1303 */     return this.A;
/*      */   }
/*      */   
/*      */   public void e(boolean flag) {
/* 1307 */     this.A = flag;
/*      */   }
/*      */   
/*      */   public boolean getSpawnAnimals() {
/* 1311 */     return this.spawnAnimals;
/*      */   }
/*      */   
/*      */   public void setSpawnAnimals(boolean flag) {
/* 1315 */     this.spawnAnimals = flag;
/*      */   }
/*      */   
/*      */   public boolean getSpawnNPCs() {
/* 1319 */     return this.spawnNPCs;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSpawnNPCs(boolean flag) {
/* 1325 */     this.spawnNPCs = flag;
/*      */   }
/*      */   
/*      */   public boolean getPVP() {
/* 1329 */     return this.pvpMode;
/*      */   }
/*      */   
/*      */   public void setPVP(boolean flag) {
/* 1333 */     this.pvpMode = flag;
/*      */   }
/*      */   
/*      */   public boolean getAllowFlight() {
/* 1337 */     return this.allowFlight;
/*      */   }
/*      */   
/*      */   public void setAllowFlight(boolean flag) {
/* 1341 */     this.allowFlight = flag;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getMotd() {
/* 1347 */     return this.motd;
/*      */   }
/*      */   
/*      */   public void setMotd(String s) {
/* 1351 */     this.motd = s;
/*      */   }
/*      */   
/*      */   public int getMaxBuildHeight() {
/* 1355 */     return this.G;
/*      */   }
/*      */   
/*      */   public void c(int i) {
/* 1359 */     this.G = i;
/*      */   }
/*      */   
/*      */   public boolean isStopped() {
/* 1363 */     return this.isStopped;
/*      */   }
/*      */   
/*      */   public PlayerList getPlayerList() {
/* 1367 */     return this.v;
/*      */   }
/*      */   
/*      */   public void a(PlayerList playerlist) {
/* 1371 */     this.v = playerlist;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setGamemode(EnumGamemode enumgamemode) {
/* 1376 */     for (int i = 0; i < this.worlds.size(); i++) {
/* 1377 */       ((WorldServer)this.worlds.get(i)).getWorldData().setGameType(enumgamemode);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ServerConnection getServerConnection() {
/* 1385 */     return this.p;
/*      */   }
/*      */   
/*      */   public ServerConnection an() {
/* 1389 */     return (this.p == null) ? (this.p = new ServerConnection(this)) : this.p;
/*      */   }
/*      */   
/*      */   public boolean ap() {
/* 1393 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int aq() {
/* 1399 */     return this.ticks;
/*      */   }
/*      */   
/*      */   public void ar() {
/* 1403 */     this.T = true;
/*      */   }
/*      */   
/*      */   public World getWorld() {
/* 1407 */     return this.worlds.get(0);
/*      */   }
/*      */   
/*      */   public int getSpawnProtection() {
/* 1411 */     return 16;
/*      */   }
/*      */   
/*      */   public boolean a(World world, BlockPosition blockposition, EntityHuman entityhuman) {
/* 1415 */     return false;
/*      */   }
/*      */   
/*      */   public void setForceGamemode(boolean flag) {
/* 1419 */     this.U = flag;
/*      */   }
/*      */   
/*      */   public boolean getForceGamemode() {
/* 1423 */     return this.U;
/*      */   }
/*      */   
/*      */   public Proxy av() {
/* 1427 */     return this.e;
/*      */   }
/*      */   
/*      */   public static long aw() {
/* 1431 */     return System.currentTimeMillis();
/*      */   }
/*      */   
/*      */   public int getIdleTimeout() {
/* 1435 */     return this.H;
/*      */   }
/*      */   
/*      */   public void setIdleTimeout(int i) {
/* 1439 */     this.H = i;
/*      */   }
/*      */   
/*      */   public MinecraftSessionService az() {
/* 1443 */     return this.W;
/*      */   }
/*      */   
/*      */   public GameProfileRepository getGameProfileRepository() {
/* 1447 */     return this.X;
/*      */   }
/*      */   
/*      */   public UserCache getUserCache() {
/* 1451 */     return this.Y;
/*      */   }
/*      */   
/*      */   public ServerPing getServerPing() {
/* 1455 */     return this.q;
/*      */   }
/*      */   
/*      */   public void aD() {
/* 1459 */     this.Z = 0L;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public Entity a(UUID uuid) {
/* 1464 */     WorldServer[] aworldserver = this.worldServer;
/* 1465 */     aworldserver.length;
/*      */ 
/*      */     
/* 1468 */     for (int j = 0; j < this.worlds.size(); j++) {
/* 1469 */       WorldServer worldserver = this.worlds.get(j);
/*      */ 
/*      */       
/* 1472 */       if (worldserver != null) {
/* 1473 */         Entity entity = worldserver.getEntity(uuid);
/*      */         
/* 1475 */         if (entity != null) {
/* 1476 */           return entity;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1481 */     return null;
/*      */   }
/*      */   
/*      */   public boolean getSendCommandFeedback() {
/* 1485 */     return ((WorldServer)this.worlds.get(0)).getGameRules().getBoolean("sendCommandFeedback");
/*      */   }
/*      */   
/*      */   public MinecraftServer C_() {
/* 1489 */     return this;
/*      */   }
/*      */   
/*      */   public int aE() {
/* 1493 */     return 29999984;
/*      */   }
/*      */   
/*      */   public <V> ListenableFuture<V> a(Callable<V> callable) {
/* 1497 */     Validate.notNull(callable);
/* 1498 */     if (!isMainThread()) {
/* 1499 */       ListenableFutureTask listenablefuturetask = ListenableFutureTask.create(callable);
/*      */ 
/*      */ 
/*      */       
/* 1503 */       this.j.add(listenablefuturetask);
/* 1504 */       return (ListenableFuture<V>)listenablefuturetask;
/*      */     } 
/*      */     
/*      */     try {
/* 1508 */       return Futures.immediateFuture(callable.call());
/* 1509 */     } catch (Exception exception) {
/* 1510 */       return (ListenableFuture<V>)Futures.immediateFailedCheckedFuture(exception);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public ListenableFuture<Object> postToMainThread(Runnable runnable) {
/* 1516 */     Validate.notNull(runnable);
/* 1517 */     return a(Executors.callable(runnable));
/*      */   }
/*      */   
/*      */   public boolean isMainThread() {
/* 1521 */     return (Thread.currentThread() == this.serverThread);
/*      */   }
/*      */   
/*      */   public int aG() {
/* 1525 */     return 256;
/*      */   }
/*      */   
/*      */   public long aH() {
/* 1529 */     return this.ab;
/*      */   }
/*      */   
/*      */   public Thread aI() {
/* 1533 */     return this.serverThread;
/*      */   }
/*      */   
/*      */   public int a(@Nullable WorldServer worldserver) {
/* 1537 */     return (worldserver != null) ? worldserver.getGameRules().c("spawnRadius") : 10;
/*      */   }
/*      */   
/*      */   public AdvancementDataWorld getAdvancementData() {
/* 1541 */     return ((WorldServer)this.worlds.get(0)).z();
/*      */   }
/*      */   
/*      */   public CustomFunctionData aL() {
/* 1545 */     return ((WorldServer)this.worlds.get(0)).A();
/*      */   }
/*      */   
/*      */   public void reload() {
/* 1549 */     if (isMainThread()) {
/* 1550 */       getPlayerList().savePlayers();
/* 1551 */       ((WorldServer)this.worlds.get(0)).getLootTableRegistry().reload();
/* 1552 */       getAdvancementData().reload();
/* 1553 */       aL().f();
/* 1554 */       getPlayerList().reload();
/*      */     } else {
/* 1556 */       postToMainThread(() -> reload());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static MinecraftServer getServer() {
/* 1564 */     return (Bukkit.getServer() instanceof CraftServer) ? ((CraftServer)Bukkit.getServer()).getServer() : null;
/*      */   }
/*      */   
/*      */   public abstract PropertyManager getPropertyManager();
/*      */   
/*      */   public abstract boolean init() throws IOException;
/*      */   
/*      */   public abstract boolean getGenerateStructures();
/*      */   
/*      */   public abstract EnumGamemode getGamemode();
/*      */   
/*      */   public abstract EnumDifficulty getDifficulty();
/*      */   
/*      */   public abstract boolean isHardcore();
/*      */   
/*      */   public abstract int q();
/*      */   
/*      */   public abstract boolean r();
/*      */   
/*      */   public abstract boolean s();
/*      */   
/*      */   public abstract boolean aa();
/*      */   
/*      */   public abstract boolean af();
/*      */   
/*      */   public abstract boolean getEnableCommandBlock();
/*      */   
/*      */   public abstract String a(EnumGamemode paramEnumGamemode, boolean paramBoolean);
/*      */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\MinecraftServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */