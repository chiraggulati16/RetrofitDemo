package com.example.hp.retrofitdemo;

public class ListItem {
        private String id;
        private String name;
        private String symbol;
        private String percentChange1h;
        private String marketCap;
        private String percentChange24h;
        private String price;
        private String volume24h;
        private String percentChange7d;


        public ListItem(String id, String name, String symbol, String price, String marketCap, String volume24h, String percentChange1h, String percentChange24h, String percentChange7d) {
            this.id = id;
            this.name = name;
            this.symbol = symbol;
            this.percentChange1h = percentChange1h;
            this.marketCap = marketCap;
            this.percentChange24h = percentChange24h;
            this.price = price;
            this.volume24h = volume24h;
            this.percentChange7d = percentChange7d;
        }

        public String getId1() {
            return id;
        }

        public String getName1() {
            return name;
        }

        public String getSymbol1() {
            return symbol;
        }

        public String getPercentChange1h() {
            return percentChange1h;
        }

        public String getMarketCap() {
            return marketCap;
        }

        public String getPercentChange24h() {
            return percentChange24h;
        }

        public String getPrice() {
            return price;
        }

        public String getVolume24h() {
            return volume24h;
        }

        public String getPercentChange7d() {
            return percentChange7d;
        }
    }

