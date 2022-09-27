package com.developer.DeveloperManager.dto;

import lombok.Data;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

@Data
public class PostRequest extends RequestBody {
    private String name;

    @Nullable
    @Override
    public MediaType contentType() {
        return null;
    }

    @Override
    public void writeTo(@NotNull BufferedSink bufferedSink) throws IOException {

    }
}
