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
package com.ljardim.cryptodash.cmcapi;

import com.ljardim.cryptodash.cmcapi.domain.Ticker;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

/**
 * Client implementation of the CoinMarketCap API.
 *
 * @author Luis Jardim
 */
@ConfigurationProperties(prefix = "CryptoPanic")
public class CoinMarketCapClient implements CoinMarketCap {

    private static final String COIN_MARKET_CAP_BASE_URL = "https://api.coinmarketcap.com/v1/ticker/";

    @Override
    public List<Ticker> retrieveTickerData() throws CoinMarketCapException {
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(COIN_MARKET_CAP_BASE_URL);

        try {
            ResponseEntity<Ticker[]> responseEntity = restTemplate.getForEntity(uriBuilder.toUriString(), Ticker[].class);
            Ticker[] tickers = responseEntity.getBody();
            return Arrays.asList(tickers);
        } catch (RestClientException e) {
            throw new CoinMarketCapException("Exception caught while trying to call CoinMarketCap API", e);
        }
    }
}
