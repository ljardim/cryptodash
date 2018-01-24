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
package com.ljardim.cryptodash.influence;

import com.ljardim.cryptodash.domain.Coin;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Applies the price influence calculation to a cryptocurrency.
 *
 * @author Luis Jardim
 */
@Component
public class PriceInfluenceApplicator implements InfluenceApplicator {

    @Override
    public void apply(List<Coin> coins) throws Exception {
        coins.forEach(coin -> {
            float percent_change_1h = coin.getPercent_change_1h();
            float percent_change_24h = coin.getPercent_change_24h();
            float percent_change_7d = coin.getPercent_change_7d();

            float rank1h = percent_change_1h + (percent_change_1h * 0.50f);
            float rank24h = percent_change_24h + (percent_change_24h * 0.30f);
            float rank7d = percent_change_7d + (percent_change_7d * 0.20f);

            coin.setPiPoints(rank1h + rank24h + rank7d);
        });
    }
}
