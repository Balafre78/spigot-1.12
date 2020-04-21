package net.minecraft.server.v1_12_R1;

import com.google.common.collect.ImmutableMap;
import java.util.Collection;

public interface IBlockData extends IBlockPhysics, IBlockProperties {
  Collection<IBlockState<?>> s();
  
  <T extends Comparable<T>> T get(IBlockState<T> paramIBlockState);
  
  <T extends Comparable<T>, V extends T> IBlockData set(IBlockState<T> paramIBlockState, V paramV);
  
  <T extends Comparable<T>> IBlockData a(IBlockState<T> paramIBlockState);
  
  ImmutableMap<IBlockState<?>, Comparable<?>> t();
  
  Block getBlock();
}


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\IBlockData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */