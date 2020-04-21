/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MapGeneratorUtils
/*    */ {
/*    */   public static <K, V> Map<K, V> b(Iterable<K> paramIterable, Iterable<V> paramIterable1) {
/* 15 */     return a(paramIterable, paramIterable1, Maps.newLinkedHashMap());
/*    */   }
/*    */   
/*    */   public static <K, V> Map<K, V> a(Iterable<K> paramIterable, Iterable<V> paramIterable1, Map<K, V> paramMap) {
/*    */     // Byte code:
/*    */     //   0: aload_1
/*    */     //   1: invokeinterface iterator : ()Ljava/util/Iterator;
/*    */     //   6: astore_3
/*    */     //   7: aload_0
/*    */     //   8: invokeinterface iterator : ()Ljava/util/Iterator;
/*    */     //   13: astore #4
/*    */     //   15: aload #4
/*    */     //   17: invokeinterface hasNext : ()Z
/*    */     //   22: ifeq -> 52
/*    */     //   25: aload #4
/*    */     //   27: invokeinterface next : ()Ljava/lang/Object;
/*    */     //   32: astore #5
/*    */     //   34: aload_2
/*    */     //   35: aload #5
/*    */     //   37: aload_3
/*    */     //   38: invokeinterface next : ()Ljava/lang/Object;
/*    */     //   43: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
/*    */     //   48: pop
/*    */     //   49: goto -> 15
/*    */     //   52: aload_3
/*    */     //   53: invokeinterface hasNext : ()Z
/*    */     //   58: ifeq -> 69
/*    */     //   61: new java/util/NoSuchElementException
/*    */     //   64: dup
/*    */     //   65: invokespecial <init> : ()V
/*    */     //   68: athrow
/*    */     //   69: aload_2
/*    */     //   70: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #19	-> 0
/*    */     //   #20	-> 7
/*    */     //   #21	-> 34
/*    */     //   #22	-> 49
/*    */     //   #24	-> 52
/*    */     //   #25	-> 61
/*    */     //   #28	-> 69
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\MapGeneratorUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */