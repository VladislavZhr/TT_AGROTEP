package com.market.fishmarket.application.port.out;

import java.io.InputStream;

public interface FileStoragePort {
    String getOriginalFilename();
    InputStream getInputStream();
}
