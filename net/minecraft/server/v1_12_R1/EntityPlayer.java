/*      */ package net.minecraft.server.v1_12_R1;
/*      */ import com.google.common.base.Preconditions;
/*      */ import com.google.common.collect.Lists;
/*      */ import com.mojang.authlib.GameProfile;
/*      */ import io.netty.buffer.Unpooled;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import javax.annotation.Nullable;
/*      */ import org.apache.logging.log4j.LogManager;
/*      */ import org.bukkit.Bukkit;
/*      */ import org.bukkit.Location;
/*      */ import org.bukkit.WeatherType;
/*      */ import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
/*      */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftHumanEntity;
/*      */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
/*      */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*      */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
/*      */ import org.bukkit.craftbukkit.v1_12_R1.util.CraftChatMessage;
/*      */ import org.bukkit.entity.Player;
/*      */ import org.bukkit.event.Event;
/*      */ import org.bukkit.event.entity.PlayerDeathEvent;
/*      */ import org.bukkit.event.inventory.InventoryType;
/*      */ import org.bukkit.event.player.PlayerChangedMainHandEvent;
/*      */ import org.bukkit.event.player.PlayerGameModeChangeEvent;
/*      */ import org.bukkit.event.player.PlayerLocaleChangeEvent;
/*      */ import org.bukkit.event.player.PlayerTeleportEvent;
/*      */ import org.bukkit.inventory.ItemStack;
/*      */ import org.bukkit.inventory.MainHand;
/*      */ 
/*      */ public class EntityPlayer extends EntityHuman implements ICrafting {
/*   33 */   private static final Logger bV = LogManager.getLogger();
/*   34 */   public String locale = "en_us";
/*      */   public PlayerConnection playerConnection;
/*      */   public final MinecraftServer server;
/*      */   public final PlayerInteractManager playerInteractManager;
/*      */   public double d;
/*      */   public double e;
/*   40 */   public final List<Integer> removeQueue = Lists.newLinkedList();
/*      */   private final AdvancementDataPlayer bY;
/*      */   private final ServerStatisticManager bZ;
/*   43 */   private float ca = Float.MIN_VALUE;
/*   44 */   private int cb = Integer.MIN_VALUE;
/*   45 */   private int cc = Integer.MIN_VALUE;
/*   46 */   private int cd = Integer.MIN_VALUE;
/*   47 */   private int ce = Integer.MIN_VALUE;
/*   48 */   private int cf = Integer.MIN_VALUE;
/*   49 */   private float lastHealthSent = -1.0E8F;
/*   50 */   private int ch = -99999999;
/*      */   private boolean ci = true;
/*   52 */   public int lastSentExp = -99999999;
/*   53 */   public int invulnerableTicks = 60;
/*      */   private EntityHuman.EnumChatVisibility cl;
/*      */   private boolean cm = true;
/*   56 */   private long cn = System.currentTimeMillis();
/*      */   private Entity co;
/*      */   public boolean worldChangeInvuln;
/*      */   private boolean cq;
/*   60 */   private final RecipeBookServer cr = new RecipeBookServer();
/*      */   
/*      */   private Vec3D cs;
/*      */   
/*      */   private int ct;
/*      */   private boolean cu;
/*      */   private Vec3D cv;
/*      */   private int containerCounter;
/*      */   public boolean f;
/*      */   public int ping;
/*      */   public boolean viewingCredits;
/*      */   public String displayName;
/*      */   public IChatBaseComponent listName;
/*      */   public Location compassTarget;
/*   74 */   public int newExp = 0;
/*   75 */   public int newLevel = 0;
/*   76 */   public int newTotalExp = 0; public boolean keepLevel = false; public double maxHealthCache; public boolean joining = true;
/*      */   public boolean sentListPacket = false;
/*      */   public long timeOffset;
/*      */   public boolean relativeTime;
/*      */   public WeatherType weather;
/*      */   private float pluginRainPosition;
/*      */   private float pluginRainPositionPrevious;
/*      */   
/*   84 */   public EntityPlayer(MinecraftServer minecraftserver, WorldServer worldserver, GameProfile gameprofile, PlayerInteractManager playerinteractmanager) { super(worldserver, gameprofile);
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
/* 1353 */     this.timeOffset = 0L;
/* 1354 */     this.relativeTime = true;
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
/* 1366 */     this.weather = null; playerinteractmanager.player = this; this.playerInteractManager = playerinteractmanager; BlockPosition blockposition = worldserver.getSpawn(); if (worldserver.worldProvider.m() && worldserver.getWorldData().getGameType() != EnumGamemode.ADVENTURE) { int i = Math.max(0, minecraftserver.a(worldserver)); int j = MathHelper.floor(worldserver.getWorldBorder().b(blockposition.getX(), blockposition.getZ())); if (j < i) i = j;  if (j <= 1) i = 1;  blockposition = worldserver.q(blockposition.a(this.random.nextInt(i * 2 + 1) - i, 0, this.random.nextInt(i * 2 + 1) - i)); }  this.server = minecraftserver; this.bZ = minecraftserver.getPlayerList().getStatisticManager(this); this.bY = minecraftserver.getPlayerList().h(this); this.P = 1.0F; setPositionRotation(blockposition, 0.0F, 0.0F); while (!worldserver.getCubes(this, getBoundingBox()).isEmpty() && this.locY < 255.0D) setPosition(this.locX, this.locY + 1.0D, this.locZ);  this.displayName = getName(); this.canPickUpLoot = true; this.maxHealthCache = getMaxHealth(); }
/*      */   public void a(NBTTagCompound nbttagcompound) { super.a(nbttagcompound); if (nbttagcompound.hasKeyOfType("playerGameType", 99)) if (C_().getForceGamemode()) { this.playerInteractManager.setGameMode(C_().getGamemode()); } else { this.playerInteractManager.setGameMode(EnumGamemode.getById(nbttagcompound.getInt("playerGameType"))); }   if (nbttagcompound.hasKeyOfType("enteredNetherPosition", 10)) { NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("enteredNetherPosition"); this.cv = new Vec3D(nbttagcompound1.getDouble("x"), nbttagcompound1.getDouble("y"), nbttagcompound1.getDouble("z")); }  this.cq = nbttagcompound.getBoolean("seenCredits"); if (nbttagcompound.hasKeyOfType("recipeBook", 10)) this.cr.a(nbttagcompound.getCompound("recipeBook"));  getBukkitEntity().readExtraData(nbttagcompound); } public static void a(DataConverterManager dataconvertermanager) { dataconvertermanager.a(DataConverterTypes.PLAYER, new DataInspector() {
/*      */           public NBTTagCompound a(DataConverter dataconverter, NBTTagCompound nbttagcompound, int i) { if (nbttagcompound.hasKeyOfType("RootVehicle", 10)) { NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("RootVehicle"); if (nbttagcompound1.hasKeyOfType("Entity", 10)) nbttagcompound1.set("Entity", dataconverter.a(DataConverterTypes.ENTITY, nbttagcompound1.getCompound("Entity"), i));  }  return nbttagcompound; }
/* 1369 */         }); } public void b(NBTTagCompound nbttagcompound) { super.b(nbttagcompound); nbttagcompound.setInt("playerGameType", this.playerInteractManager.getGameMode().getId()); nbttagcompound.setBoolean("seenCredits", this.cq); if (this.cv != null) { NBTTagCompound nbttagcompound1 = new NBTTagCompound(); nbttagcompound1.setDouble("x", this.cv.x); nbttagcompound1.setDouble("y", this.cv.y); nbttagcompound1.setDouble("z", this.cv.z); nbttagcompound.set("enteredNetherPosition", nbttagcompound1); }  Entity entity = getVehicle(); Entity entity1 = bJ(); if (entity1 != null && entity != this && entity.<EntityPlayer>b(EntityPlayer.class).size() == 1) { NBTTagCompound nbttagcompound2 = new NBTTagCompound(); NBTTagCompound nbttagcompound3 = new NBTTagCompound(); entity.d(nbttagcompound3); nbttagcompound2.a("Attach", entity1.getUniqueID()); nbttagcompound2.set("Entity", nbttagcompound3); nbttagcompound.set("RootVehicle", nbttagcompound2); }  nbttagcompound.set("recipeBook", this.cr.c()); getBukkitEntity().setExtraData(nbttagcompound); } public void spawnIn(World world) { super.spawnIn(world); if (world == null) { this.dead = false; BlockPosition position = null; if (this.spawnWorld != null && !this.spawnWorld.equals("")) { CraftWorld cworld = (CraftWorld)Bukkit.getServer().getWorld(this.spawnWorld); if (cworld != null && getBed() != null) { world = cworld.getHandle(); position = EntityHuman.getBed(cworld.getHandle(), getBed(), false); }  }  if (world == null || position == null) { world = ((CraftWorld)Bukkit.getServer().getWorlds().get(0)).getHandle(); position = world.getSpawn(); }  this.world = world; setPosition(position.getX() + 0.5D, position.getY(), position.getZ() + 0.5D); }  this.dimension = ((WorldServer)this.world).dimension; this.playerInteractManager.a((WorldServer)world); } public void levelDown(int i) { super.levelDown(i); this.lastSentExp = -1; } public void enchantDone(ItemStack itemstack, int i) { super.enchantDone(itemstack, i); this.lastSentExp = -1; } public void syncInventory() { this.activeContainer.addSlotListener(this); } public void enterCombat() { super.enterCombat(); this.playerConnection.sendPacket(new PacketPlayOutCombatEvent(getCombatTracker(), PacketPlayOutCombatEvent.EnumCombatEventType.ENTER_COMBAT)); } public void exitCombat() { super.exitCombat(); this.playerConnection.sendPacket(new PacketPlayOutCombatEvent(getCombatTracker(), PacketPlayOutCombatEvent.EnumCombatEventType.END_COMBAT)); } protected void a(IBlockData iblockdata) { CriterionTriggers.d.a(this, iblockdata); } protected ItemCooldown l() { return new ItemCooldownPlayer(this); } public void B_() { if (this.joining) this.joining = false;  this.playerInteractManager.a(); this.invulnerableTicks--; if (this.noDamageTicks > 0) this.noDamageTicks--;  this.activeContainer.b(); if (!this.world.isClientSide && !this.activeContainer.a(this)) { closeInventory(); this.activeContainer = this.defaultContainer; }  while (!this.removeQueue.isEmpty()) { int i = Math.min(this.removeQueue.size(), 2147483647); int[] aint = new int[i]; Iterator<Integer> iterator = this.removeQueue.iterator(); int j = 0; while (iterator.hasNext() && j < i) { aint[j++] = ((Integer)iterator.next()).intValue(); iterator.remove(); }  this.playerConnection.sendPacket(new PacketPlayOutEntityDestroy(aint)); }  Entity entity = getSpecatorTarget(); if (entity != this) if (entity.isAlive()) { setLocation(entity.locX, entity.locY, entity.locZ, entity.yaw, entity.pitch); this.server.getPlayerList().d(this); if (isSneaking()) setSpectatorTarget(this);  } else { setSpectatorTarget(this); }   CriterionTriggers.v.a(this); if (this.cs != null) CriterionTriggers.t.a(this, this.cs, this.ticksLived - this.ct);  this.bY.b(this); } public void playerTick() { try { super.B_(); for (int i = 0; i < this.inventory.getSize(); i++) { ItemStack itemstack = this.inventory.getItem(i); if (!itemstack.isEmpty() && itemstack.getItem().f()) { Packet<?> packet = ((ItemWorldMapBase)itemstack.getItem()).a(itemstack, this.world, this); if (packet != null) this.playerConnection.sendPacket(packet);  }  }  if (getHealth() != this.lastHealthSent || this.ch != this.foodData.getFoodLevel() || ((this.foodData.getSaturationLevel() == 0.0F)) != this.ci) { this.playerConnection.sendPacket(new PacketPlayOutUpdateHealth(getBukkitEntity().getScaledHealth(), this.foodData.getFoodLevel(), this.foodData.getSaturationLevel())); this.lastHealthSent = getHealth(); this.ch = this.foodData.getFoodLevel(); this.ci = (this.foodData.getSaturationLevel() == 0.0F); }  if (getHealth() + getAbsorptionHearts() != this.ca) { this.ca = getHealth() + getAbsorptionHearts(); a(IScoreboardCriteria.g, MathHelper.f(this.ca)); }  if (this.foodData.getFoodLevel() != this.cb) { this.cb = this.foodData.getFoodLevel(); a(IScoreboardCriteria.h, MathHelper.f(this.cb)); }  if (getAirTicks() != this.cc) { this.cc = getAirTicks(); a(IScoreboardCriteria.i, MathHelper.f(this.cc)); }  if (this.maxHealthCache != getMaxHealth()) getBukkitEntity().updateScaledHealth();  if (getArmorStrength() != this.cd) { this.cd = getArmorStrength(); a(IScoreboardCriteria.j, MathHelper.f(this.cd)); }  if (this.expTotal != this.cf) { this.cf = this.expTotal; a(IScoreboardCriteria.k, MathHelper.f(this.cf)); }  if (this.expLevel != this.ce) { this.ce = this.expLevel; a(IScoreboardCriteria.l, MathHelper.f(this.ce)); }  if (this.expTotal != this.lastSentExp) { this.lastSentExp = this.expTotal; this.playerConnection.sendPacket(new PacketPlayOutExperience(this.exp, this.expTotal, this.expLevel)); }  if (this.ticksLived % 20 == 0) CriterionTriggers.o.a(this);  if (this.oldLevel == -1) this.oldLevel = this.expLevel;  if (this.oldLevel != this.expLevel) { CraftEventFactory.callPlayerLevelChangeEvent(this.world.getServer().getPlayer(this), this.oldLevel, this.expLevel); this.oldLevel = this.expLevel; }  } catch (Throwable throwable) { CrashReport crashreport = CrashReport.a(throwable, "Ticking player"); CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Player being ticked"); appendEntityCrashDetails(crashreportsystemdetails); throw new ReportedException(crashreport); }  } private void a(IScoreboardCriteria iscoreboardcriteria, int i) { Collection collection = this.world.getServer().getScoreboardManager().getScoreboardScores(iscoreboardcriteria, getName(), new ArrayList()); Iterator<ScoreboardScore> iterator = collection.iterator(); while (iterator.hasNext()) { ScoreboardScore scoreboardscore = iterator.next(); scoreboardscore.setScore(i); }  } public void die(DamageSource damagesource) { boolean flag = this.world.getGameRules().getBoolean("showDeathMessages"); this.playerConnection.sendPacket(new PacketPlayOutCombatEvent(getCombatTracker(), PacketPlayOutCombatEvent.EnumCombatEventType.ENTITY_DIED, flag)); if (this.dead) return;  List<ItemStack> loot = new ArrayList<>(this.inventory.getSize()); boolean keepInventory = !(!this.world.getGameRules().getBoolean("keepInventory") && !isSpectator()); if (!keepInventory) for (ItemStack item : this.inventory.getContents()) { if (!item.isEmpty() && !EnchantmentManager.shouldNotDrop(item)) loot.add(CraftItemStack.asCraftMirror(item));  }   IChatBaseComponent chatmessage = getCombatTracker().getDeathMessage(); String deathmessage = chatmessage.toPlainText(); PlayerDeathEvent event = CraftEventFactory.callPlayerDeathEvent(this, loot, deathmessage, keepInventory); String deathMessage = event.getDeathMessage(); if (deathMessage != null && deathMessage.length() > 0 && flag) if (deathMessage.equals(deathmessage)) { ScoreboardTeamBase scoreboardteambase = aY(); if (scoreboardteambase != null && scoreboardteambase.getDeathMessageVisibility() != ScoreboardTeamBase.EnumNameTagVisibility.ALWAYS) { if (scoreboardteambase.getDeathMessageVisibility() == ScoreboardTeamBase.EnumNameTagVisibility.HIDE_FOR_OTHER_TEAMS) { this.server.getPlayerList().a(this, chatmessage); } else if (scoreboardteambase.getDeathMessageVisibility() == ScoreboardTeamBase.EnumNameTagVisibility.HIDE_FOR_OWN_TEAM) { this.server.getPlayerList().b(this, chatmessage); }  } else { this.server.getPlayerList().sendMessage(chatmessage); }  } else { this.server.getPlayerList().sendMessage(CraftChatMessage.fromString(deathMessage)); }   releaseShoulderEntities(); if (!event.getKeepInventory()) this.inventory.clear();  closeInventory(); setSpectatorTarget(this); Collection collection = this.world.getServer().getScoreboardManager().getScoreboardScores(IScoreboardCriteria.d, getName(), new ArrayList()); Iterator<ScoreboardScore> iterator = collection.iterator(); while (iterator.hasNext()) { ScoreboardScore scoreboardscore = iterator.next(); scoreboardscore.incrementScore(); }  EntityLiving entityliving = ci(); if (entityliving != null) { EntityTypes.MonsterEggInfo entitytypes_monsteregginfo = EntityTypes.eggInfo.get(EntityTypes.a(entityliving)); if (entitytypes_monsteregginfo != null) b(entitytypes_monsteregginfo.killedByEntityStatistic);  entityliving.a(this, this.bb, damagesource); }  b(StatisticList.A); a(StatisticList.h); extinguish(); setFlag(0, false); getCombatTracker().g(); } public void a(Entity entity, int i, DamageSource damagesource) { if (entity != this) { super.a(entity, i, damagesource); addScore(i); Collection<ScoreboardScore> collection = this.world.getServer().getScoreboardManager().getScoreboardScores(IScoreboardCriteria.f, getName(), new ArrayList()); if (entity instanceof EntityHuman) { b(StatisticList.D); this.world.getServer().getScoreboardManager().getScoreboardScores(IScoreboardCriteria.e, getName(), collection); } else { b(StatisticList.B); }  collection.addAll(E(entity)); Iterator<ScoreboardScore> iterator = collection.iterator(); while (iterator.hasNext()) ((ScoreboardScore)iterator.next()).incrementScore();  CriterionTriggers.b.a(this, entity, damagesource); }  } private Collection<ScoreboardScore> E(Entity entity) { String s = (entity instanceof EntityHuman) ? entity.getName() : entity.bn(); ScoreboardTeam scoreboardteam = getScoreboard().getPlayerTeam(getName()); if (scoreboardteam != null) { int i = scoreboardteam.getColor().b(); if (i >= 0 && i < IScoreboardCriteria.n.length) { Iterator<ScoreboardObjective> iterator = getScoreboard().getObjectivesForCriteria(IScoreboardCriteria.n[i]).iterator(); while (iterator.hasNext()) { ScoreboardObjective scoreboardobjective = iterator.next(); ScoreboardScore scoreboardscore = getScoreboard().getPlayerScoreForObjective(s, scoreboardobjective); scoreboardscore.incrementScore(); }  }  }  ScoreboardTeam scoreboardteam1 = getScoreboard().getPlayerTeam(s); if (scoreboardteam1 != null) { int j = scoreboardteam1.getColor().b(); if (j >= 0 && j < IScoreboardCriteria.m.length) return this.world.getServer().getScoreboardManager().getScoreboardScores(IScoreboardCriteria.m[j], getName(), new ArrayList());  }  return Lists.newArrayList(); } public boolean damageEntity(DamageSource damagesource, float f) { if (isInvulnerable(damagesource)) return false;  boolean flag = (this.server.aa() && canPvP() && "fall".equals(damagesource.translationIndex)); if (!flag && this.invulnerableTicks > 0 && damagesource != DamageSource.OUT_OF_WORLD) return false;  if (damagesource instanceof EntityDamageSource) { Entity entity = damagesource.getEntity(); if (entity instanceof EntityHuman && !a((EntityHuman)entity)) return false;  if (entity instanceof EntityArrow) { EntityArrow entityarrow = (EntityArrow)entity; if (entityarrow.shooter instanceof EntityHuman && !a((EntityHuman)entityarrow.shooter)) return false;  }  }  return super.damageEntity(damagesource, f); } public boolean a(EntityHuman entityhuman) { return !canPvP() ? false : super.a(entityhuman); } private boolean canPvP() { return this.world.pvpMode; } @Nullable public Entity b(int i) { if (isSleeping()) return this;  if (this.dimension == 0 && i == -1) { this.cv = new Vec3D(this.locX, this.locY, this.locZ); } else if (this.dimension != -1 && i != 0) { this.cv = null; }  if (this.dimension == 1 && i == 1) { this.worldChangeInvuln = true; this.world.kill(this); if (!this.viewingCredits) { this.viewingCredits = true; this.playerConnection.sendPacket(new PacketPlayOutGameStateChange(4, this.cq ? 0.0F : 1.0F)); this.cq = true; }  return this; }  if (this.dimension == 0 && i == 1) i = 1;  PlayerTeleportEvent.TeleportCause cause = (this.dimension == 1 || i == 1) ? PlayerTeleportEvent.TeleportCause.END_PORTAL : PlayerTeleportEvent.TeleportCause.NETHER_PORTAL; this.server.getPlayerList().changeDimension(this, i, cause); this.playerConnection.sendPacket(new PacketPlayOutWorldEvent(1032, BlockPosition.ZERO, 0, false)); this.lastSentExp = -1; this.lastHealthSent = -1.0F; this.ch = -1; return this; } public boolean a(EntityPlayer entityplayer) { return entityplayer.isSpectator() ? ((getSpecatorTarget() == this)) : (isSpectator() ? false : super.a(entityplayer)); } private void a(TileEntity tileentity) { if (tileentity != null) { PacketPlayOutTileEntityData packetplayouttileentitydata = tileentity.getUpdatePacket(); if (packetplayouttileentitydata != null) this.playerConnection.sendPacket(packetplayouttileentitydata);  }  } public void receive(Entity entity, int i) { super.receive(entity, i); this.activeContainer.b(); } public EntityHuman.EnumBedResult a(BlockPosition blockposition) { EntityHuman.EnumBedResult entityhuman_enumbedresult = super.a(blockposition); if (entityhuman_enumbedresult == EntityHuman.EnumBedResult.OK) { b(StatisticList.ab); PacketPlayOutBed packetplayoutbed = new PacketPlayOutBed(this, blockposition); x().getTracker().a(this, packetplayoutbed); this.playerConnection.a(this.locX, this.locY, this.locZ, this.yaw, this.pitch); this.playerConnection.sendPacket(packetplayoutbed); CriterionTriggers.p.a(this); }  return entityhuman_enumbedresult; } public void a(boolean flag, boolean flag1, boolean flag2) { if (!this.sleeping) return;  if (isSleeping()) x().getTracker().sendPacketToEntity(this, new PacketPlayOutAnimation(this, 2));  super.a(flag, flag1, flag2); if (this.playerConnection != null) this.playerConnection.a(this.locX, this.locY, this.locZ, this.yaw, this.pitch);  } public boolean a(Entity entity, boolean flag) { Entity entity1 = bJ(); if (!super.a(entity, flag)) return false;  Entity entity2 = bJ(); if (entity2 != entity1 && this.playerConnection != null) this.playerConnection.a(this.locX, this.locY, this.locZ, this.yaw, this.pitch);  return true; } public void stopRiding() { Entity entity = bJ(); super.stopRiding(); Entity entity1 = bJ(); if (entity1 != entity && this.playerConnection != null) this.playerConnection.a(this.locX, this.locY, this.locZ, this.yaw, this.pitch);  } public boolean isInvulnerable(DamageSource damagesource) { return !(!super.isInvulnerable(damagesource) && !L()); } protected void a(double d0, boolean flag, IBlockData iblockdata, BlockPosition blockposition) {} protected void b(BlockPosition blockposition) { if (!isSpectator()) super.b(blockposition);  } public void a(double d0, boolean flag) { int i = MathHelper.floor(this.locX); int j = MathHelper.floor(this.locY - 0.20000000298023224D); int k = MathHelper.floor(this.locZ); BlockPosition blockposition = new BlockPosition(i, j, k); IBlockData iblockdata = this.world.getType(blockposition); if (iblockdata.getMaterial() == Material.AIR) { BlockPosition blockposition1 = blockposition.down(); IBlockData iblockdata1 = this.world.getType(blockposition1); Block block = iblockdata1.getBlock(); if (block instanceof BlockFence || block instanceof BlockCobbleWall || block instanceof BlockFenceGate) { blockposition = blockposition1; iblockdata = iblockdata1; }  }  super.a(d0, flag, iblockdata, blockposition); } public void openSign(TileEntitySign tileentitysign) { tileentitysign.a(this); this.playerConnection.sendPacket(new PacketPlayOutOpenSignEditor(tileentitysign.getPosition())); } public int nextContainerCounter() { this.containerCounter = this.containerCounter % 100 + 1; return this.containerCounter; } public void openTileEntity(ITileEntityContainer itileentitycontainer) { boolean cancelled = (itileentitycontainer instanceof ILootable && ((ILootable)itileentitycontainer).b() != null && isSpectator()); Container container = CraftEventFactory.callInventoryOpenEvent(this, itileentitycontainer.createContainer(this.inventory, this), cancelled); if (container == null) return;  nextContainerCounter(); this.activeContainer = container; this.playerConnection.sendPacket(new PacketPlayOutOpenWindow(this.containerCounter, itileentitycontainer.getContainerName(), itileentitycontainer.getScoreboardDisplayName())); this.activeContainer.windowId = this.containerCounter; this.activeContainer.addSlotListener(this); } public void openContainer(IInventory iinventory) { boolean cancelled = false; if (iinventory instanceof ITileInventory) { ITileInventory itileinventory = (ITileInventory)iinventory; cancelled = (itileinventory.isLocked() && !a(itileinventory.getLock()) && !isSpectator()); }  if (iinventory instanceof ITileEntityContainer) { if (iinventory instanceof TileEntity) Preconditions.checkArgument((((TileEntity)iinventory).getWorld() != null), "Container must have world to be opened");  container = ((ITileEntityContainer)iinventory).createContainer(this.inventory, this); } else { container = new ContainerChest(this.inventory, iinventory, this); }  Container container = CraftEventFactory.callInventoryOpenEvent(this, container, cancelled); if (container == null && !cancelled) { iinventory.closeContainer(this); return; }  if (iinventory instanceof ILootable && ((ILootable)iinventory).b() != null && isSpectator()) { a((new ChatMessage("container.spectatorCantOpen", new Object[0])).setChatModifier((new ChatModifier()).setColor(EnumChatFormat.RED)), true); } else { if (this.activeContainer != this.defaultContainer) closeInventory();  if (iinventory instanceof ITileInventory) { ITileInventory itileinventory = (ITileInventory)iinventory; if (itileinventory.isLocked() && !a(itileinventory.getLock()) && !isSpectator()) { this.playerConnection.sendPacket(new PacketPlayOutChat(new ChatMessage("container.isLocked", new Object[] { iinventory.getScoreboardDisplayName() }), ChatMessageType.GAME_INFO)); this.playerConnection.sendPacket(new PacketPlayOutNamedSoundEffect(SoundEffects.ab, SoundCategory.BLOCKS, this.locX, this.locY, this.locZ, 1.0F, 1.0F)); iinventory.closeContainer(this); return; }  }  nextContainerCounter(); if (iinventory instanceof ITileEntityContainer) { this.activeContainer = container; this.playerConnection.sendPacket(new PacketPlayOutOpenWindow(this.containerCounter, ((ITileEntityContainer)iinventory).getContainerName(), iinventory.getScoreboardDisplayName(), iinventory.getSize())); } else { this.activeContainer = container; this.playerConnection.sendPacket(new PacketPlayOutOpenWindow(this.containerCounter, "minecraft:container", iinventory.getScoreboardDisplayName(), iinventory.getSize())); }  this.activeContainer.windowId = this.containerCounter; this.activeContainer.addSlotListener(this); }  } public void openTrade(IMerchant imerchant) { Container container = CraftEventFactory.callInventoryOpenEvent(this, new ContainerMerchant(this.inventory, imerchant, this.world)); if (container == null) return;  nextContainerCounter(); this.activeContainer = container; this.activeContainer.windowId = this.containerCounter; this.activeContainer.addSlotListener(this); InventoryMerchant inventorymerchant = ((ContainerMerchant)this.activeContainer).e(); IChatBaseComponent ichatbasecomponent = imerchant.getScoreboardDisplayName(); this.playerConnection.sendPacket(new PacketPlayOutOpenWindow(this.containerCounter, "minecraft:villager", ichatbasecomponent, inventorymerchant.getSize())); MerchantRecipeList merchantrecipelist = imerchant.getOffers(this); if (merchantrecipelist != null) { PacketDataSerializer packetdataserializer = new PacketDataSerializer(Unpooled.buffer()); packetdataserializer.writeInt(this.containerCounter); merchantrecipelist.a(packetdataserializer); this.playerConnection.sendPacket(new PacketPlayOutCustomPayload("MC|TrList", packetdataserializer)); }  } public void openHorseInventory(EntityHorseAbstract entityhorseabstract, IInventory iinventory) { Container container = CraftEventFactory.callInventoryOpenEvent(this, new ContainerHorse(this.inventory, iinventory, entityhorseabstract, this)); if (container == null) { iinventory.closeContainer(this); return; }  if (this.activeContainer != this.defaultContainer) closeInventory();  nextContainerCounter(); this.playerConnection.sendPacket(new PacketPlayOutOpenWindow(this.containerCounter, "EntityHorse", iinventory.getScoreboardDisplayName(), iinventory.getSize(), entityhorseabstract.getId())); this.activeContainer = container; this.activeContainer.windowId = this.containerCounter; this.activeContainer.addSlotListener(this); } public void a(ItemStack itemstack, EnumHand enumhand) { Item item = itemstack.getItem(); if (item == Items.WRITTEN_BOOK) { PacketDataSerializer packetdataserializer = new PacketDataSerializer(Unpooled.buffer()); packetdataserializer.a(enumhand); this.playerConnection.sendPacket(new PacketPlayOutCustomPayload("MC|BOpen", packetdataserializer)); }  } public void a(TileEntityCommand tileentitycommand) { tileentitycommand.c(true); a(tileentitycommand); } public void a(Container container, int i, ItemStack itemstack) { if (!(container.getSlot(i) instanceof SlotResult)) { if (container == this.defaultContainer) CriterionTriggers.e.a(this, this.inventory);  if (!this.f) this.playerConnection.sendPacket(new PacketPlayOutSetSlot(container.windowId, i, itemstack));  }  } public void updateInventory(Container container) { a(container, container.a()); } public void a(Container container, NonNullList<ItemStack> nonnulllist) { this.playerConnection.sendPacket(new PacketPlayOutWindowItems(container.windowId, nonnulllist)); this.playerConnection.sendPacket(new PacketPlayOutSetSlot(-1, -1, this.inventory.getCarried())); if (EnumSet.<InventoryType>of(InventoryType.CRAFTING, InventoryType.WORKBENCH).contains(container.getBukkitView().getType())) this.playerConnection.sendPacket(new PacketPlayOutSetSlot(container.windowId, 0, container.getSlot(0).getItem()));  } public void setContainerData(Container container, int i, int j) { this.playerConnection.sendPacket(new PacketPlayOutWindowData(container.windowId, i, j)); } public void setContainerData(Container container, IInventory iinventory) { for (int i = 0; i < iinventory.h(); i++) this.playerConnection.sendPacket(new PacketPlayOutWindowData(container.windowId, i, iinventory.getProperty(i)));  } public void closeInventory() { CraftEventFactory.handleInventoryCloseEvent(this); this.playerConnection.sendPacket(new PacketPlayOutCloseWindow(this.activeContainer.windowId)); r(); } public void broadcastCarriedItem() { if (!this.f) this.playerConnection.sendPacket(new PacketPlayOutSetSlot(-1, -1, this.inventory.getCarried()));  } public void r() { this.activeContainer.b(this); this.activeContainer = this.defaultContainer; } public void a(float f, float f1, boolean flag, boolean flag1) { if (isPassenger()) { if (f >= -1.0F && f <= 1.0F) this.be = f;  if (f1 >= -1.0F && f1 <= 1.0F) this.bg = f1;  this.bd = flag; setSneaking(flag1); }  } public WeatherType getPlayerWeather() { return this.weather; }
/*      */   public void a(Statistic statistic, int i) { if (statistic != null) { this.bZ.b(this, statistic, i); Iterator<ScoreboardObjective> iterator = getScoreboard().getObjectivesForCriteria(statistic.f()).iterator(); while (iterator.hasNext()) { ScoreboardObjective scoreboardobjective = iterator.next(); getScoreboard().getPlayerScoreForObjective(getName(), scoreboardobjective).addScore(i); }  }  }
/*      */   public void a(Statistic statistic) { if (statistic != null) { this.bZ.setStatistic(this, statistic, 0); Iterator<ScoreboardObjective> iterator = getScoreboard().getObjectivesForCriteria(statistic.f()).iterator(); while (iterator.hasNext()) { ScoreboardObjective scoreboardobjective = iterator.next(); getScoreboard().getPlayerScoreForObjective(getName(), scoreboardobjective).setScore(0); }  }  }
/*      */   public void a(List<IRecipe> list) { this.cr.a(list, this); }
/* 1373 */   public void a(MinecraftKey[] aminecraftkey) { ArrayList<IRecipe> arraylist = Lists.newArrayList(); MinecraftKey[] aminecraftkey1 = aminecraftkey; int i = aminecraftkey.length; for (int j = 0; j < i; j++) { MinecraftKey minecraftkey = aminecraftkey1[j]; if (CraftingManager.a(minecraftkey) == null) { Bukkit.getLogger().warning("Ignoring grant of non existent recipe " + minecraftkey); } else { arraylist.add(CraftingManager.a(minecraftkey)); }  }  a(arraylist); } public void b(List<IRecipe> list) { this.cr.b(list, this); } public void s() { this.cu = true; ejectPassengers(); if (this.sleeping) a(true, false, false);  } public boolean t() { return this.cu; } public void triggerHealthUpdate() { this.lastHealthSent = -1.0E8F; this.lastSentExp = -1; } public void sendMessage(IChatBaseComponent[] ichatbasecomponent) { IChatBaseComponent[] arrayOfIChatBaseComponent; int i; byte b; for (i = (arrayOfIChatBaseComponent = ichatbasecomponent).length, b = 0; b < i; ) { IChatBaseComponent component = arrayOfIChatBaseComponent[b]; sendMessage(component); b++; }  } public void a(IChatBaseComponent ichatbasecomponent, boolean flag) { this.playerConnection.sendPacket(new PacketPlayOutChat(ichatbasecomponent, flag ? ChatMessageType.GAME_INFO : ChatMessageType.CHAT)); } protected void v() { if (!this.activeItem.isEmpty() && isHandRaised()) { this.playerConnection.sendPacket(new PacketPlayOutEntityStatus(this, (byte)9)); super.v(); }  } public void copyFrom(EntityPlayer entityplayer, boolean flag) { if (flag) { this.inventory.a(entityplayer.inventory); setHealth(entityplayer.getHealth()); this.foodData = entityplayer.foodData; this.expLevel = entityplayer.expLevel; this.expTotal = entityplayer.expTotal; this.exp = entityplayer.exp; setScore(entityplayer.getScore()); this.an = entityplayer.an; this.ao = entityplayer.ao; this.ap = entityplayer.ap; } else if (this.world.getGameRules().getBoolean("keepInventory") || entityplayer.isSpectator()) { this.inventory.a(entityplayer.inventory); this.expLevel = entityplayer.expLevel; this.expTotal = entityplayer.expTotal; this.exp = entityplayer.exp; setScore(entityplayer.getScore()); }  this.bS = entityplayer.bS; this.enderChest = entityplayer.enderChest; getDataWatcher().set(br, entityplayer.getDataWatcher().<Byte>get(br)); this.lastSentExp = -1; this.lastHealthSent = -1.0F; this.ch = -1; this.removeQueue.addAll(entityplayer.removeQueue); this.cq = entityplayer.cq; this.cv = entityplayer.cv; setShoulderEntityLeft(entityplayer.getShoulderEntityLeft()); setShoulderEntityRight(entityplayer.getShoulderEntityRight()); } protected void a(MobEffect mobeffect) { super.a(mobeffect); this.playerConnection.sendPacket(new PacketPlayOutEntityEffect(getId(), mobeffect)); if (mobeffect.getMobEffect() == MobEffects.LEVITATION) { this.ct = this.ticksLived; this.cs = new Vec3D(this.locX, this.locY, this.locZ); }  CriterionTriggers.z.a(this); } protected void a(MobEffect mobeffect, boolean flag) { super.a(mobeffect, flag); this.playerConnection.sendPacket(new PacketPlayOutEntityEffect(getId(), mobeffect)); CriterionTriggers.z.a(this); } protected void b(MobEffect mobeffect) { super.b(mobeffect); this.playerConnection.sendPacket(new PacketPlayOutRemoveEntityEffect(getId(), mobeffect.getMobEffect())); if (mobeffect.getMobEffect() == MobEffects.LEVITATION) this.cs = null;  CriterionTriggers.z.a(this); } public void enderTeleportTo(double d0, double d1, double d2) { this.playerConnection.a(d0, d1, d2, this.yaw, this.pitch); } public void a(Entity entity) { x().getTracker().sendPacketToEntity(this, new PacketPlayOutAnimation(entity, 4)); } public void b(Entity entity) { x().getTracker().sendPacketToEntity(this, new PacketPlayOutAnimation(entity, 5)); } public void updateAbilities() { if (this.playerConnection != null) { this.playerConnection.sendPacket(new PacketPlayOutAbilities(this.abilities)); G(); }  } public WorldServer x() { return (WorldServer)this.world; } public void a(EnumGamemode enumgamemode) { if (enumgamemode == this.playerInteractManager.getGameMode()) return;  PlayerGameModeChangeEvent event = new PlayerGameModeChangeEvent((Player)getBukkitEntity(), GameMode.getByValue(enumgamemode.getId())); this.world.getServer().getPluginManager().callEvent((Event)event); if (event.isCancelled()) return;  this.playerInteractManager.setGameMode(enumgamemode); this.playerConnection.sendPacket(new PacketPlayOutGameStateChange(3, enumgamemode.getId())); if (enumgamemode == EnumGamemode.SPECTATOR) { releaseShoulderEntities(); stopRiding(); } else { setSpectatorTarget(this); }  updateAbilities(); cE(); } public boolean isSpectator() { return (this.playerInteractManager.getGameMode() == EnumGamemode.SPECTATOR); } public boolean z() { return (this.playerInteractManager.getGameMode() == EnumGamemode.CREATIVE); } public void sendMessage(IChatBaseComponent ichatbasecomponent) { this.playerConnection.sendPacket(new PacketPlayOutChat(ichatbasecomponent)); } public boolean a(int i, String s) { if ("@".equals(s)) return getBukkitEntity().hasPermission("minecraft.command.selector");  if ("".equals(s)) return getBukkitEntity().isOp();  return getBukkitEntity().hasPermission("minecraft.command." + s); } public String A() { String s = this.playerConnection.networkManager.getSocketAddress().toString(); s = s.substring(s.indexOf("/") + 1); s = s.substring(0, s.indexOf(":")); return s; } public void a(PacketPlayInSettings packetplayinsettings) { if (getMainHand() != packetplayinsettings.getMainHand()) { PlayerChangedMainHandEvent event = new PlayerChangedMainHandEvent((Player)getBukkitEntity(), (getMainHand() == EnumMainHand.LEFT) ? MainHand.LEFT : MainHand.RIGHT); this.server.server.getPluginManager().callEvent((Event)event); }  if (!this.locale.equals(packetplayinsettings.a())) { PlayerLocaleChangeEvent event = new PlayerLocaleChangeEvent((Player)getBukkitEntity(), packetplayinsettings.a()); this.server.server.getPluginManager().callEvent((Event)event); }  this.locale = packetplayinsettings.a(); this.cl = packetplayinsettings.c(); this.cm = packetplayinsettings.d(); getDataWatcher().set(br, Byte.valueOf((byte)packetplayinsettings.e())); getDataWatcher().set(bs, Byte.valueOf((byte)((packetplayinsettings.getMainHand() == EnumMainHand.LEFT) ? 0 : 1))); } public EntityHuman.EnumChatVisibility getChatFlags() { return this.cl; } public void setResourcePack(String s, String s1) { this.playerConnection.sendPacket(new PacketPlayOutResourcePackSend(s, s1)); } public BlockPosition getChunkCoordinates() { return new BlockPosition(this.locX, this.locY + 0.5D, this.locZ); } public void resetIdleTimer() { this.cn = MinecraftServer.aw(); } public ServerStatisticManager getStatisticManager() { return this.bZ; } public RecipeBookServer F() { return this.cr; } public void c(Entity entity) { if (entity instanceof EntityHuman) { this.playerConnection.sendPacket(new PacketPlayOutEntityDestroy(new int[] { entity.getId() })); } else { this.removeQueue.add(Integer.valueOf(entity.getId())); }  } public void d(Entity entity) { this.removeQueue.remove(Integer.valueOf(entity.getId())); } protected void G() { if (isSpectator()) { bY(); setInvisible(true); } else { super.G(); }  x().getTracker().a(this); } public Entity getSpecatorTarget() { return (this.co == null) ? this : this.co; } public void setSpectatorTarget(Entity entity) { Entity entity1 = getSpecatorTarget(); this.co = (entity == null) ? this : entity; if (entity1 != this.co) { this.playerConnection.sendPacket(new PacketPlayOutCamera(this.co)); this.playerConnection.a(this.co.locX, this.co.locY, this.co.locZ, this.yaw, this.pitch, PlayerTeleportEvent.TeleportCause.SPECTATE); }  } protected void I() { if (this.portalCooldown > 0 && !this.worldChangeInvuln) this.portalCooldown--;  } public void attack(Entity entity) { if (this.playerInteractManager.getGameMode() == EnumGamemode.SPECTATOR) { setSpectatorTarget(entity); } else { super.attack(entity); }  } public long J() { return this.cn; } @Nullable public IChatBaseComponent getPlayerListName() { return this.listName; } public void a(EnumHand enumhand) { super.a(enumhand); ds(); } public boolean L() { return this.worldChangeInvuln; } public void M() { this.worldChangeInvuln = false; } public void N() { if (!CraftEventFactory.callToggleGlideEvent(this, true).isCancelled()) setFlag(7, true);  } public void O() { if (!CraftEventFactory.callToggleGlideEvent(this, false).isCancelled()) { setFlag(7, true); setFlag(7, false); }  } public AdvancementDataPlayer getAdvancementData() { return this.bY; } @Nullable public Vec3D Q() { return this.cv; } public long getPlayerTime() { if (this.relativeTime) return this.world.getDayTime() + this.timeOffset;  return this.world.getDayTime() - this.world.getDayTime() % 24000L + this.timeOffset; } public void setPlayerWeather(WeatherType type, boolean plugin) { if (!plugin && this.weather != null) {
/*      */       return;
/*      */     }
/*      */     
/* 1377 */     if (plugin) {
/* 1378 */       this.weather = type;
/*      */     }
/*      */     
/* 1381 */     if (type == WeatherType.DOWNFALL) {
/* 1382 */       this.playerConnection.sendPacket(new PacketPlayOutGameStateChange(2, 0.0F));
/*      */     } else {
/* 1384 */       this.playerConnection.sendPacket(new PacketPlayOutGameStateChange(1, 0.0F));
/*      */     }  }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateWeather(float oldRain, float newRain, float oldThunder, float newThunder) {
/* 1392 */     if (this.weather == null) {
/*      */       
/* 1394 */       if (oldRain != newRain) {
/* 1395 */         this.playerConnection.sendPacket(new PacketPlayOutGameStateChange(7, newRain));
/*      */       
/*      */       }
/*      */     }
/* 1399 */     else if (this.pluginRainPositionPrevious != this.pluginRainPosition) {
/* 1400 */       this.playerConnection.sendPacket(new PacketPlayOutGameStateChange(7, this.pluginRainPosition));
/*      */     } 
/*      */ 
/*      */     
/* 1404 */     if (oldThunder != newThunder) {
/* 1405 */       if (this.weather == WeatherType.DOWNFALL || this.weather == null) {
/* 1406 */         this.playerConnection.sendPacket(new PacketPlayOutGameStateChange(8, newThunder));
/*      */       } else {
/* 1408 */         this.playerConnection.sendPacket(new PacketPlayOutGameStateChange(8, 0.0F));
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public void tickWeather() {
/* 1414 */     if (this.weather == null)
/*      */       return; 
/* 1416 */     this.pluginRainPositionPrevious = this.pluginRainPosition;
/* 1417 */     if (this.weather == WeatherType.DOWNFALL) {
/* 1418 */       this.pluginRainPosition = (float)(this.pluginRainPosition + 0.01D);
/*      */     } else {
/* 1420 */       this.pluginRainPosition = (float)(this.pluginRainPosition - 0.01D);
/*      */     } 
/*      */     
/* 1423 */     this.pluginRainPosition = MathHelper.a(this.pluginRainPosition, 0.0F, 1.0F);
/*      */   }
/*      */   
/*      */   public void resetPlayerWeather() {
/* 1427 */     this.weather = null;
/* 1428 */     setPlayerWeather(this.world.getWorldData().hasStorm() ? WeatherType.DOWNFALL : WeatherType.CLEAR, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1433 */     return String.valueOf(super.toString()) + "(" + getName() + " at " + this.locX + "," + this.locY + "," + this.locZ + ")";
/*      */   }
/*      */ 
/*      */   
/*      */   public void forceSetPositionRotation(double x, double y, double z, float yaw, float pitch) {
/* 1438 */     setPositionRotation(x, y, z, yaw, pitch);
/* 1439 */     this.playerConnection.syncPosition();
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean isFrozen() {
/* 1444 */     return !(!super.isFrozen() && getBukkitEntity().isOnline());
/*      */   }
/*      */   
/*      */   public void reset() {
/* 1448 */     float exp = 0.0F;
/* 1449 */     boolean keepInventory = this.world.getGameRules().getBoolean("keepInventory");
/*      */     
/* 1451 */     if (this.keepLevel || keepInventory) {
/* 1452 */       exp = this.exp;
/* 1453 */       this.newTotalExp = this.expTotal;
/* 1454 */       this.newLevel = this.expLevel;
/*      */     } 
/*      */     
/* 1457 */     setHealth(getMaxHealth());
/* 1458 */     this.fireTicks = 0;
/* 1459 */     this.fallDistance = 0.0F;
/* 1460 */     this.foodData = new FoodMetaData(this);
/* 1461 */     this.expLevel = this.newLevel;
/* 1462 */     this.expTotal = this.newTotalExp;
/* 1463 */     this.exp = 0.0F;
/* 1464 */     this.deathTicks = 0;
/* 1465 */     f(0.0F);
/* 1466 */     removeAllEffects();
/* 1467 */     this.updateEffects = true;
/* 1468 */     this.activeContainer = this.defaultContainer;
/* 1469 */     this.killer = null;
/* 1470 */     this.lastDamager = null;
/* 1471 */     this.combatTracker = new CombatTracker(this);
/* 1472 */     this.lastSentExp = -1;
/* 1473 */     if (this.keepLevel || keepInventory) {
/* 1474 */       this.exp = exp;
/*      */     } else {
/* 1476 */       giveExp(this.newExp);
/*      */     } 
/* 1478 */     this.keepLevel = false;
/*      */   }
/*      */ 
/*      */   
/*      */   public CraftPlayer getBukkitEntity() {
/* 1483 */     return (CraftPlayer)super.getBukkitEntity();
/*      */   }
/*      */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */