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
 * Applies the overall influence calculation to a cryptocurrency.
 *
 * @author Luis Jardim
 */
@Component
public class OverallInfluenceApplicator implements InfluenceApplicator {

    @Override
    public void apply(List<Coin> coins) throws Exception {
        coins.forEach(coin -> {
            float siPoints = coin.getSiPoints();
            float viPoints = coin.getViPoints();
            float piPoints = coin.getPiPoints();

            float siRank = siPoints + (siPoints * 0.60f);
            float piRank = piPoints + (piPoints * 0.40f);
//            float viRank = viPoints + (viPoints * 0.15f);

            coin.setOiPoints(siRank + piRank);
//            coin.setOiPoints(siRank + piRank + viRank);
        });
    }
}
