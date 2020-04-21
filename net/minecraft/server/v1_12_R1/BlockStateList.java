/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Function;
/*     */ import com.google.common.base.MoreObjects;
/*     */ import com.google.common.collect.HashBasedTable;
/*     */ import com.google.common.collect.ImmutableCollection;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.ImmutableSortedMap;
/*     */ import com.google.common.collect.ImmutableTable;
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Table;
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Pattern;
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
/*     */ public class BlockStateList
/*     */ {
/*  43 */   private static final Pattern a = Pattern.compile("^[a-z0-9_]+$");
/*  44 */   private static final Function<IBlockState<?>, String> b = new Function<IBlockState<?>, String>()
/*     */     {
/*     */       @Nullable
/*     */       public String a(@Nullable IBlockState<?> param1IBlockState) {
/*  48 */         return (param1IBlockState == null) ? "<NULL>" : param1IBlockState.a();
/*     */       }
/*     */     };
/*     */   
/*     */   private final Block c;
/*     */   private final ImmutableSortedMap<String, IBlockState<?>> d;
/*     */   private final ImmutableList<IBlockData> e;
/*     */   
/*     */   public BlockStateList(Block paramBlock, IBlockState<?>... paramVarArgs) {
/*  57 */     this.c = paramBlock;
/*     */     
/*  59 */     HashMap<String, IBlockState<?>> hashMap = Maps.newHashMap();
/*  60 */     for (IBlockState<?> iBlockState : paramVarArgs) {
/*  61 */       a(paramBlock, iBlockState);
/*     */       
/*  63 */       hashMap.put(iBlockState.a(), iBlockState);
/*     */     } 
/*  65 */     this.d = ImmutableSortedMap.copyOf(hashMap);
/*     */ 
/*     */     
/*  68 */     LinkedHashMap<Map<?, ?>, BlockData> linkedHashMap = Maps.newLinkedHashMap();
/*  69 */     ArrayList<BlockData> arrayList = Lists.newArrayList();
/*     */     
/*  71 */     Iterable<List<Comparable<?>>> iterable = IteratorUtils.a(e());
/*  72 */     for (List<?> list : iterable) {
/*  73 */       Map<?, ?> map = MapGeneratorUtils.b((Iterable<?>)this.d.values(), list);
/*  74 */       BlockData blockData = new BlockData(paramBlock, ImmutableMap.copyOf(map));
/*     */       
/*  76 */       linkedHashMap.put(map, blockData);
/*  77 */       arrayList.add(blockData);
/*     */     } 
/*     */     
/*  80 */     for (BlockData blockData : arrayList) {
/*  81 */       blockData.a((Map)linkedHashMap);
/*     */     }
/*     */     
/*  84 */     this.e = ImmutableList.copyOf(arrayList);
/*     */   }
/*     */   
/*     */   public static <T extends Comparable<T>> String a(Block paramBlock, IBlockState<T> paramIBlockState) {
/*  88 */     String str = paramIBlockState.a();
/*  89 */     if (!a.matcher(str).matches()) {
/*  90 */       throw new IllegalArgumentException("Block: " + paramBlock.getClass() + " has invalidly named property: " + str);
/*     */     }
/*     */     
/*  93 */     for (Comparable comparable : paramIBlockState.c()) {
/*  94 */       String str1 = paramIBlockState.a((T)comparable);
/*  95 */       if (!a.matcher(str1).matches()) {
/*  96 */         throw new IllegalArgumentException("Block: " + paramBlock.getClass() + " has property: " + str + " with invalidly named value: " + str1);
/*     */       }
/*     */     } 
/*  99 */     return str;
/*     */   }
/*     */   
/*     */   public ImmutableList<IBlockData> a() {
/* 103 */     return this.e;
/*     */   }
/*     */   
/*     */   private List<Iterable<Comparable<?>>> e() {
/* 107 */     ArrayList<Iterable<Comparable<?>>> arrayList = Lists.newArrayList();
/* 108 */     ImmutableCollection immutableCollection = this.d.values();
/* 109 */     for (UnmodifiableIterator<IBlockState> unmodifiableIterator = immutableCollection.iterator(); unmodifiableIterator.hasNext(); ) { IBlockState iBlockState = unmodifiableIterator.next();
/* 110 */       arrayList.add(iBlockState.c()); }
/*     */ 
/*     */     
/* 113 */     return arrayList;
/*     */   }
/*     */   
/*     */   public IBlockData getBlockData() {
/* 117 */     return (IBlockData)this.e.get(0);
/*     */   }
/*     */   
/*     */   public Block getBlock() {
/* 121 */     return this.c;
/*     */   }
/*     */   
/*     */   public Collection<IBlockState<?>> d() {
/* 125 */     return (Collection<IBlockState<?>>)this.d.values();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 130 */     return MoreObjects.toStringHelper(this)
/* 131 */       .add("block", Block.REGISTRY.b(this.c))
/* 132 */       .add("properties", Iterables.transform((Iterable)this.d.values(), b))
/* 133 */       .toString();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public IBlockState<?> a(String paramString) {
/* 138 */     return (IBlockState)this.d.get(paramString);
/*     */   }
/*     */   
/*     */   static class BlockData
/*     */     extends BlockDataAbstract {
/*     */     private final Block a;
/*     */     private final ImmutableMap<IBlockState<?>, Comparable<?>> b;
/*     */     private ImmutableTable<IBlockState<?>, Comparable<?>, IBlockData> c;
/*     */     
/*     */     private BlockData(Block param1Block, ImmutableMap<IBlockState<?>, Comparable<?>> param1ImmutableMap) {
/* 148 */       this.a = param1Block;
/* 149 */       this.b = param1ImmutableMap;
/*     */     }
/*     */ 
/*     */     
/*     */     public Collection<IBlockState<?>> s() {
/* 154 */       return Collections.unmodifiableCollection((Collection<? extends IBlockState<?>>)this.b.keySet());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public <T extends Comparable<T>> T get(IBlockState<T> param1IBlockState) {
/* 164 */       Comparable comparable = (Comparable)this.b.get(param1IBlockState);
/* 165 */       if (comparable == null) {
/* 166 */         throw new IllegalArgumentException("Cannot get property " + param1IBlockState + " as it does not exist in " + this.a.s());
/*     */       }
/*     */       
/* 169 */       return (T)param1IBlockState.b().cast(comparable);
/*     */     }
/*     */ 
/*     */     
/*     */     public <T extends Comparable<T>, V extends T> IBlockData set(IBlockState<T> param1IBlockState, V param1V) {
/* 174 */       Comparable comparable = (Comparable)this.b.get(param1IBlockState);
/* 175 */       if (comparable == null) {
/* 176 */         throw new IllegalArgumentException("Cannot set property " + param1IBlockState + " as it does not exist in " + this.a.s());
/*     */       }
/* 178 */       if (comparable == param1V) {
/* 179 */         return this;
/*     */       }
/*     */       
/* 182 */       IBlockData iBlockData = (IBlockData)this.c.get(param1IBlockState, param1V);
/* 183 */       if (iBlockData == null) {
/* 184 */         throw new IllegalArgumentException("Cannot set property " + param1IBlockState + " to " + param1V + " on block " + Block.REGISTRY.b(this.a) + ", it is not an allowed value");
/*     */       }
/*     */       
/* 187 */       return iBlockData;
/*     */     }
/*     */ 
/*     */     
/*     */     public ImmutableMap<IBlockState<?>, Comparable<?>> t() {
/* 192 */       return this.b;
/*     */     }
/*     */ 
/*     */     
/*     */     public Block getBlock() {
/* 197 */       return this.a;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean equals(Object param1Object) {
/* 203 */       return (this == param1Object);
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 208 */       return this.b.hashCode();
/*     */     }
/*     */     
/*     */     public void a(Map<Map<IBlockState<?>, Comparable<?>>, BlockData> param1Map) {
/* 212 */       if (this.c != null) {
/* 213 */         throw new IllegalStateException();
/*     */       }
/*     */       
/* 216 */       HashBasedTable hashBasedTable = HashBasedTable.create();
/* 217 */       for (UnmodifiableIterator<Map.Entry> unmodifiableIterator = this.b.entrySet().iterator(); unmodifiableIterator.hasNext(); ) { Map.Entry entry = unmodifiableIterator.next();
/* 218 */         IBlockState<?> iBlockState = (IBlockState)entry.getKey();
/* 219 */         for (Comparable<?> comparable : (Iterable<Comparable<?>>)iBlockState.c()) {
/* 220 */           if (comparable != entry.getValue()) {
/* 221 */             hashBasedTable.put(iBlockState, comparable, param1Map.get(b(iBlockState, comparable)));
/*     */           }
/*     */         }  }
/*     */ 
/*     */       
/* 226 */       this.c = ImmutableTable.copyOf((Table)hashBasedTable);
/*     */     }
/*     */     
/*     */     private Map<IBlockState<?>, Comparable<?>> b(IBlockState<?> param1IBlockState, Comparable<?> param1Comparable) {
/* 230 */       HashMap<IBlockState<?>, Comparable<?>> hashMap = Maps.newHashMap((Map)this.b);
/* 231 */       hashMap.put(param1IBlockState, param1Comparable);
/* 232 */       return hashMap;
/*     */     }
/*     */ 
/*     */     
/*     */     public Material getMaterial() {
/* 237 */       return this.a.q(this);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 242 */       return this.a.l(this);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a(Entity param1Entity) {
/* 247 */       return this.a.a(this, param1Entity);
/*     */     }
/*     */ 
/*     */     
/*     */     public int c() {
/* 252 */       return this.a.m(this);
/*     */     }
/*     */ 
/*     */     
/*     */     public int d() {
/* 257 */       return this.a.o(this);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean f() {
/* 267 */       return this.a.p(this);
/*     */     }
/*     */ 
/*     */     
/*     */     public MaterialMapColor a(IBlockAccess param1IBlockAccess, BlockPosition param1BlockPosition) {
/* 272 */       return this.a.c(this, param1IBlockAccess, param1BlockPosition);
/*     */     }
/*     */ 
/*     */     
/*     */     public IBlockData a(EnumBlockRotation param1EnumBlockRotation) {
/* 277 */       return this.a.a(this, param1EnumBlockRotation);
/*     */     }
/*     */ 
/*     */     
/*     */     public IBlockData a(EnumBlockMirror param1EnumBlockMirror) {
/* 282 */       return this.a.a(this, param1EnumBlockMirror);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean g() {
/* 287 */       return this.a.c(this);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public EnumRenderType i() {
/* 297 */       return this.a.a(this);
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
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean k() {
/* 312 */       return this.a.r(this);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean l() {
/* 317 */       return this.a.isOccluding(this);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean m() {
/* 322 */       return this.a.isPowerSource(this);
/*     */     }
/*     */ 
/*     */     
/*     */     public int a(IBlockAccess param1IBlockAccess, BlockPosition param1BlockPosition, EnumDirection param1EnumDirection) {
/* 327 */       return this.a.b(this, param1IBlockAccess, param1BlockPosition, param1EnumDirection);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean n() {
/* 332 */       return this.a.isComplexRedstone(this);
/*     */     }
/*     */ 
/*     */     
/*     */     public int a(World param1World, BlockPosition param1BlockPosition) {
/* 337 */       return this.a.c(this, param1World, param1BlockPosition);
/*     */     }
/*     */ 
/*     */     
/*     */     public float b(World param1World, BlockPosition param1BlockPosition) {
/* 342 */       return this.a.a(this, param1World, param1BlockPosition);
/*     */     }
/*     */ 
/*     */     
/*     */     public float a(EntityHuman param1EntityHuman, World param1World, BlockPosition param1BlockPosition) {
/* 347 */       return this.a.getDamage(this, param1EntityHuman, param1World, param1BlockPosition);
/*     */     }
/*     */ 
/*     */     
/*     */     public int b(IBlockAccess param1IBlockAccess, BlockPosition param1BlockPosition, EnumDirection param1EnumDirection) {
/* 352 */       return this.a.c(this, param1IBlockAccess, param1BlockPosition, param1EnumDirection);
/*     */     }
/*     */ 
/*     */     
/*     */     public EnumPistonReaction o() {
/* 357 */       return this.a.h(this);
/*     */     }
/*     */ 
/*     */     
/*     */     public IBlockData c(IBlockAccess param1IBlockAccess, BlockPosition param1BlockPosition) {
/* 362 */       return this.a.updateState(this, param1IBlockAccess, param1BlockPosition);
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
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean p() {
/* 377 */       return this.a.b(this);
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public AxisAlignedBB d(IBlockAccess param1IBlockAccess, BlockPosition param1BlockPosition) {
/* 383 */       return this.a.a(this, param1IBlockAccess, param1BlockPosition);
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(World param1World, BlockPosition param1BlockPosition, AxisAlignedBB param1AxisAlignedBB, List<AxisAlignedBB> param1List, @Nullable Entity param1Entity, boolean param1Boolean) {
/* 388 */       this.a.a(this, param1World, param1BlockPosition, param1AxisAlignedBB, param1List, param1Entity, param1Boolean);
/*     */     }
/*     */ 
/*     */     
/*     */     public AxisAlignedBB e(IBlockAccess param1IBlockAccess, BlockPosition param1BlockPosition) {
/* 393 */       return this.a.b(this, param1IBlockAccess, param1BlockPosition);
/*     */     }
/*     */ 
/*     */     
/*     */     public MovingObjectPosition a(World param1World, BlockPosition param1BlockPosition, Vec3D param1Vec3D1, Vec3D param1Vec3D2) {
/* 398 */       return this.a.a(this, param1World, param1BlockPosition, param1Vec3D1, param1Vec3D2);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean q() {
/* 403 */       return this.a.k(this);
/*     */     }
/*     */ 
/*     */     
/*     */     public Vec3D f(IBlockAccess param1IBlockAccess, BlockPosition param1BlockPosition) {
/* 408 */       return this.a.f(this, param1IBlockAccess, param1BlockPosition);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a(World param1World, BlockPosition param1BlockPosition, int param1Int1, int param1Int2) {
/* 413 */       return this.a.a(this, param1World, param1BlockPosition, param1Int1, param1Int2);
/*     */     }
/*     */ 
/*     */     
/*     */     public void doPhysics(World param1World, BlockPosition param1BlockPosition1, Block param1Block, BlockPosition param1BlockPosition2) {
/* 418 */       this.a.a(this, param1World, param1BlockPosition1, param1Block, param1BlockPosition2);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean r() {
/* 423 */       return this.a.t(this);
/*     */     }
/*     */ 
/*     */     
/*     */     public EnumBlockFaceShape d(IBlockAccess param1IBlockAccess, BlockPosition param1BlockPosition, EnumDirection param1EnumDirection) {
/* 428 */       return this.a.a(param1IBlockAccess, this, param1BlockPosition, param1EnumDirection);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockStateList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */