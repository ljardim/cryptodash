package com.ljardim.cryptodash.service;/*
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

import com.ljardim.cryptodash.cmcapi.CoinMarketCap;
import com.ljardim.cryptodash.cmcapi.domain.Ticker;
import com.ljardim.cryptodash.domain.Coin;
import com.ljardim.cryptodash.influence.OverallInfluenceApplicator;
import com.ljardim.cryptodash.influence.PriceInfluenceApplicator;
import com.ljardim.cryptodash.influence.SocialInfluenceApplicator;
import com.ljardim.cryptodash.influence.VolumeInfluenceApplicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Service to retrieve ranked coins.
 *
 * @author Luis Jardim
 */
@Service
public class CoinRankService {

    private SocialInfluenceApplicator socialInfluenceApplicator;
    private VolumeInfluenceApplicator volumeInfluenceApplicator;
    private PriceInfluenceApplicator priceInfluenceApplicator;
    private OverallInfluenceApplicator overallInfluenceApplicator;

    private CoinMarketCap coinMarketCap;

    @Autowired
    public CoinRankService(SocialInfluenceApplicator socialInfluenceApplicator,
                           VolumeInfluenceApplicator volumeInfluenceApplicator,
                           PriceInfluenceApplicator priceInfluenceApplicator,
                           OverallInfluenceApplicator overallInfluenceApplicator,
                           CoinMarketCap coinMarketCap) {
        this.socialInfluenceApplicator = socialInfluenceApplicator;
        this.volumeInfluenceApplicator = volumeInfluenceApplicator;
        this.priceInfluenceApplicator = priceInfluenceApplicator;
        this.overallInfluenceApplicator = overallInfluenceApplicator;
        this.coinMarketCap = coinMarketCap;
    }

    public List<Coin> retrieveTop10() throws Exception {
        List<Ticker> tickers = coinMarketCap.retrieveTickerData();

        List<Coin> coins = tickers.stream().map(t -> new Coin(t.getSymbol(),
                t.getName(),
                t.getVolume_usd_24h(),
                t.getPrice_usd(),
                t.getPrice_btc(),
                t.getPercent_change_1h(),
                t.getPercent_change_24h(),
                t.getPercent_change_7d())).collect(toList());

        socialInfluenceApplicator.apply(coins);
        // TODO Volume needs some work
//        volumeInfluenceApplicator.apply(coins);
        priceInfluenceApplicator.apply(coins);
        overallInfluenceApplicator.apply(coins);

        return coins.stream()
                .filter(coin -> coin.getSiPoints() > 0)
                .sorted((o1, o2) -> Float.compare(o2.getOiPoints(), o1.getOiPoints()))
                .limit(10)
                .collect(toList());
    }
}
