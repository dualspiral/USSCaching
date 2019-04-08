package uk.co.drnaylor.sponge.usscachingsponge;

import net.minecraft.server.management.UserListEntry;

import java.util.Map;

public interface IMixinUserList<K, V extends UserListEntry<K>> {


    Map<String, V> getValuesPublic();
}
