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

import com.ljardim.cryptodash.cpapi.CryptoPanic;
import com.ljardim.cryptodash.cpapi.domain.CryptoPanicFilter;
import com.ljardim.cryptodash.cpapi.domain.Post;
import com.ljardim.cryptodash.cpapi.domain.Posts;
import com.ljardim.cryptodash.cpapi.domain.Votes;
import com.ljardim.cryptodash.domain.Coin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Applies the social influence calculation to a cryptocurrency.
 *
 * @author Luis Jardim
 */
@Component
public class SocialInfluenceApplicator implements InfluenceApplicator {

    private final CryptoPanic cryptoPanic;

    @Autowired
    public SocialInfluenceApplicator(CryptoPanic cryptoPanic) {
        this.cryptoPanic = cryptoPanic;
    }

    @Override
    public void apply(List<Coin> coins) throws Exception {
        Posts posts = cryptoPanic.retrievePosts(EnumSet.of(CryptoPanicFilter.HOT), Collections.EMPTY_LIST);
        coins.forEach(coin -> coin.setSiPoints(calculateSocialInfluence(posts, coin)));
    }

    private float calculateSocialInfluence(Posts posts, Coin coin) {
        float siPoints = 0.0f;

        List<Post> relevantPosts = getRelevantPosts(coin, posts.getPosts());
        for (Post relevantPost : relevantPosts) {
            Votes votes = relevantPost.getVotes();
            int positive = votes.getPositive();
            int negative = votes.getNegative();
            int important = votes.getImportant();

            Date published_at = relevantPost.getPublished_at();
            long days = TimeUnit.DAYS.convert(published_at.getTime() - new Date().getTime(), TimeUnit.MILLISECONDS) + 1;

            float importantFactor = (float) important / 100f;
            float ageFactor = (float) days / 100f;

            int average = positive - negative;
            float rank = average + (average * importantFactor);
            float ageModifier = rank * ageFactor;

            siPoints += rank - ageModifier;
        }
        return siPoints;
    }

    private List<Post> getRelevantPosts(Coin coin, List<Post> allPosts) {
        String symbol = coin.getSymbol();
        return allPosts
                .stream()
                .filter(post -> {
                    boolean matches = false;
                    if (post != null && post.getCurrencies() != null && !post.getCurrencies().isEmpty()) {
                        matches = post.getCurrencies().stream().anyMatch(currency -> currency.getCode().equalsIgnoreCase(symbol));
                    }
                    return matches;
                })
                .collect(Collectors.toList());
    }
}
