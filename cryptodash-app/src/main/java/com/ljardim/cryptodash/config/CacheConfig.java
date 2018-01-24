/*
 * MIT License
 *
 * Copyright (c) 2018 Luis Jardim
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.ljardim.cryptodash.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Ticker;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Caching configuration.
 *
 * @author Luis Jardim
 */
@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager(Ticker ticker) {
        CaffeineCache cpCache = buildCache("CryptoPanicCache", ticker, 1);
        CaffeineCache cmcCache = buildCache("CoinMarketCapCache", ticker, 1);
        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(Arrays.asList(cpCache, cmcCache));
        return manager;
    }

    private CaffeineCache buildCache(String name, Ticker ticker, int minutesToExpire) {
        return new CaffeineCache(name, Caffeine.newBuilder()
                .expireAfterWrite(minutesToExpire, TimeUnit.MINUTES)
                .maximumSize(100)
                .ticker(ticker)
                .build());
    }

    @Bean
    public Ticker ticker() {
        return Ticker.systemTicker();
    }
}
