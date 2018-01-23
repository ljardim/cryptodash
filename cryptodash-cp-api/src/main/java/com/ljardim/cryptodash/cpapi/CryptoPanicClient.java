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
package com.ljardim.cryptodash.cpapi;

import com.ljardim.cryptodash.cpapi.domain.CryptoPanicFilter;
import com.ljardim.cryptodash.cpapi.domain.Posts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Client implementation of the CryptoPanic API.
 *
 * @author Luis Jardim
 */
@ConfigurationProperties(prefix = "CryptoPanic")
public class CryptoPanicClient implements CryptoPanic {

    private static final String CRYPTO_PANIC_BASE_URL = "https://cryptopanic.com/api/posts/";

    @Value("${authToken}")
    private String authToken;

    @Override
    public Posts retrievePosts(EnumSet<CryptoPanicFilter> filters, List<String> currencies) throws CryptoPanicException {
        if (authToken == null || authToken.trim().isEmpty()) {
            throw new CryptoPanicException("Authentication token is not configured in the config/application.properties file");
        }

        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(CRYPTO_PANIC_BASE_URL)
                .queryParam("auth_token", authToken);

        // Add filters if any was specified
        if (filters != null && !filters.isEmpty()) {
            String filter = filters.stream().map(CryptoPanicFilter::getFilterName).collect(Collectors.joining(","));
            uriBuilder.queryParam("filter", filter);
        }

        if (currencies != null && !currencies.isEmpty()) {
            String currencyFilter = currencies.stream().collect(Collectors.joining(","));
            uriBuilder.queryParam("currencies", currencyFilter);
        }

        try {
            return restTemplate.getForObject(uriBuilder.toUriString(), Posts.class);
        } catch (RestClientException e) {
            throw new CryptoPanicException("Exception caught while trying to call CryptoPanic API", e);
        }
    }
}
