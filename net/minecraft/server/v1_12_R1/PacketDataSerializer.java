/*      */ package net.minecraft.server.v1_12_R1;
/*      */ 
/*      */ import io.netty.buffer.ByteBuf;
/*      */ import io.netty.buffer.ByteBufAllocator;
/*      */ import io.netty.buffer.ByteBufInputStream;
/*      */ import io.netty.buffer.ByteBufOutputStream;
/*      */ import io.netty.handler.codec.DecoderException;
/*      */ import io.netty.handler.codec.EncoderException;
/*      */ import io.netty.util.ByteProcessor;
/*      */ import io.netty.util.ReferenceCounted;
/*      */ import java.io.DataInput;
/*      */ import java.io.DataOutput;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.ByteOrder;
/*      */ import java.nio.channels.FileChannel;
/*      */ import java.nio.channels.GatheringByteChannel;
/*      */ import java.nio.channels.ScatteringByteChannel;
/*      */ import java.nio.charset.Charset;
/*      */ import java.nio.charset.StandardCharsets;
/*      */ import java.util.Date;
/*      */ import java.util.UUID;
/*      */ import javax.annotation.Nullable;
/*      */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
/*      */ 
/*      */ public class PacketDataSerializer
/*      */   extends ByteBuf {
/*      */   private final ByteBuf a;
/*      */   
/*      */   public PacketDataSerializer(ByteBuf bytebuf) {
/*   33 */     this.a = bytebuf;
/*      */   }
/*      */   
/*      */   public static int a(int i) {
/*   37 */     for (int j = 1; j < 5; j++) {
/*   38 */       if ((i & -1 << j * 7) == 0) {
/*   39 */         return j;
/*      */       }
/*      */     } 
/*      */     
/*   43 */     return 5;
/*      */   }
/*      */   
/*      */   public PacketDataSerializer a(byte[] abyte) {
/*   47 */     d(abyte.length);
/*   48 */     writeBytes(abyte);
/*   49 */     return this;
/*      */   }
/*      */   
/*      */   public byte[] a() {
/*   53 */     return b(readableBytes());
/*      */   }
/*      */   
/*      */   public byte[] b(int i) {
/*   57 */     int j = g();
/*      */     
/*   59 */     if (j > i) {
/*   60 */       throw new DecoderException("ByteArray with size " + j + " is bigger than allowed " + i);
/*      */     }
/*   62 */     byte[] abyte = new byte[j];
/*      */     
/*   64 */     readBytes(abyte);
/*   65 */     return abyte;
/*      */   }
/*      */ 
/*      */   
/*      */   public PacketDataSerializer a(int[] aint) {
/*   70 */     d(aint.length);
/*   71 */     int[] aint1 = aint;
/*   72 */     int i = aint.length;
/*      */     
/*   74 */     for (int j = 0; j < i; j++) {
/*   75 */       int k = aint1[j];
/*      */       
/*   77 */       d(k);
/*      */     } 
/*      */     
/*   80 */     return this;
/*      */   }
/*      */   
/*      */   public int[] b() {
/*   84 */     return c(readableBytes());
/*      */   }
/*      */   
/*      */   public int[] c(int i) {
/*   88 */     int j = g();
/*      */     
/*   90 */     if (j > i) {
/*   91 */       throw new DecoderException("VarIntArray with size " + j + " is bigger than allowed " + i);
/*      */     }
/*   93 */     int[] aint = new int[j];
/*      */     
/*   95 */     for (int k = 0; k < aint.length; k++) {
/*   96 */       aint[k] = g();
/*      */     }
/*      */     
/*   99 */     return aint;
/*      */   }
/*      */ 
/*      */   
/*      */   public PacketDataSerializer a(long[] along) {
/*  104 */     d(along.length);
/*  105 */     long[] along1 = along;
/*  106 */     int i = along.length;
/*      */     
/*  108 */     for (int j = 0; j < i; j++) {
/*  109 */       long k = along1[j];
/*      */       
/*  111 */       writeLong(k);
/*      */     } 
/*      */     
/*  114 */     return this;
/*      */   }
/*      */   
/*      */   public BlockPosition e() {
/*  118 */     return BlockPosition.fromLong(readLong());
/*      */   }
/*      */   
/*      */   public PacketDataSerializer a(BlockPosition blockposition) {
/*  122 */     writeLong(blockposition.asLong());
/*  123 */     return this;
/*      */   }
/*      */   
/*      */   public IChatBaseComponent f() {
/*  127 */     return IChatBaseComponent.ChatSerializer.a(e(32767));
/*      */   }
/*      */   
/*      */   public PacketDataSerializer a(IChatBaseComponent ichatbasecomponent) {
/*  131 */     return a(IChatBaseComponent.ChatSerializer.a(ichatbasecomponent));
/*      */   }
/*      */   
/*      */   public <T extends Enum<T>> T a(Class<T> oclass) {
/*  135 */     return (T)((Enum[])oclass.getEnumConstants())[g()];
/*      */   }
/*      */   
/*      */   public PacketDataSerializer a(Enum<?> oenum) {
/*  139 */     return d(oenum.ordinal());
/*      */   }
/*      */   public int g() {
/*      */     byte b0;
/*  143 */     int i = 0;
/*  144 */     int j = 0;
/*      */ 
/*      */ 
/*      */     
/*      */     do {
/*  149 */       b0 = readByte();
/*  150 */       i |= (b0 & Byte.MAX_VALUE) << j++ * 7;
/*  151 */       if (j > 5) {
/*  152 */         throw new RuntimeException("VarInt too big");
/*      */       }
/*  154 */     } while ((b0 & 0x80) == 128);
/*      */     
/*  156 */     return i;
/*      */   }
/*      */   public long h() {
/*      */     byte b0;
/*  160 */     long i = 0L;
/*  161 */     int j = 0;
/*      */ 
/*      */ 
/*      */     
/*      */     do {
/*  166 */       b0 = readByte();
/*  167 */       i |= (b0 & Byte.MAX_VALUE) << j++ * 7;
/*  168 */       if (j > 10) {
/*  169 */         throw new RuntimeException("VarLong too big");
/*      */       }
/*  171 */     } while ((b0 & 0x80) == 128);
/*      */     
/*  173 */     return i;
/*      */   }
/*      */   
/*      */   public PacketDataSerializer a(UUID uuid) {
/*  177 */     writeLong(uuid.getMostSignificantBits());
/*  178 */     writeLong(uuid.getLeastSignificantBits());
/*  179 */     return this;
/*      */   }
/*      */   
/*      */   public UUID i() {
/*  183 */     return new UUID(readLong(), readLong());
/*      */   }
/*      */   
/*      */   public PacketDataSerializer d(int i) {
/*  187 */     while ((i & 0xFFFFFF80) != 0) {
/*  188 */       writeByte(i & 0x7F | 0x80);
/*  189 */       i >>>= 7;
/*      */     } 
/*      */     
/*  192 */     writeByte(i);
/*  193 */     return this;
/*      */   }
/*      */   
/*      */   public PacketDataSerializer b(long i) {
/*  197 */     while ((i & 0xFFFFFFFFFFFFFF80L) != 0L) {
/*  198 */       writeByte((int)(i & 0x7FL) | 0x80);
/*  199 */       i >>>= 7L;
/*      */     } 
/*      */     
/*  202 */     writeByte((int)i);
/*  203 */     return this;
/*      */   }
/*      */   
/*      */   public PacketDataSerializer a(@Nullable NBTTagCompound nbttagcompound) {
/*  207 */     if (nbttagcompound == null) {
/*  208 */       writeByte(0);
/*      */     } else {
/*      */       try {
/*  211 */         NBTCompressedStreamTools.a(nbttagcompound, (DataOutput)new ByteBufOutputStream(this));
/*  212 */       } catch (Exception ioexception) {
/*  213 */         throw new EncoderException(ioexception);
/*      */       } 
/*      */     } 
/*      */     
/*  217 */     return this;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public NBTTagCompound j() {
/*  222 */     int i = readerIndex();
/*  223 */     byte b0 = readByte();
/*      */     
/*  225 */     if (b0 == 0) {
/*  226 */       return null;
/*      */     }
/*  228 */     readerIndex(i);
/*      */     
/*      */     try {
/*  231 */       return NBTCompressedStreamTools.a((DataInput)new ByteBufInputStream(this), new NBTReadLimiter(2097152L));
/*  232 */     } catch (IOException ioexception) {
/*  233 */       throw new EncoderException(ioexception);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public PacketDataSerializer a(ItemStack itemstack) {
/*  239 */     if (itemstack.isEmpty() || itemstack.getItem() == null) {
/*  240 */       writeShort(-1);
/*      */     } else {
/*  242 */       writeShort(Item.getId(itemstack.getItem()));
/*  243 */       writeByte(itemstack.getCount());
/*  244 */       writeShort(itemstack.getData());
/*  245 */       NBTTagCompound nbttagcompound = null;
/*      */       
/*  247 */       if (itemstack.getItem().usesDurability() || itemstack.getItem().p()) {
/*      */         
/*  249 */         itemstack = itemstack.cloneItemStack();
/*  250 */         CraftItemStack.setItemMeta(itemstack, CraftItemStack.getItemMeta(itemstack));
/*      */         
/*  252 */         nbttagcompound = itemstack.getTag();
/*      */       } 
/*      */       
/*  255 */       a(nbttagcompound);
/*      */     } 
/*      */     
/*  258 */     return this;
/*      */   }
/*      */   
/*      */   public ItemStack k() {
/*  262 */     short short0 = readShort();
/*      */     
/*  264 */     if (short0 < 0) {
/*  265 */       return ItemStack.a;
/*      */     }
/*  267 */     byte b0 = readByte();
/*  268 */     short short1 = readShort();
/*  269 */     ItemStack itemstack = new ItemStack(Item.getById(short0), b0, short1);
/*      */     
/*  271 */     itemstack.setTag(j());
/*      */     
/*  273 */     if (itemstack.getTag() != null) {
/*  274 */       CraftItemStack.setItemMeta(itemstack, CraftItemStack.getItemMeta(itemstack));
/*      */     }
/*      */     
/*  277 */     return itemstack;
/*      */   }
/*      */ 
/*      */   
/*      */   public String e(int i) {
/*  282 */     int j = g();
/*      */     
/*  284 */     if (j > i * 4)
/*  285 */       throw new DecoderException("The received encoded string buffer length is longer than maximum allowed (" + j + " > " + (i * 4) + ")"); 
/*  286 */     if (j < 0) {
/*  287 */       throw new DecoderException("The received encoded string buffer length is less than zero! Weird string!");
/*      */     }
/*  289 */     String s = toString(readerIndex(), j, StandardCharsets.UTF_8);
/*      */     
/*  291 */     readerIndex(readerIndex() + j);
/*  292 */     if (s.length() > i) {
/*  293 */       throw new DecoderException("The received string length is longer than maximum allowed (" + j + " > " + i + ")");
/*      */     }
/*  295 */     return s;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public PacketDataSerializer a(String s) {
/*  301 */     byte[] abyte = s.getBytes(StandardCharsets.UTF_8);
/*      */     
/*  303 */     if (abyte.length > 32767) {
/*  304 */       throw new EncoderException("String too big (was " + abyte.length + " bytes encoded, max " + '翿' + ")");
/*      */     }
/*  306 */     d(abyte.length);
/*  307 */     writeBytes(abyte);
/*  308 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public MinecraftKey l() {
/*  313 */     return new MinecraftKey(e(32767));
/*      */   }
/*      */   
/*      */   public PacketDataSerializer a(MinecraftKey minecraftkey) {
/*  317 */     a(minecraftkey.toString());
/*  318 */     return this;
/*      */   }
/*      */   
/*      */   public Date m() {
/*  322 */     return new Date(readLong());
/*      */   }
/*      */   
/*      */   public PacketDataSerializer a(Date date) {
/*  326 */     writeLong(date.getTime());
/*  327 */     return this;
/*      */   }
/*      */   
/*      */   public int capacity() {
/*  331 */     return this.a.capacity();
/*      */   }
/*      */   
/*      */   public ByteBuf capacity(int i) {
/*  335 */     return this.a.capacity(i);
/*      */   }
/*      */   
/*      */   public int maxCapacity() {
/*  339 */     return this.a.maxCapacity();
/*      */   }
/*      */   
/*      */   public ByteBufAllocator alloc() {
/*  343 */     return this.a.alloc();
/*      */   }
/*      */   
/*      */   public ByteOrder order() {
/*  347 */     return this.a.order();
/*      */   }
/*      */   
/*      */   public ByteBuf order(ByteOrder byteorder) {
/*  351 */     return this.a.order(byteorder);
/*      */   }
/*      */   
/*      */   public ByteBuf unwrap() {
/*  355 */     return this.a.unwrap();
/*      */   }
/*      */   
/*      */   public boolean isDirect() {
/*  359 */     return this.a.isDirect();
/*      */   }
/*      */   
/*      */   public boolean isReadOnly() {
/*  363 */     return this.a.isReadOnly();
/*      */   }
/*      */   
/*      */   public ByteBuf asReadOnly() {
/*  367 */     return this.a.asReadOnly();
/*      */   }
/*      */   
/*      */   public int readerIndex() {
/*  371 */     return this.a.readerIndex();
/*      */   }
/*      */   
/*      */   public ByteBuf readerIndex(int i) {
/*  375 */     return this.a.readerIndex(i);
/*      */   }
/*      */   
/*      */   public int writerIndex() {
/*  379 */     return this.a.writerIndex();
/*      */   }
/*      */   
/*      */   public ByteBuf writerIndex(int i) {
/*  383 */     return this.a.writerIndex(i);
/*      */   }
/*      */   
/*      */   public ByteBuf setIndex(int i, int j) {
/*  387 */     return this.a.setIndex(i, j);
/*      */   }
/*      */   
/*      */   public int readableBytes() {
/*  391 */     return this.a.readableBytes();
/*      */   }
/*      */   
/*      */   public int writableBytes() {
/*  395 */     return this.a.writableBytes();
/*      */   }
/*      */   
/*      */   public int maxWritableBytes() {
/*  399 */     return this.a.maxWritableBytes();
/*      */   }
/*      */   
/*      */   public boolean isReadable() {
/*  403 */     return this.a.isReadable();
/*      */   }
/*      */   
/*      */   public boolean isReadable(int i) {
/*  407 */     return this.a.isReadable(i);
/*      */   }
/*      */   
/*      */   public boolean isWritable() {
/*  411 */     return this.a.isWritable();
/*      */   }
/*      */   
/*      */   public boolean isWritable(int i) {
/*  415 */     return this.a.isWritable(i);
/*      */   }
/*      */   
/*      */   public ByteBuf clear() {
/*  419 */     return this.a.clear();
/*      */   }
/*      */   
/*      */   public ByteBuf markReaderIndex() {
/*  423 */     return this.a.markReaderIndex();
/*      */   }
/*      */   
/*      */   public ByteBuf resetReaderIndex() {
/*  427 */     return this.a.resetReaderIndex();
/*      */   }
/*      */   
/*      */   public ByteBuf markWriterIndex() {
/*  431 */     return this.a.markWriterIndex();
/*      */   }
/*      */   
/*      */   public ByteBuf resetWriterIndex() {
/*  435 */     return this.a.resetWriterIndex();
/*      */   }
/*      */   
/*      */   public ByteBuf discardReadBytes() {
/*  439 */     return this.a.discardReadBytes();
/*      */   }
/*      */   
/*      */   public ByteBuf discardSomeReadBytes() {
/*  443 */     return this.a.discardSomeReadBytes();
/*      */   }
/*      */   
/*      */   public ByteBuf ensureWritable(int i) {
/*  447 */     return this.a.ensureWritable(i);
/*      */   }
/*      */   
/*      */   public int ensureWritable(int i, boolean flag) {
/*  451 */     return this.a.ensureWritable(i, flag);
/*      */   }
/*      */   
/*      */   public boolean getBoolean(int i) {
/*  455 */     return this.a.getBoolean(i);
/*      */   }
/*      */   
/*      */   public byte getByte(int i) {
/*  459 */     return this.a.getByte(i);
/*      */   }
/*      */   
/*      */   public short getUnsignedByte(int i) {
/*  463 */     return this.a.getUnsignedByte(i);
/*      */   }
/*      */   
/*      */   public short getShort(int i) {
/*  467 */     return this.a.getShort(i);
/*      */   }
/*      */   
/*      */   public short getShortLE(int i) {
/*  471 */     return this.a.getShortLE(i);
/*      */   }
/*      */   
/*      */   public int getUnsignedShort(int i) {
/*  475 */     return this.a.getUnsignedShort(i);
/*      */   }
/*      */   
/*      */   public int getUnsignedShortLE(int i) {
/*  479 */     return this.a.getUnsignedShortLE(i);
/*      */   }
/*      */   
/*      */   public int getMedium(int i) {
/*  483 */     return this.a.getMedium(i);
/*      */   }
/*      */   
/*      */   public int getMediumLE(int i) {
/*  487 */     return this.a.getMediumLE(i);
/*      */   }
/*      */   
/*      */   public int getUnsignedMedium(int i) {
/*  491 */     return this.a.getUnsignedMedium(i);
/*      */   }
/*      */   
/*      */   public int getUnsignedMediumLE(int i) {
/*  495 */     return this.a.getUnsignedMediumLE(i);
/*      */   }
/*      */   
/*      */   public int getInt(int i) {
/*  499 */     return this.a.getInt(i);
/*      */   }
/*      */   
/*      */   public int getIntLE(int i) {
/*  503 */     return this.a.getIntLE(i);
/*      */   }
/*      */   
/*      */   public long getUnsignedInt(int i) {
/*  507 */     return this.a.getUnsignedInt(i);
/*      */   }
/*      */   
/*      */   public long getUnsignedIntLE(int i) {
/*  511 */     return this.a.getUnsignedIntLE(i);
/*      */   }
/*      */   
/*      */   public long getLong(int i) {
/*  515 */     return this.a.getLong(i);
/*      */   }
/*      */   
/*      */   public long getLongLE(int i) {
/*  519 */     return this.a.getLongLE(i);
/*      */   }
/*      */   
/*      */   public char getChar(int i) {
/*  523 */     return this.a.getChar(i);
/*      */   }
/*      */   
/*      */   public float getFloat(int i) {
/*  527 */     return this.a.getFloat(i);
/*      */   }
/*      */   
/*      */   public double getDouble(int i) {
/*  531 */     return this.a.getDouble(i);
/*      */   }
/*      */   
/*      */   public ByteBuf getBytes(int i, ByteBuf bytebuf) {
/*  535 */     return this.a.getBytes(i, bytebuf);
/*      */   }
/*      */   
/*      */   public ByteBuf getBytes(int i, ByteBuf bytebuf, int j) {
/*  539 */     return this.a.getBytes(i, bytebuf, j);
/*      */   }
/*      */   
/*      */   public ByteBuf getBytes(int i, ByteBuf bytebuf, int j, int k) {
/*  543 */     return this.a.getBytes(i, bytebuf, j, k);
/*      */   }
/*      */   
/*      */   public ByteBuf getBytes(int i, byte[] abyte) {
/*  547 */     return this.a.getBytes(i, abyte);
/*      */   }
/*      */   
/*      */   public ByteBuf getBytes(int i, byte[] abyte, int j, int k) {
/*  551 */     return this.a.getBytes(i, abyte, j, k);
/*      */   }
/*      */   
/*      */   public ByteBuf getBytes(int i, ByteBuffer bytebuffer) {
/*  555 */     return this.a.getBytes(i, bytebuffer);
/*      */   }
/*      */   
/*      */   public ByteBuf getBytes(int i, OutputStream outputstream, int j) throws IOException {
/*  559 */     return this.a.getBytes(i, outputstream, j);
/*      */   }
/*      */   
/*      */   public int getBytes(int i, GatheringByteChannel gatheringbytechannel, int j) throws IOException {
/*  563 */     return this.a.getBytes(i, gatheringbytechannel, j);
/*      */   }
/*      */   
/*      */   public int getBytes(int i, FileChannel filechannel, long j, int k) throws IOException {
/*  567 */     return this.a.getBytes(i, filechannel, j, k);
/*      */   }
/*      */   
/*      */   public CharSequence getCharSequence(int i, int j, Charset charset) {
/*  571 */     return this.a.getCharSequence(i, j, charset);
/*      */   }
/*      */   
/*      */   public ByteBuf setBoolean(int i, boolean flag) {
/*  575 */     return this.a.setBoolean(i, flag);
/*      */   }
/*      */   
/*      */   public ByteBuf setByte(int i, int j) {
/*  579 */     return this.a.setByte(i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf setShort(int i, int j) {
/*  583 */     return this.a.setShort(i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf setShortLE(int i, int j) {
/*  587 */     return this.a.setShortLE(i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf setMedium(int i, int j) {
/*  591 */     return this.a.setMedium(i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf setMediumLE(int i, int j) {
/*  595 */     return this.a.setMediumLE(i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf setInt(int i, int j) {
/*  599 */     return this.a.setInt(i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf setIntLE(int i, int j) {
/*  603 */     return this.a.setIntLE(i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf setLong(int i, long j) {
/*  607 */     return this.a.setLong(i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf setLongLE(int i, long j) {
/*  611 */     return this.a.setLongLE(i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf setChar(int i, int j) {
/*  615 */     return this.a.setChar(i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf setFloat(int i, float f) {
/*  619 */     return this.a.setFloat(i, f);
/*      */   }
/*      */   
/*      */   public ByteBuf setDouble(int i, double d0) {
/*  623 */     return this.a.setDouble(i, d0);
/*      */   }
/*      */   
/*      */   public ByteBuf setBytes(int i, ByteBuf bytebuf) {
/*  627 */     return this.a.setBytes(i, bytebuf);
/*      */   }
/*      */   
/*      */   public ByteBuf setBytes(int i, ByteBuf bytebuf, int j) {
/*  631 */     return this.a.setBytes(i, bytebuf, j);
/*      */   }
/*      */   
/*      */   public ByteBuf setBytes(int i, ByteBuf bytebuf, int j, int k) {
/*  635 */     return this.a.setBytes(i, bytebuf, j, k);
/*      */   }
/*      */   
/*      */   public ByteBuf setBytes(int i, byte[] abyte) {
/*  639 */     return this.a.setBytes(i, abyte);
/*      */   }
/*      */   
/*      */   public ByteBuf setBytes(int i, byte[] abyte, int j, int k) {
/*  643 */     return this.a.setBytes(i, abyte, j, k);
/*      */   }
/*      */   
/*      */   public ByteBuf setBytes(int i, ByteBuffer bytebuffer) {
/*  647 */     return this.a.setBytes(i, bytebuffer);
/*      */   }
/*      */   
/*      */   public int setBytes(int i, InputStream inputstream, int j) throws IOException {
/*  651 */     return this.a.setBytes(i, inputstream, j);
/*      */   }
/*      */   
/*      */   public int setBytes(int i, ScatteringByteChannel scatteringbytechannel, int j) throws IOException {
/*  655 */     return this.a.setBytes(i, scatteringbytechannel, j);
/*      */   }
/*      */   
/*      */   public int setBytes(int i, FileChannel filechannel, long j, int k) throws IOException {
/*  659 */     return this.a.setBytes(i, filechannel, j, k);
/*      */   }
/*      */   
/*      */   public ByteBuf setZero(int i, int j) {
/*  663 */     return this.a.setZero(i, j);
/*      */   }
/*      */   
/*      */   public int setCharSequence(int i, CharSequence charsequence, Charset charset) {
/*  667 */     return this.a.setCharSequence(i, charsequence, charset);
/*      */   }
/*      */   
/*      */   public boolean readBoolean() {
/*  671 */     return this.a.readBoolean();
/*      */   }
/*      */   
/*      */   public byte readByte() {
/*  675 */     return this.a.readByte();
/*      */   }
/*      */   
/*      */   public short readUnsignedByte() {
/*  679 */     return this.a.readUnsignedByte();
/*      */   }
/*      */   
/*      */   public short readShort() {
/*  683 */     return this.a.readShort();
/*      */   }
/*      */   
/*      */   public short readShortLE() {
/*  687 */     return this.a.readShortLE();
/*      */   }
/*      */   
/*      */   public int readUnsignedShort() {
/*  691 */     return this.a.readUnsignedShort();
/*      */   }
/*      */   
/*      */   public int readUnsignedShortLE() {
/*  695 */     return this.a.readUnsignedShortLE();
/*      */   }
/*      */   
/*      */   public int readMedium() {
/*  699 */     return this.a.readMedium();
/*      */   }
/*      */   
/*      */   public int readMediumLE() {
/*  703 */     return this.a.readMediumLE();
/*      */   }
/*      */   
/*      */   public int readUnsignedMedium() {
/*  707 */     return this.a.readUnsignedMedium();
/*      */   }
/*      */   
/*      */   public int readUnsignedMediumLE() {
/*  711 */     return this.a.readUnsignedMediumLE();
/*      */   }
/*      */   
/*      */   public int readInt() {
/*  715 */     return this.a.readInt();
/*      */   }
/*      */   
/*      */   public int readIntLE() {
/*  719 */     return this.a.readIntLE();
/*      */   }
/*      */   
/*      */   public long readUnsignedInt() {
/*  723 */     return this.a.readUnsignedInt();
/*      */   }
/*      */   
/*      */   public long readUnsignedIntLE() {
/*  727 */     return this.a.readUnsignedIntLE();
/*      */   }
/*      */   
/*      */   public long readLong() {
/*  731 */     return this.a.readLong();
/*      */   }
/*      */   
/*      */   public long readLongLE() {
/*  735 */     return this.a.readLongLE();
/*      */   }
/*      */   
/*      */   public char readChar() {
/*  739 */     return this.a.readChar();
/*      */   }
/*      */   
/*      */   public float readFloat() {
/*  743 */     return this.a.readFloat();
/*      */   }
/*      */   
/*      */   public double readDouble() {
/*  747 */     return this.a.readDouble();
/*      */   }
/*      */   
/*      */   public ByteBuf readBytes(int i) {
/*  751 */     return this.a.readBytes(i);
/*      */   }
/*      */   
/*      */   public ByteBuf readSlice(int i) {
/*  755 */     return this.a.readSlice(i);
/*      */   }
/*      */   
/*      */   public ByteBuf readRetainedSlice(int i) {
/*  759 */     return this.a.readRetainedSlice(i);
/*      */   }
/*      */   
/*      */   public ByteBuf readBytes(ByteBuf bytebuf) {
/*  763 */     return this.a.readBytes(bytebuf);
/*      */   }
/*      */   
/*      */   public ByteBuf readBytes(ByteBuf bytebuf, int i) {
/*  767 */     return this.a.readBytes(bytebuf, i);
/*      */   }
/*      */   
/*      */   public ByteBuf readBytes(ByteBuf bytebuf, int i, int j) {
/*  771 */     return this.a.readBytes(bytebuf, i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf readBytes(byte[] abyte) {
/*  775 */     return this.a.readBytes(abyte);
/*      */   }
/*      */   
/*      */   public ByteBuf readBytes(byte[] abyte, int i, int j) {
/*  779 */     return this.a.readBytes(abyte, i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf readBytes(ByteBuffer bytebuffer) {
/*  783 */     return this.a.readBytes(bytebuffer);
/*      */   }
/*      */   
/*      */   public ByteBuf readBytes(OutputStream outputstream, int i) throws IOException {
/*  787 */     return this.a.readBytes(outputstream, i);
/*      */   }
/*      */   
/*      */   public int readBytes(GatheringByteChannel gatheringbytechannel, int i) throws IOException {
/*  791 */     return this.a.readBytes(gatheringbytechannel, i);
/*      */   }
/*      */   
/*      */   public CharSequence readCharSequence(int i, Charset charset) {
/*  795 */     return this.a.readCharSequence(i, charset);
/*      */   }
/*      */   
/*      */   public int readBytes(FileChannel filechannel, long i, int j) throws IOException {
/*  799 */     return this.a.readBytes(filechannel, i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf skipBytes(int i) {
/*  803 */     return this.a.skipBytes(i);
/*      */   }
/*      */   
/*      */   public ByteBuf writeBoolean(boolean flag) {
/*  807 */     return this.a.writeBoolean(flag);
/*      */   }
/*      */   
/*      */   public ByteBuf writeByte(int i) {
/*  811 */     return this.a.writeByte(i);
/*      */   }
/*      */   
/*      */   public ByteBuf writeShort(int i) {
/*  815 */     return this.a.writeShort(i);
/*      */   }
/*      */   
/*      */   public ByteBuf writeShortLE(int i) {
/*  819 */     return this.a.writeShortLE(i);
/*      */   }
/*      */   
/*      */   public ByteBuf writeMedium(int i) {
/*  823 */     return this.a.writeMedium(i);
/*      */   }
/*      */   
/*      */   public ByteBuf writeMediumLE(int i) {
/*  827 */     return this.a.writeMediumLE(i);
/*      */   }
/*      */   
/*      */   public ByteBuf writeInt(int i) {
/*  831 */     return this.a.writeInt(i);
/*      */   }
/*      */   
/*      */   public ByteBuf writeIntLE(int i) {
/*  835 */     return this.a.writeIntLE(i);
/*      */   }
/*      */   
/*      */   public ByteBuf writeLong(long i) {
/*  839 */     return this.a.writeLong(i);
/*      */   }
/*      */   
/*      */   public ByteBuf writeLongLE(long i) {
/*  843 */     return this.a.writeLongLE(i);
/*      */   }
/*      */   
/*      */   public ByteBuf writeChar(int i) {
/*  847 */     return this.a.writeChar(i);
/*      */   }
/*      */   
/*      */   public ByteBuf writeFloat(float f) {
/*  851 */     return this.a.writeFloat(f);
/*      */   }
/*      */   
/*      */   public ByteBuf writeDouble(double d0) {
/*  855 */     return this.a.writeDouble(d0);
/*      */   }
/*      */   
/*      */   public ByteBuf writeBytes(ByteBuf bytebuf) {
/*  859 */     return this.a.writeBytes(bytebuf);
/*      */   }
/*      */   
/*      */   public ByteBuf writeBytes(ByteBuf bytebuf, int i) {
/*  863 */     return this.a.writeBytes(bytebuf, i);
/*      */   }
/*      */   
/*      */   public ByteBuf writeBytes(ByteBuf bytebuf, int i, int j) {
/*  867 */     return this.a.writeBytes(bytebuf, i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf writeBytes(byte[] abyte) {
/*  871 */     return this.a.writeBytes(abyte);
/*      */   }
/*      */   
/*      */   public ByteBuf writeBytes(byte[] abyte, int i, int j) {
/*  875 */     return this.a.writeBytes(abyte, i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf writeBytes(ByteBuffer bytebuffer) {
/*  879 */     return this.a.writeBytes(bytebuffer);
/*      */   }
/*      */   
/*      */   public int writeBytes(InputStream inputstream, int i) throws IOException {
/*  883 */     return this.a.writeBytes(inputstream, i);
/*      */   }
/*      */   
/*      */   public int writeBytes(ScatteringByteChannel scatteringbytechannel, int i) throws IOException {
/*  887 */     return this.a.writeBytes(scatteringbytechannel, i);
/*      */   }
/*      */   
/*      */   public int writeBytes(FileChannel filechannel, long i, int j) throws IOException {
/*  891 */     return this.a.writeBytes(filechannel, i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf writeZero(int i) {
/*  895 */     return this.a.writeZero(i);
/*      */   }
/*      */   
/*      */   public int writeCharSequence(CharSequence charsequence, Charset charset) {
/*  899 */     return this.a.writeCharSequence(charsequence, charset);
/*      */   }
/*      */   
/*      */   public int indexOf(int i, int j, byte b0) {
/*  903 */     return this.a.indexOf(i, j, b0);
/*      */   }
/*      */   
/*      */   public int bytesBefore(byte b0) {
/*  907 */     return this.a.bytesBefore(b0);
/*      */   }
/*      */   
/*      */   public int bytesBefore(int i, byte b0) {
/*  911 */     return this.a.bytesBefore(i, b0);
/*      */   }
/*      */   
/*      */   public int bytesBefore(int i, int j, byte b0) {
/*  915 */     return this.a.bytesBefore(i, j, b0);
/*      */   }
/*      */   
/*      */   public int forEachByte(ByteProcessor byteprocessor) {
/*  919 */     return this.a.forEachByte(byteprocessor);
/*      */   }
/*      */   
/*      */   public int forEachByte(int i, int j, ByteProcessor byteprocessor) {
/*  923 */     return this.a.forEachByte(i, j, byteprocessor);
/*      */   }
/*      */   
/*      */   public int forEachByteDesc(ByteProcessor byteprocessor) {
/*  927 */     return this.a.forEachByteDesc(byteprocessor);
/*      */   }
/*      */   
/*      */   public int forEachByteDesc(int i, int j, ByteProcessor byteprocessor) {
/*  931 */     return this.a.forEachByteDesc(i, j, byteprocessor);
/*      */   }
/*      */   
/*      */   public ByteBuf copy() {
/*  935 */     return this.a.copy();
/*      */   }
/*      */   
/*      */   public ByteBuf copy(int i, int j) {
/*  939 */     return this.a.copy(i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf slice() {
/*  943 */     return this.a.slice();
/*      */   }
/*      */   
/*      */   public ByteBuf retainedSlice() {
/*  947 */     return this.a.retainedSlice();
/*      */   }
/*      */   
/*      */   public ByteBuf slice(int i, int j) {
/*  951 */     return this.a.slice(i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf retainedSlice(int i, int j) {
/*  955 */     return this.a.retainedSlice(i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf duplicate() {
/*  959 */     return this.a.duplicate();
/*      */   }
/*      */   
/*      */   public ByteBuf retainedDuplicate() {
/*  963 */     return this.a.retainedDuplicate();
/*      */   }
/*      */   
/*      */   public int nioBufferCount() {
/*  967 */     return this.a.nioBufferCount();
/*      */   }
/*      */   
/*      */   public ByteBuffer nioBuffer() {
/*  971 */     return this.a.nioBuffer();
/*      */   }
/*      */   
/*      */   public ByteBuffer nioBuffer(int i, int j) {
/*  975 */     return this.a.nioBuffer(i, j);
/*      */   }
/*      */   
/*      */   public ByteBuffer internalNioBuffer(int i, int j) {
/*  979 */     return this.a.internalNioBuffer(i, j);
/*      */   }
/*      */   
/*      */   public ByteBuffer[] nioBuffers() {
/*  983 */     return this.a.nioBuffers();
/*      */   }
/*      */   
/*      */   public ByteBuffer[] nioBuffers(int i, int j) {
/*  987 */     return this.a.nioBuffers(i, j);
/*      */   }
/*      */   
/*      */   public boolean hasArray() {
/*  991 */     return this.a.hasArray();
/*      */   }
/*      */   
/*      */   public byte[] array() {
/*  995 */     return this.a.array();
/*      */   }
/*      */   
/*      */   public int arrayOffset() {
/*  999 */     return this.a.arrayOffset();
/*      */   }
/*      */   
/*      */   public boolean hasMemoryAddress() {
/* 1003 */     return this.a.hasMemoryAddress();
/*      */   }
/*      */   
/*      */   public long memoryAddress() {
/* 1007 */     return this.a.memoryAddress();
/*      */   }
/*      */   
/*      */   public String toString(Charset charset) {
/* 1011 */     return this.a.toString(charset);
/*      */   }
/*      */   
/*      */   public String toString(int i, int j, Charset charset) {
/* 1015 */     return this.a.toString(i, j, charset);
/*      */   }
/*      */   
/*      */   public int hashCode() {
/* 1019 */     return this.a.hashCode();
/*      */   }
/*      */   
/*      */   public boolean equals(Object object) {
/* 1023 */     return this.a.equals(object);
/*      */   }
/*      */   
/*      */   public int compareTo(ByteBuf bytebuf) {
/* 1027 */     return this.a.compareTo(bytebuf);
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1031 */     return this.a.toString();
/*      */   }
/*      */   
/*      */   public ByteBuf retain(int i) {
/* 1035 */     return this.a.retain(i);
/*      */   }
/*      */   
/*      */   public ByteBuf retain() {
/* 1039 */     return this.a.retain();
/*      */   }
/*      */   
/*      */   public ByteBuf touch() {
/* 1043 */     return this.a.touch();
/*      */   }
/*      */   
/*      */   public ByteBuf touch(Object object) {
/* 1047 */     return this.a.touch(object);
/*      */   }
/*      */   
/*      */   public int refCnt() {
/* 1051 */     return this.a.refCnt();
/*      */   }
/*      */   
/*      */   public boolean release() {
/* 1055 */     return this.a.release();
/*      */   }
/*      */   
/*      */   public boolean release(int i) {
/* 1059 */     return this.a.release(i);
/*      */   }
/*      */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketDataSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */