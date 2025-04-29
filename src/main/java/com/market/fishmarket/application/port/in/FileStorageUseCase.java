package com.market.fishmarket.application.port.in;

import com.market.fishmarket.application.port.out.FileStoragePort;

public interface FileStorageUseCase {
    String saveFile(FileStoragePort file);
}
