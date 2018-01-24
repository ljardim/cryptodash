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
package com.ljardim.cryptodash.domain;

/**
 * Domain object to represent a cryptocurrency and its relevant details and ranking.
 *
 * @author Luis Jardim
 */
public class Coin {
    private String symbol;
    private String name;

    private float volume24h;

    private float priceUSD;
    private float priceBTC;

    private float percent_change_1h;
    private float percent_change_24h;
    private float percent_change_7d;

    /**
     * Social Influence (News and Social media influence on the coin)
     */
    private float siPoints;
    /**
     * Volume Influence (The 24h USD Volume ranking)
     */
    private float viPoints;
    /**
     * Price Influence (Price fluctuation influence over 1h, 24h and 7d periods)
     */
    private float piPoints;
    /**
     * Overall Influence (The calculated combined influence)
     */
    private float oiPoints;

    public Coin(String symbol,
                String name,
                float volume24h,
                float priceUSD,
                float priceBTC,
                float percent_change_1h,
                float percent_change_24h,
                float percent_change_7d) {
        this.symbol = symbol;
        this.name = name;
        this.volume24h = volume24h;
        this.priceUSD = priceUSD;
        this.priceBTC = priceBTC;
        this.percent_change_1h = percent_change_1h;
        this.percent_change_24h = percent_change_24h;
        this.percent_change_7d = percent_change_7d;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getVolume24h() {
        return volume24h;
    }

    public void setVolume24h(float volume24h) {
        this.volume24h = volume24h;
    }

    public float getPriceUSD() {
        return priceUSD;
    }

    public void setPriceUSD(float priceUSD) {
        this.priceUSD = priceUSD;
    }

    public float getPriceBTC() {
        return priceBTC;
    }

    public void setPriceBTC(float priceBTC) {
        this.priceBTC = priceBTC;
    }

    public float getPercent_change_1h() {
        return percent_change_1h;
    }

    public void setPercent_change_1h(float percent_change_1h) {
        this.percent_change_1h = percent_change_1h;
    }

    public float getPercent_change_24h() {
        return percent_change_24h;
    }

    public void setPercent_change_24h(float percent_change_24h) {
        this.percent_change_24h = percent_change_24h;
    }

    public float getPercent_change_7d() {
        return percent_change_7d;
    }

    public void setPercent_change_7d(float percent_change_7d) {
        this.percent_change_7d = percent_change_7d;
    }

    public float getSiPoints() {
        return siPoints;
    }

    public void setSiPoints(float siPoints) {
        this.siPoints = siPoints;
    }

    public float getViPoints() {
        return viPoints;
    }

    public void setViPoints(float viPoints) {
        this.viPoints = viPoints;
    }

    public float getPiPoints() {
        return piPoints;
    }

    public void setPiPoints(float piPoints) {
        this.piPoints = piPoints;
    }

    public float getOiPoints() {
        return oiPoints;
    }

    public void setOiPoints(float oiPoints) {
        this.oiPoints = oiPoints;
    }

    @Override
    public String toString() {
        return "Coin{" + "symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", volume24h='" + volume24h + '\'' +
                ", priceUSD=" + priceUSD +
                ", priceBTC=" + priceBTC +
                ", percent_change_1h=" + percent_change_1h +
                ", percent_change_24h=" + percent_change_24h +
                ", percent_change_7d=" + percent_change_7d +
                ", siPoints=" + siPoints +
                ", viPoints=" + viPoints +
                ", piPoints=" + piPoints +
                ", oiPoints=" + oiPoints +
                '}';
    }
}
