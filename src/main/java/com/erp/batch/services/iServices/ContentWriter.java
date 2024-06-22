package com.erp.batch.services.iServices;

import java.util.Map;

public interface ContentWriter<T> extends AutoCloseable{
    default void write(Map<Integer, T> data, boolean skipZeroIndex) { write("", data, skipZeroIndex); }
    void write(String sheetName, Map<Integer, T> data, boolean skipZeroIndex);
}
