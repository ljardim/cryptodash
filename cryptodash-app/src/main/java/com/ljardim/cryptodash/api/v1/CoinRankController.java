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
package com.ljardim.cryptodash.api.v1;

import com.ljardim.cryptodash.domain.Coin;
import com.ljardim.cryptodash.service.CoinRankService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ljardim.cryptodash.api.v1.Api.API_ENDPOINT_BASE_URI;

/**
 * Coin Ranking rest controller
 *
 * @author Luis Jardim
 */
@RestController
@RequestMapping(API_ENDPOINT_BASE_URI + "/coin")
public class CoinRankController extends Api {

    private static final Logger LOG = LogManager.getLogger();

    private final CoinRankService coinRankService;

    @Autowired
    public CoinRankController(CoinRankService coinRankService) {
        this.coinRankService = coinRankService;
    }

    @RequestMapping(value = "top10", method = RequestMethod.GET)
    public ResponseEntity<List<Coin>> getTop10() {
        try {
            List<Coin> coins = coinRankService.retrieveTop10();
            return ResponseEntity.ok(coins);
        } catch (Exception e) {
            LOG.error("Failed to retrieve top 10 coins", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
